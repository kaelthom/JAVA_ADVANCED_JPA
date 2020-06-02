package com.java_advanced_ajc;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class MediaId implements Serializable {
    private String name;
    private MediaType mediaType;

    public MediaId() {
    }

    public MediaId(String name, MediaType mediaType) {
        this.name = name;
        this.mediaType = mediaType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }
}
