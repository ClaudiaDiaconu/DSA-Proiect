-- Drop existing views to avoid conflicts
DROP VIEW IF EXISTS customers_view;
DROP VIEW IF EXISTS property_requests_view;
DROP VIEW IF EXISTS request_feedback_view;

-- 1. Get Data Source JSON Schema for customers
SELECT java_method(
    'org.spark.service.rest.QueryRESTDataService',
    'getRESTDataDocument',
    'http://localhost:8090/DSA-SQL-JDBCService/rest/jdbc/customers');

-- Create the view for customers
CREATE OR REPLACE VIEW customers_view AS
WITH json_view AS (
    SELECT from_json(json_raw.data,
        'ARRAY<STRUCT<id: INT, customerCode: STRING, fullName: STRING, email: STRING,
                      phone: STRING, registrationDate: DATE, country: STRING, city: STRING>>') AS array
    FROM (
        SELECT java_method('org.spark.service.rest.QueryRESTDataService', 'getRESTDataDocument',
            'http://localhost:8090/DSA-SQL-JDBCService/rest/jdbc/customers') AS data
    ) json_raw
)
SELECT v.*
FROM json_view LATERAL VIEW explode(json_view.array) AS v;

-- 2. Get Data Source JSON Schema for property requests
SELECT java_method(
    'org.spark.service.rest.QueryRESTDataService',
    'getRESTDataDocument',
    'http://localhost:8090/DSA-SQL-JDBCService/rest/jdbc/propertyRequests');

-- Create the view for property requests
CREATE OR REPLACE VIEW property_requests_view AS
WITH json_view AS (
    SELECT from_json(json_raw.data,
        'ARRAY<STRUCT<requestId: INT, customerId: INT, requestDate: DATE,
                      propertyType: STRING, maxBudget: DOUBLE, minSurface: DOUBLE,
                      preferredCity: STRING, requestStatus: STRING>>') AS array
    FROM (
        SELECT java_method('org.spark.service.rest.QueryRESTDataService', 'getRESTDataDocument',
            'http://localhost:8090/DSA-SQL-JDBCService/rest/jdbc/propertyRequests') AS data
    ) json_raw
)
SELECT v.*
FROM json_view LATERAL VIEW explode(json_view.array) AS v;

-- 3. Get Data Source JSON Schema for request feedback
SELECT java_method(
    'org.spark.service.rest.QueryRESTDataService',
    'getRESTDataDocument',
    'http://localhost:8090/DSA-SQL-JDBCService/rest/jdbc/requestFeedback');

-- Create the view for request feedback
CREATE OR REPLACE VIEW request_feedback_view AS
WITH json_view AS (
    SELECT from_json(json_raw.data,
        'ARRAY<STRUCT<id: INT, requestId: INT, feedbackDate: DATE,
                      rating: INT, comments: STRING>>') AS array
    FROM (
        SELECT java_method('org.spark.service.rest.QueryRESTDataService', 'getRESTDataDocument',
            'http://localhost:8090/DSA-SQL-JDBCService/rest/jdbc/requestFeedback') AS data
    ) json_raw
)
SELECT v.*
FROM json_view LATERAL VIEW explode(json_view.array) AS v;

-- TEST VIEWS
SELECT * FROM customers_view LIMIT 10;
SELECT * FROM property_requests_view LIMIT 10;
SELECT * FROM request_feedback_view LIMIT 10;
