package com.pwns.server.plugins.misc;

import static com.pwns.server.plugins.Functions.createGroundItem;
import static com.pwns.server.plugins.Functions.message;
import static com.pwns.server.plugins.Functions.removeItem;
import static com.pwns.server.plugins.Functions.showBubble;

import com.pwns.server.model.container.Item;
import com.pwns.server.model.entity.GameObject;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.listeners.action.InvUseOnObjectListener;
import com.pwns.server.plugins.listeners.action.ObjectActionListener;
import com.pwns.server.plugins.listeners.executive.InvUseOnObjectExecutiveListener;
import com.pwns.server.plugins.listeners.executive.ObjectActionExecutiveListener;
import com.pwns.server.util.rsc.Formulae;
import com.pwns.server.plugins.Functions;

public class Hopper implements InvUseOnObjectListener, InvUseOnObjectExecutiveListener, ObjectActionListener, ObjectActionExecutiveListener {

	@Override
	public boolean blockInvUseOnObject(GameObject obj, Item item, Player player) {
		return (obj.getID() == 52 || obj.getID() == 173 || obj.getID() == 246) && item.getID() == 29;
	}

	@Override
	public void onInvUseOnObject(GameObject obj, Item item, Player player) {
		if(obj.getAttribute("contains_item", null) != null) {
			player.message("There is already grain in the hopper");
			return;
		}
		Functions.showBubble(player, item);
		obj.setAttribute("contains_item", item.getID());
		Functions.removeItem(player, item);
		player.message("You put the grain in the hopper");
	}

	@Override
	public boolean blockObjectAction(GameObject obj, String command, Player player) {
		return obj.getGameObjectDef() != null && obj.getGameObjectDef().getName().toLowerCase().equals("hopper") && command.equals("operate");
	}

	@Override
	public void onObjectAction(GameObject obj, String command, Player player) {
		Functions.message(player, 500, "You operate the hopper");
		int contains = obj.getAttribute("contains_item", (int) -1);
		if(contains != 29 || contains == -1) {
			player.message("Nothing interesting happens");
			return;
		}
		player.message("The grain slides down the chute");
		if(obj.getID() == 246) {
			Functions.createGroundItem(23, 1, 162, 3533);
		} else {
			Functions.createGroundItem(23, 1, obj.getX(), Formulae.getNewY(Formulae.getNewY(obj.getY(), false), false));
		}
		obj.removeAttribute("contains_item");
	}

}
