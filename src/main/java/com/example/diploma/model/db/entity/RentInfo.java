package com.example.diploma.model.db.entity;

import com.example.diploma.model.dto.enums.car.Condition;
import com.example.diploma.model.dto.enums.rent.RentStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "rent_info")
public class RentInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "car_id")
//    private Long carId;
//
//    @Column(name = "customer_id")
//    private Long customerId;

    @Column(name = "customer_email")
    String email;

    @Column(name = "start_date_rent")
    private LocalDate startDateRent;

    @Column(name = "end_date_rent")
    private LocalDate endDateRent;

    @Column(name = "cost")
    private BigDecimal cost;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private RentStatus rentStatus;



    @Column(name = "Condition")
    @Enumerated(EnumType.STRING)
    private Condition condition;


    @Column(name = "update")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    LocalDateTime updatedAt;

    @Column(name = "created_at")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    LocalDateTime createdAt;


    @JsonManagedReference(value = "rent")
    @OneToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "car_id", referencedColumnName = "id")
    @JoinColumn(name = "car_id")
    Car car;



    @JsonManagedReference(value = "rent")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    Customer customer;

}
