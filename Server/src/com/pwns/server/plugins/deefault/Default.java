package com.pwns.server.plugins.deefault;

import com.pwns.server.model.container.Item;
import com.pwns.server.model.entity.GameObject;
import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.DefaultHandler;
import com.pwns.server.plugins.listeners.action.InvUseOnNpcListener;
import com.pwns.server.plugins.listeners.action.InvUseOnObjectListener;
import com.pwns.server.plugins.listeners.action.InvUseOnWallObjectListener;
import com.pwns.server.plugins.listeners.action.ObjectActionListener;
import com.pwns.server.plugins.listeners.action.TalkToNpcListener;
import com.pwns.server.plugins.listeners.action.WallObjectActionListener;

/**
 * Theoretically we do not need to block, as everything that is not handled
 * should be handled here.
 * 
 * @author openfrog
 * 
 */

public class Default implements DefaultHandler, TalkToNpcListener, ObjectActionListener,
		InvUseOnObjectListener, InvUseOnWallObjectListener,
		InvUseOnNpcListener, WallObjectActionListener {
	
	public static final DoorAction doors = new DoorAction();
	public static final Ladders ladders = new Ladders();

	@Override
	public void onInvUseOnNpc(final Player player, final Npc npc,
			final Item item) {
		player.message("Nothing interesting happens");
	}

	@Override
	public void onInvUseOnObject(final GameObject object, final Item item,
			final Player owner) {
		if (doors.blockInvUseOnWallObject(object, item, owner)) {
			doors.onInvUseOnWallObject(object, item, owner);
			return;
		}
		System.out.println("InvUseOnObject unhandled: item " + item.getID()
				+ " used with object: " + object.getID());
	}

	@Override
	public void onObjectAction(final GameObject obj, final String command,
			final Player player) {
		if (doors.blockObjectAction(obj, command, player)) {
			doors.onObjectAction(obj, command, player);
		} else if (ladders.blockObjectAction(obj, command, player)) {
			ladders.onObjectAction(obj, command, player);
		} else
			player.message("Nothing interesting happens");
	}

	@Override
	public void onTalkToNpc(final Player p, final Npc n) {
		p.message(
				"The " + n.getDef().getName()
						+ " does not appear interested in talking");
	}

	@Override
	public void onInvUseOnWallObject(GameObject object, Item item,
			Player owner) {
		if (doors.blockInvUseOnWallObject(object, item, owner)) {
			doors.onInvUseOnWallObject(object, item, owner);
		} else
			owner.message("Nothing interesting happens");
	}

	@Override
	public void onWallObjectAction(GameObject obj, Integer click, Player p) {
		if (doors.blockWallObjectAction(obj, click, p)) {
			doors.onWallObjectAction(obj, click, p);
		} else
			p.message("Nothing interesting happens");
	}
}
