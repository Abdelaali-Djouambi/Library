package com.example.library.service;

import com.example.library.model.MakeOrderDTO;
import com.example.library.model.OrderDTO;
import com.example.library.model.ValidateOrderDTO;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{
    @Override
    public Collection<OrderDTO> getOrders(String userName) {
        return Collections.emptyList();
    }

    @Override
    public Optional<OrderDTO> getOrder(ValidateOrderDTO validateOrderDTO) {
        return Optional.empty();
    }

    @Override
    public Optional<OrderDTO> makeOrder(MakeOrderDTO makeOrderDTO) {
        return Optional.empty();
    }

    @Override
    public Optional<OrderDTO> validateOrder(ValidateOrderDTO validateOrderDTO) {

        return Optional.empty();
    }
}
