package com.projectkorra.items;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.scheduler.BukkitRunnable;

import com.projectkorra.items.attribute.Action;
import com.projectkorra.items.attribute.Attribute;
import com.projectkorra.items.customs.PKIDisplay;
import com.projectkorra.items.customs.PKIEquip;
import com.projectkorra.items.customs.PKItem;
import com.projectkorra.items.customs.RecipeIngredient;
import com.projectkorra.items.items.Glider;
import com.projectkorra.items.utils.AttributeUtils;
import com.projectkorra.items.utils.ItemUtils;

public class PKIListener implements Listener {

	/**
	 * When the player places an item into a crafting table we need to check if the current crafting
	 * table contains the correct ingredients for a custom item. If they have placed the items
	 * correctly then we will place a custom item into the resulting inventory slot.
	 * 
	 * @param event the click event
	 */
	@EventHandler(priority = EventPriority.NORMAL)
	public void onCraftingPlace(InventoryClickEvent event) {
		if (event.isCancelled() || event.getInventory().getType() != InventoryType.WORKBENCH)
			return;

		final HumanEntity humEnt = event.getWhoClicked();
		final InventoryClickEvent fevent = event;
		if (!(humEnt instanceof Player))
			return;

		new BukkitRunnable() {
			@SuppressWarnings("unchecked")
			public void run() {
				// Player player = (Player) humEnt;
				ItemStack[] tempInvItems = fevent.getInventory().getContents();
				ArrayList<ItemStack> originalInvItems = new ArrayList<ItemStack>();

				for (ItemStack istack : tempInvItems)
					originalInvItems.add(istack);

				/*
				 * Remove the resultant slot because we don't wish to consider it for the purpose of
				 * calculating the recipe.
				 */
				originalInvItems.remove(0);
				ArrayList<ItemStack> invItemsClone = (ArrayList<ItemStack>) originalInvItems.clone();

				for (PKItem citem : PKItem.items.values()) {
					if (citem.getRecipe().size() == 0)
						continue;

					ArrayList<RecipeIngredient> recipeClone = (ArrayList<RecipeIngredient>) citem.getRecipe().clone();
					boolean validShape = true;
					int maxQuantity = Integer.MAX_VALUE;
					invItemsClone = (ArrayList<ItemStack>) originalInvItems.clone();

					for (int i = 0; i < recipeClone.size(); i++) {
						RecipeIngredient ing = recipeClone.get(i);
						for (int j = 0; j < invItemsClone.size(); j++) {

							/*
							 * The index of a perfectly shaped recipe will always stay at 0 because
							 * the ingredients are being removed as we attempt to cycle through the
							 * list.
							 */
							if (i > 0 || j > 0)
								validShape = false;

							ItemStack item = invItemsClone.get(j);
							PKItem citemInSlot = PKItem.getCustomItem(item);
							if (ing.getMaterial() == Material.AIR && item.getType() == Material.AIR) {
								recipeClone.remove(i);
								invItemsClone.remove(j);
								i--;
								j--;
								break;
							} else if (item.getAmount() >= ing.getQuantity()) {
								// We don't actually care to check the damage value if the
								// ingredient is a custom item
								if (!ing.isCustomItem() && ing.getMaterial() == item.getType()
										&& ing.getMaterial() == Material.POTION
										&& ing.getPotionType() == ((PotionMeta) item.getItemMeta()).getBasePotionData().getType()
										|| (citemInSlot != null && citemInSlot.getName().equals(ing.getCustomItemName()))) {

									// Calculate the least possible maximum quantity that can result
									// from
									// the amount of ingredients that were placed.
									if (item.getAmount() / ing.getQuantity() < maxQuantity) {
										maxQuantity = item.getAmount() / ing.getQuantity();
									}

									recipeClone.remove(i);
									invItemsClone.remove(j);
									i--;
									j--;
									break;
								}
							}
						}
					}

					/*
					 * Once there are no more recipes left in this cloned list then we know that the
					 * recipe was correct, we can now determine whether or not we let the user
					 * receive the item.
					 */
					if (recipeClone.size() == 0 && invItemsClone.size() == 0) {
						if (citem.isUnshapedRecipe() || validShape) {
							ItemStack newItem = citem.generateItem();
							newItem.setAmount(maxQuantity * newItem.getAmount());
							fevent.getInventory().setItem(0, newItem);
							// player.updateInventory();
							return;
						}
					}

				}
			}
		}.runTaskLater(ProjectKorraItems.plugin, 1);
	}

