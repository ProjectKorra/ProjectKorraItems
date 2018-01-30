package com.projectkorra.items.abilityupdater;

import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.entity.Player;

import com.projectkorra.projectkorra.firebending.FireBlast;
import com.projectkorra.projectkorra.firebending.FireJet;
import com.projectkorra.projectkorra.firebending.FireShield;
import com.projectkorra.projectkorra.firebending.HeatControl;
import com.projectkorra.projectkorra.firebending.lightning.Lightning;
import com.projectkorra.projectkorra.firebending.WallOfFire;

public class FireUpdater {

	/**
	 * updates the fire abilities based on a players bending effect attributes
	 * 
	 * @param player the player that has the effects
	 * @param ability an instance of a fire ability
	 * @param attribs the map of the players effects
	 * @return if the ability was updated correctly
	 */
	
	public static boolean updateAbilityDamage(Player player, Object ability, ConcurrentHashMap<String, Double> attribs) {
		if (ability instanceof Lightning) {
			Lightning abil = (Lightning) ability;
			if (attribs.containsKey("LightningDamage"))
				abil.setDamage(abil.getDamage() + abil.getDamage() * attribs.get("LightningDamage") / 100.0);
			return true;
		} else if (ability instanceof FireBlast) {
			FireBlast abil = (FireBlast) ability;
			if (attribs.containsKey("FireBlastDamage"))
				abil.setDamage(abil.getDamage() + abil.getDamage() * attribs.get("FireBlastDamage") / 100.0);
			return true;
		} else if (ability instanceof WallOfFire) {
			WallOfFire abil = (WallOfFire) ability;
			if (attribs.containsKey("WallOfFireDamage"))
				abil.setDamage((int) (abil.getDamage() + abil.getDamage() * attribs.get("WallOfFireDamage") / 100.0));
			return true;
		}
		return false;
	}
	
	public static boolean updateAbility(Player player, Object ability, ConcurrentHashMap<String, Double> attribs) {
		if (ability instanceof Lightning) {
			Lightning abil = (Lightning) ability;
			if (attribs.containsKey("LightningRange"))
				abil.setRange(abil.getRange() + abil.getRange() * attribs.get("LightningRange") / 100.0);
			if (attribs.containsKey("LightningChargeTime"))
				abil.setChargeTime(abil.getChargeTime() + abil.getChargeTime() * attribs.get("LightningChargeTime") / 100.0);
			if (attribs.containsKey("LightningCooldown"))
				abil.setCooldown((long) (abil.getCooldown() + abil.getCooldown() * attribs.get("LightningCooldown") / 100.0));
			if (attribs.containsKey("LightningSubArcChance"))
				abil.setSubArcChance(abil.getSubArcChance() + abil.getSubArcChance() * attribs.get("LightningSubArcChance") / 100.0);
			if (attribs.containsKey("LightningMaxChainArcs"))
				abil.setMaxChainArcs(abil.getMaxChainArcs() + abil.getMaxChainArcs() * attribs.get("LightningMaxChainArcs") / 100.0);
			if (attribs.containsKey("LightningChainRange"))
				abil.setChainRange(abil.getChainRange() + abil.getChainRange() * attribs.get("LightningChainRange") / 100.0);
			if (attribs.containsKey("LightningChainArcChance"))
				abil.setChainArcChance(abil.getChainArcChance() + abil.getChainArcChance() * attribs.get("LightningChainArcChance") / 100.0);
			if (attribs.containsKey("LightningWaterRange"))
				abil.setRange(abil.getRange() + abil.getRange() * attribs.get("LightningWaterRange") / 100.0);
			if (attribs.containsKey("LightningStunChance"))
				abil.setStunChance(abil.getStunChance() + abil.getStunChance() * attribs.get("LightningStunChance") / 100.0);
			if (attribs.containsKey("LightningStunDuration"))
				abil.setStunDuration(abil.getStunDuration() + abil.getStunDuration() * attribs.get("LightningStunDuration") / 100.0);
			return true;
		} else if (ability instanceof HeatControl) {
			HeatControl abil = (HeatControl) ability;
			if (attribs.containsKey("HeatControlCookTime"))
				//abil.setCookTime((long) (abil.getCookTime() + abil.getCookTime() * attribs.get("HeatControlCookTime") / 100.0));
			return true;
		} else if (ability instanceof FireBlast) {
			FireBlast abil = (FireBlast) ability;
			if (attribs.containsKey("FireBlastSpeed"))
				abil.setSpeed(abil.getSpeed() + abil.getSpeed() * attribs.get("FireBlastSpeed") / 100.0);
			if (attribs.containsKey("FireBlastForce"))
				abil.setPushFactor(abil.getPushFactor() + abil.getPushFactor() * attribs.get("FireBlastForce") / 100.0);
			if (attribs.containsKey("FireBlastRange"))
				abil.setRange(abil.getRange() + abil.getRange() * attribs.get("FireBlastRange") / 100.0);
			return true;
		} else if (ability instanceof FireJet) {
			FireJet abil = (FireJet) ability;
			if (attribs.containsKey("FireJetDuration"))
				abil.setDuration((long) (abil.getDuration() + abil.getDuration() * attribs.get("FireJetDuration") / 100.0));
			return true;
		} else if (ability instanceof FireShield) {
			FireShield abil = (FireShield) ability;
			if (attribs.containsKey("FireShieldClickDuration"))
				abil.setDuration((long) (abil.getDuration() + abil.getDuration() * attribs.get("FireShieldClickDuration") / 100.0));
			if (attribs.containsKey("FireShieldRadius"))
				abil.setRadius(abil.getRadius() + abil.getRadius() * attribs.get("FireShieldRadius") / 100.0);
			if (attribs.containsKey("FireShieldClickRadius"))
				abil.setDiscRadius(abil.getDiscRadius() + abil.getDiscRadius() * attribs.get("FireShieldClickRadius") / 100.0);
			return true;
		} else if (ability instanceof WallOfFire) {
			WallOfFire abil = (WallOfFire) ability;
			if (attribs.containsKey("WallOfFireRange"))
				abil.setRange((int) (abil.getRange() + abil.getRange() * attribs.get("WallOfFireRange") / 100.0));
			if (attribs.containsKey("WallOfFireHeight"))
				abil.setHeight((int) (abil.getHeight() + abil.getHeight() * attribs.get("WallOfFireHeight") / 100.0));
			if (attribs.containsKey("WallOfFireWidth"))
				abil.setWidth((int) (abil.getWidth() + abil.getWidth() * attribs.get("WallOfFireWidth") / 100.0));
			if (attribs.containsKey("WallOfFireDuration"))
				abil.setDuration((long) (abil.getDuration() + abil.getDuration() * attribs.get("WallOfFireDuration") / 100.0));
			if (attribs.containsKey("WallOfFireDamageInterval"))
				abil.setDamageInterval((long) (abil.getDamageInterval() + abil.getDamageInterval() * attribs.get("WallOfFireDamageInterval") / 100.0));
			if (attribs.containsKey("WallOfFireCooldown"))
				abil.setCooldown((long) (abil.getCooldown() + abil.getCooldown() * attribs.get("WallOfFireCooldown") / 100.0));
			return true;
		}
		return false;
	}

}
