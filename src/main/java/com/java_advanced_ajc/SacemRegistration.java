package com.java_advanced_ajc;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "SACEM_REGISTRATION")
public class SacemRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String code;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;

    @OneToOne(mappedBy = "sacemRegistration")
    Artist artist;

}
