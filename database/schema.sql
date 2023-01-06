CREATE SCHEMA IF NOT EXISTS `eshop`;
USE `eshop` ;

CREATE TABLE IF NOT EXISTS customers (
  name varchar(32) not null,
  address varchar(128) not null,
  email varchar(128) not null,

  primary key(name)
);

CREATE TABLE IF NOT EXISTS orders (
  order_id varchar(8) not null,
  name varchar(32) not null,
  order_date date not null,

  primary key(order_id),
  foreign key(name) references customers(name)
);

CREATE TABLE IF NOT EXISTS line_items (
  item_id int auto_increment,
  order_id varchar(8) not null,
  item varchar(128) not null,
  quantity int not null,

  primary key(item_id),
  foreign key(order_id) references orders(order_id)
);

CREATE TABLE IF NOT EXISTS order_status (
  order_id varchar(8) not null,
  delivery_id varchar(128) not null,
  status enum('pending', 'dispatched') default 'pending',
  status_update varchar(32) not null,

  primary key(delivery_id),
  foreign key(order_id) references orders(order_id)
);

INSERT INTO `customers` (`name`, `address`, `email`) VALUES ('fred', '201 Cobblestone Lane', 'fredflintstone@bedrock.com');
INSERT INTO `customers` (`name`, `address`, `email`) VALUES ('sherlock', '221B Baker Street, London', 'sherlock@consultingdetective.org');
INSERT INTO `customers` (`name`, `address`, `email`) VALUES ('spongebob', '124 Conch Street, Bikini Bottom', 'spongebob@yahoo.com');
INSERT INTO `customers` (`name`, `address`, `email`) VALUES ('jessica', '698 Candlewood Land, Cabot Cove', 'fletcher@gmail.com');
INSERT INTO `customers` (`name`, `address`, `email`) VALUES ('dursley', '4 Privet Drive, Little Whinging, Surrey', 'dursley@gmail.com');