package org.datasource.mongodb.views.PropertyHistory;

import org.bson.Document;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.datasource.mongodb.MongoDataSourceConnector;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PropertyHistoryViewBuilder {

	private final MongoDataSourceConnector dataSourceConnector;

	private List<PropertyHistoryView> propertyHistoryViewList = new ArrayList<>();

	public PropertyHistoryViewBuilder(MongoDataSourceConnector connector) {
		this.dataSourceConnector = connector;
	}

	public PropertyHistoryViewBuilder build() {
		this.select();
		System.out.println(">>> Total documente citite: " + propertyHistoryViewList.size());
		return this;
	}

	private void select() {
		MongoDatabase db = dataSourceConnector.getMongoDatabase();
		MongoCollection<Document> collection = db.getCollection("property_history");

		propertyHistoryViewList.clear();

		try (MongoCursor<Document> cursor = collection.find().iterator()) {
			while (cursor.hasNext()) {
				Document doc = cursor.next();
				try {
					PropertyHistoryView view = new PropertyHistoryView(
							doc.getString("property_id"),
							new SimpleDateFormat("yyyy-MM-dd").parse(doc.getString("modification_date")),
							doc.get("previous_price", Number.class).doubleValue(),
							doc.get("new_price", Number.class).doubleValue(),
							doc.getString("reason"),
							doc.getInteger("oracle_id")
					);

					propertyHistoryViewList.add(view);
				} catch (ParseException e) {
					e.printStackTrace();
				}

			}
		}
	}

	public List<PropertyHistoryView> getViewList() {
		return this.build().propertyHistoryViewList;
	}
}
