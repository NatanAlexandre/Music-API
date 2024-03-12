package com.natanalexandre.playlistmusicapi.repositories;

import com.natanalexandre.playlistmusicapi.models.BandModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BandRepository extends JpaRepository<BandModel, UUID> {
    boolean existsByBandName(String bandName);
}
