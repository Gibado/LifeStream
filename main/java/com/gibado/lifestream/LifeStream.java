package com.gibado.lifestream;

import com.gibado.lifestream.contributors.Contributor;
import com.gibado.lifestream.observers.Observer;
import com.gibado.lifestream.observers.SelectiveObserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Object that receives messages and data from contributors and notifies observers when this happens
 */
public class LifeStream {
    private int idIndex = 0;
    /** ID map for contributors */
    private Map<Contributor, Integer> idMap = new HashMap<>();
    /** List of observers */
    private List<Observer> observers = new ArrayList<>();
    /** Message details */
    private Object messageDetails = null;

    /**
     * Returns the number of contributors for this LifeStream
     * @return Returns the number of contributors for this LifeStream
     */
    public int getContributorSize() {
        return idMap.size();
    }

    /**
     * Returns the number of observers for this LifeStream
     * @return Returns the number of observers for this LifeStream
     */
    public int getObserverSize() {
        return observers.size();
    }

    /**
     * Returns the message details associated with the most recent post
     * @return Returns the message details associated with the most recent post
     */
    public Object getMessageDetails() {
        return messageDetails;
    }

    /**
     * Adds a LifeStreamNode to this LifeStream
     * @param node Node that must be a Contributor, Observer, or both
     */
    public void addLifeStreamNode(LifeStreamNode node) {
        if (node instanceof Contributor) {
            Contributor contributor = (Contributor) node;
            idMap.put(contributor, idIndex);
            contributor.setId(idIndex++);
        }
        if (node instanceof Observer) {
            observers.add((Observer) node);
        }
    }

    /**
     * Causes this LifeStream to forget the given LifeStreamNode
     * @param node LifeStreamNode to forget
     */
    public void removeLifeStreamNode(LifeStreamNode node) {
        observers.remove(node);
        idMap.remove(node);
    }

    /**
     * Triggers an update to the LifeStream, which will notify observers
     * @param messageType Message Type
     * @param messageDetails Object holding any additional data
     * @param contributor Contributor that is posting this update
     */
    public void update(String messageType, Object messageDetails, Contributor contributor) {
//        String newMessage = idMap.get(contributor) + " (" + messageType + "): " + messageDetails.toString();
//        this.messageType = messageType;
        this.messageDetails = messageDetails;
        for (Observer observer : observers) {
            if (!contributor.equals(observer)) {
                if (observer instanceof SelectiveObserver
                    && !((SelectiveObserver) observer).wantsUpdateType(messageType)) {
                    continue;
                }
                observer.onUpdate(messageType);
            }
        }
    }
}