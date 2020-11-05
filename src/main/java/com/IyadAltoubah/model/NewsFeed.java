package com.IyadAltoubah.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "newsFeed")
public class NewsFeed {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     private long id;

    private String title;

    private String description;

    private String pubdate;

    private String image;

}
