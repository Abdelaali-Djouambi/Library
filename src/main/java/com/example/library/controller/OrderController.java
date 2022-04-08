package com.example.library.controller;

import com.example.library.entity.Book;
import com.example.library.model.MakeOrderDTO;
import com.example.library.model.OrderDTO;
import com.example.library.model.ValidateOrderDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @GetMapping
    public ResponseEntity<Collection<OrderDTO>> getOrders(@RequestParam String userName){
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
