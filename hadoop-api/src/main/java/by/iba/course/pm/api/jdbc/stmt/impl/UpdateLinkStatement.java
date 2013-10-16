package by.iba.course.pm.api.jdbc.stmt.impl;


import by.iba.course.pm.api.jdbc.stmt.PredefinedStatement;
import by.iba.course.pm.api.jdbc.stmt.Statement;

import java.sql.ResultSet;

/**
 * @Author: Andrew
 * @Date: 10/16/13
 */


public class UpdateLinkStatement extends Statement<Boolean> {
	public UpdateLinkStatement(String... links) {
		super(null);
		StringBuilder updateLinksStmt = new StringBuilder("update links set %s='Y' where %s in (");
		for (String s : links) {
			updateLinksStmt.append(s).append(",");
		}
		updateLinksStmt.replace(updateLinksStmt.length() - 1, updateLinksStmt.length(), ")");
		setQuery(String.format(updateLinksStmt.toString(), PredefinedStatement.VISITED_COLUMN, PredefinedStatement.LINK_COLUMN));
	}

	@Override
	public void processResult(ResultSet resultSet) {
		// do nothing as we don't care about the update result
	}
}
