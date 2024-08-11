package com.example.diploma.model.db.entity;

import com.example.diploma.model.dto.enums.customer.CustomerCondition;
import com.example.diploma.model.dto.enums.customer.Status;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "email")
    String email;
    String password;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "middle_name")
    String middleName;

    @Column(name = "age")
    Integer age;

    @Column(name = "serial_Num_License")
    String serialNumLicense;

    @Column(name = "created_at")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    LocalDateTime createdAt;

    @Column(name = "updated_at")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    LocalDateTime updatedAt;

    @Column(name = "condition")
    @Enumerated(EnumType.STRING)
    private CustomerCondition customerCondition;

    @Column(name = "customer_status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "roles")
    private String roles;

    @OneToOne(mappedBy = "car", cascade = CascadeType.ALL)
    @JsonBackReference(value = "rent")
    private RentInfo rent;

    @OneToMany()
    @JoinColumn(name = "payment_id")
    private List<Payment> payment;
}
