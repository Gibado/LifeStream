package com.gibado.lifestream;

import com.gibado.lifestream.contributors.LifeStreamCalculator;
import com.gibado.lifestream.contributors.LifeStreamCounter;
import com.gibado.lifestream.observers.LifeStreamLogger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LifeStreamTest {
    private LifeStream lifeStream = new LifeStream();

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

        LifeStreamHelper.connect(lifeStream, logger);
        Assert.assertEquals(0, lifeStream.getContributorSize());
        Assert.assertEquals(1, lifeStream.getObserverSize());

        LifeStreamHelper.connect(lifeStream, counter);
        Assert.assertEquals(1, lifeStream.getContributorSize());
        Assert.assertEquals(1, lifeStream.getObserverSize());

        LifeStreamHelper.disconnect(lifeStream, counter);
        Assert.assertEquals(0, lifeStream.getContributorSize());
        Assert.assertEquals(1, lifeStream.getObserverSize());

        LifeStreamHelper.disconnect(lifeStream, logger);
        Assert.assertEquals(0, lifeStream.getContributorSize());
        Assert.assertEquals(0, lifeStream.getObserverSize());
    }

    @Test
    public void testCounter() {
        LifeStreamLogger logger = new LifeStreamLogger();
        LifeStreamCounter counter = new LifeStreamCounter();

        LifeStreamHelper.connect(lifeStream, logger);
        LifeStreamHelper.connect(lifeStream, counter);

        for(int i = 0; i < 5; i++) {
            counter.postUpdate();
        }
    }

    @Test
    public void testCounterAndCalculator() {
        LifeStreamLogger logger = new LifeStreamLogger();
        LifeStreamCounter counter = new LifeStreamCounter();
        LifeStreamCalculator calculator = new LifeStreamCalculator();

        LifeStreamHelper.connect(lifeStream, logger);
        LifeStreamHelper.connect(lifeStream, counter);
        LifeStreamHelper.connect(lifeStream, calculator);

        Assert.assertEquals(2, lifeStream.getContributorSize());
        Assert.assertEquals(2, lifeStream.getObserverSize());

        for(int i = 0; i < 5; i++) {
            counter.postUpdate();
        }
    }
}
