package mano2.studentperformance.converters;

import mano2.studentperformance.dto.RoleDTO;
import mano2.studentperformance.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter {

    public Role toEntity(RoleDTO roleDTO){
        Role role = new Role();
        role.setId(roleDTO.getId());
        role.setName(roleDTO.getName());
        return role;
    }

    public RoleDTO toDTO(Role role){
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName());
        return roleDTO;
    }
}
