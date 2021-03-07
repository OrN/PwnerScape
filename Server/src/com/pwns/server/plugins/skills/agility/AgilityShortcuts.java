package com.pwns.server.plugins.skills.agility;

import com.pwns.server.model.container.Item;
import com.pwns.server.model.entity.GameObject;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.model.world.World;
import com.pwns.server.plugins.listeners.action.InvUseOnObjectListener;
import com.pwns.server.plugins.listeners.action.ObjectActionListener;
import com.pwns.server.plugins.listeners.executive.InvUseOnObjectExecutiveListener;
import com.pwns.server.plugins.listeners.executive.ObjectActionExecutiveListener;
import com.pwns.server.plugins.Functions;

public class AgilityShortcuts implements ObjectActionListener,
ObjectActionExecutiveListener, InvUseOnObjectListener, InvUseOnObjectExecutiveListener {

	public static final int SHORTCUT_FALADOR_HANDHOLD = 693;
	public static final int SHORTCUT_BRIMHAVEN_SWING = 694;
	public static final int SHORTCUT_BRIMHAVEN_BACK_SWING = 695;
	public static final int SHORTCUT_EDGE_DUNGEON_SWING = 684;
	public static final int SHORTCUT_EDGE_DUNGEON_BACK_SWING = 685;
	public static final int SHORTCUT_WEST_COALTRUCKS_LOG = 681;
	public static final int SHORTCUT_EAST_COALTRUCKS_LOG = 680;
	private static final int SHORTCUT_YANILLE_AGILITY_ROPESWING = 628;
	private static final int SHORTCUT_YANILLE_AGILITY_ROPESWING_BACK = 627;

	private static final int SHORTCUT_YANILLE_AGILITY_LEDGE = 614;
	private static final int SHORTCUT_YANILLE_AGILITY_LEDGE_BACK = 615;
	private static final int SHORTCUT_YANILLE_PILE_OF_RUBBLE = 636;
	private static final int SHORTCUT_YANILLE_PILE_OF_RUBBLE_UP = 633;
	private static final int SHORTCUT_YANILLE_PIPE = 656;
	private static final int SHORTCUT_YANILLE_PIPE_BACK = 657;

	private static final int GREW_ISLAND_ROPE_ATTACH = 662;
	private static final int GREW_ISLAND_ROPE_ATTACHED = 663;
	private static final int GREW_ISLAND_SWING_BACK = 664;

	private static final int EAST_KARAMJA_LOG = 692;
	private static final int EAST_KARAMJA_STONES = 701;
	private static final int YANILLE_WATCHTOWER_HANDHOLDS = 658;

	public static final int SHILO_VILLAGE_ROCKS_TO_BRIDGE = 710;
	public static final int SHILO_VILLAGE_BRIDGE_BLOCKADE_JUMP = 691;

	@Override
	public boolean blockObjectAction(GameObject obj, String command,
			Player player) {
		return Functions.inArray(obj.getID(), SHORTCUT_YANILLE_PIPE,
				SHORTCUT_YANILLE_PIPE_BACK,
				SHORTCUT_YANILLE_PILE_OF_RUBBLE,
				SHORTCUT_YANILLE_PILE_OF_RUBBLE_UP,
				SHORTCUT_YANILLE_AGILITY_LEDGE,
				SHORTCUT_YANILLE_AGILITY_LEDGE_BACK, SHORTCUT_FALADOR_HANDHOLD,
				SHORTCUT_BRIMHAVEN_SWING, SHORTCUT_BRIMHAVEN_BACK_SWING,
				SHORTCUT_EDGE_DUNGEON_SWING, SHORTCUT_EDGE_DUNGEON_BACK_SWING,
				SHORTCUT_WEST_COALTRUCKS_LOG, SHORTCUT_EAST_COALTRUCKS_LOG,
				SHORTCUT_YANILLE_AGILITY_ROPESWING,
				SHORTCUT_YANILLE_AGILITY_ROPESWING_BACK, 
				GREW_ISLAND_ROPE_ATTACHED,
				GREW_ISLAND_SWING_BACK,
				EAST_KARAMJA_LOG,
				EAST_KARAMJA_STONES,
				YANILLE_WATCHTOWER_HANDHOLDS,
				SHILO_VILLAGE_ROCKS_TO_BRIDGE,
				SHILO_VILLAGE_BRIDGE_BLOCKADE_JUMP);
	}

	@Override
	public void onObjectAction(GameObject obj, String command, Player p) {
		p.setBusy(true);
		switch (obj.getID()) {
		case SHILO_VILLAGE_BRIDGE_BLOCKADE_JUMP:
			if (Functions.getCurrentLevel(p, Functions.AGILITY) < 32) {
				p.message("You need an agility level of 32 to climb the rocks");
				p.setBusy(false);
				return;
			}
			Functions.message(p, "The bridge beyond this fence looks very unsafe.");
			Functions.message(p, "However, you could try to negotiate it if you're feeling very agile.");
			p.message("Would you like to try?");
			int jumpMenu = Functions.showMenu(p,
					"No thanks! It looks far too dangerous!",
					"Yes, I'm totally brave and quite agile!");
			if(jumpMenu == 0) {
				Functions.message(p, "You decide that common sense is the better part of valour.",
						"And stop yourself from being hurled to what must be an ");
				p.message("inevitable death.");
			} else if(jumpMenu == 1) {
				Functions.message(p, "You prepare to negotiate the bridge fence...");
				Functions.message(p, "You run and jump...");
				if(succeed(p, 32)) {
					p.message("...and land perfectly on the other side!");
					if(p.getX() >= 460) { // back
						p.teleport(458, 828);
					} else {
						p.teleport(460, 828);
					}
				} else {
					p.message("...slip and fall incompetently into the river below!");
					p.teleport(458, 832);
					Functions.playerTalk(p, null, "* Ahhhhhhhhhh! *");
					p.damage((Functions.getCurrentLevel(p, Functions.HITS) / 10));
					Functions.sleep(500);
					p.teleport(458, 836);
					p.damage((Functions.getCurrentLevel(p, Functions.HITS) / 10));
					Functions.sleep(1000);
					Functions.playerTalk(p, null, "* Gulp! *");
					Functions.sleep(1500);
					p.teleport(459, 841);
					Functions.playerTalk(p, null, "* Gulp! *");
					Functions.sleep(1000);
					p.message("You just manage to drag your pitiful frame onto the river bank.");
					Functions.playerTalk(p, null, "* Gasp! *");
					p.damage((Functions.getCurrentLevel(p, Functions.HITS) / 10));
					Functions.sleep(1000);
					p.message("Though you nearly drowned in the river!");
				}
			}
			break;
		case SHILO_VILLAGE_ROCKS_TO_BRIDGE:
			if (Functions.getCurrentLevel(p, Functions.AGILITY) < 32) {
				p.message("You need an agility level of 32 to climb the rocks");
				p.setBusy(false);
				return;
			}
			Functions.message(p, "These rocks look quite dangerous to climb.",
					"But you may be able to scale them.");
			p.message("Would you like to try?");
			int menu = Functions.showMenu(p,
					"Yes, I can easily climb this!",
					"Nope, I'm sure I'll probably fall!");
			if(menu == 0) {
				if(succeed(p, 32)) {
					Functions.message(p, "You manage to climb the rocks succesfully and pick");
					if(obj.getX() == 450) {
						p.message("a route though the trecherous embankment to the top.");
						p.teleport(452, 829);
					} else {
						p.message("a route though the trecherous embankment to the bottom.");
						p.teleport(449, 828);
					}
				} else {
					p.teleport(450, 828);
					Functions.message(p, "You fall and hurt yourself.");
					p.damage((Functions.getCurrentLevel(p, Functions.HITS) / 10));
					Functions.sleep(500);
					p.teleport(449, 828);
				}
			} else if(menu == 1) {
				p.message("You decide not to climb the rocks.");
			}
			break;
		case SHORTCUT_FALADOR_HANDHOLD:
			if (Functions.getCurrentLevel(p, Functions.AGILITY) < 5) {
				p.message("You need an agility level of 5 to climb the wall");
				p.setBusy(false);
				return;
			}
			p.message("You climb over the wall");
			Functions.movePlayer(p, 338, 555);
			p.incExp(Functions.AGILITY, 10.0, true);
			break;
		case SHORTCUT_BRIMHAVEN_SWING:
			if (Functions.getCurrentLevel(p, Functions.AGILITY) < 10) {
				p.message("You need an agility level of 10 to attempt to swing on this vine");
				p.setBusy(false);
				return;
			}
			p.message("You grab the vine and try and swing across");
			Functions.sleep(1000);
			Functions.movePlayer(p, 511, 669);
			p.message("You skillfully swing across the stream");
			Functions.playerTalk(p, null, "Aaaaahahah");
			p.incExp(Functions.AGILITY, 7.0, true);
			break;
		case SHORTCUT_BRIMHAVEN_BACK_SWING:
			if (Functions.getCurrentLevel(p, Functions.AGILITY) < 10) {
				p.message("You need an agility level of 10 to attempt to swing on this vine");
				p.setBusy(false);
				return;
			}
			p.message("You grab the vine and try and swing across");
			Functions.sleep(1000);
			Functions.movePlayer(p, 508, 668);
			p.message("You skillfully swing across the stream");
			Functions.playerTalk(p, null, "Aaaaahahah");
			p.incExp(Functions.AGILITY, 7.0, true);
			break;
		case SHORTCUT_EDGE_DUNGEON_SWING:
			if (Functions.getCurrentLevel(p, Functions.AGILITY) < 15) {
				p.message("You need an agility level of 15 to attempt to swing on this rope");
				p.setBusy(false);
				return;
			}
			Functions.sleep(1000);
			Functions.movePlayer(p, 207, 3221);
			p.message("You skillfully swing across the hole");
			p.incExp(Functions.AGILITY, 10.0, true);
			break;
		case SHORTCUT_EDGE_DUNGEON_BACK_SWING:
			if (Functions.getCurrentLevel(p, Functions.AGILITY) < 15) {
				p.message("You need an agility level of 15 to attempt to swing on this rope");
				p.setBusy(false);
				return;
			}
			Functions.sleep(1000);
			Functions.movePlayer(p, 206, 3225);
			p.message("You skillfully swing across the hole");
			p.incExp(Functions.AGILITY, 10.0, true);
			break;
		case SHORTCUT_WEST_COALTRUCKS_LOG:
			if (Functions.getCurrentLevel(p, Functions.AGILITY) < 20) {
				p.message("You need an agility level of 20 to attempt balancing along this log");
				p.setBusy(false);
				return;
			}
			p.message("You stand on the slippery log");
			for (int x = 595; x >= 592; x--) {
				Functions.movePlayer(p, x, 458);
				Functions.sleep(650);
			}
			p.message("and you walk across");
			p.incExp(Functions.AGILITY, 8.5, true);
			break;
		case SHORTCUT_EAST_COALTRUCKS_LOG:
			if (Functions.getCurrentLevel(p, Functions.AGILITY) < 20) {
				p.message("You need an agility level of 20 to attempt balancing along this log");
				p.setBusy(false);
				return;
			}
			p.message("You stand on the slippery log");
			for (int x = 595; x <= 598; x++) {
				Functions.movePlayer(p, x, 458);
				Functions.sleep(650);
			}
			p.message("and you walk across");
			p.incExp(Functions.AGILITY, 8.5, true);
			break;
			// CONTINUE SHORTCUTS.
		case SHORTCUT_YANILLE_AGILITY_ROPESWING:
			if (Functions.getCurrentLevel(p, Functions.AGILITY) < 57) {
				p.message("You need an agility level of 57 to attempt to swing on this rope");
				p.setBusy(false);
				return;
			}
			if(p.getFatigue() >= 6975) {
				p.message("You are too tired to swing on the rope");
				p.setBusy(false);
				return;
			}
			p.message("You grab the rope and try and swing across");
			if(!succeed(p, 57)) {
				Functions.message(p, "You miss the opposite side and fall to the level below");
				Functions.movePlayer(p, 596, 3534);
				p.setBusy(false);
				return;
			}
			Functions.sleep(2200);
			Functions.movePlayer(p, 596, 3581);
			p.message("You skillfully swing across the hole");
			p.incExp(Functions.AGILITY, 27.5, true);
			break;
		case SHORTCUT_YANILLE_AGILITY_ROPESWING_BACK:
			if (Functions.getCurrentLevel(p, Functions.AGILITY) < 57) {
				p.message("You need an agility level of 57 to attempt to swing on this rope");
				p.setBusy(false);
				return;
			}
			if(p.getFatigue() >= 6975) {
				p.message("You are too tired to swing on the rope");
				p.setBusy(false);
				return;
			}
			p.message("You grab the rope and try and swing across");
			if(!succeed(p, 57)) {
				Functions.message(p, "You miss the opposite side and fall to the level below");
				Functions.movePlayer(p, 598, 3536);
				p.setBusy(false);
				return;
			}
			Functions.sleep(2200);
			Functions.movePlayer(p, 598, 3585);
			p.message("You skillfully swing across the hole");
			p.incExp(Functions.AGILITY, 27.5, true);
			break;

		case SHORTCUT_YANILLE_AGILITY_LEDGE:
			if (Functions.getCurrentLevel(p, Functions.AGILITY) < 40) {
				p.message("You need an agility level of 40 to attempt balancing along this log");
				p.setBusy(false);
				return;
			}
			if(p.getFatigue() >= 6975) {
				p.message("You are too tired to balance on the ledge");
				p.setBusy(false);
				return;
			}
			p.message("You put your foot on the ledge and try to edge across");
			Functions.sleep(2200);
			if(!succeed(p, 40)) {
				Functions.message(p, "you lose your footing and fall to the level below");
				Functions.movePlayer(p, 603, 3520);
				p.setBusy(false);
				return;
			}
			Functions.movePlayer(p, 601, 3563);
			p.setBusyTimer(1000);
			p.message("You skillfully balance across the hole");
			p.incExp(Functions.AGILITY, 22.5, true);
			break;
		case SHORTCUT_YANILLE_AGILITY_LEDGE_BACK:
			if (Functions.getCurrentLevel(p, Functions.AGILITY) < 40) {
				p.message("You need an agility level of 40 to attempt balancing along this log");
				p.setBusy(false);
				return;
			}
			if(p.getFatigue() >= 6975) {
				p.message("You are too tired to balance on the ledge");
				p.setBusy(false);
				return;
			}
			p.message("You put your foot on the ledge and try to edge across");
			Functions.sleep(2200);
			if(!succeed(p, 40)) {
				Functions.message(p, "you lose your footing and fall to the level below");
				Functions.movePlayer(p, 603, 3520);
				p.setBusy(false);
				return;
			}
			p.setBusyTimer(1000);
			Functions.movePlayer(p, 601, 3557);
			p.message("You skillfully balance across the hole");
			p.incExp(Functions.AGILITY, 22.5, true);
			break;

		case SHORTCUT_YANILLE_PILE_OF_RUBBLE:
			if (Functions.getCurrentLevel(p, Functions.AGILITY) < 67) {
				p.message("You need an agility level of 67 to attempt to climb down the rubble");
				p.setBusy(false);
				return;
			}
			Functions.movePlayer(p, 580, 3525);
			p.message("You climb down the pile of rubble");
			break;
		case SHORTCUT_YANILLE_PILE_OF_RUBBLE_UP:
			if (Functions.getCurrentLevel(p, Functions.AGILITY) < 67) {
				p.message("You need an agility level of 67 to attempt to climb up the rubble");
				p.setBusy(false);
				return;
			}
			if(p.getFatigue() >= 6975) {
				p.message("You are too tired to climb up the rubble");
				p.setBusy(false);
				return;
			}
			Functions.movePlayer(p, 582, 3573);
			p.message("You climb up the pile of rubble");
			p.incExp(Functions.AGILITY, 13.5, true);
			break;

		case SHORTCUT_YANILLE_PIPE:
			if (Functions.getCurrentLevel(p, Functions.AGILITY) < 49) {
				p.message("You need an agility level of 49 to attempt to squeeze through the pipe");
				p.setBusy(false);
				return;
			}
			if(p.getFatigue() >= 6975) {
				p.message("You are too tired to squeeze through the pipe");
				p.setBusy(false);
				return;
			}
			p.message("You squeeze through the pipe");
			Functions.sleep(2200);
			Functions.movePlayer(p, 608, 3568);
			p.incExp(Functions.AGILITY, 7.5, true);
			break;
		case SHORTCUT_YANILLE_PIPE_BACK:
			if (Functions.getCurrentLevel(p, Functions.AGILITY) < 49) {
				p.message("You need an agility level of 49 to attempt to squeeze through the pipe");
				p.setBusy(false);
				return;
			}
			if(p.getFatigue() >= 6975) {
				p.message("You are too tired to squeeze through the pipe");
				p.setBusy(false);
				return;
			}
			p.message("You squeeze through the pipe");
			Functions.sleep(2200);
			Functions.movePlayer(p, 605, 3568);
			p.incExp(Functions.AGILITY, 7.5, true);
			break;
		case GREW_ISLAND_ROPE_ATTACHED:
			if (Functions.getCurrentLevel(p, Functions.AGILITY) < 30) {
				p.message("You need an agility level of 30 to attempt to swing across the stream");
				p.setBusy(false);
				return;
			}
			p.message("You grab the rope and try and swing across");
			Functions.sleep(2200);
			Functions.movePlayer(p, 664, 755);
			p.message("You skillfully swing across the stream");
			p.incExp(Functions.AGILITY, 12, true);
			break;
		case GREW_ISLAND_SWING_BACK:
			p.message("You grab the rope and try and swing across");
			Functions.sleep(2200);
			Functions.movePlayer(p, 666, 755);
			p.message("You skillfully swing across the stream");
			p.incExp(Functions.AGILITY, 12, true);
			break;
		case EAST_KARAMJA_LOG:
			if (Functions.getCurrentLevel(p, Functions.AGILITY) < 32) {
				p.message("You need an agility level of 32 to attempt balancing along this log");
				p.setBusy(false);
				return;
			}
			p.message("You attempt to walk over the the slippery log..");
			Functions.sleep(1900);
			if(!succeed(p, 32)) {
				Functions.movePlayer(p, 368, 781);
				Functions.sleep(650);
				p.message("@red@You fall into the stream!");
				p.message("You lose some health");
				Functions.movePlayer(p, 370, 776);
				p.damage(1);
				p.setBusy(false);
				return;
			}
			if(p.getX() <= 367) {
				Functions.movePlayer(p, 368, 781);
				Functions.sleep(650);
				Functions.movePlayer(p, 370, 781);
			} else {
				Functions.movePlayer(p, 368, 781);
				Functions.sleep(650);
				Functions.movePlayer(p, 366, 781);
			}
			p.message("...and make it without any problems!");
			p.incExp(Functions.AGILITY, 2.5, true);
			break;
		case EAST_KARAMJA_STONES:
			p.setBusyTimer(1500);
			if (Functions.getCurrentLevel(p, Functions.AGILITY) < 32) {
				p.message("You need an agility level of 32 to step on these stones");
				p.setBusy(false);
				return;
			}
			if(p.getFatigue() >= 7500) {
				p.message("You are too fatigued to continue.");
				p.setBusy(false);
				return;
			}
			p.message("You jump onto the rock");
			if(p.getY() <= 805) {
				Functions.movePlayer(p, 347, 806);
				Functions.sleep(650);
				if(!succeed(p, 32)) {
					Functions.sleep(900);
					Functions.movePlayer(p, 341, 809);
					p.message("@red@!!! You Fall !!!");
					Functions.message(p, "You get washed up on the other side of the river...",
							"After being nearly half drowned");
					p.damage((int) (p.getSkills().getLevel(3) / 4) + 2);
					p.setBusy(false);
					return;
				}
				Functions.movePlayer(p, 346, 808);
			} else {
				Functions.movePlayer(p, 346, 807);
				Functions.sleep(650);
				if(!succeed(p, 32)) {
					Functions.sleep(900);
					Functions.movePlayer(p, 341, 805);
					p.message("@red@!!! You Fall !!!");
					Functions.message(p, "You get washed up on the other side of the river...",
							"After being nearly half drowned");
					p.damage((int) (p.getSkills().getLevel(3) / 4) + 2);
					p.setBusy(false);
					return;
				}
				Functions.movePlayer(p, 347, 805);
			}
			p.message("And cross the water without problems.");
			p.incExp(Functions.AGILITY, 2.5, true);
			break;
		case YANILLE_WATCHTOWER_HANDHOLDS:
			if(p.getFatigue() >= 7500) {
				p.message("You are too tired to climb up the wall");
				p.setBusy(false);
				return;
			}
			if (Functions.getCurrentLevel(p, Functions.AGILITY) < 30) {
				p.message("You need an agility level of 30 to climb up the wall");
				p.setBusy(false);
				return;
			}
			p.message("You climb up the wall");
			p.teleport(637, 1680);
			p.message("And climb in through the window");
			p.incExp(Functions.AGILITY, 12.5, true);
			break;
		}
		p.setBusy(false);
	}
	// you tie the rope to the tree

	boolean succeed(Player player, int req) {
		int level_difference = Functions.getCurrentLevel(player, Functions.AGILITY) - req;
		int percent = Functions.random(1, 100);

		if(level_difference < 0)
			return true;
		if(level_difference >= 15)
			level_difference = 80;
		if(level_difference >= 20)
			level_difference = 90;
		else
			level_difference = 30 + level_difference;

		return percent <= level_difference;
	}

	@Override
	public boolean blockInvUseOnObject(GameObject obj, Item item, Player player) {
		if(obj.getID() == GREW_ISLAND_ROPE_ATTACH && item.getID() == 237) {
			return true;
		}
		return false;
	}

	@Override
	public void onInvUseOnObject(GameObject obj, Item item, Player p) {
		if(obj.getID() == GREW_ISLAND_ROPE_ATTACH && item.getID() == 237) {
			p.message("you tie the rope to the tree");
			Functions.removeItem(p, 237, 1);
			World.getWorld().replaceGameObject(obj,
					new GameObject(obj.getLocation(), 663, obj.getDirection(), obj
							.getType()));
			World.getWorld().delayedSpawnObject(obj.getLoc(), 60000);
		}
	}

	// HERRING SPAWN I CHEST ROOM SINISTER CHEST = 362, 614, 3564
}
