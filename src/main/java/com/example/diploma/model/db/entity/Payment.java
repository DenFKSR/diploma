package com.example.diploma.model.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "rent_id")
    private RentInfo rent;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private  Customer customer;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "numCard")
    private Long numCard;

    @Column(name = "pasCard")
    private Integer pasCard;

    @Column(name = "cardDate")
    private LocalDate cardDate;

}
