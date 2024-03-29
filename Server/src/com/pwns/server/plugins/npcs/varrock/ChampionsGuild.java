package com.pwns.server.plugins.npcs.varrock;

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

public final class ChampionsGuild  implements ShopInterface,
        TalkToNpcExecutiveListener, TalkToNpcListener {

	private final Shop scavvosShop = new Shop(false, 300000, 100, 60, 2,
			new Item(406, 1), new Item(402, 1), new Item(98, 1),
			new Item(400, 1), new Item(75, 1), new Item(397, 1));
	
	private final Shop valsShop = new Shop(false, 60000, 130, 40, 3, new Item(
			229, 2), new Item(230, 1), new Item(248, 1), new Item(120,
			1));

	@Override
	public boolean blockTalkToNpc(final Player p, final Npc n) {
		return n.getID() == 183 || n.getID() == 112;
	}

	@Override
	public Shop[] getShops() {
		return new Shop[] { scavvosShop, valsShop };
	}

	@Override
	public boolean isMembers() {
		return false;
	}

	@Override
	public void onTalkToNpc(final Player p, final Npc n) {
		switch (n.getID()) {
		case 183:
			Functions.npcTalk(p, n, "ello matey", "Want to buy some exciting new toys?");
			int options = Functions.showMenu(p,n, "No, toys are for kids", "Lets have a look then", "Ooh goody goody toys");
			if(options == 1 || options == 2) {
				p.setAccessingShop(scavvosShop);
				ActionSender.showShop(p, scavvosShop);
			}
			break;
		case 112: // valaerie
			Functions.npcTalk(p, n, "Hello there",
					"Want to have a look at what we're selling today?");

			int opt = Functions.showMenu(p,n, "Yes please", "No thank you");
			if(opt == 0) {
				p.setAccessingShop(valsShop);
				ActionSender.showShop(p, valsShop);
			}
			break;
		}
	}

}
