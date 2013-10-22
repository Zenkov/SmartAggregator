package by.bsu.course.pm;


import by.bsu.course.pm.api.adapters.TutByNewsAdapter;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

public class LinksMapper extends Mapper<Object, Text, Text, IntWritable> {
    private static final Logger LOGGER = Logger.getLogger(LinksMapper.class.getName());
    private final static IntWritable one = new IntWritable(1);
    private Text newLink = new Text();


    public void map(Object key, Text value, Context context)
            throws IOException, InterruptedException {
        String url = value.toString().split("\\s+")[0].trim();

        try {

            if (isValidLink(url)) {

                Document document = Jsoup.connect(url).get();
                TutByNewsAdapter adapter = new TutByNewsAdapter(document);
                if (adapter.isArticlePresent()) {
//                    write
                }
                Elements allLinks = document.select("body a");
                for (Element a : allLinks) {
                    String urlToWrite = a.attr("abs:href");

                    if (isValidLink(urlToWrite) && isLinkToInternalPage(urlToWrite, url)) {
                        newLink.set(urlToWrite);
                        context.write(newLink, one);
                    }
                }
            }
        }
        catch (Exception e) {
            LOGGER.severe(String.format("%s: %s", e.getClass().getName(), e.getMessage()));
        }
    }

    private boolean isValidLink(String url) {
        return (url.length() > 0) &&
                (!url.startsWith("mailto")) &&
                (!url.endsWith(".jpeg")) &&
                (!url.endsWith(".jpg")) &&
                (!url.endsWith(".gif"));
    }

    private boolean isLinkToInternalPage(String linkInPage, String urlOfPage) {
        boolean validLink = false;
        try {
            URL pageUrl = new URL(urlOfPage);
            URL urlInPage = new URL(linkInPage);
            System.out.println(urlOfPage);
            validLink = urlInPage.getHost().contains(pageUrl.getHost());
        }
        catch (MalformedURLException e) {
            LOGGER.severe(String.format("%s: %s", e.getClass().getName(), e.getMessage()));
        }

        return validLink;
    }
}
