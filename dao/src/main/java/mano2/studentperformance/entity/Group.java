package mano2.studentperformance.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@Entity
@Table(name = "GROUPSE")
@AllArgsConstructor
@NoArgsConstructor
public class Group extends BaseEntity {

    private long groupNumber;
    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private Set<Student> students;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private Set<Lesson> lesson;



}
