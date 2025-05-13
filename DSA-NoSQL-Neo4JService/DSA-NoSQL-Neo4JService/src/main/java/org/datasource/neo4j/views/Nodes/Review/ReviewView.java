package org.datasource.neo4j.views.Nodes.Review;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity(label = "Review")
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class ReviewView {
    @Id
    private String propertyId;
    private Integer rating;
    private String reviewer;
    private String comment;
}
