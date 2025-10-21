CREATE TABLE IF NOT EXISTS products (
  sku TEXT PRIMARY KEY,
  name TEXT NOT NULL,
  category TEXT NOT NULL,
  price NUMERIC NOT NULL
);

CREATE TABLE IF NOT EXISTS order_lines (
  id BIGSERIAL PRIMARY KEY,
  order_id TEXT NOT NULL,
  sku TEXT NOT NULL REFERENCES products(sku),
  qty INT NOT NULL CHECK (qty > 0),
  created_at TIMESTAMPTZ DEFAULT now()
);

INSERT INTO products (sku, name, category, price) VALUES
('SKU-001','Wireless Mouse','accessories', 15.99),
('SKU-002','Mechanical Keyboard','accessories', 79.99),
('SKU-101','Gaming Laptop','computers', 1299.00),
('SKU-102','Ultrabook','computers', 999.00)
ON CONFLICT DO NOTHING;

INSERT INTO order_lines(order_id, sku, qty) VALUES
('O-1','SKU-001',3),
('O-2','SKU-002',2),
('O-3','SKU-101',1),
('O-4','SKU-001',5);
