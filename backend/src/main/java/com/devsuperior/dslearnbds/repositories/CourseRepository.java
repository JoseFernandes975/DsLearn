package com.devsuperior.dslearnbds.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dslearnbds.entities.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

	

}
