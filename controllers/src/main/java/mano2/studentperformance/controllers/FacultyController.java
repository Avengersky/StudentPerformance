package mano2.studentperformance.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import mano2.studentperformance.dto.FacultyDTO;
import mano2.studentperformance.entity.Faculty;
import mano2.studentperformance.exceptions.EntityNotFoundException;
import mano2.studentperformance.interfaces.FacultyService;
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
@RequestMapping("/faculties")
public class FacultyController {

    @Autowired
    private FacultyService service;

    @ApiOperation(value = "Gets all faculties", nickname = "FacultyController.getAllFaculties")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Faculties")})
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<FacultyDTO>> getAllFaculties() {
        Iterable<FacultyDTO> facultyDTOS = service.getAll();
        return new ResponseEntity<>(facultyDTOS, HttpStatus.OK);
    }

    @ApiOperation(value = "Gets specific faculty", nickname = "FacultyController.getFaculty")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Faculty")})
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FacultyDTO> getFaculty(@PathVariable("id") String id) throws EntityNotFoundException {
        FacultyDTO facultyDTO = service.getById(Long.valueOf(id));
        return new ResponseEntity<>(facultyDTO, HttpStatus.OK);

    }
    @ApiOperation(value = "Creates faculty", nickname = "FacultyController.createFaculty")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Faculty is created")})
    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FacultyDTO> createFaculty(@RequestBody FacultyDTO facultyDTO) {
        FacultyDTO savedFaculty = service.add(facultyDTO);
        return new ResponseEntity<>(savedFaculty, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Deletes faculty", nickname = "FacultyController.deleteFaculty")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Faculty is deleted")})
    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY')")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteFaculty(@PathVariable("id") String id) throws EntityNotFoundException {
        service.delete(Long.valueOf(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Updates faculty", nickname = "FacultyController.updateFaculty")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Faculty is updated")})
    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY')")
    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FacultyDTO> updateFaculty(@RequestBody FacultyDTO facultyDTO) throws EntityNotFoundException {
        FacultyDTO updatedFaculty = service.update(facultyDTO);
        return new ResponseEntity<>(updatedFaculty, HttpStatus.OK);
    }


}
