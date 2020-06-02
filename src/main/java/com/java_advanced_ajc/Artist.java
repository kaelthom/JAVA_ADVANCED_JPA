package com.java_advanced_ajc;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ARTIST_TABLE")
public class Artist extends Person {

    @OneToOne(cascade = CascadeType.REMOVE, mappedBy = "artist")
    private SacemRegistration sacemRegistration;

    @Column(name = "BNAME")
    private String bandName;

    @ManyToOne
    private Manager manager;
    @ManyToOne
    private Instrument favoriteInstrument;
    @OneToMany(mappedBy = "artist")
    private List<Media> medias = new ArrayList<>();
    @ManyToMany
    @JoinTable(
            name = "artist_to_inst",
            joinColumns = {@JoinColumn(name = "artist")},
            inverseJoinColumns = {@JoinColumn(name = "instrument")}
    )
    private List<Instrument> playableInstruments = new ArrayList<>();

    public Artist(String firstName, String lastName, String bandName) {
        super(firstName, lastName);
        this.bandName = bandName;
    }

    public SacemRegistration getSacemRegistration() {
        return sacemRegistration;
    }

    public void setSacemRegistration(SacemRegistration sacemRegistration) {
        this.sacemRegistration = sacemRegistration;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public Instrument getFavoriteInstrument() {
        return favoriteInstrument;
    }

    public void setFavoriteInstrument(Instrument favoriteInstrument) {
        this.favoriteInstrument = favoriteInstrument;
    }

    public List<Media> getMedias() {
        return medias;
    }

    public void setMedias(List<Media> medias) {
        this.medias = medias;
    }

    public List<Instrument> getPlayableInstruments() {
        return playableInstruments;
    }

    public void setPlayableInstruments(List<Instrument> playableInstruments) {
        this.playableInstruments = playableInstruments;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
