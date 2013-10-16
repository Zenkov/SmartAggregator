package by.iba.course.pm.api.jdbc;


import by.iba.course.pm.api.jdbc.stmt.impl.GetLinksStatement;
import by.iba.course.pm.api.jdbc.stmt.impl.InsertLinkStatement;
import by.iba.course.pm.api.jdbc.stmt.impl.UpdateLinksStatement;
import junit.framework.TestCase;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author: Andrew
 * @Date: 10/16/13
 */

//dumb tests
public class DBStatementsExecutorTest extends TestCase {

	public static final String IBM_COM_LINK = "ibm.com";
	public static final String APACHE_ORG_LINK = "apache.org";
	public static final String GOOGLE_COM_LINK = "google.com";
	public static final String YAHOO_ORG_LINK = "yahoo.com";

	//no exception should occur
	public void testInsertDuplicatedLinks() {
		DBStatementsExecutor dbStatementsExecutor = DBStatementsExecutor.getInstance();
		try {
			dbStatementsExecutor.executeStatement(new InsertLinkStatement(IBM_COM_LINK));
			dbStatementsExecutor.executeStatement(new InsertLinkStatement(YAHOO_ORG_LINK));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testGetAllLinks() {
		DBStatementsExecutor dbStatementsExecutor = DBStatementsExecutor.getInstance();
		GetLinksStatement statement = new GetLinksStatement();
		try {
			dbStatementsExecutor.executeStatement(new InsertLinkStatement(APACHE_ORG_LINK));
			dbStatementsExecutor.executeStatement(statement);
		} catch (SQLException e) {
			fail(e.getMessage());
		}
		List<String> result = statement.getResult();

		assertNotNull("at least empty list should be returned", result);
		if (!result.contains(IBM_COM_LINK) || !result.contains(YAHOO_ORG_LINK)) {
			fail("Invalid data returned");
		}


	}

	public void testUpdateLinks() {
		DBStatementsExecutor dbStatementsExecutor = DBStatementsExecutor.getInstance();
		GetLinksStatement statement = new GetLinksStatement();
		try {
			dbStatementsExecutor.executeStatement(new InsertLinkStatement(GOOGLE_COM_LINK));
			dbStatementsExecutor.executeStatement(new InsertLinkStatement(APACHE_ORG_LINK));
			dbStatementsExecutor.executeStatement(new UpdateLinksStatement(GOOGLE_COM_LINK, APACHE_ORG_LINK));
			dbStatementsExecutor.executeStatement(statement);
		} catch (SQLException e) {
			fail(e.getMessage());
		}
		List<String> result = statement.getResult();

		assertNotNull("at least empty list should be returned", result);
		assertEquals("only two values should be returned", 2, result.size());
		if (!result.contains(IBM_COM_LINK) || !result.contains(YAHOO_ORG_LINK)) {
			fail("Invalid data returned");
		}
	}
}
