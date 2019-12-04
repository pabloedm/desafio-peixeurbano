CREATE TABLE deal (
	id INT NOT NULL AUTO_INCREMENT,
	title VARCHAR(150) NOT NULL,
	text TEXT,
	create_date DATETIME NOT NULL,
	publish_date DATETIME,
	end_date DATETIME,
	url VARCHAR(150) NOT NULL UNIQUE,
	total_sold BIGINT NOT NULL DEFAULT 0,
	type VARCHAR(30) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE buy_option (
	id INT NOT NULL AUTO_INCREMENT,
	title VARCHAR(150) NOT NULL,
	normal_price DOUBLE NOT NULL,
	sale_price DOUBLE NOT NULL, 
	percentage_discount DOUBLE NOT NULL,
	quantity_coupon BIGINT NOT NULL,
	start_date DATETIME,
	end_date DATETIME,
	deal_id INT,
	PRIMARY KEY (id),
	CONSTRAINT fk_buy_option_deal FOREIGN KEY (deal_id) REFERENCES deal (id)
);