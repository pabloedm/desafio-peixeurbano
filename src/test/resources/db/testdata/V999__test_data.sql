INSERT INTO deal (id, title, text, create_date, publish_date, end_date, url, total_sold, type) VALUES
	(1, 'Frigideira de Alumínio', 'Frigideira de Alumínio com Revestimento Cerâmico de 20cm, 24cm ou 28cm.', '2019-12-04 21:00:00', '2019-12-04 22:00:00', '2020-12-31 21:00:00', '/frigideira+de+aluminio', 0, 'PRODUCT'),
	(2, 'Frigideira de Barro', 'Frigideira de Barro com Revestimento Cerâmico de 20cm, 24cm ou 28cm.', '2019-12-04 21:00:00', '2019-12-04 22:00:00', '2020-12-31 21:00:00', '/frigideira+de+barro', 0, 'PRODUCT');
	
INSERT INTO buy_option (id, title, normal_price, sale_price, percentage_discount, quantity_coupon, start_date, end_date, deal_id) VALUES
	(1, 'Tamanho 20cm - R$ 90', 100.0, 90.0, 10.0, 30, '2019-12-04 23:00:00', null, 1);