package org.datasource;
import org.datasource.neo4j.views.Nodes.Owner.OwnerViewBuilder;
import org.datasource.neo4j.views.Nodes.Customer.*;

import org.datasource.neo4j.views.Nodes.Customer.HistoryViewBuilder;
import org.datasource.neo4j.views.Nodes.Customer.RequestViewBuilder;
import org.datasource.neo4j.views.Nodes.Customer.ReviewViewBuilder;
import org.datasource.neo4j.views.Nodes.History.HistoryView;
import org.datasource.neo4j.views.Nodes.Owner.OwnerView;
import org.datasource.neo4j.views.Nodes.Property.PropertyView;
import org.datasource.neo4j.views.Nodes.Request.RequestView;
import org.datasource.neo4j.views.Nodes.Review.ReviewView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;


/*	REST Service URL
	http://localhost:8094/DSA-NoSQL-Neo4JService/rest/locations/DepartamentView
	http://localhost:8094/DSA-NoSQL-Neo4JService/rest/locations/CityView
*/
@RestController @RequestMapping("/graph")
public class RESTViewServiceNeo4J {
	private static Logger logger = Logger.getLogger(RESTViewServiceNeo4J.class.getName());

	@RequestMapping(value = "/ping", method = RequestMethod.GET,
			produces = {MediaType.TEXT_PLAIN_VALUE})
	@ResponseBody
	public String pingDataSource() {
		logger.info(">>>> org.datasource.rest.RESTViewService(JSON) is Up!");
		return "Ping response from RESTViewServiceMongoDB!";
	}

	@GetMapping(value = "/customers", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public List<CustomerView> getCustomers() {
		return customerViewBuilder.getViewList();
	}

	@GetMapping(value = "/requests", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public List<RequestView> getRequests() {
		return requestViewBuilder.getViewList();
	}

	@GetMapping(value = "/owners", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public List<OwnerView> getOwners() {
		return ownerViewBuilder.getViewList();
	}

	@GetMapping(value = "/properties", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public List<PropertyView> getProperties() {
		return propertyViewBuilder.getViewList();
	}

	@GetMapping(value = "/history", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public List<HistoryView> getHistory() {
		return historyViewBuilder.getViewList();
	}

	@GetMapping(value = "/reviews", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public List<ReviewView> getReviews() {
		return reviewViewBuilder.getViewList();
	}
	@Autowired
	private CustomerViewBuilder customerViewBuilder;
	@Autowired
	private RequestViewBuilder requestViewBuilder;
	@Autowired
	private OwnerViewBuilder ownerViewBuilder;
	@Autowired
	private org.datasource.neo4j.views.Nodes.Property.PropertyViewBuilder propertyViewBuilder;
	@Autowired
	private HistoryViewBuilder historyViewBuilder;
	@Autowired
	private ReviewViewBuilder reviewViewBuilder;
}