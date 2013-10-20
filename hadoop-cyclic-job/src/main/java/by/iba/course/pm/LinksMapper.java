package by.iba.course.pm;


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.jsoup.HttpStatusException;
import org.jsoup.*;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;

public class LinksMapper extends Mapper<Object, Text, Text, IntWritable> {
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
                   System.out.println(adapter.getTitle());

                    System.out.println("YEAH!");
                }
                Elements allLinks = document.select("body a");
                for (Element a : allLinks) {
                    String urlToWrite = a.attr("abs:href");
//                    System.out.println(urlToWrite);

                    if (isValidLink(urlToWrite) && isLinkToInternalPage(urlToWrite, url)) {
                        newLink.set(urlToWrite);
                        context.write(newLink, one);
                    }
                }
            }
        } catch (SocketTimeoutException ex) {
            System.out.println("Timeout");
        } catch (IllegalArgumentException ex) {
            System.out.println(" Illegial");
        } catch (UnsupportedMimeTypeException ex) {
            System.out.println("Unsupported");

        } catch (HttpStatusException ex) {
            System.out.println("Status");
        } catch (SocketException ex) {
            System.out.println("Socket Exc");
        } catch (IOException ex) {
            System.out.println("IO Exc");
        }
        catch (Exception e) {
            System.out.println("EXCE Exc");
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
            System.out.println("Incorrect link");
        }

        return validLink;
    }
}
