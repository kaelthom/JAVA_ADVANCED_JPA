package com.java_advanced_ajc;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MANAGER_TABLE")
public class Manager extends Person {

    @OneToMany(mappedBy = "manager")
    List<Artist> artists = new ArrayList<>();
    private int budget = 0;

    public Manager(String firstname, String lastname) {
        super(firstname, lastname);
    }

    public Manager() {
    }
}
