package com.projectkorra.items.abilityupdater;

import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.entity.Player;

import com.projectkorra.projectkorra.waterbending.Bloodbending;
import com.projectkorra.projectkorra.waterbending.IceBlast;
import com.projectkorra.projectkorra.waterbending.IceSpikeBlast;
import com.projectkorra.projectkorra.waterbending.IceSpikePillar;
import com.projectkorra.projectkorra.waterbending.OctopusForm;
import com.projectkorra.projectkorra.waterbending.SurgeWall;
import com.projectkorra.projectkorra.waterbending.SurgeWave;
import com.projectkorra.projectkorra.waterbending.Torrent;
import com.projectkorra.projectkorra.waterbending.TorrentWave;
import com.projectkorra.projectkorra.waterbending.WaterManipulation;
import com.projectkorra.projectkorra.waterbending.WaterSpout;
import com.projectkorra.projectkorra.waterbending.WaterSpoutWave;

public class WaterUpdater {

	/**
	 * updates the water abilities based on a players bending effect attributes
	 * 
	 * @param player the player that has the effects
	 * @param ability an instance of a water ability
	 * @param attribs the map of the players effects
	 * @return if the ability was updated correctly
	 */
	public static boolean updateAbility(Player player, Object ability, ConcurrentHashMap<String, Double> attribs) {
		if (ability instanceof Bloodbending) {
			Bloodbending abil = (Bloodbending) ability;
			if (attribs.containsKey("BloodbendingForce"))
				abil.setThrowFactor(abil.getThrowFactor() + abil.getThrowFactor() * attribs.get("BloodbendingForce") / 100.0);
			if (attribs.containsKey("BloodbendingRange"))
				abil.setRange((int) (abil.getRange() + abil.getRange() * attribs.get("BloodbendingRange") / 100.0));
			if (attribs.containsKey("BloodbendingHoldTime"))
				abil.setHoldTime((long) (abil.getHoldTime() + abil.getHoldTime() * attribs.get("BloodbendingHoldTime") / 100.0));
			if (attribs.containsKey("BloodbendingCooldown"))
				abil.setCooldown((long) (abil.getCooldown() + abil.getCooldown() * attribs.get("BloodbendingCooldown") / 100.0));
			return true;
		} else if (ability instanceof IceBlast) {
			IceBlast abil = (IceBlast) ability;
			if (attribs.containsKey("IceBlastRange"))
				abil.setRange(abil.getRange() + abil.getRange() * attribs.get("IceBlastRange") / 100.0);
			if (attribs.containsKey("IceBlastDamage"))
				abil.setDamage(abil.getDamage() + abil.getDamage() * attribs.get("IceBlastDamage") / 100.0);
			return true;
		} else if (ability instanceof IceSpikeBlast) {
			IceSpikeBlast abil = (IceSpikeBlast) ability;
			if (attribs.containsKey("IceSpikeDamage"))
				abil.setDamage(abil.getDamage() + abil.getDamage() * attribs.get("IceSpikeDamage") / 100.0);
			if (attribs.containsKey("IceSpikeRange"))
				abil.setRange(abil.getRange() + abil.getRange() * attribs.get("IceSpikeRange") / 100.0);
			return true;
		} else if (ability instanceof IceSpikePillar) {
			IceSpikePillar abil = (IceSpikePillar) ability;
			if (attribs.containsKey("IceSpikePillarDamage"))
				abil.setDamage(abil.getDamage() + abil.getDamage() * attribs.get("IceSpikePillarDamage") / 100.0);
			if (attribs.containsKey("IceSpikePillarRange"))
				abil.setRange(abil.getRange() + abil.getRange() * attribs.get("IceSpikePillarRange") / 100.0);
			return true;
		} else if (ability instanceof OctopusForm) {
			OctopusForm abil = (OctopusForm) ability;
			if (attribs.containsKey("OctopusFormRange"))
				abil.setAttackRange((int) (abil.getAttackRange() + abil.getAttackRange() * attribs.get("OctopusFormRange") / 100.0));
			if (attribs.containsKey("OctopusFormDamage"))
				abil.setDamage((int) (abil.getDamage() + abil.getDamage() * attribs.get("OctopusFormDamage") / 100.0));
			if (attribs.containsKey("OctopusFormInterval"))
				abil.setInterval((long) (abil.getInterval() + abil.getInterval() * attribs.get("OctopusFormInterval") / 100.0));
			if (attribs.containsKey("OctopusFormKnockback"))
				abil.setKnockback(abil.getKnockback() + abil.getKnockback() * attribs.get("OctopusFormKnockback") / 100.0);
			if (attribs.containsKey("OctopusFormRadius"))
				abil.setRadius(abil.getRadius() + abil.getRadius() * attribs.get("OctopusFormRadius") / 100.0);
			return true;
		} else if (ability instanceof Torrent) {
			Torrent abil = (Torrent) ability;
			if (attribs.containsKey("TorrentDamage"))
				abil.setDamage((int) (abil.getDamage() + abil.getDamage() * attribs.get("TorrentDamage") / 100.0));
			if (attribs.containsKey("TorrentRange"))
				abil.setRange(abil.getRange() + abil.getRange() * attribs.get("TorrentRange") / 100.0);
			if (attribs.containsKey("TorrentStreamingDamage"))
				abil.setDeflectDamage((int) (abil.getDeflectDamage() + abil.getDeflectDamage() * attribs.get("TorrentStreamingDamage") / 100.0));
			return true;
		} else if (ability instanceof TorrentWave) {
			TorrentWave abil = (TorrentWave) ability;
			if (attribs.containsKey("TorrentWaveRadius"))
				abil.setMaxRadius(abil.getMaxRadius() + abil.getMaxRadius() * attribs.get("TorrentWaveRadius") / 100.0);
			if (attribs.containsKey("TorrentWaveHeight"))
				abil.setMaxHeight(abil.getMaxHeight() + abil.getMaxHeight() * attribs.get("TorrentWaveHeight") / 100.0);
			return true;
		} else if (ability instanceof WaterManipulation) {
			WaterManipulation abil = (WaterManipulation) ability;
			if (attribs.containsKey("WaterManipulationSelectRange"))
				abil.setSelectRange(abil.getSelectRange() + abil.getSelectRange() * attribs.get("WaterManipulationSelectRange") / 100.0);
			if (attribs.containsKey("WaterManipulationDispelRange"))
				abil.setDispelRange((int) (abil.getDispelRange() + abil.getDispelRange() * attribs.get("WaterManipulationDispelRange") / 100.0));
			if (attribs.containsKey("WaterManipulationForce"))
				abil.setPushFactor(abil.getPushFactor() + abil.getPushFactor() * attribs.get("WaterManipulationForce") / 100.0);
			if (attribs.containsKey("WaterManipulationDamage"))
				abil.setDamage(abil.getDamage() + abil.getDamage() * attribs.get("WaterManipulationDamage") / 100.0);
			if (attribs.containsKey("WaterManipulationCooldown"))
				abil.setCooldown((long) (abil.getCooldown() + abil.getCooldown() * attribs.get("WaterManipulationCooldown") / 100.0));
			return true;
		} else if (ability instanceof WaterSpout) {
			WaterSpout abil = (WaterSpout) ability;
			if (attribs.containsKey("WaterSpoutHeight"))
				abil.setHeight((int) (abil.getHeight() + abil.getHeight() * attribs.get("WaterSpoutHeight") / 100.0));
			return true;
		} else if (ability instanceof WaterSpoutWave) {
			WaterSpoutWave abil = (WaterSpoutWave) ability;
			if (attribs.containsKey("WaterSpoutWaveRadius"))
				abil.setWaveRadius(abil.getWaveRadius() + abil.getWaveRadius() * attribs.get("WaterSpoutWaveRadius") / 100.0);
			if (attribs.containsKey("WaterSpoutWaveRange"))
				abil.setRadius(abil.getRadius() + abil.getRadius() * attribs.get("WaterSpoutWaveRange") / 100.0);
			if (attribs.containsKey("WaterSpoutWaveSpeed"))
				abil.setSpeed(abil.getSpeed() + abil.getSpeed() * attribs.get("WaterSpoutWaveSpeed") / 100.0);
			if (attribs.containsKey("WaterSpoutWaveChargeTime"))
				abil.setChargeTime(abil.getChargeTime() + abil.getChargeTime() * attribs.get("WaterSpoutWaveChargeTime") / 100.0);
			if (attribs.containsKey("WaterSpoutWaveFlightTime"))
				abil.setFlightTime(abil.getFlightTime() + abil.getFlightTime() * attribs.get("WaterSpoutWaveFlightTime") / 100.0);
			if (attribs.containsKey("IceWaveDamage"))
				abil.setDamage(abil.getDamage() + abil.getDamage() * attribs.get("IceWaveDamage") / 100.0);
			return true;
		} else if (ability instanceof SurgeWall) {
			SurgeWall abil = (SurgeWall) ability;
			if (attribs.containsKey("SurgeWallRadius"))
				abil.setRadius(abil.getRadius() + abil.getRadius() * attribs.get("SurgeWallRadius") / 100.0);
			if (attribs.containsKey("SurgeWallRange"))
				abil.setRange(abil.getRange() + abil.getRange() * attribs.get("SurgeWallRange") / 100.0);
			return true;
		} else if (ability instanceof SurgeWave) {
			SurgeWave abil = (SurgeWave) ability;
			if (attribs.containsKey("SurgeWaveRadius"))
				abil.setMaxRadius(abil.getMaxRadius() + abil.getMaxRadius() * attribs.get("SurgeWaveRadius") / 100.0);
			if (attribs.containsKey("SurgeWaveKnockback"))
				abil.setPushFactor(abil.getPushFactor() + abil.getPushFactor() * attribs.get("SurgeWaveKnockback") / 100.0);
			if (attribs.containsKey("SurgeWaveKnockup"))
				abil.setVerticalFactor(abil.getVerticalFactor() + abil.getVerticalFactor() * attribs.get("SurgeWaveKnockup") / 100.0);
			if (attribs.containsKey("SurgeWaveFreezeSize"))
				abil.setMaxFreezeRadius(abil.getMaxFreezeRadius() + abil.getMaxFreezeRadius() * attribs.get("SurgeWaveFreezeSize") / 100.0);
			return true;
		}
		return false;
	}
}
