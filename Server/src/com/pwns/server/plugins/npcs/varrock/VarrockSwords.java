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

public final class VarrockSwords implements ShopInterface,
        TalkToNpcExecutiveListener, TalkToNpcListener {

	private final Shop shop = new Shop(false, 30000, 100, 60, 2,
			new Item(66, 5), new Item(1, 4), new Item(67, 4),
			new Item(424, 3), new Item(68, 3), new Item(69, 2),
			new Item(70, 4), new Item(71, 3), new Item(72, 3),
			new Item(425, 2), new Item(73, 2), new Item(74, 1),
			new Item(62, 10), new Item(28, 6), new Item(63, 5),
			new Item(423, 4), new Item(64, 3), new Item(65, 2));

	@Override
	public boolean blockTalkToNpc(final Player p, final Npc n) {
		if (n.getID() == 130 || n.getID() == 56) {
			if (p.getX() >= 133 && p.getX() <= 138 && p.getY() >= 522
					&& p.getY() <= 527) {
				return true;
			}
		}
		return false;
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
		if (n.getID() == 130 || n.getID() == 56
				&& p.getLocation().inBounds(133, 522, 138, 527)) {
			Functions.npcTalk(p, n, "Hello bold adventurer",
					"Can I interest you in some swords?");

			final String[] options = new String[] { "Yes please",
					"No, I'm OK for swords right now" };
			int option = Functions.showMenu(p,n, options);
			switch (option) {
			case 0:
				p.setAccessingShop(shop);
				ActionSender.showShop(p, shop);
				break;
			case 1:
				Functions.npcTalk(p, n, "Come back if you need any");
				break;
			}
		}
	}

}
