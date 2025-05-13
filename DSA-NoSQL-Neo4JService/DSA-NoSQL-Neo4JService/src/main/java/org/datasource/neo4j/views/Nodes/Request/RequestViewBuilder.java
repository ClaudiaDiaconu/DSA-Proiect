package org.datasource.neo4j.views.Nodes.Customer;

import org.datasource.neo4j.Neo4JDataSourceConnector;
import org.datasource.neo4j.views.Nodes.Request.RequestView;
import org.neo4j.ogm.session.Session;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RequestViewBuilder {
    private final Neo4JDataSourceConnector dataSourceConnector;
    private List<RequestView> requestViewList = new ArrayList<>();

    public RequestViewBuilder(Neo4JDataSourceConnector connector) {
        this.dataSourceConnector = connector;
    }

    public RequestViewBuilder build() {
        Session session = dataSourceConnector.getNeo4JSession();
        this.requestViewList = new ArrayList<>(session.loadAll(RequestView.class));
        session.clear();
        return this;
    }

    public List<RequestView> getViewList() {
        return this.build().requestViewList;
    }
}
