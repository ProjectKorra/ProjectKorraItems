package com.projectkorra.items.abilityupdater;

import com.projectkorra.projectkorra.ability.Ability;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.projectkorra.items.Messages;
import com.projectkorra.items.ProjectKorraItems;
import com.projectkorra.items.attribute.Attribute;
import com.projectkorra.items.customs.PKItem;
import com.projectkorra.items.utils.AttributeUtils;
import com.projectkorra.items.utils.ItemUtils;
import com.projectkorra.projectkorra.event.AbilityDamageEntityEvent;
import com.projectkorra.projectkorra.event.AbilityStartEvent;
import com.projectkorra.projectkorra.util.ParticleEffect;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AbilityUpdater implements Listener {
	private final Set<String> abilityWithStart = new HashSet<>();


	/*
	private boolean isSetDoubleType(Ability ability) {
		boolean result = true;

		if (ability instanceof FireBurst)
			result = false;
		else if (ability instanceof WallOfFire)
			result = false;
		else if (ability instanceof OctopusForm)
			result = false;

		return result;
	}

	private String getDamageMod(Ability ability) {
		String methodMod = "";

		if (ability instanceof WaterArmsWhip)
			methodMod = "Punch";
		else if (ability instanceof WaterArmsSpear)
			methodMod = "Spear";
		else if (ability instanceof Torrent)
			methodMod = "Deflect";
		else if (ability instanceof WaterArmsFreeze)
			methodMod = "Ice";

		return methodMod;
	}

	private Double getDamage(Ability ability, String methodMod) {
		Double damage = null;

		if (!ability.isHarmlessAbility()) {
			try {
				Method getDamageMethod = ability.getClass().getMethod("get" + methodMod + "Damage");
				damage = (double) getDamageMethod.invoke(ability);
			} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
				// e.printStackTrace();
			}
		}

		return damage;
	}

	private void setDamage(Ability ability, String methodMod, Double damage) {
		try {
			Class<? extends Number> setMethodType = double.class;
			boolean isDouble = isSetDoubleType(ability);
			if (!isDouble)
				setMethodType = int.class;
			Method setDamageMethod = ability.getClass().getMethod("set" + methodMod + "Damage", setMethodType);
			if (isDouble)
				setDamageMethod.invoke(ability, damage);
			else
				setDamageMethod.invoke(ability, damage.intValue());
		} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
			// e.printStackTrace();
		}
	}
	 */


	@EventHandler(priority = EventPriority.HIGH)
	public void onAbilityStart(AbilityStartEvent ev) {
		abilityWithStart.add(ev.getAbility().getName());
		updateDamageAttributes(ev.getAbility());
	}


	private void updateDamageAttributes(Ability ability) {
		Player player = ability.getPlayer();
		// String damageMod = getDamageMod(ability);
		// Double damage = getDamage(ability, damageMod);

		if (!ability.isHarmlessAbility()) {
			updateAbilityDamage(player, ability);
		}
		updateAbility(player, ability);
	}


	@EventHandler(priority = EventPriority.HIGH)
	public void onDamage(AbilityDamageEntityEvent ev) {
		Ability ability = ev.getAbility();
		if (!abilityWithStart.contains(ability.getName())) {
			updateDamageAttributes(ability);
		}
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
		
		Map<String, Double> attribs = AttributeUtils.getSimplePlayerAttributeMap(player);
		
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
	
	public static void updateAbilityDamage(Player player, Ability ability) {
		if (player == null) {
			return;
		}

		boolean abilityAdded = true;
		
		Map<String, Double> attribs = AttributeUtils.getSimplePlayerAttributeMap(player);
		
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
	 * @param player the player to update
	 */
	private static void updatePlayerParticles(Player player) {
		List<ItemStack> equipment = ItemUtils.getPlayerValidEquipment(player);
		for (ItemStack istack : equipment) {
			PKItem citem = PKItem.getCustomItem(istack);
			if (citem == null)
				continue;

			Attribute attr = citem.getAttribute("ParticleEffects");
			if (attr == null)
				continue;

			List<String> values = attr.getValues();
			for (String value : values) {
				String[] colonSplit = value.split(":");
				if (colonSplit.length == 0)
					continue;

				String particleName = colonSplit[0];
				ParticleEffect effect;
				try {
					effect = ParticleEffect.valueOf(particleName.trim());
				} catch (IllegalArgumentException ex) {
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
				catch (NumberFormatException ignore) {}

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
							feffect.display(fplayer.getEyeLocation(), (int) famount, (float) fradius, (float) fradius, (float) fradius, (float) fspeed);
						}
					}.runTaskLater(ProjectKorraItems.plugin, i);
				}
			}
		}
	}
}
