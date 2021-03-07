package com.pwns.server.plugins.minigames.blurberrysbar;

import com.pwns.server.model.container.Item;
import com.pwns.server.model.entity.npc.Npc;
import com.pwns.server.model.entity.player.Player;
import com.pwns.server.net.rsc.ActionSender;
import com.pwns.server.plugins.listeners.action.InvActionListener;
import com.pwns.server.plugins.listeners.action.TalkToNpcListener;
import com.pwns.server.plugins.listeners.executive.DropExecutiveListener;
import com.pwns.server.plugins.listeners.executive.InvActionExecutiveListener;
import com.pwns.server.plugins.listeners.executive.TalkToNpcExecutiveListener;
import com.pwns.server.util.rsc.DataConversions;
import com.pwns.server.plugins.Functions;

public class BlurberrysBar implements TalkToNpcListener, TalkToNpcExecutiveListener, InvActionListener, InvActionExecutiveListener, DropExecutiveListener {

	public class Items {
		public static final int GNOME_COCKTAIL_GUIDE = 851;
	}
	public static final int BLURBERRY = 534;

	@Override
	public boolean blockTalkToNpc(Player p, Npc n) {
		if(n.getID() == BLURBERRY) {
			return true;
		}
		return false;
	}

	@Override
	public void onTalkToNpc(Player p, Npc n) {
		if(n.getID() == BLURBERRY) {
			if(!p.getCache().hasKey("blurberrys_bar")) {
				Functions.playerTalk(p, n, "hello");
				Functions.npcTalk(p, n, "well hello there traveller",
						"if your looking for a cocktail the barman will happily make you one");
				Functions.playerTalk(p, n, "he looks pretty busy");
				Functions.npcTalk(p, n, "I know,i just can't find any skilled staff",
						"I don't suppose your looking for some part time work?",
						"the pay isn't great but it's a good way to meet people");
				int menu = Functions.showMenu(p, n,
						"no thanks i prefer to stay this side of the bar",
						"ok then i'll give it a go");
				if(menu == 1) {
					Functions.npcTalk(p, n, "excellent",
							"it's not an easy job, i'll have to test you first",
							"i'm sure you'll be great though",
							"here, take this cocktail guide");
					Functions.addItem(p, 851, 1);
					p.message("blurberry gives you a cocktail guide");
					Functions.npcTalk(p, n, "the book tells you how to make all the cocktails we serve",
							"I'll tell you what i need and you can make them");
					Functions.playerTalk(p, n, "sounds easy enough");
					Functions.npcTalk(p, n, "take a look at the book and then come and talk to me");
					p.getCache().set("blurberrys_bar", 1);
				}
			} else {
				int stage = p.getCache().getInt("blurberrys_bar");
				switch(stage) {
				case 1:
					Functions.playerTalk(p, n, "hello blurberry");
					Functions.npcTalk(p, n, "hi, are you ready to make your first cocktail?");
					Functions.playerTalk(p, n, "absolutely");
					Functions.npcTalk(p, n, "ok then, to start with make me a fruit blast",
							"here, you'll need these ingredients",
							"but I'm afraid i can't give you any more if you mess up");
					Functions.message(p, "blurberry gives you two lemons,one orange, one pineapple");
					Functions.addItem(p, 855, 2);
					Functions.addItem(p, 857, 1);
					Functions.addItem(p, 861, 1);
					Functions.addItem(p, 834, 1);
					Functions.addItem(p, 833, 1);
					Functions.addItem(p, 13, 1);
					p.message("a cocktail shaker, a glass and a knife");
					Functions.npcTalk(p, n, "let me know when you're done");
					p.getCache().set("blurberrys_bar", 2);
					break;
				case 2:
					Functions.npcTalk(p, n, "so where's my fruit blast");
					if(Functions.hasItem(p, 866)) {
						Functions.playerTalk(p, n, "here you go");
						Functions.message(p, "you give blurberry the fruit blast");
						Functions.removeItem(p, 866, 1);
						p.incExp(Functions.COOKING, 40, true);
						p.message("he takes a sip");
						Functions.npcTalk(p, n, "hmmm... not bad, not bad at all",
								"now can you make me a drunk dragon",
								"here's what you need");
						p.message("blurberry gives you some vodka, some gin, some dwell berries...");
						Functions.addItem(p, 869, 1);
						Functions.addItem(p, 870, 1);
						Functions.addItem(p, 765, 1);
						Functions.addItem(p, 861, 1);
						Functions.addItem(p, 871, 1);
						Functions.addItem(p, 833, 1);
						p.message("... some pineapple and some cream");
						Functions.npcTalk(p, n, "i'm afraid i won't be able to give you anymore if you make a mistake though",
								"let me know when it's done");
						p.getCache().set("blurberrys_bar", 3);
					} else {
						Functions.npcTalk(p, n, "i don't know what you have there but it's no fruit blast");
					}
					break;
				case 3:
					Functions.playerTalk(p, n, "hello blurberry");
					Functions.npcTalk(p, n, "hello again traveller",
							"how did you do?");
					if(Functions.hasItem(p, 872)) {
						Functions.playerTalk(p, n, "here you go");
						Functions.message(p, "you give blurberry the drunk dragon");
						Functions.removeItem(p, 872, 1);
						p.incExp(Functions.COOKING, 40, true);
						p.message("he takes a sip");
						Functions.npcTalk(p, n, "woooo, that's some good stuff",
								"i can sell that",
								"there you go, your share of the profit");
						Functions.addItem(p, 10, 1);
						p.message("blurberry gives you 1 gold coin");
						Functions.playerTalk(p, n, "thanks");
						Functions.npcTalk(p, n, "okay then now i need an s g g");
						Functions.playerTalk(p, n, "a what?");
						Functions.npcTalk(p, n, "a short green guy, and don't bring me a gnome",
								"here's all you need");
						p.message("blurberry gives you four limes, some vodka and some equa leaves");
						Functions.addItem(p, 863, 4);
						Functions.addItem(p, 869, 1);
						Functions.addItem(p, 765, 1);
						Functions.addItem(p, 833, 1);
						p.getCache().set("blurberrys_bar", 4);
					} else {
						Functions.npcTalk(p, n, "i dont know what that is but it's no drunk dragon");
					}
					break;
				case 4:
					Functions.playerTalk(p, n, "hi blurberry");
					Functions.npcTalk(p, n, "so have you got my s g g?");
					if(Functions.hasItem(p, 874)) {
						Functions.playerTalk(p, n, "here you go");
						Functions.message(p, "you give blurberry the short green guy");
						Functions.removeItem(p, 874, 1);
						p.incExp(Functions.COOKING, 40, true);
						p.message("he takes a sip");
						Functions.npcTalk(p, n, "hmmm, not bad, not bad at all",
								"i can sell that",
								"there you go, that's your share");
						p.message("blurberry gives you 1 gold coin");
						Functions.addItem(p, 10, 1);
						Functions.npcTalk(p, n, "you doing quite well, i'm impressed",
								"ok let's try a chocolate saturday, i love them",
								"here's your ingredients");
						p.message("blurberry gives you some whisky, some milk, some equa leaves...");
						p.message("a chocolate bar, some cream and some chocolate dust");
						Functions.addItem(p, 868, 1);
						Functions.addItem(p, 22, 1);
						Functions.addItem(p, 873, 1);
						Functions.addItem(p, 337, 1);
						Functions.addItem(p, 871, 1);
						Functions.addItem(p, 772, 1);
						Functions.addItem(p, 833, 1);
						p.getCache().set("blurberrys_bar", 5);
					} else {
						Functions.npcTalk(p, n, "i dont know what that is but it's no s g g");
					}
					break;
				case 5:
					Functions.playerTalk(p, n, "hello blurberry");
					Functions.npcTalk(p, n, "hello, how did it go with the choc saturday");
					if(Functions.hasItem(p, 875)) {
						Functions.playerTalk(p, n, "here.. try some");
						Functions.message(p, "you give blurberry the cocktail");
						Functions.removeItem(p, 875, 1);
						p.incExp(Functions.COOKING, 40, true);
						p.message("he takes a sip");
						Functions.npcTalk(p, n, "that's blurberry-tastic",
								"you're quite a bartender",
								"okay ,lets test you once more",
								"try and make me a blurberry special",
								"then we'll see if you have what it takes",
								"here's your ingredients");
						Functions.addItem(p, 869, 1);
						Functions.addItem(p, 870, 1);
						Functions.addItem(p, 876, 1);
						Functions.addItem(p, 855, 3);
						Functions.addItem(p, 857, 2);
						Functions.addItem(p, 863, 1);
						Functions.addItem(p, 873, 1);
						Functions.addItem(p, 833, 1);
						Functions.playerTalk(p, n, "ok i'll do best");
						Functions.npcTalk(p, n, "I'm sure you'll make a great bar man");
						p.getCache().set("blurberrys_bar", 6);
					} else {
						Functions.playerTalk(p, n, "i haven't managed to make it yet");
						Functions.npcTalk(p, n, "ok, it's one choc saturday i need",
								"well let me know when you're done");
					}
					break;
				case 6:
					Functions.playerTalk(p, n, "hi again");
					Functions.npcTalk(p, n, "so how did you do");
					if(Functions.hasItem(p, 877)) {
						Functions.playerTalk(p, n, "I think i've made it right");
						Functions.message(p, "you give the blurberry special to blurberry");
						Functions.removeItem(p, 877, 1);
						p.message("he takes a sip");
						Functions.npcTalk(p, n, "well i never, incredible",
								"not many manage to get that right, but this is perfect",
								"It would be an honour to have you on the team");
						Functions.playerTalk(p, n, "thanks");
						Functions.npcTalk(p, n, "now if you ever want to make some money",
								"or want to improve your cooking skills just come and see me",
								"I'll tell you what drinks we need, and if you can, you make them");
						Functions.playerTalk(p, n, "what about ingredients?");
						Functions.npcTalk(p, n, "I'm afraid i can't give you anymore for free",
								"but you can buy them from heckel funch the grocer",
								"I'll always pay you more for the cocktail than you paid for the ingredients",
								"and it's a great way to learn how to prepare food and drink");
						p.getCache().set("blurberrys_bar", 7);
					} else {
						Functions.playerTalk(p, n, "I haven't managed to make it yet");
						Functions.npcTalk(p, n, "I need one blurberry special",
								"well let me know when you're done");
					}
					break;
				case 7:
					if(p.getCache().hasKey("blurberry_job")) {
						myCurrentJob(p, n);
					} else {
						Functions.playerTalk(p, n, "hello again blurberry");
						Functions.npcTalk(p, n, "well hello traveller",
								"i'm quite busy as usual, any chance you could help");
						int menu = Functions.showMenu(p, n,
								"I'm quite busy myself, sorry",
								"ok then, what do you need");
						if(menu == 0) {
							Functions.npcTalk(p, n, "that's ok, come back when you're free");
						} else if(menu == 1) {
							randomizeJob(p, n);
						}
					}
					break;
				}
			}
		}
	}

