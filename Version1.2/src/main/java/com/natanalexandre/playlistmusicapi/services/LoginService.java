package com.natanalexandre.playlistmusicapi.services;

import com.natanalexandre.playlistmusicapi.models.LoginModel;
import com.natanalexandre.playlistmusicapi.repositories.LoginRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoginService {
    final LoginRepository loginRepository;

    public LoginService(LoginRepository loginRepository){
        this.loginRepository = loginRepository;
    }

    @Transactional
    public LoginModel save(LoginModel loginModel){
        return loginRepository.save(loginModel);
    }

    public List<LoginModel> findAll(){
        return loginRepository.findAll();
    }

    public Optional<LoginModel> findById(UUID id){
        return loginRepository.findById(id);
    }

    @Transactional
    public void delete(LoginModel loginModel){
        loginRepository.delete(loginModel);
    }

    public boolean existsByUsername(String username){
        return loginRepository.existsByUsername(username);
    }

    public boolean existsByPassword(String password){
        return loginRepository.existsByPassword(password);
    }
}
