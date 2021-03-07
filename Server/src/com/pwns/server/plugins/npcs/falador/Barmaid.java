package com.pwns.server.plugins.npcs.falador;

import static com.pwns.server.plugins.Functions.hasItem;
import static com.pwns.server.plugins.Functions.message;
import static com.pwns.server.plugins.Functions.npcTalk;
import static com.pwns.server.plugins.Functions.showMenu;

import com.pwns.server.model.container.Item;
import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.listeners.action.TalkToNpcListener;
import com.pwns.server.plugins.listeners.executive.TalkToNpcExecutiveListener;
import com.pwns.server.plugins.Functions;

public final class Barmaid implements TalkToNpcExecutiveListener,
        TalkToNpcListener {
	private final String notEnoughMoney = "Oh dear. I don't seem to have enough money";

	@Override
	public void onTalkToNpc(Player p, final Npc n) {
		if (!p.getCache().hasKey("barcrawl")
				&& !p.getCache().hasKey("barthree")) {
			Functions.playerTalk(p, n, "Hi, what ales are you serving?");
			Functions.npcTalk(p,
					n,
					"Well you can either have a nice Asagarnian Ale, or a Wizards Mind Bomb",
					"Or a Dwarven Stout");

			String[] options = new String[] { "One Asgarnian Ale please",
					"I'll try the mind bomb", "Can I have a Dwarven Stout?",
					"I don't feel like any of those" };
			int option = Functions.showMenu(p, n, options);
			switch (option) {
			case 0:
				Functions.npcTalk(p, n, "That'll be two gold");

				if (p.getInventory().remove(10, 2) > -1) {
					p.message("You buy a pint of Asgarnian Ale");
					p.getInventory().add(new Item(267, 1));

				} else {
					Functions.playerTalk(p, n, notEnoughMoney);
				}
				break;
			case 1:
				Functions.npcTalk(p, n, "That'll be two gold");

				if (p.getInventory().remove(10, 2) > -1) {
					p.message("You buy a pint of Wizard's Mind Bomb");
					p.getInventory().add(new Item(268, 1));

				} else {
					Functions.playerTalk(p, n, notEnoughMoney);
				}

				break;
			case 2:
				Functions.npcTalk(p, n, "That'll be three gold");

				if (p.getInventory().remove(10, 3) > -1) {
					p.message("You buy a pint of Dwarven Stout");
					p.getInventory().add(new Item(269, 1));

				} else {
					Functions.playerTalk(p, n, notEnoughMoney);
				}
				break;
			}
		} else {
			int barCrawlOpt = Functions.showMenu(p, n, new String[] {
					"Hi what ales are you serving",
					"I'm doing Alfred Grimhand's barcrawl" });
			if (barCrawlOpt == 0) {
				Functions.npcTalk(p,
						n,
						"Well you can either have a nice Asagarnian Ale, or a Wizards Mind Bomb",
						"Or a Dwarven Stout");

				String[] options = new String[] { "One Asgarnian Ale please",
						"I'll try the mind bomb",
						"Can I have a Dwarven Stout?",
						"I don't feel like any of those" };

				int option = Functions.showMenu(p, n, options);
				switch (option) {
				case 0:
					Functions.npcTalk(p, n, "That'll be two gold");

					if (p.getInventory().remove(10, 2) > -1) {
						p.message("You buy a pint of Asgarnian Ale");
						p.getInventory().add(new Item(267, 1));

					} else {
						Functions.playerTalk(p, n, notEnoughMoney);
					}
					break;
				case 1:
					Functions.npcTalk(p, n, "That'll be two gold");

					if (p.getInventory().remove(10, 2) > -1) {
						p.message("You buy a pint of Wizard's Mind Bomb");
						p.getInventory().add(new Item(268, 1));

					} else {
						Functions.playerTalk(p, n, notEnoughMoney);
					}
					break;
				case 2:
					Functions.npcTalk(p, n, "That'll be three gold");

					if (p.getInventory().remove(10, 3) > -1) {
						p.message("You buy a pint of Dwarven Stout");
						p.getInventory().add(new Item(269, 1));

					} else {
						Functions.playerTalk(p, n, notEnoughMoney);
					}
					break;
				case 3:
					break;
				}
			} else if (barCrawlOpt == 1) {
				Functions.npcTalk(p,
						n,
						"Hehe this'll be fun",
						"You'll be after our off the menu hand of death cocktail then",
						"Lots of expensive parts to the cocktail though",
						"So it will cost you 70 coins");
				if (Functions.hasItem(p, 10, 70)) {
					Functions.message(p, "You buy a hand of death cocktail");
					p.getInventory().remove(10, 70);
					Functions.message(p, "You drink the cocktail",
							"You stumble around the room",
							"The barmaid giggles",
							"The barmaid signs your card");
					p.getCache().store("barthree", true);
				} else {
					Functions.playerTalk(p, n, "I don't have 70 coins right now");
				}
			}
		}
	}

	@Override
	public boolean blockTalkToNpc(Player p, Npc n) {
		return n.getID() == 142;
	}

}
