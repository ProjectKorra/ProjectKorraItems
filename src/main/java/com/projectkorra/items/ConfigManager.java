package com.projectkorra.items;

import com.projectkorra.items.attribute.Attribute;
import com.projectkorra.items.customs.PKItem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ConfigManager {

	public static final String PATH = ProjectKorraItems.plugin.getDataFolder() + "/config.yml";
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
	public static final String[] PREFIXES = { ITEM_PREF, DNAME_PREF, NAME_PREF, LORE_PREF, SHAPED_RECIPE_PREF, UNSHAPED_RECIPE_PREF, MATERIAL_PREF, DURA_PREF, AMT_PREF, ATTR_PREF, GLOW_PREF };

	public ConfigManager() {
		PKItem.items.clear();
		PKItem.itemList.clear();
		ProjectKorraItems.plugin.saveDefaultConfig();
		String str = readConfig();
		Set<String> customItemNames = getConfigItemNames(str);
		analyzeConfig(str, customItemNames);
	}

	/**
	 * Returns the entire config file as a String.
	 */
	public String readConfig() {
		String configStr = "";
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(PATH));
		}
		catch (FileNotFoundException e) {
			ProjectKorraItems.log.info(Messages.NO_CONFIG);
			return configStr;
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
			return configStr;
		}
		finally {
			try {
				br.close();
			}
			catch (IOException e) {
				ProjectKorraItems.log.info(Messages.BAD_FILE);
				return configStr;
			}
		}
		return configStr;
	}

	/**
	 * Uses a string that represents the configuration file to parse through all
	 * of the items and create instances of CustomItems.
	 * 
	 * @param configStr a string version of the config.yml
	 * @param customItemNames a set containing the names of all the custom items
	 *            that were defined in the config.
	 */
	public void analyzeConfig(String configStr, Set<String> customItemNames) {
		String[] configLines = configStr.split("\n");
		PKItem newItem = null;
		boolean invalid = false;

		for (String line : configLines) {
			line = line.trim();
			if (line.length() == 0)
				continue;
			if (line.toLowerCase().startsWith(ITEM_PREF.toLowerCase())) {
				if (newItem != null && !invalid) {
					newItem.build();
				}
				invalid = false;
				newItem = new PKItem();
			} else {
				boolean prefFound = false;
				for (String prefix : PREFIXES) {
					if (line.toLowerCase().startsWith(prefix.toLowerCase())) {
						prefFound = true;
						String tmp = "";
						try {
							tmp = line.substring(prefix.length(), line.length());
							tmp = tmp.trim();
							if (prefix.equalsIgnoreCase(NAME_PREF))
								newItem.updateName(tmp);
							else if (prefix.equalsIgnoreCase(DNAME_PREF))
								newItem.updateDisplayName(tmp);
							else if (prefix.equalsIgnoreCase(LORE_PREF))
								newItem.updateLore(tmp);
							else if (prefix.equalsIgnoreCase(SHAPED_RECIPE_PREF)) {
								newItem.updateRecipe(tmp, customItemNames);
								newItem.setUnshapedRecipe(false);
							} else if (prefix.equalsIgnoreCase(UNSHAPED_RECIPE_PREF)) {
								newItem.updateRecipe(tmp, customItemNames);
								newItem.setUnshapedRecipe(true);
							} else if (prefix.equalsIgnoreCase(MATERIAL_PREF))
								newItem.updateMaterial(tmp);
							//else if (prefix.equalsIgnoreCase(DURA_PREF))
							//	newItem.updateDamage(tmp);
							else if (prefix.equalsIgnoreCase(AMT_PREF))
								newItem.updateQuantity(tmp);
							else if (prefix.equalsIgnoreCase(GLOW_PREF))
								newItem.updateGlow(tmp);
						}
						catch (Exception e) {
							ProjectKorraItems.log.info(Messages.BAD_PREFIX + ": " + prefix);
							invalid = false;
						}
					}
				}

				/* Check if it is an attribute */
				if (!prefFound) {
					try {
						String prefix = line.substring(0, line.indexOf(":"));
						String valueStr = line.substring(line.indexOf(":") + 1, line.length()).trim();
						valueStr = valueStr.replaceAll("(?i)true", "1");
						valueStr = valueStr.replaceAll("(?i)false", "0");
						String[] commaSplit = valueStr.split(",");
						if (commaSplit.length == 0) {
							ProjectKorraItems.log.info(Messages.MISSING_VALUES + ": " + line);
							invalid = false;
						}
						Attribute att = Attribute.getAttribute(prefix);
						Attribute newAtt = new Attribute(att.getName());
						newAtt.getValues().addAll(Arrays.asList(commaSplit));
						newItem.getAttributes().add(newAtt);
					}
					catch (Exception e) {
						ProjectKorraItems.log.info(Messages.BAD_PREFIX + ": " + line);
						invalid = false;
					}
				}

			}
		}
		if (newItem != null && !invalid) {
			newItem.build();
		}
	}

	/**
	 * Gathers the names of all of the custom items within the config file.
	 * 
	 * @param config a String representation of the configuration file
	 * @return a set containing the item names
	 */
	public Set<String> getConfigItemNames(String config) {
		HashSet<String> names = new HashSet<>();
		String[] lines = config.split("\n");
		String prefix = NAME_PREF;

		for (String line : lines) {
			line = line.trim();
			if (line.toLowerCase().startsWith(prefix.toLowerCase())) {
				String itemName = line.substring(prefix.length(), line.length()).trim();
				names.add(itemName);
			}
		}

		return names;
	}
}
