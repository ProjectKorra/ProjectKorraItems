package com.projectkorra.items.customs;

import com.projectkorra.items.Messages;
import com.projectkorra.items.ProjectKorraItems;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

public class RecipeIngredient {
	private String customItemName;
	private Material material;
	private int quantity;
	private PotionType potionType;
	private boolean isCustomItem;

	private RecipeIngredient(String customItemName, Material material, int quantity, PotionType potionType, boolean isCustomItem) {
		this.customItemName = customItemName;
		this.material = material;
		this.quantity = quantity;
		this.potionType = potionType;
		this.isCustomItem = isCustomItem;
	}

	public RecipeIngredient(Material material, int quantity, PotionType potionType) {
		this(null, material, quantity, potionType, false);
	}

	public RecipeIngredient(String customItemName, int quantity, PotionType potionType) {
		this(customItemName, null, quantity, potionType, true);
	}

	public RecipeIngredient(Material material, int quantity) {
		this(material, quantity, null);
	}

	public RecipeIngredient(Material mat) {
		this(mat, 1);
	}

	public String getCustomItemName() {
		return customItemName;
	}

	public void setCustomItemName(String customItemName) {
		this.customItemName = customItemName;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public PotionType getPotionType() {
		return potionType;
	}

	public void setDamage(PotionType potionType) {
		this.potionType = potionType;
	}

	public boolean isCustomItem() {
		return isCustomItem;
	}

	public void setCustomItem(boolean isCustomItem) {
		this.isCustomItem = isCustomItem;
	}

	public ItemStack getItemStack() {
		if (isCustomItem) {
			PKItem citem = PKItem.getCustomItem(customItemName);
			if (citem != null) {
				ItemStack istack = citem.generateItem();
				istack.setAmount(quantity);
				return istack;
			} else {
				ProjectKorraItems.log.severe(Messages.ERROR_GENERATING_ITEM + ": " + customItemName);
				return new ItemStack(Material.AIR);
			}
		} else {
			ItemStack istack = new ItemStack(material, quantity);
			if (material == Material.POTION && potionType != null) {
				PotionMeta pmeta = (PotionMeta) istack.getItemMeta();
				pmeta.setBasePotionData(new PotionData(potionType));
				istack.setItemMeta(pmeta);
			}
			return istack;
		}
	}

	@Override
	public String toString() {
		return "RecipeIngredient [customItemName=" + customItemName + ", material=" + material + ", quantity=" + quantity + ", potionType=" + potionType + ", isCustomItem=" + isCustomItem + "]";
	}

}
