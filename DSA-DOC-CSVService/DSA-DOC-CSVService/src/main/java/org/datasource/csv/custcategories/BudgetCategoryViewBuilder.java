package org.datasource.csv.custcategories;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class BudgetCategoryViewBuilder {
    private List<BudgetCategoryView> viewList = new ArrayList<>();

    public List<BudgetCategoryView> getViewList() {
        return viewList;
    }

    public BudgetCategoryViewBuilder build() throws Exception {
        viewList = new ArrayList<>();

        viewList.add(new BudgetCategoryView("B1", "Very Low Budget", 0.0, 30000.0));
        viewList.add(new BudgetCategoryView("B2", "Low Budget", 30000.1, 60000.0));
        viewList.add(new BudgetCategoryView("B3", "Lower-Mid Budget", 60000.1, 100000.0));
        viewList.add(new BudgetCategoryView("B4", "Medium Budget", 100000.1, 150000.0));
        viewList.add(new BudgetCategoryView("B5", "Upper-Mid Budget", 150000.1, 200000.0));
        viewList.add(new BudgetCategoryView("B6", "High Budget", 200000.1, 300000.0));
        viewList.add(new BudgetCategoryView("B7", "Luxury Budget", 300000.1, 400000.0));
        viewList.add(new BudgetCategoryView("B8", "Elite Budget", 400000.1, 600000.0));
        viewList.add(new BudgetCategoryView("B9", "Ultra Luxury", 600000.1, 800000.0));
        viewList.add(new BudgetCategoryView("B10", "Premium Elite", 800000.1, 1000000.0));


        return this;
    }
}
