package com.projectkorra.ProjectKorraItems.attribute;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.projectkorra.ProjectKorra.Element;
import com.projectkorra.ProjectKorraItems.Messages;
import com.projectkorra.ProjectKorraItems.items.CustomItem;

public class Attribute 
{	
	private String name;
	private String desc;
	private ArrayList<String> values;
	private Element element;
	private double duration, time;
	private int benefit;
	
	public Attribute(String name, String desc, Element element, int benefit) {
		this.name = name;
		this.desc = desc;
		this.element = element;
		this.benefit = benefit;
		values = new ArrayList<String>();
	}
	
	public Attribute(String name, String desc, Element element) {
		this(name, desc, element, 1);
	}
	
	public Attribute(String name, String desc) {
		this(name, desc, null);
	}
	
	public Attribute(String name) {
		this(name, "");
	}
	
	public Attribute() {
		this("");
	}
	
	public Attribute clone() {
		Attribute attr = new Attribute();
		attr.name = this.name;
		attr.desc = this.desc;
		attr.time = this.time;
		attr.duration = this.duration;
		attr.element = this.element;
		attr.benefit = this.benefit;
		ArrayList<String> newVals = new ArrayList<String>();
		for(String str : this.values)
			newVals.add(new String(str));
		return attr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getTime() {
		return time;
	}

	public Element getElement() {
		return element;
	}

	public void setElement(Element element) {
		this.element = element;
	}

	public int getBenefit() {
		return benefit;
	}

	public void setBenefit(int benefit) {
		this.benefit = benefit;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public ArrayList<String> getValues() {
		return values;
	}

	public void setValues(ArrayList<String> values) {
		this.values = values;
	}
	
	public void setValues(double val) {
		this.values = new ArrayList<String>();
		values.add(val + "");
	}
	
	public static Attribute getAttribute(String name) {
		if(name == null)
			return null;
		for(Attribute att : AttributeList.attributes)
			if(att.getName().equalsIgnoreCase(name))
				return att;
		return null;
	}
	
	public static boolean getBooleanValue(String name, ConcurrentHashMap<String, Double> map) {
		boolean val = map.containsKey(map);
		if(val) {
			try {
				val = map.get(name).intValue() != 0; 
			}
			catch(Exception e) {}
		}
		return val;
	}

	@Override
	public String toString() {
		return "Attribute [name=" + name + ", desc=" + desc + ", values="
				+ values + ", time=" + time + "]";
	}

	public boolean getBooleanValue(String name) {
		if(!this.name.equalsIgnoreCase(name))
			return false;
		try {
			boolean val = Integer.parseInt(this.values.get(0)) != 0;
			return val;
		}
		catch (Exception e) {}
		return false;
	}
	
	public static ConcurrentHashMap<String, Double> getSimplePlayerAttributeMap(Player player) {
		/*
		 * Generates a map containing all of the attributes on the players
		 * armor and item in hand.
		 * Doesn't return values with multiple commas.
		 * Doesn't return non numerical values.
		 */
		ArrayList<ItemStack> items = new ArrayList<ItemStack>();
		ConcurrentHashMap<String, Double> map = new ConcurrentHashMap<String, Double>();
		ArrayList<Attribute> totalAttribs = new ArrayList<Attribute>();
		
		// Handle any potion style bending effects that the player might have
		if(AttributeListener.currentBendingEffects.containsKey(player.getName())) {
			ConcurrentHashMap<String, Attribute> effects = AttributeListener.currentBendingEffects.get(player.getName());
			for(Attribute effect : effects.values()) {
				if(System.currentTimeMillis() - effect.time < effect.duration) {
					totalAttribs.add(effect);
				}
			}
		}
		
		// Handle any armor bending effects
		for(ItemStack istack : player.getInventory().getArmorContents())
			items.add(istack);
		items.add(player.getItemInHand());
		
		for(ItemStack istack : items) {
			CustomItem citem = CustomItem.getCustomItem(istack);
			if(citem == null)
				continue;
			
			// Check to make sure it has valid charges
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
			
			// Handle the holdOnly/wearOnly stats
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
		
		// Handle the full element powerup stats 
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
					for(Attribute listAttr : AttributeList.attributes) {
						if(listAttr.element == elem) {
							Attribute newAttr = listAttr.clone();
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
	
	/*
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
				Attribute newAttr = Attribute.getAttribute(name).clone();
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
}
