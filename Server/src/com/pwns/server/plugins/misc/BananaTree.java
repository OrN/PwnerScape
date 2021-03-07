package com.pwns.server.plugins.misc;

import com.pwns.server.model.entity.GameObject;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.listeners.action.ObjectActionListener;
import com.pwns.server.plugins.listeners.executive.ObjectActionExecutiveListener;
import com.pwns.server.plugins.Functions;

public class BananaTree implements ObjectActionExecutiveListener,
        ObjectActionListener {

	@Override
	public void onObjectAction(GameObject obj, String command, Player p) {
		if(obj.getID() == 183) {
			p.message("you pick a banana");
			Functions.addItem(p, 249, 1);
			if(!p.getCache().hasKey("banana_pick")) {
				p.getCache().set("banana_pick", 1);
			} else {
				int bananaCount = p.getCache().getInt("banana_pick");
				p.getCache().set("banana_pick", (bananaCount + 1));
				if(bananaCount >= 4) {
					Functions.replaceObjectDelayed(obj, 60000 * 8, 184); // 8 minutes respawn time.
					p.getCache().remove("banana_pick");
				}
			}

		}
		if(obj.getID() == 184) {
			p.message("there are no bananas left on the tree");
		}
	}

	@Override
	public boolean blockObjectAction(GameObject obj, String command, Player p) {
		return obj.getID() == 183 || obj.getID() == 184;
	}

}
