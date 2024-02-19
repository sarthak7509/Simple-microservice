package com.sarthak.schoolservice.controllers;

import com.sarthak.schoolservice.domain.School;
import com.sarthak.schoolservice.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/school")
public class SchoolController {
    @Autowired
    private SchoolService schoolService;

    @PostMapping
    public School saveData(@RequestBody School school){
        return schoolService.addSchool(school);
    }

    @GetMapping
    public List<School> getSchool(){
        return schoolService.fetchAllSchool();
    }

    @GetMapping(path = "/{id}")
    public School getSchoolById(@PathVariable int id){
        return schoolService.fetchSchoolById(id);
    }
}
