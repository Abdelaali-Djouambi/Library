package com.example.library.controller;

import com.example.library.model.BookDTO;
import com.example.library.model.OrderDTO;
import com.example.library.model.UserDTO;
import com.example.library.model.ValidateOrderDTO;
import com.example.library.service.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    @InjectMocks
    OrderController orderController;

    @Mock
    OrderService orderService;

    @Test
    @DisplayName("Getting user's orders list - Success")
    void testGetOrdersSuccess() {
        List<OrderDTO> orderDTOs= Mockito.mock(ArrayList.class);
        when(orderDTOs.size()).thenReturn(10);
        when(orderService.getOrders(any(String.class))).thenReturn(orderDTOs);
        ResponseEntity<Collection<OrderDTO>> responseEntity = orderController.getOrders("foo");

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);
        assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/order");

        assertThat(responseEntity.getBody().size()).isEqualTo(10);
    }

    @Test
    @DisplayName("Get books by id - success")
    void findBookByIdSuccess(){
        BookDTO bookDTO= new BookDTO("Lord of the ring", "J.R.R Tolkien",30l,200l);
        UserDTO userDTO = new UserDTO("foo", 10000l);
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setVersion(1l);
        orderDTO.setId(2l);
        orderDTO.setBooks(List.of(bookDTO));
        orderDTO.setUser(userDTO);

        ValidateOrderDTO validateOrderDTO=new ValidateOrderDTO(1l,"foo");

        when(orderService.getOrder(any(ValidateOrderDTO.class))).thenReturn(Optional.of(orderDTO));
        ResponseEntity<OrderDTO> responseEntity = orderController.getOrder(validateOrderDTO);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);
        assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/order/2");

        assertThat(responseEntity.getBody().getBooks().get(0).getTitle()).isEqualTo("Lord of the ring");
    }

    @Test
    @DisplayName("Get books by id - Not found")
    void findBookByIdFail(){
        when(orderService.getOrder(nullable(ValidateOrderDTO.class))).thenReturn(Optional.empty());
        ResponseEntity<OrderDTO> responseEntity = orderController.getOrder(null);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(404);
    }
}
