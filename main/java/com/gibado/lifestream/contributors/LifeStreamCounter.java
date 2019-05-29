package com.gibado.lifestream.contributors;

import com.gibado.lifestream.data.EventMessage;
import com.gibado.lifestream.LifeStream;

/**
 * Simple Contributor that posts an incrementing integer
 */
public class LifeStreamCounter implements Contributor {
    private LifeStream lifeStream;
    private Integer count = 0;

    @Override
    public void postUpdate() {
        lifeStream.update(new EventMessage("Integer", this.count++, this));
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
