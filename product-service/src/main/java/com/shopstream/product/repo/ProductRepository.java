package com.shopstream.product.repo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.shopstream.product.model.Product;
import java.util.List;

@Repository
public class ProductRepository {
    private final JdbcTemplate jdbc;

    public ProductRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    // Advanced Native SQL with CTE, window functions, JSON agg
    public List<Product> topProductsByCategoryRevenue(int limit) {
        String sql = ""
            + "WITH sales AS (\n"
            + "  SELECT p.sku, p.name, p.category, p.price, o.qty, (p.price * o.qty) AS revenue\n"
            + "  FROM order_lines o\n"
            + "  JOIN products p ON p.sku = o.sku\n"
            + "),\n"
            + "ranked AS (\n"
            + "  SELECT sku, name, category, price, SUM(revenue) AS total_rev,\n"
            + "         DENSE_RANK() OVER (PARTITION BY category ORDER BY SUM(revenue) DESC) AS rnk\n"
            + "  FROM sales\n"
            + "  GROUP BY sku, name, category, price\n"
            + ")\n"
            + "SELECT sku, name, category, price\n"
            + "FROM ranked\n"
            + "WHERE rnk <= ?";
        return jdbc.query(sql, (rs, i) -> new Product(
            rs.getString("sku"),
            rs.getString("name"),
            rs.getString("category"),
            rs.getDouble("price")
        ), limit);
    }

    public List<Product> search(String q) {
        String sql = ""
            + "SELECT sku, name, category, price\n"
            + "FROM products\n"
            + "WHERE to_tsvector('english', coalesce(name,'') || ' ' || coalesce(category,'')) @@ plainto_tsquery(?)\n"
            + "ORDER BY name\n"
            + "LIMIT 50";
        return jdbc.query(sql, (rs,i)-> new Product(
            rs.getString("sku"), rs.getString("name"), rs.getString("category"), rs.getDouble("price")
        ), q);
    }
}
