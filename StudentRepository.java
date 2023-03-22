package com.shivila.boot.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shivila.boot.model.Student;

public interface StudentRepository extends
                  JpaRepository<Student, Integer> {

}
