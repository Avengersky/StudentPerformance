package mano2.studentperformance.impl;

import mano2.studentperformance.converters.MarkConverter;
import mano2.studentperformance.dto.MarkDTO;
import mano2.studentperformance.entity.Mark;
import mano2.studentperformance.exceptions.EntityNotFoundException;
import mano2.studentperformance.interfaces.MarksService;
import mano2.studentperformance.repositories.MarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("markService")
public class MarksServiceImpl implements MarksService {

    @Autowired
    private MarkRepository marksRepository;

    @Autowired
    private MarkConverter converter;

   @Override
    public MarkDTO getById(long id) throws EntityNotFoundException {

       Mark mark =  marksRepository.findById(id).orElse(null);
       if(mark != null){
           return  converter.toDTO(mark);
       } else {
         throw new EntityNotFoundException();
       }
    }

    @Override
    public MarkDTO add(MarkDTO entity) {
        Mark savedMark = marksRepository.save(converter.toEntity(entity));
        return converter.toDTO(savedMark);

    }


    @Override
    public void delete(long id) throws EntityNotFoundException {
       if(marksRepository.existsById(id)) {
           marksRepository.deleteById(id);
       } else{
           throw new EntityNotFoundException();
       }
    }

    @Override
    public List<MarkDTO> getAll() {
       List<MarkDTO> markDTOS = new ArrayList<>();
       marksRepository.findAll().forEach(mark -> markDTOS.add(converter.toDTO(mark)));
       return markDTOS;
    }

    @Override
    public MarkDTO update(MarkDTO entity) throws EntityNotFoundException {
        if (marksRepository.existsById(entity.getId())){
            Mark updatedMark = marksRepository.save(converter.toEntity(entity));
            return converter.toDTO(updatedMark);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public List<MarkDTO> getAllMarksByStudentId(long studentId) throws EntityNotFoundException {
        List<MarkDTO> markDTOS = new ArrayList<>();
        marksRepository.findAllByStudentId(studentId).forEach(mark -> markDTOS.add(converter.toDTO(mark)));
        if(markDTOS.size() == 0){
            throw new EntityNotFoundException();
        }
        else {
            return markDTOS;
        }
    }

//    @Override
//    public AverageMarkDTO getAverageMarkByExamBookId(long examBookId) throws EntityNotFoundException {
//        List<Mark> marks = marksRepository.findAllByExamBookId(examBookId);
//        AverageMarkDTO averageMarkDTO = new AverageMarkDTO();
//        if(marks.size() == 0){
//            throw new EntityNotFoundException();
//        }
//        else {
//            int sum = marks.stream().mapToInt(Mark::getMarkValue).sum();
//            averageMarkDTO.setAverageMark(Precision.round((double)sum / marks.size(),1));
//            return averageMarkDTO;
//        }
//    }

//    @Override
//    public AverageMarkDTO getAverageMarkByExamBookIdAndSubjectId(AverageMarkBySubjectDTO average) throws EntityNotFoundException {
//        List<Mark> marks = marksRepository.findAllByExamBookIdAndSubjectId(average.getExamBookId(), average.getSubjectId());
//        AverageMarkDTO averageMarkDTO = new AverageMarkDTO();
//        if(marks.size() != 0){
//            int sum = marks.stream().mapToInt(Mark::getMarkValue).sum();
//            averageMarkDTO.setAverageMark(Precision.round((double)sum / marks.size(),1));
//            return averageMarkDTO;
//        }
//        else {
//            throw new EntityNotFoundException();
//
//        }
//    }


}
