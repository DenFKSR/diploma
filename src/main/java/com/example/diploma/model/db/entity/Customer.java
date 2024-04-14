package com.example.diploma.model.db.entity;

import com.example.diploma.model.dto.enums.customer.CustomerCondition;
import com.example.diploma.model.dto.enums.customer.Status;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @Column(name = "condition")//create, delete....
    @Enumerated(EnumType.STRING)
    private CustomerCondition customerCondition;

    @Column(name = "customer_status")//free, banned....
    @Enumerated(EnumType.STRING)
    private Status status;



    @Column(name = "roles")
    private String roles;

    @OneToMany
    @JsonManagedReference
    List<Car> car;






}
