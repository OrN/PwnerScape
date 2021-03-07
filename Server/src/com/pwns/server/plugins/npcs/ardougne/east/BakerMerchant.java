package com.pwns.server.plugins.npcs.ardougne.east;

import static com.pwns.server.plugins.Functions.npcTalk;
import static com.pwns.server.plugins.Functions.showMenu;

import com.pwns.server.model.Shop;
import com.pwns.server.model.container.Item;
import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.net.rsc.ActionSender;
import com.pwns.server.plugins.ShopInterface;
import com.pwns.server.plugins.listeners.action.TalkToNpcListener;
import com.pwns.server.plugins.listeners.executive.TalkToNpcExecutiveListener;

public class BakerMerchant implements ShopInterface, TalkToNpcExecutiveListener, TalkToNpcListener {

	private final Shop shop = new Shop(false, 15000, 100, 80,2, new Item(138, 10), new Item(330, 3), new Item(336, 8));

	@Override
	public void onTalkToNpc(Player p, Npc n) {
		npcTalk(p, n, "Good day " + (p.isMale() ? "Sir" : "Madame"),
				"Would you like ze nice freshly baked bread",
				"Or perhaps a nice piece of cake");
		int menu = showMenu(p, n, "Lets see what you have", "No thankyou");
		if(menu == 0) {
			p.setAccessingShop(shop);
			ActionSender.showShop(p, shop);
		} 
	}

	// WHEN STEALING AND CAUGHT BY A MERCHANT ("Hey thats mine");
	// Delay player busy (3000); after stealing and Npc shout out to you.

	@Override
	public boolean blockTalkToNpc(Player p, Npc n) {
		return n.getID() == 325;
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
