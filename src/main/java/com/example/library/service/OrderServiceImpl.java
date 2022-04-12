package com.example.library.service;

import com.example.library.model.OrderBookDTO;
import com.example.library.model.OrderDTO;
import com.example.library.model.ValidateOrderDTO;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public Collection<OrderDTO> getOrders(String userName) {
        return Collections.emptyList();
    }

    @Override
    public Optional<OrderDTO> getOrder(ValidateOrderDTO validateOrderDTO) {
        return Optional.empty();
    }

    @Override
    public Optional<OrderDTO> orderBook(OrderBookDTO orderBookDTO) {
        return Optional.empty();
    }

    @Override
    public Optional<OrderDTO> validateOrder(ValidateOrderDTO validateOrderDTO) {
        return Optional.empty();
    }

    @Override
    public Optional<OrderDTO> cancelBookOrder(OrderBookDTO orderBookDTO) {
        return Optional.empty();
    }
}
