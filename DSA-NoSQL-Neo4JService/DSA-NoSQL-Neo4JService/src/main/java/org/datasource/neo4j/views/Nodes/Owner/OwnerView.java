package org.datasource.neo4j.views.Nodes.Owner;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.datasource.neo4j.views.Nodes.Property.PropertyView;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.List;

@NodeEntity(label = "Owner")
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class OwnerView {
    @Id
    private Long id;
    private String name;
    private String email;
    private String phone;

    @Relationship(type = "OWNS")
    private List<PropertyView> properties = new ArrayList<>();
}
