package com.natanalexandre.playlistmusicapi.services;

import com.natanalexandre.playlistmusicapi.models.AlbumEpModel;
import com.natanalexandre.playlistmusicapi.repositories.AlbumEpRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AlbumEpService {
    final AlbumEpRepository albumEpRepository;

    public AlbumEpService(AlbumEpRepository albumEpRepository){
        this.albumEpRepository = albumEpRepository;
    }

    public AlbumEpModel save(AlbumEpModel albumEpModel) {
        return albumEpRepository.save(albumEpModel);
    }

    public List<AlbumEpModel> findAll() {
        return albumEpRepository.findAll();
    }

    public Optional<AlbumEpModel> findById(UUID id) {
        return albumEpRepository.findById(id);
    }

    public boolean existsByAlbumEpNameAndArtistName(String albumEpName, String artistName) {
        return albumEpRepository.existsByAlbumEpNameAndArtistName(albumEpName, artistName);
    }

    public void delete(AlbumEpModel albumEpModel) {
        albumEpRepository.delete(albumEpModel);
    }
}
