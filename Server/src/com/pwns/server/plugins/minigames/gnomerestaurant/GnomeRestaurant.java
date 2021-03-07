package com.pwns.server.plugins.minigames.gnomerestaurant;

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

public class GnomeRestaurant implements TalkToNpcListener, TalkToNpcExecutiveListener, InvActionListener, InvActionExecutiveListener, DropExecutiveListener {

	public class Items {
		public static final int GIANNE_COOK_BOOK = 899;

		public static final int TOMATO = 320;
		public static final int CHEESE = 319;
		public static final int EQUA_LEAVES = 873;
		public static final int GIANNE_DOUGH = 881;
		public static final int GNOME_SPICE = 898;
	}

	private class Npcs {
		public static final int ALUFT_GIANNE = 536;
	}

	@Override
	public boolean blockTalkToNpc(Player p, Npc n) {
		if(n.getID() == Npcs.ALUFT_GIANNE) {
			return true;
		}
		return false;
	}

	@Override
	public void onTalkToNpc(Player p, Npc n) {
		if(n.getID() == Npcs.ALUFT_GIANNE) {
			if(!p.getCache().hasKey("gnome_cooking")) {
				Functions.playerTalk(p, n, "hello");
				Functions.npcTalk(p, n, "well hello there,you hungry..",
						"you come to the right place",
						"eat green, eat gnome cruisine",
						"my waiter will be glad to take your order");
				Functions.playerTalk(p, n, "thanks");
				Functions.npcTalk(p, n, "on the other hand if you looking for some work",
						"i have a cook's position available");
				int menu = Functions.showMenu(p, n, "no thanks i'm no cook", "ok i'll give it a go");
				if(menu == 0) {
					Functions.npcTalk(p, n, "in that case please, eat and enjoy");
				} else if(menu == 1) {
					Functions.npcTalk(p, n, "well that's great",
							"of course i'll have to see what you're like first",
							"here, have a look at our menu");
					p.message("Aluft gives you a cook book");
					Functions.addItem(p, Items.GIANNE_COOK_BOOK, 1);
					p.getCache().set("gnome_cooking", 1);
					Functions.npcTalk(p, n, "when you've had a look come back...",
							"... and i'll let you prepare a few dishes");
					Functions.playerTalk(p, n, "good stuff");
				}
			} else {
				int stage = p.getCache().getInt("gnome_cooking");
				switch(stage) {
				case 1:
					Functions.playerTalk(p, n, "hi mr gianne");
					Functions.npcTalk(p, n, "hello my good friend",
							"what did you think");
					Functions.playerTalk(p, n, "I'm not too sure about toads legs");
					Functions.npcTalk(p, n, "they're a gnome delicacy, you'll love them",
							"but we'll start with something simple",
							"can you make me a cheese and tomato gnome batta");
					Functions.npcTalk(p, n, "here's what you need");
					Functions.message(p, 1200, "aluft gives you one tomato, some cheese...");
					Functions.addItem(p, Items.TOMATO, 1);
					Functions.addItem(p, Items.CHEESE, 1);
					p.message("...some equa leaves and some plain dough");
					Functions.addItem(p, Items.EQUA_LEAVES, 1);
					Functions.addItem(p, Items.GIANNE_DOUGH, 1);
					p.getCache().set("gnome_cooking", 2);
					Functions.playerTalk(p, n, "thanks");
					Functions.npcTalk(p, n, "Let me know how you get on");
					break;
				case 2:
					Functions.playerTalk(p, n, "Hi mr gianne");
					Functions.npcTalk(p, n, "call me aluft");
					Functions.playerTalk(p, n, "ok");
					Functions.npcTalk(p, n, "so how did you get on?");
					if(Functions.hasItem(p, 901)) {
						Functions.playerTalk(p, n, "no problem, it was easy");
						Functions.message(p, 1900, "you give aluft the gnome batta");
						Functions.removeItem(p, 901, 1);
						p.message("he takes a bite");
						Functions.npcTalk(p, n, "not bad...not bad at all",
								"ok now for something a little harder",
								"try and make me a choc bomb.. they're my favorite",
								"here's what you need");
						Functions.message(p, 1200, "aluft gives you four bars of chocolate");
						Functions.addItem(p, 337, 4);
						Functions.message(p, 1200, "some equa leaves, some chocolate dust...");
						Functions.addItem(p, 873, 1);
						Functions.addItem(p, 772, 1);
						p.message("...some gianne dough and some cream");
						Functions.addItem(p, 881, 1);
						Functions.addItem(p, 871, 2);
						Functions.playerTalk(p, n, "ok aluft, i'll be back soon");
						Functions.npcTalk(p, n, "good stuff");
						p.getCache().set("gnome_cooking", 3);
					} else {
						Functions.playerTalk(p, n, "erm.. not quite done yet");
						Functions.npcTalk(p, n, "ok, let me know when you are",
								"i need one cheese and tomato batta");
					}
					break;
				case 3:
					Functions.playerTalk(p, n, "hi aluft");
					Functions.npcTalk(p, n, "hello there, how did you get on");
					if(Functions.hasItem(p, 907)) {
						Functions.playerTalk(p, n, "here you go");
						Functions.removeItem(p, 907, 1);
						Functions.message(p, 1200, "you give aluft the choc bomb");
						p.message("he takes a bite");
						Functions.npcTalk(p, n, "yes, yes, yes, that's superb",
								"i'm really impressed");
						Functions.playerTalk(p, n, "i'm glad");
						Functions.npcTalk(p, n, "ok then, now can you make me a toad batta",
								"here's what you need");
						Functions.addItem(p, Items.GIANNE_DOUGH, 1);
						Functions.addItem(p, Items.EQUA_LEAVES, 1);
						Functions.addItem(p, Items.GNOME_SPICE, 1);
						Functions.message(p, 1900, "mr gianne gives you some dough, some equaleaves...");
						p.message("...and some gnome spice");
						Functions.npcTalk(p, n, "i'm afraid all are toads legs are served fresh");
						Functions.playerTalk(p, n, "nice!");
						Functions.npcTalk(p, n, "so you'll need to go to the swamp on ground level",
								"and catch a toad",
								"let me know when the batta's ready");
						p.getCache().set("gnome_cooking", 4);
					} else {
						Functions.playerTalk(p, n, "i haven't made it yet");
						Functions.npcTalk(p, n, "just follow the instructions carefully",
								"i need one choc bomb");
					}
					break;
				case 4:
					Functions.playerTalk(p, n, "hi mr gianne");
					Functions.npcTalk(p, n, "aluft");
					Functions.playerTalk(p, n, "sorry, aluft");
					Functions.npcTalk(p, n, "so where's my toad batta?");
					if(Functions.hasItem(p, 902)) {
						Functions.playerTalk(p, n, "here you go, easy");
						Functions.message(p, 1900, "you give mr gianne the toad batta");
						Functions.removeItem(p, 902, 1);
						p.message("he takes a bite");
						Functions.npcTalk(p, n, "ooh, that's some good toad",
								"very nice",
								"let's see if you can make a worm hole");
						Functions.playerTalk(p, n, "a wormhole?");
						Functions.npcTalk(p, n, "yes, it's in the cooking guide i gave you",
								"you'll have to get the worms from the swamp",
								"but here's everything else you'll need",
								"let me know when your done");
						Functions.addItem(p, Items.GIANNE_DOUGH, 1);
						Functions.addItem(p, 241, 2);
						Functions.addItem(p, Items.EQUA_LEAVES, 1);
						p.getCache().set("gnome_cooking", 5);
					} else {
						Functions.playerTalk(p, n, "i'm not done yet");
						Functions.npcTalk(p, n, "ok, quick as you can though");
						Functions.playerTalk(p, n, "no problem");
					}
					break;
				case 5:
					Functions.playerTalk(p, n, "hello again aluft");
					Functions.npcTalk(p, n, "hello traveller, how did you do?");
					if(Functions.hasItem(p, 909)) {
						Functions.playerTalk(p, n, "here, see what you think");
						Functions.message(p, 1900, "you give mr gianne the worm hole");
						Functions.removeItem(p, 909, 1);
						p.message("he takes a bite");
						Functions.npcTalk(p, n, "hmm, that's actually really good",
								"how about you make me some toad crunchies for desert",
								"then i'll decide whether i can take you on");
						Functions.playerTalk(p, n, "toad crunchies?");
						Functions.npcTalk(p, n, "that's right, here's all you need",
								"except the toad");
						Functions.addItem(p, Items.GIANNE_DOUGH, 1);
						Functions.addItem(p, Items.EQUA_LEAVES, 1);
						p.message("mr gianne gives you some gianne dough and some equa leaves");
						Functions.npcTalk(p, n, "let me know when your done");
						p.getCache().set("gnome_cooking", 6);
					} else {
						Functions.playerTalk(p, n, "i'm not done yet");
						Functions.npcTalk(p, n, "ok, quick as you can though",
								"i need one worm hole");
						Functions.playerTalk(p, n, "no problem");
					}
					break;
				case 6:
					Functions.playerTalk(p, n, "hi aluft");
					Functions.npcTalk(p, n, "hello, how are you getting on?");
					if(Functions.hasItem(p, 913)) {
						Functions.playerTalk(p, n, "here, try it");
						Functions.message(p, 1900, "you give mr gianne the toad crunchie");
						Functions.removeItem(p, 913, 1);
						p.message("he takes a bite");
						Functions.npcTalk(p, n, "well for a human you certainly can cook",
								"i'd love to have you on the team",
								"if you ever want to make some money",
								"or want to improve your cooking skills just come and see me",
								"i'll tell you what meals i need, and if you can, you make them");
						Functions.playerTalk(p, n, "what about ingredients?");
						Functions.npcTalk(p, n, "well you know where to find toads and worms",
								"you can buy the rest from hudo glenfad the grocer",
								"i'll always pay you much more for the meal than you paid for the ingredients",
								"and it's a great way to improve your cooking skills");
						p.getCache().set("gnome_cooking", 7); // COMPLETED JOB!
					} else {
						Functions.playerTalk(p, n, "no luck so for");
						Functions.npcTalk(p, n, "ok then but don't take too long",
								"i need one toad crunchie");
					}
					break;
				case 7:
					/**
					 * Completed and hired for job.
					 */
					if(p.getCache().hasKey("gnome_restaurant_job")) {
						Functions.playerTalk(p, n, "hi aluft");
						myCurrentJob(p, n);
					} else {
						Functions.playerTalk(p, n, "hello again aluft");
						Functions.npcTalk(p, n, "well hello there traveller",
								"have you come to help me out?");
						int menu = Functions.showMenu(p, n,
								"sorry aluft, i'm too busy",
								"i would be glad to help");
						if(menu == 0) {
							Functions.npcTalk(p, n, "no worries, let me know when you're free");
						} else if(menu == 1) {
							Functions.npcTalk(p, n, "good stuff");
							randomizeJob(p, n);
						}
					}
					break;
				}
			}
		}
	}

