package com.pwns.server.event.rsc.impl.combat.scripts;

import com.pwns.server.model.entity.Mob;
/**
 * 
 * @author n0m
 *
 */
public interface OnCombatStartScript {
	
	public boolean shouldExecute(Mob attacker, Mob defender);
	public void executeScript(Mob attacker, Mob defender);
}
