package by.iba.course.pm.api.jdbc;


import by.iba.course.pm.api.jdbc.stmt.impl.InsertLinkStatement;
import junit.framework.TestCase;

/**
 * @Author: Andrew
 * @Date: 10/16/13
 */


public class DBStatementsExecutorTest extends TestCase {
	//no exception should occur
	public void testInsertDuplicatedLinks() {
		DBStatementsExecutor dbStatementsExecutor = DBStatementsExecutor.getInstance();
		try {
			dbStatementsExecutor.executeStatement(new InsertLinkStatement("ibm.com"));
			dbStatementsExecutor.executeStatement(new InsertLinkStatement("ibm.com"));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
