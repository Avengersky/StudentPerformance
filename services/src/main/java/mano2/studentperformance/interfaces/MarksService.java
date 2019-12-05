package mano2.studentperformance.interfaces;


import mano2.studentperformance.dto.MarkDTO;
import mano2.studentperformance.exceptions.EntityNotFoundException;

import java.util.List;

public interface MarksService extends Service<MarkDTO> {

    List<MarkDTO> getAllMarksByStudentId(long examBookId) throws EntityNotFoundException;

    //AverageMarkDTO getAverageMarkByExamBookId(long examBookId) throws EntityNotFoundException;

    //AverageMarkDTO getAverageMarkByExamBookIdAndSubjectId(AverageMarkBySubjectDTO average) throws EntityNotFoundException;

}
