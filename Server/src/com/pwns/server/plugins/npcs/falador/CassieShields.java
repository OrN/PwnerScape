package com.pwns.server.plugins.npcs.falador;

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

public final class CassieShields  implements ShopInterface,
        TalkToNpcExecutiveListener, TalkToNpcListener {

	public static final int npcid = 101;

	private final Shop shop = new Shop(false, 25000, 100, 60, 2,
			new Item(4, 5), new Item(124, 3), new Item(128, 3),
			new Item(3, 2), new Item(2, 0), new Item(125, 0),
			new Item(129, 0), new Item(126, 0));

	@Override
	public boolean blockTalkToNpc(final Player p, final Npc n) {
		return n.getID() == npcid;
	}

	@Override
	public Shop[] getShops() {
		return new Shop[] { shop };
	}

	@Override
	public boolean isMembers() {
		return false;
	}

	@Override
	public void onTalkToNpc(final Player p, final Npc n) {
		if (n.getID() == npcid) {
			Functions.playerTalk(p, n, "What wares are you selling?");
			Functions.npcTalk(p,n, "I buy and sell shields", "Do you want to trade?");
			int option = Functions.showMenu(p,n, "Yes please", "No thanks");
			if (option == 0) {
				p.setAccessingShop(shop);
				ActionSender.showShop(p, shop);
			}
		}
	}

}