package mano2.studentperformance.impl;


import mano2.studentperformance.converters.RoleConverter;
import mano2.studentperformance.dto.RoleDTO;
import mano2.studentperformance.entity.Role;
import mano2.studentperformance.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

@Service("roleService")
public class RoleServiceImpl{

    @Autowired
    private RoleRepository repository;

    @Autowired
    private RoleConverter converter;

    public List<RoleDTO> getAll() {
        List<RoleDTO> roleDTOS = new ArrayList<>();
        StreamSupport.stream(repository.findAll().spliterator(), false)
                .forEach(faculty ->roleDTOS.add(converter.toDTO(faculty)));
        return roleDTOS;
    }

    public RoleDTO getById(Long id) {
        return converter.toDTO(repository.findById(id).get());
    }
}
