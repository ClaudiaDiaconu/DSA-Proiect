package org.datasource.mongodb.views.PropertyHistory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@JsonIgnoreProperties({"_id"})
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class PropertyHistoryView {
	private String propertyId;
	private Date modificationDate;
	private Double previousPrice;
	private Double newPrice;
	private String reason;
	private Integer oracleId;
}
