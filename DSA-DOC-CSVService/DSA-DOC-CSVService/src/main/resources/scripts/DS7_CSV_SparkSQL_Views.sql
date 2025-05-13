-- SQL pentru Spark

-- 1. Get JSON schema
SELECT java_method(
    'org.spark.service.rest.QueryRESTDataService',
    'getRESTDataDocument',
    'http://localhost:8097/DSA-DOC-CSVService/rest/budget/budgetCategoryView'
);

SELECT schema_of_json('[{
  "categoryCode": "B1",
  "categoryName": "Low Budget",
  "lowerBound": 0.0,
  "upperBound": 50000.0
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

-- 3. Test
SELECT * FROM CTG_CUST_BUDGET_VIEW;
