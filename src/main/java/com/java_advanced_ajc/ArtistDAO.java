package com.java_advanced_ajc;

import java.util.List;

public interface ArtistDAO extends GenericDAO<Artist, Long> {
    List<Artist> findByInstrumentType(Instrument.InstrumentType instrumentType);
}
