package com.projectkorra.items.abilityupdater;

import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.entity.Player;

import com.projectkorra.projectkorra.firebending.Blaze;
import com.projectkorra.projectkorra.firebending.BlazeArc;
import com.projectkorra.projectkorra.firebending.BlazeRing;
import com.projectkorra.projectkorra.firebending.FireBlast;
import com.projectkorra.projectkorra.firebending.FireBlastCharged;
import com.projectkorra.projectkorra.firebending.FireBurst;
import com.projectkorra.projectkorra.firebending.FireJet;
import com.projectkorra.projectkorra.firebending.FireManipulation;
import com.projectkorra.projectkorra.firebending.FireShield;
import com.projectkorra.projectkorra.firebending.HeatControl;
import com.projectkorra.projectkorra.firebending.Illumination;
import com.projectkorra.projectkorra.firebending.WallOfFire;
import com.projectkorra.projectkorra.firebending.combo.FireComboStream;
import com.projectkorra.projectkorra.firebending.combo.FireKick;
import com.projectkorra.projectkorra.firebending.combo.FireSpin;
import com.projectkorra.projectkorra.firebending.combo.FireWheel;
import com.projectkorra.projectkorra.firebending.combo.JetBlast;
import com.projectkorra.projectkorra.firebending.combo.JetBlaze;
import com.projectkorra.projectkorra.firebending.combustion.Combustion;
import com.projectkorra.projectkorra.firebending.lightning.Lightning;

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
		if (ability instanceof FireBlast) {
			FireBlast abil = (FireBlast) ability;
			
			if (attribs.containsKey("FireBlastDamage"))
				abil.setDamage(abil.getDamage() + abil.getDamage() * attribs.get("FireBlastDamage") / 100.0);
			
			return true;
		}
		else if (ability instanceof FireBurst) {
			FireBurst abil = (FireBurst) ability;
			
			if (attribs.containsKey("FireBurstDamage"))
				abil.setDamage((int) (abil.getDamage() + abil.getDamage() * attribs.get("FireBurstDamage") / 100.0));
			
			return true;
		}
		else if (ability instanceof WallOfFire) {
			WallOfFire abil = (WallOfFire) ability;
			
			if (attribs.containsKey("WallOfFireDamage"))
				abil.setDamage((int) (abil.getDamage() + abil.getDamage() * attribs.get("WallOfFireDamage") / 100.0));
			
			return true;
		}
		else if (ability instanceof Combustion) {
			Combustion abil = (Combustion) ability;
			
			if (attribs.containsKey("CombustionDamage"))
				abil.setDamage(abil.getDamage() + abil.getDamage() * attribs.get("CombustionDamage") / 100.0);
			
			return true;
		}
		else if (ability instanceof Lightning) {
			Lightning abil = (Lightning) ability;
			
			if (attribs.containsKey("LightningDamage"))
				abil.setDamage(abil.getDamage() + abil.getDamage() * attribs.get("LightningDamage") / 100.0);
			
			return true;
		}
		
		// Damage can be set for FireComboStream, but has no get method.
		
		return false;
	}
	
	public static boolean updateAbility(Player player, Object ability, ConcurrentHashMap<String, Double> attribs) {
		if (ability instanceof Blaze) {
			// Blaze abil = (Blaze) ability;
			
			return true;
		}
		else if (ability instanceof BlazeArc) {
			BlazeArc abil = (BlazeArc) ability;

			if (attribs.containsKey("BlazeArcInterval"))
				abil.setInterval((long) (abil.getInterval() + abil.getInterval() * attribs.get("BlazeArcInterval") / 100.0));
			
			if (attribs.containsKey("BlazeArcRange"))
				abil.setRange(abil.getRange() + abil.getRange() * attribs.get("BlazeArcRange") / 100.0);
			
			if (attribs.containsKey("BlazeArcSpeed"))
				abil.setSpeed(abil.getSpeed() + abil.getSpeed() * attribs.get("BlazeArcSpeed") / 100.0);
			
			//if (attribs.containsKey("BlazeArcTime"))
			//	abil.setTime((long) (abil.getTime() + abil.getTime() * attribs.get("BlazeArcTime") / 100.0));
			
			return true;
		}
		else if (ability instanceof BlazeRing) {
			BlazeRing abil = (BlazeRing) ability;

			if (attribs.containsKey("BlazeRingAngleIncrement"))
				abil.setAngleIncrement(abil.getAngleIncrement() + abil.getAngleIncrement() * attribs.get("BlazeRingAngleIncrement") / 100.0);
			
			if (attribs.containsKey("BlazeRingCooldown"))
				abil.setCooldown((long) (abil.getCooldown() + abil.getCooldown() * attribs.get("BlazeRingCooldown") / 100.0));
			
			if (attribs.containsKey("BlazeRingRange"))
				abil.setRange((int) (abil.getRange() + abil.getRange() * attribs.get("BlazeRingRange") / 100.0));
			
			return true;
		}
		else if (ability instanceof FireBlast) {
			FireBlast abil = (FireBlast) ability;
			
			if (attribs.containsKey("FireBlastCollisionRadius"))
				abil.setCollisionRadius(abil.getCollisionRadius() + abil.getCollisionRadius() * attribs.get("FireBlastCollisionRadius") / 100.0);
			
			if (attribs.containsKey("FireBlastCooldown"))
				abil.setCooldown((long) (abil.getCooldown() + abil.getCooldown() * attribs.get("FireBlastCooldown") / 100.0));
			
			if (attribs.containsKey("FireBlastFireTicks"))
				abil.setFireTicks(abil.getFireTicks() + abil.getFireTicks() * attribs.get("FireBlastFireTicks") / 100.0);
			
			if (attribs.containsKey("FireBlastPushFactor"))
				abil.setPushFactor(abil.getPushFactor() + abil.getPushFactor() * attribs.get("FireBlastPushFactor") / 100.0);
			
			if (attribs.containsKey("FireBlastRange"))
				abil.setRange(abil.getRange() + abil.getRange() * attribs.get("FireBlastRange") / 100.0);
			
			if (attribs.containsKey("FireBlastSpeed"))
				abil.setSpeed(abil.getSpeed() + abil.getSpeed() * attribs.get("FireBlastSpeed") / 100.0);
			
			if (attribs.containsKey("FireBlastSpeedFactor"))
				abil.setSpeedFactor(abil.getSpeedFactor() + abil.getSpeedFactor() * attribs.get("FireBlastSpeedFactor") / 100.0);
			
			return true;
		}
		else if (ability instanceof FireBlastCharged) {
			FireBlastCharged abil = (FireBlastCharged) ability;
			
			if (attribs.containsKey("FireBlastChargedChargeTime"))
				abil.setChargeTime((long) (abil.getChargeTime() + abil.getChargeTime() * attribs.get("FireBlastChargedChargeTime") / 100.0));
			
			if (attribs.containsKey("FireBlastChargedCollisionRadius"))
				abil.setCollisionRadius(abil.getCollisionRadius() + abil.getCollisionRadius() * attribs.get("FireBlastChargedCollisionRadius") / 100.0);
			
			if (attribs.containsKey("FireBlastChargedDamageRadius"))
				abil.setDamageRadius(abil.getDamageRadius() + abil.getDamageRadius() * attribs.get("FireBlastChargedDamageRadius") / 100.0);
			
			if (attribs.containsKey("FireBlastChargedExplosionRadius"))
				abil.setExplosionRadius(abil.getExplosionRadius() + abil.getExplosionRadius() * attribs.get("FireBlastChargedExplosionRadius") / 100.0);
			
			if (attribs.containsKey("FireBlastChargedFireTicks"))
				abil.setFireTicks(abil.getFireTicks() + abil.getFireTicks() * attribs.get("FireBlastChargedFireTicks") / 100.0);
			
			if (attribs.containsKey("FireBlastChargedInnerRadius"))
				abil.setInnerRadius(abil.getInnerRadius() + abil.getInnerRadius() * attribs.get("FireBlastChargedInnerRadius") / 100.0);
			
			if (attribs.containsKey("FireBlastChargedInterval"))
				abil.setInterval((long)(abil.getInterval() + abil.getInterval() * attribs.get("FireBlastChargedInterval") / 100.0));
			
			if (attribs.containsKey("FireBlastChargedMaxDamage"))
				abil.setMaxDamage((int) (abil.getMaxDamage() + abil.getMaxDamage() * attribs.get("FireBlastChargedMaxDamage") / 100.0));
			
			if (attribs.containsKey("FireBlastChargedRange"))
				abil.setRange(abil.getRange() + abil.getRange() * attribs.get("FireBlastChargedRange") / 100.0);
			
			//if (attribs.containsKey("FireBlastChargedTime"))
			//	abil.setTime((long)(abil.getTime() + abil.getTime() * attribs.get("FireBlastChargedTime") / 100.0));
			
			return true;
		}
		else if (ability instanceof FireBurst) {
			FireBurst abil = (FireBurst) ability;
			
			if (attribs.containsKey("FireBurstAnglePhi"))
				abil.setAnglePhi(abil.getAnglePhi() + abil.getAnglePhi() * attribs.get("FireBurstAnglePhi") / 100.0);
			
			if (attribs.containsKey("FireBurstAngleTheta"))
				abil.setAngleTheta(abil.getAngleTheta() + abil.getAngleTheta() * attribs.get("FireBurstAngleTheta") / 100.0);
			
			if (attribs.containsKey("FireBurstChargeTime"))
				abil.setChargeTime((long) (abil.getChargeTime() + abil.getChargeTime() * attribs.get("FireBurstChargeTime") / 100.0));
			
			if (attribs.containsKey("FireBurstCooldown"))
				abil.setCooldown((long) (abil.getCooldown() + abil.getCooldown() * attribs.get("FireBurstCooldown") / 100.0));
			
			if (attribs.containsKey("FireBurstParticlesPercentage"))
				abil.setParticlesPercentage(abil.getParticlesPercentage() + abil.getParticlesPercentage() * attribs.get("FireBurstParticlesPercentage") / 100.0);
			
			if (attribs.containsKey("FireBurstRange"))
				abil.setRange((long) (abil.getRange() + abil.getRange() * attribs.get("FireBurstRange") / 100.0));
			
			return true;
		}
		else if (ability instanceof FireJet) {
			FireJet abil = (FireJet) ability;
			
			if (attribs.containsKey("FireJetCooldown"))
				abil.setCooldown((long) (abil.getCooldown() + abil.getCooldown() * attribs.get("FireJetCooldown") / 100.0));

			if (attribs.containsKey("FireJetDuration"))
				abil.setDuration((long) (abil.getDuration() + abil.getDuration() * attribs.get("FireJetDuration") / 100.0));
			
			if (attribs.containsKey("FireJetSpeed"))
				abil.setSpeed(abil.getSpeed() + abil.getSpeed() * attribs.get("FireJetSpeed") / 100.0);
			
			//if (attribs.containsKey("FireJetTime"))
			//	abil.setTime((long) (abil.getTime() + abil.getTime() * attribs.get("FireJetTime") / 100.0));
			
			return true;
		}
		else if (ability instanceof FireManipulation) {
			//FireManipulation abil = (FireManipulation) ability;
			
			return true;
		}
		else if (ability instanceof FireShield) {
			FireShield abil = (FireShield) ability;
			
			if (attribs.containsKey("FireShieldDiscCooldown"))
				abil.setDiscCooldown((long) (abil.getCooldown() + abil.getCooldown() * attribs.get("FireShieldDiscCooldown") / 100.0)); // Shift
			
			if (attribs.containsKey("FireShieldDiscDuration"))
				abil.setDiscDuration((long) (abil.getDuration() + abil.getDuration() * attribs.get("FireShieldDiscDuration") / 100.0)); // Shift
			
			if (attribs.containsKey("FireShieldDiscRadius"))
				abil.setDiscRadius(abil.getDiscRadius() + abil.getDiscRadius() * attribs.get("FireShieldDiscRadius") / 100.0); // Shift
			
			//if (attribs.containsKey("FireShieldInterval"))
			//	abil.setInterval((long) (abil.getInterval() + abil.getInterval() * attribs.get("FireShieldInterval") / 100.0));
			
			if (attribs.containsKey("FireShieldShieldRadius"))
				abil.setShieldRadius(abil.getShieldRadius() + abil.getShieldRadius() * attribs.get("FireShieldShieldRadius") / 100.0);
			
			if (attribs.containsKey("FireShieldShieldCooldown"))
				abil.setShieldCooldown((long) (abil.getCooldown() + abil.getCooldown() * attribs.get("FireShieldShieldCooldown") / 100.0)); // Click
			
			if (attribs.containsKey("FireShieldShieldDuration"))
				abil.setShieldDuration((long) (abil.getDuration() + abil.getDuration() * attribs.get("FireShieldShieldDuration") / 100.0)); // Click
			
			return true;
		}
		else if (ability instanceof HeatControl) { // HeatControl has no attributes of type double to be set
			//HeatControl abil = (HeatControl) ability;
			//if (attribs.containsKey("HeatControlCookTime"))
				//abil.setCookTime((long) (abil.getCookTime() + abil.getCookTime() * attribs.get("HeatControlCookTime") / 100.0));
			return true;
		}
		else if (ability instanceof Illumination) {
			Illumination abil = (Illumination) ability;
			
			if (attribs.containsKey("IlluminationCooldown"))
				abil.setCooldown((long) (abil.getCooldown() + abil.getCooldown() * attribs.get("IlluminationCooldown") / 100.0));

			if (attribs.containsKey("IlluminationRange"))
				abil.setRange(abil.getRange() + abil.getRange() * attribs.get("IlluminationRange") / 100.0);
			
			return true;
		}
		else if (ability instanceof WallOfFire) {
			WallOfFire abil = (WallOfFire) ability;
			
			if (attribs.containsKey("WallOfFireCooldown"))
				abil.setCooldown((long) (abil.getCooldown() + abil.getCooldown() * attribs.get("WallOfFireCooldown") / 100.0));
			
			if (attribs.containsKey("WallOfFireDamageInterval"))
				abil.setDamageInterval((long) (abil.getDamageInterval() + abil.getDamageInterval() * attribs.get("WallOfFireDamageInterval") / 100.0));
			
			if (attribs.containsKey("WallOfFireDamageTick"))
				abil.setDamageTick((int) (abil.getDamageTick() + abil.getDamageTick() * attribs.get("WallOfFireDamageTick") / 100.0));
			
			if (attribs.containsKey("WallOfFireDuration"))
				abil.setDuration((long) (abil.getDuration() + abil.getDuration() * attribs.get("WallOfFireDuration") / 100.0));
			
			if (attribs.containsKey("WallOfFireFireTicks"))
				abil.setFireTicks(abil.getFireTicks() + abil.getFireTicks() * attribs.get("WallOfFireFireTicks") / 100.0);
			
			if (attribs.containsKey("WallOfFireHeight"))
				abil.setHeight((int) (abil.getHeight() + abil.getHeight() * attribs.get("WallOfFireHeight") / 100.0));
			
			if (attribs.containsKey("WallOfFireInterval"))
				abil.setInterval((long) (abil.getInterval() + abil.getInterval() * attribs.get("WallOfFireInterval") / 100.0));
			
			if (attribs.containsKey("WallOfFireIntervalTick"))
				abil.setIntervalTick((int) (abil.getIntervalTick() + abil.getIntervalTick() * attribs.get("WallOfFireIntervalTick") / 100.0));
			
			if (attribs.containsKey("WallOfFireMaxAngle"))
				abil.setMaxAngle(abil.getMaxAngle() + abil.getMaxAngle() * attribs.get("WallOfFireMaxAngle") / 100.0);
			
			if (attribs.containsKey("WallOfFireRange"))
				abil.setRange((int) (abil.getRange() + abil.getRange() * attribs.get("WallOfFireRange") / 100.0));
			
			//if (attribs.containsKey("WallOfFireTime"))
			//	abil.setTime((long) (abil.getTime() + abil.getTime() * attribs.get("WallOfFireTime") / 100.0));
			
			if (attribs.containsKey("WallOfFireWidth"))
				abil.setWidth((int) (abil.getWidth() + abil.getWidth() * attribs.get("WallOfFireWidth") / 100.0));
			
			return true;
		}
		else if (ability instanceof Combustion) {
			Combustion abil = (Combustion) ability;
			
			if (attribs.containsKey("CombustionCooldown"))
				abil.setCooldown((long) (abil.getCooldown() + abil.getCooldown() * attribs.get("CombustionCooldown") / 100.0));
			
			if (attribs.containsKey("CombustionExplosivePower"))
				abil.setExplosivePower((float) (abil.getExplosivePower() + abil.getExplosivePower() * attribs.get("CombustionExplosivePower") / 100.0));
			
			if (attribs.containsKey("CombustionRadius"))
				abil.setRadius(abil.getRadius() + abil.getRadius() * attribs.get("CombustionRadius") / 100.0);
			
			if (attribs.containsKey("CombustionRange"))
				abil.setRange(abil.getRange() + abil.getRange() * attribs.get("CombustionRange") / 100.0);
			
			if (attribs.containsKey("CombustionSpeed"))
				abil.setSpeed(abil.getSpeed() + abil.getSpeed() * attribs.get("CombustionSpeed") / 100.0);
			
			if (attribs.containsKey("CombustionSpeedFactor"))
				abil.setSpeedFactor(abil.getSpeedFactor() + abil.getSpeedFactor() * attribs.get("CombustionSpeedFactor") / 100.0);
			
			if (attribs.containsKey("CombustionTicks"))
				abil.setTicks((int) (abil.getTicks() + abil.getTicks() * attribs.get("CombustionTicks") / 100.0));
			
			return true;
		}
		else if (ability instanceof Lightning) {
			Lightning abil = (Lightning) ability;
			
			if (attribs.containsKey("LightningChainArcChance"))
				abil.setChainArcChance(abil.getChainArcChance() + abil.getChainArcChance() * attribs.get("LightningChainArcChance") / 100.0);
			
			if (attribs.containsKey("LightningChainRange"))
				abil.setChainRange(abil.getChainRange() + abil.getChainRange() * attribs.get("LightningChainRange") / 100.0);
			
			if (attribs.containsKey("LightningChargeTime"))
				abil.setChargeTime(abil.getChargeTime() + abil.getChargeTime() * attribs.get("LightningChargeTime") / 100.0);
			
			if (attribs.containsKey("LightningCooldown"))
				abil.setCooldown((long) (abil.getCooldown() + abil.getCooldown() * attribs.get("LightningCooldown") / 100.0));
			
			if (attribs.containsKey("LightningMaxArcAngle"))
				abil.setMaxArcAngle(abil.getMaxArcAngle() + abil.getMaxArcAngle() * attribs.get("LightningMaxArcAngle") / 100.0);
			
			if (attribs.containsKey("LightningMaxChainArcs"))
				abil.setMaxChainArcs(abil.getMaxChainArcs() + abil.getMaxChainArcs() * attribs.get("LightningMaxChainArcs") / 100.0);
			
			//if (attribs.containsKey("LightningParticleRotation"))
			//	abil.setParticleRotation(abil.getParticleRotation() + abil.getParticleRotation() * attribs.get("LightningParticleRotation") / 100.0);
			
			if (attribs.containsKey("LightningRange"))
				abil.setRange(abil.getRange() + abil.getRange() * attribs.get("LightningRange") / 100.0);
			
			if (attribs.containsKey("LightningStunChance"))
				abil.setStunChance(abil.getStunChance() + abil.getStunChance() * attribs.get("LightningStunChance") / 100.0);
			
			if (attribs.containsKey("LightningStunDuration"))
				abil.setStunDuration(abil.getStunDuration() + abil.getStunDuration() * attribs.get("LightningStunDuration") / 100.0);
			
			if (attribs.containsKey("LightningSubArcChance"))
				abil.setSubArcChance(abil.getSubArcChance() + abil.getSubArcChance() * attribs.get("LightningSubArcChance") / 100.0);
			
			//if (attribs.containsKey("LightningTime"))
			//	abil.setTime((long) (abil.getTime() + abil.getTime() * attribs.get("LightningTime") / 100.0));
			
			if (attribs.containsKey("LightningWaterArcRange"))
				abil.setWaterArcRange(abil.getWaterArcRange() + abil.getWaterArcRange() * attribs.get("LightningWaterArcRange") / 100.0);
			
			if (attribs.containsKey("LightningWaterArcs"))
				abil.setWaterArcs((int) (abil.getWaterArcs() + abil.getWaterArcs() * attribs.get("LightningWaterArcs") / 100.0));
			
			return true;
		}
		else if (ability instanceof FireComboStream) {
			//FireComboStream abil = (FireComboStream) ability;
			
			// Has no getters
			
			return true;
		}
		else if (ability instanceof FireKick) {
			//FireKick abil = (FireKick) ability;
			
			return true;
		}
		else if (ability instanceof FireSpin) {
			//FireSpin abil = (FireSpin) ability;
			
			return true;
		}
		else if (ability instanceof FireWheel) {
			//FireWheel abil = (FireWheel) ability;
			
			return true;
		}
		else if (ability instanceof JetBlast) {
			//JetBlast abil = (JetBlast) ability;
			
			return true;
		}
		else if (ability instanceof JetBlaze) {
			//JetBlaze abil = (JetBlaze) ability;
			
			return true;
		}
		
		return false;
	}

}
