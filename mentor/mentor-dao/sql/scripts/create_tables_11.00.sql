﻿BEGIN;

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
	created_by VARCHAR(50) NOT NULL DEFAULT 'sysadm',
	updated TIMESTAMP NOT NULL DEFAULT 'now',
	updated_by VARCHAR(50) NOT NULL DEFAULT 'sysadm'
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
	created_by VARCHAR(50) NOT NULL DEFAULT 'sysadm',
	updated TIMESTAMP NOT NULL DEFAULT 'now',
	updated_by VARCHAR(50) NOT NULL DEFAULT 'sysadm'
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



/* item_type table */
CREATE SEQUENCE location_type_id_seq INCREMENT 1;
CREATE TABLE location_type
(
	id INTEGER NOT NULL DEFAULT nextval('location_type_id_seq'::regclass),
    name VARCHAR(50) NOT NULL,
    description VARCHAR(150) NOT NULL,
	created TIMESTAMP NOT NULL DEFAULT 'now',
	created_by VARCHAR(50) NOT NULL DEFAULT 'sysadm',
	updated TIMESTAMP NOT NULL DEFAULT 'now',
	updated_by VARCHAR(50) NOT NULL DEFAULT 'sysadm'
);

ALTER TABLE location_type ADD CONSTRAINT pk_location_type PRIMARY KEY (id);
CREATE UNIQUE INDEX ix_location_type_name ON location_type(name);

INSERT INTO location_type(id, name, description)
VALUES( nextval('location_type_id_seq'), 'International City', 'Major International City');
INSERT INTO location_type(id, name, description)
VALUES( nextval('location_type_id_seq'), 'City', 'City');
INSERT INTO location_type(id, name, description)
VALUES( nextval('location_type_id_seq'), 'Town', 'Town');
INSERT INTO location_type(id, name, description)
VALUES( nextval('location_type_id_seq'), 'Village', 'Village');
INSERT INTO location_type(id, name, description)
VALUES( nextval('location_type_id_seq'), 'Tourist Location', 'Tourist Location');



/* event table */
CREATE SEQUENCE location_id_seq INCREMENT 1;
CREATE TABLE location
(
	id INTEGER NOT NULL DEFAULT nextval('location_id_seq'::regclass),
    name VARCHAR(50) NOT NULL,
    location_type_id INTEGER NOT NULL,
    region_id INTEGER NOT NULL,
    description VARCHAR(150) NOT NULL,
    longitude NUMERIC(10, 2) NOT NULL DEFAULT 0,
	latitude NUMERIC(10, 2) NOT NULL DEFAULT 0,
	created TIMESTAMP NOT NULL DEFAULT 'now',
	created_by VARCHAR(50) NOT NULL DEFAULT 'sysadm',
	updated TIMESTAMP NOT NULL DEFAULT 'now',
	updated_by VARCHAR(50) NOT NULL DEFAULT 'sysadm'
);

ALTER TABLE location ADD CONSTRAINT pk_location PRIMARY KEY (id);
ALTER TABLE location ADD CONSTRAINT fk_location_location_type FOREIGN KEY (location_type_id) REFERENCES location_type(id) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE location ADD CONSTRAINT fk_location_region FOREIGN KEY (region_id) REFERENCES region(id) ON UPDATE NO ACTION ON DELETE NO ACTION;

CREATE UNIQUE INDEX ix_location_name ON location(name);
CREATE INDEX location_search_1 ON location(name);



/* site type table */
CREATE SEQUENCE site_type_id_seq INCREMENT 1;
CREATE TABLE site_type
(
	id INTEGER NOT NULL DEFAULT nextval('site_type_id_seq'::regclass),
    name VARCHAR(50) NOT NULL,
    description VARCHAR(150) NOT NULL,
	created TIMESTAMP NOT NULL DEFAULT 'now',
	created_by VARCHAR(50) NOT NULL DEFAULT 'sysadm',
	updated TIMESTAMP NOT NULL DEFAULT 'now',
	updated_by VARCHAR(50) NOT NULL DEFAULT 'sysadm'
);

ALTER TABLE site_type ADD CONSTRAINT pk_site_type PRIMARY KEY (id);
CREATE UNIQUE INDEX ix_site_type_name ON site_type(name);

INSERT INTO site_type(id, name, description)
VALUES( nextval('site_type_id_seq'), 'Vehicle Hire Site', 'Vehicle Hire Site');
INSERT INTO site_type(id, name, description)
VALUES( nextval('site_type_id_seq'), 'Leisure Activity Site', 'Leisure Activity Site');
INSERT INTO site_type(id, name, description)
VALUES( nextval('site_type_id_seq'), 'Accommodation Site', 'Accommodation Site');
INSERT INTO site_type(id, name, description)
VALUES( nextval('site_type_id_seq'), 'Transfer Site', 'Transfer Site');




/* site table */
CREATE SEQUENCE site_id_seq INCREMENT 1;
CREATE TABLE site
(
	id INTEGER NOT NULL DEFAULT nextval('site_id_seq'::regclass),
    name VARCHAR(50) NOT NULL,
    description VARCHAR(150) NOT NULL,
    locality_description VARCHAR(50) NOT NULL,
	location_id INTEGER NOT NULL,
	site_type_id INTEGER NOT NULL,
	longitude NUMERIC(10, 2) NOT NULL DEFAULT 0,
	latitude NUMERIC(10, 2) NOT NULL DEFAULT 0,
	created TIMESTAMP NOT NULL DEFAULT 'now',
	created_by VARCHAR(50) NOT NULL DEFAULT 'sysadm',
	updated TIMESTAMP NOT NULL DEFAULT 'now',
	updated_by VARCHAR(50) NOT NULL DEFAULT 'sysadm'
);

