package com.pwns.server.plugins.skills.agility;

import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.Functions;

public class AgilityUtils {
	
	public static void setNextObstacle(Player p, int id, int[] obstacleOrder, double bonus) {
		if(p.getAttribute("nextObstacle", -1) == -1) {
			if(id == obstacleOrder[0]) {
				p.setAttribute("nextObstacle", obstacleOrder[1]);
			} else {
				p.setAttribute("nextObstacle", obstacleOrder[0]);
			}
		} else {
			if((int) p.getAttribute("nextObstacle") != id) {
				p.setAttribute("nextObstacle", obstacleOrder[0]);
			} else {
				for(int i = 0; i < obstacleOrder.length;i++) {
					if(obstacleOrder[i] == id) {
						if(i == obstacleOrder.length - 1) {
							p.incExp(Functions.AGILITY, bonus, true);
							p.setAttribute("nextObstacle", obstacleOrder[0]);
							break;
						}
						p.setAttribute("nextObstacle", obstacleOrder[i + 1]);
						break;
					}
				}
			}
		}
	}
}
