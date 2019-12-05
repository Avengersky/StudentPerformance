package mano2.studentperformance.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "STUDENTS")
@AllArgsConstructor
@NoArgsConstructor
public class Student extends BaseEntity {

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<Mark> marks;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<Attendance> attendances;

    @ManyToOne
    @JoinColumn(name = "subgroup_id")
    private Subgroup subgroup;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", updatable = false, unique = true, nullable = false)
    private User user;


}
