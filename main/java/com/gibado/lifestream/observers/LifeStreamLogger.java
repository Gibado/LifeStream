package com.gibado.lifestream.observers;

import com.gibado.lifestream.LifeStream;

/**
 * A Observer that outputs all messages to System.out
 */
public class LifeStreamLogger implements Observer {
    private LifeStream lifeStream;

    @Override
    public void onUpdate(String messageType) {
        String newMessage = "(" + messageType + "): " + lifeStream.getMessageDetails().toString();
        System.out.println(newMessage);
    }

    @Override
    public void setLifeStream(LifeStream lifeStream) {
        this.lifeStream = lifeStream;
    }
}
