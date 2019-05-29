package com.gibado.lifestream.contributors;

import com.gibado.lifestream.data.EventMessage;
import com.gibado.lifestream.LifeStream;
import com.gibado.lifestream.data.EventMessageTag;
import com.gibado.lifestream.observers.SelectiveObserver;

/**
 * Listens for a message that's a number and then returns it's doubled and halved values
 */
public class LifeStreamCalculator implements Contributor, SelectiveObserver {
    private LifeStream lifeStream;
    private int doubled = 0;
    private double halved = 0.0;

    @Override
    public void postUpdate() {
        lifeStream.update(new EventMessage("String", "Doubled = " + doubled, this));
        lifeStream.update(new EventMessage("String", "Halved = " + halved, this));
    }

    @Override
    public String getName() {
        return "Life Stream Calculator";
    }

    @Override
    public void onUpdate(EventMessageTag messageTag) {
        Integer messageNumber = (Integer) lifeStream.getMessageDetails(messageTag);
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

    @Override
    public void setLifeStream(LifeStream lifeStream) {
        this.lifeStream = lifeStream;
    }
}
