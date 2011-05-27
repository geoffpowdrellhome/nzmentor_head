BEGIN;

/* currency table */
CREATE TABLE currency
(
	code VARCHAR(10) NOT NULL,
	symbol VARCHAR(10) NOT NULL,
	name VARCHAR(30) NOT NULL
);

ALTER TABLE currency ADD CONSTRAINT pk_currency PRIMARY KEY (code);
CREATE UNIQUE INDEX ix_currency_code ON currency(code);
INSERT INTO currency(code, symbol, name) values('NZD', '$', 'New Zealand dollars');


/* country table */
CREATE SEQUENCE country_id_seq INCREMENT 1;
CREATE TABLE country
(
	id INTEGER NOT NULL DEFAULT nextval('country_id_seq'::regclass),
	code VARCHAR(10) NOT NULL,
	name VARCHAR(50) NOT NULL,
	currency_code VARCHAR(10) NOT NULL
);

ALTER TABLE country ADD CONSTRAINT pk_country PRIMARY KEY (id);
ALTER TABLE country ADD CONSTRAINT fk_country_currency FOREIGN KEY (currency_code) REFERENCES currency (code) ON UPDATE NO ACTION ON DELETE NO ACTION;
CREATE UNIQUE INDEX ix_country_code ON country(code);
CREATE UNIQUE INDEX ix_country_name ON country(name);

INSERT INTO country(id, code, name, currency_code)
VALUES( nextval('country_id_seq'), 'NZ', 'New Zealand', 'NZD');


/* island table */
CREATE SEQUENCE island_id_seq INCREMENT 1;
CREATE TABLE island
(
	id INTEGER NOT NULL DEFAULT nextval('island_id_seq'::regclass),
	name VARCHAR(30) NOT NULL,
	country_id INTEGER NOT NULL
);

ALTER TABLE island ADD CONSTRAINT pk_island PRIMARY KEY (id);
ALTER TABLE island ADD CONSTRAINT fk_island_country FOREIGN KEY (country_id) REFERENCES country(id) ON UPDATE NO ACTION ON DELETE NO ACTION;
CREATE UNIQUE INDEX ix_island_name ON island(name);

INSERT INTO island(id, name, country_id) 
VALUES( nextval('island_id_seq'), 'North', (SELECT id from country where code = 'NZ'));
INSERT INTO island(id, name, country_id) 
VALUES( nextval('island_id_seq'), 'South', (SELECT id from country where code = 'NZ'));


/* region table */
CREATE SEQUENCE region_id_seq INCREMENT 1;
CREATE TABLE region
(
	id INTEGER NOT NULL DEFAULT nextval('region_id_seq'::regclass),
    name VARCHAR(50) NOT NULL,
	island_id INTEGER NOT NULL,
	area_in_square_kms NUMERIC(10, 2) NOT NULL DEFAULT 0,
	population NUMERIC(10, 2) NOT NULL DEFAULT 0,
    regional_council_name VARCHAR(50) NOT NULL,
	created TIMESTAMP NOT NULL DEFAULT 'now',
	createdby VARCHAR(50) NOT NULL DEFAULT 'sysadm',
	updated TIMESTAMP NOT NULL DEFAULT 'now',
	updatedby VARCHAR(50) NOT NULL DEFAULT 'sysadm'
);

ALTER TABLE region ADD CONSTRAINT pk_region PRIMARY KEY (id);
ALTER TABLE region ADD CONSTRAINT fk_region_island FOREIGN KEY (island_id) REFERENCES island(id) ON UPDATE NO ACTION ON DELETE NO ACTION;
CREATE UNIQUE INDEX ix_region_name ON region(name);
CREATE INDEX region_search_1 ON region(name);


/* popularity ranking table */
CREATE SEQUENCE popularity_ranking_type_id_seq INCREMENT 1;
CREATE TABLE popularity_ranking_type
(
	id INTEGER NOT NULL DEFAULT nextval('popularity_ranking_type_id_seq'::regclass),
        name VARCHAR(50) NOT NULL,
        description VARCHAR(150) NOT NULL,
	created TIMESTAMP NOT NULL DEFAULT 'now',
	createdby VARCHAR(50) NOT NULL DEFAULT 'sysadm',
	updated TIMESTAMP NOT NULL DEFAULT 'now',
	updatedby VARCHAR(50) NOT NULL DEFAULT 'sysadm'
);
ALTER TABLE popularity_ranking_type ADD CONSTRAINT pk_popularity_ranking_type PRIMARY KEY (id);
CREATE UNIQUE INDEX ix_popularity_ranking_type_name ON popularity_ranking_type(name);

INSERT INTO popularity_ranking_type(id, name, description)
VALUES( nextval('popularity_ranking_type_id_seq'), 'Low Popularity Rating', 'Not Popular');
INSERT INTO popularity_ranking_type(id, name, description)
VALUES( nextval('popularity_ranking_type_id_seq'), 'Popular with some', 'Popular with some');
INSERT INTO popularity_ranking_type(id, name, description)
VALUES( nextval('popularity_ranking_type_id_seq'), 'Popular with < 30 age group', 'Popular with some');
INSERT INTO popularity_ranking_type(id, name, description)
VALUES( nextval('popularity_ranking_type_id_seq'), 'Popular with 30+ age group', 'Popular with some');
INSERT INTO popularity_ranking_type(id, name, description)
VALUES( nextval('popularity_ranking_type_id_seq'), 'Popular with 60+ age group', 'Popular with some');
INSERT INTO popularity_ranking_type(id, name, description)
VALUES( nextval('popularity_ranking_type_id_seq'), 'Popular', 'Popular');
INSERT INTO popularity_ranking_type(id, name, description)
VALUES( nextval('popularity_ranking_type_id_seq'), 'Very Popular', 'Very Popular');
INSERT INTO popularity_ranking_type(id, name, description)
VALUES( nextval('popularity_ranking_type_id_seq'), 'Extremely Popular', 'Extremely Popular');




