package com.gibado.lifestream;

import com.gibado.lifestream.contributors.LifeStreamCalculator;
import com.gibado.lifestream.contributors.LifeStreamCounter;
import com.gibado.lifestream.observers.LifeStreamLogger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LifeStreamTest {
    private LifeStream lifeStream = null;

    @Before
    public void setup() {
        lifeStream = new LifeStream();
    }

    @Test
    public void testConnection() {
        LifeStreamLogger logger = new LifeStreamLogger();
        LifeStreamCounter counter = new LifeStreamCounter();

        Assert.assertEquals(0, lifeStream.getContributorSize());
        Assert.assertEquals(0, lifeStream.getObserverSize());

        logger.connectTo(lifeStream);
        Assert.assertEquals(0, lifeStream.getContributorSize());
        Assert.assertEquals(1, lifeStream.getObserverSize());

        counter.connectTo(lifeStream);
        Assert.assertEquals(1, lifeStream.getContributorSize());
        Assert.assertEquals(1, lifeStream.getObserverSize());

        counter.disconnect();
        Assert.assertEquals(0, lifeStream.getContributorSize());
        Assert.assertEquals(1, lifeStream.getObserverSize());

        logger.disconnect();
        Assert.assertEquals(0, lifeStream.getContributorSize());
        Assert.assertEquals(0, lifeStream.getObserverSize());
    }

    @Test
    public void testCounter() {
        LifeStreamLogger logger = new LifeStreamLogger();
        LifeStreamCounter counter = new LifeStreamCounter();

        logger.connectTo(lifeStream);
        counter.connectTo(lifeStream);

        for(int i = 0; i < 5; i++) {
            counter.postUpdate();
        }
    }

    @Test
    public void testCounterAndCalculator() {
        LifeStreamLogger logger = new LifeStreamLogger();
        LifeStreamCounter counter = new LifeStreamCounter();
        LifeStreamCalculator calculator = new LifeStreamCalculator();

        logger.connectTo(lifeStream);
        counter.connectTo(lifeStream);
        calculator.connectTo(lifeStream);

        Assert.assertEquals(2, lifeStream.getContributorSize());
        Assert.assertEquals(2, lifeStream.getObserverSize());

        for(int i = 0; i < 5; i++) {
            counter.postUpdate();
        }
    }
}
