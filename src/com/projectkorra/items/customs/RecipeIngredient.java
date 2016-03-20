package com.projectkorra.items.customs;

import com.projectkorra.items.Messages;
import com.projectkorra.items.ProjectKorraItems;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class RecipeIngredient {
	private String customItemName;
	private Material material;
	private int quantity;
	private short damage;
	private boolean isCustomItem;

	private RecipeIngredient(String customItemName, Material material, int quantity, short damage, boolean isCustomItem) {
		this.customItemName = customItemName;
		this.material = material;
		this.quantity = quantity;
		this.damage = damage;
		this.isCustomItem = isCustomItem;
	}

	public RecipeIngredient(Material material, int quantity, short damage) {
		this(null, material, quantity, damage, false);
	}

	public RecipeIngredient(String customItemName, int quantity, short damage) {
		this(customItemName, null, quantity, damage, true);
	}

	public RecipeIngredient(Material material, int quantity) {
		this(material, quantity, (short) 0);
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

	public short getDamage() {
		return damage;
	}

	public void setDamage(short damage) {
		this.damage = damage;
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
			return new ItemStack(material, quantity, damage);
		}
	}

	@Override
	public String toString() {
		return "RecipeIngredient [customItemName=" + customItemName + ", material=" + material + ", quantity=" + quantity + ", damage=" + damage + ", isCustomItem=" + isCustomItem + "]";
	}

}
