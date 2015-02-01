package com.projectkorra.ProjectKorraItems.attribute;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.projectkorra.ProjectKorra.Element;
import com.projectkorra.ProjectKorraItems.ItemUtils;
import com.projectkorra.ProjectKorraItems.Messages;
import com.projectkorra.ProjectKorraItems.attribute.AttributeListener.Action;
import com.projectkorra.ProjectKorraItems.items.CustomItem;

public class AttributeUtils {
	
	/**
	 * Generates a map containing all of the attributes on the players
	 * armor and item in hand. Doesn't return values with multiple commas.
	 * Doesn't return non numerical values.	
	 * @param player the player to create the effects of
	 * @return a map containing attribute effects
	 */
	public static ConcurrentHashMap<String, Double> getSimplePlayerAttributeMap(Player player) {
		ArrayList<ItemStack> items = new ArrayList<ItemStack>();
		ConcurrentHashMap<String, Double> map = new ConcurrentHashMap<String, Double>();
		ArrayList<Attribute> totalAttribs = new ArrayList<Attribute>();
		
		/* Handle any potion style bending effects that the player might have */
		if(AttributeListener.currentBendingEffects.containsKey(player.getName())) {
			ConcurrentHashMap<String, Attribute> effects = AttributeListener.currentBendingEffects.get(player.getName());
			for(Attribute effect : effects.values()) {
				if(System.currentTimeMillis() - effect.getTime() < effect.getDuration()) {
					totalAttribs.add(effect);
				}
			}
		}
		
		/* Handle any armor bending effects */
		for(ItemStack istack : player.getInventory().getArmorContents())
			items.add(istack);
		items.add(player.getItemInHand());
		
		for(ItemStack istack : items) {
			CustomItem citem = CustomItem.getCustomItem(istack);
			if(citem == null)
				continue;
			
			/* Check to make sure it has valid charges */
			boolean validCharges = true;
			try {
				for(String line : istack.getItemMeta().getLore()) {
					if(line.startsWith(AttributeList.CHARGES_STR) || line.startsWith(AttributeList.CLICK_CHARGES_STR)
							|| line.startsWith(AttributeList.SNEAK_CHARGES_STR)) {
						String tmpStr = line.substring(line.indexOf(": ") + 1, line.length()).trim();
						int value = Integer.parseInt(tmpStr);
						if(value <= 0)
							validCharges = false;
						else {
							validCharges = true;
							break;
						}
					}
				}
			}
			catch (Exception e) {}
			if(!validCharges)
				continue;
			
			/* Handle the holdOnly/wearOnly stats */
			boolean wearHoldValid = true;
			for(Attribute attr : citem.getAttributes()) {
				if(attr.getBooleanValue("HoldOnly") && !istack.equals(player.getItemInHand())) {
					wearHoldValid = false;
					break;
				}
				else if(attr.getBooleanValue("WearOnly") && istack.equals(player.getItemInHand())) {
					wearHoldValid = false;
					break;
				}
			}
			if(!wearHoldValid)
				continue;
			for(Attribute attr : citem.getAttributes())
				totalAttribs.add(attr);
		}
		
		/* Handle the full element powerup stats */
		for(int i = 0; i < totalAttribs.size(); i++) {
			Attribute attr = totalAttribs.get(i);
			String name = attr.getName();
			if(name.equalsIgnoreCase("Air") || name.equalsIgnoreCase("Water") 
					|| name.equalsIgnoreCase("Earth") || name.equalsIgnoreCase("Fire") 
					|| name.equalsIgnoreCase("Chi")) {
				Element elem = Element.getType(name);
				if(elem != null) {
					double val = 1;
					try {
						val = Double.parseDouble(attr.getValues().get(0));
					}
					catch (NumberFormatException e) {
						continue;
					}
					for(Attribute listAttr : AttributeList.ATTRIBUTES) {
						if(listAttr.getElement() == elem) {
							Attribute newAttr = new Attribute(listAttr);
							newAttr.setValues(val * newAttr.getBenefit());
							totalAttribs.add(newAttr);
						}
					}
				}
			}
		}
			
		for(Attribute attr : totalAttribs) {
			if(attr.getValues().size() != 1)
				continue;
			
			double val = 0;
			if(map.containsKey(attr.getName())) 
				val = map.get(attr.getName());
			try {
				val += Double.parseDouble(attr.getValues().get(0));
				map.put(attr.getName(), val);
			} catch (NumberFormatException e) {
				continue;
			}
		}
		return map;
	}

	public static ArrayList<PotionEffect> parsePotionEffects(Attribute attr) {
		ArrayList<PotionEffect> effects = new ArrayList<PotionEffect>();
		if(attr.getValues() == null)
			return effects;

		for(String val : attr.getValues()) {
			String[] colSplit = val.split(":");
			try {
				PotionEffectType type = PotionEffectType.getByName(colSplit[0].trim());
				int strength = Integer.parseInt(colSplit[1].trim());
				double duration = Double.parseDouble(colSplit[2].trim());
				PotionEffect pot = new PotionEffect(type, (int)(duration * 20), strength - 1);
				effects.add(pot);
			}
			catch (Exception e) {}
		}
		return effects;
	}
	
