package com.example.library.util;

import com.example.library.entity.Book;
import com.example.library.entity.Order;
import com.example.library.model.BookDTO;
import com.example.library.model.OrderDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MappingUtil {

    final
    ModelMapper modelMapper;

    public MappingUtil(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public BookDTO mapBookToBookDTO(Book book){
        return modelMapper.map(book, BookDTO.class);
    }

    public OrderDTO mapOrderToOrderDTO(Order order){
        return modelMapper.map(order, OrderDTO.class);
    }
}
