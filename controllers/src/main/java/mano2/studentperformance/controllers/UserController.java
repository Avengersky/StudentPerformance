package mano2.studentperformance.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import mano2.studentperformance.dto.UserDTO;
import mano2.studentperformance.entity.User;
import mano2.studentperformance.exceptions.EntityNotFoundException;
import mano2.studentperformance.exceptions.ExistingLoginException;
import mano2.studentperformance.impl.UserServiceImpl;
import mano2.studentperformance.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@Api
@RestController
@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl service;

    @ApiOperation(value = "Gets all users", nickname = "UserController.getAllUsers")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Users")})
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<UserDTO>> getAllUsers() {
        Iterable<UserDTO> userDTOS = service.getAll();
        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }

    @ApiOperation(value = "Gets specific user", nickname = "UserController.getUser")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "User")})
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") String id) throws EntityNotFoundException {
        UserDTO userDTO = service.getById(Long.valueOf(id));
        return new ResponseEntity<>(userDTO, HttpStatus.OK);

    }
    @ApiOperation(value = "Creates user", nickname = "UserController.createUser")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "User is created")})
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) throws ExistingLoginException {
        UserDTO savedUser = service.addUser(userDTO);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Deletes user", nickname = "UserController.deleteUser")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "User is deleted")})
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteUser(@PathVariable("id") String id) throws EntityNotFoundException {
        service.delete(Long.valueOf(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @ApiOperation(value = "Gets user page", nickname = "UserController.getPage")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "User page")})
    @GetMapping(value = "/page", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<User> getPage(@PageableDefault( sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return service.getUserPage(pageable);
    }

    @ApiOperation(value = "Updates user", nickname = "UserController.updateUser")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "User is updated")})
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) throws EntityNotFoundException {
        UserDTO updatedUser = service.update(userDTO);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
}
