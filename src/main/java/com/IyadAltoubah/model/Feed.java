package com.IyadAltoubah.model;


import lombok.*;

import javax.persistence.*;
@Data
@Entity
@Table(name = "feed")
public class Feed {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;

    private String description;

    private String pubdate;

    private String image;

}
