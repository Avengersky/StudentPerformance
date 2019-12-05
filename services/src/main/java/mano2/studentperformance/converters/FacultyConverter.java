package mano2.studentperformance.converters;

import mano2.studentperformance.dto.FacultyDTO;
import mano2.studentperformance.entity.Faculty;
import org.springframework.stereotype.Component;

@Component
public class FacultyConverter {

    public Faculty toEntity(FacultyDTO facultyDTO){
        Faculty faculty = new Faculty();
        faculty.setId(facultyDTO.getId());
        faculty.setFacultyName(facultyDTO.getName());
        return faculty;
    }

    public FacultyDTO toDTO(Faculty faculty){
        FacultyDTO facultyDTO = new FacultyDTO();
        facultyDTO.setId(faculty.getId());
        facultyDTO.setName(faculty.getFacultyName());
        return facultyDTO;
    }
}
