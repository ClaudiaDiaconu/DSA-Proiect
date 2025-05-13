package org.datasource.jdbc.views.propertyrequest;

import lombok.Value;
import java.util.Date;

@Value
public class PropertyRequestView {
	Integer requestId;
	Integer customerId;
	Date requestDate;
	String propertyType;
	Double maxBudget;
	Double minSurface;
	String preferredCity;
	String requestStatus;
}

