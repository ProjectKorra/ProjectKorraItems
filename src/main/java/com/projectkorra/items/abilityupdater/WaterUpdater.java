package com.projectkorra.items.abilityupdater;

import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.entity.Player;

import com.projectkorra.projectkorra.waterbending.OctopusForm;
import com.projectkorra.projectkorra.waterbending.SurgeWall;
import com.projectkorra.projectkorra.waterbending.SurgeWave;
import com.projectkorra.projectkorra.waterbending.Torrent;
import com.projectkorra.projectkorra.waterbending.TorrentWave;
import com.projectkorra.projectkorra.waterbending.WaterBubble;
import com.projectkorra.projectkorra.waterbending.WaterManipulation;
import com.projectkorra.projectkorra.waterbending.WaterSpout;
import com.projectkorra.projectkorra.waterbending.WaterSpoutWave;
import com.projectkorra.projectkorra.waterbending.blood.Bloodbending;
import com.projectkorra.projectkorra.waterbending.combo.IceBullet;
import com.projectkorra.projectkorra.waterbending.combo.IceWave;
import com.projectkorra.projectkorra.waterbending.healing.HealingWaters;
import com.projectkorra.projectkorra.waterbending.ice.IceBlast;
import com.projectkorra.projectkorra.waterbending.ice.IceSpikeBlast;
import com.projectkorra.projectkorra.waterbending.ice.IceSpikePillar;
import com.projectkorra.projectkorra.waterbending.ice.IceSpikePillarField;
import com.projectkorra.projectkorra.waterbending.ice.PhaseChange;
import com.projectkorra.projectkorra.waterbending.multiabilities.WaterArms;
import com.projectkorra.projectkorra.waterbending.multiabilities.WaterArmsFreeze;
import com.projectkorra.projectkorra.waterbending.multiabilities.WaterArmsSpear;
import com.projectkorra.projectkorra.waterbending.multiabilities.WaterArmsWhip;
import com.projectkorra.projectkorra.waterbending.plant.PlantRegrowth;

public class WaterUpdater {

	/**
	 * updates the water abilities based on a players bending effect attributes
	 * 
	 * @param player the player that has the effects
	 * @param ability an instance of a water ability
	 * @param attribs the map of the players effects
	 * @return if the ability was updated correctly
	 */
	
	public static boolean updateAbilityDamage(Player player, Object ability, ConcurrentHashMap<String, Double> attribs) {
		if (ability instanceof OctopusForm) {
			OctopusForm abil = (OctopusForm) ability;
			if (attribs.containsKey("OctopusFormDamage"))
				abil.setDamage((int) (abil.getDamage() + abil.getDamage() * attribs.get("OctopusFormDamage") / 100.0));
			return true;
		}
		else if (ability instanceof Torrent) {
			Torrent abil = (Torrent) ability;
			if (attribs.containsKey("TorrentDamage"))
				abil.setDamage(abil.getDamage() + abil.getDamage() * attribs.get("TorrentDamage") / 100.0);
			
			if (attribs.containsKey("TorrentDeflectDamage"))
				abil.setDeflectDamage(abil.getDeflectDamage() + abil.getDeflectDamage() * attribs.get("TorrentDeflectDamage") / 100.0);
			return true;
		}
		else if (ability instanceof WaterManipulation) {
			WaterManipulation abil = (WaterManipulation) ability;
			if (attribs.containsKey("WaterManipulationDamage"))
				abil.setDamage(abil.getDamage() + abil.getDamage() * attribs.get("WaterManipulationDamage") / 100.0);
			return true;
		}
		else if (ability instanceof WaterSpoutWave) {
			WaterSpoutWave abil = (WaterSpoutWave) ability;
			if (attribs.containsKey("WaterSpoutWaveDamage"))
				abil.setDamage(abil.getDamage() + abil.getDamage() * attribs.get("WaterSpoutWaveDamage") / 100.0);
			return true;
		}
		else if (ability instanceof WaterArmsSpear) {
			WaterArmsSpear abil = (WaterArmsSpear) ability;
			if (attribs.containsKey("WaterArmsSpearSpearDamage"))
				abil.setSpearDamage(abil.getSpearDamage() + abil.getSpearDamage() * attribs.get("WaterArmsSpearSpearDamage") / 100.0);
			return true;
		}
		else if (ability instanceof WaterArmsWhip) {
			WaterArmsWhip abil = (WaterArmsWhip) ability;
			if (!abil.isHasDamaged() && attribs.containsKey("WaterArmsWhipPunchDamage"))
				abil.setPunchDamage(abil.getPunchDamage() + abil.getPunchDamage() * attribs.get("WaterArmsWhipPunchDamage") / 100.0);
		}
		else if (ability instanceof IceBlast) {
			IceBlast abil = (IceBlast) ability;
			
			if (attribs.containsKey("IceBlastDamage"))
				abil.setDamage(abil.getDamage() + abil.getDamage() * attribs.get("IceBlastDamage") / 100.0);
			
			return true;
		}
		else if (ability instanceof IceSpikeBlast) {
			IceSpikeBlast abil = (IceSpikeBlast) ability;
			if (attribs.containsKey("IceSpikeDamage"))
				abil.setDamage(abil.getDamage() + abil.getDamage() * attribs.get("IceSpikeDamage") / 100.0);
			return true;
		}
		else if (ability instanceof IceSpikePillar) {
			IceSpikePillar abil = (IceSpikePillar) ability;
			if (attribs.containsKey("IceSpikePillarDamage"))
				abil.setDamage(abil.getDamage() + abil.getDamage() * attribs.get("IceSpikePillarDamage") / 100.0);
			return true;
		}
		else if (ability instanceof IceSpikePillarField) {
			IceSpikePillarField abil = (IceSpikePillarField) ability;
			if (attribs.containsKey("IceSpikePillarFieldDamage"))
				abil.setDamage(abil.getDamage() + abil.getDamage() * attribs.get("IceSpikePillarFieldDamage") / 100.0);
			return true;
		}
		else if (ability instanceof IceBullet) {
			IceBullet abil = (IceBullet) ability;
			if (attribs.containsKey("IceBulletDamage"))
				abil.setDamage(abil.getDamage() + abil.getDamage() * attribs.get("IceBulletDamage") / 100.0);
			return true;
		}
		WaterArmsFreeze f;
		
		return false;
	}
	
