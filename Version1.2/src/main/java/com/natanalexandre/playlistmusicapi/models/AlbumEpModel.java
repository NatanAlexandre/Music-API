package com.natanalexandre.playlistmusicapi.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "tb_albumep")
public class AlbumEpModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idAlbumEp;
    @Column(nullable = false, length = 80)
    private String albumEpName;
    @Column(nullable = false)
    private int albumEpYear;
    @Column(nullable = false)
    private int tracksNumber;
    @Column(nullable = false, length = 70)
    private String artistName;

    public UUID getIdAlbumEp() {
        return idAlbumEp;
    }

    public void setIdAlbumEp(UUID idAlbumEp) {
        this.idAlbumEp = idAlbumEp;
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
