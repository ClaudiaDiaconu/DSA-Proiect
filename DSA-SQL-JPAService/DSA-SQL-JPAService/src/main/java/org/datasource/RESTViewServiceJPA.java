package org.datasource;
import org.datasource.JPAView.Cities.CitiesView;
import org.datasource.JPAView.Cities.CitiesViewRepository;
import org.datasource.JPAView.Owner.OwnerView;
import org.datasource.JPAView.Owner.OwnerViewBuilder;
import org.datasource.JPAView.Owner.OwnerViewRepository;
import org.datasource.JPAView.PropertyTypes.PropertyTypesView;
import org.datasource.JPAView.PropertyTypes.PropertyTypesViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("")
public class RESTViewServiceJPA {

	private static final Logger logger = Logger.getLogger(RESTViewServiceJPA.class.getName());

	@GetMapping(value = "/ping", produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String pingDataSource() {
		logger.info(">>>> DSA-SQL-JPAService:: RESTViewService is Up!");
		return "Ping response from DSA-SQL-JPAService!";
	}

	@Autowired
	private CitiesViewRepository citiesViewRepository;

	@GetMapping(value = "/cities", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ResponseBody
	public List<CitiesView> getCities() {
		System.out.println(">>>>> getCities() endpoint called!");
		return citiesViewRepository.findAll();
	}

	@Autowired
	private OwnerViewRepository ownerViewRepository;

	@GetMapping(value = "/owners", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ResponseBody
	public List<OwnerView> getOwners() {
		return ownerViewRepository.findAll();
	}
	@Autowired
	private OwnerViewBuilder ownerViewBuilder;
	@Autowired

	private PropertyTypesViewRepository propertyTypesViewRepository;

	@GetMapping("/property-types")
	@ResponseBody
	public List<PropertyTypesView> getPropertyTypes() {
		return propertyTypesViewRepository.findAll();
	}

/*
http://localhost:8091/DSA_SQL_JPAService/rest/owners
http://localhost:8091/DSA_SQL_JPAService/rest/cities
http://localhost:8091/DSA_SQL_JPAService/rest/property-types
 */

}