	private void randomizeJob(Player p, Npc n) {
		int randomize = DataConversions.random(0, 6);
		if(randomize == 0) {
			Functions.playerTalk(p, n, "ok then, what do you need");
			Functions.npcTalk(p, n, "can you make me one pineapple punch, one choc saturday and one drunk dragon");
			Functions.playerTalk(p, n, "ok then i'll be back soon");
		}
		if(!p.getCache().hasKey("blurberry_job")) {
			p.getCache().set("blurberry_job", randomize);
		}
	}
	
	private void myCurrentJob(Player p, Npc n) {
		int job = p.getCache().getInt("blurberry_job");
		if(job == 0) {
			Functions.playerTalk(p, n, "hi");
			Functions.npcTalk(p, n, "have you made the order?");
			if(Functions.hasItem(p, 872) && Functions.hasItem(p, 875) && Functions.hasItem(p, 879)) {
				Functions.playerTalk(p, n, "here you go, one pineapple punch, one choc saturday and one drunk dragon");
				p.message("you give blurberry one pineapple punch, one choc saturday and one drunk dragon");
				p.incExp(Functions.COOKING, 90, true);
				Functions.removeItem(p, 872, 1);
				Functions.removeItem(p, 875, 1);
				Functions.removeItem(p, 879, 1);
				Functions.npcTalk(p, n, "that's blurberry-tastic");
				p.message("blurberry gives you 100 gold coins");
				Functions.addItem(p, 10, 100);
				Functions.npcTalk(p, n, "could you make me another order");
			} else {
				Functions.playerTalk(p, n, "not yet");
				Functions.npcTalk(p, n, "ok, i need one pineapple punch, one choc saturday and one drunk dragon",
						"let me know when you're done");
				return;
			}
		}
		p.getCache().remove("blurberry_job");
		if(!p.getCache().hasKey("blurberry_jobs_completed")) {
			p.getCache().set("blurberry_jobs_completed", 1);
		} else {
			int completedJobs = p.getCache().getInt("blurberry_jobs_completed");
			p.getCache().set("blurberry_jobs_completed", (completedJobs + 1));
		}
		int menu = Functions.showMenu(p, n,
				"I'm quite busy myself, sorry",
				"ok then, what do you need");
		if(menu == 0) {
			Functions.npcTalk(p, n, "that's ok, come back when you're free");
		} else if(menu == 1) {
			
		}
	}

