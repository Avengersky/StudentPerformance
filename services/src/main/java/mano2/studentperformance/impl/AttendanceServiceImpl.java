package mano2.studentperformance.impl;

import mano2.studentperformance.converters.AttendanceConverter;
import mano2.studentperformance.dto.AttendanceDTO;
import mano2.studentperformance.entity.Attendance;
import mano2.studentperformance.entity.User;
import mano2.studentperformance.exceptions.EntityNotFoundException;
import mano2.studentperformance.interfaces.AttendanceService;
import mano2.studentperformance.repositories.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;


@Service("attendanceService")
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceConverter converter;

    @Autowired
    private AttendanceRepository repository;

    @Override
    public AttendanceDTO getById(long id) throws EntityNotFoundException {
        Attendance attendance = repository.findById(id).orElse(null);
        if (attendance != null){
            return converter.toDTO(attendance);
        }
        else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public AttendanceDTO add(AttendanceDTO entity) {
        Attendance savedAttendance = repository.save(converter.toEntity(entity));
        return converter.toDTO(savedAttendance);
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
    public List<AttendanceDTO> getAll() {
        List<AttendanceDTO> attendanceDTOS = new ArrayList<>();
        StreamSupport.stream(repository.findAll().spliterator(), false)
                .forEach(attendance -> attendanceDTOS.add(converter.toDTO(attendance)));
        return attendanceDTOS;

    }

    @Override
    public AttendanceDTO update(AttendanceDTO entity) throws EntityNotFoundException {
        if (repository.existsById(entity.getId())){
            Attendance updatedAttendance = repository.save(converter.toEntity(entity));
            return converter.toDTO(updatedAttendance);
        } else {
            throw new EntityNotFoundException();
        }
    }
}
