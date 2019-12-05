package mano2.studentperformance.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import mano2.studentperformance.dto.RoleDTO;
import mano2.studentperformance.dto.UserDTO;
import mano2.studentperformance.exceptions.EntityNotFoundException;
import mano2.studentperformance.impl.RoleServiceImpl;
import mano2.studentperformance.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
@Controller
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleServiceImpl service;

    @ApiOperation(value = "Gets all roles", nickname = "RoleController.getAllRoles")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Roles")})
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<RoleDTO>> getAllRoles() {
        Iterable<RoleDTO> roleDTOS = service.getAll();
        return new ResponseEntity<>(roleDTOS, HttpStatus.OK);
    }

    @ApiOperation(value = "Gets specific role", nickname = "RoleController.getRole")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Role")})
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoleDTO> getRole(@PathVariable("id") String id) throws EntityNotFoundException {
        RoleDTO roleDTO = service.getById(Long.valueOf(id));
        return new ResponseEntity<>(roleDTO, HttpStatus.OK);
    }
}