package org.datasource.neo4j.views.Nodes.Customer;

import org.datasource.neo4j.Neo4JDataSourceConnector;
import org.neo4j.ogm.session.Session;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerViewBuilder {
    private final Neo4JDataSourceConnector dataSourceConnector;

    private List<CustomerView> customerViewList = new ArrayList<>();

    public CustomerViewBuilder(Neo4JDataSourceConnector connector) {
        this.dataSourceConnector = connector;
    }

    public CustomerViewBuilder build() {
        Session session = dataSourceConnector.getNeo4JSession();
        this.customerViewList = new ArrayList<>(session.loadAll(CustomerView.class));
        session.clear();
        return this;
    }

    public List<CustomerView> getViewList() {
        return this.build().customerViewList;
    }
}
