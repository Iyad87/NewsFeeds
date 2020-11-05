package com.IyadAltoubah;


import com.IyadAltoubah.model.Feed;
import com.IyadAltoubah.parser.RSSFeedParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.stream.XMLStreamException;
import java.util.List;

@SpringBootApplication
public class NewsFeedApplication {

    public static void main(String[] args) throws XMLStreamException {

        SpringApplication.run(NewsFeedApplication.class, args);

        RSSFeedParser parser = new RSSFeedParser(
                "http://feeds.nos.nl/nosjournaal");
       List<Feed> feedList = parser.readFeed();

       for(Feed feed : feedList){
           System.out.println(feed);
       }
    }
}
