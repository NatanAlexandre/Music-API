package com.natanalexandre.playlistmusicapi.repositories;

import com.natanalexandre.playlistmusicapi.models.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<AccountModel, UUID> {
    boolean existsByUsernameAccount(String usernameAccount);
    boolean existsByPasswordAccount(String passwordAccount);
    Optional<AccountModel> findByUsernameAccount(String usernameAccount);

    boolean existsByEmailAccount(String emailAccount);
    Optional<AccountModel> findByPasswordAccount(String password);
}
