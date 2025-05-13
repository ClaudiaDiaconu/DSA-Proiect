package org.datasource.xml.locations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "propertyHistoryList")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class PropertyHistoryListView {

	@XmlElement(name = "entry")
	private List<org.datasource.xml.locations.PropertyHistoryView> entries = new ArrayList<>();
}