	/**
	 * This method will handle logging the bad effects for both itself and
	 * the parsePotionEffects. If there was a mistake in the effect it would not pass
	 * the PotionEffectType.getByName check, and it would break on the parsing.
	 */
	public static ArrayList<Attribute> parseBendingEffects(Attribute attr) {
		ArrayList<Attribute> effects = new ArrayList<Attribute>();
		if(attr.getValues() == null)
			return effects;

		for(String val : attr.getValues()) {
			String[] colSplit = val.split(":");
			if(colSplit.length < 3) {
				Messages.logTimedMessage(Messages.MISSING_EFFECT_VALUES + ": " + val, Messages.LOG_DELAY);
				continue;
			}
			try {
				String name = colSplit[0].trim();
				
				//Make sure its not a potion
				PotionEffectType type = PotionEffectType.getByName(name);
				if(type != null)
					continue;
				
				final String modifier = colSplit[1].trim();
				double duration = Double.parseDouble(colSplit[2].trim());
				Attribute newAttr = new Attribute(Attribute.getAttribute(name));
				ArrayList<String> vals = new ArrayList<String>();
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
	
	public static void decreaseCharges(Player player, Action type) {
		if(player == null)
			return;
		
		ArrayList<ItemStack> istacks = ItemUtils.getPlayerItems(player);
		for(ItemStack istack : istacks) {
			CustomItem citem = CustomItem.getCustomItem(istack);
			if(citem == null)
				continue;
			ItemMeta meta = istack.getItemMeta();
			List<String> lore = meta.getLore();
			if(lore == null)
				continue;
			
			boolean displayDestroyMsg = false;
			List<String> newLore = new ArrayList<String>();
			for(String line : lore) {
				String newLine = line;
				try {
					if(line.startsWith(AttributeList.CHARGES_STR) 
							|| (line.startsWith(AttributeList.CLICK_CHARGES_STR) && type == Action.LEFTCLICK)
							|| (line.startsWith(AttributeList.SNEAK_CHARGES_STR) && type == Action.SHIFT)) {
						String start = line.substring(0, line.indexOf(": "));
						String end = line.substring(line.indexOf(": ") + 1, line.length());
						end = end.trim();
						int val = Integer.parseInt(end) - 1;
						if(val == 0)
							displayDestroyMsg = true;
						if(val >= 0)
							newLine = start + ": " + val;
					}
				}
				catch (Exception e) {}
				newLore.add(newLine);
			}
			meta.setLore(newLore);
			istack.setItemMeta(meta);
			
			//Check if we need to destroy the item
			boolean hasDestroyAttr = false;
			boolean hasIgnoreDestroyMsg = false;
			for(Attribute attr : citem.getAttributes()) {
				if(attr.getBooleanValue("DestroyAfterCharges") == true)
					hasDestroyAttr = true;
				else if(attr.getBooleanValue("IgnoreDestroyMessage") == true)
					hasIgnoreDestroyMsg = true;
			}
			
			boolean hasChargesLeft = true;
			for(String line : newLore) {
				try {
					if(line.startsWith(AttributeList.CHARGES_STR) || line.startsWith(AttributeList.CLICK_CHARGES_STR)
							|| line.startsWith(AttributeList.SNEAK_CHARGES_STR)) {
						String tmpStr = line.substring(line.indexOf(": ") + 1, line.length()).trim();
						int value = Integer.parseInt(tmpStr);
						if(value <= 0)
							hasChargesLeft = false;
						else {
							hasChargesLeft = true;
							break;
						}
					}
				}
				catch(Exception e) {}
			}	
			
			/*
			 * When we go to destroy an item we need to check that there
			 * were not multiple items in that stack. If there were
			 * multiple items then we need to just remove 1 and change the lore
			 * back to the start.
			 */
			if(!hasChargesLeft && !hasIgnoreDestroyMsg && displayDestroyMsg)
				player.sendMessage(citem.getDisplayName() + " " + Messages.ITEM_DESTROYED);
			if(hasDestroyAttr && !hasChargesLeft) {
				if(player.getInventory().contains(istack)) {
					if(istack.getAmount() > 1) {
						istack.setAmount(istack.getAmount() - 1);
						ItemStack newStack = citem.generateItem();
						ItemUtils.setLore(istack, newStack.getItemMeta().getLore());
					}
					else
						player.getInventory().remove(istack);
				}
				else {
					ItemStack[] armor = player.getInventory().getArmorContents();
					for(int i = 0; i < armor.length; i++) {
						if(armor[i].equals(istack)) {
							if(istack.getAmount() > 1) {
								armor[i].setAmount(armor[i].getAmount() - 1);
								ItemStack newStack = citem.generateItem();
								ItemUtils.setLore(armor[i], newStack.getItemMeta().getLore());
							}
							else
								armor[i] = new ItemStack(Material.AIR);
							break;
						}
					}
					player.getInventory().setArmorContents(armor);
				}
			}
			
		}
	}

}
