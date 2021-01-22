package com.projectkorra.items;

import org.bukkit.ChatColor;

import java.util.concurrent.ConcurrentHashMap;

public class Messages {
	/**
	 * logDelay is used to store the times that a message was logged by
	 * logTimedMessage.
	 **/
	public static ConcurrentHashMap<String, Long> logDelay = new ConcurrentHashMap<String, Long>();

	/** The amount of stats that are displayed when calling /bi stats **/
	public static final int LINES_PER_PAGE = 10;

	/**
	 * The min amount of milliseconds before a message gets logged by
	 * logTimedMessage, prevents spam
	 **/
	public static final long LOG_DELAY = 30000;

	public static final String[] RELOAD_ALIAS = { "reload", "r", "reloadconfig", "restart" };
	public static final String[] LIST_ALIAS = { "list", "l" };
	public static final String[] ITEMS_ALIAS = { "items", "item", "i", "bi", "ki", "pki", "projectkorraitems", "bendingitems", "korraitems" };
	public static final String[] DISPLAY_ALIAS = { "display", "d", "disp", "show" };
	public static final String[] STATS_ALIAS = { "stats", "s", "stat", "attr", "attri", "attribute" };
	public static final String[] RECIPE_ALIAS = { "recipe", "rec", "formula" };
	public static final String[] GIVE_ALIAS = { "give", "g", "giv" };
	public static final String[] EQUIP_ALIAS = { "equip", "e", "equ", "eq", "equi" };

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
	public static final String BAD_VALUE = "an issue occured with this value";
	public static final String BAD_PARTICLE_EFFECT = "this particle effect could not be found";
	public static final String ERROR_GENERATING_ITEM = "an error occurred while generating the custom item";
	public static final String MISSING_VALUES = "an attribute is missing it's values";
	public static final String MISSING_EFFECT_VALUES = "an effect is missing values (Effect:Power:Duration)";

	public static final String NO_PERM = ChatColor.RED + "You don't have permission to do that.";
	public static final String CONFIG_RELOADED = ChatColor.AQUA + "Bending items config reloaded.";
	public static final String PLAYER_ONLY = ChatColor.RED + "You must be a player to use this command.";
	public static final String INVALID_PLAYER = ChatColor.RED + "Player not found.";
	public static final String ITEM_NOT_FOUND = ChatColor.RED + "Item not found.";
	public static final String NO_ANVIL = ChatColor.RED + "You can't place this item in an anvil.";
	public static final String EQUIP_OFF = ChatColor.YELLOW + "You have unequipped";
	public static final String EQUIP_ON = ChatColor.GREEN + "You have equipped";
	public static final String CANT_EQUIP = ChatColor.RED + "You cannot equip this item.";
	public static final String NOT_INT = ChatColor.RED + "Not an integer!";

	public static final String USAGE = ChatColor.RED + "/bi items " + ChatColor.YELLOW + "Display all items, click to see their recipes.\n" + ChatColor.RED + "/bi items stats " + ChatColor.YELLOW + "Display all items and their stats.\n" + ChatColor.RED + "/bi stats <page> " + ChatColor.YELLOW + "Displays each stat and it's description.\n" + ChatColor.RED + "/bi stats [phrase] <page> " + ChatColor.YELLOW + "Displays only the stats that contain the phrase.\n" + ChatColor.RED + "/bi equip " + ChatColor.YELLOW + "Equips the current item so that when you change slots the item will follow.\n" + ChatColor.RED + "/bi give " + ChatColor.YELLOW + "(OP) send players custom items.";

	public static final String GIVE_USAGE = ChatColor.RED + "/bi give [item] <qty> " + ChatColor.YELLOW + "Gives yourself a bending item.\n" + ChatColor.RED + "/bi give [item] [player] <qty> " + ChatColor.YELLOW + "Gives the player a bending item.\n" + ChatColor.RED + "/bi give display " + ChatColor.YELLOW + "Shows the exact names of all the items.";

	public static final String STATS_USAGE = ChatColor.RED + "/bi stats <page> " + ChatColor.YELLOW + "Displays each stat and it's description.\n" + ChatColor.RED + "/bi stats [phrase] <page> " + ChatColor.YELLOW + "Displays only the stats that contain the phrase.\n" + ChatColor.RED + "ex:\n/bi stats Air\n/bi stats Tornado\n/bi stats blast 2";

	public static final String ITEM_DESTROYED = ChatColor.RED + "has ran out";

	/**
	 * Logs a message and stores the value in a timer, preventing it from being
	 * displayed again for a set amount of time.
	 * 
	 * @param msg the message to log
	 * @param delay the delay between duplicating msg
	 */
	public static void logTimedMessage(String msg, long delay) {
		if (logDelay.containsKey(msg) && System.currentTimeMillis() - logDelay.get(msg) < LOG_DELAY)
			return;
		logDelay.put(msg, System.currentTimeMillis() + delay);
		ProjectKorraItems.log.info(msg);
	}

	/**
	 * Logs a message and stores the value in a timer, preventing it from being
	 * displayed again for a set amount of time.
	 * 
	 * @param msg the message to log
	 * @param delay the delay between duplicating msg
	 */
	public static void logTimedMessage(String msg) {
		logTimedMessage(msg, Messages.LOG_DELAY);
	}
}
