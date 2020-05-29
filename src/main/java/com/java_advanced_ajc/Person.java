package com.java_advanced_ajc;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;

    protected String firstname;
    protected String lastname;
}
