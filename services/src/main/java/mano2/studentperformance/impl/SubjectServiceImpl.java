package mano2.studentperformance.impl;

import mano2.studentperformance.converters.SubjectConverter;
import mano2.studentperformance.dto.SubjectDTO;
import mano2.studentperformance.entity.Subject;
import mano2.studentperformance.exceptions.EntityNotFoundException;
import mano2.studentperformance.interfaces.SubjectService;
import mano2.studentperformance.repositories.SubjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("subjectService")
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectsRepository subjectsRepository;

    @Autowired
    private SubjectConverter converter;

    @Override
    public SubjectDTO getById(long id) throws EntityNotFoundException {
        Subject subject =  subjectsRepository.findById(id).orElse(null);
        if(subject != null){
            return  converter.toDTO(subject);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public SubjectDTO add(SubjectDTO entity) {
        Subject savedSubject = subjectsRepository.save(converter.toEntity(entity));
        return converter.toDTO(savedSubject);
    }


    @Override
    public void delete(long id) throws EntityNotFoundException {
        if(subjectsRepository.existsById(id)) {
            subjectsRepository.deleteById(id);
        } else{
            throw new EntityNotFoundException();
        }
    }

    @Override
    public List<SubjectDTO> getAll() {
        List<SubjectDTO> subjectDTOS = new ArrayList<>();
        subjectsRepository.findAll().forEach(subject -> subjectDTOS.add(converter.toDTO(subject)));
        return subjectDTOS;
    }

    @Override
    public SubjectDTO update(SubjectDTO entity) throws EntityNotFoundException {
        if (subjectsRepository.existsById(entity.getId())){
            Subject updatedSubject = subjectsRepository.save(converter.toEntity(entity));
            return converter.toDTO(updatedSubject);
        } else {
            throw new EntityNotFoundException();
        }
    }
}
