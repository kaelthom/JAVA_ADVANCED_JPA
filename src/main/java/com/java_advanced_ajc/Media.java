package com.java_advanced_ajc;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Media implements Serializable {
    @EmbeddedId
    private MediaId id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date releaseDate;

    @ManyToOne
    private Artist artist;

    public Media() {
    }

    public Media(MediaId id) {
        this.id = id;
    }

    public MediaId getId() {
        return id;
    }

    public void setId(MediaId id) {
        this.id = id;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

}
