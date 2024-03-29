package com.pwns.server.plugins.skills.agility;

import static com.pwns.server.plugins.Functions.inArray;
import static com.pwns.server.plugins.Functions.message;
import static com.pwns.server.plugins.Functions.movePlayer;
import static com.pwns.server.plugins.Functions.sleep;

import com.pwns.server.model.entity.GameObject;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.listeners.action.ObjectActionListener;
import com.pwns.server.plugins.listeners.executive.ObjectActionExecutiveListener;
import com.pwns.server.plugins.Functions;

public class WildernessAgilityCourse implements ObjectActionListener,
ObjectActionExecutiveListener {

	public static final int GATE = 703;
	public static final int SECOND_GATE = 704;
	public static final int WILD_PIPE = 705;
	public static final int WILD_ROPESWING = 706;
	public static final int STONE = 707;
	public static final int LEDGE = 708;
	public static final int VINE = 709;

	@Override
	public boolean blockObjectAction(GameObject obj, String command, Player p) {
		return Functions.inArray(obj.getID(), GATE, SECOND_GATE, WILD_PIPE, WILD_ROPESWING, STONE, LEDGE, VINE);
	}

	@Override
	public void onObjectAction(GameObject obj, String command, Player p) {
		p.setBusy(true);
		boolean failCourse = failWildCourse(p);
		switch(obj.getID()) {
		case GATE:
			if (Functions.getCurrentLevel(p, Functions.AGILITY) < 52) {
				p.message("You need an agility level of 52 to attempt balancing along the ridge");
				p.setBusy(false);
				return;
			}
			p.message("You go through the gate and try to edge over the ridge");
			Functions.sleep(1000);
			Functions.movePlayer(p, 298, 130);
			int failRate = failRate();
			if (failRate == 1) {
				Functions.message(p, "you lose your footing and fall into the wolf pit");
				Functions.movePlayer(p, 300, 129);
			}
			else if (failRate == 2) {
				Functions.message(p, "you lose your footing and fall into the wolf pit");
				Functions.movePlayer(p, 296, 129);
			} else {
				Functions.message(p, "You skillfully balance across the ridge");
				Functions.movePlayer(p, 298, 125);
				p.incExp(Functions.AGILITY, 22.0, true);
			}
			break;
		case SECOND_GATE:
			p.message("You go through the gate and try to edge over the ridge");
			Functions.sleep(1000);
			Functions.movePlayer(p, 298, 130);
			Functions.sleep(1000);
			failRate = failRate();
			if (failRate == 1) {
				Functions.message(p, "you lose your footing and fall into the wolf pit");
				Functions.movePlayer(p, 300, 129);

			} else if (failRate == 2) {
				Functions.message(p, "you lose your footing and fall into the wolf pit");
				Functions.movePlayer(p, 296, 129);
			} else {
				Functions.message(p, "You skillfully balance across the ridge");
				Functions.movePlayer(p, 298, 134);
				p.incExp(Functions.AGILITY, 22.0, true);
			}
			break;
		case WILD_PIPE:
			p.message("You squeeze through the pipe");
			Functions.sleep(1000);
			Functions.movePlayer(p, 294, 112);
			p.incExp(Functions.AGILITY, 13.0, true);
			break;
		case WILD_ROPESWING:
			p.message("You grab the rope and try and swing across");
			Functions.sleep(1000);
			int damage = (int) Math.round((p.getSkills().getLevel(3)) * 0.15D);
			if (failCourse) {
				Functions.message(p, "You skillfully swing across the hole");
				Functions.movePlayer(p, 292, 108);
				p.incExp(Functions.AGILITY, 25.0, true);
			} else { // 13 damage on 85hp.
				// 11 damage on 73hp.
				// 
				p.message("Your hands slip and you fall to the level below");
				Functions.sleep(1000);
				Functions.movePlayer(p, 293, 2942);
				p.message("you land painfully on the spikes");
				Functions.playerTalk(p, null, "ouch");
				p.damage(damage);
			}
			break;
		case STONE:
			p.message("You stand on the stepping stones");
			Functions.sleep(1000);
			if (failCourse) {
				Functions.movePlayer(p, 293, 105);
				Functions.sleep(600);
				Functions.movePlayer(p, 294, 104);
				Functions.sleep(600);
				Functions.movePlayer(p, 295, 104);
				p.message("and walk across");
				Functions.sleep(600);
				Functions.movePlayer(p, 296, 105);
				Functions.sleep(600);
				Functions.movePlayer(p, 297, 105);
				p.incExp(Functions.AGILITY, 20.0, true);
			} else {
				p.message("You lose your footing and land in the lava");
				Functions.movePlayer(p, 292, 104);
				int lavaDamage = (int) Math.round((p.getSkills().getLevel(3)) * 0.21D);
				p.damage(lavaDamage);
			}
			break;
		case LEDGE:
			p.message("you stand on the ledge");
			Functions.sleep(1000);
			int ledgeDamage = (int) Math.round((p.getSkills().getLevel(3)) * 0.25D);
			if (failCourse) {
				Functions.movePlayer(p, 296, 112);
				Functions.sleep(600);
				p.message("and walk across");
				Functions.movePlayer(p, 297, 112);
				Functions.sleep(600);
				Functions.movePlayer(p, 298, 112);
				Functions.sleep(600);
				Functions.movePlayer(p, 299, 111);
				Functions.sleep(600);
				Functions.movePlayer(p, 300, 111);
				Functions.sleep(600);
				Functions.movePlayer(p, 301, 111);
				p.incExp(Functions.AGILITY, 20.0, true);
			} else {
				p.message("you lose your footing and fall to the level below");
				Functions.sleep(1000);
				Functions.movePlayer(p, 298, 2945);
				p.message("you land painfully on the spikes");
				Functions.playerTalk(p, null, "ouch");
				p.damage(ledgeDamage);
			}
			break;
		case VINE:
			p.message("you climb up the cliff");
			Functions.movePlayer(p, 305, 118);
			Functions.sleep(600);
			Functions.movePlayer(p, 304, 119);
			Functions.sleep(600);
			Functions.movePlayer(p, 304, 120);
			p.incExp(Functions.AGILITY, 375.0, true); // COMPLETION OF THE COURSE.
			break;
		}
		p.setBusy(false);
	}

	boolean failWildCourse(Player player) {
		int level_difference = Functions.getCurrentLevel(player, Functions.AGILITY) - 52;
		int percent = Functions.random(1, 100);

		if(level_difference < 0)
			return false;
		else if(level_difference >= 10)
			level_difference = 80;
		
		else if(level_difference >= 15)
			level_difference = 90;
		else
			level_difference = 60 + level_difference;
		return percent <= level_difference;
	}

	int failRate() {
		return Functions.random(1, 5);
	}
}
