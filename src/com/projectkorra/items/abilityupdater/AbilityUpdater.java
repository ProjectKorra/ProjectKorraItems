package com.projectkorra.items.abilityupdater;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.projectkorra.items.ItemUtils;
import com.projectkorra.items.Messages;
import com.projectkorra.items.ProjectKorraItems;
import com.projectkorra.items.attribute.Action;
import com.projectkorra.items.attribute.Attribute;
import com.projectkorra.items.attribute.AttributeUtils;
import com.projectkorra.items.customs.CustomItem;
import com.projectkorra.projectkorra.event.AbilityDamageEntityEvent;
import com.projectkorra.projectkorra.event.AbilityStartEvent;
import com.projectkorra.projectkorra.util.ParticleEffect;

public class AbilityUpdater implements Listener {
	/**
	 * Confirming an ability instance causes the charges on an item to decrease.
	 * To avoid causing unnecessary charge decreases we use a confirmation
	 * system to double check that the ability actually got executed once the
	 * player clicks or shifts.
	 */
	public static final ConcurrentHashMap<Player, BukkitRunnable> CONFIRM_CLICK = new ConcurrentHashMap<Player, BukkitRunnable>();
	public static final ConcurrentHashMap<Player, BukkitRunnable> CONFIRM_SHIFT = new ConcurrentHashMap<Player, BukkitRunnable>();

	public static final long CLEANUP_TIME = 300000;
	private static final ConcurrentHashMap<Object, Long> UPDATED_ABILITIES = new ConcurrentHashMap<Object, Long>();
	private static BukkitRunnable cleaner;

	public AbilityUpdater() {
	}

	/**
	 * Hooks into the instance maps of all the ProjectKorra abilities, searching
	 * for any new instances of abilities. When a new ability is found it is put
	 * into UPDATED_ABILITIES.
	 */
	
	@EventHandler
	public static void onDamage(AbilityDamageEntityEvent event) {
		if (!UPDATED_ABILITIES.containsKey(event.getAbility())) {
			UPDATED_ABILITIES.put(event.getAbility(), System.currentTimeMillis());
			updateAbilityDamage(event.getAbility().getPlayer(), event.getAbility());
		}
	}
	
	@EventHandler
	public static void onStart(AbilityStartEvent event) {
		if (!UPDATED_ABILITIES.containsKey(event.getAbility())) {
			UPDATED_ABILITIES.put(event.getAbility(), System.currentTimeMillis());
			updateAbility(event.getAbility().getPlayer(), event.getAbility());
		}
	}

	/**
	 * The cleaner scans through each ability instance inside of
	 * UPDATED_ABILITIES and removes the oldest ones.
	 */
	
	public static void startCleanup() {
		cleaner = new BukkitRunnable() {
			public void run() {
				for (Object key : new HashSet<Object>(UPDATED_ABILITIES.keySet())) {
					long val = UPDATED_ABILITIES.get(key);
					if (System.currentTimeMillis() - val >= CLEANUP_TIME)
						UPDATED_ABILITIES.remove(key);
				}
			}
		};
		cleaner.runTaskTimer(ProjectKorraItems.plugin, 0, 600);
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
		if (player == null)
			return;

		boolean abilityAdded = true;
		ConcurrentHashMap<String, Double> attribs = AttributeUtils.getSimplePlayerAttributeMap(player);
		if (FireUpdater.updateAbility(player, ability, attribs)) {
		} else if (WaterUpdater.updateAbility(player, ability, attribs)) {
		} else if (AirUpdater.updateAbility(player, ability, attribs)) {
		} else if (EarthUpdater.updateAbility(player, ability, attribs)) {
		} else if (ChiUpdater.updateAbility(player, ability, attribs)) {
		} else {
			abilityAdded = false;
		}

		if (abilityAdded) {
			updatePlayerParticles(player);
		}

		confirmAbility(player, CONFIRM_CLICK, Action.LEFTCLICK);
		confirmAbility(player, CONFIRM_SHIFT, Action.SHIFT);
	}
	
	public static void updateAbilityDamage(Player player, Object ability) {
		if (player == null)
			return;

		boolean abilityAdded = true;
		ConcurrentHashMap<String, Double> attribs = AttributeUtils.getSimplePlayerAttributeMap(player);
		if (FireUpdater.updateAbilityDamage(player, ability, attribs)) {
		} else if (WaterUpdater.updateAbilityDamage(player, ability, attribs)) {
		} else if (AirUpdater.updateAbilityDamage(player, ability, attribs)) {
		} else if (EarthUpdater.updateAbilityDamage(player, ability, attribs)) {
		} else if (ChiUpdater.updateAbilityDamage(player, ability, attribs)) {
		} else {
			abilityAdded = false;
		}

		if (abilityAdded) {
			updatePlayerParticles(player);
		}

		confirmAbility(player, CONFIRM_CLICK, Action.LEFTCLICK);
		confirmAbility(player, CONFIRM_SHIFT, Action.SHIFT);
	}

	/**
	 * If the player has an item with the stat "ParticleEffects" then we will
	 * parse the information and display a particle effect around the player.
	 * 
	 * @param player
	 */
	private static void updatePlayerParticles(Player player) {
		ArrayList<ItemStack> equipment = ItemUtils.getPlayerValidEquipment(player);
		for (ItemStack istack : equipment) {
			CustomItem citem = CustomItem.getCustomItem(istack);
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

	/**
	 * When a player left clicks we can't be completely sure that the ability
	 * actually executed. If it didn't execute we can't take away a charge or
	 * click charge.
	 * 
	 * To determine if the click executed we will store the players name
	 * temporarily and then wait for the AbilityUpdater to let us know if it
	 * went through.
	 */
	public static void tryToConfirmClick(Player player, ConcurrentHashMap<Player, BukkitRunnable> map) {
		if (map.containsKey(player)) {
			map.get(player).cancel();
			CONFIRM_CLICK.remove(player);
		}

		final Player fplayer = player;
		final ConcurrentHashMap<Player, BukkitRunnable> fmap = map;
		BukkitRunnable br = new BukkitRunnable() {
			public void run() {
				fmap.remove(fplayer);
			}
		};
		br.runTaskLater(ProjectKorraItems.plugin, 3);
		map.put(player, br);
	}

	public static void confirmAbility(Player player, ConcurrentHashMap<Player, BukkitRunnable> map, Action type) {
		if (map.containsKey(player)) {
			map.get(player).cancel();
			map.remove(player);
			AttributeUtils.decreaseCharges(player, type);
		}
	}
}
