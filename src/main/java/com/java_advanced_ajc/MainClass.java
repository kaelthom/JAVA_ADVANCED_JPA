package com.java_advanced_ajc;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

public class MainClass {
    public static void main(String[] args) {

        Artist artistDetached = new Artist("Toto1", "Titi", "band1");
        artistDetached.setId(15451);  // if no id, entity is handled by persistenceContext as a transient entity
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("ecommerce");
// Getting an instance of EntityManager
        EntityManager em = emf.createEntityManager();
// Usage of the "EntityManager" to fetch elements for example
        try {


            //persist / remove artist
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            Artist artist = new Artist("Toto", "Titi", "band1");
            em.persist(artist);
//            em.remove(artistDetached); throw IllegalArgumentException: Removing a detached instance com.java_advanced_ajc.Artist
            System.out.println(em.contains(artist));
//        em.remove(artist);
//        System.out.println(em.contains(artist));
            transaction.commit();


            //persist media
            MediaId newMediaId = new MediaId("Media28", MediaType.DVD);
            transaction = em.getTransaction();
            transaction.begin();
            Media media = new Media(newMediaId);
//            em.persist(media);
            System.out.println(em.contains(media));
            transaction.commit();

            //find media
            transaction = em.getTransaction();
            transaction.begin();
            Media media2 = em.find(Media.class, newMediaId);
            System.out.println(media2);
            transaction.commit();

            //update media
            transaction = em.getTransaction();
            transaction.begin();
            Media media3 = new Media(newMediaId);
            media3.setReleaseDate(new Date());
            media3 = em.merge(media3);
            System.out.println(media2);
            transaction.commit();


            //create artist + everything
            transaction = em.getTransaction();
            transaction.begin();
            Artist artist2 = artist;
            Manager manager = new Manager("managerfirstname", "managerlastname");
            artist2.setManager(manager);
            em.persist(manager);
            Instrument guitare = new Instrument("guitare", Instrument.InstrumentType.STRING);
            em.persist(guitare);
            Instrument triangle = new Instrument("triangle", Instrument.InstrumentType.PERCUSSION);
            em.persist(triangle);
            artist2.setFavoriteInstrument(triangle);
            SacemRegistration sacemReg = new SacemRegistration("sfml5kjf5555mk5j565", new Date());
            sacemReg = em.merge(sacemReg);
            sacemReg.setArtist(artist2);
            media3.setArtist(artist2);

            artist2.getPlayableInstruments().add(guitare);
            artist2.getPlayableInstruments().add(triangle);

            System.out.println(media2);
            em.persist(artist2);
            transaction.commit();

            //remove sacem with artist
            transaction = em.getTransaction();
            transaction.begin();
            Artist artist3 = new Artist("artist3", "name", "toto");
            SacemRegistration scr = new SacemRegistration("156431", new Date());
            em.persist(scr);
            em.persist(artist3);
            artist3.setSacemRegistration(scr);
            transaction.commit();
            //remove sacem with artist
            transaction = em.getTransaction();
            transaction.begin();
            em.remove(artist3);
            transaction.commit();

            Artist artistWithStringInstrument = new Artist("String Guy", "Knopf", "DR");
            transaction = em.getTransaction();
            transaction.begin();
            artistWithStringInstrument.setFavoriteInstrument(guitare);
            em.persist(artistWithStringInstrument);
            artist3.setSacemRegistration(scr);
            transaction.commit();

            transaction = em.getTransaction();
            transaction.begin();
            Query query = em.createQuery("from Artist a where a.favoriteInstrument.instrumentType = :instrumentType");
            query.setParameter("instrumentType", Instrument.InstrumentType.STRING);
            List<Artist> artists = query.getResultList();
            for (Artist artist1 : artists) {
                System.out.println(artist1.getLastname());
            }
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // .....
// Closing the "EntityManager"
            em.close();
//Closing the "EntityManagerFactory"
            emf.close();
        }


    }
}
