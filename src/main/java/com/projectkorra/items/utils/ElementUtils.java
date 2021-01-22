package com.projectkorra.items.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.Element;
import com.projectkorra.projectkorra.Element.SubElement;
import com.projectkorra.projectkorra.ability.ElementalAbility;

public class ElementUtils {

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

		BendingPlayer bPlayer = BendingPlayer.getBendingPlayer(player);
		if (bPlayer == null)
			return false;
		
		Element e = Element.fromString(element);
		if (e instanceof SubElement) {
			SubElement s = (SubElement)e;
			
			return bPlayer.hasSubElement(s);
		}
		
		return bPlayer.hasElement(e);
	}

	/**
	 * Determines if a specific material is transparent.
	 * 
	 * @param mat the material to check
	 * @return true if it is transparent
	 */
	@SuppressWarnings("deprecation")
	public static boolean isTransparent(Material mat) {
		for (Material m : ElementalAbility.getTransparentMaterialSet()) {
			if (mat.getId() == m.getId())
				return true;
		}
		return false;
	}
}
