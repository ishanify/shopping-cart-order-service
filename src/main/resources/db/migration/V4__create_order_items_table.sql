CREATE TABLE ORDER_ITEMS(
id BIGINT NOT NULL auto_increment,
order_id BIGINT NOT NULL,
product_id INT NOT NULL,
quantity INT NOT NULL,
product_amount DECIMAL(10,2),
PRIMARY KEY(id)

);	
