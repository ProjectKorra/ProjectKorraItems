package com.projectkorra.items.customs;

import com.projectkorra.items.attribute.Attribute;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ItemDisplays are used to display all of the previously created CustomItems
 * when the player uses the "/bi items" command. If the total number of
 * CustomItems becomes too large then the display must use multiple pages.
 */
public class PKIDisplay {
	public static ConcurrentHashMap<Player, PKIDisplay> displays;
	public static final ItemStack PREV_BUTTON = new ItemStack(Material.RED_WOOL, 1);
	public static final ItemStack NEXT_BUTTON = new ItemStack(Material.LIME_WOOL, 1);
	public static final int INV_SIZE = 27;
	public static final int INV_ITEM_QTY = INV_SIZE - 9;
	private Player player;
	private boolean showStats, showingRecipeInv;
	private int page;
	private Inventory inventory;
	private Inventory prevInv;

	/**
	 * Creates a new ItemDisplay inventory that will allow the user to cycle
	 * through the "/bi items" command
	 * 
	 * @param player the player that this inventory will display for
	 * @param showStats if the items should have their attributes shown as lore
	 * @param page the page number for the inventory (almost always 0)
	 */
	public PKIDisplay(Player player, boolean showStats, int page) {
		this.player = player;
		this.showStats = showStats;
		this.page = page;
		ItemMeta meta1 = PREV_BUTTON.getItemMeta();
		meta1.setDisplayName(ChatColor.RED + "Prev");
		PREV_BUTTON.setItemMeta(meta1);

		ItemMeta meta2 = NEXT_BUTTON.getItemMeta();
		meta2.setDisplayName(ChatColor.GREEN + "Next");
		NEXT_BUTTON.setItemMeta(meta2);
		createInventory();
	}

	/**
	 * Creates a new ItemDisplay inventory that will allow the user to cycle
	 * through the "/bi items" command
	 * 
	 * @param player the player that this inventory will display for
	 * @param showStats if the items should have their attributes shown as lore
	 */
	public PKIDisplay(Player player, boolean showStats) {
		this(player, showStats, 0);
	}

	/**
	 * Creates a new ItemDisplay inventory that will allow the user to cycle
	 * through the "/bi items" command. The items will not have their stats
	 * displayed.
	 * 
	 * @param player the player that this inventory will display for
	 */
	public PKIDisplay(Player player) {
		this(player, false, 0);
	}

	/**
	 * Parses through all of the previously created CustomItems, and places them
	 * into this newly formed Inventory. The items will be displayed for the
	 * user.
	 */
	public void createInventory() {
		if (page < 0)
			page = 0;
		if (displays.containsKey(player)) {
			Inventory inv = displays.get(player).inventory;
			if (inv.getViewers().size() > 0)
				return;
			else
				displays.remove(player);
		}

		Inventory inv = Bukkit.createInventory(null, INV_SIZE, "Bending Items");
		ArrayList<ItemStack> cistacks = new ArrayList<ItemStack>();
		for (PKItem citem : PKItem.itemList) {
			ItemStack istack = citem.generateItem();
			ItemMeta meta = istack.getItemMeta();
			if (showStats) {
				ArrayList<String> lore = new ArrayList<String>();
				for (Attribute att : citem.getAttributes()) {
					if (att.getValues().toString().length() < 40)
						lore.add(new String(att.getName() + ":" + att.getValues()));
					else
						lore.add(new String(att.getName()));
				}
				meta.setLore(lore);
			} else {
				List<String> lore = meta.getLore();
				if (lore == null)
					lore = new ArrayList<String>();

				String s = "";
				if (citem.getRecipe().size() == 0)
					s = ChatColor.RED + "Uncraftable";
				else if (citem.isUnshapedRecipe())
					s = ChatColor.GREEN + "Craftable (unshaped)";
				else
					s = ChatColor.GREEN + "Craftable (shaped)";
				lore.add(s);
				meta.setLore(lore);
			}
			istack.setItemMeta(meta);
			cistacks.add(istack);
		}

		for (int i = (INV_SIZE - 9) * page; i < (INV_SIZE - 9) * (page + 1); i++) {
			if (i >= 0 && i < cistacks.size())
				inv.addItem(cistacks.get(i));
			else
				break;
		}
		if (cistacks.size() > INV_ITEM_QTY)
			inv.setItem(INV_SIZE - 1, NEXT_BUTTON);
		if (page > 0)
			inv.setItem(INV_SIZE - 9, PREV_BUTTON);

		this.inventory = inv;
		displays.put(player, this);
		player.openInventory(inv);
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public boolean isShowStats() {
		return showStats;
	}

	public void setShowStats(boolean showStats) {
		this.showStats = showStats;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public boolean isShowingRecipeInv() {
		return showingRecipeInv;
	}

	public void setShowingRecipeInv(boolean showingRecipeInv) {
		this.showingRecipeInv = showingRecipeInv;
	}

	public Inventory getPrevInv() {
		return prevInv;
	}

	public void setPrevInv(Inventory prevInv) {
		this.prevInv = prevInv;
	}

	public static void cleanup() {
		for (PKIDisplay d : displays.values()) {
			d.getPlayer().closeInventory();
			displays.remove(d);
		}
	}

}
