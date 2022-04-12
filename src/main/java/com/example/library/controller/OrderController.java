package com.example.library.controller;

import com.example.library.model.OrderBookDTO;
import com.example.library.model.OrderDTO;
import com.example.library.model.ValidateOrderDTO;
import com.example.library.service.OrderService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Collection<OrderDTO>> getOrders(@RequestParam String userName) {
        Collection<OrderDTO> orders = orderService.getOrders(userName);
        return ResponseEntity
                .ok()
                .header("Location", "/order")
                .contentType(MediaType.APPLICATION_JSON)
                .body(orders);
    }

    @GetMapping(path = "/{oderId}")
    public ResponseEntity<OrderDTO> getOrder(@RequestParam ValidateOrderDTO validateOrderDTO) {
        return orderService.getOrder(validateOrderDTO).map(orderDTO -> ResponseEntity
                .ok()
                .header("Location", "/order/" + orderDTO.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .eTag(Long.toString(orderDTO.getVersion()))
                .body(orderDTO)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/makeBookOrder")
    public ResponseEntity<OrderDTO> makeBookOrder(@RequestBody OrderBookDTO makeOrder) {
        return orderService.orderBook(makeOrder).map(orderDTO -> ResponseEntity
                .ok()
                .header("Location", "/order/makeBookOrder")
                .contentType(MediaType.APPLICATION_JSON)
                .eTag(Long.toString(orderDTO.getVersion()))
                .body(orderDTO)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/validateOrder")
    public ResponseEntity<OrderDTO> validateOrder(@RequestBody ValidateOrderDTO validateOrder) {
        return orderService.validateOrder(validateOrder).map(orderDTO -> ResponseEntity
                .ok()
                .header("Location", "/order/validateOrder")
                .contentType(MediaType.APPLICATION_JSON)
                .eTag(Long.toString(orderDTO.getVersion()))
                .body(orderDTO)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/cancelBookOrder")
    public ResponseEntity<OrderDTO> cancelBookOrder(@RequestBody OrderBookDTO orderBookDTO) {
        return orderService.cancelBookOrder(orderBookDTO).map(orderDTO -> ResponseEntity
                .ok()
                .header("Location", "/order/cancelBookOrder")
                .contentType(MediaType.APPLICATION_JSON)
                .eTag(Long.toString(orderDTO.getVersion()))
                .body(orderDTO)).orElseGet(() -> ResponseEntity.notFound().build());    }
}
