package com.projectkorra.items.customs;

import com.projectkorra.items.ProjectKorraItems;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;

public class EnchantGlow extends Enchantment {
	private static Enchantment glow;
	private static final NamespacedKey PKI_GLOW_KEY = new NamespacedKey(ProjectKorraItems.plugin, "glow_enchantment");

	public EnchantGlow(NamespacedKey name) {
		super(name);
	}

	@Override
	public boolean canEnchantItem(@NotNull ItemStack item) {
		return true;
	}

	@Override
	public boolean conflictsWith(@NotNull Enchantment other) {
		return false;
	}

	@NotNull
	@Override
	public EnchantmentTarget getItemTarget() {
		return EnchantmentTarget.ALL;
	}

	@Override
	public boolean isTreasure() {
		return false;
	}

	@Override
	public boolean isCursed() {
		return false;
	}

	@Override
	public int getMaxLevel() {
		return 1;
	}

	@NotNull
	@Override
	public String getName() {
		return "Glow";
	}

	@Override
	public int getStartLevel() {
		return 1;
	}

	public static Enchantment getGlow() {
		if (glow != null) {
			return glow;
		}

		try {
			Field f = Enchantment.class.getDeclaredField("acceptingNew");
			f.setAccessible(true);
			f.set(null, true);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		glow = new EnchantGlow(PKI_GLOW_KEY);
		try {
			Enchantment.registerEnchantment(glow);
		}
		catch (IllegalArgumentException ignored) {}

		return glow;
	}

	public static void addGlow(ItemStack item) {
		Enchantment glow = getGlow();

		item.addEnchantment(glow, 1);
	}
}
