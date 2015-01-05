package com.projectkorra.ProjectKorraItems;

import org.bukkit.ChatColor;

public class Messages {
	public static final int LINES_PER_PAGE = 10;
	
	public static final String[] RELOAD_ALIAS = {"reload", "r", "reloadconfig", "restart"};
	public static final String[] ITEMS_ALIAS = {"items", "i", "item"};
	public static final String[] DISPLAY_ALIAS = {"display", "d", "disp", "show"};
	public static final String[] STATS_ALIAS = {"stats", "s", "attr", "attri", "attribute"};
	public static final String[] RECIPE_ALIAS = {"recipe", "rec", "formula"};
	public static final String[] GIVE_ALIAS = {"give", "g", "giv"};
	
	public static final String BAD_PREFIX = "an error occured while parsing the prefix";
	public static final String BAD_ITEM = "an error occured while parsing the item";
	public static final String NO_CONFIG = "config.yml was not found";
	public static final String BAD_FILE = "a problem occured while reading config.yml";
	public static final String DUPLICATE_ITEM = "an item with this name already exists";
	public static final String BAD_RECIPE_MAT = "invalid recipe material";
	public static final String BAD_RECIPE = "an error occured while parsing the recipe";
	public static final String BAD_DAMAGE = "invalid durability value";
	public static final String BAD_QUANTITY = "invalid amount value";
	public static final String BAD_LORE = "invalid lore";
	public static final String BAD_GLOW = "invalid glow value";
	public static final String BAD_DNAME = "invalid display name";
	public static final String BAD_MATERIAL = "invalid material";
	public static final String BAD_NAME = "invalid name";
	public static final String BAD_CHARGES = "invalid charges value";
	public static final String MISSING_VALUES = "an attribute is missing it's values";
	
	public static final String NO_PERM = ChatColor.RED + "you don't have permission to use this command";
	public static final String CONFIG_RELOADED = ChatColor.AQUA + "Bending items config reloaded";
	public static final String PLAYER_ONLY = ChatColor.RED + "you must be a player to use this command";
	public static final String INVALID_PLAYER = ChatColor.RED + "player not found";
	public static final String ITEM_NOT_FOUND = ChatColor.RED + "item not found";
	public static final String NO_ANVIL = ChatColor.RED + "you can't place this item in an anvil";
	
	public static final String USAGE = 	ChatColor.RED + "/bi items " + ChatColor.YELLOW + "Display all items, click to see their recipes.\n" + 
									   	ChatColor.RED + "/bi items stats " + ChatColor.YELLOW + "Display all items and their stats.\n" +
									   	ChatColor.RED + "/bi stats <page> " + ChatColor.YELLOW + "Displays each stat and it's description.\n" +
									   	ChatColor.RED + "/bi stats [phrase] <page> " + ChatColor.YELLOW + "Displays only the stats that contain the phrase.";
	
	public static final String GIVE_USAGE = ChatColor.RED + "/bi give [item] <qty> " + ChatColor.YELLOW + "Gives yourself a bending item.\n" +
											ChatColor.RED + "/bi give [item] [player] <qty> " + ChatColor.YELLOW + "Gives the player a bending item.\n" +
											ChatColor.RED + "/bi give display " + ChatColor.YELLOW + "Shows the exact names of all the items.";
	
	public static final String STATS_USAGE = 	ChatColor.RED + "/bi stats <page> " + ChatColor.YELLOW + "Displays each stat and it's description.\n" +
		   										ChatColor.RED + "/bi stats [phrase] <page> " + ChatColor.YELLOW + "Displays only the stats that contain the phrase.\n" +
		   										ChatColor.RED + "ex:\n/bi stats Air\n/bi stats Tornado\n/bi stats blast 2";

	public static final String ITEM_DESTROYED = ChatColor.RED + " has ran out of charges and has been destroyed";


	
}