	/**
	 * Once the player attempts to grab a newly formed custom item, we need to place the item in
	 * their hand and then calculate the remaining amount of items that will stay inside of the
	 * crafting table.
	 * 
	 * @param event the click event
	 */
	@SuppressWarnings("unchecked")
	@EventHandler(priority = EventPriority.NORMAL)
	public void onGrabResultItem(InventoryClickEvent event) {
		if (event.isCancelled() || event.getSlotType() != SlotType.RESULT || event.getSlot() != 0
				|| event.getInventory().getType() != InventoryType.WORKBENCH)
			return;

		HumanEntity humEnt = event.getWhoClicked();
		if (!(humEnt instanceof Player))
			return;

		Player player = (Player) humEnt;
		ItemStack curItem = event.getCurrentItem();
		if (curItem == null)
			return;

		PKItem citem = PKItem.getCustomItem(curItem);
		if (citem == null)
			return;

		int amountCreated = curItem.getAmount() / citem.getQuantity();

		/*
		 * Once a player clicks the resultant item we need to calculate the amount of items that
		 * will remain in the crafting bench.
		 */
		ItemStack[] tempInvItems = event.getInventory().getContents();
		ArrayList<RecipeIngredient> recipeClone = (ArrayList<RecipeIngredient>) citem.getRecipe().clone();
		ArrayList<ItemStack> invItems = new ArrayList<ItemStack>();

		for (ItemStack istack : tempInvItems)
			invItems.add(istack);

		invItems.set(0, new ItemStack(Material.AIR));

		while (recipeClone.size() > 0) {
			RecipeIngredient ing = recipeClone.get(0);
			int ingQuantity = ing.getQuantity() * amountCreated;

			if (ing.getMaterial() != Material.AIR) {
				for (int i = 0; i < invItems.size(); i++) {
					ItemStack istack = invItems.get(i);
					PKItem istackCustomItem = PKItem.getCustomItem(istack);

					if (!ing.isCustomItem() && ing.getMaterial() == Material.POTION
							&& ing.getPotionType() != ((PotionMeta) istack.getItemMeta()).getBasePotionData().getType()) {
						continue;
					} else if (!ing.isCustomItem() && istack.getType() != ing.getMaterial()) {
						continue;
					} else if (ing.isCustomItem() && istackCustomItem == null) {
						continue;
					} else if (ing.isCustomItem() && !istackCustomItem.getName().equals(ing.getCustomItemName())) {
						continue;
					}

					int itemQuantity = istack.getAmount();
					if (ingQuantity > itemQuantity) {
						invItems.set(i, new ItemStack(Material.AIR));
						ingQuantity -= itemQuantity;
					} else if (ingQuantity == itemQuantity) {
						invItems.set(i, new ItemStack(Material.AIR));
						break;
					} else if (ingQuantity < itemQuantity) {
						istack.setAmount(itemQuantity - ingQuantity);
						break;
					}
				}
			}
			recipeClone.remove(0);
		}

		event.getInventory().clear();
		player.setItemOnCursor(curItem);
		final ItemStack[] finalItems = invItems.toArray(new ItemStack[invItems.size()]);
		final InventoryClickEvent fevent = event;
		//final Player fplayer = player;

		/*
		 * Update the players inventory on a short delay so that they see the newly calculated
		 * changes to each ItemStack in the inventory.
		 */
		new BukkitRunnable() {
			public void run() {
				fevent.getInventory().setContents(finalItems);
				// fplayer.updateInventory(); // Not needed anymore
			}
		}.runTaskLater(ProjectKorraItems.plugin, 1);
	}

