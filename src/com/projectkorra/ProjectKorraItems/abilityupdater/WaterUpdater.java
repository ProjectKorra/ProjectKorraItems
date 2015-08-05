package com.projectkorra.ProjectKorraItems.abilityupdater;

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

import java.util.concurrent.ConcurrentHashMap;

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
				abil.setFactor(abil.getFactor() + abil.getFactor() * attribs.get("BloodbendingForce") / 100.0);
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
				abil.setDefaultdamage(abil.getDefaultdamage() + abil.getDefaultdamage() * attribs.get("IceBlastDamage") / 100.0);
			return true;
		} else if (ability instanceof IceSpike) {
			IceSpike abil = (IceSpike) ability;
			if (attribs.containsKey("IceSpikeDamage"))
				abil.setDamage(abil.getDamage() + abil.getDamage() * attribs.get("IceSpikeDamage") / 100.0);
			if (attribs.containsKey("IceSpikeRange"))
				abil.setRange(abil.getRange() + abil.getRange() * attribs.get("IceSpikeRange") / 100.0);
			return true;
		} else if (ability instanceof IceSpike2) {
			IceSpike2 abil = (IceSpike2) ability;
			if (attribs.containsKey("IceSpikePillarDamage"))
				abil.setDefaultdamage(abil.getDefaultdamage() + abil.getDefaultdamage() * attribs.get("IceSpikePillarDamage") / 100.0);
			if (attribs.containsKey("IceSpikePillarRange"))
				abil.setDefaultrange(abil.getDefaultrange() + abil.getDefaultrange() * attribs.get("IceSpikePillarRange") / 100.0);
			return true;
		} else if (ability instanceof OctopusForm) {
			OctopusForm abil = (OctopusForm) ability;
			if (attribs.containsKey("OctopusFormRange"))
				abil.setRange((int) (abil.getRange() + abil.getRange() * attribs.get("OctopusFormRange") / 100.0));
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
				abil.setDeflectdamage((int) (abil.getDeflectdamage() + abil.getDeflectdamage() * attribs.get("TorrentStreamingDamage") / 100.0));
			return true;
		} else if (ability instanceof TorrentBurst) {
			TorrentBurst abil = (TorrentBurst) ability;
			if (attribs.containsKey("TorrentWaveRadius"))
				abil.setMaxradius(abil.getMaxradius() + abil.getMaxradius() * attribs.get("TorrentWaveRadius") / 100.0);
			if (attribs.containsKey("TorrentWaveForce"))
				abil.setFactor(abil.getFactor() + abil.getFactor() * attribs.get("TorrentWaveForce") / 100.0);
			if (attribs.containsKey("TorrentWaveHeight"))
				abil.setMaxheight(abil.getMaxheight() + abil.getMaxheight() * attribs.get("TorrentWaveHeight") / 100.0);
			return true;
		} else if (ability instanceof WaterManipulation) {
			WaterManipulation abil = (WaterManipulation) ability;
			if (attribs.containsKey("WaterManipulationRange"))
				abil.setRange(abil.getRange() + abil.getRange() * attribs.get("WaterManipulationRange") / 100.0);
			if (attribs.containsKey("WaterManipulationForce"))
				abil.setPushfactor(abil.getPushfactor() + abil.getPushfactor() * attribs.get("WaterManipulationForce") / 100.0);
			if (attribs.containsKey("WaterManipulationDamage"))
				abil.setDamage(abil.getDamage() + abil.getDamage() * attribs.get("WaterManipulationDamage") / 100.0);
			if (attribs.containsKey("WaterManipulationCooldown"))
				abil.setCooldown((long) (abil.getCooldown() + abil.getCooldown() * attribs.get("WaterManipulationCooldown") / 100.0));
			return true;
		} else if (ability instanceof WaterSpout) {
			WaterSpout abil = (WaterSpout) ability;
			if (attribs.containsKey("WaterSpoutHeight"))
				abil.setDefaultheight((int) (abil.getDefaultheight() + abil.getDefaultheight() * attribs.get("WaterSpoutHeight") / 100.0));
			return true;
		} else if (ability instanceof WaterWave) {
			WaterWave abil = (WaterWave) ability;
			if (attribs.containsKey("WaterSpoutWaveRadius"))
				abil.setWaveRadius(abil.getWaveRadius() + abil.getWaveRadius() * attribs.get("WaterSpoutWaveRadius") / 100.0);
			if (attribs.containsKey("WaterSpoutWaveRange"))
				abil.setRange(abil.getRange() + abil.getRange() * attribs.get("WaterSpoutWaveRange") / 100.0);
			if (attribs.containsKey("WaterSpoutWaveSpeed"))
				abil.setSpeed(abil.getSpeed() + abil.getSpeed() * attribs.get("WaterSpoutWaveSpeed") / 100.0);
			if (attribs.containsKey("WaterSpoutWaveChargeTime"))
				abil.setChargeTime(abil.getChargeTime() + abil.getChargeTime() * attribs.get("WaterSpoutWaveChargeTime") / 100.0);
			if (attribs.containsKey("WaterSpoutWaveFlightTime"))
				abil.setFlightTime(abil.getFlightTime() + abil.getFlightTime() * attribs.get("WaterSpoutWaveFlightTime") / 100.0);
			if (attribs.containsKey("IceWaveDamage"))
				abil.setDamage(abil.getDamage() + abil.getDamage() * attribs.get("IceWaveDamage") / 100.0);
			return true;
		} else if (ability instanceof WaterWall) {
			WaterWall abil = (WaterWall) ability;
			if (attribs.containsKey("SurgeWallRadius"))
				abil.setRadius(abil.getRadius() + abil.getRadius() * attribs.get("SurgeWallRadius") / 100.0);
			if (attribs.containsKey("SurgeWallRange"))
				abil.setRange(abil.getRange() + abil.getRange() * attribs.get("SurgeWallRange") / 100.0);
			return true;
		} else if (ability instanceof Wave) {
			Wave abil = (Wave) ability;
			if (attribs.containsKey("SurgeWaveRadius"))
				abil.setMaxradius(abil.getMaxradius() + abil.getMaxradius() * attribs.get("SurgeWaveRadius") / 100.0);
			if (attribs.containsKey("SurgeWaveKnockback"))
				abil.setFactor(abil.getFactor() + abil.getFactor() * attribs.get("SurgeWaveKnockback") / 100.0);
			if (attribs.containsKey("SurgeWaveKnockup"))
				abil.setUpfactor(abil.getUpfactor() + abil.getUpfactor() * attribs.get("SurgeWaveKnockup") / 100.0);
			if (attribs.containsKey("SurgeWaveFreezeSize"))
				abil.setMaxfreezeradius(abil.getMaxfreezeradius() + abil.getMaxfreezeradius() * attribs.get("SurgeWaveFreezeSize") / 100.0);
			return true;
		}
		return false;
	}
}
