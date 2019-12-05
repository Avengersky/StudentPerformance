package mano2.studentperformance.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "LESSONS")
@AllArgsConstructor
@NoArgsConstructor
public class Lesson extends BaseEntity {

    private Date date;

    private String topic;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lesson", cascade = CascadeType.ALL)
    private Set<Mark> marks;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
    private Set<Attendance> attendances;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "subgroup_id")
    private Subgroup subgroup;


}
