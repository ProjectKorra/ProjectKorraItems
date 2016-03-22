package com.projectkorra.items.abilityupdater;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.projectkorra.items.Messages;
import com.projectkorra.items.ProjectKorraItems;
import com.projectkorra.items.attribute.Attribute;
import com.projectkorra.items.customs.PKItem;
import com.projectkorra.items.utils.AttributeUtils;
import com.projectkorra.items.utils.PKIUtils;
import com.projectkorra.projectkorra.event.AbilityDamageEntityEvent;
import com.projectkorra.projectkorra.event.AbilityStartEvent;
import com.projectkorra.projectkorra.util.ParticleEffect;

public class AbilityUpdater implements Listener {
	
	@EventHandler
	public void onDamage(AbilityDamageEntityEvent event) {
		updateAbilityDamage(event.getAbility().getPlayer(), event.getAbility());
	}
	
	@EventHandler
	public void onStart(AbilityStartEvent event) {
		updateAbility(event.getAbility().getPlayer(), event.getAbility());
	}

	/**
	 * Attempts to update an ability based on the attribute effects of a
	 * specific player. Also calls a method to handle any particle effects that
	 * should surround the player.
	 * 
	 * @param player the player that initialized the ability
	 * @param ability the instance of a ProjectKorra ability
	 */
	public static void updateAbility(Player player, Object ability) {
		if (player == null) {
			return;
		}

		boolean abilityAdded = true;
		
		ConcurrentHashMap<String, Double> attribs = AttributeUtils.getSimplePlayerAttributeMap(player);
		
		if (FireUpdater.updateAbility(player, ability, attribs)) {
			AttributeUtils.decreaseCharges(player, null);
		} else if (WaterUpdater.updateAbility(player, ability, attribs)) {
			AttributeUtils.decreaseCharges(player, null);
		} else if (AirUpdater.updateAbility(player, ability, attribs)) {
			AttributeUtils.decreaseCharges(player, null);
		} else if (EarthUpdater.updateAbility(player, ability, attribs)) {
			AttributeUtils.decreaseCharges(player, null);
		} else if (ChiUpdater.updateAbility(player, ability, attribs)) {
			AttributeUtils.decreaseCharges(player, null);
		} else {
			abilityAdded = false;
		}

		if (abilityAdded) {
			updatePlayerParticles(player);
		}
	}
	
	public static void updateAbilityDamage(Player player, Object ability) {
		if (player == null) {
			return;
		}

		boolean abilityAdded = true;
		
		ConcurrentHashMap<String, Double> attribs = AttributeUtils.getSimplePlayerAttributeMap(player);
		
		if (FireUpdater.updateAbilityDamage(player, ability, attribs)) {
			AttributeUtils.decreaseCharges(player, null);
		} else if (WaterUpdater.updateAbilityDamage(player, ability, attribs)) {
			AttributeUtils.decreaseCharges(player, null);
		} else if (AirUpdater.updateAbilityDamage(player, ability, attribs)) {
			AttributeUtils.decreaseCharges(player, null);
		} else if (EarthUpdater.updateAbilityDamage(player, ability, attribs)) {
			AttributeUtils.decreaseCharges(player, null);
		} else if (ChiUpdater.updateAbilityDamage(player, ability, attribs)) {
			AttributeUtils.decreaseCharges(player, null);
		} else {
			abilityAdded = false;
		}

		if (abilityAdded) {
			updatePlayerParticles(player);
		}
	}

	/**
	 * If the player has an item with the stat "ParticleEffects" then we will
	 * parse the information and display a particle effect around the player.
	 * 
	 * @param player
	 */
	private static void updatePlayerParticles(Player player) {
		ArrayList<ItemStack> equipment = PKIUtils.getPlayerValidEquipment(player);
		for (ItemStack istack : equipment) {
			PKItem citem = PKItem.getCustomItem(istack);
			if (citem == null)
				continue;

			Attribute attr = citem.getAttribute("ParticleEffects");
			if (attr == null)
				continue;

			ArrayList<String> values = attr.getValues();
			for (String value : values) {
				String[] colonSplit = value.split(":");
				if (colonSplit.length == 0)
					continue;

				String particleName = colonSplit[0];
				ParticleEffect effect = ParticleEffect.fromName(particleName.trim());
				if (effect == null) {
					Messages.logTimedMessage(Messages.BAD_PARTICLE_EFFECT + ": " + particleName);
					continue;
				}

				double amount = 1;
				double radius = 100;
				double duration = 1;
				double speed = 0;
				try {
					if (colonSplit.length >= 2)
						amount = Double.parseDouble(colonSplit[1]);
					if (colonSplit.length >= 3)
						radius = Double.parseDouble(colonSplit[2]);
					if (colonSplit.length >= 4)
						duration = Double.parseDouble(colonSplit[3]);
					if (colonSplit.length >= 5)
						speed = Double.parseDouble(colonSplit[4]);
				}
				catch (NumberFormatException e) {
				}

				radius /= 100;
				speed /= 100;
				final ParticleEffect feffect = effect;
				final double fradius = radius;
				final double famount = amount;
				final double fspeed = speed;
				final Player fplayer = player;
				for (int i = 0; i < duration; i++) {
					new BukkitRunnable() {
						public void run() {
							feffect.display(fplayer.getEyeLocation(), (float) fradius, (float) fradius, (float) fradius, (float) fspeed, (int) famount);
						}
					}.runTaskLater(ProjectKorraItems.plugin, i);
				}
			}
		}
	}
}
