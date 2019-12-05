package mano2.studentperformance.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import mano2.studentperformance.dto.AttendanceDTO;
import mano2.studentperformance.exceptions.EntityNotFoundException;
import mano2.studentperformance.interfaces.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api
@RestController
@Controller
@RequestMapping("/attendances")
public class AttendanceController {

    @Autowired
    private AttendanceService service;

    @ApiOperation(value = "Gets all attendances", nickname = "AttendanceController.getAllAttendances")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Attendances")})
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER', ' DEPARTMENT', 'FACULTY')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<AttendanceDTO>> getAllAttendances() {
        Iterable<AttendanceDTO> attendanceDTOS = service.getAll();
        return new ResponseEntity<>(attendanceDTOS, HttpStatus.OK);
    }

    @ApiOperation(value = "Gets specific attendance", nickname = "AttendanceController.getAttendance")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Attendance")})
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AttendanceDTO> getAttendance(@PathVariable("id") String id) throws EntityNotFoundException {
        AttendanceDTO attendanceDTO = service.getById(Long.valueOf(id));
        return new ResponseEntity<>(attendanceDTO, HttpStatus.OK);

    }
    @ApiOperation(value = "Creates attendance", nickname = "AttendanceController.createAttendance")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Attendance is created")})
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER', ' DEPARTMENT', 'FACULTY')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AttendanceDTO> createAttendance(@RequestBody AttendanceDTO attendanceDTO) {
        AttendanceDTO savedAttendance = service.add(attendanceDTO);
        return new ResponseEntity<>(savedAttendance, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Deletes attendance", nickname = "AttendanceController.deleteAttendance")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Attendance is deleted")})
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER', ' DEPARTMENT', 'FACULTY')")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteAttendance(@PathVariable("id") String id) throws EntityNotFoundException {
        service.delete(Long.valueOf(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Updates attendance", nickname = "AttendanceController.updateAttendance")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Attendance is updated")})
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER', ' DEPARTMENT', 'FACULTY')")
    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AttendanceDTO> updateAttendance(@RequestBody AttendanceDTO attendanceDTO) throws EntityNotFoundException {
        AttendanceDTO updatedAttendance = service.update(attendanceDTO);
        return new ResponseEntity<>(updatedAttendance, HttpStatus.OK);
    }


}
