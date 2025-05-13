package org.datasource.csv.custcategories;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BudgetCategoryView implements Serializable {
    private String categoryCode;
    private String categoryName;
    private Double lowerBound;
    private Double upperBound;
}
