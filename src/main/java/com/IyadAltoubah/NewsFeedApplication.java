package com.IyadAltoubah;


import com.IyadAltoubah.model.Feed;
import com.IyadAltoubah.services.FeedsServices;
import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@SpringBootApplication
public class NewsFeedApplication {

    public static void main(String[] args) {

        SpringApplication.run(NewsFeedApplication.class, args);

        TimerTask task = new TimerTask() {
            @SneakyThrows
            public void run() {
                List<Feed> feeds = FeedsServices.getNewsFeed();
                FeedsServices.saveNewsFeed(feeds);
            }
        };
        Timer timer = new Timer();

        long period = 0;
        try {
            period = Long.parseLong( FeedsServices.getPropValues("interval"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        timer.schedule(task, 0, period);
    }
}
