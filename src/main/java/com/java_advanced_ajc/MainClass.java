package com.java_advanced_ajc;

import javax.persistence.*;
import javax.persistence.criteria.*;
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
            SacemRegistration sacemReg = new SacemRegistration("sfml555kjf511555mk5j565", new Date());
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

            System.out.println("JPQL query");
            transaction = em.getTransaction();
            transaction.begin();
            Query query = em.createQuery("from Artist a where a.favoriteInstrument.instrumentType = :instrumentType");
            query.setParameter("instrumentType", Instrument.InstrumentType.STRING);
            List<Artist> artists = query.getResultList();
            for (Artist artist1 : artists) {
                System.out.println(artist1.getLastname());
            }
            transaction.commit();

            System.out.println("JPQL named query");
            transaction = em.getTransaction();
            transaction.begin();
            Query queryNamed = em.createNamedQuery("findAllArtistsByInstrumentType");
            queryNamed.setParameter("instrumentType", Instrument.InstrumentType.STRING);
            artists = queryNamed.getResultList();
            for (Artist artist1 : artists) {
                System.out.println(artist1.getLastname());
            }
            transaction.commit();

            System.out.println("JPQL query with criteria builder");
            transaction = em.getTransaction();
            transaction.begin();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Artist> queryCriteria = cb.createQuery(Artist.class);
            Root<Artist> queryRoot = queryCriteria.from(Artist.class);
            queryCriteria.select(queryRoot);
            Query criteriaQuery = em.createQuery(queryCriteria);

            artists = criteriaQuery.getResultList();
            for (Artist artist1 : artists) {
                System.out.println(artist1.getLastname());
            }
            transaction.commit();

            System.out.println("JPQL query with criteria builder and join");
            transaction = em.getTransaction();
            transaction.begin();
            CriteriaQuery<Artist> queryCriteriaWhere = em.getCriteriaBuilder().createQuery(Artist.class);
            Root<Artist> queryRootWhere = queryCriteriaWhere.from(Artist.class);
            Join<Artist, Instrument> instJoin = queryRootWhere.join("favoriteInstrument");
            ParameterExpression<Instrument.InstrumentType> param = cb.parameter(Instrument.InstrumentType.class);
            queryCriteriaWhere.select(queryRootWhere).where(cb.equal(instJoin.get("instrumentType"), param));
            Query criteriaQueryWhere = em.createQuery(queryCriteriaWhere);
            criteriaQueryWhere.setParameter(param, Instrument.InstrumentType.STRING);
            artists = criteriaQueryWhere.getResultList();

            for (Artist artist1 : artists) {
                System.out.println(artist1.getLastname());
            }
            transaction.commit();
            em.close();


            System.out.println("query with DAO and JPA or QueryDSL");
            ArtistDAO artistDAO = new ArtistDAOQueryDSLImpl();
            Artist artist1 = new Artist("Artist1", "GuitarGuy", "Group1");
            artistDAO.create(artist1);

            Instrument guitare2 = new Instrument("Guitare", Instrument.InstrumentType.STRING);
            em = EMFSingleton.getInstance().createEntityManager();
            transaction = em.getTransaction();
            transaction.begin();
            em.persist(guitare2);
            artist1 = artistDAO.findAll().get(0);
            artist1.setFavoriteInstrument(guitare2);
            transaction.commit();
            em.close();
            artistDAO.update(artist1);


            List<Artist> artists2 = artistDAO.findByInstrumentType(Instrument.InstrumentType.STRING);
            artists2.forEach(artist4 -> System.out.println(artist4.getLastname()));


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
