package mano2.studentperformance.repositories;

import mano2.studentperformance.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
