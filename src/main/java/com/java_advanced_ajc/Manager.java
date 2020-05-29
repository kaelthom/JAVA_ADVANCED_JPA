package com.java_advanced_ajc;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MANAGER_TABLE")
public class Manager extends Person{

    @OneToMany(mappedBy = "manager")
    List<Artist> artists = new ArrayList<>();
}
