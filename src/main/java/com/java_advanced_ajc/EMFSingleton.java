package com.java_advanced_ajc;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMFSingleton {
    private static EntityManagerFactory instance;

    public static EntityManagerFactory getInstance() {
        if (instance == null) {
            instance = Persistence.createEntityManagerFactory("ecommerce");
        }
        return instance;
    }

}