ALTER TABLE site ADD CONSTRAINT pk_site PRIMARY KEY (id);
ALTER TABLE site ADD CONSTRAINT fk_site_location FOREIGN KEY (location_id) REFERENCES location(id) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE site ADD CONSTRAINT fk_site_site_type FOREIGN KEY (site_type_id) REFERENCES site_type(id) ON UPDATE NO ACTION ON DELETE NO ACTION;

CREATE UNIQUE INDEX ix_site_name ON site(name);
CREATE INDEX site_search_1 ON site(name);


/* event type table */
CREATE SEQUENCE event_type_id_seq INCREMENT 1;
CREATE TABLE event_type
(
	id INTEGER NOT NULL DEFAULT nextval('event_type_id_seq'::regclass),
    name VARCHAR(50) NOT NULL,
    description VARCHAR(150) NOT NULL,
	created TIMESTAMP NOT NULL DEFAULT 'now',
	created_by VARCHAR(50) NOT NULL DEFAULT 'sysadm',
	updated TIMESTAMP NOT NULL DEFAULT 'now',
	updated_by VARCHAR(50) NOT NULL DEFAULT 'sysadm'
);

ALTER TABLE event_type ADD CONSTRAINT pk_event_type PRIMARY KEY (id);
CREATE UNIQUE INDEX ix_event_type_name ON event_type(name);

INSERT INTO event_type(id, name, description)
VALUES( nextval('event_type_id_seq'), 'Winter Carnival', 'Winter Carnival');
INSERT INTO event_type(id, name, description)
VALUES( nextval('event_type_id_seq'), 'Spring Wine Tasting', 'Spring Wine Tasting');



/* event table */
CREATE SEQUENCE event_id_seq INCREMENT 1;
CREATE TABLE event
(
	id INTEGER NOT NULL DEFAULT nextval('event_id_seq'::regclass),
    name VARCHAR(50) NOT NULL,
    description VARCHAR(150) NOT NULL,
    event_type_id INTEGER NOT NULL,
	site_id INTEGER NOT NULL,
	startTime TIMESTAMP NOT NULL,
	endTime TIMESTAMP NOT NULL,
	created TIMESTAMP NOT NULL DEFAULT 'now',
	created_by VARCHAR(50) NOT NULL DEFAULT 'sysadm',
	updated TIMESTAMP NOT NULL DEFAULT 'now',
	updated_by VARCHAR(50) NOT NULL DEFAULT 'sysadm'
);

ALTER TABLE event ADD CONSTRAINT pk_event PRIMARY KEY (id);
ALTER TABLE event ADD CONSTRAINT fk_event_site FOREIGN KEY (site_id) REFERENCES site(id) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE event ADD CONSTRAINT fk_event_event_type FOREIGN KEY (event_type_id) REFERENCES event_type(id) ON UPDATE NO ACTION ON DELETE NO ACTION;

CREATE UNIQUE INDEX ix_event_name ON event(name);
CREATE INDEX event_search_1 ON event(name);



/* clothing type table */
CREATE SEQUENCE clothing_type_id_seq INCREMENT 1;
CREATE TABLE clothing_type
(
	id INTEGER NOT NULL DEFAULT nextval('clothing_type_id_seq'::regclass),
    name VARCHAR(50) NOT NULL,
    description VARCHAR(150) NOT NULL,
	created TIMESTAMP NOT NULL DEFAULT 'now',
	created_by VARCHAR(50) NOT NULL DEFAULT 'sysadm',
	updated TIMESTAMP NOT NULL DEFAULT 'now',
	updated_by VARCHAR(50) NOT NULL DEFAULT 'sysadm'
);

ALTER TABLE clothing_type ADD CONSTRAINT pk_clothing_type PRIMARY KEY (id);
CREATE UNIQUE INDEX ix_clothing_type_name ON clothing_type(name);

INSERT INTO clothing_type(id, name, description)
VALUES( nextval('clothing_type_id_seq'), 'Light summer wear', 'Light summer wear');
INSERT INTO clothing_type(id, name, description)
VALUES( nextval('clothing_type_id_seq'), 'Winter wear', 'Winter wear');



/* footwear type table */
CREATE SEQUENCE footwear_type_id_seq INCREMENT 1;
CREATE TABLE footwear_type
(
	id INTEGER NOT NULL DEFAULT nextval('footwear_type_id_seq'::regclass),
    name VARCHAR(50) NOT NULL,
    description VARCHAR(150) NOT NULL,
	created TIMESTAMP NOT NULL DEFAULT 'now',
	created_by VARCHAR(50) NOT NULL DEFAULT 'sysadm',
	updated TIMESTAMP NOT NULL DEFAULT 'now',
	updated_by VARCHAR(50) NOT NULL DEFAULT 'sysadm'
);

