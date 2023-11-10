package com.projectkorra.items.attribute;

import com.projectkorra.projectkorra.Element;

import org.bukkit.ChatColor;

import java.util.ArrayList;

public class AttributeList {
	public static final double AIR_GLIDE_SPEED = 0.6;
	public static final double AIR_GLIDE_FALL = -0.12;
	public static final String CHARGES_STR = ChatColor.GOLD + "Charges: ";
	public static final String SNEAK_CHARGES_STR = ChatColor.GOLD + "SneakCharges: ";
	public static final String CLICK_CHARGES_STR = ChatColor.GOLD + "ClickCharges: ";

	public static final ArrayList<Attribute> ATTRIBUTES = new ArrayList<Attribute>() {
		private static final long serialVersionUID = 1L;
		{
			// MISC
			//add(new Attribute("GrapplingHook", "Lets the user shoot out a grappling hook by left clicking. They can then left click to launch themselves toward the destination, or sneak to slowly move to the destination."));
			add(new Attribute("ParticleEffects", "Plays a particle effect around a player whenever they use an ability. " + "Uses the form 'ParticleEffect: NAME:<AMOUNT>:<RADIUS>:<DURATION>:<SPEED>', " + "amount (1 - inf), radius (0 - 100), duration (1 - inf), and speed (0 - 100) are optional. " + "Click http://pastebin.com/Trbh34WG to see the list of available particles. " + "For example: 'ParticleEffects: flame:5:100:10:100"));

			add(new Attribute("AllowFromInventory", "This item will work from the players inventory, even if it is not being held or worn."));
			add(new Attribute("RequireElement", "The user must have one of these elements to use this item. Works with Air, Water, Earth, Fire, Chi, Flight, Spiritual, Blood, Healing, Ice, Plant, Metal, Sand, Lava, Combustion, and Lightning. For example: 'RequireElement: Fire, Air, Chi'"));
			add(new Attribute("RequireWorld", "The user must be located in one of these specific worlds to use this item. For example: 'RequireWorld: bendingworld, bendingarena, bendingrpg'"));
			add(new Attribute("RequirePermission", "The user must have one of these permissions to use this item. For example: 'RequirePermission: bending.admin.*, essentials.command.give'"));

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
			add(new Attribute("AirGlideSpeed", "modifies the air gliding speed", Element.AIR));
			add(new Attribute("AirGlideFallSpeed", "modifies the air gliding fall speed", Element.AIR, -1));
			add(new Attribute("AirGlideAutomatic", "(true/false) gliding will start the moment that the user switches to the slot, they don't have to sneak"));

			// AIR
			// AirBlast
			add(new Attribute("AirBlastCooldown", "cooldown", Element.AIR, -1));
			add(new Attribute("AirBlastDamage", "damage", Element.AIR));
			add(new Attribute("AirBlastParticles", "particles", Element.AIR));
			add(new Attribute("AirBlastPushFactor", "force", Element.AIR));
			add(new Attribute("AirBlastPushFactorForOthers", "force against others", Element.AIR));
			add(new Attribute("AirBlastRadius", "radius", Element.AIR));
			add(new Attribute("AirBlastRange", "range", Element.AIR));
			add(new Attribute("AirBlastSpeed", "speed", Element.AIR));
			add(new Attribute("AirBlastSpeedFactor", "speed factor", Element.AIR));
			
			// AirBurst
			add(new Attribute("AirBurstBlastAnglePhi", "phi angle", Element.AIR));
			add(new Attribute("AirBurstBlastAngleTheta", "theta angle", Element.AIR));
			add(new Attribute("AirBurstChargeTime", "charge time", Element.AIR));
			add(new Attribute("AirBurstDamage", "damage", Element.AIR));
			add(new Attribute("AirBurstFallThreshold", "fall threshold", Element.AIR));
			add(new Attribute("AirBurstPushFactor", "force", Element.AIR));
			
			// AirBubble NOT EXISTS IN NEW VERSIONS OF PK
			//add(new Attribute("AirBubbleRadius", "radius", Element.AIR));

			// AirScooter
			add(new Attribute("AirScooterCooldown", "cooldown", Element.AIR, -1));
			add(new Attribute("AirScooterInterval", "interval", Element.AIR));
			add(new Attribute("AirScooterMaxHeightFromGround", "max height", Element.AIR));
			add(new Attribute("AirScooterRadius", "radius", Element.AIR));
			add(new Attribute("AirScooterSpeed", "speed", Element.AIR));

			// AirShield
			add(new Attribute("AirShieldMaxRadius", "max radius", Element.AIR));
			add(new Attribute("AirShieldParticles", "particles", Element.AIR));
			add(new Attribute("AirShieldRadius", "radius", Element.AIR));
			add(new Attribute("AirShieldSpeed", "speed", Element.AIR));
			add(new Attribute("AirShieldStreams", "streams", Element.AIR));

			// AirSpout
			add(new Attribute("AirSpoutAngle", "angle", Element.AIR));
			add(new Attribute("AirSpoutAnimTime", "anim time", Element.AIR));
			add(new Attribute("AirSpoutCooldown", "cooldown", Element.AIR, -1));
			add(new Attribute("AirSpoutHeight", "height", Element.AIR));
			add(new Attribute("AirSpoutInterval", "interval", Element.AIR));

			// AirSuction
			add(new Attribute("AirSuctionCooldown", "cooldown", Element.AIR, -1));
			add(new Attribute("AirSuctionPushFactor", "knockback force", Element.AIR));
			add(new Attribute("AirSuctionRadius", "affecting radius", Element.AIR));
			add(new Attribute("AirSuctionRange", "range", Element.AIR));
			add(new Attribute("AirSuctionSpeed", "travel speed", Element.AIR));

			// AirSwipe
			add(new Attribute("AirSwipeArc", "arc width, Warning! hitting too many monsters at a time with airswipe can overload your server", Element.AIR));
			add(new Attribute("AirSwipeCooldown", "cooldown", Element.AIR, -1));
			add(new Attribute("AirSwipeDamage", "damage", Element.AIR));
			add(new Attribute("AirSwipeMaxChargeFactor", "maximum possible charge damage", Element.AIR));
			add(new Attribute("AirSwipeMaxChargeTime", "maximum charge time, lower is stronger", Element.AIR, -1));
			add(new Attribute("AirSwipeParticles", "particles", Element.AIR));
			add(new Attribute("AirSwipePushFactor", "knockback force", Element.AIR));
			add(new Attribute("AirSwipeRadius", "radius, Warning! hitting too many monsters at a time with airswipe can overload your server", Element.AIR));
			add(new Attribute("AirSwipeRange", "range", Element.AIR));
			add(new Attribute("AirSwipeSpeed", "travel speed", Element.AIR));
			//add(new Attribute("AirSwipeStepSize", "step size", Element.AIR));

			// Suffocate
			add(new Attribute("SuffocateAnimationSpeed", "animation speed", Element.AIR));
			add(new Attribute("SuffocateBlind", "blind", Element.AIR));
			add(new Attribute("SuffocateBlindDelay", "blind delay", Element.AIR));
			add(new Attribute("SuffocateBlindRepeat", "blind repeat", Element.AIR));
			add(new Attribute("SuffocateChargeTime", "charge time", Element.AIR));
			add(new Attribute("SuffocateConstantAimRadius", "aim radius", Element.AIR));
			add(new Attribute("SuffocateCooldown", "charge time", Element.AIR, -1));
			add(new Attribute("SuffocateDamage", "damage", Element.AIR));
			add(new Attribute("SuffocateDamageDelay", "damage delay", Element.AIR));
			add(new Attribute("SuffocateDamageRepeat", "damage repeat", Element.AIR));
			add(new Attribute("SuffocateRadius", "radius", Element.AIR));
			add(new Attribute("SuffocateRange", "range", Element.AIR));
			add(new Attribute("SuffocateSlow", "slow", Element.AIR));
			add(new Attribute("SuffocateSlowDelay", "slow delay", Element.AIR));
			add(new Attribute("SuffocateSlowRepeat", "slow repeat", Element.AIR));
			
			// Tornado
			add(new Attribute("TornadoMaxHeight", "height", Element.AIR));
			add(new Attribute("TornadoNPCPushFactor", "knockback force gainst mobs", Element.AIR));
			add(new Attribute("TornadoNumberOfStreams", "number of streams", Element.AIR));
			add(new Attribute("TornadoPlayerPushFactor", "knockback force against players", Element.AIR));
			add(new Attribute("TornadoRadius", "radius", Element.AIR));
			add(new Attribute("TornadoRange", "maximum targettable range", Element.AIR));
			add(new Attribute("TornadoSpeed", "speed", Element.AIR));
			
			// AirStream
			add(new Attribute("AirStreamCooldown", "cooldown", Element.AIR, -1));
			add(new Attribute("AirStreamEntityCarryDuration", "duration", Element.AIR));
			add(new Attribute("AirStreamMaxEntityHeight", "max height", Element.AIR));
			add(new Attribute("AirStreamRange", "range", Element.AIR));
			add(new Attribute("AirStreamSpeed", "speed", Element.AIR));
			
			// AirSweep
			add(new Attribute("AirSweepDamage", "damage", Element.AIR));
			add(new Attribute("AirSweepKnockback", "knockback", Element.AIR));
			add(new Attribute("AirSweepRange", "range", Element.AIR));
			add(new Attribute("AirSweepSpeed", "speed", Element.AIR));
			
			// Twister
			add(new Attribute("TwisterCooldown", "cooldown", Element.AIR, -1));

			// WATER
			// OctopusForm
			add(new Attribute("OctopusFormAttackRange", "attack range", Element.WATER));
			add(new Attribute("OctopusFormCooldown", "cooldown", Element.WATER, -1));
			add(new Attribute("OctopusFormDamage", "damage", Element.WATER));
			add(new Attribute("OctopusFormInterval", "animation interval, a lower interval is faster", Element.WATER, -1));
			add(new Attribute("OctopusFormKnockback", "knockback force", Element.WATER));
			add(new Attribute("OctopusFormRadius", "radius", Element.WATER));
			add(new Attribute("OctopusFormRange", "range", Element.WATER));

			// Surge
			add(new Attribute("SurgeWallCooldown", "cooldown", Element.WATER, -1));
			add(new Attribute("SurgeWallInterval", "interval", Element.WATER));
			add(new Attribute("SurgeWallRadius", "radius", Element.WATER));
			add(new Attribute("SurgeWallRange", "the distance that the wall is away from the player", Element.WATER));

			add(new Attribute("SurgeWaveCooldown", "cooldown", Element.WATER, -1));
			add(new Attribute("SurgeWaveInterval", "interval", Element.WATER));
			add(new Attribute("SurgeWaveMaxFreezeRadius", "the size of the iceberg that covers the enemy", Element.WATER));
			add(new Attribute("SurgeWaveMaxRadius", "size of the wave", Element.WATER));
			add(new Attribute("SurgeWaveKnockback", "knockback force", Element.WATER));
			add(new Attribute("SurgeWaveRange", "range", Element.WATER));
			add(new Attribute("SurgeWaveKnockup", "knockup force", Element.WATER));

			// Torrent
			add(new Attribute("TorrentCooldown", "Torrent cooldown", Element.WATER, -1));
			add(new Attribute("TorrentDeflectDamage", "damage caused by enemies hitting the torrent while it is being circled around the player", Element.WATER));
			add(new Attribute("TorrentDamage", "Torrent Stream damage", Element.WATER));
			add(new Attribute("TorrentInterval", "Torrent interval", Element.WATER));
			add(new Attribute("TorrentMaxLayer", "Torrent max layer", Element.WATER));
			add(new Attribute("TorrentMaxUpwardForce", "Torrent max upward force", Element.WATER));
			add(new Attribute("TorrentPush", "Torrent Push", Element.WATER));
			add(new Attribute("TorrentRadius", "Torrent radius", Element.WATER));
			add(new Attribute("TorrentRange", "Torrent Stream range", Element.WATER));
			add(new Attribute("TorrentSelectRange", "Torrent select range", Element.WATER));

			add(new Attribute("TorrentWaveCooldown", "Torrent wave cooldown", Element.WATER, -1));
			add(new Attribute("TorrentWaveGrowSpeed", "Torrent wave grow speed", Element.WATER));
			add(new Attribute("TorrentWaveInterval", "Torrent wave interval", Element.WATER));
			add(new Attribute("TorrentWaveKnockback", "knockback force", Element.WATER));
			add(new Attribute("TorrentWaveMaxHeight", "max height", Element.WATER));
			add(new Attribute("TorrentWaveMaxRadius", "max radius", Element.WATER));
			//add(new Attribute("TorrentWaveHeight", "height"));

			// WaterBubble
			//add(new Attribute("WaterBubbleRadius", "radius", Element.WATER));

			// WaterManipulation
			add(new Attribute("WaterManipulationCollisionRadius", "collision radius", Element.WATER));
			add(new Attribute("WaterManipulationCooldown", "cooldown", Element.WATER, -1));
			add(new Attribute("WaterManipulationDamage", "damage", Element.WATER));
			add(new Attribute("WaterManipulationDeflectRange", "deflect range", Element.WATER));
			add(new Attribute("WaterManipulationDispelRange", "dispel range", Element.WATER));
			add(new Attribute("WaterManipulationInterval", "interval", Element.WATER));
			add(new Attribute("WaterManipulationPushFactor", "knockback force", Element.WATER));
			add(new Attribute("WaterManipulationRange", "range, you must click while the ability is in motion to extend the range", Element.WATER));
			add(new Attribute("WaterManipulationSelectRange", "select range", Element.WATER));
			add(new Attribute("WaterManipulationSpeed", "speed", Element.WATER));

			// WaterSpout
			add(new Attribute("WaterSpoutHeight", "height", Element.WATER));
			add(new Attribute("WaterSpoutInterval", "interval", Element.WATER));

			add(new Attribute("WaterSpoutWaveAnimationSpeed", "initial chargetime", Element.WATER));
			add(new Attribute("WaterSpoutWaveChargeTime", "initial chargetime", Element.WATER, -1));
			add(new Attribute("WaterSpoutWaveCooldown", "cooldown", Element.WATER, -1));
			add(new Attribute("WaterSpoutWaveDamage", "damage", Element.WATER));
			add(new Attribute("WaterSpoutWaveFlightDuration", "total flight duration", Element.WATER));
			add(new Attribute("WaterSpoutWaveRadius", "radius", Element.WATER));
			add(new Attribute("WaterSpoutWaveSelectRange", "range", Element.WATER));
			add(new Attribute("WaterSpoutWaveSpeed", "travel speed", Element.WATER));
			add(new Attribute("WaterSpoutWaveWaveRadius", "size of the rideable wave", Element.WATER));
			
			// WATER - MULTIABILITY
			// WaterArms
			add(new Attribute("WaterArmsCooldown", "cooldown", Element.WATER, -1));
			add(new Attribute("WaterArmsLengthReduction", "lenght reduction", Element.WATER));
			add(new Attribute("WaterArmsLightningDamage", "damage received on lightning (?)", Element.WATER));
			add(new Attribute("WaterArmsMaxIceBlasts", "max ice blasts", Element.WATER));
			add(new Attribute("WaterArmsMaxPunches", "max punches", Element.WATER));
			add(new Attribute("WaterArmsMaxUses", "max uses", Element.WATER));
			add(new Attribute("WaterArmsSourceGrabRange", "source grab range", Element.WATER));

			add(new Attribute("WaterArmsFreezeIceDamage", "ice damage on freeze", Element.WATER));
			add(new Attribute("WaterArmsFreezeIceRange", "ice radius on freeze", Element.WATER));
			add(new Attribute("WaterArmsFreezeUsageCooldown", "freeze cooldown", Element.WATER));

			add(new Attribute("WaterArmsSpearSpearDamage", "spear damage", Element.WATER));
			add(new Attribute("WaterArmsSpearDuration", "spear duration", Element.WATER));
			add(new Attribute("WaterArmsSpearDurationFullMoon", "spear duration on full moon", Element.WATER));
			add(new Attribute("WaterArmsSpearDurationNight", "spear duration at night", Element.WATER));
			add(new Attribute("WaterArmsSpearLength", "spear length", Element.WATER));
			add(new Attribute("WaterArmsSpearRange", "spear range", Element.WATER));
			add(new Attribute("WaterArmsSpearRangeFullMoon", "spear range on full moon", Element.WATER));
			add(new Attribute("WaterArmsSpearRangeNight", "spear range at night", Element.WATER));
			add(new Attribute("WaterArmsSpearSphere", "spear sphere", Element.WATER));
			add(new Attribute("WaterArmsSpearSphereFullMoon", "spear sphere on full moon", Element.WATER));
			add(new Attribute("WaterArmsSpearSphereNight", "spear sphere at night", Element.WATER));
			add(new Attribute("WaterArmsSpearUsageCooldown", "spear cooldown", Element.WATER));

			add(new Attribute("WaterArmsWhipGrabDuration", "whip hold time", Element.WATER));
			add(new Attribute("WaterArmsWhipPullMultiplier", "whip pull multiplier", Element.WATER));
			add(new Attribute("WaterArmsWhipPunchDamage", "whip punch damage", Element.WATER));
			add(new Attribute("WaterArmsWhipPunchLength", "whip punch length", Element.WATER));
			add(new Attribute("WaterArmsWhipPunchLengthFullMoon", "whip punch length on full moon", Element.WATER));
			add(new Attribute("WaterArmsWhipPunchLengthNight", "whip punch length at night", Element.WATER));
			add(new Attribute("WaterArmsWhipUsageCooldown", "whip cooldown", Element.WATER));
			add(new Attribute("WaterArmsWhipWhipLength", "whip length", Element.WATER));
			add(new Attribute("WaterArmsWhipWhipLengthFullMoon", "whip length on full moon", Element.WATER));
			add(new Attribute("WaterArmsWhipWhipLengthNight", "whip length at night", Element.WATER));
			add(new Attribute("WaterArmsWhipWhipLengthWeak", "whip length when weak", Element.WATER));
			add(new Attribute("WaterArmsWhipWhipSpeed", "whip speed", Element.WATER));
			
			// WATER - BLOODBENDING
			// Bloodbending
			add(new Attribute("BloodbendingCooldown", "cooldown, only works if a base cooldown is enabled in config", Element.WATER, -1));
			add(new Attribute("BloodbendingDuration", "maximum hold time, only works if a base hold time is enabled in config", Element.WATER));
			add(new Attribute("BloodbendingKnockback", "throwing force", Element.WATER));
			add(new Attribute("BloodbendingRange", "grabbing range", Element.WATER));

			// WATER - HEALING
			// Not yet amigo
			
			// WATER - ICEBENDING
			// IceBlast
			add(new Attribute("IceBlastCollisionRadius", "collision radius", Element.WATER));
			add(new Attribute("IceBlastCooldown", "cooldown", Element.WATER, -1));
			add(new Attribute("IceBlastDamage", "damage", Element.WATER));
			add(new Attribute("IceBlastDeflectRange", "deflect range", Element.WATER));
			add(new Attribute("IceBlastInterval", "interval", Element.WATER));
			add(new Attribute("IceBlastRange", "range", Element.WATER));

			// IceSpike
			add(new Attribute("IceSpikeCollisionRadius", "collision radius", Element.WATER));
			add(new Attribute("IceSpikeCooldown", "cooldown", Element.WATER, -1));
			add(new Attribute("IceSpikeDamage", "projectile damage", Element.WATER));
			add(new Attribute("IceSpikeDeflectRange", "deflect range", Element.WATER));
			add(new Attribute("IceSpikeInterval", "interval", Element.WATER));
			add(new Attribute("IceSpikeRange", "projectile range", Element.WATER));
			add(new Attribute("IceSpikeSlowCooldown", "slow cooldown", Element.WATER));
			add(new Attribute("IceSpikeSlowDuration", "slow duration", Element.WATER));
			add(new Attribute("IceSpikeSlowPotency", "slow power", Element.WATER));

			add(new Attribute("IceSpikePillarCooldown", "pillar cooldown", Element.WATER, -1));
			add(new Attribute("IceSpikePillarDamage", "pillar damage", Element.WATER));
			add(new Attribute("IceSpikePillarHeight", "pillar height", Element.WATER));
			add(new Attribute("IceSpikePillarInterval", "pillar interval", Element.WATER));
			add(new Attribute("IceSpikePillarRange", "maximum clickable range", Element.WATER));
			add(new Attribute("IceSpikePillarSlowCooldown", "slow cooldown", Element.WATER));
			add(new Attribute("IceSpikePillarSlowDuration", "slow duration", Element.WATER));
			add(new Attribute("IceSpikePillarSlowPower", "slow power", Element.WATER));
			add(new Attribute("IceSpikePillarSpeed", "pillar speed", Element.WATER));

			add(new Attribute("IceSpikePillarFieldCooldown", "pillar field cooldown", Element.WATER, -1));
			add(new Attribute("IceSpikePillarFieldDamage", "pillar field damage", Element.WATER));
			add(new Attribute("IceSpikePillarFieldNumberOfSpikes", "number of spikes in pillar field", Element.WATER));
			add(new Attribute("IceSpikePillarFieldRadius", "pillar field radius", Element.WATER));
			
			// PhaseChange
			add(new Attribute("PhaseChangeDepth", "depth", Element.WATER));
			add(new Attribute("PhaseChangeFreezeControlRadius", "freeze control radius", Element.WATER));
			add(new Attribute("PhaseChangeSourceRange", "source range", Element.WATER));
			
			// WATER - PLANT
			// PlantRegrowth
			add(new Attribute("PlantRegrowthRegrowTime", "regrow time", Element.WATER));
			
			// WATER - COMBOS
			// IceBullet
			add(new Attribute("IceBulletAnimationSpeed", "damage", Element.WATER));
			add(new Attribute("IceBulletCooldown", "cooldown", Element.WATER, -1));
			add(new Attribute("IceBulletDamage", "damage", Element.WATER));
			add(new Attribute("IceBulletMaxShots", "max shots", Element.WATER));
			add(new Attribute("IceBulletRadius", "radius", Element.WATER));
			add(new Attribute("IceBulletRange", "range", Element.WATER));
			add(new Attribute("IceBulletShootTime", "shoot time", Element.WATER));
			add(new Attribute("IceBulletSpeed", "speed", Element.WATER));
			
			// IceWave
			add(new Attribute("IceWaveCooldown", "cooldown", Element.WATER, -1));

			// EARTH
			// Catapult
			//add(new Attribute("CatapultLength", "length of the catapult earth pillar"));
			//add(new Attribute("CatapultPush", "initial push distance"));
			add(new Attribute("CatapultCooldown", "cooldown", Element.EARTH, -1));

			// Collapse
			add(new Attribute("CollapseCooldown", "cooldown", Element.EARTH, -1));
			add(new Attribute("CollapseDistance", "distance", Element.EARTH));
			add(new Attribute("CollapseHeight", "height", Element.EARTH));
			add(new Attribute("CollapseSelectRange", "select range", Element.EARTH));
			add(new Attribute("CollapseSpeed", "speed", Element.EARTH));
			
			add(new Attribute("CollapseWallCooldown", "wall cooldown", Element.EARTH, -1));
			add(new Attribute("CollapseWallHeight", "wall height", Element.EARTH));
			add(new Attribute("CollapseWallRadius", "wall speed", Element.EARTH));
			add(new Attribute("CollapseWallSelectRange", "wall select range", Element.EARTH));
			
			// EarthArmor
			//add(new Attribute("EarthArmorStrength", "resistance potion potency", Element.EARTH));
			add(new Attribute("EarthArmorCooldown", "cooldown", Element.EARTH, -1));
			add(new Attribute("EarthArmorInterval", "interval", Element.EARTH));
			add(new Attribute("EarthArmorMaxGoldHearts", "max number of golden hearts", Element.EARTH));
			add(new Attribute("EarthArmorSelectRange", "select range", Element.EARTH));

			// EarthBlast
			add(new Attribute("EarthBlastCollisionRadius", "collision radius", Element.EARTH));
			add(new Attribute("EarthBlastCooldown", "cooldown", Element.EARTH, -1));
			add(new Attribute("EarthBlastDamage", "damage", Element.EARTH));
			add(new Attribute("EarthBlastDeflectRange", "deflect range", Element.EARTH));
			add(new Attribute("EarthBlastInterval", "interval", Element.EARTH));
			add(new Attribute("EarthBlastPushFactor", "knockback force", Element.EARTH));
			add(new Attribute("EarthBlastRange", "range", Element.EARTH));
			add(new Attribute("EarthBlastSelectRange", "select range", Element.EARTH));
			add(new Attribute("EarthBlastSpeed", "speed", Element.EARTH));

			// EarthSmash
			add(new Attribute("EarthSmashChargeTime", "initial charge up time", Element.EARTH, -1));
			add(new Attribute("EarthSmashCooldown", "cooldown between uses, the config must have an initial cooldown set for this to work", Element.EARTH, -1));
			add(new Attribute("EarthSmashDamage", "shooting damage", Element.EARTH));
			add(new Attribute("EarthSmashDelay", "delay", Element.EARTH));
			add(new Attribute("EarthSmashFlightAnimationInterval", "flight animation interval", Element.EARTH));
			add(new Attribute("EarthSmashFlightDetectionRadius", "flight detection radius", Element.EARTH));
			add(new Attribute("EarthSmashFlightDuration", "flying duration", Element.EARTH));
			add(new Attribute("EarthSmashFlightSpeed", "flying speed, if this is too high the ability will get buggy", Element.EARTH));
			add(new Attribute("EarthSmashGrabDetectionRadius", "grab detection radius", Element.EARTH));
			add(new Attribute("EarthSmashGrabRange", "initial spawning distance", Element.EARTH));
			add(new Attribute("EarthSmashKnockback", "knockback force", Element.EARTH));
			add(new Attribute("EarthSmashKnockup", "knockup force", Element.EARTH));
			add(new Attribute("EarthSmashLiftAnimationInterval", "lift animation interval", Element.EARTH));
			add(new Attribute("EarthSmashMaxBlocksToPassThrough", "max blocks to pass through", Element.EARTH));
			add(new Attribute("EarthSmashSelectRange", "select range", Element.EARTH));
			add(new Attribute("EarthSmashShootAnimationInterval", "shoot animation interval", Element.EARTH));
			add(new Attribute("EarthSmashShootRange", "shooting range", Element.EARTH));

			// EarthTunnel
			add(new Attribute("EarthTunnelAngle", "angle", Element.EARTH));
			add(new Attribute("EarthTunnelDepth", "depth", Element.EARTH));
			add(new Attribute("EarthTunnelInterval", "block remove speed, negative is better", Element.EARTH, -1));
			add(new Attribute("EarthTunnelMaxRadius", "radius", Element.EARTH));
			add(new Attribute("EarthTunnelRadiusIncrement", "radius increment", Element.EARTH));
			add(new Attribute("EarthTunnelRange", "range", Element.EARTH));
			
			// RaiseEarth
			add(new Attribute("RaiseEarthCooldown", "cooldown between uses, the config must have an initial cooldown set for this to work", Element.EARTH, -1));
			add(new Attribute("RaiseEarthDistance", "distance", Element.EARTH));
			add(new Attribute("RaiseEarthHeight", "height", Element.EARTH));
			add(new Attribute("RaiseEarthInterval", "interval", Element.EARTH));
			add(new Attribute("RaiseEarthSelectRange", "select range", Element.EARTH));
			add(new Attribute("RaiseEarthSpeed", "speed", Element.EARTH));

			add(new Attribute("RaiseEarthWallCooldown", "wall cooldown between uses, the config must have an initial cooldown set for this to work", Element.EARTH, -1));
			add(new Attribute("RaiseEarthWallHeight", "wall height", Element.EARTH));
			add(new Attribute("RaiseEarthWallRange", "wall range", Element.EARTH));
			add(new Attribute("RaiseEarthWallSelectRange", "wall select range", Element.EARTH));
			add(new Attribute("RaiseEarthWallSpeed", "wall speed", Element.EARTH));
			
			// Ripple
			add(new Attribute("RippleKnockback", "knockback force", Element.EARTH));
			add(new Attribute("RippleMaxStep", "max step", Element.EARTH));
			add(new Attribute("RippleRange", "range", Element.EARTH));

			// Shockwave
			//add(new Attribute("ShockwaveRadius", "radius"));
			add(new Attribute("ShockwaveAngle", "angle", Element.EARTH));
			add(new Attribute("ShockwaveChargeTime", "initial charge up time, negative is better", Element.EARTH, -1));
			add(new Attribute("ShockwaveCooldown", "cooldown", Element.EARTH, -1));
			add(new Attribute("ShockwaveDamage", "damage", Element.EARTH));
			//add(new Attribute("ShockwaveKnockback", "knockback force", Element.EARTH));
			add(new Attribute("ShockwaveRange", "range", Element.EARTH));
			add(new Attribute("ShockwaveThreshold", "threshold", Element.EARTH));
			
			// Tremorsense
			add(new Attribute("TremorsenseCooldown", "cooldown", Element.EARTH, -1));
			add(new Attribute("TremorsenseLightThreshold", "light threshold", Element.EARTH));
			add(new Attribute("TremorsenseMaxDepth", "max depth", Element.EARTH));
			add(new Attribute("TremorsenseRadius", "radius", Element.EARTH));

			// FIRE
			// Blaze
			//add(new Attribute("BlazeRange", "range", Element.FIRE));
			add(new Attribute("BlazeArcInterval", "interval", Element.FIRE));
			add(new Attribute("BlazeArcRange", "range", Element.FIRE));
			add(new Attribute("BlazeArcSpeed", "speed", Element.FIRE));

			add(new Attribute("BlazeRingAngleIncrement", "interval", Element.FIRE));
			add(new Attribute("BlazeRingCooldown", "speed", Element.FIRE, -1));
			add(new Attribute("BlazeRingRange", "range", Element.FIRE));

			// FireBlast
			add(new Attribute("FireBlastCollisionRadius", "collision radius", Element.FIRE));
			add(new Attribute("FireBlastCooldown", "cooldown", Element.FIRE, -1));
			add(new Attribute("FireBlastDamage", "damage", Element.FIRE));
			add(new Attribute("FireBlastFireTicks", "fire ticks", Element.FIRE));
			add(new Attribute("FireBlastPushFactor", "knockback force", Element.FIRE));
			add(new Attribute("FireBlastRange", "travel range", Element.FIRE));
			add(new Attribute("FireBlastSpeed", "travel speed", Element.FIRE));
			add(new Attribute("FireBlastSpeedFactor", "travel speed factor", Element.FIRE));

			add(new Attribute("FireBlastChargedChargeTime", "initial charge up time", Element.FIRE, -1));
			add(new Attribute("FireBlastChargedCollisionRadius", "collision radius", Element.FIRE));
			add(new Attribute("FireBlastChargedDamageRadius", "damage radius", Element.FIRE));
			add(new Attribute("FireBlastChargedExplosionRadius", "explosion radius, (power is more important)", Element.FIRE));
			add(new Attribute("FireBlastChargedFireTicks", "fire ticks", Element.FIRE));
			add(new Attribute("FireBlastChargedInnerRadius", "inner radius", Element.FIRE));
			//add(new Attribute("FireBlastChargedPower", "explosion power and size", Element.FIRE));
			add(new Attribute("FireBlastChargedInterval", "interval", Element.FIRE));
			add(new Attribute("FireBlastChargedMaxDamage", "damage", Element.FIRE));
			add(new Attribute("FireBlastChargedRange", "range", Element.FIRE));

			// FireBurst
			add(new Attribute("FireBurstAnglePhi", "angle phi", Element.FIRE));
			add(new Attribute("FireBurstAngleTheta", "angle theta", Element.FIRE));
			add(new Attribute("FireBurstChargeTime", "initial charge up time", Element.FIRE, -1));
			add(new Attribute("FireBurstCooldown", "cooldown", Element.FIRE, -1));
			add(new Attribute("FireBurstDamage", "damage", Element.FIRE));
			add(new Attribute("FireBurstParticlesPercentage", "particles percentage", Element.FIRE));
			add(new Attribute("FireBurstRange", "range", Element.FIRE));

			// FireJet
			add(new Attribute("FireJetCooldown", "cooldown", Element.FIRE, -1));
			add(new Attribute("FireJetDuration", "duration", Element.FIRE));
			add(new Attribute("FireJetSpeed", "speed", Element.FIRE));

			// FireShield
			add(new Attribute("FireShieldDiscCooldown", "cooldown of click shield", Element.FIRE, -1));
			add(new Attribute("FireShieldDiscDuration", "duration of a click shield", Element.FIRE));
			add(new Attribute("FireShieldDiscRadius", "radius of click shield", Element.FIRE));
			//add(new Attribute("FireShieldInterval", "interval", Element.FIRE));
			add(new Attribute("FireShieldShieldRadius", "radius of shift shield", Element.FIRE));
			add(new Attribute("FireShieldShieldCooldown", "cooldown of shift shield", Element.FIRE, -1));
			add(new Attribute("FireShieldShieldDuration", "duration of a shift shield", Element.FIRE));

			// HeatControl
			//add(new Attribute("HeatControlCookTime", "the time it takes to cook an item, negative is better"));
			
			// Illumination
			add(new Attribute("IlluminationCooldown", "cooldown", Element.FIRE, -1));
			add(new Attribute("IlluminationRange", "range", Element.FIRE));

			// WallOfFire
			add(new Attribute("WallOfFireCooldown", "cooldown", Element.FIRE, -1));
			add(new Attribute("WallOfFireDamage", "the damage per interval", Element.FIRE));
			add(new Attribute("WallOfFireDamageInterval", "the time between damages, negative is better", Element.FIRE, -1));
			add(new Attribute("WallOfFireDamageTick", "damage tick", Element.FIRE));
			add(new Attribute("WallOfFireDuration", "duration", Element.FIRE));
			add(new Attribute("WallOfFireFireTicks", "fire ticks", Element.FIRE));
			add(new Attribute("WallOfFireHeight", "height"));
			add(new Attribute("WallOfFireInterval", "interval"));
			add(new Attribute("WallOfFireIntervalTick", "interval tick"));
			add(new Attribute("WallOfFireMaxAngle", "max angle"));
			add(new Attribute("WallOfFireRange", "the distance from the player"));
			add(new Attribute("WallOfFireWidth", "width"));

			// FIRE - COMBUSTION
			// Combustion
			add(new Attribute("CombustionCooldown", "cooldown", Element.FIRE, -1));
			add(new Attribute("CombustionDamage", "damage", Element.FIRE));
			add(new Attribute("CombustionExplosivePower", "explosive power", Element.FIRE));
			add(new Attribute("CombustionRadius", "radius", Element.FIRE));
			add(new Attribute("CombustionRange", "range", Element.FIRE));
			add(new Attribute("CombustionSpeed", "speed", Element.FIRE));
			add(new Attribute("CombustionSpeedFactor", "speed factor", Element.FIRE));
			add(new Attribute("CombustionTicks", "ticks", Element.FIRE));

			//FIRE - LIGHTNING
			// Lightning
			add(new Attribute("LightningChainArcChance", "chance to chain to more enemies", Element.FIRE));
			add(new Attribute("LightningChainRange", "seeking range of chain arcs", Element.FIRE));
			add(new Attribute("LightningChargeTime", "initial charge up time", Element.FIRE, -1));
			add(new Attribute("LightningCooldown", "cooldown", Element.FIRE, -1));
			add(new Attribute("LightningDamage", "damage", Element.FIRE));
			add(new Attribute("LightningMaxArcAngle", "maximum arc angle", Element.FIRE));
			add(new Attribute("LightningMaxChainArcs", "maximum amount of times an arc can chain to more enemies", Element.FIRE));
			add(new Attribute("LightningRange", "range", Element.FIRE));
			add(new Attribute("LightningStunChance", "chance for stun", Element.FIRE));
			add(new Attribute("LightningStunDuration", "duration of stun", Element.FIRE));
			add(new Attribute("LightningSubArcChance", "chance for the arcs to split", Element.FIRE));
			add(new Attribute("LightningWaterArcRange", "range of water arcs", Element.FIRE));
			add(new Attribute("LightningWaterArcs", "number of water arcs", Element.FIRE));
			
			// FIRE - COMBOS
			// Nope

			// CHI
			// AcrobatStance
			add(new Attribute("AcrobatStanceJump", "jump potion potency, you may have to increase this a lot to see a difference", Element.CHI));
			add(new Attribute("AcrobatStanceSpeed", "speed potion potency, you may have to increase this a lot to see a difference", Element.CHI));
			
			// HighJump
			add(new Attribute("HighJumpCooldown", "cooldown, negative is better", Element.CHI, -1));
			add(new Attribute("HighJumpHeight", "height", Element.CHI));
			
			// QuickStrike
			add(new Attribute("QuickStrikeBlockChance", "block chance", Element.CHI));
			add(new Attribute("QuickStrikeDamage", "damage", Element.CHI));

			// RapidPunch
			add(new Attribute("RapidPunchDamage", "damage per hit", Element.CHI));
			add(new Attribute("RapidPunchHits", "total amount of hits", Element.CHI));
			//add(new Attribute("RapidPunchDistance", "distance", Element.CHI));
			add(new Attribute("RapidPunchCooldown", "cooldown, negative is better", Element.CHI, -1));
			
			// Smokescreen
			add(new Attribute("SmokescreenCooldown", "cooldown, negative is better", Element.CHI, -1));
			add(new Attribute("SmokescreenDuration", "duration", Element.CHI));
			add(new Attribute("SmokescreenRadius", "radius", Element.CHI));

			// SwiftKick
			add(new Attribute("SwiftKickBlockChance", "block chance", Element.CHI));
			add(new Attribute("SwiftKickCooldown", "cooldown, negative is better", Element.CHI, -1));
			add(new Attribute("SwiftKickDamage", "damage", Element.CHI));
			
			// WariorStance
			add(new Attribute("WarriorStanceStrength", "strength potion potency, you may have to increase this a lot to see a difference", Element.CHI));
			add(new Attribute("WarriorStanceResistance", "resistance potion potency, you may have to increase this a lot to see a difference", Element.CHI));
			
			// CHI - COMBOS
			// Immobilize
			add(new Attribute("ImmobilizeCooldown", "cooldown, negative is better", Element.CHI, -1));
			add(new Attribute("ImmobilizeDuration", "duration", Element.CHI));

		}
	};
}
