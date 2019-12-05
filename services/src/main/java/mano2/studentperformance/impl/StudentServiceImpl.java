package mano2.studentperformance.impl;


import mano2.studentperformance.converters.StudentConverter;
import mano2.studentperformance.dto.StudentDTO;
import mano2.studentperformance.entity.Student;
import mano2.studentperformance.entity.User;
import mano2.studentperformance.exceptions.EntityNotFoundException;
import mano2.studentperformance.exceptions.ExistingLoginException;
import mano2.studentperformance.interfaces.StudentService;

import mano2.studentperformance.repositories.StudentsRepository;

import mano2.studentperformance.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;


@Service("studentService")
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentsRepository studentsRepository;

    @Autowired
    private StudentConverter converter;

    @Autowired
    private UserRepository userRepository;

    @Bean
    private PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    public StudentDTO getById(long id) throws EntityNotFoundException {

        Student student =  studentsRepository.findById(id).orElse(null);
        if(student != null){
            return  converter.toDTO(student);
        } else {
            throw new EntityNotFoundException();
        }
    }




    @Override
    public void delete(long id) throws EntityNotFoundException {
        if(studentsRepository.existsById(id)) {
            studentsRepository.deleteById(id);
        } else{
            throw new EntityNotFoundException();
        }
    }

    @Override
    public List<StudentDTO> getAll() {
        List<StudentDTO> studentDTOS = new ArrayList<>();
        StreamSupport.stream(studentsRepository.findAll().spliterator(), false)
                .forEach(student -> studentDTOS.add(converter.toDTO(student)));
        return studentDTOS;
    }

    @Override
    public StudentDTO update(StudentDTO entity) throws EntityNotFoundException {
        if (studentsRepository.existsById(entity.getId())){
            Student updatedStudent = studentsRepository.save(converter.toEntity(entity));
            return converter.toDTO(updatedStudent);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public StudentDTO addStudent(StudentDTO entity) throws ExistingLoginException {
        if (!userRepository.existsByLogin(entity.getUserDTO().getLogin()) &&
                !userRepository.existsById(entity.getUserDTO().getId())) {
            entity.getUserDTO().setPassword(encoder().encode(entity.getUserDTO().getPassword()));
            Student savedStudent = studentsRepository.save(converter.toEntity(entity));
            return converter.toDTO(savedStudent);

        }
        else {
            throw new ExistingLoginException();
        }
    }

    @Override
    public StudentDTO add(StudentDTO entity) {
        return null;
    }
}
