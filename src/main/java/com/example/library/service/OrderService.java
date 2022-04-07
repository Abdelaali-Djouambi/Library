package com.example.library.service;

import com.example.library.model.MakeOrderDTO;
import com.example.library.model.OrderDTO;
import com.example.library.model.ValidateOrderDTO;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getOrders(String userName);
    OrderDTO makeOrder(MakeOrderDTO makeOrderDTO);
    OrderDTO validateOrder(ValidateOrderDTO validateOrderDTO);
}
