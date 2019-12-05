package mano2.studentperformance.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Builder
@Getter
@Setter
@Entity
@Table(name = "MARKS")
@NoArgsConstructor
@AllArgsConstructor
public class Mark extends BaseEntity {


    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    private String note;

    @Column(name = "mark")
    private int markValue;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;


}
