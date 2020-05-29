package com.java_advanced_ajc;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MANAGER_TABLE")
public class Manager {

    @Id
    public long id;

    @Column(name = "FNAME")
    String firstname;

    @Column(name = "LNAME")
    String lastname;

    @OneToMany(mappedBy = "manager")
    List<Artist> artists = new ArrayList<>();
}
