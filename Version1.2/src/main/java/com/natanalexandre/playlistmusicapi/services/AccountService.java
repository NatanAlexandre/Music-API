package com.natanalexandre.playlistmusicapi.services;

import com.natanalexandre.playlistmusicapi.models.AccountModel;
import com.natanalexandre.playlistmusicapi.repositories.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {
    final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @Transactional
    public AccountModel save(AccountModel accountModel){
        return accountRepository.save(accountModel);
    }

    public List<AccountModel> findAll(){
        return accountRepository.findAll();
    }

    public Optional<AccountModel> findById(UUID id){
        return accountRepository.findById(id);
    }

    @Transactional
    public void delete(AccountModel accountModel){
        accountRepository.delete(accountModel);
    }

    public boolean existsByUsernameAccount(String usernameAccount){
        return accountRepository.existsByUsernameAccount(usernameAccount);
    }

    public Optional<AccountModel> findByUsernameAccount(String usernameAccount){
        return accountRepository.findByUsernameAccount(usernameAccount);
    }

    public boolean existsByEmailAccount(String emailAccount) {
        return accountRepository.existsByEmailAccount(emailAccount);
    }

    public boolean existsByPasswordAccount(String passwordAccount){
        return accountRepository.existsByPasswordAccount(passwordAccount);
    }

    public Optional<AccountModel> findByPasswordAccount(String password) {
        return accountRepository.findByPasswordAccount(password);
    }
}
