package com.natanalexandre.playlistmusicapi.services;

import com.natanalexandre.playlistmusicapi.models.MusicModel;
import com.natanalexandre.playlistmusicapi.repositories.ArtistRepository;
import com.natanalexandre.playlistmusicapi.repositories.MusicRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MusicService {

    final MusicRepository musicRepository;
    final ArtistRepository artistRepository;

    public MusicService(MusicRepository musicRepository, ArtistRepository artistRepository){
        this.musicRepository = musicRepository;
        this.artistRepository = artistRepository;
    }

    @Transactional
    public MusicModel save(MusicModel musicModel){
        return musicRepository.save(musicModel);
    }

    public boolean existsByMusicVersionAndMusicNameAndSingleNameAndAlbumEpName(String musicVersion, String musicName, String singleName, String albumEpName) {
        return musicRepository.existsByMusicVersionAndMusicNameAndSingleNameAndAlbumEpName(musicVersion, musicName, singleName, albumEpName);
    }

    public boolean existsByArtistName(String artistName) {
        return musicRepository.existsByArtistName(artistName);
    }


    public boolean existsByAlbumEpName(String albumEpName){
        return musicRepository.existsByAlbumEpName(albumEpName);
    }

    public List<MusicModel> findAll(){
        return musicRepository.findAll();
    }

    public Optional<MusicModel> findById(UUID id){
        return musicRepository.findById(id);
    }

    public void delete(MusicModel musicModel) {
        musicRepository.delete(musicModel);
    }

    public boolean existsByOtherArtists(List<String> otherArtists){
        return musicRepository.existsByOtherArtists(otherArtists);
    }

}
