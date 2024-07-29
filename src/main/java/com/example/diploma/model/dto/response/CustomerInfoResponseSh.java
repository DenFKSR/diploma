package com.example.diploma.model.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Data
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerInfoResponseSh {
String email;
String firstName;
String lastName;
String middleName;
Integer age;
String serialNumLicense;
}
