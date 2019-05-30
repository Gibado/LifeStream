package com.gibado.lifestream;

import com.gibado.lifestream.contributors.LifeStreamCalculator;
import com.gibado.lifestream.contributors.LifeStreamCounter;
import com.gibado.lifestream.data.EventMessage;
import com.gibado.lifestream.observers.Historian;
import com.gibado.lifestream.observers.LifeStreamLogger;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

        assertEquals(0, lifeStream.getContributorSize());
        assertEquals(0, lifeStream.getObserverSize());

        LifeStreamHelper.connect(lifeStream, logger);
        assertEquals(0, lifeStream.getContributorSize());
        assertEquals(1, lifeStream.getObserverSize());

        LifeStreamHelper.connect(lifeStream, counter);
        assertEquals(1, lifeStream.getContributorSize());
        assertEquals(1, lifeStream.getObserverSize());

        LifeStreamHelper.disconnect(lifeStream, counter);
        assertEquals(0, lifeStream.getContributorSize());
        assertEquals(1, lifeStream.getObserverSize());

        LifeStreamHelper.disconnect(lifeStream, logger);
        assertEquals(0, lifeStream.getContributorSize());
        assertEquals(0, lifeStream.getObserverSize());

        assertEquals(0, lifeStream.getEventMessageStorageSize());
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

        assertEquals(5, lifeStream.getEventMessageStorageSize());
    }

    @Test
    public void testCounterAndCalculator() {
        LifeStreamLogger logger = new LifeStreamLogger();
        LifeStreamCounter counter = new LifeStreamCounter();
        LifeStreamCalculator calculator = new LifeStreamCalculator();

        LifeStreamHelper.connect(lifeStream, logger);
        LifeStreamHelper.connect(lifeStream, counter);
        LifeStreamHelper.connect(lifeStream, calculator);

        assertEquals(2, lifeStream.getContributorSize());
        assertEquals(2, lifeStream.getObserverSize());

        for(int i = 0; i < 5; i++) {
            counter.postUpdate();
        }

        assertEquals(15, lifeStream.getEventMessageStorageSize());
    }

    @Test
    public void testHistorian() {
        LifeStreamLogger logger = new LifeStreamLogger();
        LifeStreamCounter counter = new LifeStreamCounter();
        Historian historian = new Historian();

        LifeStreamHelper.connect(lifeStream, logger);
        LifeStreamHelper.connect(lifeStream, counter);
        LifeStreamHelper.connect(lifeStream, historian);

        for(int i = 0; i < 5; i++) {
            counter.postUpdate();
        }

        assertEquals(5, lifeStream.getEventMessageStorageSize());

        List<EventMessage> messages = historian.getMessages();
        int index = 0;
        for (EventMessage message : messages) {
            assertEquals(counter, message.getAuthor());
            assertEquals("Integer", message.getTag().getType());
            Object details = message.getDetails();
            assertTrue(details instanceof Integer);
            assertEquals(index++, ((Integer) details).intValue());
        }
    }
}
