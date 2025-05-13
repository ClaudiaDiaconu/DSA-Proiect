package org.datasource.xml.locations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class PropertyHistoryView implements Serializable {
	private String propertyId;
	private String modificationDate;
	private Double previousPrice;
	private Double newPrice;
	private String reason;
	private Integer oracleId;
}
