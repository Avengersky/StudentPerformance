package mano2.studentperformance.converters;

import mano2.studentperformance.dto.TeacherDTO;
import mano2.studentperformance.entity.Role;
import mano2.studentperformance.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeacherConverter{

    @Autowired
    private UserConverter userConverter;

    public Teacher toEntity(TeacherDTO teacherDTO){
        Teacher teacher = new Teacher();
        teacher.setId(teacherDTO.getId());
        teacher.setUser(userConverter.toEntity(teacherDTO.getUserDTO()));
        return teacher;
    }

    public TeacherDTO toDTO(Teacher teacher){
        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDTO.setId(teacher.getId());
        teacherDTO.setUserDTO(userConverter.toDTO(teacher.getUser()));
        return teacherDTO;
    }
}
