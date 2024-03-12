package com.natanalexandre.playlistmusicapi.repositories;

import com.natanalexandre.playlistmusicapi.dtos.ArtistDto;
import com.natanalexandre.playlistmusicapi.models.ArtistModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ArtistRepository extends JpaRepository<ArtistModel, UUID> {
    boolean existsByArtistName(String artistName);

}
