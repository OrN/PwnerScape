package com.pwns.server.plugins.misc;

import static com.pwns.server.plugins.Functions.message;
import static com.pwns.server.plugins.Functions.movePlayer;
import static com.pwns.server.plugins.Functions.showMenu;

import com.pwns.server.model.entity.GameObject;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.listeners.action.ObjectActionListener;
import com.pwns.server.plugins.listeners.executive.ObjectActionExecutiveListener;
import com.pwns.server.plugins.Functions;

public class MagicalPool implements ObjectActionListener, ObjectActionExecutiveListener {


	@Override
	public boolean blockObjectAction(GameObject obj, String command, Player player) {
		if(obj.getID() == 1166) { // mage arena gods place pool.
			return true;
		}
		if (obj.getID() == 1155) {
			return true;
		}
		return false;
	}

	@Override
	public void onObjectAction(GameObject obj, String command, Player player) {
		if (obj.getID() == 1155) {
			if (!player.canUsePool()) {
				player.message("You have just died, you must wait for "
										+ player.secondsUntillPool()
										+ " seconds before using this pool again");
				return;
			}
			while (System.currentTimeMillis()
					- player.getLastMoved() < 10000
					&& player.getLocation().inWilderness()) {
				player.message("You must stand still for 10 seconds before using portal");
				return;
			}
			while (System.currentTimeMillis()
					- player.getCombatTimer() < 10000
					&& player.getLocation().inWilderness()) {
				player.message("You must be out of combat for 10 seconds before using portal");
				return;
			}
			int option = Functions.showMenu(player, "Edgeville", "Varrock",
					"Castle (dangerous)", "Graveyard (dangerous)", "Hobgoblins (dangerous)", "Altar (dangerous)",
					"Dragon Maze (dangerous)", "Mage Arena (dangerous)", "Rune rocks (dangerous)", "Red dragons (dangerous)", "Further underground mage arena");
			
			if (option == 0) {
				player.teleport(215, 436);
			} else if (option == 1) {
				player.teleport(111, 505);
			} else if (option == 2) {
				player.teleport(272, 354);
			} else if (option == 3) {
				player.teleport(187, 297);
			} else if (option == 4) {
				player.teleport(218, 271);
			} else if (option == 5) {
				player.teleport(316, 199);
			} else if (option == 6) {
				player.teleport(271, 195);
			} else if (option == 7) {
				player.teleport(224, 110);
			} else if (option == 8) {
				player.teleport(264, 148);
			} else if(option == 9) {
				player.teleport(143, 173);
			} else if(option == 10) {
				Functions.movePlayer(player, 471, 3385);
				player.message("you are teleported further under ground");
			}

		}
		if (obj.getID() == 1166) {
			Functions.message(player, 1200, "you step into the sparkling water");
			Functions.message(player, 1200, "you feel energy rush through your veins");
			Functions.movePlayer(player, 447, 3373);
			player.message("you are teleported to kolodions cave");
		}
	}
}
