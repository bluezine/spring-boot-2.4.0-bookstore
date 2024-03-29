CREATE TABLE `book` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`title` VARCHAR(100) NOT NULL COLLATE 'utf8mb4_bin',
	`status` INT(6) NOT NULL DEFAULT '0',
	`auth_name` VARCHAR(100) NOT NULL COLLATE 'utf8mb4_bin',
	`pub_cmpy` VARCHAR(100) NOT NULL COLLATE 'utf8mb4_bin',
	`pub_year` INT(4) NOT NULL,
	`pub_date` DATETIME NOT NULL,
	`isbn` VARCHAR(13) NOT NULL COLLATE 'utf8mb4_bin',
	PRIMARY KEY (`id`) USING BTREE
);

CREATE TABLE `category` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`pid` INT(11) NULL DEFAULT NULL,
	`rid` INT(11) NULL DEFAULT NULL,
	`title` VARCHAR(100) NOT NULL COLLATE 'utf8mb4_bin',
	`status` INT(6) NOT NULL DEFAULT '0',
	`rgst_date` DATETIME NULL DEFAULT sysdate(),
	`updt_date` DATETIME NULL DEFAULT sysdate(),
	`isleaf` INT(1) NULL DEFAULT NULL,
	`sort` INT(11) NULL DEFAULT NULL,
	PRIMARY KEY (`id`) USING BTREE,
	INDEX `KM_PID_KM_ID` (`pid`) USING BTREE,
	CONSTRAINT `KM_PID_KM_ID` FOREIGN KEY (`pid`) REFERENCES `bookstore`.`category` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
)