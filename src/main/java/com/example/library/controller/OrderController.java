package com.example.library.controller;

import com.example.library.entity.Book;
import com.example.library.model.BookDTO;
import com.example.library.model.MakeOrderDTO;
import com.example.library.model.OrderDTO;
import com.example.library.model.ValidateOrderDTO;
import com.example.library.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {

    final
    OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<Collection<OrderDTO>> getOrders(@RequestParam String userName){
        Collection<OrderDTO> orders = orderService.getOrders( userName);
        return ResponseEntity
                .ok()
                .header("Location", "/order")
                .contentType(MediaType.APPLICATION_JSON)
                .body(orders);
    }
    @GetMapping
    public ResponseEntity<OrderDTO> getOrder(@RequestParam ValidateOrderDTO validateOrderDTO){
        return null;
    }

    @PostMapping("/makeOrder")
    public ResponseEntity<OrderDTO> makeOrder(@RequestBody MakeOrderDTO makeOrder){
        return null;
    }

    @PostMapping("/validateOrder")
    public ResponseEntity<OrderDTO> validateOrder(@RequestBody ValidateOrderDTO validateOrder){
        return null;
    }
}
