package by.bsu.course.pm.api.jdbc.stmt;


/**
 * @Author: Andrew
 * @Date: 10/16/13
 */


public interface PredefinedStatement {
	String VISITED_COLUMN = "visited";
	String LINK_COLUMN = "link";
	String INSERT_LINK = "insert into links values (?, 'N')";
	String SELECT_LINKS = String.format("select %s from links where %s='N'", LINK_COLUMN, VISITED_COLUMN);
    String INSERT_ARTICLE = "insert into articles values (DEFAULT, ?, ?, ?)";
    String CONTENT_COLUMN = "content";
    String SELECT_ARTICLE_BY_ID = "select * from articles where id=?";
}