ALTER TABLE footwear_type ADD CONSTRAINT pk_footwear_type PRIMARY KEY (id);
CREATE UNIQUE INDEX ix_footwear_type_name ON footwear_type(name);

INSERT INTO footwear_type(id, name, description)
VALUES( nextval('footwear_type_id_seq'), 'Good boots', 'Good boots');
INSERT INTO footwear_type(id, name, description)
VALUES( nextval('footwear_type_id_seq'), 'Sneakers', 'Sneakers');



/* head wear type table */
CREATE SEQUENCE headwear_type_id_seq INCREMENT 1;
CREATE TABLE headwear_type
(
	id INTEGER NOT NULL DEFAULT nextval('headwear_type_id_seq'::regclass),
    name VARCHAR(50) NOT NULL,
    description VARCHAR(150) NOT NULL,
	created TIMESTAMP NOT NULL DEFAULT 'now',
	created_by VARCHAR(50) NOT NULL DEFAULT 'sysadm',
	updated TIMESTAMP NOT NULL DEFAULT 'now',
	updated_by VARCHAR(50) NOT NULL DEFAULT 'sysadm'
);

ALTER TABLE headwear_type ADD CONSTRAINT pk_headwear_type PRIMARY KEY (id);
CREATE UNIQUE INDEX ix_headwear_type_name ON headwear_type(name);

INSERT INTO headwear_type(id, name, description)
VALUES( nextval('headwear_type_id_seq'), 'Warm woolen hat', 'Warm woolen hat');
INSERT INTO headwear_type(id, name, description)
VALUES( nextval('headwear_type_id_seq'), 'Shade hat', 'Shade hat');


/* climate_condition_type table */
CREATE SEQUENCE climate_condition_type_id_seq INCREMENT 1;
CREATE TABLE climate_condition_type
(
	id INTEGER NOT NULL DEFAULT nextval('climate_condition_type_id_seq'::regclass),
    name VARCHAR(50) NOT NULL,
    description VARCHAR(150) NOT NULL,
	created TIMESTAMP NOT NULL DEFAULT 'now',
	created_by VARCHAR(50) NOT NULL DEFAULT 'sysadm',
	updated TIMESTAMP NOT NULL DEFAULT 'now',
	updated_by VARCHAR(50) NOT NULL DEFAULT 'sysadm'
);

ALTER TABLE climate_condition_type ADD CONSTRAINT pk_climate_condition_type PRIMARY KEY (id);
CREATE UNIQUE INDEX ix_climate_condition_type_name ON climate_condition_type(name);

INSERT INTO climate_condition_type(id, name, description)
VALUES( nextval('climate_condition_type_id_seq'), 'Sunny', 'Sunny');
INSERT INTO climate_condition_type(id, name, description)
VALUES( nextval('climate_condition_type_id_seq'), 'Cloudy', 'Cloudy');
INSERT INTO climate_condition_type(id, name, description)
VALUES( nextval('climate_condition_type_id_seq'), 'Light Rain', 'Light Rain');
INSERT INTO climate_condition_type(id, name, description)
VALUES( nextval('climate_condition_type_id_seq'), 'Heavy Rain', 'Heavy Rain');
INSERT INTO climate_condition_type(id, name, description)
VALUES( nextval('climate_condition_type_id_seq'), 'Snow/Sleet Extreme conditions', 'Snow/Sleet Extreme conditions');



/* climate_windfactor_type table */
CREATE SEQUENCE climate_windfactor_type_id_seq INCREMENT 1;
CREATE TABLE climate_windfactor_type
(
	id INTEGER NOT NULL DEFAULT nextval('climate_windfactor_type_id_seq'::regclass),
    name VARCHAR(50) NOT NULL,
    description VARCHAR(150) NOT NULL,
	created TIMESTAMP NOT NULL DEFAULT 'now',
	created_by VARCHAR(50) NOT NULL DEFAULT 'sysadm',
	updated TIMESTAMP NOT NULL DEFAULT 'now',
	updated_by VARCHAR(50) NOT NULL DEFAULT 'sysadm'
);

ALTER TABLE climate_windfactor_type ADD CONSTRAINT pk_climate_windfactor_type PRIMARY KEY (id);
CREATE UNIQUE INDEX ix_climate_windfactor_type_name ON climate_windfactor_type(name);

INSERT INTO climate_windfactor_type(id, name, description)
VALUES( nextval('climate_windfactor_type_id_seq'), 'Very Low', 'Very Low');
INSERT INTO climate_windfactor_type(id, name, description)
VALUES( nextval('climate_windfactor_type_id_seq'), 'Low', 'Low');
INSERT INTO climate_windfactor_type(id, name, description)
VALUES( nextval('climate_windfactor_type_id_seq'), 'Medium', 'Medium');
INSERT INTO climate_windfactor_type(id, name, description)
VALUES( nextval('climate_windfactor_type_id_seq'), 'High', 'High');
INSERT INTO climate_windfactor_type(id, name, description)
VALUES( nextval('climate_windfactor_type_id_seq'), 'Extreme', 'Extreme');



