package mano2.studentperformance.repositories;

import mano2.studentperformance.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}
