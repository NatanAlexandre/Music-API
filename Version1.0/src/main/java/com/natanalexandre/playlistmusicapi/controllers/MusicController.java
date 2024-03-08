package com.natanalexandre.playlistmusicapi.controllers;

import com.natanalexandre.playlistmusicapi.dtos.MusicDto;
import com.natanalexandre.playlistmusicapi.models.AlbumEpModel;
import com.natanalexandre.playlistmusicapi.models.MusicModel;
import com.natanalexandre.playlistmusicapi.services.AlbumEpService;
import com.natanalexandre.playlistmusicapi.services.ArtistService;
import com.natanalexandre.playlistmusicapi.services.MusicService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/musics")
public class MusicController {
    final MusicService musicService;
    final ArtistService artistService;
    final AlbumEpService albumEpService;

    public MusicController(MusicService musicService, ArtistService artistService, AlbumEpService albumEpService){
        this.musicService = musicService;
        this.artistService = artistService;
        this.albumEpService = albumEpService;
    }

    @PostMapping
    public ResponseEntity<Object> saveMusic(@RequestBody @Valid MusicDto musicDto){
        if (musicService.existsByMusicVersionAndMusicNameAndSingleNameAndAlbumEpName(musicDto.getMusicVersion(), musicDto.getMusicName(),
                musicDto.getSingleName(), musicDto.getAlbumEpName())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This music version already exists");
        }
        if (!artistService.existsByArtistName(musicDto.getArtistName())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Artist not found!");
        }
        if (!musicDto.isSingle() && !musicDto.isAlbumEp()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Missing Single or Album/Ep Name");
        }
        if (musicDto.isSingle() && musicDto.isAlbumEp()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Single and Album/EP marked");
        }
        if (musicDto.isSingle() && musicDto.getSingleName().isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Missing Single Name");

        }
        if (musicDto.isAlbumEp() && musicDto.getAlbumEpName().isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Missing Album/Ep Name");

        }
        if (musicDto.isFeat() && musicDto.getOtherArtists().isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Missing Other Artists name");
        }
        if (musicDto.isAlbumEp() && !musicDto.getAlbumEpName().isEmpty()){
            if (!albumEpService.existsByAlbumEpNameAndArtistName(musicDto.getAlbumEpName(), musicDto.getArtistName())){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Album don't exists!");
            }
        }
        var musicModel = new MusicModel();
        BeanUtils.copyProperties(musicDto, musicModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(musicService.save(musicModel));
    }

    @GetMapping
    public ResponseEntity<List<MusicModel>> getAllMusics(){
        List<MusicModel> musicModelList = musicService.findAll();
        if (!musicModelList.isEmpty()){
            for (MusicModel music : musicModelList){
                UUID id = music.getIdMusic();
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(musicModelList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneMusic(@PathVariable(value = "id") UUID id){
        Optional<MusicModel> music = musicService.findById(id);
        if (!music.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Music with id " + id + " not found!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(music.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMusic(@PathVariable(value = "id") UUID id,
                                              @RequestBody @Valid MusicDto musicDto){
        Optional<MusicModel> music = musicService.findById(id);
        if (!music.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Music with id " + id + " not found!");
        }
        if (musicService.existsByMusicVersionAndMusicNameAndSingleNameAndAlbumEpName(musicDto.getMusicVersion(), musicDto.getMusicName(),
                musicDto.getSingleName(), musicDto.getAlbumEpName())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This music version already exists");
        }
        var musicModel = new MusicModel();
        BeanUtils.copyProperties(musicDto, musicModel);
        musicModel.setIdMusic(music.get().getIdMusic());
        musicModel.setAlbumEpName(music.get().getAlbumEpName());
        return ResponseEntity.status(HttpStatus.OK).body(musicService.save(musicModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMusic(@PathVariable(value = "id") UUID id){
        Optional<MusicModel> music = musicService.findById(id);
        if (!music.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("music with id " + id + " not found!");
        }
        musicService.delete(music.get());
        return ResponseEntity.status(HttpStatus.OK).body("Music deleted sucessfully!");
    }



}
