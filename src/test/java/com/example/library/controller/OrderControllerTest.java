package com.example.library.controller;

import com.example.library.model.BookDTO;
import com.example.library.model.OrderDTO;
import com.example.library.service.BookService;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
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


}
