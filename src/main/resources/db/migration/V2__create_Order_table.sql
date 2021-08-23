CREATE TABLE ORDERS(
id BIGINT NOT NULL auto_increment,
user_id BIGINT NOT NULL,
amount DECIMAL(10,2) NOT NULL,
payment_status VARCHAR(50),
product_count INT,
created_date DATE,
PRIMARY KEY (id)
);	
