package mano2.studentperformance.impl;

import mano2.studentperformance.converters.FacultyConverter;
import mano2.studentperformance.dto.FacultyDTO;
import mano2.studentperformance.entity.Faculty;
import mano2.studentperformance.entity.User;
import mano2.studentperformance.exceptions.EntityNotFoundException;
import mano2.studentperformance.interfaces.FacultyService;
import mano2.studentperformance.repositories.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;


@Service("facultyService")
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    private FacultyConverter converter;

    @Autowired
    private FacultyRepository repository;

    @Override
    public FacultyDTO getById(long id) throws EntityNotFoundException {
        Faculty faculty = repository.findById(id).orElse(null);
        if (faculty != null){
            return converter.toDTO(faculty);
        }
        else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public FacultyDTO add(FacultyDTO entity) {
        Faculty savedFaculty = repository.save(converter.toEntity(entity));
        return converter.toDTO(savedFaculty);
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
    public List<FacultyDTO> getAll() {
        List<FacultyDTO> facultyDTOS = new ArrayList<>();
        StreamSupport.stream(repository.findAll().spliterator(), false)
                .forEach(faculty ->facultyDTOS.add(converter.toDTO(faculty)));
        return facultyDTOS;
    }

    @Override
    public FacultyDTO update(FacultyDTO entity) throws EntityNotFoundException {
        if (repository.existsById(entity.getId())){
            Faculty updatedFaculty = repository.save(converter.toEntity(entity));
            return converter.toDTO(updatedFaculty);
        } else {
            throw new EntityNotFoundException();
        }
    }
}
