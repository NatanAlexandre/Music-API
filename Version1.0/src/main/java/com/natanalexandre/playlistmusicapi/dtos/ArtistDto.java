package com.natanalexandre.playlistmusicapi.dtos;

import jakarta.validation.constraints.NotBlank;

public class ArtistDto {
    @NotBlank
    private String artistName;
    @NotBlank
    private String musicStyle;
    @NotBlank
    private String about;

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
