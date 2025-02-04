CREATE TABLE `grocify`.`product_detail` (
  `id` BIGINT(20) NOT NULL,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(200) NULL,
  `price` INT NULL,
  `unit` VARCHAR(10) NULL,
  `available_unit` INT NULL,
  `store_id` INT NULL,
  `metadata` JSON NULL,
  `image_url` VARCHAR(200) NULL,
  `status` BIT NULL DEFAULT 1,
  `created_at` DATE NULL ,
  `updated_at` DATE NULL ,
  PRIMARY KEY (`id`));