	/**
	 * When the player clicks in an inventory it is possible that they are viewing an inventory used
	 * to display the custom items with /bi items. If they click in one of these inventories then we
	 * need to handle stuff differently.
	 * 
	 * @param event the click event
	 */
	@EventHandler(priority = EventPriority.NORMAL)
	public void onClickInDisplayInv(InventoryClickEvent event) {
		if (event.isCancelled())
			return;

		HumanEntity humEnt = event.getWhoClicked();
		if (!(humEnt instanceof Player))
			return;

		Inventory inv = event.getInventory();
		Player player = (Player) humEnt;

		for (PKIDisplay disp : PKIDisplay.displays.values())
			if (disp.getInventory().equals(inv))
				event.setCancelled(true);

		if (!PKIDisplay.displays.containsKey(player))
			return;

		PKIDisplay disp = PKIDisplay.displays.get(player);
		boolean hasPerm = player.hasPermission("bendingitems.command.give");

		/*
		 * If they have permissions and right click, they are probably holding an item
		 */
		if (hasPerm && event.getClick() == ClickType.RIGHT && player.getItemOnCursor().getType() != Material.AIR
				&& !disp.isShowStats()) {
			event.setCancelled(false);
			return;
		}

		/*
		 * If the player clicks a prev or next button then we need to regenerate the inventory using
		 * items from the next or previous page.
		 */
		ItemStack curItem = event.getCurrentItem();
		if (curItem == null)
			return;
		else if (curItem.equals(PKIDisplay.NEXT_BUTTON)) {
			player.closeInventory();
			disp.setPage(disp.getPage() + 1);
			disp.createInventory();
			return;
		} else if (curItem.equals(PKIDisplay.PREV_BUTTON)) {
			player.closeInventory();
			if (!disp.isShowingRecipeInv()) {
				disp.setPage(disp.getPage() - 1);
				disp.createInventory();
			} else {
				// Just recreate the old inventory page
				disp.createInventory();
			}
			return;
		}

		PKItem citem = PKItem.getCustomItem(curItem);
		if (citem == null)
			return;

		/*
		 * If they right clicked then they might be able to grab the item or place one down
		 */
		if (hasPerm && event.getClick() == ClickType.RIGHT && !disp.isShowStats()) {
			player.setItemOnCursor(citem.generateItem());
			return;
		}

		/*
		 * Create the new Recipe inventory
		 */
		String displayName = citem.getDisplayName();
		if (displayName.length() > 32)
			displayName = displayName.substring(0, 32);
		Inventory recInv = Bukkit.createInventory(null, 27, displayName);
		for (int i = 0; i < citem.getRecipe().size(); i++) {
			RecipeIngredient ing = citem.getRecipe().get(i);
			int pos = ((i % 3) + 3) + ((i / 3) * 9);
			recInv.setItem(pos, ing.getItemStack());
		}
		recInv.setItem(18, PKIDisplay.PREV_BUTTON);

		/*
		 * By closing the inventory onCloseDisplayInv is going to remove this ItemDisplay from the
		 * map. Since we are going to reuse this ItemDisplay, we need to add it back;
		 */
		PKIDisplay.displays.put(player, disp);
		disp.setInventory(recInv);
		disp.setShowingRecipeInv(true);
		player.openInventory(recInv);
	}

	/**
	 * When the inventory closes we need to remove this instance from the ItemDisplay.displays map.
	 * 
	 * @param event the close event
	 */
	@EventHandler(priority = EventPriority.NORMAL)
	public void onCloseDisplayInv(InventoryCloseEvent event) {
		HumanEntity humEnt = event.getPlayer();
		if (!(humEnt instanceof Player))
			return;

		Inventory inv = event.getInventory();
		Player player = (Player) humEnt;
		for (PKIDisplay disp : PKIDisplay.displays.values())
			if (disp.getInventory().equals(inv)) {
				PKIDisplay.displays.remove(player);
				return;
			}
	}

