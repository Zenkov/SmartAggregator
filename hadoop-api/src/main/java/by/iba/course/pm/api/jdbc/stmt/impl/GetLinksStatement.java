package by.iba.course.pm.api.jdbc.stmt.impl;


import by.iba.course.pm.api.jdbc.stmt.PredefinedStatement;
import by.iba.course.pm.api.jdbc.stmt.Statement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: Andrew
 * @Date: 10/16/13
 */


public class GetLinksStatement extends Statement<List<String>> {
	public GetLinksStatement() {
		super(PredefinedStatement.SELECT_LINKS);
	}

	@Override
	public void processResult(ResultSet resultSet) throws SQLException {
		if (resultSet != null) {
			List<String> links = new LinkedList<String>();
			while (resultSet.next()) {
				links.add(resultSet.getString(PredefinedStatement.LINK_COLUMN));
			}
			result = links;
		} else {
			LOGGER.severe("Null ResultSet after SELECT");
		}
	}
}