	private void myCurrentJob(Player p, Npc n) {
		int job = p.getCache().getInt("gnome_restaurant_job");
		if(job == 0) {
			Functions.npcTalk(p, n, "hello again, are the dishes ready?");
			if(Functions.hasItem(p, 904, 2) && Functions.hasItem(p, 902) && Functions.hasItem(p, 906)) {
				Functions.playerTalk(p, n, "all done, here you go");
				Functions.message(p, 1900, "you give aluft two worm batta's a veg batta and a toad batta");
				p.incExp(Functions.COOKING, 106.25, true);
				Functions.removeItem(p, 904, 2);
				Functions.removeItem(p, 902, 1);
				Functions.removeItem(p, 906, 1);
				Functions.npcTalk(p, n, "they look great, well done",
						"here's your share of the profit");
				p.message("mr gianne gives you 45 gold coins");
				Functions.addItem(p, 10, 45);
			} else {
				Functions.playerTalk(p, n, "i'm not done yet");
				Functions.npcTalk(p, n, "i need  two worm batta's, one toad batta",
						"...and one veg batta please",
						"be as quick as you can");
				return;
			}
		} else if(job == 1) {
			Functions.npcTalk(p, n, "hello again, are the dishes ready?");
			if(Functions.hasItem(p, 907) && Functions.hasItem(p, 911, 2) && Functions.hasItem(p, 913, 2)) {
				Functions.playerTalk(p, n, "here you go aluft");
				Functions.message(p, 1900, "you give aluft choc bomb, two choc crunchies and two toad crunchies");
				Functions.removeItem(p, 907, 1);
				Functions.removeItem(p, 911, 2);
				Functions.removeItem(p, 913, 2);
				p.incExp(Functions.COOKING, 168.75, true);
				Functions.npcTalk(p, n, "they look great, well done",
						"here's your share of the profit");
				p.message("mr gianne gives you 75 gold coins");
				Functions.addItem(p, 10, 75);
			} else {
				Functions.playerTalk(p, n, "i'm not done yet");
				Functions.npcTalk(p, n, "ok, i need a choc bomb, two choc crunchies and two toad crunchies",
						"don't take too long",
						"it's a full house tonight");
				return;
			}
		} else if(job == 2) {
			Functions.npcTalk(p, n, "hello again traveller how did you do?");
			if(Functions.hasItem(p, 911, 2)) {
				Functions.playerTalk(p, n, "all done, here you go");
				Functions.message(p, 1900, "you give aluft the two choc crunchies");
				Functions.removeItem(p, 911, 2);
				p.incExp(Functions.COOKING, 75, true);
				Functions.npcTalk(p, n, "they look great, well done",
						"here's your share of the profit");
				p.message("mr gianne gives you 30 gold coins");
				Functions.addItem(p, 10, 30);
			} else {
				Functions.playerTalk(p, n, "i'm not done yet");
				Functions.npcTalk(p, n, "i just need two choc crunchies",
						"should be easy");
				return;
			}
		} else if(job == 3) {
			Functions.npcTalk(p, n, "hello again traveller how did you do?");
			if(Functions.hasItem(p, 907) && Functions.hasItem(p, 911, 2)) {
				Functions.playerTalk(p, n, "here you go aluft");
				Functions.message(p, 1900, "you give aluft one choc bomb and two choc crunchies");
				Functions.removeItem(p, 907, 1);
				Functions.removeItem(p, 911, 2);
				p.incExp(Functions.COOKING, 106.25, true);
				Functions.npcTalk(p, n, "they look great, well done",
						"here's your share of the profit");
				p.message("mr gianne gives you 45 gold coins");
				Functions.addItem(p, 10, 45);
			} else {
				Functions.playerTalk(p, n, "i'm not done yet");
				Functions.npcTalk(p, n, "i need one choc bomb and two choc crunchies please");
				return;
			}
		} else if(job == 4) {
			Functions.npcTalk(p, n, "hello again traveller how did you do?");
			if(Functions.hasItem(p, 906, 2) && Functions.hasItem(p, 909)) {
				Functions.playerTalk(p, n, "here you go aluft");
				Functions.message(p, 1900, "you give aluft two veg batta's and a worm hole");
				Functions.removeItem(p, 906, 2);
				Functions.removeItem(p, 909, 1);
				p.incExp(Functions.COOKING, 106.25, true);
				Functions.npcTalk(p, n, "they look great, well done",
						"here's your share of the profit");
				p.message("mr gianne gives you 45 gold coins");
				Functions.addItem(p, 10, 45);
			} else {
				Functions.playerTalk(p, n, "i'm not done yet");
				Functions.npcTalk(p, n, "ok, i need two veg batta's and one worm hole",
						"ok, but try not to take too long",
						"it's a full house tonight");
				return;
			}
		} else if(job == 5) {
			Functions.npcTalk(p, n, "hello again, are the dishes ready?");
			if(Functions.hasItem(p, 908) && Functions.hasItem(p, 910) && Functions.hasItem(p, 909)) {
				Functions.playerTalk(p, n, "all done, here you go");
				Functions.message(p, 1900, "you give aluft one veg ball, one twisted toads legs and one worm hole");
				Functions.removeItem(p, 908, 1);
				Functions.removeItem(p, 910, 1);
				Functions.removeItem(p, 909, 1);
				p.incExp(Functions.COOKING, 106.25, true);
				Functions.npcTalk(p, n, "they look great, well done",
						"here's your share of the profit");
				p.message("mr gianne gives you 45 gold coins");
				Functions.addItem(p, 10, 45);
			} else {
				Functions.playerTalk(p, n, "i'm not done yet");
				Functions.npcTalk(p, n, "i need  one veg ball, one twisted toads legs...",
						"...and one worm hole please");
				return;
			}
		} else if(job == 6) {
			Functions.npcTalk(p, n, "hello again traveller how did you do?");
			if(Functions.hasItem(p, 901) && Functions.hasItem(p, 908) && Functions.hasItem(p, 912, 2)) {
				Functions.message(p, 1900, "you give aluft one cheese and tomato batta,one vegball and two portions of worm crunchies");
				Functions.removeItem(p, 901, 1);
				Functions.removeItem(p, 908, 1);
				Functions.removeItem(p, 912, 2);
				p.incExp(Functions.COOKING, 168.75, true);
				Functions.npcTalk(p, n, "they look great, well done",
						"here's your share of the profit");
				p.message("mr gianne gives you 75 gold coins");
				Functions.addItem(p, 10, 75);
			} else {
				Functions.playerTalk(p, n, "i'm not done yet");
				Functions.npcTalk(p, n, "i need one cheese and tomato batta,one veg ball...",
						"...and two portions of worm crunchies please");
				return;
			}
		}
		p.getCache().remove("gnome_restaurant_job");
		if(!p.getCache().hasKey("gnome_jobs_completed")) {
			p.getCache().set("gnome_jobs_completed", 1);
		} else {
			int completedJobs = p.getCache().getInt("gnome_jobs_completed");
			p.getCache().set("gnome_jobs_completed", (completedJobs + 1));
		}
		Functions.npcTalk(p, n, "can you stay and make another dish?");
		int menu = Functions.showMenu(p, n,
				"sorry aluft, i'm too busy",
				"i would be glad to help");
		if(menu == 0) {
			Functions.npcTalk(p, n, "no worries, let me know when you're free");
		} else if(menu == 1) {
			Functions.npcTalk(p, n, "your a life saver");
			randomizeJob(p, n);
		}
	}