/* event_history table */
CREATE SEQUENCE event_history_id_seq INCREMENT 1;
CREATE TABLE event_history
(
	id INTEGER NOT NULL DEFAULT nextval('event_history_id_seq'::regclass),
	event_id INTEGER NOT NULL,
	startTime TIMESTAMP NOT NULL,
	endTime TIMESTAMP NOT NULL,
	popularity_ranking_type_id INTEGER NOT NULL,
	climate_condition_type_id INTEGER NOT NULL,
	climate_windfactor_type_id INTEGER NOT NULL,
	suitable_clothing_type_id INTEGER NOT NULL,
	suitable_footwear_type_id INTEGER NOT NULL,
	suitable_headwear_type_id INTEGER NOT NULL,
	created TIMESTAMP NOT NULL DEFAULT 'now',
	created_by VARCHAR(50) NOT NULL DEFAULT 'sysadm',
	updated TIMESTAMP NOT NULL DEFAULT 'now',
	updated_by VARCHAR(50) NOT NULL DEFAULT 'sysadm'
);

ALTER TABLE event_history ADD CONSTRAINT pk_event_history PRIMARY KEY (id);
ALTER TABLE event_history ADD CONSTRAINT fk_event_history_event FOREIGN KEY (event_id) REFERENCES event(id) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE event_history ADD CONSTRAINT fk_event_history_popularity_ranking FOREIGN KEY (popularity_ranking_type_id) REFERENCES popularity_ranking_type(id) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE event_history ADD CONSTRAINT fk_event_history_climate_condition FOREIGN KEY (climate_condition_type_id) REFERENCES climate_condition_type(id) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE event_history ADD CONSTRAINT fk_event_history_climate_windfactor FOREIGN KEY (climate_windfactor_type_id) REFERENCES climate_windfactor_type(id) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE event_history ADD CONSTRAINT fk_event_history_suitable_clothing FOREIGN KEY (suitable_clothing_type_id) REFERENCES clothing_type(id) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE event_history ADD CONSTRAINT fk_event_history_suitable_footwear FOREIGN KEY (suitable_footwear_type_id) REFERENCES footwear_type(id) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE event_history ADD CONSTRAINT fk_event_history_suitable_headwear FOREIGN KEY (suitable_headwear_type_id) REFERENCES headwear_type(id) ON UPDATE NO ACTION ON DELETE NO ACTION;

CREATE INDEX event_history_search_1 ON event_history(startTime,endTime);



-- /* island_route_type table */
-- CREATE SEQUENCE island_route_type_id_seq INCREMENT 1;
-- CREATE TABLE island_route_type
-- (
-- 	id INTEGER NOT NULL DEFAULT nextval('island_route_type_id_seq'::regclass),
--     name VARCHAR(50) NOT NULL,
--     description VARCHAR(150) NOT NULL,
--     island_id INTEGER,
-- 	created TIMESTAMP NOT NULL DEFAULT 'now',
-- 	created_by VARCHAR(50) NOT NULL DEFAULT 'sysadm',
-- 	updated TIMESTAMP NOT NULL DEFAULT 'now',
-- 	updated_by VARCHAR(50) NOT NULL DEFAULT 'sysadm'
-- );
--
-- ALTER TABLE island_route_type ADD CONSTRAINT pk_island_route_type PRIMARY KEY (id);
-- ALTER TABLE island_route_type ADD CONSTRAINT fk_island_route_type_island FOREIGN KEY (island_id) REFERENCES island(id) ON UPDATE NO ACTION ON DELETE NO ACTION;
-- CREATE UNIQUE INDEX ix_island_route_type_name ON island_route_type(name);
--
-- INSERT INTO island_route_type(id, name, description)
-- VALUES( nextval('island_route_type_id_seq'), 'Both North and South Islands', 'Both North and South Islands');
-- INSERT INTO island_route_type(id, name, description)
-- VALUES( nextval('island_route_type_id_seq'), 'North Island only', 'North Island only');
-- INSERT INTO island_route_type(id, name, description)
-- VALUES( nextval('island_route_type_id_seq'), 'South Island only', 'South Island only');




/* route table */
-- CREATE SEQUENCE route_id_seq INCREMENT 1;
-- CREATE TABLE route
-- (
-- 	id INTEGER NOT NULL DEFAULT nextval('route_id_seq'::regclass),
--     name VARCHAR(50) NOT NULL,
--     description VARCHAR(150) NOT NULL,
--     starting_location_id INTEGER NOT NULL,
--     ending_location_id INTEGER NOT NULL,
-- 	created TIMESTAMP NOT NULL DEFAULT 'now',
-- 	created_by VARCHAR(50) NOT NULL DEFAULT 'sysadm',
-- 	updated TIMESTAMP NOT NULL DEFAULT 'now',
-- 	updated_by VARCHAR(50) NOT NULL DEFAULT 'sysadm'
-- );
--
-- ALTER TABLE route ADD CONSTRAINT pk_route PRIMARY KEY (id);
-- ALTER TABLE route ADD CONSTRAINT fk_route_location_start FOREIGN KEY (starting_location_id) REFERENCES location(id) ON UPDATE NO ACTION ON DELETE NO ACTION;
-- ALTER TABLE route ADD CONSTRAINT fk_route_location_end FOREIGN KEY (ending_location_id) REFERENCES location(id) ON UPDATE NO ACTION ON DELETE NO ACTION;
--
-- CREATE UNIQUE INDEX ix_route_name ON route(name);
-- CREATE UNIQUE INDEX ix_route_starting_and_ending ON route(starting_location_id,ending_location_id);


