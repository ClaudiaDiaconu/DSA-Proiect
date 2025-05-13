package org.spark.service;

import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.hive.thriftserver.HiveThriftServer2;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;

@Service
public class SparkSQLService {
    private static Logger logger = Logger.getLogger(SparkSQLService.class.getName());

    private SparkSession spark;

    public SparkSession getSpark() {
        return spark;
    }

    public SparkSQLService() {
        startThriftServer2();
    }
    /*
     * https://spark.apache.org/docs/latest/sql-distributed-sql-engine.html
     *
     */
    private void startThriftServer2(){
        // Create a SparkSession with Hive support
        this.spark = SparkSession.builder()
                .appName("SparkSQL")
                .master("local[*]")
                .config("spark.sql.warehouse.dir", "file:///c:/spark-warehouse")
                .config("spark.sql.catalogImplementation", "hive")
                .enableHiveSupport()
                .getOrCreate();

        executeSQLScript(spark, "src/main/resources/scripts/SparkSQL_OLAP_Multidimensional_Analytical.sql");


        // Start the Thrift server
        HiveThriftServer2.startWithContext(spark.sqlContext());

        logger.info(">>> HiveThriftServer2 started successfully!");
    }

    private void executeSQLScript(SparkSession spark, String scriptPath) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(scriptPath));
            StringBuilder statement = new StringBuilder();

            for (String line : lines) {
                // Skip comments
                if (line.trim().startsWith("--") || line.trim().isEmpty()) continue;

                statement.append(line).append("\n");
                // If line ends with semicolon, execute
                if (line.trim().endsWith(";")) {
                    spark.sql(statement.toString().replace(";", ""));
                    statement.setLength(0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}




/* -- SparkSQL Hive Driver Config
<!-- https://mvnrepository.com/artifact/org.apache.hive/hive-jdbc -->
<dependency>
    <groupId>org.apache.hive</groupId>
    <artifactId>hive-jdbc</artifactId>
    <version>3.1.2</version>
</dependency>
*/