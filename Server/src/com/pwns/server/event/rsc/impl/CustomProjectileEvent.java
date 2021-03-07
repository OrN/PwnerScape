package com.pwns.server.event.rsc.impl;

import com.pwns.server.model.entity.Mob;
import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;

public abstract class CustomProjectileEvent extends ProjectileEvent {

	public CustomProjectileEvent(Mob caster, Mob opponent, int type) {
		super(caster, opponent, 0, type);
	}

	@Override
	public void action() {
		if(!canceled) {
			doSpell();
			if(opponent.isNpc() && caster.isPlayer())
				((Npc) opponent).setChasing((Player) caster);
		}
	}

	public abstract void doSpell();
}
