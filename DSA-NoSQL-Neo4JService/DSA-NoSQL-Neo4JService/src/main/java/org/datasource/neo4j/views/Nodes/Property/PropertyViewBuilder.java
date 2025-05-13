package org.datasource.neo4j.views.Nodes.Property;

import org.datasource.neo4j.views.Nodes.Property.PropertyViewBuilder;
import org.datasource.neo4j.Neo4JDataSourceConnector;
import org.datasource.neo4j.views.Nodes.Property.PropertyView;
import org.neo4j.ogm.session.Session;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
public class PropertyViewBuilder {
    private final Neo4JDataSourceConnector dataSourceConnector;
    private List<PropertyView> propertyViewList = new ArrayList<>();

    public PropertyViewBuilder(Neo4JDataSourceConnector connector) {
        this.dataSourceConnector = connector;
    }

    public PropertyViewBuilder build() {
        Session session = dataSourceConnector.getNeo4JSession();
        this.propertyViewList = new ArrayList<>(session.loadAll(PropertyView.class));
        session.clear();
        return this;
    }

    public List<PropertyView> getViewList() {
        return this.build().propertyViewList;
    }
}
