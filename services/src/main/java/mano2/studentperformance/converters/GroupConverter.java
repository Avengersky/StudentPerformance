package mano2.studentperformance.converters;

import mano2.studentperformance.dto.BaseEntityDTO;
import mano2.studentperformance.dto.GroupDTO;
import mano2.studentperformance.entity.BaseEntity;
import mano2.studentperformance.entity.Faculty;
import mano2.studentperformance.entity.Group;
import org.springframework.stereotype.Component;

@Component
public class GroupConverter{

    public Group toEntity(GroupDTO groupDTO){
        Group group = new Group();
        group.setId(groupDTO.getId());
        group.setGroupNumber(groupDTO.getGroupNumber());
        Faculty faculty = new Faculty();
        faculty.setId(groupDTO.getFacultyId());
        group.setFaculty(faculty);
        return group;
    }


    public GroupDTO toDTO(Group group) {
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setId(group.getId());
        groupDTO.setGroupNumber(group.getGroupNumber());
        groupDTO.setFacultyId(group.getFaculty().getId());
        return groupDTO;
    }

}
