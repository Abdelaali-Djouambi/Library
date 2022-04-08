package com.example.library.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@Table(name = "lb_order")
public class Order extends AbstractEntity{
    @ManyToOne
    private User user;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Book> books;
}
