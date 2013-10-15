package by.iba.course.pm.api.jdbc.stmt;


/**
 * @Author: Andrew
 * @Date: 10/16/13
 */


public interface PredefinedStatement {
	String VISITED_COLUMN = "visited";
	String LINK_COLUMN = "link";
	String INSERT_LINK = "insert into links values (?, 'N')";
	String UPDATE_LINK = String.format("update links set %s='Y' where %s=?", VISITED_COLUMN, LINK_COLUMN);
	String SELECT_LINKS = String.format("select %s from links where %s='N'", LINK_COLUMN, VISITED_COLUMN);
}
