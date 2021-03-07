package com.pwns.server.plugins.npcs.varrock;

import static com.pwns.server.plugins.Functions.npcTalk;
import static com.pwns.server.plugins.Functions.showMenu;

import com.pwns.server.model.Shop;
import com.pwns.server.model.container.Item;
import com.pwns.server.model.entity.GroundItem;
import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.model.world.World;
import com.pwns.server.net.rsc.ActionSender;
import com.pwns.server.plugins.ShopInterface;
import com.pwns.server.plugins.listeners.action.PickupListener;
import com.pwns.server.plugins.listeners.action.TalkToNpcListener;
import com.pwns.server.plugins.listeners.executive.PickupExecutiveListener;
import com.pwns.server.plugins.listeners.executive.TalkToNpcExecutiveListener;
import com.pwns.server.plugins.Functions;

public final class TeaSeller implements ShopInterface,
        TalkToNpcExecutiveListener, TalkToNpcListener, PickupExecutiveListener,
        PickupListener {

	private final Shop shop = new Shop(false, 30000, 100, 60, 2, new Item(739,
			20));

	@Override
	public boolean blockPickup(final Player p, final GroundItem i) {
		return i.getID() == 1285;
	}

	@Override
	public boolean blockTalkToNpc(final Player p, final Npc n) {
		return n.getID() == 780;
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
	public void onPickup(final Player p, final GroundItem i) {
		if (i.getID() == 1285) {
			final Npc n = World.getWorld().getNpcById(780);
			if (n == null) {
				return;
			}
			n.face(p);
			Functions.npcTalk(p, n, "hey ! get your hands off that tea !",
					"that's for display purposes only",
					"Im not running a charity here !");
		}
	}

	@Override
	public void onTalkToNpc(final Player p, final Npc n) {
		Functions.npcTalk(p, n, "Greetings!",
				"Are you in need of refreshment ?");

		final String[] options = new String[] { "Yes please", "No thanks",
				"What are you selling?" };
		int option = Functions.showMenu(p,n, options);
		switch (option) {
		case 0:
			p.setAccessingShop(shop);
			ActionSender.showShop(p, shop);
			break;
		case 1:
			Functions.npcTalk(p, n, "Well if you're sure",
					"You know where to come if you do !");
			break;
		case 2:
			Functions.npcTalk(p, n, "Only the most delicious infusion",
					"Of the leaves of the tea plant",
					"Grown in the exotic regions of this world...",
					"Buy yourself a cup !");
			break;
		}
	}

}
