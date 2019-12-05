package mano2.studentperformance.impl;

import mano2.studentperformance.converters.SubgroupConverter;
import mano2.studentperformance.dto.SubgroupDTO;
import mano2.studentperformance.entity.Subgroup;
import mano2.studentperformance.exceptions.EntityNotFoundException;
import mano2.studentperformance.interfaces.SubgroupService;
import mano2.studentperformance.repositories.SubgroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

@Service("subgroupService")
public class SubgroupServiceImpl implements SubgroupService {

    @Autowired
    private SubgroupConverter converter;

    @Autowired
    private SubgroupRepository repository;

    @Override
    public SubgroupDTO getById(long id) throws EntityNotFoundException {
        Subgroup subgroup = repository.findById(id).orElse(null);
        if (subgroup != null){
            return converter.toDTO(subgroup);
        }
        else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public SubgroupDTO add(SubgroupDTO entity) {
        Subgroup savedSubgroup = repository.save(converter.toEntity(entity));
        return converter.toDTO(savedSubgroup);
    }


    @Override
    public void delete(long id) throws EntityNotFoundException {
        if(repository.existsById(id)){
            repository.deleteById(id);
        }else{
            throw new EntityNotFoundException();
        }
    }

    @Override
    public List<SubgroupDTO> getAll() {
        List<SubgroupDTO> subgroupDTOS = new ArrayList<>();
        StreamSupport.stream(repository.findAll().spliterator(), false)
                .forEach(subgroup ->subgroupDTOS.add(converter.toDTO(subgroup)));
        return subgroupDTOS;
    }

    @Override
    public SubgroupDTO update(SubgroupDTO entity) throws EntityNotFoundException {
        if (repository.existsById(entity.getId())){
            Subgroup updatedSubgroup = repository.save(converter.toEntity(entity));
            return converter.toDTO(updatedSubgroup);
        } else {
            throw new EntityNotFoundException();
        }
    }
}
