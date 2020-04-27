DROP TABLE IF EXISTS favorites;

CREATE TABLE favorites (
	 id	bigint	NOT NULL  AUTO_INCREMENT PRIMARY KEY ,
	 user_id	bigint	NOT NULL,
	 name 	varchar(255)	NOT NULL,
     FOREIGN KEY (user_id) REFERENCES user (id)
);