	@Override
	public boolean blockInvAction(Item item, Player p) {
		if(item.getID() == Items.GNOME_COCKTAIL_GUIDE) {
			return true;
		}
		return false;
	}

	@Override
	public void onInvAction(Item item, Player p) {
		if(item.getID() == Items.GNOME_COCKTAIL_GUIDE) {
			p.message("you open blurberry's cocktail book");
			p.message("inside are a list of cocktails");
			int menu = Functions.showMenu(p,
					"non alcoholic",
					"alcoholic");
			if(menu == 0) {
				int non_alcoholic = Functions.showMenu(p,
						"fruit blast",
						"pineapple punch");
				if(non_alcoholic == 0) {
					ActionSender.sendBox(p, "@yel@Fruit blast% %Mix the juice of one lemon, one orange and one pineapple in the shaker% %Pour into glass and top with slices of lemon.", true);
				} else if(non_alcoholic == 1) {
					ActionSender.sendBox(p, "@yel@Pineapple Punch% %mix the juice of two pineapples with the juice of one lemon and one orange% %pour the mix into a glass and add diced pineapple followed by diced lime% %top drink with one slice of lime", true);
				}
			} else if(menu == 1) {
				int alcoholic = Functions.showMenu(p,
						"drunkdragon",
						"sgg",
						"choc saturday",
						"blurberry special",
						"wizard blizzard");
				if(alcoholic == 0) {
					ActionSender.sendBox(p, "@yel@Drunk Dragon% %Mix vodka with gin and dwellberry juice% %Pour the mixture into a glass and add a diced pineapple.Next add a generous portion of cream% %Heat the drink briefly in a warm oven.. yum.", true);
				} else if(alcoholic == 1) {
					ActionSender.sendBox(p, "@yel@s g g - short green guy% %Mix vodka with the juice of three limes and pour into a glass% %sprinkle equa leaves over the top of the drink% %Finally add a slice of lime to finish the drink", true);
				} else if(alcoholic == 2) {
					ActionSender.sendBox(p, "@yel@Choc Saturday% %Mix together whiskey, milk, equa leaves% %Pour mixture into a glass add some chocolate and briefly heat in the oven% %Then add a generous helping of cream% %Finish of the drink with sprinkled chocolate dust", true);
				} else if(alcoholic == 3) {
					ActionSender.sendBox(p, "@yel@Blurberry Special% %Mix together vodka, gin and brandy% %Add to this the juice of two lemons and one orange and pour into the glass% %next add to the glass orange chunks and then lemon chunks% %Finish of with one lime slice and then add a sprinkling of equa leaves", true);
				} else if(alcoholic == 4) {
					ActionSender.sendBox(p, "@yel@Wizard Blizzard% %thoroughly mix together the juice of one pinapple, one orange, one lemon and one lime% %Add to this two measures of vodka and one measure of gin% %Pour the mixture into a glass, top with pineapple chunks and then add slices of lime", true);
				}
			}
		}
	}

	@Override
	public boolean blockDrop(Player p, Item i) {
		if(i.getID() == 854 || i.getID() == 867) {
			Functions.checkAndRemoveBlurberry(p, true);
			return false;
		}
		return false;
	}
}
