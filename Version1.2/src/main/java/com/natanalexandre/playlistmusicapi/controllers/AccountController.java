package com.natanalexandre.playlistmusicapi.controllers;

import com.natanalexandre.playlistmusicapi.dtos.AccountDto;
import com.natanalexandre.playlistmusicapi.models.AccountModel;
import com.natanalexandre.playlistmusicapi.services.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/register")
public class AccountController {
    final AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<Object> saveAccount(@RequestBody @Valid AccountDto accountDto){
        if (accountDto.getUsernameAccount().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username field Required!");
        }
        if (accountDto.getEmailAccount().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email field Required!");
        }
        if (accountDto.getNumberAccount().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Telephone field Required!");
        }
        if (accountDto.getPasswordAccount().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password field Required!");
        }
        if (accountService.existsByUsernameAccount(accountDto.getUsernameAccount())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username: " + accountDto.getUsernameAccount() + " Already taken!");
        }
        if (accountService.existsByEmailAccount(accountDto.getEmailAccount())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email: " + accountDto.getEmailAccount() + " Already taken!");
        }
        var accountModel = new AccountModel();
        BeanUtils.copyProperties(accountDto, accountModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.save(accountModel));
    }

    @GetMapping
    public ResponseEntity<List<AccountModel>> getAllAccounts(){
        List<AccountModel> accountList = accountService.findAll();
        if (!accountList.isEmpty()){
            for (AccountModel account : accountList){
                UUID id = account.getIdAccount();
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(accountList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneAccount(@PathVariable(value = "id") UUID id){
        Optional<AccountModel> account = accountService.findById(id);
        if (!account.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account with id: " + id + " not found!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(account.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAccount(@PathVariable(name = "id") UUID id,
                                                @RequestBody @Valid AccountDto accountDto){
        Optional<AccountModel> account = accountService.findById(id);
        if (!account.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body("Account with id: " + id + " not found!");
        }
        var accountModel = new AccountModel();
        BeanUtils.copyProperties(accountDto, accountModel);
        accountModel.setIdAccount(accountModel.getIdAccount());
        return ResponseEntity.status(HttpStatus.OK).body(accountService.save(accountModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAccount(@PathVariable(value = "id") UUID id){
        Optional<AccountModel> account = accountService.findById(id);
        if (!account.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body("Account with id: " + id + " not found!");
        }
        accountService.delete(account.get());
        return ResponseEntity.status(HttpStatus.OK).body("Account with id: " + id + " deleted successfully!");
    }
}
