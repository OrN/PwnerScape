package com.pwns.server.plugins.npcs.alkharid;

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

public final class ZekeScimitars implements ShopInterface,
        TalkToNpcExecutiveListener, TalkToNpcListener {

	public static final int npcid = 84;

	private final Shop shop = new Shop(false, 25000, 100, 55, 2,
			new Item(82, 5), new Item(83, 3), new Item(84, 2),
			new Item(85, 1));

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
		Functions.npcTalk(p, n, "A thousand greetings " + ((p.isMale()) ? "sir"
				: "madam"));

		final String[] options = new String[] { "Do you want to trade?", "Nice cloak" };
		int option = Functions.showMenu(p, n, options);
		if (option == 0) {
			Functions.npcTalk(p, n, "Yes, certainly","I deal in scimitars");
			p.setAccessingShop(shop);
			ActionSender.showShop(p, shop);
		} else if(option == 1) {
			Functions.npcTalk(p, n, "Thank you");
		}
	}

}