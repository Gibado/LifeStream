package com.gibado.lifestream.observers;

import com.gibado.lifestream.LifeStreamNode;
import com.gibado.lifestream.data.EventMessageTag;

/**
 * Defines methods for reacting to something posted to a LifeStream
 */
public interface Observer extends LifeStreamNode {

    /**
     * Defines what actions should take place when a new message with the given type is posted
     * @param messageTag New message tag holding information needed to identify and access the message
     */
    void onUpdate(EventMessageTag messageTag);
}
