package com.java_advanced_ajc;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MainClass {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("ecommerce");
// Getting an instance of EntityManager
        EntityManager em = emf.createEntityManager();
// Usage of the "EntityManager" to fetch elements for example
// .....
// Closing the "EntityManager"
        em.close();
//Closing the "EntityManagerFactory"
        emf.close();
    }
}
