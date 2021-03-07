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

public class JatixHerblawShop  implements ShopInterface,
        TalkToNpcListener, TalkToNpcExecutiveListener {

	private final int JATIX = 230;
	private final Shop shop = new Shop(false, 10000, 100, 70, 2,
			new Item(465, 50), new Item(468, 3), new Item(270, 50));

	@Override
	public boolean blockTalkToNpc(final Player p, final Npc n) {
		return n.getID() == JATIX;
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
		Functions.npcTalk(p, n, "Hello how can I help you?");
		final int option = Functions.showMenu(p, n, new String[] {
				"What are you selling?", "You can't, I'm beyond help",
				"I'm okay, thankyou" });

		if (option == 0) {
			p.setAccessingShop(shop);
			ActionSender.showShop(p, shop);
		}

	}

}
