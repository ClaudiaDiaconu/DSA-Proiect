package org.datasource.mongodb.MarketInsight;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.datasource.mongodb.MongoDataSourceConnector;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MarketInsightViewBuilder {

    private final MongoDataSourceConnector dataSourceConnector;
    private List<MarketInsightView> marketInsightViewList = new ArrayList<>();

    public MarketInsightViewBuilder(MongoDataSourceConnector connector) {
        this.dataSourceConnector = connector;
    }

    public MarketInsightViewBuilder build() {
        this.select();
        System.out.println(">>> Total documente citite din market_insights: " + marketInsightViewList.size());
        return this;
    }

    private void select() {
        MongoDatabase db = dataSourceConnector.getMongoDatabase();
        MongoCollection<Document> collection = db.getCollection("market_insights");

        marketInsightViewList.clear();

        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                try {
                    MarketInsightView view = new MarketInsightView(
                            doc.getString("city"),
                            doc.getDouble("average_price"),
                            doc.getDouble("average_surface"),
                            new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(doc.getString("timestamp")),
                            doc.getString("trend"),
                            doc.getInteger("oracle_id")
                    );
                    marketInsightViewList.add(view);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<MarketInsightView> getViewList() {
        return this.build().marketInsightViewList;
    }
}

