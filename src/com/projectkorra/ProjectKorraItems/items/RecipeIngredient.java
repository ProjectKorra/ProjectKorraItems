package com.projectkorra.ProjectKorraItems.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class RecipeIngredient {
	private Material material;
	private int quantity;
	private short damage;
	
	public RecipeIngredient(Material material, int quantity, short damage) {
		this.material = material;
		this.quantity = quantity;
		this.damage = damage;
	}
	
	public RecipeIngredient(Material material, int quantity) {
		this(material, quantity, (short) 0);
	}
	
	public RecipeIngredient(Material mat) {
		this(mat, 1);
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
	
	public ItemStack getItemStack() {
		return new ItemStack(material, quantity, damage);
	}

	public String toString() {
		return "RecipeIngredient [material=" + material + ", quantity="
				+ quantity + ", damage=" + damage + "]";
	}

	
}
