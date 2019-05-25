package com.gibado.lifestream.observers;

import com.gibado.lifestream.LifeStreamNode;

/**
 * A Observer that outputs all messages to System.out
 */
public class LifeStreamLogger extends LifeStreamNode implements Observer {

    @Override
    public void onUpdate(String messageType) {
        String newMessage = "(" + messageType + "): " + lifeStream.getMessageDetails().toString();
        System.out.println(newMessage);
    }
}
