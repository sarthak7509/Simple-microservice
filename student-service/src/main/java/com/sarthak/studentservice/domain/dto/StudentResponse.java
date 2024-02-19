package com.sarthak.studentservice.domain.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {
    private String id;
    private String name;
    private int age;
    private String gender;
    private School school;
}