	private void randomizeJob(Player p, Npc n) {
		int randomize = DataConversions.random(0, 6);
		if(randomize == 0) {
			Functions.npcTalk(p, n, "can you make me a two worm batta's, one toad batta...",
					"...and one veg batta please");
			Functions.playerTalk(p, n, "ok then");
		} else if(randomize == 1) {
			Functions.npcTalk(p, n, "ok, i need a choc bomb, two choc crunchies and two toad crunchies");
			Functions.playerTalk(p, n, "no problem");
		} else if(randomize == 2) {
			Functions.npcTalk(p, n, "i just need two choc crunchies please");
			Functions.playerTalk(p, n, "no problem");
		} else if(randomize == 3) {
			Functions.npcTalk(p, n, "i just need one choc bomb and two choc crunchies please");
			Functions.playerTalk(p, n, "no problem");
		} else if(randomize == 4) {
			Functions.npcTalk(p, n, "excellent, i need two veg batta's and one worm hole");
			Functions.playerTalk(p, n, "no problem");
		} else if(randomize == 5) {
			Functions.npcTalk(p, n, "can you make me a one veg ball, one twisted toads legs...",
					"...and one worm hole please");
			Functions.playerTalk(p, n, "ok then");
		} else if(randomize == 6) {
			Functions.npcTalk(p, n, "i need one cheese and tomato batta,one veg ball...",
					"...and two portions of worm crunchies please");
			Functions.playerTalk(p, n, "ok, i'll do my best");
		}
		if(!p.getCache().hasKey("gnome_restaurant_job")) {
			p.getCache().set("gnome_restaurant_job", randomize);
		}
	}

