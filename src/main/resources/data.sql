INSERT INTO owners (first_name, last_name) VALUES ('Ivan', 'Petrov');
INSERT INTO owners (first_name, last_name) VALUES ('Egor', 'Ivanov');
INSERT INTO owners (first_name, last_name) VALUES ('Oleg', 'Blinov');
INSERT INTO owners (first_name, last_name) VALUES ('Semen', 'Vasin');
INSERT INTO owners (first_name, last_name) VALUES ('Nikolay', 'Stupin');
INSERT INTO owners (first_name, last_name) VALUES ('Irina', 'Fedorova');

INSERT INTO groups (name) VALUES ('Food');
INSERT INTO groups (name) VALUES ('Education');
INSERT INTO groups (name) VALUES ('Travel');
INSERT INTO groups (name) VALUES ('Entertainment');

INSERT INTO currency (name) VALUES ('Dollar');
INSERT INTO currency (name) VALUES ('Euro');
INSERT INTO currency (name) VALUES ('Ruble');

INSERT INTO wallets (owner_id, currency_id, create_date, create_time) values (1, 1, '2020-09-07', '09:00:00');
INSERT INTO wallets (owner_id, currency_id, create_date, create_time) values (2, 1, '2020-09-10', '19:00:00');
INSERT INTO wallets (owner_id, currency_id, create_date, create_time) values (3, 1, '2020-09-11', '10:00:00');
INSERT INTO wallets (owner_id, currency_id, create_date, create_time) values (4, 2, '2020-09-12', '09:10:00');
INSERT INTO wallets (owner_id, currency_id, create_date, create_time) values (5, 3, '2020-09-13', '12:00:00');
INSERT INTO wallets (owner_id, currency_id, create_date, create_time) values (6, 3, '2020-09-14', '02:00:00');
INSERT INTO wallets (owner_id, currency_id, create_date, create_time) values (6, 1, '2020-09-15', '12:00:00');

INSERT INTO expenses (price, group_id, wallet_id, date, time) values (10011, 1, 1, '2021-09-07', '09:00:00');
INSERT INTO expenses (price, group_id, wallet_id, date, time) values (20011, 2, 2, '2021-09-08', '08:00:00');
INSERT INTO expenses (price, group_id, wallet_id, date, time) values (30011, 2, 3, '2021-09-09', '07:00:00');
INSERT INTO expenses (price, group_id, wallet_id, date, time) values (40011, 3, 4, '2021-09-10', '06:00:00');
INSERT INTO expenses (price, group_id, wallet_id, date, time) values (50011, 3, 5, '2021-09-11', '19:00:00');
INSERT INTO expenses (price, group_id, wallet_id, date, time) values (60011, 4, 6, '2021-09-12', '19:10:00');
INSERT INTO expenses (price, group_id, wallet_id, date, time) values (33333, 1, 7, '2021-09-12', '23:10:00');