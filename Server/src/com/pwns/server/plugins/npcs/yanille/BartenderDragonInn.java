package com.pwns.server.plugins.npcs.yanille;

import static com.pwns.server.plugins.Functions.hasItem;
import static com.pwns.server.plugins.Functions.npcTalk;
import static com.pwns.server.plugins.Functions.removeItem;

import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.listeners.action.TalkToNpcListener;
import com.pwns.server.plugins.listeners.executive.TalkToNpcExecutiveListener;
import com.pwns.server.plugins.menu.Menu;
import com.pwns.server.plugins.menu.Option;
import com.pwns.server.plugins.Functions;

public final class BartenderDragonInn implements TalkToNpcExecutiveListener,
        TalkToNpcListener {

	@Override
	public void onTalkToNpc(final Player p, final Npc n) {
		if (n.getID() == 529) {
			Functions.npcTalk(p, n, "What can I get you?");
			Functions.playerTalk(p, n, "What's on the menu?");
			Functions.npcTalk(p, n, "Dragon bitter and Greenmans ale");
			Menu defaultMenu = new Menu();
			defaultMenu.addOption(new Option("I'll give it a miss I think") {
				@Override
				public void action() {
					Functions.npcTalk(p, n, "Come back when you're a little thirstier");
				}
			});
			defaultMenu.addOption(new Option("I'll try the dragon bitter") {
				@Override
				public void action() {
					Functions.npcTalk(p, n, "Ok, that'll be two coins");
					if (Functions.hasItem(p, 10, 2)) {
						p.message("You buy a pint of dragon bitter");
						Functions.addItem(p, 829, 1);
						Functions.removeItem(p, 10, 2);
					} else {
						Functions.playerTalk(p, n,"Oh dear. I don't seem to have enough money");
					}
				}
			});
			defaultMenu.addOption(new Option("Can I have some greenmans ale?") {
					@Override
					public void action() {
						Functions.npcTalk(p, n, "Ok, that'll be ten coins");
						if (Functions.hasItem(p, 10, 10)) {
							p.message("You buy a pint of ale");
							Functions.addItem(p, 830, 1);
							Functions.removeItem(p, 10, 10);
						} else {
							Functions.playerTalk(p, n, "Oh dear. I don't seem to have enough money");
						}
					}
				});
			defaultMenu.showMenu(p);
		}
	}

	@Override
	public boolean blockTalkToNpc(Player p, Npc n) {
		if (n.getID() == 529) {
			return true;
		}
		return false;
	}

}
