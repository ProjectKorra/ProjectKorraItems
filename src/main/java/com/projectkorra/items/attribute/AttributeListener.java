package com.projectkorra.items.attribute;

import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import com.projectkorra.items.items.Glider;
import com.projectkorra.items.utils.AttributeUtils;
import com.projectkorra.items.utils.ItemUtils;

public class AttributeListener implements Listener {
	/**
	 * A map of player names that holds their current bending potion effects.
	 **/
	public static final ConcurrentHashMap<String, ConcurrentHashMap<String, Attribute>> currentBendingEffects = new ConcurrentHashMap<String, ConcurrentHashMap<String, Attribute>>();

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
		// new GrapplingHook(player, Action.SHIFT);

		// Handles the Charges, and ShiftCharges attribute
		if (!player.isSneaking()) {
			ItemUtils.updateOnActionEffects(player, Action.SHIFT);
			ItemUtils.handleItemSource(player, "WaterSource", ItemUtils.getWaterBottles(1));
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
		ItemUtils.handleItemSource(player, "WaterSource", ItemUtils.getWaterBottles(1));

		// new GrapplingHook(player, Action.LEFTCLICK);
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
		ConcurrentHashMap<String, Double> attribs = AttributeUtils.getSimplePlayerAttributeMap(player);
		boolean auto = Attribute.getBooleanValue("AirGlideAutomatic", attribs);
		if (auto)
			new Glider(player, true);
	}
}
