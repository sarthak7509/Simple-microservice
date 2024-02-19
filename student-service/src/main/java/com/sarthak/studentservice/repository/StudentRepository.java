package com.sarthak.studentservice.repository;

import com.sarthak.studentservice.domain.Students;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<Students,String> {

}
