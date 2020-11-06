package com.IyadAltoubah.parser;

import com.IyadAltoubah.model.Feed;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FeedParser extends DefaultHandler {

    private final URL url;

    public FeedParser(String url) {

        try {
            this.url = new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Feed> readFeed() {
        List<Feed> feedList = new ArrayList<>();

        try{
            String contentType = "";
            try{
                contentType = getContentType();
            } catch (IOException e){
                System.out.println("Exception is " + e);
            }

            switch (contentType){
                case "xml":
                    XmlParser xmlParser = new XmlParser();
                    feedList = xmlParser.readXmlFeed(url);
                    break;
            }
        } catch (RuntimeException e){
            System.out.println("Exception is " + e);


        }
        return feedList;
    }

    private String getContentType() throws IOException {
        String contentType = "";
        String contentTypeFull = url.openConnection().getContentType();
        if (contentTypeFull.contains("xml")) {
            contentType = "xml";
        }
        return contentType;
    }
}
