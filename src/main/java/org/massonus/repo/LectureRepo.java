package org.massonus.repo;

import org.massonus.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepo extends JpaRepository<Lecture, Long> {
}
