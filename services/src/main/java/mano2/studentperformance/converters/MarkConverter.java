package mano2.studentperformance.converters;

import mano2.studentperformance.dto.MarkDTO;
import mano2.studentperformance.entity.Lesson;
import mano2.studentperformance.entity.Mark;
import mano2.studentperformance.entity.Student;
import mano2.studentperformance.entity.Subject;
import org.springframework.stereotype.Component;

@Component
public class MarkConverter {

    public Mark toEntity(MarkDTO markDTO){
        Mark mark = new Mark();
        mark.setId(markDTO.getId());
        mark.setMarkValue(markDTO.getMarkValue());
        mark.setNote(markDTO.getNote());
        Lesson lesson = new Lesson();
        lesson.setId(markDTO.getLessonId());
        mark.setLesson(lesson);
        Student student = new Student();
        student.setId(markDTO.getStudentId());
        mark.setStudent(student);
        return mark;
    }

    public MarkDTO toDTO(Mark mark){
        MarkDTO markDTO = new MarkDTO();
        markDTO.setId(mark.getId());
        markDTO.setMarkValue(mark.getMarkValue());
        markDTO.setNote(mark.getNote());
        markDTO.setLessonId(mark.getLesson().getId());
        markDTO.setStudentId(mark.getStudent().getId());
        return markDTO;
    }
}
