package com.example.library.controller;

import com.example.library.entity.OrderLib;
import com.example.library.exceptions.ResourceNotFoundException;
import com.example.library.model.*;
import com.example.library.service.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    @InjectMocks
    OrderController orderController;

    @Mock
    OrderService orderService;

    @Test
    @DisplayName("Getting user's orders list - Success")
    void test_get_order_success() {
        List<OrderDTO> orderDTOs = Mockito.mock(ArrayList.class);
        when(orderDTOs.size()).thenReturn(10);
        when(orderService.getOrders(any(String.class))).thenReturn(orderDTOs);
        ResponseEntity<Collection<OrderDTO>> responseEntity = orderController.getOrders("foo");

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);
        assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/order");

        assertThat(responseEntity.getBody()).hasSize(10);
    }

    @Test
    @DisplayName("Get order- success")
    void find_order_byId_success() {
        BookDTO bookDTO = new BookDTO("Lord of the ring", "J.R.R Tolkien", 30l, 200l);
        UserDTO userDTO = new UserDTO("foo", 10000l);
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setVersion(1l);
        orderDTO.setId(2l);
        orderDTO.setBooks(List.of(bookDTO));
        orderDTO.setUser(userDTO);

        ValidateOrderDTO validateOrderDTO = new ValidateOrderDTO(1l, "foo");

        when(orderService.getOrder(any(ValidateOrderDTO.class))).thenReturn(Optional.of(orderDTO));
        ResponseEntity<OrderDTO> responseEntity = orderController.getOrder(validateOrderDTO);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);
        assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/order/2");

        assertThat(responseEntity.getBody().getBooks().get(0).getTitle()).isEqualTo("Lord of the ring");
    }

    @Test
    @DisplayName("Get order - Not found")
    void find_order_byId_not_found() {
        when(orderService.getOrder(nullable(ValidateOrderDTO.class))).thenReturn(Optional.empty());
        ResponseEntity<OrderDTO> responseEntity = orderController.getOrder(null);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(404);
    }

    @Test
    @DisplayName("User adds book to order - Success")
    void make_order_success() {
        OrderBookDTO orderBookDTO = new OrderBookDTO("foo", 2l);
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(2l);
        orderDTO.setVersion(1l);

        when(orderService.orderBook(nullable(OrderBookDTO.class))).thenReturn(Optional.of(orderDTO));

        ResponseEntity<OrderDTO> responseEntity = orderController.makeBookOrder(orderBookDTO);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);
        assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/order/makeBookOrder");
    }


    @Test
    @DisplayName("User cancels an ordered book - Success")
    void cancel_book_order_success() {
        OrderBookDTO orderBookDTO = new OrderBookDTO("foo", 2l);
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(2l);
        orderDTO.setVersion(1l);

        when(orderService.cancelBookOrder(nullable(OrderBookDTO.class))).thenReturn(Optional.of(orderDTO));

        ResponseEntity<OrderDTO> responseEntity = orderController.cancelBookOrder(orderBookDTO);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);
        assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/order/cancelBookOrder");
    }

    @Test
    @DisplayName("User cancels an ordered book - Resource not found")
    void cancel_book_order_error_user_not_found() {
        OrderBookDTO orderBookDTO = new OrderBookDTO("foo", 2l);
        when(orderService.cancelBookOrder(nullable(OrderBookDTO.class))).thenThrow(ResourceNotFoundException.class);
        assertThrows(ResourceNotFoundException.class, () -> orderController.cancelBookOrder(orderBookDTO));
    }

    @Test
    @DisplayName("User adds book to order - Resource not found")
    void make_order_error_user_not_found() {
        when(orderService.orderBook(nullable(OrderBookDTO.class))).thenReturn(Optional.empty());
        ResponseEntity responseEntity = orderController.makeBookOrder(null);
        assertEquals(HttpStatus.NOT_FOUND.value(), responseEntity.getStatusCode().value());
    }


    @Test
    @DisplayName("User validates order - Success")
    void validate_order_success() {
        ValidateOrderDTO validateOrderDTO = new ValidateOrderDTO(2l, "foo");
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(2l);
        orderDTO.setVersion(1l);
        orderDTO.setUser(new UserDTO("foo", 1000l));
        orderDTO.setStatus(OrderLib.STATUS.VALIDATED.toString());
        when(orderService.validateOrder(nullable(ValidateOrderDTO.class))).thenReturn(Optional.of(orderDTO));

        ResponseEntity<OrderDTO> responseEntity = orderController.validateOrder(validateOrderDTO);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);
        assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/order/validateOrder");

        assertThat(responseEntity.getBody().getUser().getUserName()).isEqualTo("foo");
        assertThat(responseEntity.getBody().getStatus()).isEqualTo(OrderLib.STATUS.VALIDATED.toString());
    }


    @Test
    @DisplayName("User validates order - Resource not found")
    void validate_order_error_user_not_found() {
        when(orderService.validateOrder(nullable(ValidateOrderDTO.class))).thenReturn(Optional.empty());
        ResponseEntity responseEntity = orderController.validateOrder(null);
        assertEquals(HttpStatus.NOT_FOUND.value(), responseEntity.getStatusCode().value());
    }


}
