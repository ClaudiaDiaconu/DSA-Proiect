package org.datasource;

import org.datasource.jdbc.views.CustomerRequestFeedback.CustomerRequestFeedbackView;
import org.datasource.jdbc.views.CustomerRequestFeedback.CustomerRequestFeedbackViewBuilder;
import org.datasource.jdbc.views.customers.CustomerView;
import org.datasource.jdbc.views.customers.CustomerViewBuilder;
import org.datasource.jdbc.views.propertyrequest.PropertyRequestView;
import org.datasource.jdbc.views.propertyrequest.PropertyRequestViewBuilder;
import org.datasource.jdbc.views.requestfeedback.RequestFeedbackView;
import org.datasource.jdbc.views.requestfeedback.RequestFeedbackViewBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/jdbc")
public class RESTViewServiceJDBC {

	/*
	Linkuri pentru testare în browser sau Postman:
	http://localhost:8090/DSA-SQL-JDBCService/rest/jdbc/ping
	http://localhost:8090/DSA-SQL-JDBCService/rest/jdbc/customers
	http://localhost:8090/DSA-SQL-JDBCService/rest/jdbc/propertyRequests
	http://localhost:8090/DSA-SQL-JDBCService/rest/jdbc/requestFeedback
	http://localhost:8090/DSA-SQL-JDBCService/rest/jdbc/customerRequestFeedback
*/
	private static final Logger logger = Logger.getLogger(RESTViewServiceJDBC.class.getName());

	@Autowired
	private CustomerViewBuilder customerViewBuilder;

	@Autowired
	private PropertyRequestViewBuilder propertyRequestViewBuilder;

	@Autowired
	private RequestFeedbackViewBuilder requestFeedbackViewBuilder;

	@GetMapping(value = "/ping", produces = MediaType.TEXT_PLAIN_VALUE)
	public String ping() {
		logger.info(">>>>> REST JDBC Service is Up!");
		return "Ping OK - JDBC Service is alive!";
	}

	@GetMapping(value = "/customers", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public List<CustomerView> getCustomers() {
		return customerViewBuilder.getViewList();
	}

	@GetMapping(value = "/propertyRequests", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public List<PropertyRequestView> getPropertyRequests() {
		return propertyRequestViewBuilder.getViewList();
	}

	@GetMapping(value = "/requestFeedback", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public List<RequestFeedbackView> getFeedback() {
		return requestFeedbackViewBuilder.getViewList();
	}
	@Autowired
	private CustomerRequestFeedbackViewBuilder customerRequestFeedbackViewBuilder;

	@GetMapping(value = "/customerRequestFeedback", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public List<CustomerRequestFeedbackView> getCustomerRequestFeedback() {
		return customerRequestFeedbackViewBuilder.getViewList();
	}

}
