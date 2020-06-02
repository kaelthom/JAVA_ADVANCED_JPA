package com.java_advanced_ajc;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "SACEM_REGISTRATION")
public class SacemRegistration {
    @OneToOne
    private Artist artist;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true)
    private String code;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;

    public SacemRegistration() {
    }

    public SacemRegistration(String code, Date registrationDate) {
        this.code = code;
        this.registrationDate = registrationDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
