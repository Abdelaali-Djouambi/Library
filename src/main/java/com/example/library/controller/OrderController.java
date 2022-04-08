package com.example.library.controller;

import com.example.library.model.MakeOrderDTO;
import com.example.library.model.OrderDTO;
import com.example.library.model.ValidateOrderDTO;
import com.example.library.service.OrderService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

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
    @GetMapping(path = "/{oderId}")
    public ResponseEntity<OrderDTO> getOrder(@RequestParam ValidateOrderDTO validateOrderDTO){
        return orderService.getOrder(validateOrderDTO).map(orderDTO -> ResponseEntity
                .ok()
                .header("Location", "/order/" + validateOrderDTO.getOrderId())
                .contentType(MediaType.APPLICATION_JSON)
                .eTag(Long.toString(orderDTO.getVersion()))
                .location(URI.create("/order/" + orderDTO.getId()))
                .body(orderDTO)).orElseGet(() -> ResponseEntity.notFound().build());
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
