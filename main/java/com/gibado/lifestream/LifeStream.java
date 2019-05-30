package com.gibado.lifestream;

import com.gibado.lifestream.contributors.Contributor;
import com.gibado.lifestream.data.EventMessage;
import com.gibado.lifestream.data.EventMessageTag;
import com.gibado.lifestream.data.LifeTree;
import com.gibado.lifestream.observers.Observer;

import java.util.ArrayList;
import java.util.List;

public class LifeStream {
    private LifeTree lifeTree = new LifeTree();
    private EventStream eventStream = new EventStream(lifeTree);
    private List<Contributor> contributorList = new ArrayList<>();

    /**
     * Triggers an update to the LifeStream, which will notify observers
     * @param message Message to post to the stream
     */
    public void update(EventMessage message) {
        eventStream.addMessage(message);
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
        return eventStream.getObserverSize();
    }

    public int getEventMessageStorageSize() { return this.lifeTree.size(); }

    /**
     * Returns the message details associated with the most recent post
     * @return Returns the message details associated with the most recent post
     */
    public Object getMessageDetails(EventMessageTag tag) {
        return getMessage(tag).getDetails();
    }

    public EventMessage getMessage(EventMessageTag tag) {
        return lifeTree.get(tag);
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
            eventStream.addObserver((Observer) node);
        }
    }

    /**
     * Causes this LifeStream to forget the given LifeStreamNode
     * @param node LifeStreamNode to forget
     */
    public void removeLifeStreamNode(LifeStreamNode node) {
        if (node instanceof Observer) {
            eventStream.removeObserver((Observer) node);
        }
        contributorList.remove(node);
    }
}
