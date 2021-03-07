package com.pwns.server.plugins.misc;

import com.pwns.server.model.container.Item;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.listeners.action.InvActionListener;
import com.pwns.server.plugins.listeners.executive.InvActionExecutiveListener;
import com.pwns.server.util.rsc.DataConversions;
import com.pwns.server.util.rsc.MessageType;
import com.pwns.server.plugins.Functions;

public class Casket implements InvActionListener, InvActionExecutiveListener {

	public static final int CASKET = 549;
	public static final int COINS = 10;
	public static final int UNCUT_SAPPHIRE = 160;
	public static final int UNCUT_EMERALD = 159;
	public static final int UNCUT_RUBY = 158;
	public static final int UNCUT_DIAMOND = 157;
	public static final int TOOTH_KEY_HALF = 526;
	public static final int LOOP_KEY_HALF = 527;

	@Override
	public boolean blockInvAction(Item item, Player p) {
		if(item.getID() == CASKET) {
			return true;
		}
		return false;
	}

	@Override
	public void onInvAction(Item item, Player p) {
		if(item.getID() == CASKET) {
			p.setBusyTimer(1300);

			int randomChanceOpen = DataConversions.random(0, 1081);

			Functions.message(p, 1300, "you open the casket");
			p.playerServerMessage(MessageType.QUEST, "you find some treasure inside!");

			Functions.removeItem(p, CASKET, 1);

			// Coins, 54.11% chance
			if(randomChanceOpen <= 585) {
				// Randomly gives different coin amounts
				int randomChanceCoin = DataConversions.random(0, 6);
				if(randomChanceCoin == 0) {
					Functions.addItem(p, COINS, 10);
				}
				else if(randomChanceCoin == 1) {
					Functions.addItem(p, COINS, 20);
				}
				else if(randomChanceCoin == 2) {
					Functions.addItem(p, COINS, 40);
				}
				else if(randomChanceCoin == 3) {
					Functions.addItem(p, COINS, 80);
				}
				else if(randomChanceCoin == 4) {
					Functions.addItem(p, COINS, 160);
				}
				else if(randomChanceCoin == 5) {
					Functions.addItem(p, COINS, 320);
				}
				else {
					Functions.addItem(p, COINS, 640);
				}
			}
			// Uncut sapphire, 25.34% chance
			else if(randomChanceOpen > 585 && randomChanceOpen <= 859) {
				Functions.addItem(p, UNCUT_SAPPHIRE, 1);
			}
			// Uncut emerald, 12.11% chance
			else if(randomChanceOpen > 859 && randomChanceOpen <= 990) {
				Functions.addItem(p, UNCUT_EMERALD, 1);
			}
			//Uncut ruby, 5.27% chance
			else if(randomChanceOpen > 990 && randomChanceOpen <= 1047) {
				Functions.addItem(p, UNCUT_RUBY, 1);
			}
			// Uncut diamond, 1.57% chance
			else if(randomChanceOpen > 1047 && randomChanceOpen <= 1064) {
				Functions.addItem(p, UNCUT_DIAMOND, 1);
			}
			// Tooth halves, 1.56% chance
			else {
				// Randomly give one part or the other
				int randomChanceKey = DataConversions.random(0, 1);
				if(randomChanceKey == 0) {
					Functions.addItem(p, TOOTH_KEY_HALF, 1);
				}
				else {
					Functions.addItem(p, LOOP_KEY_HALF, 1);
				}
			}
		} 
	}
}