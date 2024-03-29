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

public class Jiminua implements ShopInterface,
        TalkToNpcExecutiveListener, TalkToNpcListener {

	public static final int JIMINUA = 522;
	
	private final Shop shop = new Shop(true, 15000, 150, 50, 2,
			new Item(166, 2), new Item(465, 10), new Item(468, 3),
			new Item(135, 3), new Item(87, 3), new Item(156, 2),
			new Item(12, 5), new Item(15, 12), new Item(16, 10), 
			new Item(17, 10), new Item(132, 2), new Item(138, 10), 
			new Item(169, 10), new Item(211, 10), new Item(599, 10),
			new Item(773, 10), new Item(167, 10), new Item(168, 10),
			new Item(982, 50), new Item(983, 50), new Item(464, 50),
			new Item(1172, 50));

	@Override
	public void onTalkToNpc(Player p, Npc n) {
		if(n.getID() == JIMINUA) {
			Functions.npcTalk(p, n, "Welcome to the Jungle Store, Can I help you at all?");
			int menu = Functions.showMenu(p, n,
					"Yes please. What are you selling?",
					"No thanks");
			if(menu == 0) {
				Functions.npcTalk(p, n, "Take yourself a good look");
				p.setAccessingShop(shop);
				ActionSender.showShop(p, shop);
			}
		}
	}

	@Override
	public boolean blockTalkToNpc(Player p, Npc n) {
		if(n.getID() == JIMINUA) {
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
