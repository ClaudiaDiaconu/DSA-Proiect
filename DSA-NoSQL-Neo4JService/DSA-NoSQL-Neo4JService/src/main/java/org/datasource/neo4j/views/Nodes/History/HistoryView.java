package org.datasource.neo4j.views.Nodes.History;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity(label = "History")
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class HistoryView {
    @Id
    private String propertyId;
    private String reason;
    private Double prevPrice;
    private Double newPrice;
}
