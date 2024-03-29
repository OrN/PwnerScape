package com.pwns.server.event.rsc.impl.combat.scripts.all;

import com.pwns.server.event.rsc.impl.combat.scripts.CombatScript;
import com.pwns.server.model.Skills;
import com.pwns.server.model.entity.Mob;
import com.pwns.server.model.entity.npc.Npc;

/**
 * 
 * @author n0m
 *
 */
public class TutorialIslandScriptIGuess implements CombatScript {

	@Override
	public void executeScript(Mob attacker, Mob victim) {
		victim.getSkills().setLevel(Skills.HITPOINTS, victim.getSkills().getLevel(Skills.HITPOINTS) + 3);
	}

	@Override
	public boolean shouldExecute(Mob attacker, Mob victim) {
		if(attacker.isNpc()) {
			if (((Npc) attacker).getID() == 473 && victim.getSkills().getLevel(Skills.HITPOINTS) <= 3) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean shouldCombatStop() {
		return false;
	}

}
