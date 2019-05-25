package com.gibado.lifestream.observers;

/**
 *  Defines extra methods for observers that don't care about all message types
 */
public interface SelectiveObserver extends Observer {
    /**
     * Identifies if this observer cares about this type of message
     * @param messageType Message type
     * @return Returns true if this observer should act on this message type
     */
    public boolean wantsUpdateType(String messageType);
}
