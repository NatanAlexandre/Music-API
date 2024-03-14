package com.natanalexandre.playlistmusicapi.repositories;

import com.natanalexandre.playlistmusicapi.models.LoginModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LoginRepository extends JpaRepository<LoginModel, UUID> {
    boolean existsByUsername(String username);
    boolean existsByPassword(String password);
}
