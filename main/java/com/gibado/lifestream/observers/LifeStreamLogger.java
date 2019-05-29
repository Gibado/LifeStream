package com.gibado.lifestream.observers;

import com.gibado.lifestream.LifeStream;
import com.gibado.lifestream.data.EventMessageTag;

/**
 * A Observer that outputs all messages to System.out
 */
public class LifeStreamLogger implements Observer {
    private LifeStream lifeStream;

    @Override
    public void onUpdate(EventMessageTag messageTag) {
        String newMessage = "(" + messageTag.getType() + "): " + lifeStream.getMessageDetails(messageTag).toString();
        System.out.println(newMessage);
    }

    @Override
    public void setLifeStream(LifeStream lifeStream) {
        this.lifeStream = lifeStream;
    }
}
