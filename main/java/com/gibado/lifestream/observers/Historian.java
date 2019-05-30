package com.gibado.lifestream.observers;

import com.gibado.lifestream.LifeStream;
import com.gibado.lifestream.data.EventMessage;
import com.gibado.lifestream.data.EventMessageTag;

import java.util.LinkedList;
import java.util.List;

/**
 * Observer that holds onto each message that's been posted.
 */
public class Historian implements Observer {
    private List<EventMessage> messages = new LinkedList<>();
    private LifeStream lifeStream;

    @Override
    public void onUpdate(EventMessageTag messageTag) {
        messages.add(lifeStream.getMessage(messageTag));
    }

    @Override
    public void setLifeStream(LifeStream lifeStream) {
        this.lifeStream = lifeStream;
    }

    /**
     * Returns the list of messages that have been posted in the order they
     * have been posted.
     * @return Returns the list of messages that have been posted in the order they
     *      have been posted.
     */
    public List<EventMessage> getMessages() {
        return messages;
    }
}
