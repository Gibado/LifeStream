package com.gibado.lifestream.contributors;

/**
 * Defines methods needed to contribute data to the LifeStream
 */
public interface Contributor {
    /**
     * posts a message string to the LifeStream
     */
    public void postUpdate();

    /**
     * Assigns this contributor an ID
     * @param id ID for this contributor
     */
    public void setId(int id);
}
