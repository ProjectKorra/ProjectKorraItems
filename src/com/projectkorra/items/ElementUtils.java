package com.projectkorra.items;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.ability.EarthAbility;

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

		BendingPlayer bplayer = BendingPlayer.getBendingPlayer(player);
		if (bplayer == null)
			return false;

		if (element.toLowerCase().startsWith(Element.AIR.name().toLowerCase())) {
			return bplayer.hasElement(com.projectkorra.projectkorra.Element.AIR);
		} else if (element.toLowerCase().startsWith(Element.WATER.name().toLowerCase())) {
			return bplayer.hasElement(com.projectkorra.projectkorra.Element.WATER);
		} else if (element.toLowerCase().startsWith(Element.EARTH.name().toLowerCase())) {
			return bplayer.hasElement(com.projectkorra.projectkorra.Element.EARTH);
		} else if (element.toLowerCase().startsWith(Element.FIRE.name().toLowerCase())) {
			return bplayer.hasElement(com.projectkorra.projectkorra.Element.FIRE);
		} else if (element.toLowerCase().startsWith(Element.CHI.name().toLowerCase())) {
			return bplayer.hasElement(com.projectkorra.projectkorra.Element.CHI);
		} else if (element.toLowerCase().startsWith(Element.FLIGHT.name().toLowerCase())) {
			return bplayer.canUseFlight();
		} else if (element.toLowerCase().startsWith(Element.SPIRITUAL.name().toLowerCase())) {
			return bplayer.canUseSpiritualProjection();
		} else if (element.toLowerCase().startsWith(Element.BLOOD.name().toLowerCase())) {
			return bplayer.canBloodbend();
		} else if (element.toLowerCase().startsWith(Element.HEALING.name().toLowerCase())) {
			return bplayer.canWaterHeal();
		} else if (element.toLowerCase().startsWith(Element.ICE.name().toLowerCase())) {
			return bplayer.canIcebend();
		} else if (element.toLowerCase().startsWith(Element.PLANT.name().toLowerCase())) {
			return bplayer.canPlantbend();
		} else if (element.toLowerCase().startsWith(Element.METAL.name().toLowerCase())) {
			return bplayer.canMetalbend();
		} else if (element.toLowerCase().startsWith(Element.SAND.name().toLowerCase())) {
			return bplayer.canSandbend();
		} else if (element.toLowerCase().startsWith(Element.LAVA.name().toLowerCase())) {
			return bplayer.canLavabend();
		} else if (element.toLowerCase().startsWith(Element.COMBUSTION.name().toLowerCase())) {
			return bplayer.canCombustionbend();
		} else if (element.toLowerCase().startsWith(Element.LIGHTNING.name().toLowerCase())) {
			return bplayer.canLightningbend();
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
		for (int x : EarthAbility.getTransparentMaterial()) {
			if (mat.getId() == x)
				return true;
		}
		return false;
	}
}
