package com.projectkorra.ProjectKorraItems.attribute;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;

import com.projectkorra.ProjectKorraItems.ItemUtils;
import com.projectkorra.ProjectKorraItems.ProjectKorraItems;
import com.projectkorra.ProjectKorraItems.abilityupdater.AbilityUpdater;
import com.projectkorra.ProjectKorraItems.items.CustomItem;

public class AttributeListener implements Listener {	
	
	/** Represents the possible actions that a player
	 * can do while holding or wearing an item.
	 */
	public static enum Action {
		LEFTCLICK, RIGHTCLICK, SHIFT, CONSUME
	}
		
	/** A map of player names that holds their current bending potion effects. **/
	public static final ConcurrentHashMap<String, ConcurrentHashMap<String, Attribute>> currentBendingEffects = new ConcurrentHashMap<String, ConcurrentHashMap<String, Attribute>>();
	
	/**
	 * When the player sneaks we should attempt to let them Glide.
	 * The Glider class will handle whether or not they can actually glide.
	 * 
	 * Attempt to confirm an ability to decrease charges.
	 * @param event a sneak event
	 */
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerSneak(PlayerToggleSneakEvent event) {
		if(event.isCancelled())
			return;
		
		Player player = event.getPlayer();
		new Glider(player);
		
		// Handles the Charges, and ShiftCharges attribute
		if(!player.isSneaking()) {
			AbilityUpdater.tryToConfirmClick(player, AbilityUpdater.CONFIRM_SHIFT);
			updateOnActionEffects(player, Action.SHIFT);
			handleItemSource(player, "WaterSource", new ItemStack(Material.POTION));
		}
	}
	
	/**
	 * Confirm if an ability was executed via clicking.
	 * Also handle specific stats that related to left clicking.
	 * @param event a player animation event
	 */
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerSwing(PlayerAnimationEvent event) {
		if(event.isCancelled())
			return;
		Player player = event.getPlayer();
		AbilityUpdater.tryToConfirmClick(player, AbilityUpdater.CONFIRM_CLICK);
		updateOnActionEffects(player, Action.LEFTCLICK);
		handleItemSource(player, "WaterSource", new ItemStack(Material.POTION));
	}
		
	/**
	 * Make a call to allow all consume based affects
	 * for the custom item that the player ate.
	 * @param event a consume event
	 */
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerConsume(PlayerItemConsumeEvent event) {
		if(event.isCancelled())
			return;
		updateOnActionEffects(event.getPlayer(), Action.CONSUME);
	}
	
	/**
	 * Because of the "AirGliderAutomatic" stat,
	 * we need to attempt to glide whenever the user switches
	 * their item.
	 * @param event item change event
	 */
	@EventHandler(priority = EventPriority.NORMAL)
	public void onChangeItem(PlayerItemHeldEvent event) {
		if(event.isCancelled())
			return;
		
		Player player = event.getPlayer();
		ConcurrentHashMap<String, Double> attribs = AttributeUtils.getSimplePlayerAttributeMap(player);
		boolean auto = Attribute.getBooleanValue("AirGlideAutomatic", attribs);	
		if(auto)
			new Glider(player, true);
	}
	
	/**
	 * Handles the specific stat "WaterSource" and in the future "MetalSource".
	 * These stats cause specific temporary items to spawn inside of the players
	 * inventory.
	 * @param player the player with the WaterSource stat
	 * @param attrib the name of the stat "WaterSource" or "MetalSource"
	 * @param istack the ItemStack that will temporarily spawn
	 */
	@SuppressWarnings("deprecation")
	public void handleItemSource(Player player, String attrib, ItemStack istack) {
		ConcurrentHashMap<String, Double> attribs = AttributeUtils.getSimplePlayerAttributeMap(player);
		if(attribs.containsKey(attrib) && attribs.get(attrib) == 1) {
			final PlayerInventory inv = player.getInventory();
			int slot = -1;
			for(int i = 9; i < inv.getSize(); i++) {
				if(inv.getItem(i) == null || inv.getItem(i).getType() == Material.AIR) {
					slot = i;	
					break;
				}
			}
			if(slot < 0)
				slot = inv.first(Material.AIR);
			if(slot >= 0) {
				inv.setItem(slot, istack);
				player.updateInventory();
			}
			else
				return;
			
			final int fslot = slot;
			new BukkitRunnable() {
				public void run() {
					inv.setItem(fslot, new ItemStack(Material.AIR));
				}
			}.runTaskLater(ProjectKorraItems.plugin, 10);
		}
	}

	/**
	 * OnActionEffects are PotionEffects and BendingAffects
	 * that get added to the players Attribute map for a 
	 * limited amount of time.
	 * @param player the player receiving the stat modifications
	 * @param type the type of action that caused this to trigger
	 */
	public static void updateOnActionEffects(Player player, Action type) {
		if(player == null)
			return;
		
		ArrayList<ItemStack> istacks = ItemUtils.getPlayerValidEquipment(player);
		String[] validAttribs = null;
		if(type == Action.LEFTCLICK) validAttribs = new String[]{"Effects", "ClickEffects"};
		else if(type == Action.SHIFT) validAttribs = new String[]{"Effects", "SneakEffects"};
		else if(type == Action.CONSUME) validAttribs = new String[]{"Effects", "ConsumeEffects"};
		else
			validAttribs = new String[]{"Effects"};
		
		boolean effectAdded = false;
		for(ItemStack istack : istacks) {
			CustomItem citem = CustomItem.getCustomItem(istack);
			if(citem == null)
				continue;
			
			for(Attribute att : citem.getAttributes())
				for(String allowedEff : validAttribs) 
					if(att.getName().equalsIgnoreCase(allowedEff)) {
						ArrayList<PotionEffect> potEffects = AttributeUtils.parsePotionEffects(att);
						ArrayList<Attribute> bendEffects = AttributeUtils.parseBendingEffects(att);
						
						for(PotionEffect pot : potEffects)
							player.addPotionEffect(pot, true);
						effectAdded = true;
						
						for(Attribute effect : bendEffects) {
							if(!currentBendingEffects.containsKey(player.getName()))
								currentBendingEffects.put(player.getName(), new ConcurrentHashMap<String, Attribute>());
							effect.setTime(System.currentTimeMillis());
							ConcurrentHashMap<String, Attribute> playerEffList = currentBendingEffects.get(player.getName());
							playerEffList.put(effect.getName(), effect);
						}
					}
		}
		if(effectAdded) 
			AttributeUtils.decreaseCharges(player, type);
	}
		

}
