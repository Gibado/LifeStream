package com.gibado.lifestream.contributors;

import com.gibado.lifestream.LifeStream;

/**
 * Simple Contributor that posts an incrementing integer
 */
public class LifeStreamCounter implements Contributor {
    private LifeStream lifeStream;
    private int id = 0;
    private Integer count = 0;

    @Override
    public void postUpdate() {
        lifeStream.update("Integer", this.count++, this);
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return "Life Stream Counter";
    }

    @Override
    public void setLifeStream(LifeStream lifeStream) {
        this.lifeStream = lifeStream;
    }
}
