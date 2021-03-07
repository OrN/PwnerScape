package com.pwns.server.plugins.npcs.taverly;

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
import com.pwns.server.plugins.Functions;

public class HelemosShop  implements ShopInterface,
        TalkToNpcListener, TalkToNpcExecutiveListener {

	private static final int HELEMOS = 269;
	private final Shop shop = new Shop(false, 60000, 100, 55,3, new Item(594, 1));

	@Override
	public boolean blockTalkToNpc(final Player p, final Npc n) {
		return n.getID() == HELEMOS;
	}

	@Override
	public Shop[] getShops() {
		return new Shop[] { shop };
	}

	@Override
	public boolean isMembers() {
		return true;
	}

	@Override
	public void onTalkToNpc(final Player p, final Npc n) {
		Functions.npcTalk(p, n, "Welcome to the hero's guild");
		final int option = Functions.showMenu(p, n, new String[] {
				"So do you sell anything here?", "So what can I do here?" });
		if (option == 0) {
			Functions.npcTalk(p, n, "Why yes we do run an exclusive shop for our members");
			p.setAccessingShop(shop);
			ActionSender.showShop(p, shop);
		} else if (option == 1) {
			Functions.npcTalk(p, n, "Look around there are all sorts of things to keep our members entertained");
		} 
	}
}
