package com.IyadAltoubah.controller;


import com.IyadAltoubah.model.Feed;
import com.IyadAltoubah.repository.NewsFeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class FeedController {
    @Autowired
     private  NewsFeedRepository newsFeedRepository;


    @GetMapping("/feeds")
    public Iterable<Feed> getAllFeeds(){
        return newsFeedRepository.findAll();
    }

    @GetMapping("/feeds/{id}")
  public Optional<Feed> getFeedById(@PathVariable long id){
      return newsFeedRepository.findById(id);
    }

}
