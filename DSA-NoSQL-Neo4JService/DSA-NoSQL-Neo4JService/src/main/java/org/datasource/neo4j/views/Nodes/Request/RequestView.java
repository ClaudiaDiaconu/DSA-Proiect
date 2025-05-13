package org.datasource.neo4j.views.Nodes.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity(label = "Request")
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class RequestView {
    @Id
    private Long id;
    private String type;
    private Double maxBudget;
    private Integer minSurface;
    private String city;
    private String status;
}
