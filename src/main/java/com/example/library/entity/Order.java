package com.example.library.entity;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

public class Order extends AbstractEntity{
    private User user;
    @OneToMany
    @JoinColumn(name = "order_id")
    private List<Book> books;
}
