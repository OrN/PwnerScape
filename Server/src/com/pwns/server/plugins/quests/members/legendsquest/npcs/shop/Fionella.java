package com.pwns.server.plugins.quests.members.legendsquest.npcs.shop;

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

public class Fionella implements ShopInterface, TalkToNpcExecutiveListener, TalkToNpcListener {

	private final Shop shop = new Shop(true, 20000, 155, 55, 13,
			new Item(370, 2), new Item(257, 5), new Item(1263, 1),
			new Item(474, 3), new Item(640, 50));

	public static final int FIONELLA = 788;

	@Override
	public void onTalkToNpc(Player p, Npc n) {
		if(n.getID() == FIONELLA) {
			npcTalk(p, n, "Can I help you at all?");
			int menu = showMenu(p, n,
					"Yes please. What are you selling?",
					"No thanks");
			if(menu == 0) {
				npcTalk(p, n, "Take a look");
				p.setAccessingShop(shop);
				ActionSender.showShop(p, shop);
			}
		}
	}

	@Override
	public boolean blockTalkToNpc(Player p, Npc n) {
		if(n.getID() == FIONELLA) {
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