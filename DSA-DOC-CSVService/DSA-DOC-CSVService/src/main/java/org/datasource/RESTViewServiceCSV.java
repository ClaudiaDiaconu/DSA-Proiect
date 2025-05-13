package org.datasource;

import org.datasource.csv.custcategories.BudgetCategoryView;
import org.datasource.csv.custcategories.BudgetCategoryViewBuilder;
import org.datasource.property_stats.PropertyDemandStatView;
import org.datasource.property_stats.PropertyDemandStatViewBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;


/*	REST Service URL
       http://localhost:8097/DSA-DOC-CSVService/rest/budget/budgetCategoryView
       http://localhost:8097/DSA-DOC-CSVService/rest/budget/propertyDemandStats
*/
@RestController
@RequestMapping("/budget")
public class RESTViewServiceCSV {

	private static final Logger logger = Logger.getLogger(RESTViewServiceCSV.class.getName());

	@Autowired
	private PropertyDemandStatViewBuilder propertyDemandStatViewBuilder;
	@Autowired
	private BudgetCategoryViewBuilder budgetCategoryViewBuilder;

	@GetMapping(value = "/budgetCategoryView", produces = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE
	})
	public List<BudgetCategoryView> getBudgetCategoryView() throws Exception {
		if (budgetCategoryViewBuilder.getViewList().isEmpty()) {
			return budgetCategoryViewBuilder.build().getViewList();
		} else {
			return budgetCategoryViewBuilder.getViewList();
		}
	}

	@GetMapping(value = "/propertyDemandStats", produces = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE
	})
	public List<PropertyDemandStatView> getAllPropertyDemandStats() {
		return propertyDemandStatViewBuilder.build().getViewList();
	}

		}
