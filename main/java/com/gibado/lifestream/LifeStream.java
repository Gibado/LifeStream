package com.gibado.lifestream;

import com.gibado.lifestream.contributors.Contributor;
import com.gibado.lifestream.data.EventMessage;
import com.gibado.lifestream.data.EventMessageTag;
import com.gibado.lifestream.data.LifeTree;
import com.gibado.lifestream.observers.Observer;
import com.gibado.lifestream.observers.SelectiveObserver;

import java.util.ArrayList;
import java.util.List;

public class LifeStream {
    private LifeTree lifeTree = new LifeTree();
    private List<Contributor> contributorList = new ArrayList<>();

    /** List of observers */
    private List<Observer> observers = new ArrayList<>();

    /**
     * Triggers an update to the LifeStream, which will notify observers
     * @param message Message to post to the stream
     */
    public void update(EventMessage message) {
        lifeTree.add(message);
        for (Observer observer : observers) {
            if (!message.getAuthor().equals(observer)) {
                if (observer instanceof SelectiveObserver
                        && !((SelectiveObserver) observer).wantsUpdateType(message.getTag().getType())) {
                    continue;
                }
                observer.onUpdate(message.getTag());
            }
        }
    }

    /**
     * Returns the number of contributors for this LifeStream
     * @return Returns the number of contributors for this LifeStream
     */
    public int getContributorSize() {
        return contributorList.size();
    }

    /**
     * Returns the number of observers for this LifeStream
     * @return Returns the number of observers for this LifeStream
     */
    public int getObserverSize() {
        return observers.size();
    }

    public int getEventMessageStorageSize() { return this.lifeTree.size(); }

    /**
     * Returns the message details associated with the most recent post
     * @return Returns the message details associated with the most recent post
     */
    public Object getMessageDetails(EventMessageTag tag) {
        return lifeTree.get(tag).getDetails();
    }

    /**
     * Adds a LifeStreamNode to this LifeStream
     * @param node Node that must be a Contributor, Observer, or both
     */
    public void addLifeStreamNode(LifeStreamNode node) {
        if (node instanceof Contributor) {
            contributorList.add((Contributor) node);
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
        contributorList.remove(node);
    }
}
