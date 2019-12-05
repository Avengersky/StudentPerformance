package mano2.studentperformance.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "ATTENDANCE")
@AllArgsConstructor
@NoArgsConstructor
public class Attendance extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "lessons_id")
    private Lesson lesson;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    private boolean visit;

    private String reason;

    private String note;
}
