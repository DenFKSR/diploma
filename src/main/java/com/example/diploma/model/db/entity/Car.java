package com.example.diploma.model.db.entity;

import com.example.diploma.model.dto.enums.car.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "car")

public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "year")
    private Integer year;

    @Column(name = "body_type")
    @Enumerated(EnumType.STRING)
    private Body bodyType;

    @Column(name = "transmission")
    @Enumerated(EnumType.STRING)
    private Transmission transmission;

    @Column(name = "type_engine")
    @Enumerated(EnumType.STRING)
    private EngineType engineType;

    @Column(name = "amount_seats")
    private Integer seatsAmount;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "fuel_сonsumption")
    private Double fuelСonsumption;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "Condition")
    @Enumerated(EnumType.STRING)
    private Condition condition;

    @Column(name = "address")
    private String address;

    @Column(name = "register_number")
    private String registerNumber;


    @Column(name = "update")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    LocalDateTime updatedAt;



    @ManyToOne
    @JsonBackReference(value = "driver_cars")
    Customer customer;

}
