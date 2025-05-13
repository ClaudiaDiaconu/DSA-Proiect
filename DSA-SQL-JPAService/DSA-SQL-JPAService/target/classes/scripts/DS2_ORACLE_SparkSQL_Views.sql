----------------------------------------------------------------------------------
--- DS2_ORACLE_SparkSQL_Views.sql
------------------------
-- View: cities_view
-- 1. Get Data Source JSON Schema
SELECT java_method(
               'org.spark.service.rest.QueryRESTDataService',
               'getRESTDataDocument',
               'http://localhost:8091/DSA_SQL_JPAService/rest/cities');

SELECT schema_of_json('[{"id": 1, "cityName": "Austin", "state": "TX", "zipCode": "73301"}]');

-- 2. Create Remote View
-- DROP VIEW cities_view;
CREATE OR REPLACE VIEW cities_view AS
WITH json_view AS (
    SELECT from_json(json_raw.data,
                     'ARRAY<STRUCT<id: INT, cityName: STRING, state: STRING, zipCode: STRING>>') array
    FROM (SELECT java_method('org.spark.service.rest.QueryRESTDataService', 'getRESTDataDocument',
        'http://localhost:8091/DSA_SQL_JPAService/rest/cities')
        as data) json_raw
)
SELECT v.*
FROM json_view LATERAL VIEW explode(json_view.array) AS v;

-- 3. Test Remote View
SELECT * FROM cities_view;

----------------------------------------------------------------------------------
-- View: owners_view
----------------------------------------------------------------------------------
-- 1. Get Data Source JSON Schema
SELECT java_method(
               'org.spark.service.rest.QueryRESTDataService',
               'getRESTDataDocument',
               'http://localhost:8091/DSA_SQL_JPAService/rest/owners');

SELECT schema_of_json('[{"id": 1, "fullName": "Owner 1", "email": "owner1@example.com", "phone": "0710000001", "createdAt": "2025-04-14"}]');

-- 2. Create Remote View
-- DROP VIEW owners_view;
CREATE OR REPLACE VIEW owners_view AS
WITH json_view AS (
    SELECT from_json(json_raw.data,
                     'ARRAY<STRUCT<id: INT, fullName: STRING, email: STRING, phone: STRING, createdAt: DATE>>') array
    FROM (SELECT java_method('org.spark.service.rest.QueryRESTDataService', 'getRESTDataDocument',
        'http://localhost:8091/DSA_SQL_JPAService/rest/owners')
        as data) json_raw
)
SELECT v.*
FROM json_view LATERAL VIEW explode(json_view.array) AS v;

-- 3. Test Remote View
SELECT * FROM owners_view;

----------------------------------------------------------------------------------
-- View: property_types_view
----------------------------------------------------------------------------------
-- 1. Get Data Source JSON Schema
SELECT java_method(
               'org.spark.service.rest.QueryRESTDataService',
               'getRESTDataDocument',
               'http://localhost:8091/DSA_SQL_JPAService/rest/property-types');

SELECT schema_of_json('[{"id": 1, "typeName": "House", "description": "Description for House"}]');

-- 2. Create Remote View
-- DROP VIEW property_types_view;
CREATE OR REPLACE VIEW property_types_view AS
WITH json_view AS (
    SELECT from_json(json_raw.data,
                     'ARRAY<STRUCT<id: INT, typeName: STRING, description: STRING>>') array
    FROM (SELECT java_method('org.spark.service.rest.QueryRESTDataService', 'getRESTDataDocument',
        'http://localhost:8091/DSA_SQL_JPAService/rest/property-types')
        as data) json_raw
)
SELECT v.*
FROM json_view LATERAL VIEW explode(json_view.array) AS v;

-- 3. Test Remote View
SELECT * FROM property_types_view;