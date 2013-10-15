package by.iba.course.pm.api.jdbc.stmt.impl;


import by.iba.course.pm.api.jdbc.stmt.PredefinedStatement;
import by.iba.course.pm.api.jdbc.stmt.Statement;

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
	public Boolean processResult(ResultSet resultSet) {
		return true; // as we don't care about the insert result
	}
}