	@Override
	public boolean blockInvAction(Item item, Player p) {
		if(item.getID() == Items.GIANNE_COOK_BOOK) {
			return true;
		}
		return false;
	}

	@Override
	public void onInvAction(Item item, Player p) {
		if(item.getID() == Items.GIANNE_COOK_BOOK) {
			p.message("you open aluft's cook book");
			p.message("inside are various gnome dishes");
			int menu = Functions.showMenu(p,
					"gnomebattas",
					"gnomebakes",
					"gnomecrunchies");
			if(menu == 0) {
				int battaMenu = Functions.showMenu(p,
						"cheese and tomato batta",
						"toad batta",
						"worm batta",
						"fruit batta",
						"veg batta");
				if(battaMenu == 0) {
					ActionSender.sendBox(p, "@yel@Cheese and tomato batta% %Make some gnome batta dough from the Gianne dough% %Bake the gnome batta, once removed place cheese and then tomato on top% %Place batta in oven once more untill cheese has melted, remove and top with equaleaves.", true);
				} else if(battaMenu == 1) {
					ActionSender.sendBox(p, "@yel@Toad batta% %Make some gnome batta dough from the Gianne dough% %Bake the gnome batta, mix some equa leaves with your toad's legs and then add some gnomespice% %Place the seasoned toads legs on the batta, add cheese and bake once more.", true);
				} else if(battaMenu == 2) {
					ActionSender.sendBox(p, "@yel@Worm batta% %Make some gnome batta dough from the Gianne dough% %Bake the gnome batta, mix some gnomespice with a king worm% %Place the seasoned worm on the batta, add cheese and bake once more% %Remove from oven and finish with a sprinkle of equaleaves...yum.", true);
				} else if(battaMenu == 3) {
					ActionSender.sendBox(p, "@yel@Fruit batta% %Make some gnome batta dough from the Gianne dough% %Bake the gnome batta and remove from oven, then lay four sprigs of equa leaves on the batta and bake once more% %Add chunks of pineapple, orange and lime then finish with a sprinkle of gnomespice.", true);
				} else if(battaMenu == 4) {
					ActionSender.sendBox(p, "@yel@Veg Batta% %Make some gnome batta dough from the Gianne dough% %Bake the gnome batta then add an onion, two tomatos, one cabbage and some dwellberrys, next place the batta in the oven% %Add some cheese and place in the oven once more% %To finish add a sprinkle of equa leaves.", true);
				}
			} else if(menu == 1) {
				int bakesMenu = Functions.showMenu(p,
						"choc bomb",
						"veg ball",
						"wormhole",
						"tangled toads legs");
				if(bakesMenu == 0) {
					ActionSender.sendBox(p, "@yel@Choc bomb% %Make some gnomebowl dough from the Gianne dough% %Bake the gnome bowl% %Add to the gnomebowl four bars of chocolate and one sprig of equaleaves% %Bake the gnome bowl in an oven% %Next add two portions of cream and finish with a sprinkle of chocolate dust.", true);
				} else if(bakesMenu == 1) {
					ActionSender.sendBox(p, "@yel@Vegball% %Make some gnomebowl dough from the Gianne dough% %Bake the gnomebowl% %Add two onions,two potatoes and some gnome spice% %Bake the gnomebowl once more% %To finish sprinkle with equaleaves", true);
				} else if(bakesMenu == 2) {
					ActionSender.sendBox(p, "@yel@Worm hole% %Make some gnomebowl dough from the Gianne dough% %Bake the gnomebowl% %Add six king worms, two onions and some gnome spice% %Bake the gnomebowl once more% %To finish sprinkle with equaleaves", true);
				} else if(bakesMenu == 3) {
					ActionSender.sendBox(p, "@yel@Tangled toads legs% %Make some gnomebowl dough from the Gianne dough% %Bake the gnomebowl% %Add two portions of cheese, five pairs of toad's legs, two sprigs of equa leaves, some dwell berries and two sprinkle's of gnomespice% %Bake the gnomebowl once more", true);
				}
			} else if(menu == 2) {
				int crunchiesMenu = Functions.showMenu(p,
						"choc crunchies",
						"worm crunchies",
						"toad crunchies",
						"spice crunchies");
				if(crunchiesMenu == 0) {
					ActionSender.sendBox(p, "@yel@choc crunchies% %Mix some gnome spice and two bars of chocolate with the Gianne dough% %Use dough to make gnomecrunchie dough% %Bake in oven% %Add of sprinkle of chocolate dust", true);
				} else if(crunchiesMenu == 1) {
					ActionSender.sendBox(p, "@yel@worm crunchies% %Mix some gnome spice, two king worms and some equa leaves with the Gianne dough% %Use dough to make gnomecrunchie dough% %Bake in oven% %Add of sprinkle of gnome spice", true);
				} else if(crunchiesMenu == 2) {
					ActionSender.sendBox(p, "@yel@toad crunchies% %Mix some gnome spice and two pair's of toads legs with the Gianne dough% %Use dough to make gnomecrunchie dough% %Bake in oven% %Add of sprinkle of equa leaves", true);
				} else if(crunchiesMenu == 3) {
					ActionSender.sendBox(p, "@yel@spice crunchies% %Mix three sprinkles of gnomespice and two sprigs of equa leaves with Gianne dough% %Use dough to make gnomecrunchie dough% %Bake in oven% %Add of sprinkle of gnome spice", true);
				}
			}
		}
	}

	@Override
	public boolean blockDrop(Player p, Item i) {
		if(i.getID() == Items.GIANNE_DOUGH + 9 || i.getID() == Items.GIANNE_DOUGH + 4 || i.getID() == Items.GIANNE_DOUGH + 3) {
			Functions.resetGnomeCooking(p);
			return false;
		}
		return false;
	}
}
