package com.projectkorra.items;

import com.projectkorra.items.attribute.Attribute;
import com.projectkorra.items.customs.PKItem;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class ConfigManager {
	public static final String DNAME_PREF = "displayname";
	public static final String LORE_PREF = "lore";
	public static final String SHAPED_RECIPE_PREF = "shapedrecipe";
	public static final String UNSHAPED_RECIPE_PREF = "unshapedrecipe";
	public static final String MATERIAL_PREF = "material";
	public static final String AMT_PREF = "amount";
	public static final String ATTR_PREF = "stats";
	public static final String GLOW_PREF = "glow";


	public ConfigManager() {
		ProjectKorraItems.plugin.saveDefaultConfig();
		reloadConfig();
	}

	/**
	 * Reloads the config file.
	 */
	private void reloadConfig() {
		PKItem.items.clear();
		PKItem.itemList.clear();
		try {
			FileConfiguration config = ProjectKorraItems.plugin.getConfig();
			// if (config == null) {
			// 	ProjectKorraItems.log.info(Messages.NO_CONFIG);
			// }
			Set<String> itemNames = config.getKeys(false);
			for (String itemName : itemNames) {
				ConfigurationSection itemSection = config.getConfigurationSection(itemName);
				if (itemSection == null) {
					ProjectKorraItems.log.warning(Messages.BAD_ITEM + " " + itemName + ": " + Messages.BAD_VALUE);
					continue;
				}
				try {
					loadItem(itemName, itemNames, itemSection);
				}
				catch (Exception ex) {
					ProjectKorraItems.log.warning(Messages.BAD_ITEM + " " + itemName);
					ex.printStackTrace();
				}
			}
		}
		catch (Exception ex) {
			ProjectKorraItems.log.severe(Messages.BAD_FILE);
			ex.printStackTrace();
		}
	}

	private void loadItem(@NotNull String itemName, @NotNull Set<String> customItemNames, @NotNull ConfigurationSection itemConfig) {
		PKItem item = new PKItem();
		item.updateName(itemName);
		Set<String> keys = itemConfig.getKeys(false);
		boolean hasRecipe = false;
		for (String key : keys) {
			switch(key.toLowerCase()) {
				case DNAME_PREF:
					item.updateDisplayName(itemConfig.getString(key));
					break;
				case LORE_PREF:
					item.updateLore(itemConfig.getString(key));
					break;
				case SHAPED_RECIPE_PREF:
					if (hasRecipe) {
						ProjectKorraItems.log.warning(Messages.DUPLICATED_RECIPE + " (" + itemName + ")");
						return;
					}
					item.updateRecipe(itemConfig.getString(key), customItemNames);
					item.setUnshapedRecipe(false);
					hasRecipe = true;
					break;
				case UNSHAPED_RECIPE_PREF:
					if (hasRecipe) {
						ProjectKorraItems.log.warning(Messages.DUPLICATED_RECIPE + " (" + itemName + ")");
						return;
					}
					item.updateDisplayName(itemConfig.getString(key));
					item.setUnshapedRecipe(true);
					hasRecipe = true;
					break;
				case MATERIAL_PREF:
					item.updateMaterial(itemConfig.getString(key));
					break;
				case AMT_PREF:
					item.updateQuantity(itemConfig.getInt(key));
					break;
				case ATTR_PREF:
					updateAttributes(item, Objects.requireNonNull(itemConfig.getConfigurationSection(key)));
					break;
				case GLOW_PREF:
					item.updateGlow(itemConfig.getBoolean(key));
					break;
			}
		}
		item.build();
	}

	private void updateAttributes(@NotNull PKItem item, @NotNull ConfigurationSection cs) {
		for (String attributeName : cs.getKeys(false)) {
			Attribute att = Attribute.getAttribute(attributeName);
			if (att == null) {
				ProjectKorraItems.log.info(Messages.BAD_ATTRIBUTE + ": " + attributeName);
				continue;
			}
			Attribute newAtt = new Attribute(att.getName());
			String valueStr = Objects.requireNonNull(cs.getString(attributeName)).trim();
			valueStr = valueStr.replaceAll("(?i)true", "1");
			valueStr = valueStr.replaceAll("(?i)false", "0");
			String[] commaSplit = valueStr.split(",");
			if (commaSplit.length == 0) {
				ProjectKorraItems.log.info(Messages.MISSING_VALUES + ": " + valueStr);
				continue;
			}
			newAtt.getValues().addAll(Arrays.asList(commaSplit));
			item.getAttributes().add(newAtt);
		}
	}

	/**
	 * Gathers the names of all of the custom items within the config file.
	 *
	 * @return a set containing the item names
	 */
	public Set<String> getConfigItemNames() {
		return ProjectKorraItems.plugin.getConfig().getKeys(false);
	}
}
