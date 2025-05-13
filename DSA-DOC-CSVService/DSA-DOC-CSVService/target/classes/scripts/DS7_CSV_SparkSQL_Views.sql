-- SQL pentru Spark

-- 1. Get JSON schema
SELECT java_method(
    'org.spark.service.rest.QueryRESTDataService',
    'getRESTDataDocument',
    'http://localhost:8097/DSA-DOC-CSVService/rest/budget/budgetCategoryView'
);

SELECT java_method(
    'org.spark.service.rest.QueryRESTDataService',
    'getRESTDataDocument',
    'http://localhost:8097/DSA-DOC-CSVService/rest/budget/propertyDemandStats'
);

SELECT schema_of_json('[{
  "categoryCode": "B1",
  "categoryName": "Low Budget",
  "lowerBound": 0.0,
  "upperBound": 50000.0
}]');

SELECT schema_of_json('[{
  "city": "Greece",
  "propertyType": "Apartment",
  "totalRequests": 3,
  "mostRequestedMonth": "March"
}]');

-- 2. Create view in Spark
CREATE OR REPLACE VIEW CTG_CUST_BUDGET_VIEW AS
WITH json_view AS (
    SELECT from_json(json_raw.data,
        'ARRAY<STRUCT<categoryCode: STRING, categoryName: STRING, lowerBound: DOUBLE, upperBound: DOUBLE>>') array
    FROM (
        SELECT java_method('org.spark.service.rest.QueryRESTDataService', 'getRESTDataDocument',
            'http://localhost:8097/DSA-DOC-CSVService/rest/budget/budgetCategoryView') AS data
    ) json_raw
)
SELECT v.*
FROM json_view LATERAL VIEW explode(json_view.array) AS v;

CREATE OR REPLACE VIEW PROPERTY_DEMAND_STATS_VIEW AS
WITH json_view AS (
    SELECT from_json(json_raw.data,
        'ARRAY<STRUCT<city: STRING, propertyType: STRING, totalRequests: INT, mostRequestedMonth: STRING>>') array
    FROM (
        SELECT java_method('org.spark.service.rest.QueryRESTDataService', 'getRESTDataDocument',
            'http://localhost:8097/DSA-DOC-CSVService/rest/budget/propertyDemandStats') AS data
    ) json_raw
)
SELECT v.*
FROM json_view LATERAL VIEW explode(json_view.array) AS v;

-- 3. Test
SELECT * FROM CTG_CUST_BUDGET_VIEW;
SELECT * FROM PROPERTY_DEMAND_STATS_VIEW;




-- 5.1 Average requests per city
SELECT city, ROUND(AVG(totalRequests), 2) AS avg_requests
FROM PROPERTY_DEMAND_STATS_VIEW
GROUP BY city
ORDER BY avg_requests DESC;

-- 5.2 Most popular property type per city
SELECT city, propertyType, MAX(totalRequests) as max_requests
FROM PROPERTY_DEMAND_STATS_VIEW
GROUP BY city, propertyType
ORDER BY city, max_requests DESC;

-- 5.3 Requests distribution by month
SELECT mostRequestedMonth, COUNT(*) AS entries, SUM(totalRequests) as total
FROM PROPERTY_DEMAND_STATS_VIEW
GROUP BY mostRequestedMonth
ORDER BY total DESC;

