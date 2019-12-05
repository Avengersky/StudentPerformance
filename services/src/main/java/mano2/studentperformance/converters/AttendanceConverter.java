package mano2.studentperformance.converters;

import mano2.studentperformance.dto.AttendanceDTO;
import mano2.studentperformance.entity.Attendance;
import mano2.studentperformance.entity.Lesson;
import mano2.studentperformance.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class AttendanceConverter {

    public Attendance toEntity(AttendanceDTO attendanceDTO){
        Attendance attendance = new Attendance();
        attendance.setId(attendanceDTO.getId());
        attendance.setVisit(attendanceDTO.getVisit());
        attendance.setNote(attendanceDTO.getNote());
        attendance.setReason(attendanceDTO.getReason());
        Lesson lesson = new Lesson();
        lesson.setId(attendanceDTO.getLessonId());
        attendance.setLesson(lesson);
        Student student = new Student();
        student.setId(attendanceDTO.getStudentId());
        attendance.setStudent(student);
        return attendance;
    }

    public AttendanceDTO toDTO(Attendance attendance){
        AttendanceDTO attendanceDTO = new AttendanceDTO();
        attendanceDTO.setId(attendance.getId());
        attendanceDTO.setVisit(attendance.isVisit());
        attendanceDTO.setNote(attendance.getNote());
        attendanceDTO.setReason(attendance.getReason());
        attendanceDTO.setLessonId(attendance.getLesson().getId());
        attendanceDTO.setStudentId(attendance.getStudent().getId());
        return attendanceDTO;
    }
}
