

CREATE TABLE CART(
id BIGINT NOT NULL auto_increment,
user_id BIGINT ,
product_id INT ,
quantity INT ,
cart_product_status VARCHAR(50) ,
PRIMARY KEY (id)
);	
