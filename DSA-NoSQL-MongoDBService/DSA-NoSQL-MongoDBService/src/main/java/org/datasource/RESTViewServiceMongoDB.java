package org.datasource;

import org.datasource.mongodb.MarketInsight.MarketInsightView;
import org.datasource.mongodb.MarketInsight.MarketInsightViewBuilder;
import org.datasource.mongodb.PropertyReviews.PropertyReviewsView;
import org.datasource.mongodb.PropertyReviews.PropertyReviewsViewBuilder;
import org.datasource.mongodb.SiteActiviyLogs.SiteActivityLogsView;
import org.datasource.mongodb.SiteActiviyLogs.SiteActivityLogsViewBuilder;
import org.datasource.mongodb.views.PropertyHistory.PropertyHistoryView;
import org.datasource.mongodb.views.PropertyHistory.PropertyHistoryViewBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;


@RestController @RequestMapping("/locations")
public class RESTViewServiceMongoDB {
	private static Logger logger = Logger.getLogger(RESTViewServiceMongoDB.class.getName());
	
	@RequestMapping(value = "/ping", method = RequestMethod.GET,
		produces = {MediaType.TEXT_PLAIN_VALUE})
	@ResponseBody
	public String pingDataSource() {
		logger.info(">>>> org.datasource.rest.RESTViewService(JSON) is Up!");
		return "Ping response from RESTViewServiceMongoDB!";
	}
	@GetMapping(value = "/propertyHistory", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public List<PropertyHistoryView> getPropertyHistory() {
		return propertyHistoryViewBuilder.getViewList();
	}
	@GetMapping(value = "/propertyReviews", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public List<PropertyReviewsView> getPropertyReviews() {
		return propertyReviewsViewBuilder.getViewList(); // acesta apelează build()
	}
	@GetMapping(value = "/marketInsights", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public List<MarketInsightView> getMarketInsights() {
		return marketInsightViewBuilder.getViewList();
	}
	@GetMapping(value = "/siteActivityLogs", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public List<SiteActivityLogsView> getSiteActivityLogs() {
		return siteActivityLogsViewBuilder.getViewList();
	}


	@Autowired
	private PropertyReviewsViewBuilder propertyReviewsViewBuilder;

	// Set-up 
	@Autowired
	private PropertyHistoryViewBuilder propertyHistoryViewBuilder;

	@Autowired
	private MarketInsightViewBuilder marketInsightViewBuilder;

	@Autowired
	private SiteActivityLogsViewBuilder siteActivityLogsViewBuilder;



}

/*
   http://localhost:8093/DSA-NoSQL-MongoDBService/rest/locations/ping
   http://localhost:8093/DSA-NoSQL-MongoDBService/rest/locations/propertyHistory
   http://localhost:8093/DSA-NoSQL-MongoDBService/rest/locations/propertyReviews
   http://localhost:8093/DSA-NoSQL-MongoDBService/rest/locations/marketInsights
   http://localhost:8093/DSA-NoSQL-MongoDBService/rest/locations/siteActivityLogs
*/
