package com.projectkorra.items.abilityupdater;

import com.projectkorra.projectkorra.airbending.AirBlast;
import com.projectkorra.projectkorra.airbending.AirBurst;
import com.projectkorra.projectkorra.airbending.AirScooter;
import com.projectkorra.projectkorra.airbending.AirShield;
import com.projectkorra.projectkorra.airbending.AirSpout;
import com.projectkorra.projectkorra.airbending.AirSuction;
import com.projectkorra.projectkorra.airbending.AirSwipe;
import com.projectkorra.projectkorra.airbending.Suffocate;
import com.projectkorra.projectkorra.airbending.Tornado;
import com.projectkorra.projectkorra.airbending.combo.AirStream;
import com.projectkorra.projectkorra.airbending.combo.AirSweep;
import com.projectkorra.projectkorra.airbending.combo.Twister;
import com.projectkorra.projectkorra.airbending.flight.FlightMultiAbility;

import org.bukkit.entity.Player;

import java.util.concurrent.ConcurrentHashMap;

public class AirUpdater {

	/**
	 * updates the air abilities based on a players bending effect attributes
	 * 
	 * @param player the player that has the effects
	 * @param ability an instance of a air ability
	 * @param attribs the map of the players effects
	 * @return if the ability was updated correctly
	 */
	
	public static boolean updateAbilityDamage(Player player, Object ability, ConcurrentHashMap<String, Double>attribs) {
		if (ability instanceof AirBlast) { 
			AirBlast abil = (AirBlast) ability;
			
			if (attribs.containsKey("AirBlastDamage"))
				abil.setDamage(abil.getDamage() + abil.getDamage() * attribs.get("AirBlastDamage") / 100.0);
			
			return true;
		}
		else if (ability instanceof AirBurst) {
			AirBurst abil = (AirBurst) ability;
			
			if (attribs.containsKey("AirBurstDamage"))
				abil.setDamage(abil.getDamage() + abil.getDamage() * attribs.get("AirBurstDamage") / 100.0);
			
			return true;
		}
		else if (ability instanceof AirSweep) {
			AirSweep abil = (AirSweep) ability;
			
			if (attribs.containsKey("AirSweepDamage"))
				abil.setDamage(abil.getDamage() + abil.getDamage() * attribs.get("AirSweepDamage") / 100.0);
			
			return true;
		}
		else if (ability instanceof AirSwipe) {
			AirSwipe abil = (AirSwipe) ability;
			
			if (attribs.containsKey("AirSwipeDamage"))
				abil.setDamage(abil.getDamage() + abil.getDamage() * attribs.get("AirSwipeDamage") / 100.0);
			
			return true;
		}
		else if (ability instanceof Suffocate) {
			Suffocate abil = (Suffocate) ability;
			
			if (attribs.containsKey("SuffocateDamage"))
				abil.setDamage(abil.getDamage() + abil.getDamage() * attribs.get("SuffocateDamage") / 100.0);
			
			return true;
		}
		
		return false;
	}
	
