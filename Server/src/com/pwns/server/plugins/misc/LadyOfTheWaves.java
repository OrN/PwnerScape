package com.pwns.server.plugins.misc;

import com.pwns.server.model.entity.GameObject;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.listeners.action.ObjectActionListener;
import com.pwns.server.plugins.listeners.executive.ObjectActionExecutiveListener;
import com.pwns.server.plugins.Functions;

public class LadyOfTheWaves implements ObjectActionListener, ObjectActionExecutiveListener {

	public static final int SHIP_LADY_OF_THE_WAVES_FRONT = 780;
	public static final int SHIP_LADY_OF_THE_WAVES_BACK = 781;
	@Override
	public boolean blockObjectAction(GameObject obj, String command, Player p) {
		return obj.getID() == SHIP_LADY_OF_THE_WAVES_FRONT || obj.getID() == SHIP_LADY_OF_THE_WAVES_BACK;
	}

	@Override
	public void onObjectAction(GameObject obj, String command, Player p) {
		if(obj.getID() == SHIP_LADY_OF_THE_WAVES_FRONT || obj.getID() == SHIP_LADY_OF_THE_WAVES_BACK) {
			p.message("This ship looks like it might take you somewhere.");
			p.message("The captain shouts down,");
			p.message("@yel@Captain: Where would you like to go?");
			int menu = Functions.showMenu(p,
					"Khazard Port",
					"Port Sarim",
					"No where thanks!");
			if(menu == 0) {
				sail(p, menu);
			} else if(menu == 1) {
				sail(p, menu);
			} else if(menu == 2) {
				Functions.playerTalk(p, null, "No where thanks!");
				p.message("@yel@Captain: Ok, come back if you change your mind.");
			}
		}
	}
	private void sail(Player p, int option) {
		p.setBusy(true);
		if(Functions.hasItem(p, 988)) { // ship ticket.
			Functions.removeItem(p, 988, 1);
			Functions.message(p, 1200, "@yel@Captain: Thanks for the ticket, let's set sail!");
			Functions.message(p, 1200, "You board the ship and it sails off.");
			if(option == 0) {
				p.teleport(545, 703);
				p.message("Before you know it, you're in Khazard Port.");
				p.setBusy(false);
			} else if(option == 1) {
				p.teleport(269, 640);
				p.message("Before you know it, you're in Port Sarim.");
				p.setBusy(false);
			}
		} else {
			Functions.message(p, 1200, "The captain shakes his head.");
			Functions.message(p, 1200, "@yel@Captain: Sorry Bwana, but you need a ticket!");
			Functions.message(p, 1200, "@yel@Captain: You can get one in Shilo Village ");
			Functions.message(p, 1200, "@yel@Captain: Just above the fishing shop. ");
			p.setBusy(false);
		}
	}
}