package com.projectkorra.ProjectKorraItems;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.projectkorra.ProjectKorra.BendingPlayer;
import com.projectkorra.ProjectKorra.Methods;

public class ElementUtils {
	/**
	 * Represents the Elements and SubElements
	 * of ProjectKorra.
	 */
	public static enum Element {
		AIR, WATER, EARTH, FIRE, CHI,
		FLIGHT, SPIRITUAL, 
		BLOOD, HEALING, ICE, PLANT, 
		METAL, SAND, LAVA,
		COMBUSTION, LIGHTNING
	}

	/**
	 * Checks if a player has the permissions to bend an
	 * element or subelement.
	 * @param player the player to check
	 * @param element the name of the element
	 * @return true if they can bend that element
	 */
	public static boolean hasElement(Player player, String element) {
		if(player == null)
			return false;
		
		BendingPlayer bplayer = Methods.getBendingPlayer(player.getName());
		if(bplayer == null)
			return false;
		
		if(element.toLowerCase().startsWith(Element.AIR.name().toLowerCase())) {
			return bplayer.hasElement(com.projectkorra.ProjectKorra.Element.Air);
		}
		else if(element.toLowerCase().startsWith(Element.WATER.name().toLowerCase())) {
			return bplayer.hasElement(com.projectkorra.ProjectKorra.Element.Water);
		}
		else if(element.toLowerCase().startsWith(Element.EARTH.name().toLowerCase())) {
			return bplayer.hasElement(com.projectkorra.ProjectKorra.Element.Earth);
		}
		else if(element.toLowerCase().startsWith(Element.FIRE.name().toLowerCase())) {
			return bplayer.hasElement(com.projectkorra.ProjectKorra.Element.Fire);
		}
		else if(element.toLowerCase().startsWith(Element.CHI.name().toLowerCase())) {
			return bplayer.hasElement(com.projectkorra.ProjectKorra.Element.Chi);
		}
		else if(element.toLowerCase().startsWith(Element.FLIGHT.name().toLowerCase())) {
			return Methods.canAirFlight(player);
		}
		else if(element.toLowerCase().startsWith(Element.SPIRITUAL.name().toLowerCase())) {
			return Methods.canUseSpiritualProjection(player);
		}
		else if(element.toLowerCase().startsWith(Element.BLOOD.name().toLowerCase())) {
			return Methods.canBloodbend(player);
		}
		else if(element.toLowerCase().startsWith(Element.HEALING.name().toLowerCase())) {
			return Methods.canWaterHeal(player);
		}
		else if(element.toLowerCase().startsWith(Element.ICE.name().toLowerCase())) {
			return Methods.canIcebend(player);
		}
		else if(element.toLowerCase().startsWith(Element.PLANT.name().toLowerCase())) {
			return Methods.canPlantbend(player);
		}
		else if(element.toLowerCase().startsWith(Element.METAL.name().toLowerCase())) {
			return Methods.canMetalbend(player);
		}
		else if(element.toLowerCase().startsWith(Element.SAND.name().toLowerCase())) {
			return Methods.canSandbend(player);
		}
		else if(element.toLowerCase().startsWith(Element.LAVA.name().toLowerCase())) {
			return Methods.canLavabend(player);
		}
		else if(element.toLowerCase().startsWith(Element.COMBUSTION.name().toLowerCase())) {
			return Methods.canCombustionbend(player);
		}
		else if(element.toLowerCase().startsWith(Element.LIGHTNING.name().toLowerCase())) {
			return Methods.canLightningbend(player);
		}
		
		throw new IllegalArgumentException(element + " is not a valid element");
	}
	
	/**
	 * Determines if a specific material is transparent.
	 * @param mat the material to check
	 * @return true if it is transparent
	 */
	@SuppressWarnings("deprecation")
	public static boolean isTransparent(Material mat) {
		for(int x : Methods.transparentToEarthbending) {
			if(mat.getId() == x)
				return true;
		}
		return false;
	}
}
