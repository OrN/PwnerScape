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

public final class FlynnMaces implements ShopInterface,
        TalkToNpcExecutiveListener, TalkToNpcListener {

	public static final int npcid = 115;

	private final Shop shop = new Shop(false, 25000, 100, 60, 1,
			new Item(94, 5), new Item(0, 4), new Item(95, 4),
			new Item(96, 3), new Item(97, 2));

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
		Functions.npcTalk(p, n, "Hello do you want to buy or sell any maces?");

		int opt = Functions.showMenu(p, n, "No thanks", "Well I'll have a look anyway");
		if (opt == 1) {
			p.setAccessingShop(shop);
			ActionSender.showShop(p, shop);
		}

	}

}