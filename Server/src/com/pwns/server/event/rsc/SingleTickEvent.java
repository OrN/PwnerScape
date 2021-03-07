package com.pwns.server.event.rsc;

import com.pwns.server.model.entity.Mob;

public abstract class SingleTickEvent extends GameTickEvent {
	
    public SingleTickEvent(Mob caster, int ticks) {
		super(caster, ticks);
	}

    public abstract void action();

    public void run() {
        action();
        stop();
    }
}
