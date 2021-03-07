package com.pwns.server.plugins.misc;

import com.pwns.server.model.entity.GameObject;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.listeners.action.ObjectActionListener;
import com.pwns.server.plugins.listeners.executive.ObjectActionExecutiveListener;
import com.pwns.server.plugins.Functions;

public class PineappleTree implements ObjectActionExecutiveListener,
        ObjectActionListener {

	@Override
	public void onObjectAction(GameObject obj, String command, Player p) {
		if(obj.getID() == 430) {
			p.message("you pick a pineapple");
			Functions.addItem(p, 861, 1);
			if(!p.getCache().hasKey("pineapple_pick")) {
				p.getCache().set("pineapple_pick", 1);
			} else {
				int pineappleCount = p.getCache().getInt("pineapple_pick");
				p.getCache().set("pineapple_pick", (pineappleCount + 1));
				if(pineappleCount >= 4) {
					Functions.replaceObjectDelayed(obj, 60000 * 8, 431); // 8 minutes respawn time.
					p.getCache().remove("pineapple_pick");
				}
			}

		}
		if(obj.getID() == 431) {
			p.message("there are no pineapples left on the tree");
		}
		
	}

	@Override
	public boolean blockObjectAction(GameObject obj, String command, Player player) {
		if(obj.getID() == 431 || obj.getID() == 430) {
			return true;
		}
		return false;
	}

}
