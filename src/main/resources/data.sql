DROP TABLE IF EXISTS payment;

CREATE TABLE payment (
  id INT AUTO_INCREMENT PRIMARY KEY,
  trx_no INT AUTO_INCREMENT NOT NULL,
  cart_no INT NOT NULL,
  cust_no INT NOT NULL,
  total_price DECIMAL(19,4) NOT NULL,
  trx_date DATE NOT NULL
  
);

DROP TABLE IF EXISTS cart;

CREATE TABLE cart (
  id INT AUTO_INCREMENT PRIMARY KEY,
  cart_no INT NOT NULL,
  product_no INT NOT NULL,
  qty INT NOT NULL,
  paid_flag INT NOT NULL
);

INSERT INTO cart (cart_no, product_no, qty, paid_flag) VALUES
  (101, 100001, 1, 0), 
  (101, 100002, 2, 0), 
  (102, 100001, 5, 0);