package com.projectkorra.items.abilityupdater;

import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.entity.Player;

import com.projectkorra.projectkorra.earthbending.Catapult;
import com.projectkorra.projectkorra.earthbending.Collapse;
import com.projectkorra.projectkorra.earthbending.CollapseWall;
import com.projectkorra.projectkorra.earthbending.EarthArmor;
import com.projectkorra.projectkorra.earthbending.EarthBlast;
import com.projectkorra.projectkorra.earthbending.EarthDome;
import com.projectkorra.projectkorra.earthbending.EarthGrab;
import com.projectkorra.projectkorra.earthbending.EarthSmash;
import com.projectkorra.projectkorra.earthbending.EarthTunnel;
import com.projectkorra.projectkorra.earthbending.RaiseEarth;
import com.projectkorra.projectkorra.earthbending.RaiseEarthWall;
import com.projectkorra.projectkorra.earthbending.Ripple;
import com.projectkorra.projectkorra.earthbending.Shockwave;
import com.projectkorra.projectkorra.earthbending.Tremorsense;
import com.projectkorra.projectkorra.earthbending.combo.EarthDomeOthers;
import com.projectkorra.projectkorra.earthbending.combo.EarthDomeSelf;
import com.projectkorra.projectkorra.earthbending.combo.EarthPillars;
import com.projectkorra.projectkorra.earthbending.lava.LavaFlow;
import com.projectkorra.projectkorra.earthbending.lava.LavaSurge;
import com.projectkorra.projectkorra.earthbending.lava.LavaSurgeWall;
import com.projectkorra.projectkorra.earthbending.lava.LavaSurgeWave;
import com.projectkorra.projectkorra.earthbending.metal.Extraction;
import com.projectkorra.projectkorra.earthbending.metal.MetalClips;

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
		if (ability instanceof EarthBlast) {
			EarthBlast abil = (EarthBlast) ability;
			
			if (attribs.containsKey("EarthBlastDamage"))
				abil.setDamage(abil.getDamage() + abil.getDamage() * attribs.get("EarthBlastDamage") / 100.0);
			
			return true;
		}
		else if (ability instanceof EarthSmash) {
			EarthSmash abil = (EarthSmash) ability;
			
			if (attribs.containsKey("EarthSmashDamage"))
				abil.setDamage(abil.getDamage() + abil.getDamage() * attribs.get("EarthSmashDamage") / 100.0);
			
			return true;
		}
		else if (ability instanceof Ripple) {
			Ripple abil = (Ripple) ability;
			
			if (attribs.containsKey("ShockwaveDamage"))
				abil.setDamage(abil.getDamage() + abil.getDamage() * attribs.get("ShockwaveDamage") / 100.0);
			
			return true;
		}
		
		return false;
	}
	
	public static boolean updateAbility(Player player, Object ability, ConcurrentHashMap<String, Double> attribs) {
		if (ability instanceof Catapult) {
			Catapult abil = (Catapult) ability;
			
			if (attribs.containsKey("CatapultCooldown"))
				abil.setCooldown((long) (abil.getCooldown() + abil.getCooldown() * attribs.get("CatapultCooldown") / 100.0));
			
			//if (attribs.containsKey("CatapultLength")) // Removed
			//	abil.setLength((int) (abil.getLength() + abil.getLength() * attribs.get("CatapultLength") / 100.0));
				
			//if (attribs.containsKey("CatapultPush")) // Removed
			//	abil.setPush(abil.getPush() + abil.getPush() * attribs.get("CatapultPush") / 100.0);
				
			return true;
		}
		else if (ability instanceof Collapse) {
			Collapse abil = (Collapse) ability;
			
			if (attribs.containsKey("CollapseCooldown"))
				abil.setCooldown((long) (abil.getCooldown() + abil.getCooldown() * attribs.get("CollapseCooldown") / 100.0));
			
			if (attribs.containsKey("CollapseDistance"))
				abil.setDistance((int) (abil.getDistance() + abil.getDistance() * attribs.get("CollapseDistance") / 100.0));
			
			if (attribs.containsKey("CollapseHeight"))
				abil.setHeight((int) (abil.getHeight() + abil.getHeight() * attribs.get("CollapseHeight") / 100.0));
			
			if (attribs.containsKey("CollapseSelectRange"))
				abil.setSelectRange(abil.getSelectRange() + abil.getSelectRange() * attribs.get("CollapseSelectRange") / 100.0);
			
			if (attribs.containsKey("CollapseSpeed"))
				abil.setSpeed(abil.getSpeed() + abil.getSpeed() * attribs.get("CollapseSpeed") / 100.0);
			
			//if (attribs.containsKey("CollapseTime"))
			//	abil.setTime((long) (abil.getTime() + abil.getTime() * attribs.get("CollapseTime") / 100.0));
			
			return true;
		}
		else if (ability instanceof CollapseWall) {
			CollapseWall abil = (CollapseWall) ability;
			
			if (attribs.containsKey("CollapseWallCooldown"))
				abil.setCooldown((long) (abil.getCooldown() + abil.getCooldown() * attribs.get("CollapseWallCooldown") / 100.0));
			
			if (attribs.containsKey("CollapseWallHeight"))
				abil.setHeight((int) (abil.getHeight() + abil.getHeight() * attribs.get("CollapseWallHeight") / 100.0));
			
			if (attribs.containsKey("CollapseWallRadius"))
				abil.setRadius(abil.getRadius() + abil.getRadius() * attribs.get("CollapseWallRadius") / 100.0);
			
			if (attribs.containsKey("CollapseWallSelectRange"))
				abil.setSelectRange((int) (abil.getSelectRange() + abil.getSelectRange() * attribs.get("CollapseWallSelectRange") / 100.0));
			
			return true;
		}
		else if (ability instanceof EarthArmor) {
			EarthArmor abil = (EarthArmor) ability;
			
			if (attribs.containsKey("EarthArmorCooldown"))
				abil.setCooldown((long) (abil.getCooldown() + abil.getCooldown() * attribs.get("EarthArmorCooldown") / 100.0));
			
			//if (attribs.containsKey("EarthArmorGoldHearts"))
			//	abil.setGoldHearts((float) (abil.getGoldHearts() + abil.getGoldHearts() * attribs.get("EarthArmorGoldHearts") / 100.0));
			
			if (attribs.containsKey("EarthArmorInterval"))
				abil.setInterval((long) (abil.getInterval() + abil.getInterval() * attribs.get("EarthArmorInterval") / 100.0));
			
			if (attribs.containsKey("EarthArmorMaxGoldHearts"))
				abil.setMaxGoldHearts((int) (abil.getMaxGoldHearts() + abil.getMaxGoldHearts() * attribs.get("EarthArmorMaxGoldHearts") / 100.0));
			
			if (attribs.containsKey("EarthArmorSelectRange"))
				abil.setSelectRange(abil.getSelectRange() + abil.getSelectRange() * attribs.get("EarthArmorSelectRange") / 100.0);
			
			//if (attribs.containsKey("EarthArmorStrength")) // Removed
			//	abil.setStrength((int) (abil.getStrength() + abil.getStrength() * attribs.get("EarthArmorStrength") / 100.0));
				
			return true;
		}
		else if (ability instanceof EarthBlast) {
			EarthBlast abil = (EarthBlast) ability;
			
			if (attribs.containsKey("EarthBlastCollisionRadius"))
				abil.setCollisionRadius(abil.getCollisionRadius() + abil.getCollisionRadius() * attribs.get("EarthBlastCollisionRadius") / 100.0);
			
			if (attribs.containsKey("EarthBlastCooldown"))
				abil.setCooldown((long) (abil.getCooldown() + abil.getCooldown() * attribs.get("EarthBlastCooldown") / 100.0));
			
			if (attribs.containsKey("EarthBlastDeflectRange"))
				abil.setDeflectRange(abil.getDeflectRange() + abil.getDeflectRange() * attribs.get("EarthBlastDeflectRange") / 100.0);
			
			if (attribs.containsKey("EarthBlastInterval"))
				abil.setInterval((long) (abil.getInterval() + abil.getInterval() * attribs.get("EarthBlastInterval") / 100.0));
			
			if (attribs.containsKey("EarthBlastPushFactor"))
				abil.setPushFactor(abil.getPushFactor() + abil.getPushFactor() * attribs.get("EarthBlastPushFactor") / 100.0);
			
			if (attribs.containsKey("EarthBlastRange"))
				abil.setRange(abil.getRange() + abil.getRange() * attribs.get("EarthBlastRange") / 100.0);
			
			if (attribs.containsKey("EarthBlastSelectRange"))
				abil.setSelectRange(abil.getSelectRange() + abil.getSelectRange() * attribs.get("EarthBlastSelectRange") / 100.0);
			
			if (attribs.containsKey("EarthBlastSpeed"))
				abil.setSpeed(abil.getSpeed() + abil.getSpeed() * attribs.get("EarthBlastSpeed") / 100.0);
			
			//if (attribs.containsKey("EarthBlastTime"))
			//	abil.setTime((long) (abil.getTime() + abil.getTime() * attribs.get("EarthBlastTime") / 100.0));
			
			return true;
		}
		else if (ability instanceof EarthDome) {
			/* I accessors not properly set
			EarthDome abil = (EarthDome) ability;
			
			if (attribs.containsKey("EarthDomeHeight"))
				abil.height = (int) (abil.height + abil.height * attribs.get("EarthDomeHeight") / 100.0);
			
			if (attribs.containsKey("EarthDomeRadius"))
				abil.radius = abil.radius + abil.radius * attribs.get("EarthDomeRadius") / 100.0;
			*/
			return true;
		}
		else if (ability instanceof EarthGrab) {
			/* I accessors not properly set
			EarthGrab abil = (EarthGrab) ability;
			
			if (attribs.containsKey("EarthGrabCooldown"))
				abil.cooldown = (long) (abil.getCooldown() + abil.getCooldown() * attribs.get("EarthGrabCooldown") / 100.0);
			
			if (attribs.containsKey("EarthGrabDamageThreshold"))
				abil.damageThreshold = abil.damageThreshold + abil.damageThreshold * attribs.get("EarthGrabDamageThreshold") / 100.0;
			
			if (attribs.containsKey("EarthGrabDragSpeed"))
				abil.dragSpeed = abil.dragSpeed + abil.dragSpeed * attribs.get("EarthGrabDragSpeed") / 100.0;
			*/
			return true;
		}
		else if (ability instanceof EarthSmash) {
			EarthSmash abil = (EarthSmash) ability;
			
			//if (attribs.containsKey("EarthSmashAnimationCounter"))
			//	abil.setAnimationCounter((int) (abil.getAnimationCounter() + abil.getAnimationCounter() * attribs.get("EarthSmashAnimationCounter") / 100.0));
			
			if (attribs.containsKey("EarthSmashChargeTime"))
				abil.setChargeTime((long) (abil.getChargeTime() + abil.getChargeTime() * attribs.get("EarthSmashChargeTime") / 100.0));
			
			if (attribs.containsKey("EarthSmashCooldown"))
				abil.setCooldown((long) (abil.getCooldown() + abil.getCooldown() * attribs.get("EarthSmashCooldown") / 100.0));
			
			if (attribs.containsKey("EarthSmashDelay"))
				abil.setDelay((long) (abil.getDelay() + abil.getDelay() * attribs.get("EarthSmashDelay") / 100.0));
			
			if (attribs.containsKey("EarthSmashFlightAnimationInterval"))
				abil.setFlightAnimationInterval((long) (abil.getFlightAnimationInterval() + abil.getFlightAnimationInterval() * attribs.get("EarthSmashFlightAnimationInterval") / 100.0));
			
			if (attribs.containsKey("EarthSmashFlightDetectionRadius"))
				abil.setFlightDetectionRadius(abil.getFlightDetectionRadius() + abil.getFlightDetectionRadius() * attribs.get("EarthSmashFlightDetectionRadius") / 100.0);
			
			if (attribs.containsKey("EarthSmashFlightDuration"))
				abil.setFlightDuration((long) (abil.getFlightDuration() + abil.getFlightDuration() * attribs.get("EarthSmashFlightDuration") / 100.0));
			
			//if (attribs.containsKey("EarthSmashFlightRemoveTimer"))
			//	abil.setFlightRemoveTimer((long) (abil.getFlightRemoveTimer() + abil.getFlightRemoveTimer() * attribs.get("EarthSmashFlightRemoveTimer") / 100.0));
			
			if (attribs.containsKey("EarthSmashFlightSpeed"))
				abil.setFlightSpeed(abil.getFlightSpeed() + abil.getFlightSpeed() * attribs.get("EarthSmashFlightSpeed") / 100.0);
			
			//if (attribs.containsKey("EarthSmashFlightStartTime"))
			//	abil.setFlightStartTime((long) (abil.getFlightStartTime() + abil.getFlightStartTime() * attribs.get("EarthSmashFlightStartTime") / 100.0));
			
			//if (attribs.containsKey("EarthSmashGrabbedDistance"))
			//	abil.setGrabbedDistance(abil.getGrabbedDistance() + abil.getGrabbedDistance() * attribs.get("EarthSmashGrabbedDistance") / 100.0);
			
			if (attribs.containsKey("EarthSmashGrabDetectionRadius"))
				abil.setGrabDetectionRadius(abil.getGrabDetectionRadius() + abil.getGrabDetectionRadius() * attribs.get("EarthSmashGrabDetectionRadius") / 100.0);
			
			if (attribs.containsKey("EarthSmashGrabRange"))
				abil.setGrabRange(abil.getGrabRange() + abil.getGrabRange() * attribs.get("EarthSmashGrabRange") / 100.0);
			
			if (attribs.containsKey("EarthSmashKnockback"))
				abil.setKnockback(abil.getKnockback() + abil.getKnockback() * attribs.get("EarthSmashKnockback") / 100.0);
			
			if (attribs.containsKey("EarthSmashKnockup"))
				abil.setKnockup(abil.getKnockup() + abil.getKnockup() * attribs.get("EarthSmashKnockup") / 100.0);
			
			if (attribs.containsKey("EarthSmashLiftAnimationInterval"))
				abil.setLiftAnimationInterval((long) (abil.getLiftAnimationInterval() + abil.getLiftAnimationInterval() * attribs.get("EarthSmashLiftAnimationInterval") / 100.0));
			
			if (attribs.containsKey("EarthSmashMaxBlocksToPassThrough"))
				abil.setMaxBlocksToPassThrough((int) (abil.getMaxBlocksToPassThrough() + abil.getMaxBlocksToPassThrough() * attribs.get("EarthSmashMaxBlocksToPassThrough") / 100.0));
			
			//if (attribs.containsKey("EarthSmashProgressCounter"))
			//	abil.setProgressCounter((int) (abil.getProgressCounter() + abil.getProgressCounter() * attribs.get("EarthSmashProgressCounter") / 100.0));
			
			//if (attribs.containsKey("EarthSmashRemoveTimer"))
			//	abil.setRemoveTimer((long) (abil.getRemoveTimer() + abil.getRemoveTimer() * attribs.get("EarthSmashRemoveTimer") / 100.0));
			
			//if (attribs.containsKey("EarthSmashRequiredBendableBlocks"))
			//	abil.setRequiredBendableBlocks((int) (abil.getRequiredBendableBlocks() + abil.getRequiredBendableBlocks() * attribs.get("EarthSmashRequiredBendableBlocks") / 100.0));
			
			if (attribs.containsKey("EarthSmashSelectRange"))
				abil.setSelectRange((int) (abil.getSelectRange() + abil.getSelectRange() * attribs.get("EarthSmashSelectRange") / 100.0));
			
			if (attribs.containsKey("EarthSmashShootAnimationInterval"))
				abil.setShootAnimationInterval((long) (abil.getShootAnimationInterval() + abil.getShootAnimationInterval() * attribs.get("EarthSmashShootAnimationInterval") / 100.0));
			
			if (attribs.containsKey("EarthSmashShootRange"))
				abil.setShootRange(abil.getShootRange() + abil.getShootRange() * attribs.get("EarthSmashShootRange") / 100.0);
			
			return true;
		}
		else if (ability instanceof EarthTunnel) {
			EarthTunnel abil = (EarthTunnel) ability;
			
			if (attribs.containsKey("EarthTunnelAngle"))
				abil.setAngle(abil.getAngle() + abil.getAngle() * attribs.get("EarthTunnelAngle") / 100.0);
			
			if (attribs.containsKey("EarthTunnelDepth"))
				abil.setDepth(abil.getDepth() + abil.getDepth() * attribs.get("EarthTunnelDepth") / 100.0);
			
			if (attribs.containsKey("EarthTunnelInterval"))
				abil.setInterval((long) (abil.getInterval() + abil.getInterval() * attribs.get("EarthTunnelInterval") / 100.0));
			
			if (attribs.containsKey("EarthTunnelMaxRadius"))
				abil.setMaxRadius(abil.getMaxRadius() + abil.getMaxRadius() * attribs.get("EarthTunnelMaxRadius") / 100.0);
			
			//if (attribs.containsKey("EarthTunnelRadius"))
			//	abil.setRadius(abil.getRadius() + abil.getRadius() * attribs.get("EarthTunnelRadius") / 100.0);
			
			if (attribs.containsKey("EarthTunnelRadiusIncrement"))
				abil.setRadiusIncrement(abil.getRadiusIncrement() + abil.getRadiusIncrement() * attribs.get("EarthTunnelRadiusIncrement") / 100.0);
			
			if (attribs.containsKey("EarthTunnelRange"))
				abil.setRange(abil.getRange() + abil.getRange() * attribs.get("EarthTunnelRange") / 100.0);
			
			//if (attribs.containsKey("EarthTunnelTime"))
			//	abil.setTime((long) (abil.getTime() + abil.getTime() * attribs.get("EarthTunnelTime") / 100.0));
			
			return true;
		}
		else if (ability instanceof RaiseEarth) {
			RaiseEarth abil = (RaiseEarth) ability;
			
			if (attribs.containsKey("RaiseEarthCooldown"))
				abil.setCooldown((long) (abil.getCooldown() + abil.getCooldown() * attribs.get("RaiseEarthCooldown") / 100.0));
			
			if (attribs.containsKey("RaiseEarthDistance"))
				abil.setDistance((int) (abil.getDistance() + abil.getDistance() * attribs.get("RaiseEarthDistance") / 100.0));
			
			if (attribs.containsKey("RaiseEarthHeight"))
				abil.setHeight((int) (abil.getHeight() + abil.getHeight() * attribs.get("RaiseEarthHeight") / 100.0));
			
			if (attribs.containsKey("RaiseEarthInterval"))
				abil.setInterval((long) (abil.getInterval() + abil.getInterval() * attribs.get("RaiseEarthInterval") / 100.0));
			
			if (attribs.containsKey("RaiseEarthSelectRange"))
				abil.setSelectRange(abil.getSelectRange() + abil.getSelectRange() * attribs.get("RaiseEarthSelectRange") / 100.0);
			
			if (attribs.containsKey("RaiseEarthSpeed"))
				abil.setSpeed(abil.getSpeed() + abil.getSpeed() * attribs.get("RaiseEarthSpeed") / 100.0);
			
			//if (attribs.containsKey("RaiseEarthTime"))
			//	abil.setTime((long) (abil.getTime() + abil.getTime() * attribs.get("RaiseEarthTime") / 100.0));
			
			return true;
		}
		else if (ability instanceof RaiseEarthWall) {
			RaiseEarthWall abil = (RaiseEarthWall) ability;
			
			if (attribs.containsKey("RaiseEarthWallCooldown"))
				abil.setCooldown((long) (abil.getCooldown() + abil.getCooldown() * attribs.get("RaiseEarthWallCooldown") / 100.0));
			
			if (attribs.containsKey("RaiseEarthWallHeight"))
				abil.setHeight((int) (abil.getHeight() + abil.getHeight() * attribs.get("RaiseEarthWallHeight") / 100.0));
			
			if (attribs.containsKey("RaiseEarthWallRange"))
				abil.setRange((int) (abil.getRange() + abil.getRange() * attribs.get("RaiseEarthWallRange") / 100.0));
			
			if (attribs.containsKey("RaiseEarthWallSelectRange"))
				abil.setSelectRange((int) (abil.getSelectRange() + abil.getSelectRange() * attribs.get("RaiseEarthWallSelectRange") / 100.0));
			
			if (attribs.containsKey("RaiseEarthWallSpeed"))
				abil.setWidth((int) (abil.getWidth() + abil.getWidth() * attribs.get("RaiseEarthWallWidth") / 100.0));
			
			return true;
		}
		else if (ability instanceof Ripple) {
			Ripple abil = (Ripple) ability;
			
			if (attribs.containsKey("RippleKnockback"))
				abil.setKnockback(abil.getKnockback() + abil.getKnockback() * attribs.get("RippleKnockback") / 100.0);
			
			if (attribs.containsKey("RippleMaxStep"))
				abil.setMaxStep((int) (abil.getMaxStep() + abil.getMaxStep() * attribs.get("RippleMaxStep") / 100.0));
			
			if (attribs.containsKey("RippleRange"))
				abil.setRange(abil.getRange() + abil.getRange() * attribs.get("RippleRange") / 100.0);
			
			//if (attribs.containsKey("RippleStep"))
			//	abil.setStep((int) (abil.getStep() + abil.getStep() * attribs.get("RippleStep") / 100.0));
			
			return true;
		}
		else if (ability instanceof Shockwave) {
			Shockwave abil = (Shockwave) ability;
			
			if (attribs.containsKey("ShockwaveAngle"))
				abil.setAngle(abil.getAngle() + abil.getAngle() * attribs.get("ShockwaveAngle") / 100.0);
			
			if (attribs.containsKey("ShockwaveChargeTime"))
				abil.setChargeTime((long) (abil.getChargeTime() + abil.getChargeTime() * attribs.get("ShockwaveChargeTime") / 100.0));
			
			if (attribs.containsKey("ShockwaveCooldown"))
				abil.setCooldown((long) (abil.getCooldown() + abil.getCooldown() * attribs.get("ShockwaveCooldown") / 100.0));
			
			if (attribs.containsKey("ShockwaveRange"))
				abil.setRange(abil.getRange() + abil.getRange() * attribs.get("ShockwaveRange") / 100.0);
			
			if (attribs.containsKey("ShockwaveThreshold"))
				abil.setThreshold(abil.getThreshold() + abil.getThreshold() * attribs.get("ShockwaveThreshold") / 100.0);
			
			return true;
		}
		else if (ability instanceof Tremorsense) {
			Tremorsense abil = (Tremorsense) ability;
			
			if (attribs.containsKey("TremorsenseCooldown"))
				abil.setCooldown((long) (abil.getCooldown() + abil.getCooldown() * attribs.get("TremorsenseCooldown") / 100.0));
			
			if (attribs.containsKey("TremorsenseLightThreshold"))
				abil.setLightThreshold((byte) (abil.getLightThreshold() + abil.getLightThreshold() * attribs.get("TremorsenseLightThreshold") / 100.0));
			
			if (attribs.containsKey("TremorsenseMaxDepth"))
				abil.setMaxDepth((int) (abil.getMaxDepth() + abil.getMaxDepth() * attribs.get("TremorsenseMaxDepth") / 100.0));
			
			if (attribs.containsKey("TremorsenseRadius"))
				abil.setRadius((int) (abil.getRadius() + abil.getRadius() * attribs.get("TremorsenseRadius") / 100.0));
			
			return true;
		}
		else if (ability instanceof EarthDomeOthers) {
			//EarthDomeOthers abil = (EarthDomeOthers) ability;
			
			return true;
		}
		else if (ability instanceof EarthDomeSelf) {
			//EarthDomeSelf abil = (EarthDomeSelf) ability;
			
			return true;
		}
		else if (ability instanceof EarthPillars) {
			//EarthPillars abil = (EarthPillars) ability;
			
			return true;
		}
		else if (ability instanceof LavaFlow) {
			//LavaFlow abil = (LavaFlow) ability;
			
			return true;
		}
		else if (ability instanceof LavaSurge) {
			//LavaSurge abil = (LavaSurge) ability;
			
			return true;
		}
		else if (ability instanceof LavaSurgeWall) {
			//LavaSurgeWall abil = (LavaSurgeWall) ability;
			
			return true;
		}
		else if (ability instanceof LavaSurgeWave) {
			//LavaSurgeWave abil = (LavaSurgeWave) ability;
			
			return true;
		}
		else if (ability instanceof Extraction) {
			//Extraction abil = (Extraction) ability;
			
			return true;
		}
		else if (ability instanceof MetalClips) {
			//MetalClips abil = (MetalClips) ability;
			
			return true;
		}
		
		return false;
	}
}
