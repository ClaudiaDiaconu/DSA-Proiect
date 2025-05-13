-- ----------------------------------------------------------------------------------
-- DS7_XML_SparkSQL_Views.sql – Property History from XML 
-- ----------------------------------------------------------------------------------

-- 1. Get JSON from REST Endpoint (to test response)
SELECT java_method(
    'org.spark.service.rest.QueryRESTDataService',
    'getRESTDataDocument',
    'http://localhost:8092/DSA-DOC-XMLService/rest/xml/propertyHistory'
);

-- 2. Get JSON Schema
SELECT schema_of_json('[{
  "propertyId": "d3fb671d-e9fa-4782-b1eb-50ec729e1540",
  "modificationDate": "2020-12-22",
  "previousPrice": 221986.17,
  "newPrice": 280569.03,
  "reason": "market change",
  "oracleId": 1
}]');

-- 3. Create Remote View in Spark
CREATE OR REPLACE VIEW property_history_view AS
WITH json_view AS (
    SELECT from_json(json_raw.data,
      'ARRAY<STRUCT<
          propertyId: STRING,
          modificationDate: STRING,
          previousPrice: DOUBLE,
          newPrice: DOUBLE,
          reason: STRING,
          oracleId: INT
      >>') array
    FROM (
        SELECT java_method('org.spark.service.rest.QueryRESTDataService',
                           'getRESTDataDocument',
                           'http://localhost:8092/DSA-DOC-XMLService/rest/xml/propertyHistory') AS data
    ) json_raw
)
SELECT *
FROM json_view LATERAL VIEW explode(json_view.array) AS v;

-- 4. Test Remote View
SELECT * FROM property_history_view;
