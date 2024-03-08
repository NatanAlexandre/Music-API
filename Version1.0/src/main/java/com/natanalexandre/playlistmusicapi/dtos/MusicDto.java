package com.natanalexandre.playlistmusicapi.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MusicDto {
    @NotBlank
    private String musicName;
    @NotBlank
    private String musicVersion;
    @NotBlank
    private String artistName;
    @NotNull
    private boolean feat;
    private String otherArtists;
    @NotNull
    private boolean single;
    private String singleName;
    @NotNull
    private boolean albumEp;
    private String albumEpName;

    public MusicDto(String musicName, String musicVersion, String artistName, boolean feat, String otherArtists,
                    boolean single, String singleName, boolean albumEp, String albumEpName){
        this.musicName = musicName;
        this.musicVersion = musicVersion;
        this.artistName = artistName;
        this.feat = feat;
        this.otherArtists = otherArtists;
        this.single = single;
        this.singleName = singleName;
        this.albumEp = albumEp;
        this.albumEpName = albumEpName;
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

    public String getSingleName() {
        return singleName;
    }

    public boolean isAlbumEp() {
        return albumEp;
    }

    public String getAlbumEpName() {
        return albumEpName;
    }
}