	/**
	 * We can't allow users to place custom items into an anvil, if they rename the item it may
	 * cause it to break.
	 * 
	 * @param event the click event
	 */
	@EventHandler(priority = EventPriority.NORMAL)
	public void onAnvilItemPlace(InventoryClickEvent event) {
		if (event.isCancelled() || event.getInventory().getType() != InventoryType.ANVIL)
			return;
		HumanEntity humEnt = event.getWhoClicked();
		if (!(humEnt instanceof Player))
			return;
		Player player = (Player) humEnt;
		ItemStack cursorItem = event.getCurrentItem();
		if (cursorItem == null)
			return;

		PKItem citem = PKItem.getCustomItem(cursorItem);
		if (citem == null)
			return;
		else {
			event.setCancelled(true);
			player.sendMessage(Messages.NO_ANVIL);
		}
	}

	/**
	 * When a player changes their currently held item, we need to check if the item was being
	 * tracked by an ItemEquip, so we will call ItemEquip.updatePlayerSlot
	 * 
	 * @param event the item change event
	 */
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerItemChange(PlayerItemHeldEvent event) {
		PKIEquip.updatePlayerSlot(event.getPlayer(), event.getPreviousSlot(), event.getNewSlot());
	}

	/**
	 * When the player sneaks we should attempt to let them Glide. The Glider class will handle
	 * whether or not they can actually glide. Attempt to confirm an ability to decrease charges.
	 * 
	 * @param event a sneak event
	 */
	/* DUPLICATED IN AttributeListener
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerSneak(PlayerToggleSneakEvent event) {
		if (event.isCancelled())
			return;

		Player player = event.getPlayer();
		new Glider(player);
		// new GrapplingHook(player, Action.SHIFT);

		// Handles the Charges, and ShiftCharges attribute
		if (!player.isSneaking()) {
			ItemUtils.updateOnActionEffects(player, Action.SHIFT);
			ItemUtils.handleItemSource(player, "WaterSource", ItemUtils.getWaterBottles(1));
		}
	}
	*/

	/**
	 * Confirm if an ability was executed via clicking. Also handle specific stats that related to
	 * left clicking.
	 * 
	 * @param event a player animation event
	 */
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (event.isCancelled())
			return;
		Player player = event.getPlayer();
		org.bukkit.event.block.Action a = event.getAction();
		
		if (a == org.bukkit.event.block.Action.LEFT_CLICK_AIR || a == org.bukkit.event.block.Action.LEFT_CLICK_BLOCK) {
			ItemUtils.updateOnActionEffects(player, Action.LEFT_CLICK);
			ItemUtils.handleItemSource(player, "WaterSource", ItemUtils.getWaterBottles(1));
		} else if (a == org.bukkit.event.block.Action.RIGHT_CLICK_AIR || a == org.bukkit.event.block.Action.RIGHT_CLICK_BLOCK) {
			
		}

		// new GrapplingHook(player, Action.LEFT_CLICK);
	}

	/**
	 * Make a call to allow all consume based affects for the custom item that the player ate.
	 * 
	 * @param event a consume event
	 */
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerConsume(PlayerItemConsumeEvent event) {
		if (event.isCancelled())
			return;
		ItemUtils.updateOnActionEffects(event.getPlayer(), Action.CONSUME);
	}

	/**
	 * Because of the "AirGliderAutomatic" stat, we need to attempt to glide whenever the user
	 * switches their item.
	 * 
	 * @param event item change event
	 */
	@EventHandler(priority = EventPriority.NORMAL)
	public void onChangeItem(PlayerItemHeldEvent event) {
		if (event.isCancelled())
			return;

		Player player = event.getPlayer();
		ConcurrentHashMap<String, Double> attribs = AttributeUtils.getSimplePlayerAttributeMap(player);
		boolean auto = Attribute.getBooleanValue("AirGlideAutomatic", attribs);
		if (auto)
			new Glider(player, true);
	}
}
