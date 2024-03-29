package com.pwns.server.plugins.npcs.gutanoth;

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

public class OgreTrader implements ShopInterface, TalkToNpcListener,
TalkToNpcExecutiveListener {

	private final Shop shop = new Shop(false, 15000, 130, 40, 3,
			new Item(135, 3), 
			new Item(140, 2), 
			new Item(144, 2), 
			new Item(21, 2), 
			new Item(166, 2), 
			new Item(167, 2), 
			new Item(168, 5),
			new Item(1263, 10));

	@Override
	public boolean blockTalkToNpc(Player p, Npc n) {
		return n.getID() == 687;
	}

	@Override
	public void onTalkToNpc(Player p, Npc n) {
		npcTalk(p,n, "What the human be wantin'");
		int menu = showMenu(p,n,
				"Can I see what you are selling ?",
				"I don't need anything");
		if(menu == 0) {
			npcTalk(p,n, "I suppose so...");
			p.setAccessingShop(shop);
			ActionSender.showShop(p, shop);
		} else if(menu == 1) {
			npcTalk(p,n, "As you wish");
		}
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