/* suggested_itinerary table */
CREATE SEQUENCE suggested_itinerary_id_seq INCREMENT 1;
CREATE TABLE suggested_itinerary
(
	id INTEGER NOT NULL DEFAULT nextval('suggested_itinerary_id_seq'::regclass),
    name VARCHAR(50) NOT NULL,
    description VARCHAR(150) NOT NULL,
	created TIMESTAMP NOT NULL DEFAULT 'now',
	created_by VARCHAR(50) NOT NULL DEFAULT 'sysadm',
	updated TIMESTAMP NOT NULL DEFAULT 'now',
	updated_by VARCHAR(50) NOT NULL DEFAULT 'sysadm'
);

ALTER TABLE suggested_itinerary ADD CONSTRAINT pk_suggested_itinerary PRIMARY KEY (id);
CREATE UNIQUE INDEX ix_suggested_itinerary_name ON suggested_itinerary(name);




/* suggested_itinerary_location table  */
CREATE SEQUENCE suggested_itinerary_location_id_seq INCREMENT 1;
CREATE TABLE suggested_itinerary_location
(
    id INTEGER NOT NULL DEFAULT nextval('suggested_itinerary_location_id_seq'::regclass),
	suggested_itinerary_id INTEGER NOT NULL,
	location_id INTEGER NOT NULL,
	order_sequence INTEGER NOT NULL,
	created TIMESTAMP NOT NULL DEFAULT 'now',
	created_by VARCHAR(50) NOT NULL DEFAULT 'sysadm',
	updated TIMESTAMP NOT NULL DEFAULT 'now',
	updated_by VARCHAR(50) NOT NULL DEFAULT 'sysadm'
);

ALTER TABLE suggested_itinerary_location ADD CONSTRAINT pk_suggested_itinerary_location PRIMARY KEY (id);
ALTER TABLE suggested_itinerary_location ADD CONSTRAINT fk_suggested_itinerary_location_location FOREIGN KEY (location_id) REFERENCES location(id) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE suggested_itinerary_location ADD CONSTRAINT fk_suggested_itinerary_location_suggested_itinerary FOREIGN KEY (suggested_itinerary_id) REFERENCES suggested_itinerary(id) ON UPDATE NO ACTION ON DELETE NO ACTION;

CREATE UNIQUE INDEX ix_suggested_itinerary_location ON suggested_itinerary_location(suggested_itinerary_id,location_id);





/* itinerary table */
CREATE SEQUENCE itinerary_id_seq INCREMENT 1;
CREATE TABLE itinerary
(
	id INTEGER NOT NULL DEFAULT nextval('itinerary_id_seq'::regclass),
    name VARCHAR(50) NOT NULL,
    description VARCHAR(150) NOT NULL,
	created TIMESTAMP NOT NULL DEFAULT 'now',
	created_by VARCHAR(50) NOT NULL DEFAULT 'sysadm',
	updated TIMESTAMP NOT NULL DEFAULT 'now',
	updated_by VARCHAR(50) NOT NULL DEFAULT 'sysadm'
);

ALTER TABLE itinerary ADD CONSTRAINT pk_itinerary PRIMARY KEY (id);
CREATE UNIQUE INDEX ix_itinerary_name ON itinerary(name);


/* itinerary_location table  */
CREATE SEQUENCE itinerary_location_id_seq INCREMENT 1;
CREATE TABLE itinerary_location
(
    id INTEGER NOT NULL DEFAULT nextval('itinerary_location_id_seq'::regclass),
	itinerary_id INTEGER NOT NULL,
	location_id INTEGER NOT NULL,
	order_sequence INTEGER NOT NULL,
	created TIMESTAMP NOT NULL DEFAULT 'now',
	created_by VARCHAR(50) NOT NULL DEFAULT 'sysadm',
	updated TIMESTAMP NOT NULL DEFAULT 'now',
	updated_by VARCHAR(50) NOT NULL DEFAULT 'sysadm'
);

ALTER TABLE itinerary_location ADD CONSTRAINT pk_itinerary_location PRIMARY KEY (id);
ALTER TABLE itinerary_location ADD CONSTRAINT fk_itinerary_location_itinerary FOREIGN KEY (itinerary_id) REFERENCES itinerary(id) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE itinerary_location ADD CONSTRAINT fk_itinerary_location_location FOREIGN KEY (location_id) REFERENCES location(id) ON UPDATE NO ACTION ON DELETE NO ACTION;

CREATE UNIQUE INDEX ix_itinerary_location ON itinerary_location(itinerary_id,location_id);




/* supplier_type table */
CREATE SEQUENCE supplier_type_id_seq INCREMENT 1;
CREATE TABLE supplier_type
(
	id INTEGER NOT NULL DEFAULT nextval('supplier_type_id_seq'::regclass),
    name VARCHAR(50) NOT NULL,
    description VARCHAR(150) NOT NULL,
	created TIMESTAMP NOT NULL DEFAULT 'now',
	created_by VARCHAR(50) NOT NULL DEFAULT 'sysadm',
	updated TIMESTAMP NOT NULL DEFAULT 'now',
	updated_by VARCHAR(50) NOT NULL DEFAULT 'sysadm'
);

ALTER TABLE supplier_type ADD CONSTRAINT pk_supplier_type PRIMARY KEY (id);
CREATE UNIQUE INDEX ix_supplier_type_name ON supplier_type(name);

