package com.gibado.lifestream.contributors;

import com.gibado.lifestream.LifeStreamNode;
import com.gibado.lifestream.observers.SelectiveObserver;

/**
 * Listens for a message that's a number and then returns it's doubled and halved values
 */
public class LifeStreamCalculator extends LifeStreamNode implements Contributor, SelectiveObserver {
    private int id = 0;
    private int doubled = 0;
    private double halved = 0.0;

    @Override
    public void postUpdate() {
        lifeStream.update("String", "Doubled = " + doubled, this);
        lifeStream.update("String", "Halved = " + halved, this);
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void onUpdate(String messageType) {
        Integer messageNumber = (Integer) lifeStream.getMessageDetails();
        if (messageNumber != null) {
            this.doubled = messageNumber * 2;
            this.halved = messageNumber * 0.5;
            this.postUpdate();
        }
    }

    @Override
    public boolean wantsUpdateType(String messageType) {
        return "integer".equalsIgnoreCase(messageType);
    }
}
