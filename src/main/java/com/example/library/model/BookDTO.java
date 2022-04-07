package com.example.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO extends AbstractDTO{

    private String title;
    private LocalDate releaseDate;
    private String author;
    private Long price;


}
