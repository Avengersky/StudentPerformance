package mano2.studentperformance.impl;

import mano2.studentperformance.converters.TeacherConverter;
import mano2.studentperformance.converters.UserConverter;
import mano2.studentperformance.dto.TeacherDTO;
import mano2.studentperformance.entity.Teacher;
import mano2.studentperformance.exceptions.EntityNotFoundException;
import mano2.studentperformance.exceptions.ExistingLoginException;
import mano2.studentperformance.interfaces.TeacherService;
import mano2.studentperformance.repositories.TeacherRepository;
import mano2.studentperformance.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {


    @Autowired
    private TeacherRepository repository;

    @Autowired
    private TeacherConverter converter;

    @Bean
    private PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private UserRepository userRepository;

    @Override
    public TeacherDTO getById(long id) throws EntityNotFoundException {
        Teacher teacher =  repository.findById(id).orElse(null);
        if(teacher != null){
            return  converter.toDTO(teacher);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public TeacherDTO add(TeacherDTO entity) {
        return null;
    }


    @Override
    public void delete(long id) throws EntityNotFoundException {
        if(repository.existsById(id)) {
            repository.deleteById(id);
        } else{
            throw new EntityNotFoundException();
        }
    }

    @Override
    public List<TeacherDTO> getAll() {
        List<TeacherDTO> teacherDTOS = new ArrayList<>();
        repository.findAll().forEach(subject -> teacherDTOS.add(converter.toDTO(subject)));
        return teacherDTOS;
    }

    @Override
    public TeacherDTO update(TeacherDTO entity) throws EntityNotFoundException {
        if (repository.existsById(entity.getId())){
            Teacher updatedTeacher = repository.save(converter.toEntity(entity));
            return converter.toDTO(updatedTeacher);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public TeacherDTO addTeacher(TeacherDTO entity) throws ExistingLoginException {
        if (!userRepository.existsByLogin(entity.getUserDTO().getLogin())
                && !userRepository.existsById(entity.getUserDTO().getId())) {
            entity.getUserDTO().setPassword(encoder().encode(entity.getUserDTO().getPassword()));
            Teacher savedTeacher = repository.save(converter.toEntity(entity));
            return converter.toDTO(savedTeacher);

        }
        else {
            throw new ExistingLoginException();
        }
    }
}
