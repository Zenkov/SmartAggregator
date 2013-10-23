package by.bsu.course.pm.api.jdbc.stmt.impl;

import by.bsu.course.pm.api.jdbc.stmt.PredefinedStatement;
import by.bsu.course.pm.api.jdbc.stmt.Statement;

import java.sql.ResultSet;

/**
 * Created with IntelliJ IDEA.
 * User: afilipko
 * Date: 10/22/13
 * Time: 4:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class InsertArticleStatement extends Statement<Boolean>  {
    public InsertArticleStatement(String link, String articleTitle, String articleContent) {
        super(PredefinedStatement.INSERT_ARTICLE, link, articleTitle, articleContent);
    }

    @Override
    public void processResult(ResultSet resultSet) {
        // do nothing as we don't care about the insert result
    }
}
