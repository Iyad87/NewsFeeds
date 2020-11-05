package com.IyadAltoubah.parser;

import com.IyadAltoubah.model.Feed;
import com.IyadAltoubah.repository.NewsFeedRepository;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RSSFeedParser {

    static final String ITEM = "item";
    static final String TITLE = "title";
    static final String DESCRIPTION = "description";
    static final String PUB_DATE = "pubDate";
    static final String ENCLOSURE = "enclosure";


    final URL url;

    public RSSFeedParser(String feedUrl) {

        try {
            this.url = new URL(feedUrl);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Feed> readFeed() throws XMLStreamException {
        Feed feed = null;
        List<Feed> feedList = new ArrayList<>();

        try {
            // Set header values intial to the empty string
            String description = "";
            String title = "";
            String image = "";
            String pubdate = "";

            // First create a new XMLInputFactory

            XMLInputFactory inputFactory = XMLInputFactory.newInstance();

            // Setup a new eventReader

            InputStream in = read();
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);

            // read the XML document

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    String localPart = event.asStartElement().getName()
                            .getLocalPart();
                    switch (localPart) {
                        case TITLE:
                            title = getCharacterData(eventReader);
                            break;
                        case DESCRIPTION:
                            description = eventReader.getElementText();
                            break;
                        case PUB_DATE:
                            pubdate = getCharacterData(eventReader);
                            break;
                        case ENCLOSURE:
                            image = event.asStartElement().getAttributeByName(new QName("url")).getValue();
                            break;
                    }
                }  else if (event.isEndElement()) {
                    if (event.asEndElement().getName().getLocalPart() == (ITEM)) {
                        feed = new Feed();
                        feed.setTitle(title);
                        feed.setDescription(description);
                        feed.setPubdate(pubdate);
                        feed.setImage(image);
                        feedList.add(feed);

                        continue;
                    }
                }
            }
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
        return feedList;
    }
    private InputStream read() {
        try {
            return url.openStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getCharacterData(XMLEventReader eventReader) throws XMLStreamException {
        String result = "";
        XMLEvent event = eventReader.nextEvent();
        if (event instanceof Characters) {
            result = event.asCharacters().getData();
        }
        return result;
    }
}
