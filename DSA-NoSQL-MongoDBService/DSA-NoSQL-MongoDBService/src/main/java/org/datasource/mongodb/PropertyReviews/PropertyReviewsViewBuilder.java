package org.datasource.mongodb.PropertyReviews;

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
public class PropertyReviewsViewBuilder {

    private final MongoDataSourceConnector dataSourceConnector;

    private List<PropertyReviewsView> propertyReviewViewList = new ArrayList<>();

    public PropertyReviewsViewBuilder(MongoDataSourceConnector connector) {
        this.dataSourceConnector = connector;
    }

    public PropertyReviewsViewBuilder build() {
        this.select();
        System.out.println(">>> Total documente citite: " + propertyReviewViewList.size());
        return this;
    }

    private void select() {
        MongoDatabase db = dataSourceConnector.getMongoDatabase();
        MongoCollection<Document> collection = db.getCollection("property_reviews");

        propertyReviewViewList.clear();

        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                try {
                    PropertyReviewsView view = new PropertyReviewsView(
                            doc.getString("property_id"),
                            new SimpleDateFormat("yyyy-MM-dd").parse(doc.getString("review_date")),
                            doc.getInteger("rating"),
                            doc.getString("reviewer"),
                            doc.getString("comment"),
                            doc.getInteger("oracle_id")
                    );
                    propertyReviewViewList.add(view);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<PropertyReviewsView> getViewList() {
        System.out.println(">>> getViewList() called!");
        return this.build().propertyReviewViewList;
    }

}