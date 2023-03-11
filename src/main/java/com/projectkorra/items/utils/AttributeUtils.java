package com.projectkorra.items.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.projectkorra.items.Messages;
import com.projectkorra.items.attribute.Action;
import com.projectkorra.items.attribute.Attribute;
import com.projectkorra.items.attribute.AttributeList;
import com.projectkorra.items.attribute.AttributeListener;
import com.projectkorra.items.customs.PKItem;
import com.projectkorra.projectkorra.Element;

public class AttributeUtils {

	/**
	 * Generates a map containing all of the attributes on the players armor and
	 * item in hand. Doesn't return values with multiple commas. Doesn't return
	 * non numerical values.
	 * 
	 * @param player the player to create the effects of
	 * @return a map containing attribute effects
	 */
	public static Map<String, Double> getSimplePlayerAttributeMap(Player player) {
		List<ItemStack> equipment = ItemUtils.getPlayerValidEquipment(player);
		Map<String, Double> attribMap = new ConcurrentHashMap<>();
		List<Attribute> totalAttribs = new ArrayList<>();

		/* Handle any potion style bending effects that the player might have */
		if (AttributeListener.currentBendingEffects.containsKey(player.getName())) {
			Map<String, Attribute> effects = AttributeListener.currentBendingEffects.get(player.getName());
			for (Attribute effect : effects.values()) {
				if (System.currentTimeMillis() - effect.getTime() < effect.getDuration()) {
					totalAttribs.add(effect);
				}
			}
		}

		/* Handle any armor bending effects */
		for (ItemStack istack : equipment) {
			PKItem citem = PKItem.getCustomItem(istack);
			if (citem == null)
				continue;
			totalAttribs.addAll(citem.getAttributes());
		}

		/* Handles the "Air", "Water", "Earth", and "Fire" stats */
		List<Attribute> fullElementAttribs = new ArrayList<>();
		for (Attribute attr : totalAttribs) {
			fullElementAttribs.addAll(getFullElementAttributes(attr));
		}
		totalAttribs.addAll(fullElementAttribs);

		for (Attribute attr : totalAttribs) {
			if (attr.getValues().size() != 1)
				continue;

			double val = 0;
			if (attribMap.containsKey(attr.getName()))
				val = attribMap.get(attr.getName());
			val += attr.getValueAsDouble();
			attribMap.put(attr.getName(), val);
		}
		return attribMap;
	}

	/**
	 * Takes an attribute stat and tries to split its values into a list of
	 * PotionEffects.
	 * 
	 * @param attr the attribute to split
	 * @return a list of the new PotionEffects
	 */
	public static List<PotionEffect> parsePotionEffects(Attribute attr) {
		List<PotionEffect> effects = new ArrayList<>();
		if (attr.getValues() == null)
			return effects;

		for (String val : attr.getValues()) {
			String[] colSplit = val.split(":");
			PotionEffectType type = PotionEffectType.getByName(colSplit[0].trim());
			if (type != null) {
				int strength = Integer.parseInt(colSplit[1].trim());
				double duration = Double.parseDouble(colSplit[2].trim());
				PotionEffect pot = new PotionEffect(type, (int) (duration * 20), strength - 1);
				effects.add(pot);
			}
		}
		return effects;
	}

	/**
	 * This method will handle logging the bad effects for both itself and the
	 * parsePotionEffects. If there was a mistake in the effect it would not
	 * pass the PotionEffectType.getByName check, and it would break on the
	 * parsing.
	 * 
	 * @param attr the attribute containing a list of bending effects as values
	 * @return a list of new attributes representing the bending effects
	 */
	public static List<Attribute> parseBendingEffects(Attribute attr) {
		List<Attribute> effects = new ArrayList<>();
		if (attr.getValues() == null)
			return effects;

		for (String val : attr.getValues()) {
			String[] colSplit = val.split(":");
			if (colSplit.length < 3) {
				Messages.logTimedMessage(Messages.MISSING_EFFECT_VALUES + ": " + val, Messages.LOG_DELAY);
				continue;
			}
			try {
				String name = colSplit[0].trim();

				// Make sure its not a potion
				PotionEffectType type = PotionEffectType.getByName(name);
				if (type != null)
					continue;

				final String modifier = colSplit[1].trim();
				double duration = Double.parseDouble(colSplit[2].trim());
				Attribute newAttr = new Attribute(Attribute.getAttribute(name));
				List<String> vals = new ArrayList<>();
				vals.add(modifier);
				newAttr.setValues(vals);
				newAttr.setDuration(duration * 1000);
				effects.add(newAttr);
			}
			catch (Exception e) {
				Messages.logTimedMessage(Messages.BAD_VALUE + ": " + val, Messages.LOG_DELAY);
			}
		}
		return effects;
	}

