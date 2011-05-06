BEGIN;

-- Ecuador
--
-- add the country
INSERT INTO COUNTRY(countryid, name,countrycode, telephonecode, hasstates, currencyid, gstdescription, gstrate) 
VALUES ( nextval('country_countryid_seq'), 'Ecuador', 'EC' ,'593', false, (SELECT currencyid from currency where code = 'USD')  ,'GST', 0.00 );

-- Quito 
--
-- add the location
INSERT INTO LOCATION(id, name, postcode, shortcode, countryid, stateid) VALUES( nextval('location_id_seq'), 'Quito', '', 'UIO', (SELECT countryid from country where countrycode = 'EC'), -1);


COMMIT;
