package com.projectkorra.items.attribute;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.codingforcookies.armorequip.ArmorEquipEvent;
import com.projectkorra.items.ProjectKorraItems;
import com.projectkorra.items.customs.PKItem;
import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.Element;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

import com.projectkorra.items.items.Glider;
import com.projectkorra.items.utils.AttributeUtils;
import com.projectkorra.items.utils.ItemUtils;
import org.bukkit.inventory.ItemStack;

public class AttributeListener implements Listener {
	/**
	 * A map of player names that holds their current bending potion effects.
	 **/
	public static final Map<String, Map<String, Attribute>> currentBendingEffects = new ConcurrentHashMap<>();

	/**
	 * When the player sneaks we should attempt to let them Glide. The Glider
	 * class will handle whether or not they can actually glide. Attempt to
	 * confirm an ability to decrease charges.
	 * 
	 * @param event a sneak event
	 */
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerSneak(PlayerToggleSneakEvent event) {
		if (event.isCancelled())
			return;

		Player player = event.getPlayer();
		new Glider(player);

		// Handles the Charges, and ShiftCharges attribute
		if (!player.isSneaking()) {
			ItemUtils.updateOnActionEffects(player, Action.SHIFT);
		}
	}

	/**
	 * Confirm if an ability was executed via clicking. Also handle specific
	 * stats that related to left clicking.
	 * 
	 * @param event a player animation event
	 */
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerSwing(PlayerAnimationEvent event) {
		if (event.isCancelled())
			return;
		Player player = event.getPlayer();
		ItemUtils.updateOnActionEffects(player, Action.LEFT_CLICK);
	}

	/**
	 * Make a call to allow all consume based affects for the custom item that
	 * the player ate.
	 * 
	 * @param event a consume event
	 */
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerConsume(PlayerItemConsumeEvent event) {
		if (event.isCancelled())
			return;
		ItemUtils.updateOnActionEffects(event.getPlayer(), Action.CONSUME);
	}

	/**
	 * Because of the "AirGliderAutomatic" stat, we need to attempt to glide
	 * whenever the user switches their item.
	 * 
	 * @param event item change event
	 */
	@EventHandler(priority = EventPriority.NORMAL)
	public void onChangeItem(PlayerItemHeldEvent event) {
		if (event.isCancelled())
			return;

		Player player = event.getPlayer();
		Map<String, Double> attribs = AttributeUtils.getSimplePlayerAttributeMap(player);
		boolean auto = Attribute.getBooleanValue("AirGlideAutomatic", attribs);
		if (auto) {
			new Glider(player, true);
		}
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onArmorEquipEvent(ArmorEquipEvent event) {
		BendingPlayer bendingPlayer = BendingPlayer.getBendingPlayer(event.getPlayer());
		ItemStack newStack = event.getNewArmorPiece();
		PKItem newPKItem = PKItem.getCustomItem(newStack);
		// If the new item has WaterSource we set water pouch and exit
		if (newPKItem != null) {
			for (Attribute attr : newPKItem.getAttributes()) {
				if (attr.getName().equals("WaterSource")) {
					bendingPlayer.setWaterPouch(true);
					return;
				}
			}
		}

		// If prior to changing armor we didn't have water pouch, we don't need to do anything
		if (!bendingPlayer.hasWaterPouch()) {
			return;
		}

		// If we had water pouch, we need to check if it was due to the removed armor piece
		ItemStack oldStack = event.getOldArmorPiece();
		PKItem oldPKItem = PKItem.getCustomItem(oldStack);
		boolean hadWaterSource = false;
		if (oldPKItem != null) {
			for (Attribute attr : oldPKItem.getAttributes()) {
				if (attr.getName().equals("WaterSource")) {
					hadWaterSource = true;
					break;
				}
			}
		}
		// If the armor had it, we set water pouch to false
		if (hadWaterSource) {
			bendingPlayer.setWaterPouch(false);
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerJoin(PlayerJoinEvent event) {
		Bukkit.getScheduler().runTaskLater(ProjectKorraItems.plugin, () -> {
			BendingPlayer bendingPlayer = BendingPlayer.getBendingPlayer(event.getPlayer());
			if (!bendingPlayer.hasElement(Element.WATER)) {
				return;
			}
			List<ItemStack> equipment = ItemUtils.getPlayerValidEquipment(event.getPlayer());
			for (ItemStack istack : equipment) {
				PKItem citem = PKItem.getCustomItem(istack);
				if (citem == null)
					continue;
				for (Attribute attr : citem.getAttributes()) {
					if (attr.getName().equals("WaterSource")) {
						bendingPlayer.setWaterPouch(true);
						return;
					}
				}
			}
		}, 20);
	}
}
