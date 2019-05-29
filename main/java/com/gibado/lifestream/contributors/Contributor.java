package com.gibado.lifestream.contributors;

import com.gibado.lifestream.LifeStreamNode;

/**
 * Defines methods needed to contribute data to the LifeStream
 */
public interface Contributor extends LifeStreamNode {
    /**
     * posts a message string to the LifeStream
     */
    void postUpdate();

    /**
     * Returns a name representative for this contributor
     * @return Returns a name representative for this contributor
     */
    String getName();
}
