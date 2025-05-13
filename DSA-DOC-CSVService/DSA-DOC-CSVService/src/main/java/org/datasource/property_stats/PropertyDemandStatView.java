package org.datasource.property_stats;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class PropertyDemandStatView {
    private String city;
    private String propertyType;
    private Integer totalRequests;
    private String mostRequestedMonth;
}
