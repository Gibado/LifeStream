package com.gibado.lifestream;

public class LifeStreamHelper {
    /**
     * Connects a LifeStreamNode to a LifeStream
     * @param lifeStream LifeStream to attach to
     * @param node LifeStreamNode to connect
     */
    public static void connect(LifeStream lifeStream, LifeStreamNode node) {
        node.setLifeStream(lifeStream);
        lifeStream.addLifeStreamNode(node);
    }

    /**
     * Disconnects a LifeStreamNode from the LifeStream
     * @param lifeStream LifeStream to notify about disconnection
     * @param node LifeStreamNode Node to remove from LifeStream
     */
    public static void disconnect(LifeStream lifeStream, LifeStreamNode node) {
        lifeStream.removeLifeStreamNode(node);
    }
}
