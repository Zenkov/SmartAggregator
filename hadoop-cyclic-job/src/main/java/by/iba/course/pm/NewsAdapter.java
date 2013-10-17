package by.iba.course.pm;

import org.jsoup.nodes.Document;

/**
 * Created with IntelliJ IDEA.
 * User: afilipko
 * Date: 10/17/13
 * Time: 9:29 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class NewsAdapter {

    private Document page;

    public NewsAdapter() {

    }

    public NewsAdapter(Document document) {
        this.page = document;
    }
}
