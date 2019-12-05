package mano2.studentperformance.converters;

import mano2.studentperformance.dto.SubjectDTO;
import mano2.studentperformance.entity.Subject;
import org.springframework.stereotype.Component;

@Component
public class SubjectConverter {

    public Subject toEntity(SubjectDTO subjectDTO){
        Subject subject = new Subject();
        subject.setId(subjectDTO.getId());
        subject.setName(subjectDTO.getName());
        return subject;
    }

    public SubjectDTO toDTO(Subject subject){
        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.setId(subject.getId());
        subjectDTO.setName(subject.getName());
        return subjectDTO;
    }
}
