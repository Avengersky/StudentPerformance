package mano2.studentperformance.controllers;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import mano2.studentperformance.dto.TeacherDTO;
import mano2.studentperformance.entity.Teacher;
import mano2.studentperformance.exceptions.EntityNotFoundException;
import mano2.studentperformance.exceptions.ExistingLoginException;
import mano2.studentperformance.interfaces.TeacherService;
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
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherService service;

    @ApiOperation(value = "Gets all teachers", nickname = "TeacherController.getAllTeachers")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Teachers")})
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<TeacherDTO>> getAllTeachers() {
        Iterable<TeacherDTO> teacherDTOS = service.getAll();
        return new ResponseEntity<>(teacherDTOS, HttpStatus.OK);
    }

    @ApiOperation(value = "Gets specific teacher", nickname = "TeacherController.getTeacher")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Teacher")})
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TeacherDTO> getTeacher(@PathVariable("id") String id) throws EntityNotFoundException {
        TeacherDTO teacherDTO = service.getById(Long.valueOf(id));
        return new ResponseEntity<>(teacherDTO, HttpStatus.OK);

    }
    @ApiOperation(value = "Creates teacher", nickname = "TeacherController.createTeacher")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Teacher is created")})
    @PreAuthorize("hasAnyRole('ADMIN', 'DEPARTMENT', 'FACULTY')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TeacherDTO> createTeacher(@RequestBody TeacherDTO teacherDTO) throws ExistingLoginException {
        TeacherDTO savedTeacher = service.addTeacher(teacherDTO);
        return new ResponseEntity<>(savedTeacher, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Deletes teacher", nickname = "TeacherController.deleteTeacher")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Teacher is deleted")})
    @PreAuthorize("hasAnyRole('ADMIN', 'DEPARTMENT', 'FACULTY')")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteTeacher(@PathVariable("id") String id) throws EntityNotFoundException {
        service.delete(Long.valueOf(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Updates teacher", nickname = "TeacherController.updateTeacher")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Teacher is updated")})
    @PreAuthorize("hasAnyRole('ADMIN', 'DEPARTMENT', 'FACULTY')")
    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TeacherDTO> updateTeacher(@RequestBody TeacherDTO teacherDTO) throws EntityNotFoundException {
        TeacherDTO updatedTeacher = service.update(teacherDTO);
        return new ResponseEntity<>(updatedTeacher, HttpStatus.OK);
    }

}
