package com.pwns.server.plugins.npcs.varrock;

import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.net.rsc.ActionSender;
import com.pwns.server.plugins.listeners.action.NpcCommandListener;
import com.pwns.server.plugins.listeners.action.TalkToNpcListener;
import com.pwns.server.plugins.listeners.executive.NpcCommandExecutiveListener;
import com.pwns.server.plugins.listeners.executive.TalkToNpcExecutiveListener;
import com.pwns.server.plugins.Functions;

public class Auctioneers implements TalkToNpcExecutiveListener, TalkToNpcListener, NpcCommandListener, NpcCommandExecutiveListener {

	public static int AUCTIONEER = 794;
	public static int AUCTION_CLERK = 795;

	@Override
	public boolean blockTalkToNpc(final Player player, final Npc npc) {
		if(npc.getID() == AUCTIONEER) {
			return true;
		}
		if(npc.getID() == AUCTION_CLERK) {
			return true;
		}
		return false;
	}

	@Override
	public void onTalkToNpc(Player player, final Npc npc) {
		Functions.npcTalk(player, npc, "Hello");
		int menu;
		if(npc.getID() == AUCTION_CLERK) {
			menu = Functions.showMenu(player, npc, "I'd like to browse the auction house", "Can you teleport me to Varrock Centre");
		} else {
			menu = Functions.showMenu(player, npc, "I'd like to browse the auction house");
		}
		if(menu == 0) {
			if(player.isIronMan(1) || player.isIronMan(2) || player.isIronMan(3)) {
				player.message("As an Iron Man, you cannot use the Auction.");
				return;
			}
			if (player.getCache().hasKey("bank_pin") && !player.getAttribute("bankpin", false)) {
				String pin = Functions.getBankPinInput(player);
				if (pin == null) {
					return;
				}
				if (!player.getCache().getString("bank_pin").equals(pin)) {
					ActionSender.sendBox(player, "Incorrect bank pin", false);
					return;
				}
				player.setAttribute("bankpin", true);
				ActionSender.sendBox(player, "Bank pin correct", false);
			}
			Functions.npcTalk(player, npc, "Certainly " + (player.isMale() ? "Sir" : "Miss"));
			ActionSender.sendOpenAuctionHouse(player);
		} else if(menu == 1) {
			Functions.npcTalk(player, npc, "Yes of course " + (player.isMale() ? "Sir" : "Miss"),
					"the costs is 1,000 coins");
			int tMenu = Functions.showMenu(player, npc, "Teleport me", "I'll stay here");
			if(tMenu == 0) {
				if(Functions.hasItem(player, 10, 1000)) {
					Functions.removeItem(player, 10, 1000);
					player.teleport(133, 508);
				} else {
					player.message("You don't seem to have enough coins");
				}
			}
		}
	}

	@Override
	public boolean blockNpcCommand(Npc n, String command, Player p) {
		if((n.getID() == AUCTIONEER) && command.equalsIgnoreCase("Auction")) {
			return true;
		}
		if(n.getID() == AUCTION_CLERK && (command.equalsIgnoreCase("Teleport") || command.equalsIgnoreCase("Auction"))) {
			return true;
		}
		return false;
	}

	@Override
	public void onNpcCommand(Npc n, String command, Player p) {
		if(n.getID() == AUCTIONEER) {
			if(command.equalsIgnoreCase("Auction")) {
				if(p.isIronMan(1) || p.isIronMan(2) || p.isIronMan(3)) {
					p.message("As an Iron Man, you cannot use the Auction.");
					return;
				}
				ActionSender.sendOpenAuctionHouse(p);
			} 
		}
		else if(n.getID() == AUCTION_CLERK) {
			if(command.equalsIgnoreCase("Auction")) {
				if(p.isIronMan(1) || p.isIronMan(2) || p.isIronMan(3)) {
					p.message("As an Iron Man, you cannot use the Auction.");
					return;
				}

				ActionSender.sendOpenAuctionHouse(p);
			} 
			else if(command.equalsIgnoreCase("Teleport")) {
				n.face(p);
				p.face(n);
				Functions.message(p, n, 1300, "Would you like to be teleport to Varrock centre for 1000 gold?");
				int yesOrNo = Functions.showMenu(p, "Yes please!", "No thanks.");
				if(yesOrNo == 0) {
					if(Functions.hasItem(p, 10, 1000)) {
						Functions.removeItem(p, 10, 1000);
						p.teleport(133, 508);
						p.message("You have been teleported to the Varrock Centre");
					} else {
						p.message("You don't seem to have enough coins");
					}
				} else if(yesOrNo == 1) {
					p.message("You decide to stay where you are located.");
				}
			}
		}
	}
}
