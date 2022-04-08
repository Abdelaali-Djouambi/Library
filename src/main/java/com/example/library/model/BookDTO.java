package com.example.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO extends AbstractDTO{

    private String title;
    private String author;
    private Long price;
    private Long stock;


}
