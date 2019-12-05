package mano2.studentperformance.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import mano2.studentperformance.dto.StudentDTO;
import mano2.studentperformance.exceptions.EntityNotFoundException;
import mano2.studentperformance.exceptions.ExistingLoginException;
import mano2.studentperformance.interfaces.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api
@RestController
@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService service;

    @ApiOperation(value = "Gets all students", nickname = "StudentController.getAllStudents")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Students")})
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<StudentDTO>> getAllStudents() {
        Iterable<StudentDTO> students = service.getAll();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @ApiOperation(value = "Gets specific student", nickname = "StudentController.getStudent")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Student")})
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentDTO> getStudent(@PathVariable("id") String id) throws EntityNotFoundException {
        StudentDTO studentDTO = service.getById(Long.valueOf(id));
        return new ResponseEntity<>(studentDTO, HttpStatus.OK);

    }
    @ApiOperation(value = "Creates student", nickname = "StudentController.createStudent")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Student is created")})
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER', 'DEPARTMENT', 'FACULTY')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) throws ExistingLoginException {
        StudentDTO savedStudent = service.addStudent(studentDTO);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);

    }

    @ApiOperation(value = "Deletes student", nickname = "StudentController.deleteStudent")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Student is deleted")})
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER', 'DEPARTMENT', 'FACULTY')")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteStudent(@PathVariable("id") String id) throws EntityNotFoundException {
        service.delete(Long.valueOf(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Updates student", nickname = "StudentController.updateStudent")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Student is updated")})
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER', 'DEPARTMENT', 'FACULTY')")
    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO studentDTO) throws EntityNotFoundException {
        StudentDTO updatedStudent = service.update(studentDTO);
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }




}
