DROP TABLE IF EXISTS payment;

CREATE TABLE payment (
  id INT AUTO_INCREMENT PRIMARY KEY,
  trx_no INT AUTO_INCREMENT NOT NULL,
  cart_no INT NOT NULL,
  cust_no INT NOT NULL,
  total_price DECIMAL(19,4) NOT NULL,
  trx_date DATE NOT NULL,
  created_date DATETIME DEFAULT NOT NULL,
  modified_date DATETIME DEFAULT NOT NULL
);

DROP TABLE IF EXISTS cart;

CREATE TABLE cart (
  id INT AUTO_INCREMENT PRIMARY KEY,
  cart_no INT NOT NULL,
  product_no INT NOT NULL,
  qty INT NOT NULL,
  paid_flag INT NOT NULL,
  created_date DATETIME DEFAULT NOT NULL,
  modified_date DATETIME DEFAULT NOT NULL
);

INSERT INTO cart (cart_no, product_no, qty, paid_flag, created_date, modified_date) VALUES
  (101, 100001, 1, 0, '2020-12-24 00:00:00', '2020-12-24 04:03:00'), 
  (101, 100002, 2, 0, '2020-12-24 00:00:00', '2020-12-24 04:03:00'), 
  (102, 100001, 5, 0, '2020-12-24 00:00:00', '2020-12-24 04:03:00');