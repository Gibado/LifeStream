package com.gibado.lifestream;

import com.gibado.lifestream.contributors.Contributor;

/**
 * An Object that encapsulates an event message.  These are meant to be readonly.
 */
public class EventMessage {
    private final Contributor author;
    private final Object details;
    private final String type;
    private final long timeToLive;

    public EventMessage(String type, Object details) {
        this(type, details, null);
    }

    public EventMessage(String type, Object details, Contributor contributor) {
        this(type, details, contributor, 0);
    }

    public EventMessage(String type, Object details, Contributor contributor, long timeToLive) {
        this.type = type;
        this.details = details;
        this.author = contributor;
        this.timeToLive = timeToLive;
    }

    public Contributor getAuthor() {
        return this.author;
    }

    public Object getDetails() {
        return this.details;
    }

    public String getType() {
        return type;
    }

    public long getTimeToLive() {
        return timeToLive;
    }
}
