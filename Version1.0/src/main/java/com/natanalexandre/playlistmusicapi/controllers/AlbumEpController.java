package com.natanalexandre.playlistmusicapi.controllers;

import com.natanalexandre.playlistmusicapi.dtos.AlbumEpDto;
import com.natanalexandre.playlistmusicapi.models.AlbumEpModel;
import com.natanalexandre.playlistmusicapi.models.MusicModel;
import com.natanalexandre.playlistmusicapi.services.AlbumEpService;
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
@RequestMapping("/albumEp")
public class AlbumEpController {
    final AlbumEpService albumEpService;
    final ArtistService artistService;
    final MusicService musicService;

    public AlbumEpController(AlbumEpService albumEpService, ArtistService artistService, MusicService musicService){
        this.albumEpService = albumEpService;
        this.artistService = artistService;
        this.musicService = musicService;
    }

    @PostMapping
    public ResponseEntity<Object> saveAlbumEp(@RequestBody @Valid AlbumEpDto albumEpDto){
        if (!artistService.existsByArtistName(albumEpDto.getArtistName())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Artist not found!");
        }
        if (albumEpService.existsByAlbumEpNameAndArtistName(albumEpDto.getAlbumEpName(), albumEpDto.getArtistName())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Album already exists!");
        }
        var albumEpModel = new AlbumEpModel();
        BeanUtils.copyProperties(albumEpDto, albumEpModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(albumEpService.save(albumEpModel));
    }

    @GetMapping
    public ResponseEntity<List<AlbumEpModel>> getAllAlbumsEps(){
        List<AlbumEpModel> albumList = albumEpService.findAll();
        if (!albumList.isEmpty()){
            for (AlbumEpModel album : albumList){
                UUID id = album.getIdAlbumEp();
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(albumList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneAlbumEp(@PathVariable(value = "id") UUID id){
        Optional<AlbumEpModel> album = albumEpService.findById(id);
        if (!album.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Album/Ep not found!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(album.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> UpdateAlbumEp(@PathVariable(value = "id") UUID id,
                                                @RequestBody @Valid AlbumEpDto albumEpDto){
        Optional<AlbumEpModel> album = albumEpService.findById(id);
        if (!album.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Album/Ep with id " + id + " found!");
        }
        if (!artistService.existsByArtistName(albumEpDto.getArtistName())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Artist not found!");
        }
        var albumEpModel = new AlbumEpModel();
        albumEpModel.setArtistName(album.get().getArtistName());
        albumEpModel.setIdAlbumEp(album.get().getIdAlbumEp());
        if (albumEpService.existsByAlbumEpNameAndArtistName(albumEpDto.getAlbumEpName(), albumEpDto.getArtistName())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This album already exists!");
        }
        BeanUtils.copyProperties(albumEpDto, albumEpModel);
        return ResponseEntity.status(HttpStatus.OK).body(albumEpService.save(albumEpModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAlbumEp(@PathVariable(value = "id") UUID id){
        Optional<AlbumEpModel> album = albumEpService.findById(id);
        List<AlbumEpModel> albumList = albumEpService.findAll();
        int albumcount = 0;
        if (!album.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Album/Ep with id " + id + " found!");
        }
        if (!albumList.isEmpty()){
            for (AlbumEpModel album0 : albumList){
                if (musicService.existsByAlbumEpName(album.get().getAlbumEpName())){
                    albumcount += 1;
                }
            }
        }
        if (albumcount > 0){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This album can't be deleted!");
        }
        albumEpService.delete(album.get());
        return ResponseEntity.status(HttpStatus.OK).body("Album deleted Sucessfully");
    }
}
