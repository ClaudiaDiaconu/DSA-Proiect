package org.datasource.jdbc.views.requestfeedback;

import lombok.Value;
import java.util.Date;

@Value
public class RequestFeedbackView {
    Integer id;
    Integer requestId;
    Date feedbackDate;
    Integer rating;
    String comments;

public RequestFeedbackView(Integer id, Integer requestId, Date feedbackDate, Integer rating, String comments) {
    this.id = id;
    this.requestId = requestId;
    this.feedbackDate = feedbackDate;
    this.rating = rating;
    this.comments = comments;
}
}


