package com.pwns.server.plugins.npcs.edgeville;

import static com.pwns.server.plugins.Functions.npcTalk;
import static com.pwns.server.plugins.Functions.playerTalk;
import static com.pwns.server.plugins.Functions.showMenu;

import com.pwns.server.Constants.Quests;
import com.pwns.server.model.Shop;
import com.pwns.server.model.container.Item;
import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.net.rsc.ActionSender;
import com.pwns.server.plugins.ShopInterface;
import com.pwns.server.plugins.listeners.action.TalkToNpcListener;
import com.pwns.server.plugins.listeners.executive.TalkToNpcExecutiveListener;

public class OziachsRunePlateShop  implements ShopInterface,
TalkToNpcListener, TalkToNpcExecutiveListener {

	private final Shop shop = new Shop(false, 30000, 100, 60, 2, new Item(401,
			1));

	@Override
	public boolean blockTalkToNpc(final Player p, final Npc n) {
		return n.getID() == 187 && p.getQuestStage(Quests.DRAGON_SLAYER) == -1;
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
		playerTalk(p, n, "I have slain the dragon");
		npcTalk(p, n, "Well done");
		final int option = showMenu(p, n, new String[] {
				"Can I buy a rune plate mail body now please?", "Thank you" });
		if (option == 0) {
			p.setAccessingShop(shop);
			ActionSender.showShop(p, shop);
		} 
	}
}
