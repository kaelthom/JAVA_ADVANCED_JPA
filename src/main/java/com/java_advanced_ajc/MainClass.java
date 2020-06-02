package com.java_advanced_ajc;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class MainClass {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("ecommerce");
// Getting an instance of EntityManager
        EntityManager em = emf.createEntityManager();
// Usage of the "EntityManager" to fetch elements for example

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Artist artist = new Artist("Toto", "Titi", "band1");
        em.persist(artist);
        System.out.println(em.contains(artist));
//        em.remove(artist);
//        System.out.println(em.contains(artist));
        transaction.commit();

        transaction = em.getTransaction();
        transaction.begin();
        Media media = new Media(new MediaId("Media6", MediaType.DVD));
        em.persist(media);
        System.out.println(em.contains(media));
        transaction.commit();

        transaction = em.getTransaction();
        transaction.begin();
        Media media2 = em.find(Media.class, new MediaId("Media6", MediaType.DVD) );
        System.out.println(media2);
        transaction.commit();


        // .....
// Closing the "EntityManager"
        em.close();
//Closing the "EntityManagerFactory"
        emf.close();
    }
}
