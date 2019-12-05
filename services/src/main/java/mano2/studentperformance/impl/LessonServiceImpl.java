package mano2.studentperformance.impl;

import mano2.studentperformance.converters.LessonConverter;
import mano2.studentperformance.dto.LessonDTO;
import mano2.studentperformance.entity.Faculty;
import mano2.studentperformance.entity.Lesson;
import mano2.studentperformance.entity.User;
import mano2.studentperformance.exceptions.EntityNotFoundException;
import mano2.studentperformance.interfaces.LessonService;
import mano2.studentperformance.repositories.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

@Service("lessonService")
public class LessonServiceImpl implements LessonService {

    @Autowired
    private LessonConverter converter;

    @Autowired
    private LessonRepository repository;

    @Override
    public LessonDTO getById(long id) throws EntityNotFoundException {
        Lesson lesson = repository.findById(id).orElse(null);
        if (lesson != null){
            return converter.toDTO(lesson);
        }
        else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public LessonDTO add(LessonDTO entity) {
        Lesson savedLesson = repository.save(converter.toEntity(entity));
        return converter.toDTO(savedLesson);
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
    public List<LessonDTO> getAll() {
        List<LessonDTO> lessonDTOS = new ArrayList<>();
        StreamSupport.stream(repository.findAll().spliterator(), false)
                .forEach(lesson ->lessonDTOS.add(converter.toDTO(lesson)));
        return lessonDTOS;
    }

    @Override
    public LessonDTO update(LessonDTO entity) throws EntityNotFoundException {
        if (repository.existsById(entity.getId())){
            Lesson updatedLesson = repository.save(converter.toEntity(entity));
            return converter.toDTO(updatedLesson);
        } else {
            throw new EntityNotFoundException();
        }
    }
}
