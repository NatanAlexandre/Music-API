package com.natanalexandre.playlistmusicapi.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AlbumEpDto {
    @NotBlank
    private String albumEpName;
    @NotNull
    private int albumEpYear;
    @NotNull
    private int tracksNumber;
    @NotBlank
    private String artistName;

    public AlbumEpDto(String albumEpName, int albumEpYear, int tracksNumber, String artistName){
        this.albumEpName = albumEpName;
        this.albumEpYear = albumEpYear;
        this.tracksNumber = tracksNumber;
        this.artistName = artistName;
    }

    public String getAlbumEpName() {
        return albumEpName;
    }

    public void setAlbumEpName(String albumEpName) {
        this.albumEpName = albumEpName;
    }

    public int getAlbumEpYear() {
        return albumEpYear;
    }

    public void setAlbumEpYear(int albumEpYear) {
        this.albumEpYear = albumEpYear;
    }

    public int getTracksNumber() {
        return tracksNumber;
    }

    public void setTracksNumber(int tracksNumber) {
        this.tracksNumber = tracksNumber;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
}
