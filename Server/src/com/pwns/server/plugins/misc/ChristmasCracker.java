package com.pwns.server.plugins.misc;

import com.pwns.server.model.container.Item;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.listeners.action.InvUseOnPlayerListener;
import com.pwns.server.plugins.listeners.executive.InvUseOnPlayerExecutiveListener;
import com.pwns.server.util.rsc.DataConversions;
import com.pwns.server.plugins.Functions;

public class ChristmasCracker implements InvUseOnPlayerListener, InvUseOnPlayerExecutiveListener {
	
	public static int RED_CRACKER = 575;

	@Override
	public void onInvUseOnPlayer(Player player, Player otherPlayer, Item item) {
		if(item.getID() == RED_CRACKER) {
			if(otherPlayer.isIronMan(1) || otherPlayer.isIronMan(2) || otherPlayer.isIronMan(3)) {
				player.message(otherPlayer.getUsername() + " is an Iron Man. He stands alone.");
				return;
			}
			Functions.showBubble(player, item);
			player.message("You pull a christmas cracker");
			otherPlayer.message("You pull a christmas cracker");
			Item phat = new Item(DataConversions.random(576, 581));
			if (DataConversions.random(0, 1) == 1) {
				otherPlayer.message("The person you pull the cracker with gets the prize");
				player.message("You get the prize from the cracker");
				player.getInventory().add(phat);
			} else {
				player.message("The person you pull the cracker with gets the prize");
				otherPlayer.message("You get the prize from the cracker");
				otherPlayer.getInventory().add(phat);
			}
			player.getInventory().remove(item);
		}
	}

	@Override
	public boolean blockInvUseOnPlayer(Player player, Player otherPlayer, Item item) {
		if(item.getID() == RED_CRACKER) {
			return true;
		}
		return false;
	}
}