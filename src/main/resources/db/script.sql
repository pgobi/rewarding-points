DROP TABLE carts IF EXISTS;
DROP TABLE customers IF EXISTS;
DROP TABLE products IF EXISTS;
DROP TABLE transactions IF EXISTS;
DROP TABLE orders IF EXISTS;

CREATE TABLE products (id long auto_increment,product_name VARCHAR2(50),price numeric ,currency VARCHAR2(10), created_date DateTime default CURRENT_TIMESTAMP);
CREATE TABLE customers(id long auto_increment,first_name VARCHAR2(50) ,last_name VARCHAR2(50) ,registration_date DateTime default CURRENT_TIMESTAMP, update_date DateTime);
CREATE TABLE carts (id long auto_increment,order_id long, product_id long, cart_number VARCHAR2(35),quantity numeric, created_date TIMESTAMP default CURRENT_TIMESTAMP);
CREATE TABLE orders (id long auto_increment,customer_id long ,cart_number VARCHAR2(35),transaction_id long, order_date DateTime default CURRENT_TIMESTAMP);
CREATE TABLE transactions (id long auto_increment, amount numeric,rewards_points numeric, currency VARCHAR2(10),transaction_date DateTime default CURRENT_TIMESTAMP);

INSERT INTO products(id,product_name,price,currency) values(1, 'phone', 99.0,'$');
INSERT INTO products(id,product_name,price,currency) values(2, 'shoes', 20.0,'$');
INSERT INTO products(id,product_name,price,currency) values(3, 'tv', 300.0,'$');
INSERT INTO products(id,product_name,price,currency) values(4, 'candy' ,3.0,'$');
INSERT INTO products(id,product_name,price,currency) values(5, 'shoes', 101.0,'$');
INSERT INTO products(id,product_name,price,currency) values(6, 'dinner', 120.0,'$');
INSERT INTO customers(id,first_name,last_name) values(1, 'Olivia' ,'Boyes');
INSERT INTO customers(id,first_name,last_name) values(2, 'Jane','Wyatt');
INSERT INTO carts(order_id,product_id,quantity,cart_number) values (1,1,1,'pqrstuvwxyzABCDEFGHIJKLMRSTUVWXYZ01');
INSERT INTO carts(order_id,product_id,quantity,cart_number) values (2,1,2,'pqrstuvwxyzABCDEFGHIJKLMRSTUVWXYZ01');
INSERT INTO orders(customer_id,transaction_id, order_date,cart_number) values ( 1, 1,'2024-02-01 00:00:00','pqrstuvwxyzABCDEFGHIJKLMRSTUVWXYZ01');
INSERT INTO transactions(amount,currency,rewards_points,transaction_date) values (120.0,'$',90,'2024-02-18 23:25:01.590226');

COMMIT;