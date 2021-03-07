package com.pwns.server.plugins.misc;

import static com.pwns.server.plugins.Functions.inArray;
import static com.pwns.server.plugins.Functions.sleep;

import com.pwns.server.model.entity.GameObject;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.listeners.action.WallObjectActionListener;
import com.pwns.server.plugins.listeners.executive.WallObjectActionExecutiveListener;
import com.pwns.server.plugins.Functions;

public class MagicGuildPortals implements WallObjectActionListener, WallObjectActionExecutiveListener {

	public static int[] MAGIC_PORTALS = { 147, 148, 149 };

	@Override
	public boolean blockWallObjectAction(GameObject obj, Integer click, Player player) {
		return Functions.inArray(obj.getID(), MAGIC_PORTALS);
	}

	@Override
	public void onWallObjectAction(GameObject obj, Integer click, Player p) {
		if(Functions.inArray(obj.getID(), MAGIC_PORTALS)) {
			p.message("you enter the magic portal");
			if(obj.getID() == MAGIC_PORTALS[0]) {
				p.teleport(212, 695);
			} else if(obj.getID() == MAGIC_PORTALS[1]) {
				p.teleport(511, 1452);
			} else if(obj.getID() == MAGIC_PORTALS[2]) {
				p.teleport(362, 1515);
			}
			Functions.sleep(600);
			Functions.displayTeleportBubble(p, p.getX(), p.getY(), false);
		}
	}
}
