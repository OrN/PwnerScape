package com.pwns.server.event;

import com.pwns.server.model.entity.player.Player;
/**
 * Event which only executes once
 * @author n0m
 *
 */
public abstract class SingleEvent extends DelayedEvent {

    public SingleEvent(Player owner, int delay) {
        super(owner, delay);
    }

    public abstract void action();

    public void run() {
        action();
        super.matchRunning = false;
    }

}