package org.datasource.mongodb.SiteActiviyLogs;
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
public class SiteActivityLogsViewBuilder {
    private final MongoDataSourceConnector dataSourceConnector;
    private List<SiteActivityLogsView> siteActivityLogsViewList = new ArrayList<>();

    public SiteActivityLogsViewBuilder(MongoDataSourceConnector connector) {
        this.dataSourceConnector = connector;
    }

    public SiteActivityLogsViewBuilder build() {
        this.select();
        System.out.println(">>> Total documente citite din site_activity_logs: " + siteActivityLogsViewList.size());
        return this;
    }

    private void select() {
        MongoDatabase db = dataSourceConnector.getMongoDatabase();
        MongoCollection<Document> collection = db.getCollection("site_activity_logs");

        siteActivityLogsViewList.clear();

        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                try {
                    SiteActivityLogsView view = new SiteActivityLogsView(
                            doc.getString("user_id"),
                            doc.getString("action"),
                            new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(doc.getString("timestamp")),
                            doc.getString("ip_address"),
                            doc.getInteger("oracle_id")
                    );
                    siteActivityLogsViewList.add(view);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<SiteActivityLogsView> getViewList() {
        return this.build().siteActivityLogsViewList;
    }
}


