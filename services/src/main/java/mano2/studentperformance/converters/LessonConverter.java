package mano2.studentperformance.converters;

import mano2.studentperformance.dto.LessonDTO;
import mano2.studentperformance.entity.*;
import org.springframework.stereotype.Component;

@Component
public class LessonConverter {

    public Lesson toEntity(LessonDTO lessonDTO){
        Lesson lesson = new Lesson();
        lesson.setId(lessonDTO.getId());
        lesson.setDate(lessonDTO.getDate());
        lesson.setTopic(lessonDTO.getTopic());
        Group group = new Group();
        group.setId(lessonDTO.getGroupId());
        lesson.setGroup(group);
        Subgroup subgroup = new Subgroup();
        subgroup.setId(lessonDTO.getSubgroupId());
        lesson.setSubgroup(subgroup);
        Subject subject = new Subject();
        subject.setId(lessonDTO.getSubjectId());
        lesson.setSubject(subject);
        Teacher teacher = new Teacher();
        teacher.setId(lessonDTO.getTeacherId());
        lesson.setTeacher(teacher);
        return lesson;
    }

    public LessonDTO toDTO(Lesson lesson){
        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.setId(lesson.getId());
        lessonDTO.setDate(lesson.getDate());
        lessonDTO.setTopic(lesson.getTopic());
        lessonDTO.setGroupId(lesson.getGroup().getId());
        lessonDTO.setSubgroupId(lesson.getSubgroup().getId());
        lessonDTO.setSubjectId(lesson.getSubject().getId());
        lessonDTO.setTeacherId(lesson.getTeacher().getId());
        return lessonDTO;
    }
}
