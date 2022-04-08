package com.example.library.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Book extends AbstractEntity{

    private String title;
    private long stock;
    private String author;
    private Long price;

}
