package com.gibado.lifestream;

import com.gibado.lifestream.data.EventMessage;
import com.gibado.lifestream.data.LifeTree;
import com.gibado.lifestream.observers.Observer;
import com.gibado.lifestream.observers.SelectiveObserver;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * An object that is in charge of accepting new EventMessages and
 * distributing them to Observers that care and placing those messages in the
 * LifeTree for reference later.
 */
public class EventStream {
    /** Queue that forces messages to be handled in FIFO order */
    private Queue<EventMessage> queue = new LinkedList<>();
    /** List of observers */
    private List<Observer> observerList = new ArrayList<>();
    /** LifeTree to store the messages in */
    private LifeTree lifeTree;

    public EventStream(LifeTree lifeTree) {
        this.lifeTree = lifeTree;
    }

    /**
     * Adds a message to be passed along to Observers
     * @param message
     */
    public void addMessage(EventMessage message) {
        queue.add(message);
        processMessage();
    }

    /**
     * Adds a observer to notify
     * @param observer Observer to notify about EventMessages
     */
    public void addObserver(Observer observer) {
        observerList.add(observer);
    }

    /**
     * Pulls a message off the queue and processes the message
     */
    private void processMessage() {
        if (queue.peek() != null) {
            EventMessage message = queue.poll();
            lifeTree.add(message);
            for (Observer observer : observerList) {
                if (!observer.equals(message.getAuthor())) {
                    if (observer instanceof SelectiveObserver
                            && !((SelectiveObserver) observer).wantsUpdateType(message.getTag().getType())) {
                        continue;
                    }
                    observer.onUpdate(message.getTag());
                }
            }
        }
    }

    /**
     * Removes an observer so that it is no longer notified
     * @param observer Observer who should no longer be notified
     */
    public void removeObserver(Observer observer) {
        observerList.remove(observer);
    }

    /**
     * Returns how many observers exist
     * @return Returns how many observers exist
     */
    public int getObserverSize() {
        return observerList.size();
    }
}
