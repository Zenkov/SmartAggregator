package by.bsu.course.pm.api.adapters;

import org.jsoup.nodes.Document;

/**
 * Created with IntelliJ IDEA.
 * User: afilipko
 * Date: 10/17/13
 * Time: 9:36 AM
 * To change this template use File | Settings | File Templates.
 */
public class TutByNewsAdapter extends NewsAdapter {
    public TutByNewsAdapter(Document document) {
        this.page = document;
    }

    public boolean isArticlePresent() {
        return !this.page.select("body div.b-article div.m_header h1").isEmpty() &&
                !this.page.select("body div#article_body").isEmpty();
    }

    public String getTitle() {
        return this.page.select("body div.b-article div.m_header h1").first().text();
    }

    public String getContent() {
        return this.page.select("body div#article_body").first().text();
    }

    @Override
    public String siteUrl() {
        return "http://tut.by";
    }
}
