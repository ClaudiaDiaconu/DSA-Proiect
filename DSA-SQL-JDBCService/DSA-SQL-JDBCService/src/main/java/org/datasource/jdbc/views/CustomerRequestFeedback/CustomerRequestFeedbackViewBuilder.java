package org.datasource.jdbc.views.CustomerRequestFeedback;

import org.datasource.jdbc.JDBCDataSourceConnector;
import org.datasource.jdbc.views.CustomerRequestFeedback.CustomerRequestFeedbackView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerRequestFeedbackViewBuilder {

    private static final String SQL_VIEW = """
        SELECT full_name, customer_city, request_id, property_type, max_budget,
               request_date, feedback_date, rating, comments
        FROM v_request_feedback_summary
    """;

    @Autowired
    private JDBCDataSourceConnector dataSourceConnector;

    private final List<CustomerRequestFeedbackView> viewList = new ArrayList<>();

    public List<CustomerRequestFeedbackView> getViewList() {
        return this.build().viewList;
    }

    public CustomerRequestFeedbackViewBuilder build() {
        try (Connection conn = dataSourceConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_VIEW);
             ResultSet rs = stmt.executeQuery()) {

            viewList.clear();

            while (rs.next()) {
                CustomerRequestFeedbackView view = new CustomerRequestFeedbackView(
                        rs.getString("full_name"),
                        rs.getString("customer_city"),
                        rs.getInt("request_id"),
                        rs.getString("property_type"),
                        rs.getBigDecimal("max_budget").doubleValue(),
                        rs.getDate("request_date"),
                        rs.getDate("feedback_date"),
                        rs.getInt("rating"),
                        rs.getString("comments")
                );
                viewList.add(view);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return this;
    }
}