/* site type table */
CREATE SEQUENCE site_type_id_seq INCREMENT 1;
CREATE TABLE site_type
(
	id INTEGER NOT NULL DEFAULT nextval('site_type_id_seq'::regclass),
    name VARCHAR(50) NOT NULL,
    description VARCHAR(150) NOT NULL,
	created TIMESTAMP NOT NULL DEFAULT 'now',
	createdby VARCHAR(50) NOT NULL DEFAULT 'sysadm',
	updated TIMESTAMP NOT NULL DEFAULT 'now',
	updatedby VARCHAR(50) NOT NULL DEFAULT 'sysadm'
);

ALTER TABLE site_type ADD CONSTRAINT pk_site_type PRIMARY KEY (id);
CREATE UNIQUE INDEX ix_site_type_name ON site_type(name);

INSERT INTO site_type(id, name, description)
VALUES( nextval('site_type_id_seq'), 'Rental Car Depot', 'Rental Car Depot');
INSERT INTO site_type(id, name, description)
VALUES( nextval('site_type_id_seq'), 'Activity Venue', 'Activity Venue');
INSERT INTO site_type(id, name, description)
VALUES( nextval('site_type_id_seq'), 'Hotel', 'Hotel');
INSERT INTO site_type(id, name, description)
VALUES( nextval('site_type_id_seq'), 'Motel', 'Motel');
INSERT INTO site_type(id, name, description)
VALUES( nextval('site_type_id_seq'), 'Cabin', 'Cabin');
INSERT INTO site_type(id, name, description)
VALUES( nextval('site_type_id_seq'), 'Lodge', 'Lodge');
INSERT INTO site_type(id, name, description)
VALUES( nextval('site_type_id_seq'), 'Historical Landmark', 'Historical landmark');




/* site table */
CREATE SEQUENCE site_id_seq INCREMENT 1;
CREATE TABLE site
(
	id INTEGER NOT NULL DEFAULT nextval('site_id_seq'::regclass),
    name VARCHAR(50) NOT NULL,
    description VARCHAR(150) NOT NULL,
    locality_description VARCHAR(50) NOT NULL,
	region_id INTEGER NOT NULL,
	site_type_id INTEGER NOT NULL,
	area_in_square_kms NUMERIC(10, 2) NOT NULL DEFAULT 0,
	population NUMERIC(10, 2) NOT NULL DEFAULT 0,
    regional_council_name VARCHAR(50) NOT NULL,
	created TIMESTAMP NOT NULL DEFAULT 'now',
	createdby VARCHAR(50) NOT NULL DEFAULT 'sysadm',
	updated TIMESTAMP NOT NULL DEFAULT 'now',
	updatedby VARCHAR(50) NOT NULL DEFAULT 'sysadm'
);

ALTER TABLE site ADD CONSTRAINT pk_site PRIMARY KEY (id);
ALTER TABLE site ADD CONSTRAINT fk_site_region FOREIGN KEY (region_id) REFERENCES region(id) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE site ADD CONSTRAINT fk_site_site_type FOREIGN KEY (site_type_id) REFERENCES site_type(id) ON UPDATE NO ACTION ON DELETE NO ACTION;

CREATE UNIQUE INDEX ix_site_name ON site(name);
CREATE INDEX site_search_1 ON site(name);



/* event table */
CREATE SEQUENCE event_id_seq INCREMENT 1;
CREATE TABLE event
(
	id INTEGER NOT NULL DEFAULT nextval('event_id_seq'::regclass),
    name VARCHAR(50) NOT NULL,
    description VARCHAR(150) NOT NULL,
	site_id INTEGER NOT NULL,
	popularity_ranking_type_id INTEGER NOT NULL,
	startTime TIMESTAMP NOT NULL,
	endTime TIMESTAMP NOT NULL,
	averageTempertureAtEventTime NUMERIC(10, 2),
	averageMonthlyRainFallAtEventTime NUMERIC(10, 2),
	suggestedClothingAtEventTime VARCHAR(150),
	suggestedFootwearAtEventTime VARCHAR(150),
	suggestedHeadwearAtEventTime VARCHAR(150),
	created TIMESTAMP NOT NULL DEFAULT 'now',
	createdby VARCHAR(50) NOT NULL DEFAULT 'sysadm',
	updated TIMESTAMP NOT NULL DEFAULT 'now',
	updatedby VARCHAR(50) NOT NULL DEFAULT 'sysadm'
);

ALTER TABLE event ADD CONSTRAINT pk_event PRIMARY KEY (id);
ALTER TABLE event ADD CONSTRAINT fk_event_site FOREIGN KEY (site_id) REFERENCES site(id) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE event ADD CONSTRAINT fk_event_popularity_ranking_type FOREIGN KEY (popularity_ranking_type_id) REFERENCES popularity_ranking_type(id) ON UPDATE NO ACTION ON DELETE NO ACTION;

CREATE UNIQUE INDEX ix_event_name ON event(name);
CREATE INDEX event_search_1 ON event(name);


COMMIT;