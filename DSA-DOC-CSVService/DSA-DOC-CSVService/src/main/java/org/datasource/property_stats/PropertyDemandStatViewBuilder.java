package org.datasource.property_stats;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class PropertyDemandStatViewBuilder  {

    private List<PropertyDemandStatView> viewList = new ArrayList<>();

    public PropertyDemandStatViewBuilder build() {
        viewList = new ArrayList<>();

        viewList.add(new PropertyDemandStatView("Greece", "Apartment", 3, "March"));
        viewList.add(new PropertyDemandStatView("Saudi Arabia", "Studio", 2, "April"));
        viewList.add(new PropertyDemandStatView("Denmark", "Apartment", 1, "February"));
        viewList.add(new PropertyDemandStatView("Italy", "Studio", 1, "February"));
        viewList.add(new PropertyDemandStatView("Japan", "Apartment", 2, "May"));
        viewList.add(new PropertyDemandStatView("Unspecified", "Studio", 1, "April"));
        viewList.add(new PropertyDemandStatView("Finland", "House", 3, "March"));
        viewList.add(new PropertyDemandStatView("USA", "Apartment", 1, "April"));
        return this;
    }

    public List<PropertyDemandStatView> getViewList() {
        return viewList;
    }
}
