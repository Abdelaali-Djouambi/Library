package com.example.library.service;

import com.example.library.model.OrderBookDTO;
import com.example.library.model.OrderDTO;
import com.example.library.model.ValidateOrderDTO;

import java.util.Collection;
import java.util.Optional;

public interface OrderService {
    Collection<OrderDTO> getOrders(String userName);
    Optional<OrderDTO> getOrder(ValidateOrderDTO validateOrderDTO);
    Optional<OrderDTO> orderBook(OrderBookDTO orderBookDTO);
    Optional<OrderDTO> validateOrder(ValidateOrderDTO validateOrderDTO);
    Optional<OrderDTO> cancelBookOrder(OrderBookDTO orderBookDTO);
}
