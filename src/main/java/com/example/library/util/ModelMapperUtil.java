package com.example.library.util;

import com.example.library.entity.Book;
import com.example.library.entity.OrderLib;
import com.example.library.entity.User;
import com.example.library.model.BookDTO;
import com.example.library.model.OrderDTO;
import com.example.library.model.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperUtil {

    private final ModelMapper modelMapper;

    @Autowired
    public ModelMapperUtil(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public BookDTO mapBookToBookDTO(Book book){
        return modelMapper.map(book, BookDTO.class);
    }

    public OrderDTO mapOrderToOrderDTO(OrderLib order){
        return modelMapper.map(order, OrderDTO.class);
    }

    public UserDTO mapUserToUserDTO(User user){
        return modelMapper.map(user, UserDTO.class);
    }

}
