package com.gibado.lifestream.data;

/**
 * Holds information needed to retrieve EventMessage from the LifeStream
 */
public class EventMessageTag {
    private final String type;
    private final Long key;

    public EventMessageTag(String type) {
        this.type = type;
        this.key = System.nanoTime();
    }

    public String getType() {
        return type;
    }

    public Long getKey() {
        return key;
    }
}
