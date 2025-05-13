package org.datasource.neo4j.views.Nodes.Customer;

import org.datasource.neo4j.Neo4JDataSourceConnector;
import org.datasource.neo4j.views.Nodes.Review.ReviewView;
import org.neo4j.ogm.session.Session;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewViewBuilder {
    private final Neo4JDataSourceConnector dataSourceConnector;
    private List<ReviewView> reviewViewList = new ArrayList<>();

    public ReviewViewBuilder(Neo4JDataSourceConnector connector) {
        this.dataSourceConnector = connector;
    }

    public ReviewViewBuilder build() {
        Session session = dataSourceConnector.getNeo4JSession();
        this.reviewViewList = new ArrayList<>(session.loadAll(ReviewView.class));
        session.clear();
        return this;
    }

    public List<ReviewView> getViewList() {
        return this.build().reviewViewList;
    }
}
