package by.bsu.course.pm.api.jdbc.stmt;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * @Author: Andrew
 * @Date: 10/16/13
 */


public abstract class Statement<T> {
	protected final static Logger LOGGER = Logger.getLogger(Statement.class.getName());

	protected T result;
	private String query;
	private Object[] params;

	public Statement(String query, Object... params) {
		this.query = query;
		this.params = params;
	}

	public final String getQuery() {
		return query;
	}

	protected void setQuery(String query) {
		this.query = query;
	}

	public final Object[] getParams() {
		return params;
	}

	public abstract void processResult(ResultSet resultSet) throws SQLException;

	public T getResult() {
		return result;
	}
}
