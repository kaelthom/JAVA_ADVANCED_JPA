package com.java_advanced_ajc;

import javax.persistence.*;

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

}