	public static boolean updateAbility(Player player, Object ability, ConcurrentHashMap<String, Double> attribs) {
		if (ability instanceof OctopusForm) {
			OctopusForm abil = (OctopusForm) ability;
			
			//if (attribs.containsKey("OctopusFormAngle"))
			//	abil.setAngle(abil.getAngle() + abil.getAngle() * attribs.get("OctopusFormAngle") / 100.0);
			
			//if (attribs.containsKey("OctopusFormAngleIncrement"))
			//	abil.setAngleIncrement(abil.getAngleIncrement() + abil.getAngleIncrement() * attribs.get("OctopusFormAngleIncrement") / 100.0);
			
			if (attribs.containsKey("OctopusFormAttackRange"))
				abil.setAttackRange(abil.getAttackRange() + abil.getAttackRange() * attribs.get("OctopusFormAttackRange") / 100.0);
			
			if (attribs.containsKey("OctopusFormCooldown"))
				abil.setCooldown((long) (abil.getCooldown() + abil.getCooldown() * attribs.get("OctopusFormCooldown") / 100.0));
			
			//if (attribs.containsKey("OctopusFormCurrentAnimationStep"))
			//	abil.setCurrentAnimationStep((int) (abil.getCurrentAnimationStep() + abil.getCurrentAnimationStep() * attribs.get("OctopusFormCurrentAnimationStep") / 100.0));
			
			//if (attribs.containsKey("OctopusFormCurrentFormHeight"))
			//	abil.setCurrentFormHeight(abil.getCurrentFormHeight() + abil.getCurrentFormHeight() * attribs.get("OctopusFormCurrentFormHeight") / 100.0);
			
			if (attribs.containsKey("OctopusFormInterval"))
				abil.setInterval((long) (abil.getInterval() + abil.getInterval() * attribs.get("OctopusFormInterval") / 100.0));
			
			if (attribs.containsKey("OctopusFormKnockback"))
				abil.setKnockback(abil.getKnockback() + abil.getKnockback() * attribs.get("OctopusFormKnockback") / 100.0);
			
			if (attribs.containsKey("OctopusFormRadius"))
				abil.setRadius(abil.getRadius() + abil.getRadius() * attribs.get("OctopusFormRadius") / 100.0);
			
			if (attribs.containsKey("OctopusFormRange"))
				abil.setRange((int) (abil.getRange() + abil.getRange() * attribs.get("OctopusFormRange") / 100.0));
			
			//if (attribs.containsKey("OctopusFormStartAngle"))
			//	abil.setStartAngle(abil.getStartAngle() + abil.getStartAngle() * attribs.get("OctopusFormStartAngle") / 100.0);
			
			//if (attribs.containsKey("OctopusFormStepCounter"))
			//	abil.setStepCounter((int) (abil.getStepCounter() + abil.getStepCounter() * attribs.get("OctopusFormStepCounter") / 100.0));
			
			//if (attribs.containsKey("OctopusFormTime"))
			//	abil.setTime((long) (abil.getTime() + abil.getTime() * attribs.get("OctopusFormTime") / 100.0));
			
			//if (attribs.containsKey("OctopusFormTotalStepCount"))
			//	abil.setTotalStepCount((int) (abil.getTotalStepCount() + abil.getTotalStepCount() * attribs.get("OctopusFormTotalStepCount") / 100.0));
			
			return true;
		}
		else if (ability instanceof SurgeWall) {
			SurgeWall abil = (SurgeWall) ability;
			
			if (attribs.containsKey("SurgeWallCooldown"))
				abil.setCooldown((long) (abil.getCooldown() + abil.getCooldown() * attribs.get("SurgeWallCooldown") / 100.0));
			
			if (attribs.containsKey("SurgeWallInterval"))
				abil.setInterval((long) (abil.getInterval() + abil.getInterval() * attribs.get("SurgeWallInterval") / 100.0));
			
			if (attribs.containsKey("SurgeWallRadius"))
				abil.setRadius(abil.getRadius() + abil.getRadius() * attribs.get("SurgeWallRadius") / 100.0);
			
			if (attribs.containsKey("SurgeWallRange"))
				abil.setRange(abil.getRange() + abil.getRange() * attribs.get("SurgeWallRange") / 100.0);
			
			//if (attribs.containsKey("SurgeWallTime"))
			//	abil.setTime((long) (abil.getTime() + abil.getTime() * attribs.get("SurgeWallTime") / 100.0));
			
			return true;
		}
		else if (ability instanceof SurgeWave) {
			SurgeWave abil = (SurgeWave) ability;
			
			if (attribs.containsKey("SurgeWaveCooldown"))
				abil.setCooldown((long) (abil.getCooldown() + abil.getCooldown() * attribs.get("SurgeWaveCooldown") / 100.0));
			
			//if (attribs.containsKey("SurgeWaveCurrentRadius"))
			//	abil.setCurrentRadius(abil.getCurrentRadius() + abil.getCurrentRadius() * attribs.get("SurgeWaveCurrentRadius") / 100.0);
			
			if (attribs.containsKey("SurgeWaveInterval"))
				abil.setInterval((long) (abil.getInterval() + abil.getInterval() * attribs.get("SurgeWaveInterval") / 100.0));
			
			if (attribs.containsKey("SurgeWaveMaxFreezeRadius"))
				abil.setMaxFreezeRadius(abil.getMaxFreezeRadius() + abil.getMaxFreezeRadius() * attribs.get("SurgeWaveMaxFreezeRadius") / 100.0);
			
			if (attribs.containsKey("SurgeWaveMaxRadius"))
				abil.setMaxRadius(abil.getMaxRadius() + abil.getMaxRadius() * attribs.get("SurgeWaveMaxRadius") / 100.0);
			
			if (attribs.containsKey("SurgeWaveKnockback"))
				abil.setKnockback(abil.getKnockback() + abil.getKnockback() * attribs.get("SurgeWaveKnockback") / 100.0);
			
			if (attribs.containsKey("SurgeWaveRange"))
				abil.setRange(abil.getRange() + abil.getRange() * attribs.get("SurgeWaveRange") / 100.0);
			
			//if (attribs.containsKey("SurgeWaveTime"))
			//	abil.setTime((long) (abil.getTime() + abil.getTime() * attribs.get("SurgeWaveTime") / 100.0));
			
			if (attribs.containsKey("SurgeWaveKnockup"))
				abil.setKnockup(abil.getKnockup() + abil.getKnockup() * attribs.get("SurgeWaveKnockup") / 100.0);
			
			return true;
		}
		else if (ability instanceof Torrent) {
			Torrent abil = (Torrent) ability;
			
			//if (attribs.containsKey("TorrentAngle"))
			//	abil.setAngle(abil.getAngle() + abil.getAngle() * attribs.get("TorrentAngle") / 100.0);
			
			if (attribs.containsKey("TorrentCooldown"))
				abil.setCooldown((long) (abil.getCooldown() + abil.getCooldown() * attribs.get("TorrentCooldown") / 100.0));
			
			if (attribs.containsKey("TorrentInterval"))
				abil.setInterval((long) (abil.getInterval() + abil.getInterval() * attribs.get("TorrentInterval") / 100.0));
			
			//if (attribs.containsKey("TorrentLayer"))
			//	abil.setLayer((int) (abil.getLayer() + abil.getLayer() * attribs.get("TorrentLayer") / 100.0));
			
			if (attribs.containsKey("TorrentMaxLayer"))
				abil.setMaxLayer((int) (abil.getMaxLayer() + abil.getMaxLayer() * attribs.get("TorrentMaxLayer") / 100.0));
			
			if (attribs.containsKey("TorrentMaxUpwardForce"))
				abil.setMaxUpwardForce(abil.getMaxUpwardForce() + abil.getMaxUpwardForce() * attribs.get("TorrentMaxUpwardForce") / 100.0);
			
			if (attribs.containsKey("TorrentPush"))
				abil.setPush(abil.getPush() + abil.getPush() * attribs.get("TorrentPush") / 100.0);
			
			if (attribs.containsKey("TorrentRadius"))
				abil.setRadius(abil.getRadius() + abil.getRadius() * attribs.get("TorrentRadius") / 100.0);
			
			if (attribs.containsKey("TorrentRange"))
				abil.setRange(abil.getRange() + abil.getRange() * attribs.get("TorrentRange") / 100.0);
			
			if (attribs.containsKey("TorrentSelectRange"))
				abil.setSelectRange(abil.getSelectRange() + abil.getSelectRange() * attribs.get("TorrentSelectRange") / 100.0);
			
			//if (attribs.containsKey("TorrentStartAngle"))
			//	abil.setStartAngle(abil.getStartAngle() + abil.getStartAngle() * attribs.get("TorrentStartAngle") / 100.0);
			
			//if (attribs.containsKey("TorrentTime"))
			//	abil.setTime((long) (abil.getTime() + abil.getTime() * attribs.get("TorrentTime") / 100.0));
			
			return true;
		}
		else if (ability instanceof TorrentWave) {
			TorrentWave abil = (TorrentWave) ability;
			
			if (attribs.containsKey("TorrentWaveCooldown"))
				abil.setCooldown((long) (abil.getCooldown() + abil.getCooldown() * attribs.get("TorrentWaveCooldown") / 100.0));
			
			if (attribs.containsKey("TorrentWaveGrowSpeed"))
				abil.setGrowSpeed(abil.getGrowSpeed() + abil.getGrowSpeed() * attribs.get("TorrentWaveGrowSpeed") / 100.0);
			
			if (attribs.containsKey("TorrentWaveInterval"))
				abil.setInterval((long) (abil.getInterval() + abil.getInterval() * attribs.get("TorrentWaveInterval") / 100.0));
			
			if (attribs.containsKey("TorrentWaveKnockback"))
				abil.setKnockback(abil.getKnockback() + abil.getKnockback() * attribs.get("TorrentWaveKnockback") / 100.0);
			
			if (attribs.containsKey("TorrentWaveMaxHeight"))
				abil.setMaxHeight(abil.getMaxHeight() + abil.getMaxHeight() * attribs.get("TorrentWaveMaxHeight") / 100.0);
			
			if (attribs.containsKey("TorrentWaveMaxRadius"))
				abil.setMaxRadius(abil.getMaxRadius() + abil.getMaxRadius() * attribs.get("TorrentWaveMaxRadius") / 100.0);
			
			//if (attribs.containsKey("TorrentWaveRadius"))
			//	abil.setRadius(abil.getRadius() + abil.getRadius() * attribs.get("TorrentWaveRadius") / 100.0);
			
			//if (attribs.containsKey("TorrentWaveTime"))
			//	abil.setTime((long) (abil.getTime() + abil.getTime() * attribs.get("TorrentWaveTime") / 100.0));
			
			return true;
		}
		else if (ability instanceof WaterBubble) {
			//WaterBubble abil = (WaterBubble) ability;
			
			return true;
		}
		else if (ability instanceof WaterManipulation) {
			WaterManipulation abil = (WaterManipulation) ability;
			
			if (attribs.containsKey("WaterManipulationCollisionRadius"))
				abil.setCollisionRadius(abil.getCollisionRadius() + abil.getCollisionRadius() * attribs.get("WaterManipulationCollisionRadius") / 100.0);
			
			if (attribs.containsKey("WaterManipulationCooldown"))
				abil.setCooldown((long) (abil.getCooldown() + abil.getCooldown() * attribs.get("WaterManipulationCooldown") / 100.0));
			
			if (attribs.containsKey("WaterManipulationDeflectRange"))
				abil.setDeflectRange(abil.getDeflectRange() + abil.getDeflectRange() * attribs.get("WaterManipulationDeflectRange") / 100.0);
			
			if (attribs.containsKey("WaterManipulationDispelRange"))
				abil.setDispelRange((int) (abil.getDispelRange() + abil.getDispelRange() * attribs.get("WaterManipulationDispelRange") / 100.0));
			
			if (attribs.containsKey("WaterManipulationInterval"))
				abil.setInterval((long) (abil.getInterval() + abil.getInterval() * attribs.get("WaterManipulationInterval") / 100.0));
			
			if (attribs.containsKey("WaterManipulationPushFactor"))
				abil.setPushFactor(abil.getPushFactor() + abil.getPushFactor() * attribs.get("WaterManipulationPushFactor") / 100.0);
			
			if (attribs.containsKey("WaterManipulationRange"))
				abil.setRange(abil.getRange() + abil.getRange() * attribs.get("WaterManipulationRange") / 100.0);
			
			if (attribs.containsKey("WaterManipulationSelectRange"))
				abil.setSelectRange(abil.getSelectRange() + abil.getSelectRange() * attribs.get("WaterManipulationSelectRange") / 100.0);
			
			if (attribs.containsKey("WaterManipulationSpeed"))
				abil.setSpeed(abil.getSpeed() + abil.getSpeed() * attribs.get("WaterManipulationSpeed") / 100.0);
			
			//if (attribs.containsKey("WaterManipulationTime"))
			//	abil.setTime((long) (abil.getTime() + abil.getTime() * attribs.get("WaterManipulationTime") / 100.0));
			
			return true;
		}
		else if (ability instanceof WaterSpout) {
			WaterSpout abil = (WaterSpout) ability;
			
			//if (attribs.containsKey("WaterSpoutAngle"))
			//	abil.setAngle((int) (abil.getAngle() + abil.getAngle() * attribs.get("WaterSpoutAngle") / 100.0));
			
			if (attribs.containsKey("WaterSpoutHeight"))
				abil.setHeight((int) (abil.getHeight() + abil.getHeight() * attribs.get("WaterSpoutHeight") / 100.0));
			
			if (attribs.containsKey("WaterSpoutInterval"))
				abil.setInterval((long) (abil.getInterval() + abil.getInterval() * attribs.get("WaterSpoutInterval") / 100.0));
			
			//if (attribs.containsKey("WaterSpoutRotation"))
			//	abil.setRotation(abil.getRotation() + abil.getRotation() * attribs.get("WaterSpoutRotation") / 100.0);
			
			//if (attribs.containsKey("WaterSpoutTime"))
			//	abil.setTime((long) (abil.getTime() + abil.getTime() * attribs.get("WaterSpoutTime") / 100.0));
			
			return true;
		}
		else if (ability instanceof WaterSpoutWave) {
			WaterSpoutWave abil = (WaterSpoutWave) ability;
			
			if (attribs.containsKey("WaterSpoutWaveAnimationSpeed"))
				abil.setAnimationSpeed(abil.getAnimationSpeed() + abil.getAnimationSpeed() * attribs.get("WaterSpoutWaveAnimationSpeed") / 100.0);
			
			if (attribs.containsKey("WaterSpoutWaveChargeTime"))
				abil.setChargeTime(abil.getChargeTime() + abil.getChargeTime() * attribs.get("WaterSpoutWaveChargeTime") / 100.0);
			
			if (attribs.containsKey("WaterSpoutWaveCooldown"))
				abil.setCooldown((long) (abil.getCooldown() + abil.getCooldown() * attribs.get("WaterSpoutWaveCooldown") / 100.0));
			
			if (attribs.containsKey("WaterSpoutWaveFlightDuration"))
				abil.setFlightDuration(abil.getFlightDuration() + abil.getFlightDuration() * attribs.get("WaterSpoutWaveFlightDuration") / 100.0);
			
			//if (attribs.containsKey("WaterSpoutWaveProgressCounter"))
			//	abil.setProgressCounter((int) (abil.getProgressCounter() + abil.getProgressCounter() * attribs.get("WaterSpoutWaveProgressCounter") / 100.0));
			
			if (attribs.containsKey("WaterSpoutWaveRadius"))
				abil.setRadius(abil.getRadius() + abil.getRadius() * attribs.get("WaterSpoutWaveRadius") / 100.0);
			
			if (attribs.containsKey("WaterSpoutWaveSelectRange"))
				abil.setSelectRange(abil.getSelectRange() + abil.getSelectRange() * attribs.get("WaterSpoutWaveSelectRange") / 100.0);
			
			if (attribs.containsKey("WaterSpoutWaveSpeed"))
				abil.setSpeed(abil.getSpeed() + abil.getSpeed() * attribs.get("WaterSpoutWaveSpeed") / 100.0);
			
			//if (attribs.containsKey("WaterSpoutWaveTime"))
			//	abil.setTime((long) (abil.getTime() + abil.getTime() * attribs.get("WaterSpoutWaveTime") / 100.0));
			
			if (attribs.containsKey("WaterSpoutWaveWaveRadius"))
				abil.setWaveRadius(abil.getWaveRadius() + abil.getWaveRadius() * attribs.get("WaterSpoutWaveWaveRadius") / 100.0);
			
			return true;
		}
		else if (ability instanceof WaterArms) {
			WaterArms abil = (WaterArms) ability;
			
			if (attribs.containsKey("WaterArmsCooldown"))
				abil.setCooldown((long) (abil.getCooldown() + abil.getCooldown() * attribs.get("WaterArmsCooldown") / 100.0));
			
			//if (attribs.containsKey("WaterArmsFreezeSlot"))
			//	abil.setFreezeSlot((int) (abil.getFreezeSlot() + abil.getFreezeSlot() * attribs.get("WaterArmsFreezeSlot") / 100.0));
			
			//if (attribs.containsKey("WaterArmsInitLength"))
			//	abil.setInitLength((int) (abil.getInitLength() + abil.getInitLength() * attribs.get("WaterArmsInitLength") / 100.0));
			
			//if (attribs.containsKey("WaterArmsLastClickTime"))
			//	abil.setLastClickTime((long) (abil.getLastClickTime() + abil.getLastClickTime() * attribs.get("WaterArmsLastClickTime") / 100.0));
			
			if (attribs.containsKey("WaterArmsLengthReduction"))
				abil.setLengthReduction((int) (abil.getLengthReduction() + abil.getLengthReduction() * attribs.get("WaterArmsLengthReduction") / 100.0));
			
			if (attribs.containsKey("WaterArmsLightningDamage"))
				abil.setLightningDamage(abil.getLightningDamage() + abil.getLightningDamage() * attribs.get("WaterArmsLightningDamage") / 100.0);
			
			if (attribs.containsKey("WaterArmsMaxIceBlasts"))
				abil.setMaxIceBlasts((int) (abil.getMaxIceBlasts() + abil.getMaxIceBlasts() * attribs.get("WaterArmsMaxIceBlasts") / 100.0));
			
			if (attribs.containsKey("WaterArmsMaxPunches"))
				abil.setMaxPunches((int) (abil.getMaxPunches() + abil.getMaxPunches() * attribs.get("WaterArmsMaxPunches") / 100.0));
			
			if (attribs.containsKey("WaterArmsMaxUses"))
				abil.setMaxUses((int) (abil.getMaxUses() + abil.getMaxUses() * attribs.get("WaterArmsMaxUses") / 100.0));
			
			//if (attribs.containsKey("WaterArmsSelectedSlot"))
			//	abil.setSelectedSlot((int) (abil.getSelectedSlot() + abil.getSelectedSlot() * attribs.get("WaterArmsSelectedSlot") / 100.0));
			
			if (attribs.containsKey("WaterArmsSourceGrabRange"))
				abil.setSourceGrabRange((int) (abil.getSourceGrabRange() + abil.getSourceGrabRange() * attribs.get("WaterArmsSourceGrabRange") / 100.0));
			
			return true;
		}
		else if (ability instanceof WaterArmsFreeze) {
			WaterArmsFreeze abil = (WaterArmsFreeze) ability;
			
			//if (attribs.containsKey("WaterArmsFreezeDistanceTravelled"))
			//	abil.setDistanceTravelled((int) (abil.getDistanceTravelled() + abil.getDistanceTravelled() * attribs.get("WaterArmsFreezeDistanceTravelled") / 100.0));
			
			if (attribs.containsKey("WaterArmsFreezeIceDamage"))
				abil.setIceDamage(abil.getIceDamage() + abil.getIceDamage() * attribs.get("WaterArmsFreezeIceDamage") / 100.0);
			
			if (attribs.containsKey("WaterArmsFreezeIceRange"))
				abil.setIceRange((int) (abil.getIceRange() + abil.getIceRange() * attribs.get("WaterArmsFreezeIceRange") / 100.0));
			
			if (attribs.containsKey("WaterArmsFreezeUsageCooldown"))
				abil.setUsageCooldown((long) (abil.getUsageCooldown() + abil.getUsageCooldown() * attribs.get("WaterArmsFreezeUsageCooldown") / 100.0));
			
			return true;
		}
		else if (ability instanceof WaterArmsSpear) {
			WaterArmsSpear abil = (WaterArmsSpear) ability;
			
			//if (attribs.containsKey("WaterArmsSpearDistanceTravelled"))
			//	abil.setDistanceTravelled((int) (abil.getDistanceTravelled() + abil.getDistanceTravelled() * attribs.get("WaterArmsSpearDistanceTravelled") / 100.0));
			
			if (attribs.containsKey("WaterArmsSpearDuration"))
				abil.setSpearDuration((long) (abil.getSpearDuration() + abil.getSpearDuration() * attribs.get("WaterArmsSpearDuration") / 100.0));
			
			if (attribs.containsKey("WaterArmsSpearDurationFullMoon"))
				abil.setSpearDurationFullMoon((long) (abil.getSpearDurationFullMoon() + abil.getSpearDurationFullMoon() * attribs.get("WaterArmsSpearDurationFullMoon") / 100.0));
			
			if (attribs.containsKey("WaterArmsSpearDurationNight"))
				abil.setSpearDurationNight((long) (abil.getSpearDurationNight() + abil.getSpearDurationNight() * attribs.get("WaterArmsSpearDurationNight") / 100.0));
			
			if (attribs.containsKey("WaterArmsSpearLength"))
				abil.setSpearLength((int) (abil.getSpearLength() + abil.getSpearLength() * attribs.get("WaterArmsSpearLength") / 100.0));
			
			if (attribs.containsKey("WaterArmsSpearRange"))
				abil.setSpearRange((int) (abil.getSpearRange() + abil.getSpearRange() * attribs.get("WaterArmsSpearRange") / 100.0));
			
			if (attribs.containsKey("WaterArmsSpearRangeFullMoon"))
				abil.setSpearRangeFullMoon((int) (abil.getSpearRangeFullMoon() + abil.getSpearRangeFullMoon() * attribs.get("WaterArmsSpearRangeFullMoon") / 100.0));
			
			if (attribs.containsKey("WaterArmsSpearRangeNight"))
				abil.setSpearRangeNight((int) (abil.getSpearRangeNight() + abil.getSpearRangeNight() * attribs.get("WaterArmsSpearRangeNight") / 100.0));
			
			if (attribs.containsKey("WaterArmsSpearSphere"))
				abil.setSpearSphere((int) (abil.getSpearSphere() + abil.getSpearSphere() * attribs.get("WaterArmsSpearSphere") / 100.0));
			
			if (attribs.containsKey("WaterArmsSpearSphereFullMoon"))
				abil.setSpearSphereFullMoon((int) (abil.getSpearSphereFullMoon() + abil.getSpearSphereFullMoon() * attribs.get("WaterArmsSpearSphereFullMoon") / 100.0));
			
			if (attribs.containsKey("WaterArmsSpearSphereNight"))
				abil.setSpearSphereNight((int) (abil.getSpearSphereNight() + abil.getSpearSphereNight() * attribs.get("WaterArmsSpearSphereNight") / 100.0));
			
			if (attribs.containsKey("WaterArmsSpearUsageCooldown"))
				abil.setUsageCooldown((long) (abil.getUsageCooldown() + abil.getUsageCooldown() * attribs.get("WaterArmsSpearUsageCooldown") / 100.0));
			
			return true;
		}
		else if (ability instanceof WaterArmsWhip) {
			WaterArmsWhip abil = (WaterArmsWhip) ability;
			
			//if (attribs.containsKey("WaterArmsWhipActiveLength"))
			//	abil.setActiveLength((int) (abil.getActiveLength() + abil.getActiveLength() * attribs.get("WaterArmsWhipActiveLength") / 100.0));
			
			if (attribs.containsKey("WaterArmsWhipGrabDuration"))
				abil.setGrabDuration((long) (abil.getGrabDuration() + abil.getGrabDuration() * attribs.get("WaterArmsWhipGrabDuration") / 100.0));
			
			// if (attribs.containsKey("WaterArmsWhipInitLength"))
			//	abil.setInitLength((int) (abil.getInitLength() + abil.getInitLength() * attribs.get("WaterArmsWhipInitLength") / 100.0));
			
			//if (attribs.containsKey("WaterArmsWhipPlayerHealth"))
			//	abil.setPlayerHealth(abil.getPlayerHealth() + abil.getPlayerHealth() * attribs.get("WaterArmsWhipPlayerHealth") / 100.0);
			
			if (attribs.containsKey("WaterArmsWhipPullMultiplier"))
				abil.setPullMultiplier(abil.getPullMultiplier() + abil.getPullMultiplier() * attribs.get("WaterArmsWhipPullMultiplier") / 100.0);
			
			if (attribs.containsKey("WaterArmsWhipPunchLength"))
				abil.setPunchLength((int) (abil.getPunchLength() + abil.getPunchLength() * attribs.get("WaterArmsWhipPunchLength") / 100.0));
			
			if (attribs.containsKey("WaterArmsWhipPunchLengthFullMoon"))
				abil.setPunchLengthFullMoon((int) (abil.getPunchLengthFullMoon() + abil.getPunchLengthFullMoon() * attribs.get("WaterArmsWhipPunchLengthFullMoon") / 100.0));
			
			if (attribs.containsKey("WaterArmsWhipPunchLengthNight"))
				abil.setPunchLengthNight((int) (abil.getPunchLengthNight() + abil.getPunchLengthNight() * attribs.get("WaterArmsWhipPunchLengthNight") / 100.0));
			
			//if (attribs.containsKey("WaterArmsWhipTime"))
			//	abil.setTime((long) (abil.getTime() + abil.getTime() * attribs.get("WaterArmsWhipTime") / 100.0));
			
			if (attribs.containsKey("WaterArmsWhipUsageCooldown"))
				abil.setUsageCooldown((long) (abil.getUsageCooldown() + abil.getUsageCooldown() * attribs.get("WaterArmsWhipUsageCooldown") / 100.0));
			
			if (attribs.containsKey("WaterArmsWhipWhipLength"))
				abil.setWhipLength((int) (abil.getWhipLength() + abil.getWhipLength() * attribs.get("WaterArmsWhipWhipLength") / 100.0));
			
			if (attribs.containsKey("WaterArmsWhipWhipLengthFullMoon"))
				abil.setWhipLengthFullMoon((int) (abil.getWhipLengthFullMoon() + abil.getWhipLengthFullMoon() * attribs.get("WaterArmsWhipWhipLengthFullMoon") / 100.0));
			
			if (attribs.containsKey("WaterArmsWhipWhipLengthNight"))
				abil.setWhipLengthNight((int) (abil.getWhipLengthNight() + abil.getWhipLengthNight() * attribs.get("WaterArmsWhipWhipLengthNight") / 100.0));
			
			if (attribs.containsKey("WaterArmsWhipWhipLengthWeak"))
				abil.setWhipLengthWeak((int) (abil.getWhipLengthWeak() + abil.getWhipLengthWeak() * attribs.get("WaterArmsWhipWhipLengthWeak") / 100.0));
			
			if (attribs.containsKey("WaterArmsWhipWhipSpeed"))
				abil.setWhipSpeed((int) (abil.getWhipSpeed() + abil.getWhipSpeed() * attribs.get("WaterArmsWhipWhipSpeed") / 100.0));
			
			return true;
		}
		else if (ability instanceof Bloodbending) {
			Bloodbending abil = (Bloodbending) ability;
			
			if (attribs.containsKey("BloodbendingCooldown"))
				abil.setCooldown((long) (abil.getCooldown() + abil.getCooldown() * attribs.get("BloodbendingCooldown") / 100.0));
			
			if (attribs.containsKey("BloodbendingDuration"))
				abil.setDuration((long) (abil.getDuration() + abil.getDuration() * attribs.get("BloodbendingDuration") / 100.0));
			
			if (attribs.containsKey("BloodbendingKnockback"))
				abil.setKnockback(abil.getKnockback() + abil.getKnockback() * attribs.get("BloodbendingKnockback") / 100.0);
			
			if (attribs.containsKey("BloodbendingRange"))
				abil.setRange((int) (abil.getRange() + abil.getRange() * attribs.get("BloodbendingRange") / 100.0));
			
			//if (attribs.containsKey("BloodbendingTime"))
			//	abil.setTime((long) (abil.getTime() + abil.getTime() * attribs.get("BloodbendingTime") / 100.0));
			
			return true;
		}
		else if (ability instanceof HealingWaters) {
			//HealingWaters abil = (HealingWaters) ability;
			
			return true;
		}
		else if (ability instanceof IceBlast) {
			IceBlast abil = (IceBlast) ability;
			
			if (attribs.containsKey("IceBlastCollisionRadius"))
				abil.setCollisionRadius(abil.getCollisionRadius() + abil.getCollisionRadius() * attribs.get("IceBlastCollisionRadius") / 100.0);
			
			if (attribs.containsKey("IceBlastCooldown"))
				abil.setCooldown((long) (abil.getCooldown() + abil.getCooldown() * attribs.get("IceBlastCooldown") / 100.0));
			
			if (attribs.containsKey("IceBlastDeflectRange"))
				abil.setDeflectRange(abil.getDeflectRange() + abil.getDeflectRange() * attribs.get("IceBlastDeflectRange") / 100.0);
			
			if (attribs.containsKey("IceBlastInterval"))
				abil.setInterval((long) (abil.getInterval() + abil.getInterval() * attribs.get("IceBlastInterval") / 100.0));

			if (attribs.containsKey("IceBlastRange"))
				abil.setRange(abil.getRange() + abil.getRange() * attribs.get("IceBlastRange") / 100.0);
			
			//if (attribs.containsKey("IceBlastTime"))
			//	abil.setTime((long) (abil.getTime() + abil.getTime() * attribs.get("IceBlastTime") / 100.0));
			
			return true;
		}
		else if (ability instanceof IceSpikeBlast) {
			IceSpikeBlast abil = (IceSpikeBlast) ability;
			
			if (attribs.containsKey("IceSpikeCollisionRadius"))
				abil.setCollisionRadius(abil.getCollisionRadius() + abil.getCollisionRadius() * attribs.get("IceSpikeCollisionRadius") / 100.0);
			
			if (attribs.containsKey("IceSpikeCooldown"))
				abil.setCooldown((long) (abil.getCooldown() + abil.getCooldown() * attribs.get("IceSpikeCooldown") / 100.0));
			
			if (attribs.containsKey("IceSpikeDeflectRange"))
				abil.setDeflectRange(abil.getDeflectRange() + abil.getDeflectRange() * attribs.get("IceSpikeDeflectRange") / 100.0);
			
			if (attribs.containsKey("IceSpikeInterval"))
				abil.setInterval((long) (abil.getInterval() + abil.getInterval() * attribs.get("IceSpikeInterval") / 100.0));
			
			if (attribs.containsKey("IceSpikeRange"))
				abil.setRange(abil.getRange() + abil.getRange() * attribs.get("IceSpikeRange") / 100.0);
			
			if (attribs.containsKey("IceSpikeSlowCooldown"))
				abil.setSlowCooldown((long) (abil.getSlowCooldown() + abil.getSlowCooldown() * attribs.get("IceSpikeSlowCooldown") / 100.0));
			
			if (attribs.containsKey("IceSpikeSlowDuration"))
				abil.setSlowDuration((int) (abil.getSlowDuration() + abil.getSlowDuration() * attribs.get("IceSpikeSlowDuration") / 100.0));
			
			if (attribs.containsKey("IceSpikeSlowPotency"))
				abil.setSlowPotency((int) (abil.getSlowPotency() + abil.getSlowPotency() * attribs.get("IceSpikeSlowPotency") / 100.0));
			
			//if (attribs.containsKey("IceSpikeTime"))
			//	abil.setTime((long) (abil.getTime() + abil.getTime() * attribs.get("IceSpikeTime") / 100.0));
			
			return true;
		}
		else if (ability instanceof IceSpikePillar) {
			IceSpikePillar abil = (IceSpikePillar) ability;
			
			if (attribs.containsKey("IceSpikePillarCooldown"))
				abil.setCooldown((long) (abil.getCooldown() + abil.getCooldown() * attribs.get("IceSpikePillarCooldown") / 100.0));
			
			if (attribs.containsKey("IceSpikePillarHeight"))
				abil.setHeight((int) (abil.getHeight() + abil.getHeight() * attribs.get("IceSpikePillarHeight") / 100.0));
			
			if (attribs.containsKey("IceSpikePillarInterval"))
				abil.setInterval((long) (abil.getInterval() + abil.getInterval() * attribs.get("IceSpikePillarInterval") / 100.0));
			
			//if (attribs.containsKey("IceSpikePillarProgress"))
			//	abil.setProgress((int) (abil.getProgress() + abil.getProgress() * attribs.get("IceSpikePillarProgress") / 100.0));
			
			if (attribs.containsKey("IceSpikePillarRange"))
				abil.setRange(abil.getRange() + abil.getRange() * attribs.get("IceSpikePillarRange") / 100.0);
			
			//if (attribs.containsKey("IceSpikePillarRemoveTimer"))
			//	abil.setRemoveTimer((long) (abil.getRemoveTimer() + abil.getRemoveTimer() * attribs.get("IceSpikePillarRemoveTimer") / 100.0));
			
			//if (attribs.containsKey("IceSpikePillarRemoveTimestamp"))
			//	abil.setRemoveTimestamp((long) (abil.getRemoveTimestamp() + abil.getRemoveTimestamp() * attribs.get("IceSpikePillarRemoveTimestamp") / 100.0));
			
			if (attribs.containsKey("IceSpikePillarSlowCooldown"))
				abil.setSlowCooldown((long) (abil.getSlowCooldown() + abil.getSlowCooldown() * attribs.get("IceSpikePillarSlowCooldown") / 100.0));
			
			if (attribs.containsKey("IceSpikePillarSlowDuration"))
				abil.setSlowDuration((int) (abil.getSlowDuration() + abil.getSlowDuration() * attribs.get("IceSpikePillarSlowDuration") / 100.0));
			
			if (attribs.containsKey("IceSpikePillarSlowPower"))
				abil.setSlowPower((int) (abil.getSlowPower() + abil.getSlowPower() * attribs.get("IceSpikePillarSlowPower") / 100.0));
			
			if (attribs.containsKey("IceSpikePillarSpeed"))
				abil.setSpeed(abil.getSpeed() + abil.getSpeed() * attribs.get("IceSpikePillarSpeed") / 100.0);
			
			//if (attribs.containsKey("IceSpikePillarTime"))
			//	abil.setTime((long) (abil.getTime() + abil.getTime() * attribs.get("IceSpikePillarTime") / 100.0));
			
			return true;
		}
		else if (ability instanceof IceSpikePillarField) {
			IceSpikePillarField abil = (IceSpikePillarField) ability;
			
			if (attribs.containsKey("IceSpikePillarFieldCooldown"))
				abil.setCooldown((long) (abil.getCooldown() + abil.getCooldown() * attribs.get("IceSpikePillarFieldCooldown") / 100.0));
			
			if (attribs.containsKey("IceSpikePillarFieldNumberOfSpikes"))
				abil.setNumberOfSpikes((int) (abil.getNumberOfSpikes() + abil.getNumberOfSpikes() * attribs.get("IceSpikePillarFieldNumberOfSpikes") / 100.0));
			
			if (attribs.containsKey("IceSpikePillarFieldRadius"))
				abil.setRadius(abil.getRadius() + abil.getRadius() * attribs.get("IceSpikePillarFieldRadius") / 100.0);
			
			return true;
		}
		else if (ability instanceof PhaseChange) {
			PhaseChange abil = (PhaseChange) ability;
			
			if (attribs.containsKey("PhaseChangeDepth"))
				abil.setDepth((int) (abil.getDepth() + abil.getDepth() * attribs.get("PhaseChangeDepth") / 100.0));
			
			if (attribs.containsKey("PhaseChangeFreezeControlRadius"))
				abil.setFreezeControlRadius((int) (abil.getFreezeControlRadius() + abil.getFreezeControlRadius() * attribs.get("PhaseChangeFreezeControlRadius") / 100.0));
			
			if (attribs.containsKey("PhaseChangeSourceRange"))
				abil.setSourceRange((int) (abil.getSourceRange() + abil.getSourceRange() * attribs.get("PhaseChangeSourceRange") / 100.0));
			
			return true;
		}
		else if (ability instanceof PlantRegrowth) {
			PlantRegrowth abil = (PlantRegrowth) ability;
			
			if (attribs.containsKey("PlantRegrowthRegrowTime"))
				abil.setRegrowTime((long) (abil.getRegrowTime() + abil.getRegrowTime() * attribs.get("PlantRegrowthRegrowTime") / 100.0));
			
			//if (attribs.containsKey("PlantRegrowthTime"))
			//	abil.setTime((long) (abil.getTime() + abil.getTime() * attribs.get("PlantRegrowthTime") / 100.0));
			
			return true;
		}
		else if (ability instanceof IceBullet) {
			IceBullet abil = (IceBullet) ability;
			
			if (attribs.containsKey("IceBulletAnimationSpeed"))
				abil.setAnimationSpeed(abil.getAnimationSpeed() + abil.getAnimationSpeed() * attribs.get("IceBulletAnimationSpeed") / 100.0);
			
			if (attribs.containsKey("IceBulletCooldown"))
				abil.setCooldown((long) (abil.getCooldown() + abil.getCooldown() * attribs.get("IceBulletCooldown") / 100.0));
			
			//if (attribs.containsKey("IceBulletLeftClicks"))
			//	abil.setLeftClicks((int) (abil.getLeftClicks() + abil.getLeftClicks() * attribs.get("IceBulletLeftClicks") / 100.0));
			
			if (attribs.containsKey("IceBulletMaxShots"))
				abil.setMaxShots(abil.getMaxShots() + abil.getMaxShots() * attribs.get("IceBulletMaxShots") / 100.0);
			
			if (attribs.containsKey("IceBulletRadius"))
				abil.setRadius(abil.getRadius() + abil.getRadius() * attribs.get("IceBulletRadius") / 100.0);
			
			if (attribs.containsKey("IceBulletRange"))
				abil.setRange(abil.getRange() + abil.getRange() * attribs.get("IceBulletRange") / 100.0);
			
			//if (attribs.containsKey("IceBulletRightClicks"))
			//	abil.setRightClicks((int) (abil.getRightClicks() + abil.getRightClicks() * attribs.get("IceBulletRightClicks") / 100.0));
			
			if (attribs.containsKey("IceBulletShootTime"))
				abil.setShootTime(abil.getShootTime() + abil.getShootTime() * attribs.get("IceBulletShootTime") / 100.0);
			
			//if (attribs.containsKey("IceBulletShots"))
			//	abil.setShots(abil.getShots() + abil.getShots() * attribs.get("IceBulletShots") / 100.0);
			
			if (attribs.containsKey("IceBulletSpeed"))
				abil.setSpeed(abil.getSpeed() + abil.getSpeed() * attribs.get("IceBulletSpeed") / 100.0);
			
			//if (attribs.containsKey("IceBulletTime"))
			//	abil.setTime((long) (abil.getTime() + abil.getTime() * attribs.get("IceBulletTime") / 100.0));
			
			return true;
		}
		else if (ability instanceof IceWave) {
			IceWave abil = (IceWave) ability;
			
			if (attribs.containsKey("IceWaveCooldown"))
				abil.setCooldown((long) (abil.getCooldown() + abil.getCooldown() * attribs.get("IceWaveCooldown") / 100.0));
			
			return true;
		}
		
		return false;
	}
}
