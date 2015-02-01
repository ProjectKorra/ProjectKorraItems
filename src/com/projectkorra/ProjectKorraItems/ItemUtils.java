package com.projectkorra.ProjectKorraItems;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUtils {

	/**
	 * Returns a list of all of the players effectable items.
	 * @param player the player with the items
	 * @return a list of items
	 */
	public static ArrayList<ItemStack> getPlayerItems(Player player) {
		ArrayList<ItemStack> istacks = new ArrayList<ItemStack>();
		for(ItemStack istack : player.getInventory().getArmorContents())
			istacks.add(istack);
		istacks.add(player.getItemInHand());
		return istacks;
	}
	
	/**
	 * A quick way to set the lore of an item
	 * @param istack the item that will have its lore set
	 * @param lore the strings that make up the lore
	 * @return true if the lore was set correctly
	 */
	public static boolean setLore(ItemStack istack, List<String> lore) {
		if(istack == null || lore == null)
			return false;
		ItemMeta meta = istack.getItemMeta();
		if(meta == null)
			return false;
		meta.setLore(lore);
		istack.setItemMeta(meta);
		return true;
	}
}
