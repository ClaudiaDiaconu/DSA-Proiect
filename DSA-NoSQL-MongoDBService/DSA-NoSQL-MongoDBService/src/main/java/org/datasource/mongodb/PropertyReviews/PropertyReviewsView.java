package org.datasource.mongodb.PropertyReviews;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties({"_id"})
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class PropertyReviewsView implements Serializable {
    private String propertyId;
    private Date reviewDate;
    private Integer rating;
    private String reviewer;
    private String comment;
    private Integer oracleId;
}
