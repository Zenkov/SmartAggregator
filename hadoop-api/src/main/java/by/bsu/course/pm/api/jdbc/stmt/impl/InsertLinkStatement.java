package by.bsu.course.pm.api.jdbc.stmt.impl;


import by.bsu.course.pm.api.jdbc.stmt.PredefinedStatement;
import by.bsu.course.pm.api.jdbc.stmt.Statement;

import java.sql.ResultSet;

/**
 * @Author: Andrew
 * @Date: 10/16/13
 */


public class InsertLinkStatement extends Statement<Boolean>{
	public InsertLinkStatement(String link) {
		super(PredefinedStatement.INSERT_LINK, link);
	}

	@Override
	public void processResult(ResultSet resultSet) {
		// do nothing as we don't care about the insert result
	}
}
