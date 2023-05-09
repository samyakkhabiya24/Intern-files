package com.db.courseproject.repositry;

import com.db.courseproject.classes.CourseDetails;

import java.util.List;

public interface CourseDetailsRepository {

    List<CourseDetails> findAll();

    List<CourseDetails> findById(Long id);
}
