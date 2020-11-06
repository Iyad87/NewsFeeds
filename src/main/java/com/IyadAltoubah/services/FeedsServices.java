package com.IyadAltoubah.services;

import com.IyadAltoubah.NewsFeedApplication;
import com.IyadAltoubah.model.Feed;
import com.IyadAltoubah.parser.FeedParser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public class FeedsServices {

    public static List<Feed>  getNewsFeed() throws IOException {

        FeedParser feedParser = null;
        try{
            feedParser = new FeedParser(getPropValues("source") + "?" + getPropValues("format"));
        } catch (IOException e){
            e.printStackTrace();
        }

        return feedParser.readFeed();

    }


    public static void saveNewsFeed( List<Feed> feeds) throws IOException {
        try(Connection connection = DriverManager.getConnection(getPropValues("spring.datasource.url"),
                getPropValues("spring.datasource.username"),
                getPropValues("spring.datasource.password"))) {

            System.out.println("Reading News Feeds");

            truncateTable(connection);


            PreparedStatement st = null;
            for (Feed feed : feeds) {
                st = connection.prepareStatement("INSERT INTO feed (title, description, pubdate, image) VALUES (?, ?, ?, ?)");
                st.setString(1, feed.getTitle());
                st.setString(2, feed.getDescription());
                st.setString(3, feed.getPubdate());
                st.setString(4, feed.getImage());
                st.executeUpdate();
            }
            st.close();


        } catch (SQLException | IOException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
    }

   private static void truncateTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE feed");
        }
    }


    public static String getPropValues(String property) throws IOException {
        String result = "";
        InputStream inputStream = null;

        try {
            Properties prop = new Properties();
            String propFileName = "application.properties";
            inputStream = NewsFeedApplication.class.getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            result = prop.getProperty(property);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
        return result;
    }
}
