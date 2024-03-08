package com.natanalexandre.playlistmusicapi.repositories;

import com.natanalexandre.playlistmusicapi.models.AlbumEpModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AlbumEpRepository extends JpaRepository<AlbumEpModel, UUID> {
    boolean existsByAlbumEpNameAndArtistName(String albumEpName, String artistName);
}
