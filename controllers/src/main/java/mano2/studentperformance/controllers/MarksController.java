package mano2.studentperformance.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import mano2.studentperformance.dto.MarkDTO;
import mano2.studentperformance.exceptions.EntityNotFoundException;
import mano2.studentperformance.interfaces.MarksService;
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
@RequestMapping("/marks")
public class MarksController {

    @Autowired
    private MarksService service;

    @ApiOperation(value = "Gets all marks", nickname = "MarksController.getAllMarks")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Marks")})
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<MarkDTO>> getAllMarks() {
        Iterable<MarkDTO> marks = service.getAll();
        return new ResponseEntity<>(marks, HttpStatus.OK);
    }

    @ApiOperation(value = "Gets specific mark", nickname = "MarksController.getMark")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Mark")})
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MarkDTO> getMark(@PathVariable("id") String id) throws EntityNotFoundException {
        MarkDTO markDTO = service.getById(Long.valueOf(id));
        return new ResponseEntity<>(markDTO, HttpStatus.OK);

    }
    @ApiOperation(value = "Creates mark", nickname = "MarksController.createMark")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Mark is created")})
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER', 'DEPARTMENT', 'FACULTY')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MarkDTO> createMark(@RequestBody @Valid MarkDTO markDTO) {
        MarkDTO savedMark = service.add(markDTO);
        return new ResponseEntity<>(savedMark, HttpStatus.CREATED);

    }


    @ApiOperation(value = "Deletes mark", nickname = "MarksController.deleteMark")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Mark is deleted")})
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER', 'DEPARTMENT', 'FACULTY')")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteMark(@PathVariable("id") String id) throws EntityNotFoundException {
        service.delete(Long.valueOf(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Updates mark", nickname = "MarkController.updateMark")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Mark is updated")})
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER', 'DEPARTMENT', 'FACULTY')")
    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MarkDTO> updateMark(@RequestBody MarkDTO markDTO) throws EntityNotFoundException {
        MarkDTO updatedMark = service.update(markDTO);
        return new ResponseEntity<>(updatedMark, HttpStatus.OK);
    }

}
