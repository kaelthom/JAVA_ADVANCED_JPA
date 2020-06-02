package com.java_advanced_ajc;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@IdClass(MediaId.class)
public class Media {
    @Id
    private String name;
    @Id
    private MediaType mediaType;
    @Temporal(TemporalType.TIMESTAMP)
    private Date releaseDate;

    @ManyToOne
    private Artist artist;

}
