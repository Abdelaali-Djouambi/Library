package com.example.library.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class OrderDTO extends AbstractDTO{

    List<BookDTO> books;

}
