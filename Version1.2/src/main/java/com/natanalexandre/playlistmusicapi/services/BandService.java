package com.natanalexandre.playlistmusicapi.services;

import com.natanalexandre.playlistmusicapi.models.BandModel;
import com.natanalexandre.playlistmusicapi.repositories.BandRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BandService {
    final BandRepository bandRepository;

    public BandService(BandRepository bandRepository){
        this.bandRepository = bandRepository;
    }

    public List<BandModel> findAll(){
        return bandRepository.findAll();
    }

    public Optional<BandModel> findById(UUID id){
        return bandRepository.findById(id);
    }

    public void delete(BandModel bandModel){
        bandRepository.delete(bandModel);
    }

    public Object save(BandModel bandModel) {
        return bandRepository.save(bandModel);
    }

    public boolean existsByBandName(String bandName){
        return bandRepository.existsByBandName(bandName);
    }
}
