DROP TABLE IF EXISTS owners CASCADE;
DROP TABLE IF EXISTS wallets CASCADE;
DROP TABLE IF EXISTS currency CASCADE;
DROP TABLE IF EXISTS groups CASCADE;
DROP TABLE IF EXISTS expenses CASCADE;

CREATE TABLE groups (
	id serial PRIMARY KEY,
    name varchar(15) NOT NULL
);

CREATE TABLE owners (
	id serial PRIMARY KEY,
    first_name varchar(15) NOT NULL,
    last_name varchar(15) NOT NULL
);

CREATE TABLE currency (
	id serial PRIMARY KEY,
	name varchar(15) NOT NULL
);

CREATE TABLE wallets (
	id serial PRIMARY KEY,
	owner_id INT, 
	currency_id INT, 
	create_date DATE NOT NULL,
	create_time TIME NOT NULL,
    CONSTRAINT fk_owner_id FOREIGN KEY (owner_id) REFERENCES owners(id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_currency_id FOREIGN KEY (currency_id) REFERENCES currency(id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE expenses (
	id serial PRIMARY KEY,
    price INT,    	
    group_id INT,    	
    wallet_id INT,    	
    date DATE NOT NULL,
	time TIME NOT NULL,
    CONSTRAINT fk_group_id FOREIGN KEY (group_id) REFERENCES groups(id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_wallet_id FOREIGN KEY (wallet_id) REFERENCES wallets(id) ON UPDATE CASCADE ON DELETE CASCADE
);