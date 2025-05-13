package org.datasource.neo4j.views.Nodes.Property;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.datasource.neo4j.views.Nodes.History.HistoryView;
import org.datasource.neo4j.views.Nodes.Review.ReviewView;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.List;

@NodeEntity(label = "Property")
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class PropertyView {
    @Id
    private Long id;
    private Double price;
    private Integer size;
    private String listed;
    private Boolean available;

    @Relationship(type = "HAS_HISTORY")
    private List<HistoryView> history = new ArrayList<>();

    @Relationship(type = "HAS_REVIEW")
    private List<ReviewView> reviews = new ArrayList<>();
}
