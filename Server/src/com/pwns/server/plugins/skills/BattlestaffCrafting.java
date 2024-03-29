package com.pwns.server.plugins.skills;

import static com.pwns.server.plugins.Functions.message;
import static com.pwns.server.plugins.Functions.removeItem;

import com.pwns.server.model.container.Item;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.listeners.action.InvUseOnItemListener;
import com.pwns.server.plugins.listeners.executive.InvUseOnItemExecutiveListener;
import com.pwns.server.plugins.Functions;

public class BattlestaffCrafting implements InvUseOnItemListener,
		InvUseOnItemExecutiveListener {
	
	enum Battlestaff {
		WATER_BATTLESTAFF(614, 613, 616, 100, 54, ""),
		EARTH_BATTLESTAFF(614, 627, 618, 112, 58,  ""),
		FIRE_BATTLESTAFF(614, 612, 615, 125, 62, ""),
		AIR_BATTLESTAFF(614, 626, 617, 137, 66, "");
		
		private int itemID;
		private int itemIDOther;
		private int resultItem;
		private int experience;
		private int requiredLevel;
		private String[] messages;
		
		Battlestaff(int itemOne, int itemTwo, int resultItem, int experience, int level, String... messages) {
			this.itemID = itemOne;
			this.itemIDOther = itemTwo;
			this.resultItem = resultItem;
			this.experience = experience;
			this.requiredLevel = level;
			this.messages = messages;
		}
		
		public boolean isValid(int i, int is) {
			return itemID == i && itemIDOther == is || itemIDOther == i && itemID == is;
		}
	}
	
	public boolean canCraft(Item itemOne, Item itemTwo) {
		for(Battlestaff c : Battlestaff.values()) {
			if(c.isValid(itemOne.getID(), itemTwo.getID())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void onInvUseOnItem(Player p, Item item1, Item item2) {
		Battlestaff combine = null;
		for(Battlestaff c : Battlestaff.values()) {
			if(c.isValid(item1.getID(), item2.getID())) {
				combine = c;
			}
		}
		if (p.getSkills().getLevel(Functions.CRAFTING) < combine.requiredLevel) {
			p.message("You need level " + combine.requiredLevel + " crafting to do this");
			return;
		}
		if(Functions.removeItem(p, combine.itemID, 1) && Functions.removeItem(p, combine.itemIDOther, 1)) {
			if(combine.messages.length > 1)
				Functions.message(p, combine.messages[0]);
			else
				p.message(combine.messages[0]);
			
			Functions.addItem(p, combine.resultItem, 1);
			p.incExp(Functions.CRAFTING, combine.experience, true);
			
			if(combine.messages.length > 1)
				p.message(combine.messages[1]);
		}
	}

	@Override
	public boolean blockInvUseOnItem(Player player, Item item1, Item item2) {
		return canCraft(item1, item2);
	}
}
