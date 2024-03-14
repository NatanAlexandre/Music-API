package com.natanalexandre.playlistmusicapi.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class ArtistDto {
    @NotBlank
    private String artistName;
    @NotBlank
    private String musicStyle;
    @NotBlank
    private String about;

    public ArtistDto(String artistName, String musicStyle, String about){
        this.artistName = artistName;
        this.musicStyle = musicStyle;
        this.about = about;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getMusicStyle() {
        return musicStyle;
    }

    public void setMusicStyle(String musicStyle) {
        this.musicStyle = musicStyle;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
