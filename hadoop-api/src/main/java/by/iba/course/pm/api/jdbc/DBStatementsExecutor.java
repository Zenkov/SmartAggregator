package by.iba.course.pm.api.jdbc;


import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @Author: Andrew
 * @Date: 10/15/13
 */


public class DBStatementsExecutor {
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
		dataSource.setUrl(String.format("jdbc:derby://%s:%s/%s;create=true", HOST, PORT, DB_NAME));
	}

	public static DBStatementsExecutor getInstance() {
		return dbStatementsExecutor;
	}


	//TODO: just an example
	public void insertUrl(String url) {
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("");
			preparedStatement.execute();
		} catch (Exception ignored) {
		}
	}

}
