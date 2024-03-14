package com.natanalexandre.playlistmusicapi.repositories;

import com.natanalexandre.playlistmusicapi.models.MusicModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MusicRepository extends JpaRepository<MusicModel, UUID> {

    boolean existsByMusicVersionAndMusicNameAndSingleNameAndAlbumEpName(String musicVersion, String musicName, String singleName, String albumEpName);

    boolean existsByArtistName(String artistName);

    boolean existsByOtherArtists(List<String> otherArtists);
    boolean existsByAlbumEpName(String albumEpName);
}
