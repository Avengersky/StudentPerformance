package mano2.studentperformance.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import mano2.studentperformance.dto.SubgroupDTO;
import mano2.studentperformance.exceptions.EntityNotFoundException;
import mano2.studentperformance.interfaces.SubgroupService;
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
@RequestMapping("/subgroups")
public class SubgroupController {

    @Autowired
    private SubgroupService service;

    @ApiOperation(value = "Gets all subgroups", nickname = "SubgroupController.getAllSubgroups")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Subgroups")})
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<SubgroupDTO>> getAllSubgroups() {
        Iterable<SubgroupDTO> subgroupDTOS = service.getAll();
        return new ResponseEntity<>(subgroupDTOS, HttpStatus.OK);
    }

    @ApiOperation(value = "Gets specific subgroup", nickname = "SubgroupController.getSubgroup")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Subgroup")})
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubgroupDTO> getSubgroup(@PathVariable("id") String id) throws EntityNotFoundException {
        SubgroupDTO subgroupDTO = service.getById(Long.valueOf(id));
        return new ResponseEntity<>(subgroupDTO, HttpStatus.OK);

    }
    @ApiOperation(value = "Creates subgroup", nickname = "SubgroupController.createSubgroup")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Subgroup is created")})
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER', 'DEPARTMENT', 'FACULTY')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubgroupDTO> createSubgroup(@RequestBody SubgroupDTO subgroupDTO) {
        SubgroupDTO savedSubgroup = service.add(subgroupDTO);
        return new ResponseEntity<>(savedSubgroup, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Deletes subgroup", nickname = "SubgroupController.deleteSubgroup")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Subgroup is deleted")})
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER', 'DEPARTMENT', 'FACULTY')")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteSubgroup(@PathVariable("id") String id) throws EntityNotFoundException {
        service.delete(Long.valueOf(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Updates subgroup", nickname = "SubgroupController.updateSubgroup")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Subgroup is updated")})
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER', 'DEPARTMENT', 'FACULTY')")
    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubgroupDTO> updateSubgroup(@RequestBody SubgroupDTO subgroupDTO) throws EntityNotFoundException {
        SubgroupDTO updatedSubgroup = service.update(subgroupDTO);
        return new ResponseEntity<>(updatedSubgroup, HttpStatus.OK);
    }




}
