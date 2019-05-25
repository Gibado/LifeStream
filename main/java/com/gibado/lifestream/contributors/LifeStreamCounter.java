package com.gibado.lifestream.contributors;

import com.gibado.lifestream.LifeStreamNode;

/**
 * Simple Contributor that posts an incrementing integer
 */
public class LifeStreamCounter extends LifeStreamNode implements Contributor {
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
}
