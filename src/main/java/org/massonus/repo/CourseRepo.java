package org.massonus.repo;

import org.massonus.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepo extends JpaRepository<Course, Long> {


}
