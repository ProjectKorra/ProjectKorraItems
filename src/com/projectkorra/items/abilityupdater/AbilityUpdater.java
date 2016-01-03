package com.projectkorra.items.abilityupdater;

import com.projectkorra.items.ItemUtils;
import com.projectkorra.items.Messages;
import com.projectkorra.items.ProjectKorraItems;
import com.projectkorra.items.attribute.Action;
import com.projectkorra.items.attribute.Attribute;
import com.projectkorra.items.attribute.AttributeUtils;
import com.projectkorra.items.customs.CustomItem;
import com.projectkorra.projectkorra.ability.api.CoreAbility;
import com.projectkorra.projectkorra.airbending.AirBlast;
import com.projectkorra.projectkorra.airbending.AirBubble;
import com.projectkorra.projectkorra.airbending.AirScooter;
import com.projectkorra.projectkorra.airbending.AirShield;
import com.projectkorra.projectkorra.airbending.AirSpout;
import com.projectkorra.projectkorra.airbending.AirSuction;
import com.projectkorra.projectkorra.airbending.AirSwipe;
import com.projectkorra.projectkorra.airbending.Tornado;
import com.projectkorra.projectkorra.chiblocking.AcrobatStance;
import com.projectkorra.projectkorra.chiblocking.RapidPunch;
import com.projectkorra.projectkorra.chiblocking.WarriorStance;
import com.projectkorra.projectkorra.earthbending.Catapult;
import com.projectkorra.projectkorra.earthbending.EarthArmor;
import com.projectkorra.projectkorra.earthbending.EarthBlast;
import com.projectkorra.projectkorra.earthbending.EarthSmash;
import com.projectkorra.projectkorra.earthbending.EarthTunnel;
import com.projectkorra.projectkorra.earthbending.Ripple;
import com.projectkorra.projectkorra.earthbending.Shockwave;
import com.projectkorra.projectkorra.firebending.Cook;
import com.projectkorra.projectkorra.firebending.FireBlast;
import com.projectkorra.projectkorra.firebending.FireJet;
import com.projectkorra.projectkorra.firebending.FireShield;
import com.projectkorra.projectkorra.firebending.FireStream;
import com.projectkorra.projectkorra.firebending.Fireball;
import com.projectkorra.projectkorra.firebending.Lightning;
import com.projectkorra.projectkorra.firebending.WallOfFire;
import com.projectkorra.projectkorra.util.ParticleEffect;
import com.projectkorra.projectkorra.waterbending.Bloodbending;
import com.projectkorra.projectkorra.waterbending.IceBlast;
import com.projectkorra.projectkorra.waterbending.IceSpike;
import com.projectkorra.projectkorra.waterbending.IceSpike2;
import com.projectkorra.projectkorra.waterbending.OctopusForm;
import com.projectkorra.projectkorra.waterbending.Torrent;
import com.projectkorra.projectkorra.waterbending.TorrentBurst;
import com.projectkorra.projectkorra.waterbending.WaterManipulation;
import com.projectkorra.projectkorra.waterbending.WaterSpout;
import com.projectkorra.projectkorra.waterbending.WaterWall;
import com.projectkorra.projectkorra.waterbending.WaterWave;
import com.projectkorra.projectkorra.waterbending.Wave;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

public class AbilityUpdater {
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
	private static BukkitRunnable updater, cleaner;

	private AbilityUpdater() {
	}

