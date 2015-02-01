package com.projectkorra.ProjectKorraItems.abilityupdater;

import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.entity.Player;

import com.projectkorra.ProjectKorra.airbending.AirBlast;
import com.projectkorra.ProjectKorra.airbending.AirBubble;
import com.projectkorra.ProjectKorra.airbending.AirScooter;
import com.projectkorra.ProjectKorra.airbending.AirShield;
import com.projectkorra.ProjectKorra.airbending.AirSpout;
import com.projectkorra.ProjectKorra.airbending.AirSuction;
import com.projectkorra.ProjectKorra.airbending.AirSwipe;
import com.projectkorra.ProjectKorra.airbending.Tornado;

public class AirUpdater {
	
	/**
	 * updates the air abilities based on a players bending effect attributes
	 * @param player the player that has the effects
	 * @param ability an instance of a air ability
	 * @param attribs the map of the players effects
	 * @return if the ability was updated correctly
	 */
	public static boolean updateAbility(Player player, Object ability, ConcurrentHashMap<String, Double> attribs) {
		if(ability instanceof AirBlast) {
			AirBlast abil = (AirBlast) ability;
			if(attribs.containsKey("AirBlastRange"))
				abil.setRange(abil.getRange() + abil.getRange() * attribs.get("AirBlastRange") / 100.0);
			if(attribs.containsKey("AirBlastForce"))
				abil.setPushfactor(abil.getPushfactor() + abil.getPushfactor() * attribs.get("AirBlastForce") / 100.0);
			return true;
		}
		else if(ability instanceof AirSwipe) {
			AirSwipe abil = (AirSwipe) ability;
			if(attribs.containsKey("AirSwipeDamage"))
				abil.setDamage(abil.getDamage() + abil.getDamage() * attribs.get("AirSwipeDamage") / 100.0);
			if(attribs.containsKey("AirSwipeForce"))
				abil.setPushfactor(abil.getPushfactor() + abil.getPushfactor() * attribs.get("AirSwipeForce") / 100.0);
			if(attribs.containsKey("AirSwipeSpeed"))
				abil.setSpeed(abil.getSpeed() + abil.getSpeed() * attribs.get("AirSwipeSpeed") / 100.0);
			if(attribs.containsKey("AirSwipeRange"))
				abil.setRange(abil.getRange() + abil.getRange() * attribs.get("AirSwipeRange") / 100.0);
			if(attribs.containsKey("AirSwipeMaxCharge"))
				abil.setMaxfactor(abil.getMaxfactor() + abil.getMaxfactor() * attribs.get("AirSwipeMaxCharge") / 100.0);
			if(attribs.containsKey("AirSwipeRadius"))
				abil.setAffectingradius(abil.getAffectingradius() + abil.getAffectingradius() * attribs.get("AirSwipeRadius") / 100.0);
			if(attribs.containsKey("AirSwipeArc"))
				abil.setArc((int) (abil.getArc() + abil.getArc() * attribs.get("AirSwipeArc") / 100.0));
			if(attribs.containsKey("AirSwipeChargeTime"))
				abil.setMaxchargetime((long) (abil.getMaxchargetime() + abil.getMaxchargetime() * attribs.get("AirSwipeChargeTime") / 100.0));
			return true;
		}
		else if(ability instanceof AirShield) {
			AirShield abil = (AirShield) ability;
			if(attribs.containsKey("AirShieldRadius"))
				abil.setMaxradius(abil.getMaxradius() + abil.getMaxradius() * attribs.get("AirShieldRadius") / 100.0);
			return true;
		}
		else if(ability instanceof AirBubble) {
			AirBubble abil = (AirBubble) ability;
			if(attribs.containsKey("AirBubbleRadius"))
				abil.setDefaultAirRadius(abil.getDefaultAirRadius() + abil.getDefaultAirRadius() * attribs.get("AirBubbleRadius") / 100.0);
			if(attribs.containsKey("WaterBubbleRadius"))
				abil.setDefaultWaterRadius(abil.getDefaultWaterRadius() + abil.getDefaultWaterRadius() * attribs.get("WaterBubbleRadius") / 100.0);
			return true;
		}
		else if(ability instanceof AirScooter) {
			AirScooter abil = (AirScooter) ability;
			if(attribs.containsKey("AirScooterSpeed"))
				abil.setSpeed(abil.getSpeed() + abil.getSpeed() * attribs.get("AirScooterSpeed") / 100.0);
			return true;
		}
		else if(ability instanceof Tornado) {
			Tornado abil = (Tornado) ability;
			if(attribs.containsKey("TornadoMaxHeight"))
				abil.setMaxheight(abil.getMaxheight() + abil.getMaxheight() * attribs.get("TornadoMaxHeight") / 100.0);
			if(attribs.containsKey("TornadoMaxRadius"))
				abil.setMaxradius(abil.getMaxradius() + abil.getMaxradius() * attribs.get("TornadoMaxRadius") / 100.0);
			if(attribs.containsKey("TornadoRange"))
				abil.setRange(abil.getRange() + abil.getRange() * attribs.get("TornadoRange") / 100.0);
			if(attribs.containsKey("TornadoPlayerPushFactor"))
				abil.setPCpushfactor(abil.getPCpushfactor() + abil.getPCpushfactor() * attribs.get("TornadoPlayerPushFactor") / 100.0);
			if(attribs.containsKey("TornadoNPCPushFactor"))
				abil.setNPCpushfactor(abil.getNPCpushfactor() + abil.getNPCpushfactor() * attribs.get("TornadoNPCPushFactor") / 100.0);
			return true;
		}
		else if(ability instanceof AirSpout) {
			AirSpout abil = (AirSpout) ability;
			if(attribs.containsKey("AirSpoutHeight"))
				abil.setHeight(abil.getHeight() + abil.getHeight() * attribs.get("AirSpoutHeight") / 100.0);
			return true;
		}
		else if(ability instanceof AirSuction) {
			AirSuction abil = (AirSuction) ability;
			if(attribs.containsKey("AirSuctionSpeed"))
				abil.setSpeed(abil.getSpeed() + abil.getSpeed() * attribs.get("AirSuctionSpeed") / 100.0);
			if(attribs.containsKey("AirSuctionRange"))
				abil.setRange(abil.getRange() + abil.getRange() * attribs.get("AirSuctionRange") / 100.0);
			if(attribs.containsKey("AirSuctionForce"))
				abil.setPushfactor(abil.getPushfactor() + abil.getPushfactor() * attribs.get("AirSuctionForce") / 100.0);
			if(attribs.containsKey("AirSuctionRadius"))
				abil.setAffectingradius(abil.getAffectingradius() + abil.getAffectingradius() * attribs.get("AirSuctionRadius") / 100.0);
			return true;
		}
		
		return false;
	}
}
