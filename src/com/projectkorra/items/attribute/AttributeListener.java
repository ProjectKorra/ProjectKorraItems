package com.projectkorra.items.attribute;

import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;

import com.projectkorra.items.items.Glider;
import com.projectkorra.items.utils.AttributeUtils;
import com.projectkorra.items.utils.PKIUtils;

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
			PKIUtils.updateOnActionEffects(player, Action.SHIFT);
			PKIUtils.handleItemSource(player, "WaterSource", new ItemStack(Material.POTION));
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
		PKIUtils.updateOnActionEffects(player, Action.LEFTCLICK);
		PKIUtils.handleItemSource(player, "WaterSource", new ItemStack(Material.POTION));

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
		PKIUtils.updateOnActionEffects(event.getPlayer(), Action.CONSUME);
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
