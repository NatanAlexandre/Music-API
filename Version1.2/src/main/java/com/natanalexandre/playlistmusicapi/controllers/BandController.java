package com.natanalexandre.playlistmusicapi.controllers;

import com.natanalexandre.playlistmusicapi.dtos.BandDto;
import com.natanalexandre.playlistmusicapi.models.BandModel;
import com.natanalexandre.playlistmusicapi.services.BandService;
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
@RequestMapping("/band")
public class BandController {
    final BandService bandService;

    public BandController(BandService bandService){
        this.bandService = bandService;
    }

    @PostMapping
    public ResponseEntity<Object> saveBand(@RequestBody @Valid BandDto bandDto){
        for (String member : bandDto.getBandMembers()){
            if (member.isEmpty()){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Band Member without information!");
            }
        }
        if (bandService.existsByBandName(bandDto.getBandName())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Band already exists");
        }
        var bandModel = new BandModel();
        BeanUtils.copyProperties(bandDto, bandModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(bandService.save(bandModel));
    }

    @GetMapping
    public ResponseEntity<List<BandModel>> getAllBands(){
        List<BandModel> bandList = bandService.findAll();
        if (!bandList.isEmpty()){
            for (BandModel band : bandList){
                UUID id = band.getIdBand();
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(bandList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneBand(@PathVariable(value = "id") UUID id){
        Optional<BandModel> band = bandService.findById(id);
        if (!band.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Band with id " + id + " not found!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(band.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBand(@PathVariable(value = "id") UUID id,
                                             @RequestBody @Valid BandDto bandDto){
        Optional<BandModel> band = bandService.findById(id);
        if (!band.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Band with id " + id + " not found!");
        }
        for (String member : bandDto.getBandMembers()){
            if (member.isEmpty()){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Band Member without information!");
            }
        }
        var bandModel = new BandModel();
        BeanUtils.copyProperties(bandDto, bandModel);
        bandModel.setIdBand(band.get().getIdBand());
        bandModel.setBandName(bandModel.getBandName());
        return ResponseEntity.status(HttpStatus.OK).body(bandService.save(bandModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBand(@PathVariable(value = "id")UUID id){
        Optional<BandModel> band = bandService.findById(id);
        if (!band.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Band with id " + id + " not found!");

        }
        bandService.delete(band.get());
        return ResponseEntity.status(HttpStatus.OK).body("Band " + band.get().getBandName() + " deleted successfully!");
    }

}
