package com.pwns.server.plugins.npcs;

import java.util.ArrayList;

import com.pwns.server.content.achievement.Achievement;
import com.pwns.server.content.achievement.AchievementSystem;
import com.pwns.server.model.entity.GameObject;
import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.listeners.action.ObjectActionListener;
import com.pwns.server.plugins.listeners.action.TalkToNpcListener;
import com.pwns.server.plugins.listeners.executive.ObjectActionExecutiveListener;
import com.pwns.server.plugins.listeners.executive.TalkToNpcExecutiveListener;
import com.pwns.server.plugins.menu.Menu;
import com.pwns.server.plugins.menu.Option;

public class TaskPlugin implements TalkToNpcListener, TalkToNpcExecutiveListener, ObjectActionExecutiveListener, ObjectActionListener {

	@Override
	public void onObjectAction(GameObject obj, String command, Player player) {
		ArrayList<Achievement> availableTasks = AchievementSystem.getAvailableQuestsForEntity(player, obj);
		if(availableTasks.size() > 1) {
			player.message("You can get multiple tasks from this object");
			Menu menu = new Menu();
			for(Achievement task : availableTasks) {
				menu.addOption(new Option(task.getName()) {
					@Override
					public void action() {
						//AchievementSystem.triggerTask(player, obj, task);
					}
				});
			}
			menu.showMenu(player);
		} else if(availableTasks.size() == 1) {
			//AchievementSystem.triggerTask(player, obj, availableTasks.get(0));
		}
	
	}
	
	@Override
	public void onTalkToNpc(Player p, Npc n) {
		ArrayList<Achievement> availableTasks = AchievementSystem.getAvailableQuestsForEntity(p, n);
		if(availableTasks.size() > 1) {
			p.message("You can get multiple tasks from this character");
			Menu menu = new Menu();
			for(Achievement task : availableTasks) {
				menu.addOption(new Option(task.getName()) {
					@Override
					public void action() {
						//AchievementSystem.triggerTask(p, n, task);
					}
				});
			}
			menu.showMenu(p);
		} else if(availableTasks.size() == 1) {
			//AchievementSystem.triggerTask(p, n, availableTasks.get(0));
		}
	}
	
	@Override
	public boolean blockObjectAction(GameObject obj, String command, Player player) {
		return AchievementSystem.getAvailableQuestsForEntity(player, obj).size() > 0;
	}

	@Override
	public boolean blockTalkToNpc(Player p, Npc n) {
		return AchievementSystem.getAvailableQuestsForEntity(p, n).size() > 0;
	}
}