	public static boolean updateAbility(Player player, Object ability, ConcurrentHashMap<String, Double> attribs) {
		if (ability instanceof AirBlast) { 
			AirBlast abil = (AirBlast) ability;
			
			if (attribs.containsKey("AirBlastCooldown"))
				abil.setCooldown((long) (abil.getCooldown() + abil.getCooldown() * attribs.get("AirBlastCooldown") / 100.0));
			
			if (attribs.containsKey("AirBlastParticles"))
				abil.setParticles((int) (abil.getParticles() + abil.getParticles() * attribs.get("AirBlastParticles") / 100.0));
			
			if (attribs.containsKey("AirBlastPushFactor"))
				abil.setPushFactor(abil.getPushFactor() + abil.getPushFactor() * attribs.get("AirBlastPushFactor") / 100.0);
			
			if (attribs.containsKey("AirBlastPushFactorForOthers"))
				abil.setPushFactorForOthers(abil.getPushFactorForOthers() + abil.getPushFactorForOthers() * attribs.get("AirBlastPushFactorForOthers") / 100.0);
			
			if (attribs.containsKey("AirBlastRadius"))
				abil.setRadius(abil.getRadius() + abil.getRadius() * attribs.get("AirBlastRadius") / 100.0);
			
			if (attribs.containsKey("AirBlastRange"))
				abil.setRange(abil.getRange() + abil.getRange() * attribs.get("AirBlastRange") / 100.0);
			
			if (attribs.containsKey("AirBlastSpeed"))
				abil.setSpeed(abil.getSpeed() + abil.getSpeed() * attribs.get("AirBlastSpeed") / 100.0);
			
			if (attribs.containsKey("AirBlastSpeedFactor"))
				abil.setSpeedFactor(abil.getSpeedFactor() + abil.getSpeedFactor() * attribs.get("AirBlastSpeedFactor") / 100.0);
			
			//if (attribs.containsKey("AirBlastTicks"))
			//	abil.setTicks((int) (abil.getTicks() + abil.getTicks() * attribs.get("AirBlastTicks") / 100.0));
			
			return true;
		}
		else if (ability instanceof AirBurst) { 
			AirBurst abil = (AirBurst) ability;
			
			if (attribs.containsKey("AirBurstBlastAnglePhi"))
				abil.setBlastAnglePhi(abil.getBlastAnglePhi() + abil.getBlastAnglePhi() * attribs.get("AirBurstBlastAnglePhi") / 100.0);
			
			if (attribs.containsKey("AirBurstBlastAngleTheta"))
				abil.setBlastAngleTheta(abil.getBlastAngleTheta() + abil.getBlastAngleTheta() * attribs.get("AirBurstBlastAngleTheta") / 100.0);
			
			if (attribs.containsKey("AirBurstChargeTime"))
				abil.setChargeTime((long) (abil.getChargeTime() + abil.getChargeTime() * attribs.get("AirBurstChargeTime") / 100.0));
			
			if (attribs.containsKey("AirBurstFallThreshold"))
				abil.setFallThreshold(abil.getFallThreshold() + abil.getFallThreshold() * attribs.get("AirBurstFallThreshold") / 100.0);
			
			if (attribs.containsKey("AirBurstPushFactor"))
				abil.setPushFactor(abil.getPushFactor() + abil.getPushFactor() * attribs.get("AirBurstPushFactor") / 100.0);
			
			return true;
		}
		else if (ability instanceof AirScooter) {
			AirScooter abil = (AirScooter) ability;
			
			if (attribs.containsKey("AirScooterCooldown"))
				abil.setCooldown((long) (abil.getCooldown() + abil.getCooldown() * attribs.get("AirScooterCooldown") / 100.0));
			
			if (attribs.containsKey("AirScooterInterval"))
				abil.setInterval(abil.getInterval() + abil.getInterval() * attribs.get("AirScooterInterval") / 100.0);
			
			if (attribs.containsKey("AirScooterMaxHeightFromGround"))
				abil.setMaxHeightFromGround(abil.getMaxHeightFromGround() + abil.getMaxHeightFromGround() * attribs.get("AirScooterMaxHeightFromGround") / 100.0);
			
			if (attribs.containsKey("AirScooterRadius"))
				abil.setRadius(abil.getRadius() + abil.getRadius() * attribs.get("AirScooterRadius") / 100.0);
			
			if (attribs.containsKey("AirScooterSpeed"))
				abil.setSpeed(abil.getSpeed() + abil.getSpeed() * attribs.get("AirScooterSpeed") / 100.0);
			
			return true;
		}
		else if (ability instanceof AirShield) {
			AirShield abil = (AirShield) ability;
			
			if (attribs.containsKey("AirShieldMaxRadius"))
				abil.setMaxRadius(abil.getMaxRadius() + abil.getMaxRadius() * attribs.get("AirShieldMaxRadius") / 100.0);
			
			if (attribs.containsKey("AirShieldParticles"))
				abil.setParticles((int) (abil.getParticles() + abil.getParticles() * attribs.get("AirShieldParticles") / 100.0));
			
			if (attribs.containsKey("AirShieldRadius"))
				abil.setRadius(abil.getRadius() + abil.getRadius() * attribs.get("AirShieldRadius") / 100.0);
			
			if (attribs.containsKey("AirShieldSpeed"))
				abil.setSpeed(abil.getSpeed() + abil.getSpeed() * attribs.get("AirShieldSpeed") / 100.0);
			
			if (attribs.containsKey("AirShieldStreams"))
				abil.setStreams((int) (abil.getStreams() + abil.getStreams() * attribs.get("AirShieldStreams") / 100.0));
			
			return true;
		}
		else if (ability instanceof AirSpout) {
			AirSpout abil = (AirSpout) ability;
			
			if (attribs.containsKey("AirSpoutAngle"))
				abil.setAngle((int) (abil.getAngle() + abil.getAngle() * attribs.get("AirSpoutAngle") / 100.0));
			
			if (attribs.containsKey("AirSpoutAnimTime"))
				abil.setAnimTime((long) (abil.getAnimTime() + abil.getAnimTime() * attribs.get("AirSpoutAnimTime") / 100.0));
			
			if (attribs.containsKey("AirSpoutCooldown"))
				abil.setCooldown((long) (abil.getCooldown() + abil.getCooldown() * attribs.get("AirSpoutCooldown") / 100.0));
			
			if (attribs.containsKey("AirSpoutHeight"))
				abil.setHeight(abil.getHeight() + abil.getHeight() * attribs.get("AirSpoutHeight") / 100.0);
			
			if (attribs.containsKey("AirSpoutInterval"))
				abil.setInterval((long) (abil.getInterval() + abil.getInterval() * attribs.get("AirSpoutInterval") / 100.0));
			
			return true;
		}
		else if (ability instanceof AirSuction) {
			AirSuction abil = (AirSuction) ability;
			
			if (attribs.containsKey("AirSuctionCooldown"))
				abil.setCooldown((long) (abil.getCooldown() + abil.getCooldown() * attribs.get("AirSuctionCooldown") / 100.0));
			
			//if (attribs.containsKey("AirSuctionParticleCount"))
			//	abil.setParticleCount((int) (abil.getParticleCount() + abil.getParticleCount() * attribs.get("AirSuctionParticleCount") / 100.0));
			
			if (attribs.containsKey("AirSuctionPushFactor"))
				abil.setPushFactor(abil.getPushFactor() + abil.getPushFactor() * attribs.get("AirSuctionPushFactor") / 100.0);
			
			if (attribs.containsKey("AirSuctionRadius"))
				abil.setRadius(abil.getRadius() + abil.getRadius() * attribs.get("AirSuctionRadius") / 100.0);
			
			if (attribs.containsKey("AirSuctionRange"))
				abil.setRange(abil.getRange() + abil.getRange() * attribs.get("AirSuctionRange") / 100.0);
			
			if (attribs.containsKey("AirSuctionSpeed"))
				abil.setSpeed(abil.getSpeed() + abil.getSpeed() * attribs.get("AirSuctionSpeed") / 100.0);
			
			//if (attribs.containsKey("AirSuctionTicks"))
			//	abil.setTicks((int) (abil.getTicks() + abil.getTicks() * attribs.get("AirSuctionTicks") / 100.0));
			
			return true;
		}
		else if (ability instanceof AirSwipe) {
			AirSwipe abil = (AirSwipe) ability;
			
			if (attribs.containsKey("AirSwipeArc"))
				abil.setArc((int) (abil.getArc() + abil.getArc() * attribs.get("AirSwipeArc") / 100.0));
			
			if (attribs.containsKey("AirSwipeCooldown"))
				abil.setCooldown((long) (abil.getCooldown() + abil.getCooldown() * attribs.get("AirSwipeCooldown") / 100.0));
			
			if (attribs.containsKey("AirSwipeMaxChargeFactor"))
				abil.setMaxChargeFactor(abil.getMaxChargeFactor() + abil.getMaxChargeFactor() * attribs.get("AirSwipeMaxChargeFactor") / 100.0);
			
			if (attribs.containsKey("AirSwipeMaxChargeTime"))
				abil.setMaxChargeTime((long) (abil.getMaxChargeTime() + abil.getMaxChargeTime() * attribs.get("AirSwipeMaxChargeTime") / 100.0));
			
			if (attribs.containsKey("AirSwipeParticles"))
				abil.setParticles((int) (abil.getParticles() + abil.getParticles() * attribs.get("AirSwipeParticles") / 100.0));
			
			if (attribs.containsKey("AirSwipePushFactor"))
				abil.setPushFactor(abil.getPushFactor() + abil.getPushFactor() * attribs.get("AirSwipePushFactor") / 100.0);
			
			if (attribs.containsKey("AirSwipeRadius"))
				abil.setRadius(abil.getRadius() + abil.getRadius() * attribs.get("AirSwipeRadius") / 100.0);
			
			if (attribs.containsKey("AirSwipeRange"))
				abil.setRange(abil.getRange() + abil.getRange() * attribs.get("AirSwipeRange") / 100.0);
			
			if (attribs.containsKey("AirSwipeSpeed"))
				abil.setSpeed(abil.getSpeed() + abil.getSpeed() * attribs.get("AirSwipeSpeed") / 100.0);
			
			//if (attribs.containsKey("AirSwipeStepSize"))
			//	abil.setStepSize((int) (abil.getStepSize() + abil.getStepSize() * attribs.get("AirSwipeStepSize") / 100.0));
			
			return true;
		}
		else if (ability instanceof Suffocate) {
			Suffocate abil = (Suffocate) ability;
			
			if (attribs.containsKey("SuffocateAnimationSpeed"))
				abil.setAnimationSpeed(abil.getAnimationSpeed() + abil.getAnimationSpeed() * attribs.get("SuffocateAnimationSpeed") / 100.0);
			
			if (attribs.containsKey("SuffocateBlind"))
				abil.setBlind(abil.getBlind() + abil.getBlind() * attribs.get("SuffocateBlind") / 100.0);
			
			if (attribs.containsKey("SuffocateBlindDelay"))
				abil.setBlindDelay(abil.getBlindDelay() + abil.getBlindDelay() * attribs.get("SuffocateBlindDelay") / 100.0);
			
			if (attribs.containsKey("SuffocateBlindRepeat"))
				abil.setBlindRepeat(abil.getBlindRepeat() + abil.getBlindRepeat() * attribs.get("SuffocateBlindRepeat") / 100.0);
			
			if (attribs.containsKey("SuffocateChargeTime"))
				abil.setChargeTime((long) (abil.getChargeTime() + abil.getChargeTime() * attribs.get("SuffocateChargeTime") / 100.0));
			
			if (attribs.containsKey("SuffocateConstantAimRadius"))
				abil.setConstantAimRadius(abil.getConstantAimRadius() + abil.getConstantAimRadius() * attribs.get("SuffocateConstantAimRadius") / 100.0);
			
			if (attribs.containsKey("SuffocateCooldown"))
				abil.setCooldown((long) (abil.getCooldown() + abil.getCooldown() * attribs.get("SuffocateCooldown") / 100.0));
			
			if (attribs.containsKey("SuffocateDamageDelay"))
				abil.setDamageDelay(abil.getDamageDelay() + abil.getDamageDelay() * attribs.get("SuffocateDamageDelay") / 100.0);
			
			if (attribs.containsKey("SuffocateDamageRepeat"))
				abil.setDamageRepeat(abil.getDamageRepeat() + abil.getDamageRepeat() * attribs.get("SuffocateDamageRepeat") / 100.0);
			
			//if (attribs.containsKey("SuffocateParticleCount"))
			//	abil.setParticleCount((int) (abil.getParticleCount() + abil.getParticleCount() * attribs.get("SuffocateParticleCount") / 100.0));
			
			if (attribs.containsKey("SuffocateRadius"))
				abil.setRadius(abil.getRadius() + abil.getRadius() * attribs.get("SuffocateRadius") / 100.0);
			
			if (attribs.containsKey("SuffocateRange"))
				abil.setRange(abil.getRange() + abil.getRange() * attribs.get("SuffocateRange") / 100.0);
			
			if (attribs.containsKey("SuffocateSlow"))
				abil.setSlow(abil.getSlow() + abil.getSlow() * attribs.get("SuffocateSlow") / 100.0);
			
			if (attribs.containsKey("SuffocateSlowDelay"))
				abil.setSlowDelay(abil.getSlowDelay() + abil.getSlowDelay() * attribs.get("SuffocateSlowDelay") / 100.0);
			
			if (attribs.containsKey("SuffocateSlowRepeat"))
				abil.setSlowRepeat(abil.getSlowRepeat() + abil.getSlowRepeat() * attribs.get("SuffocateSlowRepeat") / 100.0);
			
			return true;
		}
		else if (ability instanceof Tornado) {
			Tornado abil = (Tornado) ability;
			
			//if (attribs.containsKey("TornadoCurrentHeight"))
			//	abil.setCurrentHeight(abil.getCurrentHeight() + abil.getCurrentHeight() * attribs.get("TornadoCurrentHeight") / 100.0);
			
			//if (attribs.containsKey("TornadoCurrentRadius"))
			//	abil.setCurrentRadius(abil.getCurrentRadius() + abil.getCurrentRadius() * attribs.get("TornadoCurrentRadius") / 100.0);
			
			if (attribs.containsKey("TornadoMaxHeight"))
				abil.setMaxHeight(abil.getMaxHeight() + abil.getMaxHeight() * attribs.get("TornadoMaxHeight") / 100.0);
			
			if (attribs.containsKey("TornadoNPCPushFactor"))
				abil.setNpcPushFactor(abil.getNpcPushFactor() + abil.getNpcPushFactor() * attribs.get("TornadoNPCPushFactor") / 100.0);
			
			if (attribs.containsKey("TornadoNumberOfStreams"))
				abil.setNumberOfStreams((int) (abil.getNumberOfStreams() + abil.getNumberOfStreams() * attribs.get("TornadoNumberOfStreams") / 100.0));
			
			//if (attribs.containsKey("TornadoParticleCount"))
			//	abil.setParticleCount((int) (abil.getParticleCount() + abil.getParticleCount() * attribs.get("TornadoParticleCount") / 100.0));
			
			if (attribs.containsKey("TornadoPlayerPushFactor"))
				abil.setPlayerPushFactor(abil.getPlayerPushFactor() + abil.getPlayerPushFactor() * attribs.get("TornadoPlayerPushFactor") / 100.0);
			
			if (attribs.containsKey("TornadoRadius"))
				abil.setRadius(abil.getRadius() + abil.getRadius() * attribs.get("TornadoRadius") / 100.0);
			
			if (attribs.containsKey("TornadoRange"))
				abil.setRange(abil.getRange() + abil.getRange() * attribs.get("TornadoRange") / 100.0);
			
			if (attribs.containsKey("TornadoSpeed"))
				abil.setSpeed(abil.getSpeed() + abil.getSpeed() * attribs.get("TornadoSpeed") / 100.0);
			
			return true;
		}
		else if (ability instanceof AirStream) { 
			AirStream abil = (AirStream) ability;
			
			if (attribs.containsKey("AirStreamCooldown"))
				abil.setCooldown((long) (abil.getCooldown() + abil.getCooldown() * attribs.get("AirStreamCooldown") / 100.0));
			
			if (attribs.containsKey("AirStreamEntityCarryDuration"))
				abil.setAirStreamEntityCarryDuration(abil.getAirStreamEntityCarryDuration() + abil.getAirStreamEntityCarryDuration() * attribs.get("AirStreamEntityCarryDuration") / 100.0);
			
			if (attribs.containsKey("AirStreamMaxEntityHeight"))
				abil.setAirStreamMaxEntityHeight(abil.getAirStreamMaxEntityHeight() + abil.getAirStreamMaxEntityHeight() * attribs.get("AirStreamMaxEntityHeight") / 100.0);
			
			if (attribs.containsKey("AirStreamRange"))
				abil.setRange(abil.getRange() + abil.getRange() * attribs.get("AirStreamRange") / 100.0);
			
			if (attribs.containsKey("AirStreamSpeed"))
				abil.setSpeed(abil.getSpeed() + abil.getSpeed() * attribs.get("AirStreamSpeed") / 100.0);
			
			//if (attribs.containsKey("AirStreamTime"))
			//	abil.setTime((long) (abil.getTime() + abil.getTime() * attribs.get("AirStreamTime") / 100.0));
			
			return true;
		}
		else if (ability instanceof AirSweep) { 
			AirSweep abil = (AirSweep) ability;
			
			if (attribs.containsKey("AirSweepKnockback"))
				abil.setKnockback(abil.getKnockback() + abil.getKnockback() * attribs.get("AirSweepKnockback") / 100.0);
			
			//if (attribs.containsKey("AirSweepProgressCounter"))
			//	abil.setProgressCounter((int) (abil.getProgressCounter() + abil.getProgressCounter() * attribs.get("AirSweepTime") / 100.0));
			
			if (attribs.containsKey("AirSweepRange"))
				abil.setRange(abil.getRange() + abil.getRange() * attribs.get("AirSweepRange") / 100.0);
			
			if (attribs.containsKey("AirSweepSpeed"))
				abil.setSpeed(abil.getSpeed() + abil.getSpeed() * attribs.get("AirSweepSpeed") / 100.0);
			
			return true;
		}
		else if (ability instanceof Twister) { 
			Twister abil = (Twister) ability;
			
			if (attribs.containsKey("TwisterCooldown"))
				abil.setCooldown((long) (abil.getCooldown() + abil.getCooldown() * attribs.get("TwisterCooldown") / 100.0));
			
			return true;
		}
		else if (ability instanceof FlightMultiAbility) { 
			//FlightMultiAbility abil = (FlightMultiAbility) ability;
			
			return true;
		}

		return false;
	}
}
