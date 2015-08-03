package com.projectkorra.ProjectKorraItems;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.projectkorra.airbending.AirMethods;
import com.projectkorra.projectkorra.earthbending.EarthMethods;
import com.projectkorra.projectkorra.firebending.FireMethods;
import com.projectkorra.projectkorra.waterbending.WaterMethods;

public class ElementUtils {
	/**
	 * Represents the Elements and SubElements of ProjectKorra.
	 */
	public static enum Element {
		AIR, WATER, EARTH, FIRE, CHI, FLIGHT, SPIRITUAL, BLOOD, HEALING, ICE, PLANT, METAL, SAND, LAVA, COMBUSTION, LIGHTNING
	}

	/**
	 * Checks if a player has the permissions to bend an element or subelement.
	 * 
	 * @param player the player to check
	 * @param element the name of the element
	 * @return true if they can bend that element
	 */
	public static boolean hasElement(Player player, String element) {
		if (player == null)
			return false;

		BendingPlayer bplayer = GeneralMethods.getBendingPlayer(player.getName());
		if (bplayer == null)
			return false;

		if (element.toLowerCase().startsWith(Element.AIR.name().toLowerCase())) {
			return bplayer.hasElement(com.projectkorra.projectkorra.Element.Air);
		} else if (element.toLowerCase().startsWith(Element.WATER.name().toLowerCase())) {
			return bplayer.hasElement(com.projectkorra.projectkorra.Element.Water);
		} else if (element.toLowerCase().startsWith(Element.EARTH.name().toLowerCase())) {
			return bplayer.hasElement(com.projectkorra.projectkorra.Element.Earth);
		} else if (element.toLowerCase().startsWith(Element.FIRE.name().toLowerCase())) {
			return bplayer.hasElement(com.projectkorra.projectkorra.Element.Fire);
		} else if (element.toLowerCase().startsWith(Element.CHI.name().toLowerCase())) {
			return bplayer.hasElement(com.projectkorra.projectkorra.Element.Chi);
		} else if (element.toLowerCase().startsWith(Element.FLIGHT.name().toLowerCase())) {
			return AirMethods.canAirFlight(player);
		} else if (element.toLowerCase().startsWith(Element.SPIRITUAL.name().toLowerCase())) {
			return AirMethods.canUseSpiritualProjection(player);
		} else if (element.toLowerCase().startsWith(Element.BLOOD.name().toLowerCase())) {
			return WaterMethods.canBloodbend(player);
		} else if (element.toLowerCase().startsWith(Element.HEALING.name().toLowerCase())) {
			return WaterMethods.canWaterHeal(player);
		} else if (element.toLowerCase().startsWith(Element.ICE.name().toLowerCase())) {
			return WaterMethods.canIcebend(player);
		} else if (element.toLowerCase().startsWith(Element.PLANT.name().toLowerCase())) {
			return WaterMethods.canPlantbend(player);
		} else if (element.toLowerCase().startsWith(Element.METAL.name().toLowerCase())) {
			return EarthMethods.canMetalbend(player);
		} else if (element.toLowerCase().startsWith(Element.SAND.name().toLowerCase())) {
			return EarthMethods.canSandbend(player);
		} else if (element.toLowerCase().startsWith(Element.LAVA.name().toLowerCase())) {
			return EarthMethods.canLavabend(player);
		} else if (element.toLowerCase().startsWith(Element.COMBUSTION.name().toLowerCase())) {
			return FireMethods.canCombustionbend(player);
		} else if (element.toLowerCase().startsWith(Element.LIGHTNING.name().toLowerCase())) {
			return FireMethods.canLightningbend(player);
		}

		throw new IllegalArgumentException(element + " is not a valid element");
	}

	/**
	 * Determines if a specific material is transparent.
	 * 
	 * @param mat the material to check
	 * @return true if it is transparent
	 */
	@SuppressWarnings("deprecation")
	public static boolean isTransparent(Material mat) {
		for (int x : EarthMethods.transparentToEarthbending) {
			if (mat.getId() == x)
				return true;
		}
		return false;
	}
}
