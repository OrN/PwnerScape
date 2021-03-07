package com.pwns.server.plugins.npcs.khazard;

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

public final class FishingTrawlerGeneralStore implements ShopInterface,
        TalkToNpcExecutiveListener, TalkToNpcListener {

	private final Shop shop = new Shop(false, 3000, 130, 40, 3,
			new Item(156, 5), new Item(135, 3), new Item(140, 2),
			new Item(144, 2), new Item(50, 2), new Item(166, 2),
			new Item(167, 2), new Item(168, 5), new Item(237, 30),
			new Item(136, 30), new Item(1282, 30), new Item(785, 30));

	@Override
	public void onTalkToNpc(Player p, final Npc n) {

		Functions.npcTalk(p, n, "Can I help you at all");

		String[] options = new String[] { "Yes please. What are you selling?",
				"No thanks" };
		int option = Functions.showMenu(p,n, options);
		switch (option) {
		case 0:
			Functions.npcTalk(p, n, "Take a look");
			p.setAccessingShop(shop);
			ActionSender.showShop(p, shop);
			break;
		}
	}

	@Override
	public boolean blockTalkToNpc(Player p, Npc n) {
		return n.getID() == 391;
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
