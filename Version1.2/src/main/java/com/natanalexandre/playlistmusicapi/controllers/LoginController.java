package com.natanalexandre.playlistmusicapi.controllers;

import com.natanalexandre.playlistmusicapi.dtos.LoginDto;
import com.natanalexandre.playlistmusicapi.models.AccountModel;
import com.natanalexandre.playlistmusicapi.services.AccountService;
import com.natanalexandre.playlistmusicapi.services.LoginService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/login")
public class LoginController {
    final LoginService loginService;
    final AccountService accountService;

    public LoginController(LoginService loginService, AccountService accountService){
        this.loginService = loginService;
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<Object> getOneLogin(@RequestBody @Valid LoginDto loginDto){
        Optional<AccountModel> account = accountService.findByUsernameAccount(loginDto.getUsername());
        if (!account.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Incorrect Username or Password!");
        }
        if (!account.get().getPasswordAccount().equals(loginDto.getPassword())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Incorrect Username or Password!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(account.get());
    }
}
