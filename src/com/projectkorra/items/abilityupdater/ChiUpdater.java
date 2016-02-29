package com.projectkorra.items.abilityupdater;

import com.projectkorra.projectkorra.chiblocking.AcrobatStance;
import com.projectkorra.projectkorra.chiblocking.RapidPunch;
import com.projectkorra.projectkorra.chiblocking.WarriorStance;

import org.bukkit.entity.Player;

import java.util.concurrent.ConcurrentHashMap;

public class ChiUpdater {

	/**
	 * updates the chi abilities based on a players bending effect attributes
	 * 
	 * @param player the player that has the effects
	 * @param ability an instance of a chi ability
	 * @param attribs the map of the players effects
	 * @return if the ability was updated correctly
	 */
	
	public static boolean updateAbilityDamage(Player player, Object ability, ConcurrentHashMap<String, Double> attribs) {
		if (ability instanceof RapidPunch) {
			RapidPunch abil = (RapidPunch) ability;
			if (attribs.containsKey("RapidPunchDamage"))
				abil.setDamage((int) (abil.getDamage() + abil.getDamage() * attribs.get("RapidPunchDamage") / 100.0));
			return true;
		}
		return false;
	}
	
	public static boolean updateAbility(Player player, Object ability, ConcurrentHashMap<String, Double> attribs) {
		if (ability instanceof AcrobatStance) {
			AcrobatStance abil = (AcrobatStance) ability;
			if (attribs.containsKey("AcrobatStanceJump"))
				abil.setSpeed((int) (abil.getSpeed() + abil.getSpeed() * attribs.get("AcrobatStanceSpeed") / 100.0));
			if (attribs.containsKey("AcrobatStanceSpeed"))
				abil.setJump((int) (abil.getJump() + abil.getJump() * attribs.get("AcrobatStanceSpeed") / 100.0));
			return true;
		} else if (ability instanceof RapidPunch) {
			RapidPunch abil = (RapidPunch) ability;
			if (attribs.containsKey("RapidPunchHits"))
				abil.setNumPunches((int) (abil.getNumPunches() + abil.getNumPunches() * attribs.get("RapidPunchHits") / 100.0));
			if (attribs.containsKey("RapidPunchDistance"))
				abil.setDistance((int) (abil.getDistance() + abil.getDistance() * attribs.get("RapidPunchDistance") / 100.0));
			if (attribs.containsKey("RapidPunchCooldown"))
				abil.setCooldown((long) (abil.getCooldown() + abil.getCooldown() * attribs.get("RapidPunchCooldown") / 100.0));
			return true;
		} else if (ability instanceof WarriorStance) {
			WarriorStance abil = (WarriorStance) ability;
			if (attribs.containsKey("WarriorStanceStrength"))
				abil.setStrength((int) (abil.getStrength() + abil.getStrength() * attribs.get("WarriorStanceStrength") / 100.0));
			if (attribs.containsKey("WarriorStanceResistance"))
				abil.setResistance((int) (abil.getResistance() + abil.getResistance() * attribs.get("WarriorStanceResistance") / 100.0));
			return true;
		}
		return false;
	}
}
