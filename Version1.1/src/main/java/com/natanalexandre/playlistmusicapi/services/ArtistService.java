package com.natanalexandre.playlistmusicapi.services;

import com.natanalexandre.playlistmusicapi.dtos.ArtistDto;
import com.natanalexandre.playlistmusicapi.models.ArtistModel;
import com.natanalexandre.playlistmusicapi.repositories.ArtistRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ArtistService {
    final ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository){
        this.artistRepository = artistRepository;
    }

    @Transactional
    public ArtistModel save(ArtistModel artistModel){
        return artistRepository.save(artistModel);
    }

    public boolean existsByArtistName(String artistName) {
        return artistRepository.existsByArtistName(artistName);
    }

    public List<ArtistModel> findAll(){
        return artistRepository.findAll();
    }

    public Optional<ArtistModel> findById(UUID id){
        return artistRepository.findById(id);
    }

    @Transactional
    public void delete(ArtistModel artistModel){
        artistRepository.delete(artistModel);
    }

}
