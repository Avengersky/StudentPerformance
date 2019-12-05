package mano2.studentperformance.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import mano2.studentperformance.dto.AccountDTO;
import mano2.studentperformance.entity.Account;
import mano2.studentperformance.exceptions.EntityNotFoundException;
import mano2.studentperformance.interfaces.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api
@RestController
@Controller
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService service;

    @ApiOperation(value = "Gets all accounts", nickname = "AccountController.getAllAccounts")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Accounts")})
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<AccountDTO>> getAllAccounts() {
        Iterable<AccountDTO> accountDTOS = service.getAll();
        return new ResponseEntity<>(accountDTOS, HttpStatus.OK);
    }

    @ApiOperation(value = "Gets specific account", nickname = "AccountController.getAccount")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Account")})
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDTO> getAccount(@PathVariable("id") String id) throws EntityNotFoundException {
        AccountDTO accountDTO = service.getById(Long.valueOf(id));
        return new ResponseEntity<>(accountDTO, HttpStatus.OK);

    }
//    @ApiOperation(value = "Creates account", nickname = "AccountController.createAccount")
//    @ApiResponses(value = {@ApiResponse(code = 201, message = "Account is created")})
//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO accountDTO) {
//        AccountDTO savedAccount = service.add(accountDTO);
//        return new ResponseEntity<>(savedAccount, HttpStatus.CREATED);
//    }

//    @ApiOperation(value = "Deletes account", nickname = "AccountController.deleteAccount")
//    @ApiResponses(value = {@ApiResponse(code = 200, message = "Account is deleted")})
//    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Void> deleteAccount(@PathVariable("id") String id) throws EntityNotFoundException {
//        service.delete(Long.valueOf(id));
//        return new ResponseEntity<>(HttpStatus.OK);
//    }


    @ApiOperation(value = "Gets account page", nickname = "AccountController.getPage")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Account page")})
    @GetMapping(value = "/page", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Account> getPage(@PageableDefault( sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return service.getAccountPage(pageable);
    }
}
