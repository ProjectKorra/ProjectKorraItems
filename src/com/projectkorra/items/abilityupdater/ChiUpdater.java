package com.projectkorra.items.abilityupdater;

import com.projectkorra.projectkorra.chiblocking.AcrobatStance;
import com.projectkorra.projectkorra.chiblocking.HighJump;
import com.projectkorra.projectkorra.chiblocking.Paralyze;
import com.projectkorra.projectkorra.chiblocking.QuickStrike;
import com.projectkorra.projectkorra.chiblocking.RapidPunch;
import com.projectkorra.projectkorra.chiblocking.Smokescreen;
import com.projectkorra.projectkorra.chiblocking.SwiftKick;
import com.projectkorra.projectkorra.chiblocking.WarriorStance;
import com.projectkorra.projectkorra.chiblocking.combo.Immobilize;

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
		if (ability instanceof QuickStrike) {
			QuickStrike abil = (QuickStrike) ability;
			
			if (attribs.containsKey("QuickStrikeDamage"))
				abil.setDamage(abil.getDamage() + abil.getDamage() * attribs.get("QuickStrikeDamage") / 100.0);
			
			return true;
		}
		else if (ability instanceof RapidPunch) {
			RapidPunch abil = (RapidPunch) ability;
			
			if (attribs.containsKey("RapidPunchDamage"))
				abil.setDamage(abil.getDamage() + abil.getDamage() * attribs.get("RapidPunchDamage") / 100.0);
			
			return true;
		}
		else if (ability instanceof SwiftKick) {
			SwiftKick abil = (SwiftKick) ability;
			
			if (attribs.containsKey("SwiftKickDamage"))
				abil.setDamage(abil.getDamage() + abil.getDamage() * attribs.get("SwiftKickDamage") / 100.0);
			
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
		}
		else if (ability instanceof HighJump) {
			HighJump abil = (HighJump) ability;
			
			if (attribs.containsKey("HighJumpCooldown"))
				abil.setCooldown((long) (abil.getCooldown() + abil.getCooldown() * attribs.get("HighJumpCooldown") / 100.0));
			
			if (attribs.containsKey("HighJumpHeight"))
				abil.setHeight((int) (abil.getHeight() + abil.getHeight() * attribs.get("HighJumpHeight") / 100.0));
			
			return true;
		}
		else if (ability instanceof Paralyze) {
			//Paralyze abil = (Paralyze) ability;
			
			return true;
		}
		else if (ability instanceof QuickStrike) {
			QuickStrike abil = (QuickStrike) ability;
			
			if (attribs.containsKey("QuickStrikeBlockChance"))
				abil.setBlockChance((int) (abil.getBlockChance() + abil.getBlockChance() * attribs.get("QuickStrikeBlockChance") / 100.0));
			
			return true;
		}
		else if (ability instanceof RapidPunch) {
			RapidPunch abil = (RapidPunch) ability;
			
			if (attribs.containsKey("RapidPunchHits"))
				abil.setNumPunches((int) (abil.getNumPunches() + abil.getNumPunches() * attribs.get("RapidPunchHits") / 100.0));
			
			//if (attribs.containsKey("RapidPunchDistance"))
			//	abil.setDistance((int) (abil.getDistance() + abil.getDistance() * attribs.get("RapidPunchDistance") / 100.0));
			
			if (attribs.containsKey("RapidPunchCooldown"))
				abil.setCooldown((long) (abil.getCooldown() + abil.getCooldown() * attribs.get("RapidPunchCooldown") / 100.0));
			
			return true;
		}
		else if (ability instanceof Smokescreen) {
			Smokescreen abil = (Smokescreen) ability;
			
			if (attribs.containsKey("SmokescreenCooldown"))
				abil.setCooldown((long) (abil.getCooldown() + abil.getCooldown() * attribs.get("SmokescreenCooldown") / 100.0));
			
			if (attribs.containsKey("SmokescreenDuration"))
				abil.setDuration((int) (abil.getDuration() + abil.getDuration() * attribs.get("SmokescreenDuration") / 100.0));
			
			if (attribs.containsKey("SmokescreenRadius"))
				abil.setRadius(abil.getRadius() + abil.getRadius() * attribs.get("SmokescreenRadius") / 100.0);
			
			return true;
		}
		else if (ability instanceof SwiftKick) {
			SwiftKick abil = (SwiftKick) ability;
			
			if (attribs.containsKey("SwiftKickBlockChance"))
				abil.setBlockChance((int) (abil.getBlockChance() + abil.getBlockChance() * attribs.get("SwiftKickBlockChance") / 100.0));
			
			if (attribs.containsKey("SwiftKickCooldown"))
				abil.setCooldown((long) (abil.getCooldown() + abil.getCooldown() * attribs.get("SwiftKickCooldown") / 100.0));
			
			return true;
		}
		else if (ability instanceof WarriorStance) {
			WarriorStance abil = (WarriorStance) ability;
			
			if (attribs.containsKey("WarriorStanceStrength"))
				abil.setStrength((int) (abil.getStrength() + abil.getStrength() * attribs.get("WarriorStanceStrength") / 100.0));
			
			if (attribs.containsKey("WarriorStanceResistance"))
				abil.setResistance((int) (abil.getResistance() + abil.getResistance() * attribs.get("WarriorStanceResistance") / 100.0));
			
			return true;
		}
		else if (ability instanceof Immobilize) {
			Immobilize abil = (Immobilize) ability;
			
			if (attribs.containsKey("ImmobilizeCooldown"))
				abil.setCooldown((long) (abil.getCooldown() + abil.getCooldown() * attribs.get("ImmobilizeCooldown") / 100.0));
			
			if (attribs.containsKey("ImmobilizeDuration"))
				abil.setDuration((long) (abil.getDuration() + abil.getDuration() * attribs.get("ImmobilizeDuration") / 100.0));
			
			return true;
		}
		
		return false;
	}
}
