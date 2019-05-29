package com.gibado.lifestream.data;

import java.util.HashMap;
import java.util.Map;

/**
 * Holds event messages in case someone wants to look at them.
 * TODO need to add in destroying the messages
 */
public class LifeTree {
    private Map<Long, EventMessage> dataMap = new HashMap<>();

    public void add(EventMessage message) {
        dataMap.put(message.getTag().getKey(), message);
    }

    public EventMessage get(EventMessageTag tag) {
        return get(tag.getKey());
    }

    public EventMessage get(Long key) {
        return dataMap.get(key);
    }

    public int size() {
        return dataMap.size();
    }
}