INSERT INTO supplier_type(id, name, description)
VALUES( nextval('supplier_type_id_seq'), 'Accommodation', 'Accommodation');
INSERT INTO supplier_type(id, name, description)
VALUES( nextval('supplier_type_id_seq'), 'Rental Vehicles', 'Rental Vehicles');
INSERT INTO supplier_type(id, name, description)
VALUES( nextval('supplier_type_id_seq'), 'Activities', 'Activities');
INSERT INTO supplier_type(id, name, description)
VALUES( nextval('supplier_type_id_seq'), 'Transfers', 'Transfers');


/* supplier table  */
CREATE SEQUENCE supplier_id_seq INCREMENT 1;
CREATE TABLE supplier
(
    id INTEGER NOT NULL DEFAULT nextval('supplier_id_seq'::regclass),
    name VARCHAR(50) NOT NULL,
    description VARCHAR(150) NOT NULL,
	supplier_type_id INTEGER NOT NULL,
	location_id INTEGER NOT NULL,
	created TIMESTAMP NOT NULL DEFAULT 'now',
	created_by VARCHAR(50) NOT NULL DEFAULT 'sysadm',
	updated TIMESTAMP NOT NULL DEFAULT 'now',
	updated_by VARCHAR(50) NOT NULL DEFAULT 'sysadm'
);

ALTER TABLE supplier ADD CONSTRAINT pk_supplier PRIMARY KEY (id);
ALTER TABLE supplier ADD CONSTRAINT fk_supplier_supplier_type FOREIGN KEY (supplier_type_id) REFERENCES supplier_type(id) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE supplier ADD CONSTRAINT fk_supplier_location FOREIGN KEY (location_id) REFERENCES location(id) ON UPDATE NO ACTION ON DELETE NO ACTION;

CREATE UNIQUE INDEX ix_supplier_name ON supplier(name);



/* item_type table */
CREATE SEQUENCE item_type_id_seq INCREMENT 1;
CREATE TABLE item_type
(
	id INTEGER NOT NULL DEFAULT nextval('item_type_id_seq'::regclass),
    name VARCHAR(50) NOT NULL,
    description VARCHAR(150) NOT NULL,
	created TIMESTAMP NOT NULL DEFAULT 'now',
	created_by VARCHAR(50) NOT NULL DEFAULT 'sysadm',
	updated TIMESTAMP NOT NULL DEFAULT 'now',
	updated_by VARCHAR(50) NOT NULL DEFAULT 'sysadm'
);

ALTER TABLE item_type ADD CONSTRAINT pk_item_type PRIMARY KEY (id);
CREATE UNIQUE INDEX ix_type_name ON item_type(name);

INSERT INTO item_type(id, name, description)
VALUES( nextval('supplier_type_id_seq'), 'Accommodation', 'Accommodation');
INSERT INTO item_type(id, name, description)
VALUES( nextval('supplier_type_id_seq'), 'Rental Vehicles', 'Rental Vehicles');
INSERT INTO item_type(id, name, description)
VALUES( nextval('supplier_type_id_seq'), 'Activities', 'Activities');
INSERT INTO item_type(id, name, description)
VALUES( nextval('supplier_type_id_seq'), 'Transfers', 'Transfers');



/* item table  */
CREATE SEQUENCE item_id_seq INCREMENT 1;
CREATE TABLE item
(
    id INTEGER NOT NULL DEFAULT nextval('item_id_seq'::regclass),
    name VARCHAR(50) NOT NULL,
    description VARCHAR(150) NOT NULL,
	item_type_id INTEGER NOT NULL,
	site_id INTEGER NOT NULL,
	created TIMESTAMP NOT NULL DEFAULT 'now',
	created_by VARCHAR(50) NOT NULL DEFAULT 'sysadm',
	updated TIMESTAMP NOT NULL DEFAULT 'now',
	updated_by VARCHAR(50) NOT NULL DEFAULT 'sysadm'
);

ALTER TABLE item ADD CONSTRAINT pk_item PRIMARY KEY (id);
ALTER TABLE item ADD CONSTRAINT fk_item_item_type FOREIGN KEY (item_type_id) REFERENCES item_type(id) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE item ADD CONSTRAINT fk_item_location FOREIGN KEY (site_id) REFERENCES site(id) ON UPDATE NO ACTION ON DELETE NO ACTION;

CREATE UNIQUE INDEX ix_item_name ON item(name);



/* bookline table  */
CREATE SEQUENCE bookline_id_seq INCREMENT 1;
CREATE TABLE bookline
(
    id INTEGER NOT NULL DEFAULT nextval('bookline_id_seq'::regclass),
	item_id INTEGER NOT NULL,
	start_date_time TIMESTAMP NOT NULL,
	end_date_time TIMESTAMP NOT NULL,
	created TIMESTAMP NOT NULL DEFAULT 'now',
	created_by VARCHAR(50) NOT NULL DEFAULT 'sysadm',
	updated TIMESTAMP NOT NULL DEFAULT 'now',
	updated_by VARCHAR(50) NOT NULL DEFAULT 'sysadm'
);

