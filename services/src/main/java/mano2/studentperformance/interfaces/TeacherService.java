package mano2.studentperformance.interfaces;

import mano2.studentperformance.dto.TeacherDTO;
import mano2.studentperformance.exceptions.ExistingLoginException;

public interface TeacherService extends Service<TeacherDTO> {

    TeacherDTO addTeacher(TeacherDTO entity) throws ExistingLoginException;

}
