package com.gibado.lifestream.contributors;

import com.gibado.lifestream.LifeStreamNode;

/**
 * Defines methods needed to contribute data to the LifeStream
 */
public interface Contributor extends LifeStreamNode {
    /**
     * posts a message string to the LifeStream
     */
    public void postUpdate();

    /**
     * Assigns this contributor an ID
     * @param id ID for this contributor
     */
    public void setId(int id);

    /**
     * Returns a name representative for this contributor
     * @return Returns a name representative for this contributor
     */
    public String getName();
}
