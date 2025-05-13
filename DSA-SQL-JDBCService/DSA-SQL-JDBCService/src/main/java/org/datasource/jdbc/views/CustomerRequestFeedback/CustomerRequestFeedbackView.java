package org.datasource.jdbc.views.CustomerRequestFeedback;

import lombok.Value;
import java.util.Date;

@Value
public class CustomerRequestFeedbackView {
    String fullName;
    String customerCity;
    Integer requestId;
    String propertyType;
    Double maxBudget;
    Date requestDate;
    Date feedbackDate;
    Integer rating;
    String comments;
}
