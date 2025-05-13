package org.spark.service.rest;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.spark.service.SparkSQLService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

/*
 * REST controller pentru interogarea view-urilor SparkSQL din proiectul
 * „Platformă pentru analiza pieței imobiliare”.
 *
 * Exemple URL acces:
 *  http://localhost:9990/DSA-SparkSQL-Service/rest/view/customers_view
 *  http://localhost:9990/DSA-SparkSQL-Service/rest/view/property_history_view
 *  http://localhost:9990/DSA-SparkSQL-Service/rest/STRUCT/OLAP_VIEW_BUDGET_PER_YEAR
 */
@RestController
@RequestMapping("/rest")
public class SparkSQLRESTService {

    private static final Logger logger = Logger.getLogger(SparkSQLRESTService.class.getName());

    private final SparkSQLService sparkSQLService;

    // Constructor autowired
    public SparkSQLRESTService(SparkSQLService sparkSQLService) {
        this.sparkSQLService = sparkSQLService;
    }

    // Ping endpoint
    @GetMapping(value = "/ping", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String pingDataSource() {
        logger.info(">>>> SparkSQLRESTService is Up!");
        return "PING response from SparkSQLRESTService!";
    }

    // Endpoint pentru returnarea conținutului unui view
    @GetMapping(value = "/view/{VIEW_NAME}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    @ResponseBody
    public String getViewDataSet(@PathVariable("VIEW_NAME") String viewName) throws Exception {
        logger.info("DEBUG: getViewDataSet: Querying View: " + viewName);
        Dataset<Row> viewDataSet = sparkSQLService.getSpark().sql("SELECT * FROM " + viewName);
        viewDataSet.printSchema();
        viewDataSet.show();
        return viewDataSet.toJSON().collectAsList().toString();
    }

    // Endpoint pentru returnarea structurii unui view
    @GetMapping(value = "/STRUCT/{VIEW_NAME}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    @ResponseBody
    public String getViewDataStruct(@PathVariable("VIEW_NAME") String viewName) throws Exception {
        logger.info("DEBUG: getViewDataSTRUCT: Querying View: " + viewName);
        Dataset<Row> viewDataSet = sparkSQLService.getSpark().sql("SELECT * FROM " + viewName + " WHERE 1=0");
        viewDataSet.printSchema();
        return viewDataSet.schema().sql();
    }
}