ALTER TABLE bookline ADD CONSTRAINT pk_bookline PRIMARY KEY (id);
ALTER TABLE bookline ADD CONSTRAINT fk_bookline_item FOREIGN KEY (item_id) REFERENCES item(id) ON UPDATE NO ACTION ON DELETE NO ACTION;

CREATE UNIQUE INDEX ix_bookline_item_startdatetime_enddatetime ON bookline(item_id, start_date_time, end_date_time);


/* itinerary_bookline table  */
CREATE SEQUENCE itinerary_location_bookline_id_seq INCREMENT 1;
CREATE TABLE itinerary_location_bookline
(
    id INTEGER NOT NULL DEFAULT nextval('itinerary_location_bookline_id_seq'::regclass),
	itinerary_location_id INTEGER NOT NULL,
	bookline_id INTEGER NOT NULL,
	created TIMESTAMP NOT NULL DEFAULT 'now',
	created_by VARCHAR(50) NOT NULL DEFAULT 'sysadm',
	updated TIMESTAMP NOT NULL DEFAULT 'now',
	updated_by VARCHAR(50) NOT NULL DEFAULT 'sysadm'
);

ALTER TABLE itinerary_location_bookline ADD CONSTRAINT pk_itinerary_location_bookline PRIMARY KEY (id);
ALTER TABLE itinerary_location_bookline ADD CONSTRAINT fk_itinerary_location_bookline_itinerary_location FOREIGN KEY (itinerary_location_id) REFERENCES itinerary_location(id) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE itinerary_location_bookline ADD CONSTRAINT fk_itinerary_location_bookline_bookline FOREIGN KEY (bookline_id) REFERENCES bookline(id) ON UPDATE NO ACTION ON DELETE NO ACTION;

CREATE UNIQUE INDEX ix_itinerary_location_bookline_location_bookline ON itinerary_location_bookline(itinerary_location_id, bookline_id);



/* bookline_pax table  */
CREATE SEQUENCE bookline_pax_id_seq INCREMENT 1;
CREATE TABLE bookline_pax
(
    id INTEGER NOT NULL DEFAULT nextval('bookline_pax_id_seq'::regclass),
	bookline_id INTEGER NOT NULL,
	salutation VARCHAR(50) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	address_1 VARCHAR(50) NOT NULL,
	address_2 VARCHAR(50) NOT NULL,
	suburb VARCHAR(50) NOT NULL,
	postcode VARCHAR(50) NOT NULL,
	city VARCHAR(50) NOT NULL,
	created TIMESTAMP NOT NULL DEFAULT 'now',
	created_by VARCHAR(50) NOT NULL DEFAULT 'sysadm',
	updated TIMESTAMP NOT NULL DEFAULT 'now',
	updated_by VARCHAR(50) NOT NULL DEFAULT 'sysadm'
);


ALTER TABLE bookline_pax ADD CONSTRAINT pk_bookline_pax PRIMARY KEY (id);
ALTER TABLE bookline_pax ADD CONSTRAINT fk_bookline_pax FOREIGN KEY (bookline_id) REFERENCES bookline(id) ON UPDATE NO ACTION ON DELETE NO ACTION;

CREATE UNIQUE INDEX ix_bookline_pax_bookline_firstname ON bookline_pax(bookline_id, first_name, last_name);



/* accommodation_site type table */
CREATE SEQUENCE accommodation_site_type_id_seq INCREMENT 1;
CREATE TABLE accommodation_site_type
(
	id INTEGER NOT NULL DEFAULT nextval('accommodation_site_type_id_seq'::regclass),
    name VARCHAR(50) NOT NULL,
    description VARCHAR(150) NOT NULL,
	created TIMESTAMP NOT NULL DEFAULT 'now',
	created_by VARCHAR(50) NOT NULL DEFAULT 'sysadm',
	updated TIMESTAMP NOT NULL DEFAULT 'now',
	updated_by VARCHAR(50) NOT NULL DEFAULT 'sysadm'
);

ALTER TABLE accommodation_site_type ADD CONSTRAINT pk_accommodation_site_type PRIMARY KEY (id);
CREATE UNIQUE INDEX ix_accommodation_site_type_name ON accommodation_site_type(name);

INSERT INTO accommodation_site_type(id, name, description)
VALUES( nextval('accommodation_site_type_id_seq'), 'Hotel Site', 'Hotel Site');
INSERT INTO accommodation_site_type(id, name, description)
VALUES( nextval('accommodation_site_type_id_seq'), 'Apartment Site', 'Apartment Site');
INSERT INTO accommodation_site_type(id, name, description)
VALUES( nextval('accommodation_site_type_id_seq'), 'Motel/Motor-Inn Site', 'Motel/Motor-Inn Site');
INSERT INTO accommodation_site_type(id, name, description)
VALUES( nextval('accommodation_site_type_id_seq'), 'Bed & Breakfast Site', 'Bed & Breakfast Site');
INSERT INTO accommodation_site_type(id, name, description)
VALUES( nextval('accommodation_site_type_id_seq'), 'Home-stay Site', 'Home-stay Site');
INSERT INTO accommodation_site_type(id, name, description)
VALUES( nextval('accommodation_site_type_id_seq'), 'Farm-stay Site', 'Farm-stay Site');
INSERT INTO accommodation_site_type(id, name, description)
VALUES( nextval('accommodation_site_type_id_seq'), 'Lodge Site', 'Lodge Site');
INSERT INTO accommodation_site_type(id, name, description)
VALUES( nextval('accommodation_site_type_id_seq'), 'Holiday Park Tourist Flat Site', 'Holiday Park Tourist Flag Site');
INSERT INTO accommodation_site_type(id, name, description)
VALUES( nextval('accommodation_site_type_id_seq'), 'Holiday Park Cabin Site', 'Holiday Park Cabin Site');
INSERT INTO accommodation_site_type(id, name, description)
VALUES( nextval('accommodation_site_type_id_seq'), 'Backpacker Site', 'Backpacker Site');


