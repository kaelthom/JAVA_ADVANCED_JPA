package com.java_advanced_ajc;

import javax.persistence.*;
import java.util.List;

@Entity
public class Instrument {

    @Id
    private long id ;

    private String name;

    @Enumerated(EnumType.STRING)
    private InstrumentType instrumentType;

    enum InstrumentType {
        WIND,
        WOODWIND,
        BRASS,
        PERCUSSION,
        STRING
    }

    @OneToMany(mappedBy = "favoriteInstrument")
    List<Artist> artistsForWhichItsFavorite;

    @ManyToMany(mappedBy = "playableInstruments")
    List<Artist> artistsWhichCcanPlay;
}
