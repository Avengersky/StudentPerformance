package mano2.studentperformance.converters;

import mano2.studentperformance.dto.StudentDTO;
import mano2.studentperformance.entity.Group;
import mano2.studentperformance.entity.Role;
import mano2.studentperformance.entity.Student;
import mano2.studentperformance.entity.Subgroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentConverter {

    @Autowired
    private UserConverter userConverter;

    public Student toEntity(StudentDTO studentDTO){
        Student student = new Student();
        student.setId(studentDTO.getId());
        Group group = new Group();
        group.setId(studentDTO.getGroupId());
        student.setGroup(group);
        Subgroup subgroup = new Subgroup();
        subgroup.setId(studentDTO.getSubGroupId());
        student.setSubgroup(subgroup);
        student.setUser(userConverter.toEntity(studentDTO.getUserDTO()));
        return student;
    }

    public StudentDTO toDTO(Student student){
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setGroupId(student.getGroup().getId());
        studentDTO.setSubGroupId(student.getSubgroup().getId());
        studentDTO.setUserDTO(userConverter.toDTO(student.getUser()));
        return studentDTO;
    }
}


