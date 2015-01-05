package com.projectkorra.ProjectKorraItems;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import com.projectkorra.ProjectKorraItems.attribute.Attribute;
import com.projectkorra.ProjectKorraItems.items.CustomItem;

public class ConfigManager {
	
	public static final String path = ProjectKorraItems.plugin.getDataFolder() + "/config.yml";
	public static String configStr = "";
	public static final String ITEM_PREF = "Item:";
	public static final String DNAME_PREF = "DisplayName:";
	public static final String NAME_PREF = "Name:";
	public static final String LORE_PREF = "Lore:";
	public static final String SHAPED_RECIPE_PREF = "ShapedRecipe:";
	public static final String UNSHAPED_RECIPE_PREF = "UnshapedRecipe:";
	public static final String MATERIAL_PREF = "Material:";
	public static final String DURA_PREF = "Durability:";
	public static final String AMT_PREF = "Amount:";
	public static final String ATTR_PREF = "Stats:";
	public static final String GLOW_PREF = "Glow:";
	public static final String[] PREFIXES = {ITEM_PREF, DNAME_PREF, NAME_PREF, LORE_PREF, 
		SHAPED_RECIPE_PREF, UNSHAPED_RECIPE_PREF, MATERIAL_PREF, DURA_PREF, AMT_PREF, ATTR_PREF,
		GLOW_PREF};
	
	public ProjectKorraItems plugin;
	
	public ConfigManager(ProjectKorraItems plugin) {
		this.plugin = plugin;
		CustomItem.items.clear();
		CustomItem.itemList.clear();
		plugin.saveDefaultConfig();
		readConfig();
		analyzeConfig();
	}
	
	private void readConfig() {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path));
		} catch (FileNotFoundException e) {
			ProjectKorraItems.log.info(Messages.NO_CONFIG);
			return;
		}
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	        configStr = sb.toString();
	    }
	    catch (IOException e) {
	    	ProjectKorraItems.log.info(Messages.BAD_FILE);
			return;
	    }
	    finally {
	        try {
				br.close();
			} catch (IOException e) {
				ProjectKorraItems.log.info(Messages.BAD_FILE);
				return;
			}
	    }
	}
	
	private void analyzeConfig() {
		String[] items = configStr.split("\n");
		CustomItem newItem = null;
		boolean invalid = false;
		for(String s : items) {
			s = s.trim();
			if(s.length() == 0)
				continue;
			if(s.toLowerCase().startsWith(ITEM_PREF.toLowerCase())) {
				if(newItem != null && !invalid) {
					newItem.finalize();
				}
				invalid = false;
				newItem = new CustomItem();
			}
			else {
				boolean prefFound = false;
				for(String prefix : PREFIXES) {
					if(s.toLowerCase().startsWith(prefix.toLowerCase())) {
						prefFound = true;
						String tmp = "";
						try {
							tmp = s.substring(prefix.length(), s.length());
							tmp = tmp.trim();
							if(prefix.equalsIgnoreCase(NAME_PREF))
								newItem.updateName(tmp);
							else if(prefix.equalsIgnoreCase(DNAME_PREF))
								newItem.updateDisplayName(tmp);
							else if(prefix.equalsIgnoreCase(LORE_PREF))
								newItem.updateLore(tmp);
							else if(prefix.equalsIgnoreCase(SHAPED_RECIPE_PREF)) {
								newItem.updateRecipe(tmp);
								newItem.setUnshapedRecipe(false);
							}
							else if(prefix.equalsIgnoreCase(UNSHAPED_RECIPE_PREF)) {
								newItem.updateRecipe(tmp);
								newItem.setUnshapedRecipe(true);
							}
							else if(prefix.equalsIgnoreCase(MATERIAL_PREF))
								newItem.updateMaterial(tmp);
							else if(prefix.equalsIgnoreCase(DURA_PREF))
								newItem.updateDamage(tmp);
							else if(prefix.equalsIgnoreCase(AMT_PREF))
								newItem.updateQuantity(tmp);
							else if(prefix.equalsIgnoreCase(GLOW_PREF))
								newItem.updateGlow(tmp);
						} catch(Exception e) {
							plugin.log.info(Messages.BAD_PREFIX + ": " + prefix);
							invalid = false;
						}
					}
				}
				/*
				 * Check if it is an attribute
				 */
				if(!prefFound) {
					try {
						String prefix = s.substring(0, s.indexOf(":"));
						String valueStr = s.substring(s.indexOf(":") + 1, s.length()).trim();
						valueStr = valueStr.replaceAll("(?i)true", "1");
						valueStr = valueStr.replaceAll("(?i)false", "0");
						String[] commaSplit = valueStr.split(",");
						if(commaSplit.length == 0) {
							plugin.log.info(Messages.MISSING_VALUES + ": " + s);
							invalid = false;
						}
						Attribute att = Attribute.getAttribute(prefix);
						Attribute newAtt = new Attribute(att.getName());
						newAtt.getValues().addAll(Arrays.asList(commaSplit));
						newItem.getAttributes().add(newAtt);
					} catch(Exception e) {
						plugin.log.info(Messages.BAD_PREFIX + ": " + s);
						invalid = false;
					}
				}
				
			}
		}
		if(newItem != null && !invalid) {
			newItem.finalize();
		}
	}
}
