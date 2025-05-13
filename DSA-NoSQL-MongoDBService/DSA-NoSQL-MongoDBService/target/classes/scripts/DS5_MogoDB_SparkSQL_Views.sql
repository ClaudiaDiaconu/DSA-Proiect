-- 1. Get JSON Schema
SELECT java_method(
    'org.spark.service.rest.QueryRESTDataService',
    'getRESTDataDocument',
    'http://localhost:8093/DSA-NoSQL-MongoDBService/rest/locations/propertyHistory');

SELECT schema_of_json('[{
  "propertyId": "P001",
  "modificationDate": "2023-01-01",
  "previousPrice": 120000.0,
  "newPrice": 125000.0,
  "reason": "Renovation",
  "oracleId": 101
}]');

-- 2. Create View
CREATE OR REPLACE VIEW property_history_view AS
WITH json_view AS (
    SELECT from_json(json_raw.data,
        'ARRAY<STRUCT<propertyId: STRING, modificationDate: TIMESTAMP, previousPrice: DOUBLE, newPrice: DOUBLE, reason: STRING, oracleId: INT>>') array
    FROM (
        SELECT java_method('org.spark.service.rest.QueryRESTDataService', 'getRESTDataDocument',
            'http://localhost:8093/DSA-NoSQL-MongoDBService/rest/locations/propertyHistory') as data
    ) json_raw
)
SELECT v.*
FROM json_view LATERAL VIEW explode(json_view.array) AS v;

-- 1. Get JSON Schema
SELECT java_method(
    'org.spark.service.rest.QueryRESTDataService',
    'getRESTDataDocument',
    'http://localhost:8093/DSA-NoSQL-MongoDBService/rest/locations/propertyReviews');

SELECT schema_of_json('[{
  "propertyId": "P001",
  "reviewDate": "2023-03-15",
  "rating": 5,
  "reviewer": "John",
  "comment": "Great property!",
  "oracleId": 101
}]');

-- 2. Create View
CREATE OR REPLACE VIEW property_reviews_view AS
WITH json_view AS (
    SELECT from_json(json_raw.data,
        'ARRAY<STRUCT<propertyId: STRING, reviewDate: TIMESTAMP, rating: INT, reviewer: STRING, comment: STRING, oracleId: INT>>') array
    FROM (
        SELECT java_method('org.spark.service.rest.QueryRESTDataService', 'getRESTDataDocument',
            'http://localhost:8093/DSA-NoSQL-MongoDBService/rest/locations/propertyReviews') as data
    ) json_raw
)
SELECT v.*
FROM json_view LATERAL VIEW explode(json_view.array) AS v;

-- 1. Get JSON Schema
SELECT java_method(
    'org.spark.service.rest.QueryRESTDataService',
    'getRESTDataDocument',
    'http://localhost:8093/DSA-NoSQL-MongoDBService/rest/locations/marketInsights');

SELECT schema_of_json('[{
  "city": "Bucuresti",
  "averagePrice": 150000.0,
  "averageSurface": 85.0,
  "timestamp": "2023-04-01",
  "trend": "rising",
  "oracleId": 101
}]');

-- 2. Create View
CREATE OR REPLACE VIEW market_insights_view AS
WITH json_view AS (
    SELECT from_json(json_raw.data,
        'ARRAY<STRUCT<city: STRING, averagePrice: DOUBLE, averageSurface: DOUBLE, timestamp: TIMESTAMP, trend: STRING, oracleId: INT>>') array
    FROM (
        SELECT java_method('org.spark.service.rest.QueryRESTDataService', 'getRESTDataDocument',
            'http://localhost:8093/DSA-NoSQL-MongoDBService/rest/locations/marketInsights') as data
    ) json_raw
)
SELECT v.*
FROM json_view LATERAL VIEW explode(json_view.array) AS v;

-- 1. Get JSON Schema
SELECT java_method(
    'org.spark.service.rest.QueryRESTDataService',
    'getRESTDataDocument',
    'http://localhost:8093/DSA-NoSQL-MongoDBService/rest/locations/siteActivityLogs');

SELECT schema_of_json('[{
  "userId": "user001",
  "action": "login",
  "timestamp": "2023-05-01",
  "ipAddress": "192.168.1.1",
  "oracleId": 101
}]');

-- 2. Create View
CREATE OR REPLACE VIEW site_activity_logs_view AS
WITH json_view AS (
    SELECT from_json(json_raw.data,
        'ARRAY<STRUCT<userId: STRING, action: STRING, timestamp: TIMESTAMP, ipAddress: STRING, oracleId: INT>>') array
    FROM (
        SELECT java_method('org.spark.service.rest.QueryRESTDataService', 'getRESTDataDocument',
            'http://localhost:8093/DSA-NoSQL-MongoDBService/rest/locations/siteActivityLogs') as data
    ) json_raw
)
SELECT v.*
FROM json_view LATERAL VIEW explode(json_view.array) AS v;
