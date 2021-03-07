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

public final class Tailor  implements ShopInterface,
        TalkToNpcExecutiveListener, TalkToNpcListener {

	private final Shop shop = new Shop(false, 30000, 130, 40,2, new Item(192,
			0), new Item(185, 3), new Item(512, 1), new Item(541, 3),
			new Item(146, 3), new Item(39, 3), new Item(43, 100),
			new Item(16, 10), new Item(17, 10), new Item(807, 3),
			new Item(808, 3), new Item(191, 1), new Item(194, 5),
			new Item(195, 3), new Item(187, 2), new Item(183, 4),
			new Item(609, 3));

	@Override
	public boolean blockTalkToNpc(final Player p, final Npc n) {
		return n.getID() == 501;
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
		Functions.npcTalk(p, n, "Now you look like someone who goes to a lot of fancy dress parties");
		Functions.playerTalk(p, n,"Errr... what are you saying exactly?");
		Functions.npcTalk(p, n, "I'm just saying that perhaps you would like to peruse my selection of garments");
		int opt = Functions.showMenu(p,n, "I think I might leave the perusing for now thanks",
				"OK,lets see what you've got then" );
		if(opt == 1) {
			p.setAccessingShop(shop);
			ActionSender.showShop(p, shop);
		}
	}

}
