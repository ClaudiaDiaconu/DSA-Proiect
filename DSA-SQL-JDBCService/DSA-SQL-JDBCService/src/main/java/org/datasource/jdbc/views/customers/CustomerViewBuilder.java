package org.datasource.jdbc.views.customers;

import org.datasource.jdbc.JDBCDataSourceConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerViewBuilder {

	private static final String SQL_CUSTOMERS = """
        SELECT id, customer_code, full_name, email, phone, registration_date, country, city
        FROM customers
    """;

	private final List<CustomerView> customersViewList = new ArrayList<>();

	@Autowired
	private JDBCDataSourceConnector dataSourceConnector;

	public List<CustomerView> getViewList() {
		return this.build().customersViewList;
	}

	public CustomerViewBuilder build() {
		try (Connection conn = dataSourceConnector.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(SQL_CUSTOMERS);
			 ResultSet rs = stmt.executeQuery()) {

			customersViewList.clear(); // golim lista înainte de adăugare

			while (rs.next()) {
				CustomerView view = new CustomerView(
						rs.getInt("id"),
						rs.getString("customer_code"),
						rs.getString("full_name"),
						rs.getString("email"),
						rs.getString("phone"),
						rs.getDate("registration_date"),
						rs.getString("country"),
						rs.getString("city")
				);
				customersViewList.add(view);
			}

		} catch (Exception e) {
			e.printStackTrace(); // în proiect real: logger.warning(e.getMessage());
		}

		return this;
	}
}
