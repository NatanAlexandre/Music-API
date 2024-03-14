package com.natanalexandre.playlistmusicapi.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BandDto {
    @NotBlank
    private String bandName;
    @NotEmpty
    private List<String> bandMembers;
    @NotBlank
    private String musicStyle;
    @NotBlank
    private String about;

    public BandDto(String bandName, List<String> bandMembers, String musicStyle, String about){
        this.bandName = bandName;
        this.bandMembers = bandMembers;
        this.musicStyle = musicStyle;
        this.about = about;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public List<String> getBandMembers() {
        return bandMembers;
    }

    public void setBandMembers(List<String> bandMembers) {
        this.bandMembers = bandMembers;
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
