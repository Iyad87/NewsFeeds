package com.IyadAltoubah.queryResolver;


import com.IyadAltoubah.model.Feed;
import com.IyadAltoubah.repository.NewsFeedRepository;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FeedQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private NewsFeedRepository newsFeedRepository;

    public Iterable<Feed>  getFeeds(){
        return newsFeedRepository.findAll();
    }
}
