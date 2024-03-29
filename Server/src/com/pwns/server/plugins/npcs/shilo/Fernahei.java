package com.pwns.server.plugins.npcs.shilo;

import com.pwns.server.model.Shop;
import com.pwns.server.model.container.Item;
import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.net.rsc.ActionSender;
import com.pwns.server.plugins.ShopInterface;
import com.pwns.server.plugins.listeners.action.TalkToNpcListener;
import com.pwns.server.plugins.listeners.executive.TalkToNpcExecutiveListener;
import com.pwns.server.plugins.Functions;

public class Fernahei implements ShopInterface,
        TalkToNpcExecutiveListener, TalkToNpcListener {

	public static final int FERNAHEI = 616;

	private final Shop shop = new Shop(true, 15000, 100, 70, 2,
			new Item(377, 5), new Item(378, 5), new Item(380, 200),
			new Item(381, 200), new Item(358, 0), new Item(363, 0),
			new Item(356, 0));

	@Override
	public void onTalkToNpc(Player p, Npc n) {
		if(n.getID() == FERNAHEI) {
			Functions.npcTalk(p, n, "Welcome to Fernahei's Fishing Shop Bwana!",
					"Would you like to see my items?");
			int menu = Functions.showMenu(p, n,
					"Yes please!",
					"No, but thanks for the offer.");
			if(menu == 0) {
				p.setAccessingShop(shop);
				ActionSender.showShop(p, shop);
			} else if(menu == 1) {
				Functions.npcTalk(p, n, "That's fine and thanks for your interest.");
			}
		}
	}

	@Override
	public boolean blockTalkToNpc(Player p, Npc n) {
		if(n.getID() == FERNAHEI) {
			return true;
		}
		return false;
	}

	@Override
	public Shop[] getShops() {
		return new Shop[] { shop };
	}

	@Override
	public boolean isMembers() {
		return true;
	}

}
