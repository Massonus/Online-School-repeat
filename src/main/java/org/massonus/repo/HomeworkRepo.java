package org.massonus.repo;

import org.massonus.entity.Homework;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeworkRepo extends JpaRepository<Homework, Long> {
}
