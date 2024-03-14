package com.natanalexandre.playlistmusicapi.controllers;

import com.natanalexandre.playlistmusicapi.dtos.ArtistDto;
import com.natanalexandre.playlistmusicapi.models.ArtistModel;
import com.natanalexandre.playlistmusicapi.models.MusicModel;
import com.natanalexandre.playlistmusicapi.services.ArtistService;
import com.natanalexandre.playlistmusicapi.services.MusicService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("artist")
public class ArtistController {
    final ArtistService artistService;
    final MusicService musicService;

    public ArtistController(ArtistService artistService, MusicService musicService){
        this.artistService = artistService;
        this.musicService = musicService;
    }

    @PostMapping
    public ResponseEntity<Object> saveArtist(@RequestBody @Valid ArtistDto artistDto){
        if (artistService.existsByArtistName(artistDto.getArtistName())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Artist " + artistDto.getArtistName() + " already exists");
        }
        var artistModel = new ArtistModel();
        BeanUtils.copyProperties(artistDto, artistModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(artistService.save(artistModel));
    }

    @GetMapping
    public ResponseEntity<List<ArtistModel>> getAllArtists(){
        List<ArtistModel> artistList = artistService.findAll();
        if (!artistList.isEmpty()){
            for (ArtistModel artist : artistList){
                UUID id = artist.getIdArtist();
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(artistList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneArtist(@PathVariable(value = "id") UUID id){
        Optional<ArtistModel> artist = artistService.findById(id);
        if (!artist.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Artist with name " + id + "not found!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(artist.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateArtist(@PathVariable(value = "id") UUID id,
                                               @RequestBody @Valid ArtistDto artistDto){
        Optional<ArtistModel> artist = artistService.findById(id);
        if (!artist.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Artist with id" + id + "not found!");
        }
        var artistModel = new ArtistModel();
        BeanUtils.copyProperties(artistDto, artistModel);
        artistModel.setIdArtist(artist.get().getIdArtist());
        artistModel.setArtistName(artist.get().getArtistName());
        return ResponseEntity.status(HttpStatus.OK).body(artistService.save(artistModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteArtist(@PathVariable(value = "id") UUID id){
        Optional<ArtistModel> artist = artistService.findById(id);
        List<MusicModel> musicList = musicService.findAll();
        int music0 = 0;
        if (!artist.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Artist with id " + id + " not found!");
        }
        for (MusicModel music : musicList){
            if (musicService.existsByArtistName(artist.get().getArtistName())){
                music0 += 1;
            }
        }
        if (music0 > 0){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Artist " + artist.get().getArtistName() + " can't be deleted!");
        }
        artistService.delete(artist.get());
        return ResponseEntity.status(HttpStatus.OK).body("Artist " + artist.get().getArtistName() + " deleted sucessfully!");
    }
}
