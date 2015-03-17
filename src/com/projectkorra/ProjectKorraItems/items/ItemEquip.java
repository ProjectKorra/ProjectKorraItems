package com.projectkorra.ProjectKorraItems.items;

import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitRunnable;

import com.projectkorra.ProjectKorraItems.Messages;
import com.projectkorra.ProjectKorraItems.ProjectKorraItems;

/** ItemEquip handles allowing the player to use the "bi equip" command. It
 * focuses an item so that whenever the player changes their item slot the
 * focused item will follow.
 * 
 * If a player uses the equip command a second time then the previous item will
 * stop being followed. */
public class ItemEquip {
	public static final ConcurrentHashMap<String, ItemEquip> INSTANCES = new ConcurrentHashMap<String, ItemEquip>();

	private CustomItem customItem;
	private ItemStack item;
	private Player player;

	/** Creates a new ItemEquip for the player or removes a pre-existing
	 * ItemEquip for player.
	 * @param player the player to add or remove an ItemEquip */
	public ItemEquip(Player player) {
		String name = player.getName();

		if (INSTANCES.containsKey(name)) {
			ItemEquip itemEq = INSTANCES.get(name);
			INSTANCES.remove(name);
			player.sendMessage(Messages.EQUIP_OFF + " " + itemEq.customItem.getDisplayName());
			return;
		}

		ItemStack inhand = player.getItemInHand();
		CustomItem citem = CustomItem.getCustomItem(inhand);
		if (citem == null) {
			player.sendMessage(Messages.CANT_EQUIP);
			return;
		}

		player.sendMessage(Messages.EQUIP_ON + " " + citem.getDisplayName());
		customItem = citem;
		item = inhand;
		this.player = player;
		INSTANCES.put(name, this);
	}

	/** Attempts to move an equipped item to the newSlot, only if the item is
	 * currently in the players inventory.
	 * @param player the player with an equipped item
	 * @param prevSlot the previous item slot
	 * @param newSlot the new item slot
	 * @return true if the item was moved */
	public static boolean updatePlayerSlot(Player player, int prevSlot, int newSlot) {
		String name = player.getName();
		if (!INSTANCES.containsKey(name)) {
			return false;
		}

		final PlayerInventory inv = player.getInventory();
		ItemEquip itemEq = INSTANCES.get(name);

		ItemStack newSlotItem = inv.getItem(newSlot);
		ItemStack prevSlotItem = inv.getItem(prevSlot);
		if (newSlotItem != null && newSlotItem.getType() != Material.AIR) {
			return false;
		}

		/*
		 * We update itemEq.item everytime because the lore may be constantly
		 * changing if the item had charges on it.
		 */
		if (inv.contains(itemEq.item)) {
			int foundSlot = inv.first(itemEq.item);
			final ItemStack foundItem = inv.getItem(foundSlot);
			final ItemEquip fitemEq = itemEq;

			new BukkitRunnable() {
				public void run() {
					fitemEq.item = foundItem;
				}
			}.runTaskLater(ProjectKorraItems.plugin, 1L);

			inv.setItem(newSlot, foundItem);
			inv.setItem(foundSlot, new ItemStack(Material.AIR));

			return true;
		} else if (prevSlotItem != null
				&& prevSlotItem.hasItemMeta()
				&& prevSlotItem.getItemMeta().hasDisplayName()
				&& prevSlotItem.getItemMeta().getDisplayName()
						.equals(itemEq.customItem.getDisplayName())) {

			final ItemStack prevItem = inv.getItem(prevSlot);
			final ItemEquip fitemEq = itemEq;
			new BukkitRunnable() {
				public void run() {
					fitemEq.item = prevItem;
				}
			}.runTaskLater(ProjectKorraItems.plugin, 1L);

			inv.setItem(newSlot, prevItem);
			inv.setItem(prevSlot, new ItemStack(Material.AIR));
			return true;
		}
		return false;
	}

	public CustomItem getCustomItem() {
		return customItem;
	}

	public void setCustomItem(CustomItem customItem) {
		this.customItem = customItem;
	}

	public ItemStack getItem() {
		return item;
	}

	public void setItem(ItemStack item) {
		this.item = item;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
