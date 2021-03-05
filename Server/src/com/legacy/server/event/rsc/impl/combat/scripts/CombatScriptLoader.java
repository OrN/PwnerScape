package com.legacy.server.event.rsc.impl.combat.scripts;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.legacy.server.model.world.World;
import com.legacy.server.util.ClassEnumerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.legacy.server.model.entity.Mob;
import com.legacy.server.plugins.PluginHandler;

import static org.apache.logging.log4j.util.Unbox.box;

/**
 * 
 * @author n0m
 *
 */
public class CombatScriptLoader {
	
	/**
     * The asynchronous logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();

	private static final Map<String, CombatScript> combatScripts = new HashMap<String, CombatScript>();
	
	private static final Map<String, OnCombatStartScript> combatStartScripts = new HashMap<String, OnCombatStartScript>();

	public static void loadCombatScripts() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		List<Class<?>> combatClasses = ClassEnumerator.getPackageClasses(com.legacy.server.event.rsc.impl.combat.scripts.all.DragonFireBreath.class.getPackage());
		for(Class<?> c : combatClasses) {
			Object classInstance = c.newInstance();
			if(classInstance instanceof CombatScript) {
				CombatScript script = (CombatScript) classInstance;
				combatScripts.put(classInstance.getClass().getName(), script);
			}
			if(classInstance instanceof OnCombatStartScript) {
				OnCombatStartScript script = (OnCombatStartScript) classInstance;
				combatStartScripts.put(classInstance.getClass().getName(), script);
			}
		}

		LOGGER.info("Loaded {}", box(combatScripts.size()) + " combat scripts.");
		LOGGER.info("Loaded total of {}", box(combatStartScripts.size()) + " combat start scripts.");
	}
	
	public static void checkAndExecuteCombatScript(final Mob attacker, final Mob victim) {
		for(CombatScript script : combatScripts.values()) {
			if(script.shouldExecute(attacker, victim)) {
				script.executeScript(attacker, victim);
			}
		}
	}
	
	public static void checkAndExecuteOnStartCombatScript(final Mob attacker, final Mob victim) {
		try {
			for(OnCombatStartScript script : combatStartScripts.values()) {
				if(script.shouldExecute(attacker, victim)) {
					script.executeScript(attacker, victim);
				}
			}
		} catch(Throwable e) {
			LOGGER.catching(e);
		}
	}

	public static void init() {
		try {
			loadCombatScripts();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			LOGGER.catching(e);
		}
	}
}
