package by.bsu.course.pm.api.jdbc.stmt.impl;

import by.bsu.course.pm.api.jdbc.stmt.PredefinedStatement;
import by.bsu.course.pm.api.jdbc.stmt.Statement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: afilipko
 * Date: 10/23/13
 * Time: 9:02 AM
 * To change this template use File | Settings | File Templates.
 */
public class GetArticleByIdStatement extends Statement<String> {
    public GetArticleByIdStatement(int id) {
        super(PredefinedStatement.SELECT_ARTICLE_BY_ID, id);
    }

    @Override
    public void processResult(ResultSet resultSet) throws SQLException {
        if (resultSet != null) {
            result = resultSet.getString(PredefinedStatement.CONTENT_COLUMN);
        } else {
            LOGGER.severe("Null ResultSet after SELECT");
        }
    }
}
