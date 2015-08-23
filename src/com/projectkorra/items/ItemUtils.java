package com.projectkorra.items;

import com.projectkorra.items.attribute.AttributeList;
import com.projectkorra.items.attribute.AttributeUtils;
import com.projectkorra.items.customs.CustomItem;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemUtils {

	/**
	 * Returns a list of all of the players effectable items.
	 * 
	 * @param player the player with the items
	 * @return a list of items
	 */
	public static ArrayList<ItemStack> getPlayerEquipment(Player player) {
		ArrayList<ItemStack> istacks = new ArrayList<ItemStack>();
		for (ItemStack istack : player.getInventory().getArmorContents())
			istacks.add(istack);
		istacks.add(player.getItemInHand());
		return istacks;
	}

	/**
	 * Returns a list of the players armor and in hand item, ONLY if the item is
	 * usable for the specific player. If the item has the "HoldOnly",
	 * "WearOnly", or "RequireElement" stat, then this method may ignore some
	 * items.
	 * 
	 * @param player the player that has equipment
	 * @return a list of the equipment
	 */
	public static ArrayList<ItemStack> getPlayerValidEquipment(Player player) {
		if (player == null) {
			return new ArrayList<ItemStack>();
		}
		ArrayList<ItemStack> equipment = getPlayerEquipment(player);

		/*
		 * Get any inventory items that contain the "AllowFromInventory" stat.
		 */
		PlayerInventory playerInv = player.getInventory();
		for (ItemStack invItem : playerInv) {
			if (invItem == null || invItem.getType() == Material.AIR)
				continue;

			CustomItem citem = CustomItem.getCustomItem(invItem);
			if (citem != null && !equipment.contains(invItem) && citem.getBooleanAttributeValue("AllowFromInventory")) {
				equipment.add(invItem);
			}
		}

		for (int i = 0; i < equipment.size(); i++) {
			ItemStack istack = equipment.get(i);
			CustomItem citem = CustomItem.getCustomItem(istack);
			if (citem == null)
				continue;

			boolean keepItem = true;
			if (!hasValidCharges(istack)) {
				keepItem = false;
			} else if (citem.getBooleanAttributeValue("HoldOnly") && !istack.equals(player.getItemInHand())) {
				keepItem = false;
			} else if (citem.getBooleanAttributeValue("WearOnly") && istack.equals(player.getItemInHand())) {
				keepItem = false;
			} else if (!AttributeUtils.hasRequiredElement(player, citem)) {
				keepItem = false;
			} else if (!AttributeUtils.hasRequiredWorld(player, citem)) {
				keepItem = false;
			} else if (!AttributeUtils.hasRequiredPermission(player, citem)) {
				keepItem = false;
			}

			if (!keepItem) {
				equipment.remove(i);
				i--;
				continue;
			}
		}
		return equipment;
	}

	/**
	 * Checks if an item has a usable amount of charges, or doesn't require
	 * charges at all.
	 * 
	 * @param item the custom item
	 * @return true if the item has a usable amount of charges, or no charges
	 */
	public static boolean hasValidCharges(ItemStack item) {
		boolean validCharges = true;
		try {
			for (String line : item.getItemMeta().getLore()) {
				if (line.startsWith(AttributeList.CHARGES_STR) || line.startsWith(AttributeList.CLICK_CHARGES_STR) || line.startsWith(AttributeList.SNEAK_CHARGES_STR)) {
					String tmpStr = line.substring(line.indexOf(": ") + 1, line.length()).trim();
					int value = Integer.parseInt(tmpStr);
					if (value <= 0)
						validCharges = false;
					else {
						validCharges = true;
						break;
					}
				}
			}
		}
		catch (Exception e) {
		}
		return validCharges;
	}

	/**
	 * A quick way to set the lore of an item
	 * 
	 * @param istack the item that will have its lore set
	 * @param lore the strings that make up the lore
	 * @return true if the lore was set correctly
	 */
	public static boolean setLore(ItemStack istack, List<String> lore) {
		if (istack == null || lore == null)
			return false;
		ItemMeta meta = istack.getItemMeta();
		if (meta == null)
			return false;
		meta.setLore(lore);
		istack.setItemMeta(meta);
		return true;
	}
}
