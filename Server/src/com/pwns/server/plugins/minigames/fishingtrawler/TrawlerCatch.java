package com.pwns.server.plugins.minigames.fishingtrawler;

import static com.pwns.server.plugins.Functions.message;
import static com.pwns.server.plugins.Functions.showBubble;

import com.pwns.server.external.EntityHandler;
import com.pwns.server.model.Skills;
import com.pwns.server.model.container.Item;
import com.pwns.server.model.entity.GameObject;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.plugins.listeners.action.ObjectActionListener;
import com.pwns.server.plugins.listeners.executive.ObjectActionExecutiveListener;
import com.pwns.server.util.rsc.DataConversions;
import com.pwns.server.plugins.Functions;

public class TrawlerCatch implements ObjectActionListener, ObjectActionExecutiveListener {

	public static final int TRAWLER_CATCH = 1106;
	public static final int[] JUNK_ITEMS = new int[] { 1875, 778, 1155, 1157, 1158, 1167, 1169, 1165, 1159, 1166,
			2078 };

	@Override
	public boolean blockObjectAction(GameObject obj, String command, Player p) {
		return obj.getID() == TRAWLER_CATCH;
	}

	@Override
	public void onObjectAction(GameObject obj, String command, Player p) {
		if (obj.getID() == TRAWLER_CATCH) {
			Functions.message(p, 1900, "you search the smelly net");
			Functions.showBubble(p, new Item(376));
			if (p.getCache().hasKey("fishing_trawler_reward")) {
				p.message("you find...");
				int fishCaught = p.getCache().getInt("fishing_trawler_reward");
				for (int fishGiven = 0; fishGiven < fishCaught; fishGiven++) {
					if (catchFish(81, p.getSkills().getLevel(Skills.FISHING))) {
						Functions.message(p, 1200, "..some manta ray");
						Functions.addItem(p, 2068, 1); // RAW MANTA RAY NOTES
						p.incExp(Skills.FISHING, 115, false);
					} else if (catchFish(75, p.getSkills().getLevel(Skills.FISHING))) {
						Functions.message(p, 1200, "..some sea turtle");
						Functions.addItem(p, 2069, 1); // RAW SEA TURTLE NOTES
						p.incExp(Skills.FISHING, 105, false);
					} else if (catchFish(70, p.getSkills().getLevel(Skills.FISHING))) {
						Functions.message(p, 1200, "..some shark");
						Functions.addItem(p, 1752, 1); // RAW SHARK NOTES
						p.incExp(Skills.FISHING, 110, false);
					} else if (catchFish(50, p.getSkills().getLevel(Skills.FISHING))) {
						Functions.message(p, 1200, "..some swordfish");
						Functions.addItem(p, 1613, 1); // RAW SWORDFISH NOTES
						p.incExp(Skills.FISHING, 100, false);
					} else if (catchFish(40, p.getSkills().getLevel(Skills.FISHING))) {
						Functions.message(p, 1200, "..some lobster...");
						Functions.addItem(p, 1616, 1); // RAW LOBSTER NOTES
						p.incExp(Skills.FISHING, 90, false);
					} else if (catchFish(30, p.getSkills().getLevel(Skills.FISHING))) {
						Functions.message(p, 1200, "..some tuna...");
						Functions.addItem(p, 1610, 1); // RAW TUNA NOTES
						p.incExp(Skills.FISHING, 80, false);
					} else if (catchFish(15, p.getSkills().getLevel(Skills.FISHING))) {
						Functions.message(p, 1200, "..some anchovies...");
						Functions.addItem(p, 1595, 1); // RAW ANCHOVIES NOTES
						p.incExp(Skills.FISHING, 40, false);
					} else if (catchFish(5, p.getSkills().getLevel(Skills.FISHING))) {
						Functions.message(p, 1200, "..some sardine...");
						Functions.addItem(p, 1598, 1); // RAW SARDINE NOTES
						p.incExp(Skills.FISHING, 20, false);
					} else if (catchFish(1, p.getSkills().getLevel(Skills.FISHING))) {
						Functions.message(p, 1200, "..some shrimp...");
						Functions.addItem(p, 1594, 1); // RAW SHRIMP NOTES
						p.incExp(Skills.FISHING, 10, false);
					} else {
						int randomJunkItem = JUNK_ITEMS[DataConversions.random(0, JUNK_ITEMS.length - 1)];
						Functions.message(p, 1200, "..some " + EntityHandler.getItemDef(randomJunkItem).getName().toLowerCase());
						Functions.addItem(p, randomJunkItem, 1);
					}
				}
				p.getCache().remove("fishing_trawler_reward");
				p.message("that's the lot");
			} else {
				p.message("the smelly net is empty");
			}
		}
	}

	public boolean catchFish(int levelReq, int level) {
		int levelDiff = level - levelReq;
		if (levelDiff <= 0) {
			return false;
		}
		return DataConversions.percentChance(offsetToPercent(levelDiff));
	}

	private static int offsetToPercent(int levelDiff) {
		return levelDiff > 40 ? 60 : 20 + levelDiff;
	}

}
