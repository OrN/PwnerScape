package com.pwns.server.plugins.skills.agility;

import static com.pwns.server.plugins.Functions.inArray;
import static com.pwns.server.plugins.Functions.movePlayer;
import static com.pwns.server.plugins.Functions.sleep;

import com.pwns.server.model.entity.GameObject;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.listeners.action.ObjectActionListener;
import com.pwns.server.plugins.listeners.action.WallObjectActionListener;
import com.pwns.server.plugins.listeners.executive.ObjectActionExecutiveListener;
import com.pwns.server.plugins.listeners.executive.WallObjectActionExecutiveListener;
import com.pwns.server.plugins.Functions;

public class BarbarianAgilityCourse implements WallObjectActionListener,
		WallObjectActionExecutiveListener, ObjectActionListener,
		ObjectActionExecutiveListener {
	 
	public static final int LOW_WALL = 164;
	public static final int LOW_WALL2 = 163;
	public static final int LEDGE = 678;
	public static final int NET = 677;
	public static final int LOG = 676;
	public static final int PIPE = 671;
	public static final int BACK_PIPE = 672;
	public static final int SWING = 675;
	private static final int HANDHOLDS = 679;
	
	public static final int[] obstacleOrder = {675, 676, 677, 678, 163, 164};
	
	@Override
	public boolean blockObjectAction(GameObject obj, String command, Player player) {
		return Functions.inArray(obj.getID(), PIPE, BACK_PIPE, SWING, LOG, LEDGE, NET, HANDHOLDS);
	}

	@Override
	public void onObjectAction(GameObject obj, String command, Player p) {
		p.setBusy(true);
		boolean fail = succeed(p);
		switch(obj.getID()) {
			case BACK_PIPE:
			case PIPE:
				p.message("You squeeze through the pipe");
				Functions.sleep(1000);
				if(p.getY() <= 551) {
					Functions.movePlayer(p, 487, 554);
				} else {
					Functions.movePlayer(p, 487, 551);
				}
				p.incExp(Functions.AGILITY, 5.0, true);
				break;
			case SWING:
				p.message("you grab the rope and try and swing across");
				Functions.sleep(1000);
				int swingDamage = (int) Math.round((p.getSkills().getLevel(3)) * 0.15D);
				if(fail) {
					p.message("you skillfully swing across the hole");
					Functions.movePlayer(p, 486, 559);
					p.incExp(Functions.AGILITY, 20.0, true);
					AgilityUtils.setNextObstacle(p, obj.getID(), obstacleOrder, 75.0);
				} else {
					p.message("Your hands slip and you fall to the level below");
					Functions.sleep(1000);
					Functions.movePlayer(p, 486, 3389);
					p.message("you land painfully on the spikes");
					p.damage(swingDamage);
					Functions.playerTalk(p, null, "ouch");
				}
				break;
			case LOG:
				int slipDamage = (int) Math.round((p.getSkills().getLevel(3)) * 0.1D);
				p.message("You stand on the slippery log");
				Functions.sleep(2000);
				if(fail) {
					Functions.movePlayer(p,489, 563);
					Functions.sleep(650);
					Functions.movePlayer(p,490, 563);
					Functions.sleep(650);
					p.message("and walk across");
					Functions.movePlayer(p, 492, 563);
					p.incExp(Functions.AGILITY, 12.0, true);
					AgilityUtils.setNextObstacle(p, obj.getID(), obstacleOrder, 75.0);
				} else {
					p.message("You lose your footing and land in the water");
					Functions.movePlayer(p, 490, 561);
					p.message("Something in the water bites you");
					p.damage(slipDamage);
				}
				break;
			case NET:
				p.message("You climb up the netting");
				Functions.movePlayer(p, 496, 1507);
				p.incExp(Functions.AGILITY, 12.0, true);
				AgilityUtils.setNextObstacle(p, obj.getID(), obstacleOrder, 75.0);
				break;
			case LEDGE:
				if(obj.getX() != 498) {
					p.setBusy(false);
					return;
				}
				int ledgeDamage = (int) Math.round((p.getSkills().getLevel(3)) * 0.15D);
				if(fail) {
					Functions.movePlayer(p, 501, 1506);
					p.message("You skillfully balance across the hole");
					p.incExp(Functions.AGILITY, 20.0, true);
					AgilityUtils.setNextObstacle(p, obj.getID(), obstacleOrder, 75.0);
				} else {
					p.message("you lose your footing and fall to the level below");
					Functions.movePlayer(p, 499, 563);
					p.message("You land painfully on the spikes");
					p.damage(ledgeDamage);
					Functions.playerTalk(p, null, "ouch");
					
				}
				break;
			case HANDHOLDS:
				p.message("You climb up the wall");
				Functions.movePlayer(p, 497, 555);
				p.incExp(Functions.AGILITY, 5.0, true);
				AgilityUtils.setNextObstacle(p, obj.getID(), obstacleOrder, 75.0);
				break;
		}
		
		p.setBusy(false);
	}

	@Override
	public boolean blockWallObjectAction(GameObject obj, Integer click, Player player) {
		return Functions.inArray(obj.getID(), LOW_WALL, LOW_WALL2);
	}

	@Override
	public void onWallObjectAction(GameObject obj, Integer click, Player p) {
		p.setBusy(true);
		switch(obj.getID()) {
			case LOW_WALL:
				p.message("You jump over the wall");
				p.setBusyTimer(1000);
				Functions.sleep(650);
				Functions.movePlayer(p, p.getX() == obj.getX() ? p.getX() - 1 : p.getX() + 1, p.getY());
				break;
			case LOW_WALL2:
				p.message("You jump over the wall");
				p.setBusyTimer(1000);
				Functions.sleep(650);
				Functions.movePlayer(p, p.getX() == obj.getX() ? p.getX() - 1 : p.getX() + 1, p.getY());
				break;
		}
		p.incExp(Functions.AGILITY, 5.0, true);
		AgilityUtils.setNextObstacle(p, obj.getID(), obstacleOrder, 75.0);
		p.setBusy(false);
	}
	
	boolean succeed(Player player) {
		int level_difference = Functions.getCurrentLevel(player, Functions.AGILITY) - 35;
		int percent = Functions.random(1, 100);
		
		if(level_difference < 0)
			return true;
		if(level_difference >= 10)
		 	level_difference = 80;
		if(level_difference >= 15)
			level_difference = 90;
		else
		 	level_difference = 60 + level_difference;
		
		return percent <= level_difference;
	}

}
