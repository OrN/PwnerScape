package com.pwns.server;

import com.sun.org.apache.bcel.internal.Const;

public class TickTimer {
    private long currentUpdate;
    private long count;

    private static TickTimer instance = new TickTimer();

    public static TickTimer getInstance() {
        return instance;
    }

    public void start() {
        count = 0;
        currentUpdate = System.currentTimeMillis();
    }

    public long tickToMS(long count) {
        long currentTime = System.currentTimeMillis();
        long elapsed = currentTime - currentUpdate;
        long ms = (Constants.GameServer.GAME_TICK * count) - elapsed;
        return ms;
    }

    public long delayTick(long count) {
        long currentTime = System.currentTimeMillis();
        long elapsed = currentTime - currentUpdate;
        long timeOffset = (Constants.GameServer.GAME_TICK * count) - elapsed;
        if (timeOffset > 0) {
            try {
                Thread.sleep(timeOffset);
            } catch (Exception e) { e.printStackTrace(); }
        }
        return timeOffset;
    }

    public void delayThread() {
        try {
            Thread.sleep(10);
        } catch (Exception e) {}
    }

    public long process() {
        long ticks = 0;
        long currentTime = System.currentTimeMillis();
        long elapsed = currentTime - currentUpdate;
        if (elapsed >= Constants.GameServer.GAME_TICK) {
            ticks = elapsed / Constants.GameServer.GAME_TICK;
            currentUpdate += ticks * Constants.GameServer.GAME_TICK;
        }
        return ticks;
    }
}
