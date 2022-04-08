package com.example.library.service;

import com.example.library.model.MakeOrderDTO;
import com.example.library.model.OrderDTO;
import com.example.library.model.ValidateOrderDTO;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<OrderDTO> getOrders(String userName);
    Optional<OrderDTO> getOrder(ValidateOrderDTO validateOrderDTO);
    Optional<OrderDTO> makeOrder(MakeOrderDTO makeOrderDTO);
    Optional<OrderDTO> validateOrder(ValidateOrderDTO validateOrderDTO);
}
