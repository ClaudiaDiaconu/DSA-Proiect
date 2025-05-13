package org.datasource.neo4j.views.Nodes.Customer;

import org.datasource.neo4j.Neo4JDataSourceConnector;
import org.datasource.neo4j.views.Nodes.History.HistoryView;
import org.neo4j.ogm.session.Session;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HistoryViewBuilder {
    private final Neo4JDataSourceConnector dataSourceConnector;
    private List<HistoryView> historyViewList = new ArrayList<>();

    public HistoryViewBuilder(Neo4JDataSourceConnector connector) {
        this.dataSourceConnector = connector;
    }

    public HistoryViewBuilder build() {
        Session session = dataSourceConnector.getNeo4JSession();
        this.historyViewList = new ArrayList<>(session.loadAll(HistoryView.class));
        session.clear();
        return this;
    }

    public List<HistoryView> getViewList() {
        return this.build().historyViewList;
    }
}
