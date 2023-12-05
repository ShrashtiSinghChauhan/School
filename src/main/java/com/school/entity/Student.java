package com.school.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Column(name="email",nullable = false,unique = true)
    private String email;
    @Column(name="phoneNumber", nullable = false, unique = true)
    private String phoneNumber;
    private String address;
}
