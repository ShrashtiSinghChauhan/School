package com.school.payload;

import lombok.Data;

@Data
public class StudentDto {
    private long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
}