	/**
	 * Decreases the charges on all of the player's items by 1, if the charge
	 * type on the item matches the Action type.
	 * 
	 * @param player the player to decrease charges
	 * @param type the action that caused the charge decrease
	 */
	
	public static void decreaseCharges(Player player, Action type) {
		if (player == null)
			return;

		List<ItemStack> istacks = ItemUtils.getPlayerValidEquipment(player);
		for (ItemStack istack : istacks) {
			PKItem citem = PKItem.getCustomItem(istack);
			if (citem == null)
				continue;

			ItemMeta meta = istack.getItemMeta();
			if (meta == null)
				continue;
			List<String> lore = meta.getLore();
			if (lore == null)
				continue;

			boolean displayDestroyMsg = false;
			List<String> newLore = new ArrayList<>();
			for (String line : lore) {
				String newLine = line;
				if (line.startsWith(AttributeList.CHARGES_STR) || (line.startsWith(AttributeList.CLICK_CHARGES_STR) && (type == Action.LEFT_CLICK || type == Action.RIGHT_CLICK || type == null)) || (line.startsWith(AttributeList.SNEAK_CHARGES_STR) && (type == Action.SHIFT || type == null))) {
					String start = line.substring(0, line.indexOf(": "));
					String end = line.substring(line.indexOf(": ") + 1);
					end = end.trim();
					int val = Integer.parseInt(end) - 1;
					if (val == 0)
						displayDestroyMsg = true;
					if (val >= 0)
						newLine = start + ": " + val;
				}
				newLore.add(newLine);
			}
			meta.setLore(newLore);
			istack.setItemMeta(meta);

			// Check if we need to destroy the item
			boolean hasDestroyAttr = false;
			boolean hasIgnoreDestroyMsg = false;
			if (citem.getBooleanAttributeValue("DestroyAfterCharges"))
				hasDestroyAttr = true;
			if (citem.getBooleanAttributeValue("IgnoreDestroyMessage"))
				hasIgnoreDestroyMsg = true;

			boolean hasChargesLeft = true;
			for (String line : newLore) {
				if (line.startsWith(AttributeList.CHARGES_STR) || line.startsWith(AttributeList.CLICK_CHARGES_STR) || line.startsWith(AttributeList.SNEAK_CHARGES_STR)) {
					String tmpStr = line.substring(line.indexOf(": ") + 1).trim();
					int value = Integer.parseInt(tmpStr);
					if (value <= 0)
						hasChargesLeft = false;
					else {
						hasChargesLeft = true;
						break;
					}
				}
			}

			/*
			 * When we go to destroy an item we need to check that there were
			 * not multiple items in that stack. If there were multiple items
			 * then we need to just remove 1 and change the lore back to the
			 * start.
			 */
			if (!hasChargesLeft && !hasIgnoreDestroyMsg && displayDestroyMsg)
				player.sendMessage(citem.getDisplayName() + " " + Messages.ITEM_DESTROYED);
			if (hasDestroyAttr && !hasChargesLeft) {
				if (player.getInventory().contains(istack)) {
					if (istack.getAmount() > 1) {
						istack.setAmount(istack.getAmount() - 1);
						ItemStack newStack = citem.generateItem();
						List<String> aux = null;
						if (newStack.getItemMeta() != null) {
							aux = newStack.getItemMeta().getLore();
						}
						ItemUtils.setLore(istack, aux);
					} else
						player.getInventory().remove(istack);
				} else {
					ItemStack[] armor = player.getInventory().getArmorContents();
					for (int i = 0; i < armor.length; i++) {
						if (armor[i].equals(istack)) {
							if (istack.getAmount() > 1) {
								armor[i].setAmount(armor[i].getAmount() - 1);
								ItemStack newStack = citem.generateItem();
								List<String> aux = null;
								if (newStack.getItemMeta() != null) {
									aux = newStack.getItemMeta().getLore();
								}
								ItemUtils.setLore(armor[i], aux);
							} else
								armor[i] = new ItemStack(Material.AIR);
							break;
						}
					}
					player.getInventory().setArmorContents(armor);
				}
			}
		}
	}

