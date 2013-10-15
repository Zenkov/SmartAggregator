package by.iba.course.pm.api.jdbc;


import by.iba.course.pm.api.jdbc.stmt.Statement;
import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author: Andrew
 * @Date: 10/15/13
 */


public final class DBStatementsExecutor {
	private static final String DB_NAME = "";
	private static final String HOST = "";
	private static final String PORT = "";
	private static final DBStatementsExecutor dbStatementsExecutor = new DBStatementsExecutor();

	private DataSource dataSource;

	private DBStatementsExecutor() {
		BasicDataSource dataSource = new BasicDataSource();
		configureDS(dataSource);
		this.dataSource = dataSource;

	}

	private void configureDS(BasicDataSource dataSource) {
		dataSource.setDriverClassName("org.apache.derby.jdbc.EmbeddedDriver");
		dataSource.setUrl(String.format("jdbc:derby://%s:%s/%s", HOST, PORT, DB_NAME));
	}

	public static DBStatementsExecutor getInstance() {
		return dbStatementsExecutor;
	}


	public void executeStatement(Statement statement) throws SQLException {
		Connection connection = dataSource.getConnection();
		PreparedStatement preparedStmt = connection.prepareStatement(statement.getQuery());
		Object[] params = statement.getParams();
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				preparedStmt.setObject(i + 1, params[i]);
			}
		}

		try {
			preparedStmt.execute();
			ResultSet resultSet = preparedStmt.getResultSet();
			statement.processResult(resultSet);
			resultSet.close();
		} catch (Exception ignored) {
			ignored.printStackTrace(); //TODO: for debug purposes only, remove later
		} finally {
			connection.close();
		}
	}
}
