package com.natanalexandre.playlistmusicapi.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "tb_musics")
public class MusicModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idMusic;
    @Column(nullable = false, length = 70)
    private String musicName;
    @Column(nullable = false, length = 70)
    private String musicVersion;
    @Column(nullable = false, length = 70)
    private String artistName;
    @Column(nullable = false)
    private boolean feat;
    @Column(nullable = true, length = 120)
    private String otherArtists;
    @Column(nullable = false)
    private boolean single;
    @Column(nullable = true, length = 70)
    private String singleName;
    @Column(nullable = false)
    private boolean albumEp;
    @Column(nullable = true, length = 100)
    private String albumEpName;

    public UUID getIdMusic() {
        return idMusic;
    }

    public void setIdMusic(UUID idMusic) {
        this.idMusic = idMusic;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getMusicVersion() {
        return musicVersion;
    }

    public void setMusicVersion(String musicVersion) {
        this.musicVersion = musicVersion;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public boolean isFeat() {
        return feat;
    }

    public void setFeat(boolean feat) {
        this.feat = feat;
    }

    public String getOtherArtists() {
        return otherArtists;
    }

    public void setOtherArtists(String otherArtists) {
        this.otherArtists = otherArtists;
    }

    public boolean isSingle() {
        return single;
    }

    public void setSingle(boolean single) {
        this.single = single;
    }

    public String getSingleName() {
        return singleName;
    }

    public void setSingleName(String singleName) {
        this.singleName = singleName;
    }

    public boolean isAlbumEp() {
        return albumEp;
    }

    public void setAlbumEp(boolean albumEp) {
        this.albumEp = albumEp;
    }

    public String getAlbumEpName() {
        return albumEpName;
    }

    public void setAlbumEpName(String albumEpName) {
        this.albumEpName = albumEpName;
    }
}