	/**
	 * Hooks into the instance maps of all the ProjectKorra abilities, searching
	 * for any new instances of abilities. When a new ability is found it is put
	 * into UPDATED_ABILITIES.
	 */
	public static void startUpdater() {
		updater = new BukkitRunnable() {
			public void run() {
				for (Integer id : AirBlast.instances.keySet()) {
					AirBlast abil = AirBlast.instances.get(id);
					if (!UPDATED_ABILITIES.containsKey(abil)) {
						UPDATED_ABILITIES.put(abil, System.currentTimeMillis());
						updateAbility(abil.getPlayer(), abil);
					}
				}
				for (Player id : AirSwipe.instances.keySet()) {
					AirSwipe abil = AirSwipe.instances.get(id);
					if (!UPDATED_ABILITIES.containsKey(abil)) {
						UPDATED_ABILITIES.put(abil, System.currentTimeMillis());
						updateAbility(abil.getPlayer(), abil);
					}
				}
				for (Player id : AirShield.instances.keySet()) {
					AirShield abil = AirShield.instances.get(id);
					if (!UPDATED_ABILITIES.containsKey(abil)) {
						UPDATED_ABILITIES.put(abil, System.currentTimeMillis());
						updateAbility(abil.getPlayer(), abil);
					}
				}
				for (Player id : AirBubble.instances.keySet()) {
					AirBubble abil = AirBubble.instances.get(id);
					if (!UPDATED_ABILITIES.containsKey(abil)) {
						UPDATED_ABILITIES.put(abil, System.currentTimeMillis());
						updateAbility(abil.getPlayer(), abil);
					}
				}
				for (Player id : AirScooter.instances.keySet()) {
					AirScooter abil = AirScooter.instances.get(id);
					if (!UPDATED_ABILITIES.containsKey(abil)) {
						UPDATED_ABILITIES.put(abil, System.currentTimeMillis());
						updateAbility(abil.getPlayer(), abil);
					}
				}
				for (Player id : Tornado.instances.keySet()) {
					Tornado abil = Tornado.instances.get(id);
					if (!UPDATED_ABILITIES.containsKey(abil)) {
						UPDATED_ABILITIES.put(abil, System.currentTimeMillis());
						updateAbility(abil.getPlayer(), abil);
					}
				}
				for (Player id : AirSpout.instances.keySet()) {
					AirSpout abil = AirSpout.instances.get(id);
					if (!UPDATED_ABILITIES.containsKey(abil)) {
						UPDATED_ABILITIES.put(abil, System.currentTimeMillis());
						updateAbility(abil.getPlayer(), abil);
					}
				}
				for (Player id : AirSuction.instances.keySet()) {
					AirSuction abil = AirSuction.instances.get(id);
					if (!UPDATED_ABILITIES.containsKey(abil)) {
						UPDATED_ABILITIES.put(abil, System.currentTimeMillis());
						updateAbility(abil.getPlayer(), abil);
					}
				}

				for (Player id : AcrobatStance.instances.keySet()) {
					AcrobatStance abil = AcrobatStance.instances.get(id);
					if (!UPDATED_ABILITIES.containsKey(abil)) {
						UPDATED_ABILITIES.put(abil, System.currentTimeMillis());
						updateAbility(abil.getPlayer(), abil);
					}
				}
				for (Player id : RapidPunch.instances.keySet()) {
					RapidPunch abil = RapidPunch.instances.get(id);
					if (!UPDATED_ABILITIES.containsKey(abil)) {
						UPDATED_ABILITIES.put(abil, System.currentTimeMillis());
						updateAbility(abil.getPlayer(), abil);
					}
				}
				for (Player id : WarriorStance.instances.keySet()) {
					WarriorStance abil = WarriorStance.instances.get(id);
					if (!UPDATED_ABILITIES.containsKey(abil)) {
						UPDATED_ABILITIES.put(abil, System.currentTimeMillis());
						updateAbility(abil.getPlayer(), abil);
					}
				}

				for (Player id : Catapult.instances.keySet()) {
					Catapult abil = Catapult.instances.get(id);
					if (!UPDATED_ABILITIES.containsKey(abil)) {
						UPDATED_ABILITIES.put(abil, System.currentTimeMillis());
						updateAbility(abil.getPlayer(), abil);
					}
				}
				for (Player id : EarthArmor.instances.keySet()) {
					EarthArmor abil = EarthArmor.instances.get(id);
					if (!UPDATED_ABILITIES.containsKey(abil)) {
						UPDATED_ABILITIES.put(abil, System.currentTimeMillis());
						updateAbility(abil.getPlayer(), abil);
					}
				}
				for (Integer id : EarthBlast.instances.keySet()) {
					EarthBlast abil = EarthBlast.instances.get(id);
					if (!UPDATED_ABILITIES.containsKey(abil)) {
						UPDATED_ABILITIES.put(abil, System.currentTimeMillis());
						updateAbility(abil.getPlayer(), abil);
					}
				}
				for (Player id : EarthTunnel.instances.keySet()) {
					EarthTunnel abil = EarthTunnel.instances.get(id);
					if (!UPDATED_ABILITIES.containsKey(abil)) {
						UPDATED_ABILITIES.put(abil, System.currentTimeMillis());
						updateAbility(abil.getPlayer(), abil);
					}
				}
				for (Integer id : Ripple.instances.keySet()) {
					Ripple abil = Ripple.instances.get(id);
					if (!UPDATED_ABILITIES.containsKey(abil)) {
						UPDATED_ABILITIES.put(abil, System.currentTimeMillis());
						updateAbility(abil.getPlayer(), abil);
					}
				}
				for (Player id : Shockwave.instances.keySet()) {
					Shockwave abil = Shockwave.instances.get(id);
					if (!UPDATED_ABILITIES.containsKey(abil)) {
						UPDATED_ABILITIES.put(abil, System.currentTimeMillis());
						updateAbility(abil.getPlayer(), abil);
					}
				}
				for (EarthSmash abil : EarthSmash.instances) {
					if (!UPDATED_ABILITIES.containsKey(abil)) {
						UPDATED_ABILITIES.put(abil, System.currentTimeMillis());
						updateAbility(abil.getPlayer(), abil);
					}
				}
				/*
				 * for(Player id : MetalClips.instances.keySet()) { MetalClips
				 * abil = MetalClips.instances.get(id);
				 * if(!UPDATED_ABILITIES.containsKey(abil)) {
				 * UPDATED_ABILITIES.put(abil, System.currentTimeMillis());
				 * updateAbility(abil.getPlayer(), abil); } }
				 */

				for (Player id : Cook.instances.keySet()) {
					Cook abil = Cook.instances.get(id);
					if (!UPDATED_ABILITIES.containsKey(abil)) {
						UPDATED_ABILITIES.put(abil, System.currentTimeMillis());
						updateAbility(abil.getPlayer(), abil);
					}
				}
				for (Integer id : Fireball.instances.keySet()) {
					Fireball abil = Fireball.instances.get(id);
					if (!UPDATED_ABILITIES.containsKey(abil)) {
						UPDATED_ABILITIES.put(abil, System.currentTimeMillis());
						updateAbility(abil.getPlayer(), abil);
					}
				}
				for (Integer id : FireBlast.instances.keySet()) {
					FireBlast abil = FireBlast.instances.get(id);
					if (!UPDATED_ABILITIES.containsKey(abil)) {
						UPDATED_ABILITIES.put(abil, System.currentTimeMillis());
						updateAbility(abil.getPlayer(), abil);
					}
				}
				for (Player id : FireJet.instances.keySet()) {
					FireJet abil = FireJet.instances.get(id);
					if (!UPDATED_ABILITIES.containsKey(abil)) {
						UPDATED_ABILITIES.put(abil, System.currentTimeMillis());
						updateAbility(abil.getPlayer(), abil);
					}
				}
				for (Player id : FireShield.instances.keySet()) {
					FireShield abil = FireShield.instances.get(id);
					if (!UPDATED_ABILITIES.containsKey(abil)) {
						UPDATED_ABILITIES.put(abil, System.currentTimeMillis());
						updateAbility(abil.getPlayer(), abil);
					}
				}
				for (Integer id : FireStream.instances.keySet()) {
					FireStream abil = FireStream.instances.get(id);
					if (!UPDATED_ABILITIES.containsKey(abil)) {
						UPDATED_ABILITIES.put(abil, System.currentTimeMillis());
						updateAbility(abil.getPlayer(), abil);
					}
				}
				for (Player id : WallOfFire.instances.keySet()) {
					WallOfFire abil = WallOfFire.instances.get(id);
					if (!UPDATED_ABILITIES.containsKey(abil)) {
						UPDATED_ABILITIES.put(abil, System.currentTimeMillis());
						updateAbility(abil.getPlayer(), abil);
					}
				}
				for (Integer id : Lightning.instances.keySet()) {
					Lightning abil = Lightning.instances.get(id);
					if (!UPDATED_ABILITIES.containsKey(abil)) {
						UPDATED_ABILITIES.put(abil, System.currentTimeMillis());
						updateAbility(abil.getPlayer(), abil);
					}
				}

				for (Player id : Bloodbending.instances.keySet()) {
					Bloodbending abil = Bloodbending.instances.get(id);
					if (!UPDATED_ABILITIES.containsKey(abil)) {
						UPDATED_ABILITIES.put(abil, System.currentTimeMillis());
						updateAbility(abil.getPlayer(), abil);
					}
				}
				for (Integer id : IceBlast.instances.keySet()) {
					IceBlast abil = IceBlast.instances.get(id);
					if (!UPDATED_ABILITIES.containsKey(abil)) {
						UPDATED_ABILITIES.put(abil, System.currentTimeMillis());
						updateAbility(abil.getPlayer(), abil);
					}
				}
				for (Integer id : IceSpike.instances.keySet()) {
					IceSpike abil = IceSpike.instances.get(id);
					if (!UPDATED_ABILITIES.containsKey(abil)) {
						UPDATED_ABILITIES.put(abil, System.currentTimeMillis());
						updateAbility(abil.getPlayer(), abil);
					}
				}
				for (Integer id : IceSpike2.instances.keySet()) {
					IceSpike2 abil = IceSpike2.instances.get(id);
					if (!UPDATED_ABILITIES.containsKey(abil)) {
						UPDATED_ABILITIES.put(abil, System.currentTimeMillis());
						updateAbility(abil.getPlayer(), abil);
					}
				}
				for (Player id : OctopusForm.instances.keySet()) {
					OctopusForm abil = OctopusForm.instances.get(id);
					if (!UPDATED_ABILITIES.containsKey(abil)) {
						UPDATED_ABILITIES.put(abil, System.currentTimeMillis());
						updateAbility(abil.getPlayer(), abil);
					}
				}
				for (Player id : Torrent.instances.keySet()) {
					Torrent abil = Torrent.instances.get(id);
					if (!UPDATED_ABILITIES.containsKey(abil)) {
						UPDATED_ABILITIES.put(abil, System.currentTimeMillis());
						updateAbility(abil.getPlayer(), abil);
					}
				}
				for (Integer id : TorrentBurst.instances.keySet()) {
					TorrentBurst abil = TorrentBurst.instances.get(id);
					if (!UPDATED_ABILITIES.containsKey(abil)) {
						UPDATED_ABILITIES.put(abil, System.currentTimeMillis());
						updateAbility(abil.getPlayer(), abil);
					}
				}
				for (Integer id : WaterManipulation.instances.keySet()) {
					WaterManipulation abil = WaterManipulation.instances.get(id);
					if (!UPDATED_ABILITIES.containsKey(abil)) {
						UPDATED_ABILITIES.put(abil, System.currentTimeMillis());
						updateAbility(abil.getPlayer(), abil);
					}
				}
				for (Player id : WaterSpout.instances.keySet()) {
					WaterSpout abil = WaterSpout.instances.get(id);
					if (!UPDATED_ABILITIES.containsKey(abil)) {
						UPDATED_ABILITIES.put(abil, System.currentTimeMillis());
						updateAbility(abil.getPlayer(), abil);
					}
				}
				for (WaterWave abil : WaterWave.instances)
					if (!UPDATED_ABILITIES.containsKey(abil)) {
						UPDATED_ABILITIES.put(abil, System.currentTimeMillis());
						updateAbility(abil.getPlayer(), abil);
					}
				for (Integer id : WaterWall.instances.keySet()) {
					WaterWall abil = WaterWall.instances.get(id);
					if (!UPDATED_ABILITIES.containsKey(abil)) {
						UPDATED_ABILITIES.put(abil, System.currentTimeMillis());
						updateAbility(abil.getPlayer(), abil);
					}
				}
				for (Integer id : Wave.instances.keySet()) {
					Wave abil = Wave.instances.get(id);
					if (!UPDATED_ABILITIES.containsKey(abil)) {
						UPDATED_ABILITIES.put(abil, System.currentTimeMillis());
						updateAbility(abil.getPlayer(), abil);
					}
				}
			}
		};
		updater.runTaskTimer(ProjectKorraItems.plugin, 0, 1);
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
