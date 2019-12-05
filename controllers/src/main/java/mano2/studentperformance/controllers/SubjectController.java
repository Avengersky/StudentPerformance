package mano2.studentperformance.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import mano2.studentperformance.dto.SubjectDTO;
import mano2.studentperformance.exceptions.EntityNotFoundException;
import mano2.studentperformance.interfaces.SubjectService;
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
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private SubjectService service;

    @ApiOperation(value = "Gets all subject", nickname = "SubjectController.getAllSubjects")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Subjects")})
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<SubjectDTO>> getAllSubjects() {
        Iterable<SubjectDTO> subjects = service.getAll();
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }

    @ApiOperation(value = "Gets specific subject", nickname = "SubjectController.getSubject")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Subject")})
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubjectDTO> getSubject(@PathVariable("id") String id) throws EntityNotFoundException {
        SubjectDTO subjectDTO = service.getById(Long.valueOf(id));
        return new ResponseEntity<>(subjectDTO, HttpStatus.OK);
    }


    @ApiOperation(value = "Creates subject", nickname = "SubjectController.createSubject")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Subject is created")})
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER', 'DEPARTMENT', 'FACULTY')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubjectDTO> createSubject(@RequestBody @Valid SubjectDTO subjectDTO) {
        SubjectDTO savedSubject = service.add(subjectDTO);
        return new ResponseEntity<>(savedSubject, HttpStatus.CREATED);

    }

    @ApiOperation(value = "Deletes subject", nickname = "SubjectController.deleteSubject")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Subject is deleted")})
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER', 'DEPARTMENT', 'FACULTY')")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteSubject(@PathVariable("id") String id) throws EntityNotFoundException {
        service.delete(Long.valueOf(id));
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @ApiOperation(value = "Updates subject", nickname = "SubjectController.updateSubject")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Subject is updated")})
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER', 'DEPARTMENT', 'FACULTY')")
    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubjectDTO> updateSubject(@RequestBody SubjectDTO subjectDTO) throws EntityNotFoundException {
        SubjectDTO updatedSubject = service.update(subjectDTO);
        return new ResponseEntity<>(updatedSubject, HttpStatus.OK);
    }




}
