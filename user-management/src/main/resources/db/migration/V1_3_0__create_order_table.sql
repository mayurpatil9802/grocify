CREATE TABLE `grocify`.`order_detail` (
  `order_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(20) NOT NULL,
  `order_details` JSON NULL,
  `delivery_by` BIGINT(20) NULL,
  `status` ENUM('DELIVERED', 'PENDING', 'PROGRESS', 'CANCLED') NULL,
  `order_take_time` DATE NULL,
  `order_delivered_time` DATE NULL,
  PRIMARY KEY (`order_id`));
