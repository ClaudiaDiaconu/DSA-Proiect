package org.datasource.jdbc.views.requestfeedback;

import org.datasource.jdbc.JDBCDataSourceConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class RequestFeedbackViewBuilder {

	private static final String SQL_FEEDBACK = """
		SELECT id, request_id, feedback_date, rating, comments
		FROM request_feedback
	""";

	@Autowired
	private JDBCDataSourceConnector dataSourceConnector;

	private final List<RequestFeedbackView> requestFeedbackViewList = new ArrayList<>();

	public List<RequestFeedbackView> getViewList() {
		return this.build().requestFeedbackViewList;
	}

	public RequestFeedbackViewBuilder build() {
		try (Connection conn = dataSourceConnector.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(SQL_FEEDBACK);
			 ResultSet rs = stmt.executeQuery()) {

			requestFeedbackViewList.clear();

			while (rs.next()) {
				RequestFeedbackView view = new RequestFeedbackView(
						rs.getInt("id"),
						rs.getInt("request_id"),
						rs.getDate("feedback_date"),
						rs.getInt("rating"),
						rs.getString("comments")
				);
				requestFeedbackViewList.add(view);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
}
