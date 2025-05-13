package org.datasource.neo4j.views.Nodes.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.datasource.neo4j.views.Nodes.Request.RequestView;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.List;

@NodeEntity(label = "Customer")
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class CustomerView {

    @Id
    private Long id;
    private String name;
    private String email;
    private String country;
    private String city;

    @Relationship(type = "HAS_REQUEST")
    private List<RequestView> requests = new ArrayList<>();
}


