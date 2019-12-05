package mano2.studentperformance.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Data
@Entity
@Table(name = "SUBGROUPS")
@AllArgsConstructor
@NoArgsConstructor
public class Subgroup extends BaseEntity {

    @OneToMany(mappedBy = "subgroup", cascade = CascadeType.ALL)
    private Set<Student> students;

    @OneToMany(mappedBy = "subgroup", cascade = CascadeType.ALL)
    private Set<Lesson> lesson;

    private int number;
}
