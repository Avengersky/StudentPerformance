package mano2.studentperformance.controllers;


import io.swagger.annotations.Api;
import mano2.studentperformance.dto.GroupDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import mano2.studentperformance.exceptions.EntityNotFoundException;
import mano2.studentperformance.interfaces.GroupService;
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
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @ApiOperation(value = "Gets all groups", nickname = "GroupController.getAllGroups")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Groups")})
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<GroupDTO>> getAllGroupse() {
        Iterable<GroupDTO> groups = groupService.getAll();
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    @ApiOperation(value = "Gets specific group", nickname = "GroupController.getGroup")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Group")})
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GroupDTO> getGroup(@PathVariable("id") String id) throws EntityNotFoundException {
        GroupDTO groupDTO = groupService.getById(Long.valueOf(id));
        return new ResponseEntity<>(groupDTO, HttpStatus.OK);

    }
    @ApiOperation(value = "Creates group", nickname = "GroupController.createGroup")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Group is created")})
    @PreAuthorize("hasAnyRole('ADMIN', ' DEPARTMENT', 'FACULTY')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GroupDTO> createGroup(@RequestBody GroupDTO groupDTO) {
        GroupDTO savedGroup = groupService.add(groupDTO);
        return new ResponseEntity<>(savedGroup, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Deletes group", nickname = "GroupController.deleteGroup")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Group is deleted")})
    @PreAuthorize("hasAnyRole('ADMIN', 'DEPARTMENT', 'FACULTY')")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteGroup(@PathVariable("id") String id) throws EntityNotFoundException {
        groupService.delete(Long.valueOf(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Gets group by number", nickname = "GroupController.getGroupByNumber")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Group by number")})
    @GetMapping(value = "/number/{number}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GroupDTO> getGroupByNumber(@PathVariable("number") String number) throws EntityNotFoundException {
        GroupDTO groupDTO = groupService.getGroupByNumber(Long.valueOf(number));
        return new ResponseEntity<>(groupDTO, HttpStatus.OK);
    }

    @ApiOperation(value = "Updates group", nickname = "GroupController.updateGroup")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Group is updated")})
    @PreAuthorize("hasAnyRole('ADMIN', 'DEPARTMENT', 'FACULTY')")
    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GroupDTO> updateGroup(@RequestBody GroupDTO groupDTO) throws EntityNotFoundException {
        GroupDTO updatedGroup = groupService.update(groupDTO);
        return new ResponseEntity<>(updatedGroup, HttpStatus.OK);
    }



}


