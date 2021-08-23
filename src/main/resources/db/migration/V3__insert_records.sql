INSERT INTO CART (id, user_id, product_id, quantity, cart_product_status) VALUES (1, 100, 2, 4,'ACTIVE');
INSERT INTO CART (id, user_id, product_id, quantity, cart_product_status) VALUES (2, 100, 1, 1,'ACTIVE');

INSERT INTO CART (id, user_id, product_id, quantity, cart_product_status) VALUES (3, 200, 3, 1,'INACTIVE');
INSERT INTO CART (id, user_id, product_id, quantity, cart_product_status) VALUES (4, 200, 4, 2,'INACTIVE');



INSERT INTO ORDERS (id, user_id, amount, payment_status, created_date) VALUES (1, 200, 90, 'SUCCESS', '2021-08-21');