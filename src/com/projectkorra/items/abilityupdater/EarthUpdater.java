package com.projectkorra.items.abilityupdater;

import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.entity.Player;

import com.projectkorra.projectkorra.earthbending.Catapult;
import com.projectkorra.projectkorra.earthbending.EarthArmor;
import com.projectkorra.projectkorra.earthbending.EarthBlast;
import com.projectkorra.projectkorra.earthbending.EarthSmash;
import com.projectkorra.projectkorra.earthbending.EarthTunnel;
import com.projectkorra.projectkorra.earthbending.Ripple;
import com.projectkorra.projectkorra.earthbending.Shockwave;

public class EarthUpdater {

	/**
	 * updates the earth abilities based on a players bending effect attributes
	 * 
	 * @param player the player that has the effects
	 * @param ability an instance of a earth ability
	 * @param attribs the map of the players effects
	 * @return if the ability was updated correctly
	 */
	
	public static boolean updateAbilityDamage(Player player, Object ability, ConcurrentHashMap<String, Double> attribs) {
		if (ability instanceof EarthSmash) {
			EarthSmash abil = (EarthSmash) ability;
			if (attribs.containsKey("EarthSmashDamage"))
				abil.setDamage(abil.getDamage() + abil.getDamage() * attribs.get("EarthSmashDamage") / 100.0);
			return true;
		} else if (ability instanceof EarthBlast) {
			EarthBlast abil = (EarthBlast) ability;
			if (attribs.containsKey("EarthBlastDamage"))
				abil.setDamage(abil.getDamage() + abil.getDamage() * attribs.get("EarthBlastDamage") / 100.0);
			return true;
		} else if (ability instanceof Ripple) {
			Ripple abil = (Ripple) ability;
			if (attribs.containsKey("ShockwaveDamage"))
				abil.setDamage(abil.getDamage() + abil.getDamage() * attribs.get("ShockwaveDamage") / 100.0);
			return true;
		}
		return false;
	}
	
	public static boolean updateAbility(Player player, Object ability, ConcurrentHashMap<String, Double> attribs) {
		if (ability instanceof EarthSmash) {
			EarthSmash abil = (EarthSmash) ability;
			if (attribs.containsKey("EarthSmashGrabRange"))
				abil.setSelectRange((int) (abil.getSelectRange() + abil.getSelectRange() * attribs.get("EarthSmashGrabRange") / 100.0));
			if (attribs.containsKey("EarthSmashShootRange"))
				abil.setShootRange(abil.getShootRange() + abil.getShootRange() * attribs.get("EarthSmashShootRange") / 100.0);
			if (attribs.containsKey("EarthSmashChargeTime"))
				abil.setChargeTime((long) (abil.getChargeTime() + abil.getChargeTime() * attribs.get("EarthSmashChargeTime") / 100.0));
			if (attribs.containsKey("EarthSmashCooldown"))
				abil.setCooldown((long) (abil.getCooldown() + abil.getCooldown() * attribs.get("EarthSmashCooldown") / 100.0));
			if (attribs.containsKey("EarthSmashKnockback"))
				abil.setKnockback(abil.getKnockback() + abil.getKnockback() * attribs.get("EarthSmashKnockback") / 100.0);
			if (attribs.containsKey("EarthSmashKnockup"))
				abil.setKnockup(abil.getKnockup() + abil.getKnockup() * attribs.get("EarthSmashKnockup") / 100.0);
			if (attribs.containsKey("EarthSmashFlySpeed"))
				abil.setFlightSpeed(abil.getFlightSpeed() + abil.getFlightSpeed() * attribs.get("EarthSmashFlySpeed") / 100.0);
			if (attribs.containsKey("EarthSmashFlyDuration"))
				abil.setFlightRemoveTimer((long) (abil.getFlightRemoveTimer() + abil.getFlightRemoveTimer() * attribs.get("EarthSmashFlyDuration") / 100.0));
			return true;
		} else if (ability instanceof Catapult) {
			Catapult abil = (Catapult) ability;
			if (attribs.containsKey("CatapultLength"))
				abil.setLength((int) (abil.getLength() + abil.getLength() * attribs.get("CatapultLength") / 100.0));
			if (attribs.containsKey("CatapultPush"))
				abil.setPush(abil.getPush() + abil.getPush() * attribs.get("CatapultPush") / 100.0);
			return true;
		} else if (ability instanceof EarthArmor) {
			EarthArmor abil = (EarthArmor) ability;
			if (attribs.containsKey("EarthArmorStrength"))
				abil.setStrength((int) (abil.getStrength() + abil.getStrength() * attribs.get("EarthArmorStrength") / 100.0));
			return true;
		} else if (ability instanceof EarthBlast) {
			EarthBlast abil = (EarthBlast) ability;
			if (attribs.containsKey("EarthBlastRange"))
				abil.setRange(abil.getRange() + abil.getRange() * attribs.get("EarthBlastRange") / 100.0);
			if (attribs.containsKey("EarthBlastForce"))
				abil.setPushFactor(abil.getPushFactor() + abil.getPushFactor() * attribs.get("EarthBlastForce") / 100.0);
			return true;
		} else if (ability instanceof EarthTunnel) {
			EarthTunnel abil = (EarthTunnel) ability;
			if (attribs.containsKey("EarthTunnelMaxRadius"))
				abil.setMaxRadius(abil.getMaxRadius() + abil.getMaxRadius() * attribs.get("EarthTunnelMaxRadius") / 100.0);
			if (attribs.containsKey("EarthTunnelRadius"))
				abil.setRadiusIncrement(abil.getRadiusIncrement() + abil.getRadiusIncrement() * attribs.get("EarthTunnelRadius") / 100.0);
			if (attribs.containsKey("EarthTunnelRange"))
				abil.setRange(abil.getRange() + abil.getRange() * attribs.get("EarthTunnelRange") / 100.0);
			if (attribs.containsKey("EarthTunnelInterval"))
				abil.setInterval((long) (abil.getInterval() + abil.getInterval() * attribs.get("EarthTunnelInterval") / 100.0));
			return true;
		} else if (ability instanceof Ripple) {
			Ripple abil = (Ripple) ability;
			if (attribs.containsKey("ShockwaveRadius"))
				abil.setRange(abil.getRange() + abil.getRange() * attribs.get("ShockwaveRadius") / 100.0);
			if (attribs.containsKey("ShockwaveKnockback"))
				abil.setKnockback(abil.getKnockback() + abil.getKnockback() * attribs.get("ShockwaveKnockback") / 100.0);
			return true;
		} else if (ability instanceof Shockwave) {
			Shockwave abil = (Shockwave) ability;
			if (attribs.containsKey("ShockwaveChargeTime"))
				abil.setChargeTime((long) (abil.getChargeTime() + abil.getChargeTime() * attribs.get("ShockwaveChargeTime") / 100.0));
			return true;
		}
		return false;
	}
}
