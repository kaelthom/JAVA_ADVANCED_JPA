package com.java_advanced_ajc;

import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

public class ArtistDAOQueryDSLImpl extends ArtistDAOJPAImpl {

    static EntityManagerFactory emf = EMFSingleton.getInstance();

    @Override
    public List<Artist> findByInstrumentType(Instrument.InstrumentType instrumentType) {
        EntityManager em = null;
        List<Artist> artists = new ArrayList<>();

        try {
            em = emf.createEntityManager();
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            QArtist artist = QArtist.artist;
            JPQLQuery<Artist> query = new JPAQuery<>(em);
            artists = query.from(artist)
                    .join(artist.favoriteInstrument)
                    .where(artist.favoriteInstrument.instrumentType.eq(Instrument.InstrumentType.STRING))
                    .fetch();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return artists;
    }

}
