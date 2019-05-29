package com.gibado.lifestream.observers;

import com.gibado.lifestream.LifeStreamNode;

/**
 * Defines methods for reacting to something posted to a LifeStream
 */
public interface Observer extends LifeStreamNode {

    /**
     * Defines what actions should take place when a new message with the given type is posted
     * @param messageType New message type
     */
    public void onUpdate(String messageType);
}
