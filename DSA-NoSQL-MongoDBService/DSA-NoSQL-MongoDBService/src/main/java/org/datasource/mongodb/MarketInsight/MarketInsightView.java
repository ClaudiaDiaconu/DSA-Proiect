package org.datasource.mongodb.MarketInsight;
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
public class MarketInsightView implements Serializable {
    private String city;
    private Double averagePrice;
    private Double averageSurface;
    private Date timestamp;
    private String trend;
    private Integer oracleId;
}
