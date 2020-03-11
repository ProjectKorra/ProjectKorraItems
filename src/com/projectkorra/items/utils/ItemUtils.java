package com.projectkorra.items.utils;

import com.projectkorra.items.ProjectKorraItems;
import com.projectkorra.items.attribute.Action;
import com.projectkorra.items.attribute.Attribute;
import com.projectkorra.items.attribute.AttributeList;
import com.projectkorra.items.customs.PKItem;
import com.projectkorra.projectkorra.waterbending.util.WaterReturn;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ItemUtils {
	
	/**
	 * A map of player names that holds their current bending potion effects.
	 **/
	public static final ConcurrentHashMap<String, ConcurrentHashMap<String, Attribute>> currentBendingEffects = new ConcurrentHashMap<String, ConcurrentHashMap<String, Attribute>>();


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
		istacks.add(player.getInventory().getItemInMainHand());
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

			PKItem citem = PKItem.getCustomItem(invItem);
			if (citem != null && !equipment.contains(invItem) && citem.getBooleanAttributeValue("AllowFromInventory")) {
				equipment.add(invItem);
			}
		}

		for (int i = 0; i < equipment.size(); i++) {
			ItemStack istack = equipment.get(i);
			PKItem citem = PKItem.getCustomItem(istack);
			if (citem == null)
				continue;

			boolean keepItem = true;
			if (!hasValidCharges(istack)) {
				keepItem = false;
			} else if (citem.getBooleanAttributeValue("HoldOnly") && !istack.equals(player.getInventory().getItemInMainHand())) {
				keepItem = false;
			} else if (citem.getBooleanAttributeValue("WearOnly") && istack.equals(player.getInventory().getItemInMainHand())) {
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
	
	/**
	 * OnActionEffects are PotionEffects and BendingAffects that get added to the players Attribute
	 * map for a limited amount of time.
	 * 
	 * @param player the player receiving the stat modifications
	 * @param type the type of action that caused this to trigger
	 */
	public static void updateOnActionEffects(Player player, Action type) {
		if (player == null)
			return;

		ArrayList<ItemStack> istacks = ItemUtils.getPlayerValidEquipment(player);
		String[] validAttribs = null;
		if (type == Action.LEFT_CLICK)
			validAttribs = new String[] { "Effects", "ClickEffects" };
		else if (type == Action.RIGHT_CLICK)
			validAttribs = new String[] { "Effects", "ClickEffects" };
		else if (type == Action.SHIFT)
			validAttribs = new String[] { "Effects", "SneakEffects" };
		else if (type == Action.CONSUME)
			validAttribs = new String[] { "Effects", "ConsumeEffects" };
		else
			validAttribs = new String[] { "Effects" };

		boolean effectAdded = false;
		for (ItemStack istack : istacks) {
			PKItem citem = PKItem.getCustomItem(istack);
			if (citem == null)
				continue;

			for (Attribute att : citem.getAttributes())
				for (String allowedEff : validAttribs)
					if (att.getName().equalsIgnoreCase(allowedEff)) {
						ArrayList<PotionEffect> potEffects = AttributeUtils.parsePotionEffects(att);
						ArrayList<Attribute> bendEffects = AttributeUtils.parseBendingEffects(att);

						for (PotionEffect pot : potEffects)
							player.addPotionEffect(pot, true);
						effectAdded = true;

						for (Attribute effect : bendEffects) {
							if (!currentBendingEffects.containsKey(player.getName()))
								currentBendingEffects.put(player.getName(), new ConcurrentHashMap<String, Attribute>());
							effect.setTime(System.currentTimeMillis());
							ConcurrentHashMap<String, Attribute> playerEffList = currentBendingEffects.get(player.getName());
							playerEffList.put(effect.getName(), effect);
						}
					}
		}
		if (effectAdded)
			AttributeUtils.decreaseCharges(player, type);
	}
	
	/**
	 * Returns the first slot index in a PlayerInventory, trying to avoid hotbar slots
	 * 
	 * @param Inventory the inventory to check
	 * @return The slot found, -1 if inventory full
	 */
	public static int firstAvoidHotbar(final PlayerInventory inventory) {
		int slot = -1;
		for (int i = 9; i < inventory.getSize(); i++) {
			if (inventory.getItem(i) == null || inventory.getItem(i).getType() == Material.AIR) {
				slot = i;
				break;
			}
		}
		
		if (slot < 0)
			slot = inventory.firstEmpty();
		
		return slot;
	}
	
	/**
	 * Handles the specific stat "WaterSource" and in the future "MetalSource". These stats cause
	 * specific temporary items to spawn inside of the players inventory.
	 * 
	 * @param player the player with the WaterSource stat
	 * @param attrib the name of the stat "WaterSource" or "MetalSource"
	 * @param istack the ItemStack that will temporarily spawn
	 */
	public static void handleItemSource(Player player, String attrib, ItemStack istack) {
		ConcurrentHashMap<String, Double> attribs = AttributeUtils.getSimplePlayerAttributeMap(player);
		if (attribs.containsKey(attrib) && attribs.get(attrib) == 1) {
			final PlayerInventory inv = player.getInventory();
			final int fslot = firstAvoidHotbar(inv);
			
			if (fslot >= 0 && (!attrib.equalsIgnoreCase("WaterSource") || !WaterReturn.hasWaterBottle(player))) {
				inv.setItem(fslot, istack);

				new BukkitRunnable() {
					public void run() {
						inv.setItem(fslot, new ItemStack(Material.AIR));
					}
				}.runTaskLater(ProjectKorraItems.plugin, 10);
			}
		}
	}
	
	/**
	 * Returns an ItemStack with the specified number of bottles of water
	 * 
	 * @param qty The quantity of water bottles in the ItemStack
	 */
	public static ItemStack getWaterBottles(int qty) {
		ItemStack bottles = null;
		
		if (qty > 0 && qty <= 64) {
			bottles = new ItemStack(Material.POTION, qty);
			PotionMeta meta = (PotionMeta) bottles.getItemMeta();
			PotionData data = new PotionData(PotionType.WATER);
			meta.setBasePotionData(data);
			bottles.setItemMeta(meta);
		}
		
		return bottles;
	}
}
