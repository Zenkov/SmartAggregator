package by.bsu.course.pm.api.adapters;

import org.jsoup.nodes.Document;

/**
 * Created with IntelliJ IDEA.
 * User: afilipko
 * Date: 10/17/13
 * Time: 9:29 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class NewsAdapter {

    protected Document page;

    public NewsAdapter() {

    }

    public abstract boolean isArticlePresent();

    public abstract String getTitle();

    public abstract String getContent();
}
