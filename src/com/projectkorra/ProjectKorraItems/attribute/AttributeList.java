package com.projectkorra.ProjectKorraItems.attribute;

import java.util.ArrayList;

import org.bukkit.ChatColor;

import com.projectkorra.ProjectKorra.Element;

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
		add(new Attribute("Air", "improves all Air stats"));
		add(new Attribute("Water", "improves all Water stats"));
		add(new Attribute("Earth", "improves all Earth stats"));
		add(new Attribute("Fire", "improves all Fire stats"));
		add(new Attribute("Chi", "improves all Chi stats"));
		
		add(new Attribute("WaterSource", "acts as a temporary water source for water abilities"));
		
		add(new Attribute("Effects", "adds potion and bending effects whenever the player clicks, sneaks, or consumes the item. For example: Effects: JUMP:1:30, BlazeRange:25:30 would add Jump 1 and increase Blaze range by 25% for 30 seconds."));
		add(new Attribute("ClickEffects", "adds potion and bending effects when the player clicks"));
		add(new Attribute("SneakEffects", "adds potion and bending effects when the player sneaks"));
		add(new Attribute("ConsumeEffects", "adds potion and bending effects when this item is consumed"));		
		
		add(new Attribute("Charges", "the number of charges remaining, charges are decreased by both clicking and sneaking, and an item stops working if it runs out of charges"));
		add(new Attribute("ClickCharges", "charges are only decreased by clicking"));
		add(new Attribute("SneakCharges", "charges are only decreased by sneaking"));
		add(new Attribute("DestroyAfterCharges", "the item will be destroyed when the charges run out"));
		add(new Attribute("IgnoreDestroyMessage", "the player will not receive a message that the item was destroyed when the charges run out"));
		
		add(new Attribute("WearOnly", "the item will only work if it is being worn as armor"));
		add(new Attribute("HoldOnly", "the item will only work if it is being held"));
		add(new Attribute("LeatherColor", "gives leather armor a specific color, specified as R,G,B. For example, LeatherColor:255,0,0 would be red armor"));
		
		add(new Attribute("AirGlide", "(true/false) allows an Airbender to glide through the air by sneaking"));
		add(new Attribute("AirGlideSpeed", "modifies the air gliding speed", Element.Air));
		add(new Attribute("AirGlideFallSpeed", "modifies the air gliding fall speed", Element.Air, -1));
		add(new Attribute("AirGlideAutomatic", "(true/false) gliding will start the moment that the user switches to the slot, they don't have to sneak"));

		
		
		// AIR
		add(new Attribute("AirBlastRange", "range", Element.Air));
		add(new Attribute("AirBlastForce", "force", Element.Air));
		
		add(new Attribute("AirSwipeDamage", "damage", Element.Air));
		add(new Attribute("AirSwipeForce", "knockback force", Element.Air));
		add(new Attribute("AirSwipeSpeed", "travel speed", Element.Air));
		add(new Attribute("AirSwipeRange", "range", Element.Air));
		add(new Attribute("AirSwipeMaxCharge", "maximum possible charge damage", Element.Air));
		add(new Attribute("AirSwipeRadius", "radius, Warning! hitting too many monsters at a time with airswipe can overload your server", Element.Air));
		add(new Attribute("AirSwipeArc", "arc width, Warning! hitting too many monsters at a time with airswipe can overload your server", Element.Air));
		add(new Attribute("AirSwipeChargeTime", "maximum charge time, lower is stronger", Element.Air, -1));
		
		add(new Attribute("AirShieldRadius", "radius", Element.Air));
		
		add(new Attribute("AirBubbleRadius", "radius", Element.Air));
		
		add(new Attribute("AirScooterSpeed", "speed", Element.Air));
		
		add(new Attribute("TornadoMaxHeight", "height", Element.Air));
		add(new Attribute("TornadoMaxRadius", "radius", Element.Air));
		add(new Attribute("TornadoRange", "maximum targettable range", Element.Air));
		add(new Attribute("TornadoPlayerPushFactor", "knockback force against players", Element.Air));
		add(new Attribute("TornadoNPCPushFactor", "knockback force gainst mobs", Element.Air));
		
		add(new Attribute("AirSpoutHeight", "height", Element.Air));
		
		add(new Attribute("AirSuctionSpeed", "travel speed", Element.Air));
		add(new Attribute("AirSuctionRange", "range", Element.Air));
		add(new Attribute("AirSuctionForce", "knockback force", Element.Air));
		add(new Attribute("AirSuctionRadius", "affecting radius", Element.Air));
		
		
		// WATER
		add(new Attribute("BloodbendingForce", "throwing force", Element.Water));
		add(new Attribute("BloodbendingRange", "grabbing range", Element.Water));
		add(new Attribute("BloodbendingHoldTime", "maximum hold time, only works if a base hold time is enabled in config", Element.Water));
		add(new Attribute("BloodbendingCooldown", "cooldown, only works if a base cooldown is enabled in config", Element.Water, -1));
		
		add(new Attribute("WaterBubbleRadius", "radius", Element.Water));
		
		add(new Attribute("IceBlastRange", "range", Element.Water));
		add(new Attribute("IceBlastDamage", "damage", Element.Water));
		
		add(new Attribute("IceSpikeDamage", "projectile damage", Element.Water));
		add(new Attribute("IceSpikeRange", "projectile range", Element.Water));
		
		add(new Attribute("IceSpikePillarDamage", "pillar damage", Element.Water));
		add(new Attribute("IceSpikePillarRange", "maximum clickable range", Element.Water));
		
		add(new Attribute("OctopusFormRange", "hit range", Element.Water));
		add(new Attribute("OctopusFormDamage", "damage", Element.Water));
		add(new Attribute("OctopusFormInterval", "animation interval, a lower interval is faster", Element.Water, -1));
		add(new Attribute("OctopusFormKnockback", "knockback force", Element.Water));
		add(new Attribute("OctopusFormRadius", "radius", Element.Water));
		
		add(new Attribute("TorrentDamage", "Torrent Stream damage", Element.Water));
		add(new Attribute("TorrentRange", "Torrent Stream range", Element.Water));
		add(new Attribute("TorrentStreamingDamage", "damage caused by enemies hitting the torrent while it is being circled around the player", Element.Water));
		
		add(new Attribute("TorrentWaveRadius", "radius", Element.Water));
		add(new Attribute("TorrentWaveForce", "knockback force", Element.Water));
		//add(new Attribute("TorrentWaveHeight", "height"));
		
		add(new Attribute("WaterManipulationRange", "range, you must click while the ability is in motion to extend the range", Element.Water));
		add(new Attribute("WaterManipulationForce", "knockback force", Element.Water));
		add(new Attribute("WaterManipulationDamage", "damage", Element.Water));
		add(new Attribute("WaterManipulationCooldown", "cooldown", Element.Water, -1));
		
		add(new Attribute("WaterSpoutHeight", "height", Element.Water));
		
		add(new Attribute("WaterSpoutWaveRadius", "size of the rideable wave", Element.Water));
		add(new Attribute("WaterSpoutWaveRange", "maximum travel range", Element.Water));
		add(new Attribute("WaterSpoutWaveSpeed", "travel speed", Element.Water));
		add(new Attribute("WaterSpoutWaveChargeTime", "initial chargetime", Element.Water, -1));
		add(new Attribute("WaterSpoutWaveFlightTime", "total flight time", Element.Water));
		add(new Attribute("IceWaveDamage", "damage", Element.Water));
		
		add(new Attribute("SurgeWallRadius", "radius", Element.Water));
		add(new Attribute("SurgeWallRange", "the distance that the wall is away from the player", Element.Water));
		
		add(new Attribute("SurgeWaveRadius", "size of the wave", Element.Water));
		add(new Attribute("SurgeWaveKnockback", "knockback force", Element.Water));
		add(new Attribute("SurgeWaveKnockup", "knockup force", Element.Water));
		add(new Attribute("SurgeWaveFreezeSize", "the size of the iceberg that covers the enemy", Element.Water));
		

		// EARTH
		//add(new Attribute("CatapultLength", "length of the catapult earth pillar"));
		//add(new Attribute("CatapultPush", "initial push distance"));
		
		add(new Attribute("EarthArmorStrength", "resistance potion potency", Element.Earth));
		
		add(new Attribute("EarthBlastRange", "range", Element.Earth));
		add(new Attribute("EarthBlastDamage", "damage", Element.Earth));
		add(new Attribute("EarthBlastForce", "knockback force", Element.Earth));
		
		add(new Attribute("EarthTunnelMaxRadius", "radius", Element.Earth));
		add(new Attribute("EarthTunnelRange", "range", Element.Earth));
		add(new Attribute("EarthTunnelInterval", "block remove speed, negative is better", Element.Earth, -1));
		
		//add(new Attribute("ShockwaveRadius", "radius"));
		add(new Attribute("ShockwaveDamage", "damage", Element.Earth));
		add(new Attribute("ShockwaveKnockback", "knockback force", Element.Earth));
		add(new Attribute("ShockwaveChargeTime", "initial charge up time, negative is better", Element.Earth, -1));
		
		add(new Attribute("EarthSmashDamage", "shooting damage", Element.Earth));
		add(new Attribute("EarthSmashGrabRange", "initial spawning distance", Element.Earth));
		add(new Attribute("EarthSmashShootRange", "shooting range", Element.Earth));
		add(new Attribute("EarthSmashChargeTime", "initial charge up time", Element.Earth, -1));
		add(new Attribute("EarthSmashCooldown", "cooldown between uses, the config must have an initial cooldown set for this to work", Element.Earth, -1));
		add(new Attribute("EarthSmashKnockback", "knockback force", Element.Earth));
		add(new Attribute("EarthSmashKnockup", "knockup force", Element.Earth));
		add(new Attribute("EarthSmashFlySpeed", "flying speed, if this is too high the ability will get buggy", Element.Earth));
		add(new Attribute("EarthSmashFlyDuration", "flying duration", Element.Earth));
		
		
		// FIRE
		add(new Attribute("HeatControlCookTime", "the time it takes to cook an item, negative is better"));
		
		add(new Attribute("FireBlastChargedDamage", "damage", Element.Fire));
		add(new Attribute("FireBlastChargedRange", "range", Element.Fire));
		add(new Attribute("FireBlastChargedExplosionRadius", "explosion radius, (power is more important)", Element.Fire));
		add(new Attribute("FireBlastChargedPower", "explosion power and size", Element.Fire));
		add(new Attribute("FireBlastChargedChargeTime", "initial charge up time", Element.Fire, -1));
		
		add(new Attribute("FireBlastSpeed", "travel speed", Element.Fire));
		add(new Attribute("FireBlastForce", "knockback force", Element.Fire));
		add(new Attribute("FireBlastRange", "travel range", Element.Fire));
		add(new Attribute("FireBlastDamage", "damage", Element.Fire));
		
		add(new Attribute("FireJetDuration", "duration", Element.Fire));
		add(new Attribute("FireJetSpeed", "speed", Element.Fire));
		
		add(new Attribute("FireShieldClickDuration", "duration of a click shield", Element.Fire));
		add(new Attribute("FireShieldRadius", "radius of shift shield", Element.Fire));
		add(new Attribute("FireShieldClickRadius", "radius of click shield", Element.Fire));
		
		add(new Attribute("BlazeRange", "range", Element.Fire));
		
		//add(new Attribute("WallOfFireRange", "the distance from the player"));
		//add(new Attribute("WallOfFireHeight", "height"));
		//add(new Attribute("WallOfFireWidth", "width"));
		add(new Attribute("WallOfFireDuration", "duration", Element.Fire));
		add(new Attribute("WallOfFireDamage", "the damage per interval", Element.Fire));
		add(new Attribute("WallOfFireDamageInterval", "the time between damages, negative is better", Element.Fire, -1));
		add(new Attribute("WallOfFireCooldown", "cooldown", Element.Fire, -1));
		
		add(new Attribute("LightningDamage", "damage", Element.Fire));
		add(new Attribute("LightningRange", "range", Element.Fire));
		add(new Attribute("LightningChargeTime", "initial charge up time", Element.Fire, -1));
		add(new Attribute("LightningCooldown", "cooldown", Element.Fire, -1));
		add(new Attribute("LightningSubArcChance", "chance for the arcs to split", Element.Fire));
		add(new Attribute("LightningMaxChainArcs", "maximum amount of times an arc can chain to more enemies", Element.Fire));
		add(new Attribute("LightningChainRange", "seeking range of chain arcs", Element.Fire));
		add(new Attribute("LightningChainArcChance", "chance to chain to more enemies", Element.Fire));
		add(new Attribute("LightningWaterRange", "range of water arcs", Element.Fire));
		add(new Attribute("LightningStunChance", "chance for stun", Element.Fire));
		add(new Attribute("LightningStunDuration", "duration of stun", Element.Fire));
		
		
		// CHI
		add(new Attribute("AcrobatStanceJump", "jump potion potency, you may have to increase this a lot to see a difference", Element.Chi));
		add(new Attribute("AcrobatStanceSpeed", "speed potion potency, you may have to increase this a lot to see a difference", Element.Chi));
		
		add(new Attribute("RapidPunchDamage", "damage per hit", Element.Chi));
		add(new Attribute("RapidPunchHits", "total amount of hits", Element.Chi));
		add(new Attribute("RapidPunchDistance", "distance", Element.Chi));
		add(new Attribute("RapidPunchCooldown", "cooldown, negative is better", Element.Chi, -1));
		
		add(new Attribute("WarriorStanceStrength", "strength potion potency, you may have to increase this a lot to see a difference", Element.Chi));
		add(new Attribute("WarriorStanceResistance", "resistance potion potency, you may have to increase this a lot to see a difference", Element.Chi));
		
	}};
}
