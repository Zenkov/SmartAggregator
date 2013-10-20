package by.bsu.course.pm.api.jdbc.stmt.impl;


import by.bsu.course.pm.api.jdbc.stmt.PredefinedStatement;
import by.bsu.course.pm.api.jdbc.stmt.Statement;

import java.sql.ResultSet;

/**
 * @Author: Andrew
 * @Date: 10/16/13
 */


public class UpdateLinksStatement extends Statement<Boolean> {
	public UpdateLinksStatement(String... links) {
		super(null);
		StringBuilder updateLinksStmt = new StringBuilder(String.format("update links set %s='Y' where %s in (",
				PredefinedStatement.VISITED_COLUMN, PredefinedStatement.LINK_COLUMN));
		for (String s : links) {
			updateLinksStmt.append("'").append(s).append("'").append(",");
		}
		updateLinksStmt.replace(updateLinksStmt.length() - 1, updateLinksStmt.length(), ")");
		setQuery(updateLinksStmt.toString());
	}

	@Override
	public void processResult(ResultSet resultSet) {
		// do nothing as we don't care about the update result
	}
}
