package com.sarthak.studentservice.domain.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class School {
    private int id;
    private String schoolName;
    private String location;
    private String principalName;
}