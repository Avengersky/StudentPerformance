package mano2.studentperformance.interfaces;

import mano2.studentperformance.dto.StudentDTO;
import mano2.studentperformance.exceptions.ExistingLoginException;

public interface StudentService extends Service<StudentDTO> {

    StudentDTO addStudent(StudentDTO entity) throws ExistingLoginException;
}