	/**
	 * Given an attribute with a name of "Air", "Water", "Earth", or "Fire" this
	 * method will return a list of all the attributes that correspond to that
	 * specific element. All of the attributes will have a benefit corresponding
	 * to the value of the attribute.
	 * 
	 * @param attr an attribute with an element as a name
	 * @return a list of attributes for that element
	 */
	public static List<Attribute> getFullElementAttributes(Attribute attr) {
		return getFullElementAttributes(attr.getName(), attr.getValueAsDouble());
	}

	/**
	 * Given the String "Air", "Water", "Earth", or "Fire" this method will
	 * return a list of all the attributes that correspond to that specific
	 * element. All of the attributes will have a benefit of value.
	 * 
	 * @param name the name of the element
	 * @param value the amount of benefit to give
	 * @return a list of attributes for that element
	 */
	public static List<Attribute> getFullElementAttributes(String name, double value) {
		List<Attribute> lst = new ArrayList<>();
		if (name == null) {
			return lst;
		}

		Element elem = Element.getElement(name);
		if (elem != null) {
			for (Attribute listAttr : AttributeList.ATTRIBUTES) {
				if (listAttr.getElement() == elem) {
					Attribute newAttr = new Attribute(listAttr);
					newAttr.setValues(value * newAttr.getBenefit());
					lst.add(newAttr);
				}
			}
		}
		return lst;
	}

	/**
	 * Determines if a player is allowed to use a specific CustomItem, depending
	 * on if the CustomItem has the "RequireElement" Attribute. If the item has
	 * the Attribute then it compares the elements to the player's element.
	 * 
	 * @param player the player using the custom item
	 * @param citem a custom item in the player's inventory
	 * @return true if the player can use this custom item
	 */
	public static boolean hasRequiredElement(Player player, PKItem citem) {
		if (player == null || citem == null) {
			return false;
		}
		Attribute requireElem = citem.getAttribute("RequireElement");
		if (requireElem != null) {
			boolean allowed = false;
			for (String val : requireElem.getValues()) {
				try {
					if (ElementUtils.hasElement(player, val)) {
						allowed = true;
						break;
					}
				}
				catch (IllegalArgumentException e) {
					Messages.logTimedMessage(e.getMessage());
				}
			}

			return allowed;
		}
		return true;
	}

	/**
	 * Determines if a player is allowed to use a specific CustomItem, depending
	 * on if the CustomItem has the "RequireWorld" Attribute. If the item has
	 * the Attribute then it compares the player's current world to any of the
	 * item's possible worlds.
	 * 
	 * @param player the player using the custom item
	 * @param citem a custom item in the player's inventory
	 * @return true if the player can use this custom item
	 */
	public static boolean hasRequiredWorld(Player player, PKItem citem) {
		if (player == null || citem == null) {
			return false;
		}
		Attribute require = citem.getAttribute("RequireWorld");
		if (require != null) {
			return require.getValues().contains(player.getWorld().getName());
		}
		return true;
	}

	/**
	 * Determines if a player is allowed to use a specific CustomItem, depending
	 * on if the CustomItem has the "RequirePermission" Attribute. If the item
	 * has the Attribute then it compares all of the CustomItem's permissions to
	 * see if the player has at least one of them.
	 * 
	 * @param player the player using the custom item
	 * @param citem a custom item in the player's inventory
	 * @return true if the player can use this custom item
	 */
	public static boolean hasRequiredPermission(Player player, PKItem citem) {
		if (player == null || citem == null) {
			return false;
		}
		Attribute require = citem.getAttribute("RequirePermission");
		if (require != null) {
			for (String perm : require.getValues()) {
				if (player.hasPermission(perm)) {
					return true;
				}
			}
			return false;
		}
		return true;
	}
}
