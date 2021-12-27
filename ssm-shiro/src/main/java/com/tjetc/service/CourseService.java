package com.tjetc.service;

import com.tjetc.entity.Course;

/**
 * @author solang
 * @date 2021-12-27 9:58
 */
public interface CourseService {
    Course findById(Long id);
}
