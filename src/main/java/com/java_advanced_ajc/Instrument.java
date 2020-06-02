package com.java_advanced_ajc;

import javax.persistence.*;
import java.util.List;

@Entity
public class Instrument {

    @OneToMany(mappedBy = "favoriteInstrument")
    List<Artist> artistsForWhichItsFavorite;
    @ManyToMany(mappedBy = "playableInstruments")
    List<Artist> artistsWhichCcanPlay;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private InstrumentType instrumentType;

    public Instrument(String name, InstrumentType instrumentType) {
        this.name = name;
        this.instrumentType = instrumentType;
    }

    public List<Artist> getArtistsForWhichItsFavorite() {
        return artistsForWhichItsFavorite;
    }

    public void setArtistsForWhichItsFavorite(List<Artist> artistsForWhichItsFavorite) {
        this.artistsForWhichItsFavorite = artistsForWhichItsFavorite;
    }

    public List<Artist> getArtistsWhichCcanPlay() {
        return artistsWhichCcanPlay;
    }

    public void setArtistsWhichCcanPlay(List<Artist> artistsWhichCcanPlay) {
        this.artistsWhichCcanPlay = artistsWhichCcanPlay;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InstrumentType getInstrumentType() {
        return instrumentType;
    }

    public void setInstrumentType(InstrumentType instrumentType) {
        this.instrumentType = instrumentType;
    }

    enum InstrumentType {
        WIND,
        WOODWIND,
        BRASS,
        PERCUSSION,
        STRING
    }
}
