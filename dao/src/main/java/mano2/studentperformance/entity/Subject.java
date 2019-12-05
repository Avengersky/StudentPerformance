package mano2.studentperformance.entity;

import lombok.*;

import javax.persistence.*;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "SUBJECTS")
@AllArgsConstructor
@NoArgsConstructor
public class Subject extends BaseEntity{

    @Column(name = "name")
    private String name;
    @ManyToMany(mappedBy = "subjects")
    private Set<Teacher> teachers;
    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private Set<Lesson> lessons;

}
