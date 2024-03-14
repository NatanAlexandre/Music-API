package com.natanalexandre.playlistmusicapi.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_bands")
public class BandModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idBand;
    @Column(nullable = false, unique = true, length = 80)
    private String bandName;
    @Column(nullable = false, length = 400)
    private List<String> bandMembers;
    @Column(nullable = false, length = 80)
    private String musicStyle;
    @Column(nullable = false, length = 500)
    private String about;

    public UUID getIdBand() {
        return idBand;
    }

    public void setIdBand(UUID idBand) {
        this.idBand = idBand;
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
