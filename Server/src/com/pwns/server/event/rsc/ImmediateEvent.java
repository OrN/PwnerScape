package com.pwns.server.event.rsc;

import com.pwns.server.model.entity.Mob;

public abstract class ImmediateEvent extends GameTickEvent {

	public ImmediateEvent() {
		super(null, 0);
		setImmediate(true);
	}
	
	public ImmediateEvent(Mob mob) {
		super(mob, 0);
		setImmediate(true);
	}
	
	@Override
	public void run() {
		action();
		stop();
	}

	public abstract void action();
}
