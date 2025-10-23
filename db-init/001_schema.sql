create table if not exists products (
  sku varchar(64) primary key,
  name text not null,
  category text not null,
  price numeric(12,2) not null
);

insert into products (sku, name, category, price) values
  ('SKU-001','Wireless Mouse','accessories',15.99)
on conflict (sku) do nothing;
