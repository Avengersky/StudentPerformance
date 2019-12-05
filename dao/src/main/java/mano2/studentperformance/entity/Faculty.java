package mano2.studentperformance.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Data
@Builder
@Entity
@Table(name = "FACULTIES")
@AllArgsConstructor
@NoArgsConstructor
public class Faculty extends BaseEntity {

    private String facultyName;

    @OneToMany(mappedBy = "faculty", cascade = CascadeType.ALL)
    private Set<Group> groups;

}
