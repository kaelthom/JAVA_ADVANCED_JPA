package com.java_advanced_ajc;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ARTIST_TABLE")
public class Artist {

    public Artist(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "FNAME")
    private String firstName;

    @Column(name = "LNAME")
    private String lastName;

    @Column(name = "BNAME")
    private String bandName;

    @ManyToOne
    private Manager manager;

    @OneToOne
    SacemRegistration sacemRegistration;

    @ManyToOne
    private Instrument favoriteInstrument;

    @ManyToMany
    @JoinTable(
            name = "artist_to_inst",
            joinColumns = {@JoinColumn(name = "instrument")},
            inverseJoinColumns = {@JoinColumn(name = "artist")}
    )
    private List<Instrument> playableInstruments;

}
