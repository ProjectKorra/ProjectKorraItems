package com.projectkorra.ProjectKorraItems.attribute;

import java.util.ArrayList;

import org.bukkit.ChatColor;

public class AttributeList {
	public static double AIR_GLIDE_SPEED = 0.6;
	public static double AIR_GLIDE_FALL = -0.12;
	public static String CHARGES_STR = ChatColor.GOLD + "Charges: ";
	public static String SNEAK_CHARGES_STR = ChatColor.GOLD + "SneakCharges: ";
	public static String CLICK_CHARGES_STR = ChatColor.GOLD + "ClickCharges: ";
	
	public static ArrayList<Attribute> attributes = new ArrayList<Attribute>(){
		private static final long serialVersionUID = 1L;
	{
		
		// MISC
		add(new Attribute("Charges", "the number of charges remaining, charges are decreased by both clicking and sneaking, and an item stops working if it runs out of charges"));
		add(new Attribute("ClickCharges", "charges are only decreased by clicking"));
		add(new Attribute("SneakCharges", "charges are only decreased by sneaking"));
		add(new Attribute("DestroyAfterCharges", "the item will be destroyed when the charges run out"));
		add(new Attribute("IgnoreDestroyMessage", "the player will not receive a message that the item was destroyed when the charges run out"));
		add(new Attribute("WearOnly", "the item will only work if it is being worn as armor"));
		add(new Attribute("HoldOnly", "the item will only work being held"));
		add(new Attribute("LeatherColor", "gives leather armor a specific color, specified as R,G,B. For example, LeatherColor:255,0,0 would be red armor"));
		
		// AIR
		add(new Attribute("AirGlide", "(true/false) allows an Airbender to glide through the air by sneaking"));
		add(new Attribute("AirGlideSpeed", "modifies the air gliding speed"));
		add(new Attribute("AirGlideFallSpeed", "modifies the air gliding fall speed"));
		add(new Attribute("AirGlideAutomatic", "(true/false) gliding will start the moment that the user switches to the slot, they don't have to sneak"));

		add(new Attribute("AirBlastRange", "range"));
		add(new Attribute("AirBlastForce", "force"));
		
		add(new Attribute("AirSwipeDamage", "damage"));
		add(new Attribute("AirSwipeForce", "knockback force"));
		add(new Attribute("AirSwipeSpeed", "travel speed"));
		add(new Attribute("AirSwipeRange", "range"));
		add(new Attribute("AirSwipeMaxCharge", "maximum possible charge damage"));
		add(new Attribute("AirSwipeRadius", "radius, Warning! hitting too many monsters at a time with airswipe can overload your server"));
		add(new Attribute("AirSwipeArc", "arc width, Warning! hitting too many monsters at a time with airswipe can overload your server"));
		add(new Attribute("AirSwipeChargeTime", "maximum charge time, lower is stronger"));
		
		add(new Attribute("AirShieldRadius", "radius"));
		
		add(new Attribute("AirBubbleRadius", "radius"));
		
		add(new Attribute("AirScooterSpeed", "speed"));
		
		add(new Attribute("TornadoMaxHeight", "height"));
		add(new Attribute("TornadoMaxRadius", "radius"));
		add(new Attribute("TornadoRange", "maximum targettable range"));
		add(new Attribute("TornadoPlayerPushFactor", "knockback force against players"));
		add(new Attribute("TornadoNPCPushFactor", "knockback force gainst mobs"));
		
		add(new Attribute("AirSpoutHeight", "height"));
		
		add(new Attribute("AirSuctionSpeed", "travel speed"));
		add(new Attribute("AirSuctionRange", "range"));
		add(new Attribute("AirSuctionForce", "knockback force"));
		add(new Attribute("AirSuctionRadius", "affecting radius"));
		
		
		// WATER
		add(new Attribute("BloodbendingForce", "throwing force"));
		add(new Attribute("BloodbendingRange", "grabbing range"));
		add(new Attribute("BloodbendingHoldTime", "maximum hold time, only works if a base hold time is enabled in config"));
		add(new Attribute("BloodbendingCooldown", "cooldown, only works if a base cooldown is enabled in config"));
		
		add(new Attribute("WaterBubbleRadius", "radius"));
		
		add(new Attribute("IceBlastRange", "range"));
		add(new Attribute("IceBlastDamage", "damage"));
		
		add(new Attribute("IceSpikeDamage", "projectile damage"));
		add(new Attribute("IceSpikeRange", "projectile range"));
		
		add(new Attribute("IceSpikePillarDamage", "pillar damage"));
		add(new Attribute("IceSpikePillarRange", "maximum clickable range"));
		
		add(new Attribute("OctopusFormRange", "hit range"));
		add(new Attribute("OctopusFormDamage", "damage"));
		add(new Attribute("OctopusFormInterval", "animation interval, a lower interval is faster"));
		add(new Attribute("OctopusFormKnockback", "knockback force"));
		add(new Attribute("OctopusFormRadius", "radius"));
		
		add(new Attribute("TorrentDamage", "Torrent Stream damage"));
		add(new Attribute("TorrentRange", "Torrent Stream range"));
		add(new Attribute("TorrentStreamingDamage", "damage caused by enemies hitting the torrent while it is being circled around the player"));
		
		add(new Attribute("TorrentWaveRadius", "radius"));
		add(new Attribute("TorrentWaveForce", "knockback force"));
		//add(new Attribute("TorrentWaveHeight", "height"));
		
		add(new Attribute("WaterManipulationRange", "range, you must click while the ability is in motion to extend the range"));
		add(new Attribute("WaterManipulationForce", "knockback force"));
		add(new Attribute("WaterManipulationDamage", "damage"));
		add(new Attribute("WaterManipulationCooldown", "cooldown"));
		
		add(new Attribute("WaterSpoutHeight", "height"));
		
		add(new Attribute("WaterSpoutWaveRadius", "size of the rideable wave"));
		add(new Attribute("WaterSpoutWaveRange", "maximum travel range"));
		add(new Attribute("WaterSpoutWaveSpeed", "travel speed"));
		add(new Attribute("WaterSpoutWaveChargeTime", "initial chargetime"));
		add(new Attribute("WaterSpoutWaveFlightTime", "total flight time"));
		add(new Attribute("IceWaveDamage", "damage"));
		
		add(new Attribute("SurgeWallRadius", "radius"));
		add(new Attribute("SurgeWallRange", "the distance that the wall is away from the player"));
		
		add(new Attribute("SurgeWaveRadius", "size of the wave"));
		add(new Attribute("SurgeWaveKnockback", "knockback force"));
		add(new Attribute("SurgeWaveKnockup", "knockup force"));
		add(new Attribute("SurgeWaveFreezeSize", "the size of the iceberg that covers the enemy"));
		

		// EARTH
		//add(new Attribute("CatapultLength", "length of the catapult earth pillar"));
		//add(new Attribute("CatapultPush", "initial push distance"));
		
		add(new Attribute("EarthArmorStrength", "resistance potion potency"));
		
		add(new Attribute("EarthBlastRange", "range"));
		add(new Attribute("EarthBlastDamage", "damage"));
		add(new Attribute("EarthBlastForce", "knockback force"));
		
		add(new Attribute("EarthTunnelMaxRadius", "radius"));
		add(new Attribute("EarthTunnelRange", "range"));
		add(new Attribute("EarthTunnelInterval", "block remove speed, negative is better"));
		
		//add(new Attribute("ShockwaveRadius", "radius"));
		add(new Attribute("ShockwaveDamage", "damage"));
		add(new Attribute("ShockwaveKnockback", "knockback force"));
		add(new Attribute("ShockwaveChargeTime", "initial charge up time, negative is better"));
		
		add(new Attribute("EarthSmashDamage", "shooting damage"));
		add(new Attribute("EarthSmashGrabRange", "initial spawning distance"));
		add(new Attribute("EarthSmashShootRange", "shooting range"));
		add(new Attribute("EarthSmashChargeTime", "initial charge up time"));
		add(new Attribute("EarthSmashCooldown", "cooldown between uses, the config must have an initial cooldown set for this to work"));
		add(new Attribute("EarthSmashKnockback", "knockback force"));
		add(new Attribute("EarthSmashKnockup", "knockup force"));
		add(new Attribute("EarthSmashFlySpeed", "flying speed, if this is too high the ability will get buggy"));
		add(new Attribute("EarthSmashFlyDuration", "flying duration"));
		
		
		// FIRE
		add(new Attribute("HeatControlCookTime", "the time it takes to cook an item, negative is better"));
		
		add(new Attribute("FireBlastChargedDamage", "damage"));
		add(new Attribute("FireBlastChargedRange", "range"));
		add(new Attribute("FireBlastChargedExplosionRadius", "explosion radius, (power is more important)"));
		add(new Attribute("FireBlastChargedPower", "explosion power and size"));
		add(new Attribute("FireBlastChargedChargeTime", "initial charge up time"));
		
		add(new Attribute("FireBlastSpeed", "travel speed"));
		add(new Attribute("FireBlastForce", "knockback force"));
		add(new Attribute("FireBlastRange", "travel range"));
		add(new Attribute("FireBlastDamage", "damage"));
		
		add(new Attribute("FireJetDuration", "duration"));
		add(new Attribute("FireJetSpeed", "speed"));
		
		add(new Attribute("FireShieldClickDuration", "duration of a click shield"));
		add(new Attribute("FireShieldRadius", "radius of shift shield"));
		add(new Attribute("FireShieldClickRadius", "radius of click shield"));
		
		add(new Attribute("BlazeRange", "range"));
		
		//add(new Attribute("WallOfFireRange", "the distance from the player"));
		//add(new Attribute("WallOfFireHeight", "height"));
		//add(new Attribute("WallOfFireWidth", "width"));
		add(new Attribute("WallOfFireDuration", "duration"));
		add(new Attribute("WallOfFireDamage", "the damage per interval"));
		add(new Attribute("WallOfFireDamageInterval", "the time between damages, negative is better"));
		add(new Attribute("WallOfFireCooldown", "cooldown"));
		
		add(new Attribute("LightningDamage", "damage"));
		add(new Attribute("LightningRange", "range"));
		add(new Attribute("LightningChargeTime", "initial charge up time"));
		add(new Attribute("LightningCooldown", "cooldown"));
		add(new Attribute("LightningSubArcChance", "chance for the arcs to split"));
		add(new Attribute("LightningMaxChainArcs", "maximum amount of times an arc can chain to more enemies"));
		add(new Attribute("LightningChainRange", "seeking range of chain arcs"));
		add(new Attribute("LightningChainArcChance", "chance to chain to more enemies"));
		add(new Attribute("LightningWaterRange", "range of water arcs"));
		add(new Attribute("LightningStunChance", "chance for stun"));
		add(new Attribute("LightningStunDuration", "duration of stun"));
		
		
		// CHI
		add(new Attribute("AcrobatStanceJump", "jump potion potency, you may have to increase this a lot to see a difference"));
		add(new Attribute("AcrobatStanceSpeed", "speed potion potency, you may have to increase this a lot to see a difference"));
		
		add(new Attribute("RapidPunchDamage", "damage per hit"));
		add(new Attribute("RapidPunchHits", "total amount of hits"));
		add(new Attribute("RapidPunchDistance", "distance"));
		add(new Attribute("RapidPunchCooldown", "cooldown, negative is better"));
		
		add(new Attribute("WarriorStanceStrength", "strength potion potency, you may have to increase this a lot to see a difference"));
		add(new Attribute("WarriorStanceResistance", "resistance potion potency, you may have to increase this a lot to see a difference"));
		
	}};
}
