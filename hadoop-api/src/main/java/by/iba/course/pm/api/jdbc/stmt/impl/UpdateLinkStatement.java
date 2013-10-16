package by.iba.course.pm.api.jdbc.stmt.impl;


import by.iba.course.pm.api.jdbc.stmt.PredefinedStatement;
import by.iba.course.pm.api.jdbc.stmt.Statement;

import java.sql.ResultSet;

/**
 * @Author: Andrew
 * @Date: 10/16/13
 */


public class UpdateLinkStatement extends Statement<Boolean> {
	public UpdateLinkStatement(String link) {
		super(PredefinedStatement.UPDATE_LINK, link);
	}

	@Override
	public void processResult(ResultSet resultSet) {
		// do nothing as we don't care about the update result
	}
}
