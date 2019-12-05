package mano2.studentperformance.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import mano2.studentperformance.dto.LessonDTO;
import mano2.studentperformance.exceptions.EntityNotFoundException;
import mano2.studentperformance.interfaces.LessonService;
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
@RequestMapping("/lessons")
public class LessonController {

    @Autowired
    private LessonService service;

    @ApiOperation(value = "Gets all lessons", nickname = "LessonController.getAllLessons")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Lessons")})
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<LessonDTO>> getAllLessons() {
        Iterable<LessonDTO> lessonDTOS = service.getAll();
        return new ResponseEntity<>(lessonDTOS, HttpStatus.OK);
    }

    @ApiOperation(value = "Gets specific lesson", nickname = "LessonController.getLesson")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Lesson")})
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LessonDTO> getLesson(@PathVariable("id") String id) throws EntityNotFoundException {
        LessonDTO lessonDTO = service.getById(Long.valueOf(id));
        return new ResponseEntity<>(lessonDTO, HttpStatus.OK);

    }
    @ApiOperation(value = "Creates lesson", nickname = "LessonController.createLesson")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Lesson is created")})
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER', 'DEPARTMENT', 'FACULTY')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LessonDTO> createLesson(@RequestBody LessonDTO lessonDTO) {
        LessonDTO savedLesson = service.add(lessonDTO);
        return new ResponseEntity<>(savedLesson, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Deletes lesson", nickname = "LessonController.deleteLesson")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Lesson is deleted")})
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER', 'DEPARTMENT', 'FACULTY')")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteLesson(@PathVariable("id") String id) throws EntityNotFoundException {
        service.delete(Long.valueOf(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Updates lesson", nickname = "LessonController.updateLesson")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Lesson is updated")})
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER', 'DEPARTMENT', 'FACULTY')")
    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LessonDTO> updateLesson(@RequestBody LessonDTO lessonDTO) throws EntityNotFoundException {
        LessonDTO updatedLesson = service.update(lessonDTO);
        return new ResponseEntity<>(updatedLesson, HttpStatus.OK);
    }

}
