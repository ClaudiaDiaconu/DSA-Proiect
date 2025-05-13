package org.datasource;

import org.datasource.xml.locations.PropertyHistoryView;
import org.datasource.xml.locations.PropertyHistoryViewBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/xml")
public class RESTViewServiceXML {

	private static final Logger logger = Logger.getLogger(RESTViewServiceXML.class.getName());

	@Autowired
	private PropertyHistoryViewBuilder propertyHistoryViewBuilder;

	@GetMapping(value = "/propertyHistory", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<PropertyHistoryView> getPropertyHistoryData() throws Exception {
		logger.info("Returning property history from XML...");
		return propertyHistoryViewBuilder.build().getEntries();
	}
}
/*
http://localhost:8092//DSA-DOC-XMLService/rest/xml/propertyHistory
 */
