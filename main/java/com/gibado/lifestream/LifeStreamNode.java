package com.gibado.lifestream;

/**
 * An object that can connect to a LifeStream
 */
public abstract class LifeStreamNode {
    protected LifeStream lifeStream;

    /**
     * Connects this object to a LifeStream
     * @param lifeStream LifeStream to attach to
     */
    public void connectTo(LifeStream lifeStream) {
        this.lifeStream = lifeStream;
        lifeStream.addLifeStreamNode(this);
    }

    /**
     * Disconnects this object from it's associated LifeStream
     */
    public void disconnect() {
        this.lifeStream.removeLifeStreamNode(this);
    }
}
