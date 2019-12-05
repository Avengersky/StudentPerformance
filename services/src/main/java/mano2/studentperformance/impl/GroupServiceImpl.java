package mano2.studentperformance.impl;


import mano2.studentperformance.converters.GroupConverter;
import mano2.studentperformance.dto.GroupDTO;
import mano2.studentperformance.entity.Group;
import mano2.studentperformance.entity.User;
import mano2.studentperformance.exceptions.EntityNotFoundException;
import mano2.studentperformance.interfaces.GroupService;
import mano2.studentperformance.repositories.GroupRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service("groupService")
public class GroupServiceImpl implements GroupService {


    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupConverter converter;

    @Override
    public GroupDTO getById(long id) throws EntityNotFoundException {

        Group group = groupRepository.findById(id).orElse(null);
        if (group != null){
            return converter.toDTO(group);
        }
        else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public GroupDTO getGroupByNumber(long groupNumber) throws EntityNotFoundException {
        Group group = groupRepository.findByGroupNumber((groupNumber));
        if (group != null){
            return converter.toDTO(group);
        }
        else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public GroupDTO add(GroupDTO entity) {
        Group savedGroup = groupRepository.save(converter.toEntity(entity));
        return converter.toDTO(savedGroup);
    }


    @Override
    public void delete(long id) throws EntityNotFoundException {
        if(groupRepository.existsById(id)){
            groupRepository.deleteById(id);
        }else{
            throw new EntityNotFoundException();
        }
    }

    @Override
    public List<GroupDTO> getAll() {
        List<GroupDTO> groupDTOS = new ArrayList<>();
        StreamSupport.stream(groupRepository.findAll().spliterator(), false)
                .forEach(group -> groupDTOS.add(converter.toDTO(group)));
        return groupDTOS;

     }

    @Override
    public GroupDTO update(GroupDTO entity) throws EntityNotFoundException {
        if (groupRepository.existsById(entity.getId())){
            Group updatedGroup = groupRepository.save(converter.toEntity(entity));
            return converter.toDTO(updatedGroup);
        } else {
            throw new EntityNotFoundException();
        }
    }

}
