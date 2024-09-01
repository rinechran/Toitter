"# Toitter" 

CREATE TABLE

``````
CREATE TABLE `comment` (
	`pk` BIGINT NOT NULL AUTO_INCREMENT,
	`uuid` BINARY(16) NOT NULL,
	`user_uuid` BINARY(16) NOT NULL,
	`tweat_uuid` BINARY(16) NOT NULL,
	`comment` VARCHAR(50) NOT NULL COLLATE 'utf8mb4_bin',
	`parent_comment_uuid` BINARY(16) NULL DEFAULT NULL,
	`is_delete` TINYINT(1) NOT NULL,
	`create_data_at` TIMESTAMP NOT NULL,
	`update_data_at` DATETIME NOT NULL,
	PRIMARY KEY (`pk`) USING BTREE,
	INDEX `uuid` (`uuid`) USING BTREE,
	INDEX `isdelete_createAt` (`is_delete`, `create_data_at` DESC) USING BTREE,
	INDEX `parent_uuid` (`parent_comment_uuid`) USING BTREE
)
COMMENT='comment'
COLLATE='utf8mb4_bin'
ENGINE=InnoDB
;

CREATE TABLE `tweat` (
	`pk` BIGINT NOT NULL AUTO_INCREMENT,
	`uuid` BINARY(16) NOT NULL,
	`user_uuid` BINARY(16) NOT NULL,
	`msg` VARCHAR(100) NOT NULL COLLATE 'utf8mb4_bin',
	`is_delete` TINYINT(1) NOT NULL,
	`create_data_at` TIMESTAMP NOT NULL,
	`update_data_at` TIMESTAMP NOT NULL,
	PRIMARY KEY (`pk`) USING BTREE,
	INDEX `uuid` (`uuid`) USING BTREE,
	INDEX `isdelete_createAt` (`is_delete`, `create_data_at` DESC) USING BTREE,
	INDEX `idx_tweat_user_uuid` (`user_uuid`) USING BTREE,
	INDEX `idx_tweat_msg` (`msg`) USING BTREE,
	FULLTEXT INDEX `idx_tweat_msg_fulltext` (`msg`)
)
COMMENT='tweat'
COLLATE='utf8mb4_bin'
ENGINE=InnoDB
;
CREATE TABLE `users` (
	`pk` BIGINT NOT NULL AUTO_INCREMENT,
	`uuid` BINARY(16) NOT NULL,
	`name` VARCHAR(20) NOT NULL COLLATE 'utf8mb4_general_ci',
	`email` VARCHAR(50) NOT NULL COLLATE 'utf8mb4_general_ci',
	`password` VARCHAR(50) NOT NULL COLLATE 'utf8mb4_bin',
	`is_delete` TINYINT(1) NOT NULL,
	`create_data_at` TIMESTAMP NOT NULL,
	`update_data_at` TIMESTAMP NOT NULL,
	PRIMARY KEY (`pk`) USING BTREE,
	INDEX `uuid` (`uuid`) USING BTREE,
	INDEX `idx_users_name` (`name`) USING BTREE
)
COMMENT='users'
COLLATE='utf8mb4_bin'
ENGINE=InnoDB
;
```
