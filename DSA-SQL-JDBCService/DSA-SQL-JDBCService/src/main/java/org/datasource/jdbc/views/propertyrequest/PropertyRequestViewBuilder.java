package org.datasource.jdbc.views.propertyrequest;

import org.datasource.jdbc.JDBCDataSourceConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class PropertyRequestViewBuilder {

	private static final String SQL_PROPERTY_REQUESTS = """
        SELECT request_id, customer_id, request_date, property_type,
               max_budget, min_surface, preferred_city, request_status
        FROM property_requests
    """;

	@Autowired
	private JDBCDataSourceConnector dataSourceConnector;

	private final List<PropertyRequestView> propertyRequestViewList = new ArrayList<>();

	public List<PropertyRequestView> getViewList() {
		return this.build().propertyRequestViewList;
	}

	public PropertyRequestViewBuilder build() {
		try (Connection conn = dataSourceConnector.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(SQL_PROPERTY_REQUESTS);
			 ResultSet rs = stmt.executeQuery()) {

			propertyRequestViewList.clear();

			while (rs.next()) {
				PropertyRequestView view = new PropertyRequestView(
						rs.getInt("request_id"),
						rs.getInt("customer_id"),
						rs.getDate("request_date"),
						rs.getString("property_type"),
						rs.getBigDecimal("max_budget").doubleValue(),
						rs.getBigDecimal("min_surface").doubleValue(),
						rs.getString("preferred_city"),
						rs.getString("request_status")
				);
				propertyRequestViewList.add(view);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return this;
	}
}