/* room type table */
CREATE SEQUENCE room_type_id_seq INCREMENT 1;
CREATE TABLE room_type
(
	id INTEGER NOT NULL DEFAULT nextval('room_type_id_seq'::regclass),
    name VARCHAR(50) NOT NULL,
    description VARCHAR(150) NOT NULL,
	created TIMESTAMP NOT NULL DEFAULT 'now',
	created_by VARCHAR(50) NOT NULL DEFAULT 'sysadm',
	updated TIMESTAMP NOT NULL DEFAULT 'now',
	updated_by VARCHAR(50) NOT NULL DEFAULT 'sysadm'
);

ALTER TABLE room_type ADD CONSTRAINT pk_room_type PRIMARY KEY (id);
CREATE UNIQUE INDEX ix_room_type_name ON room_type(name);

INSERT INTO room_type(id, name, description)
VALUES( nextval('room_type_id_seq'), 'Penthouse Suite', 'Penthouse Suite');
INSERT INTO room_type(id, name, description)
VALUES( nextval('room_type_id_seq'), 'Sub-Penthouse Suite', 'Sub-Penthouse Suite');
INSERT INTO room_type(id, name, description)
VALUES( nextval('room_type_id_seq'), '3 Bed, 2 Bath', '3 Bed, 2 Bath');
INSERT INTO room_type(id, name, description)
VALUES( nextval('room_type_id_seq'), '2 Bed, 1 Bath', '2 Bed, 1 Bath');
INSERT INTO room_type(id, name, description)
VALUES( nextval('room_type_id_seq'), '1 Bed, 1 Bath', '1 Bed, 1 Bath');


-- NEED TO ROOM_CONFIGURATION_TYPE.....
/* room type table */
CREATE SEQUENCE room_configuration_type_id_seq INCREMENT 1;
CREATE TABLE room_configuration_type
(
	id INTEGER NOT NULL DEFAULT nextval('room_configuration_type_id_seq'::regclass),
    name VARCHAR(50) NOT NULL,
    description VARCHAR(150) NOT NULL,
	created TIMESTAMP NOT NULL DEFAULT 'now',
	created_by VARCHAR(50) NOT NULL DEFAULT 'sysadm',
	updated TIMESTAMP NOT NULL DEFAULT 'now',
	updated_by VARCHAR(50) NOT NULL DEFAULT 'sysadm'
);

ALTER TABLE room_configuration_type ADD CONSTRAINT pk_room_configuration_type PRIMARY KEY (id);
CREATE UNIQUE INDEX ix_room_configuration_type_name ON room_configuration_type(name);

INSERT INTO room_configuration_type(id, name, description)
VALUES( nextval('room_configuration_type_id_seq'), 'Includes Ensuite', 'Includes Ensuite');
INSERT INTO room_configuration_type(id, name, description)
VALUES( nextval('room_configuration_type_id_seq'), 'Includes Kitchenette', 'Includes Kitchenette');


/* site table */
CREATE SEQUENCE accommodation_site_id_seq INCREMENT 1;
CREATE TABLE accommodation_site
(
	id INTEGER NOT NULL DEFAULT nextval('accommodation_site_id_seq'::regclass),
    name VARCHAR(50) NOT NULL,
    description VARCHAR(150) NOT NULL,
	accommodation_site_type_id INTEGER NOT NULL,
	room_type_id INTEGER NOT NULL,
    room_configuration_type_id INTEGER NOT NULL,
	created TIMESTAMP NOT NULL DEFAULT 'now',
	created_by VARCHAR(50) NOT NULL DEFAULT 'sysadm',
	updated TIMESTAMP NOT NULL DEFAULT 'now',
	updated_by VARCHAR(50) NOT NULL DEFAULT 'sysadm'
);

ALTER TABLE accommodation_site ADD CONSTRAINT pk_accommodation_site PRIMARY KEY (id);
ALTER TABLE accommodation_site ADD CONSTRAINT fk_accommodation_site_site FOREIGN KEY (id) REFERENCES site(id) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE accommodation_site ADD CONSTRAINT fk_accommodation_site_site_type FOREIGN KEY (accommodation_site_type_id) REFERENCES accommodation_site_type(id) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE accommodation_site ADD CONSTRAINT fk_accommodation_site_room_type FOREIGN KEY (room_type_id) REFERENCES room_type(id) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE accommodation_site ADD CONSTRAINT fk_accommodation_site_room_configuration_type FOREIGN KEY (room_configuration_type_id) REFERENCES room_configuration_type(id) ON UPDATE NO ACTION ON DELETE NO ACTION;

CREATE UNIQUE INDEX ix_accomodation_site_name ON accommodation_site(name);
CREATE INDEX accommodation_site_search_1 ON site(name);






COMMIT;