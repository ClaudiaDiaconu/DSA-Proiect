package org.datasource.neo4j.views.Nodes.Owner;
import org.datasource.neo4j.views.Nodes.Owner.OwnerView;
import org.springframework.stereotype.Service;
import org.datasource.neo4j.Neo4JDataSourceConnector;
import org.neo4j.ogm.session.Session;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
public class OwnerViewBuilder {
    private final Neo4JDataSourceConnector dataSourceConnector;
    private List<OwnerView> ownerViewList = new ArrayList<>();

    public OwnerViewBuilder(Neo4JDataSourceConnector connector) {
        this.dataSourceConnector = connector;
    }

    public OwnerViewBuilder build() {
        Session session = dataSourceConnector.getNeo4JSession();
        this.ownerViewList = new ArrayList<>(session.loadAll(OwnerView.class));
        session.clear();
        return this;
    }

    public List<OwnerView> getViewList() {
        return this.build().ownerViewList;
    }
}
