package com.gibado.lifestream.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Holds event messages in case someone wants to look at them.
 * TODO need to add in destroying the messages
 */
public class LifeTree {
    private Map<Long, EventMessage> dataMap = new HashMap<>();
//    private Thread destroyer = null;
    private Destroyer destroyer = new Destroyer();

    protected Map<Long, EventMessage> getDataMap() {
        return dataMap;
    }

    public void add(EventMessage message) {
        message.setDeathTime(System.nanoTime() + (message.getTimeToLive() * 1000000000));
        dataMap.put(message.getTag().getKey(), message);
    }

    public EventMessage get(EventMessageTag tag) {
        return get(tag.getKey());
    }

    public EventMessage get(Long key) {
        checkDestroyer();
        return dataMap.get(key);
    }

    public int size() {
        checkDestroyer();
        return dataMap.size();
    }

    /** TODO Change this to running on a separate thread at some point */
    private void checkDestroyer() {
//        if (this.destroyer == null) {
//            this.destroyer = new Thread(new Destroyer());
//            destroyer.run();
//        } else if (!destroyer.isAlive()) {
//            this.destroyer = new Thread(new Destroyer());
//            destroyer.run();
//        }
        destroyer.destroy();
    }

    private class Destroyer implements Runnable {

        @Override
        public void run() {
            while (size() > 0) {
                this.destroy();
            }
        }

        public void destroy() {
            List<Long> removeKeys = new ArrayList<>();
            for (Map.Entry<Long, EventMessage> entry : dataMap.entrySet()) {
                if (entry.getValue().getDeathTime() <= System.nanoTime()) {
                    removeKeys.add(entry.getKey());
                }
            }
            for (Long key : removeKeys) {
                dataMap.remove(key);
            }
        }
    }
}
