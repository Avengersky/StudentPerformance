package mano2.studentperformance.repositories;

import mano2.studentperformance.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
}
