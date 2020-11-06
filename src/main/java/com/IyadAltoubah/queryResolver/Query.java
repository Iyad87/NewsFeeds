package com.IyadAltoubah.queryResolver;


import com.IyadAltoubah.model.Feed;
import com.IyadAltoubah.repository.NewsFeedRepository;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Query implements GraphQLQueryResolver {

    @Autowired
    private NewsFeedRepository newsFeedRepository = null;

    public Iterable<Feed> feed(){
        return newsFeedRepository.findAll();
    }
}
