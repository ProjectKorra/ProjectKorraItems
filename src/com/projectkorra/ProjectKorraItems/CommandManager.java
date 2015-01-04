package com.projectkorra.ProjectKorraItems;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.projectkorra.ProjectKorraItems.attribute.Attribute;
import com.projectkorra.ProjectKorraItems.attribute.AttributeList;
import com.projectkorra.ProjectKorraItems.items.CustomItem;
import com.projectkorra.ProjectKorraItems.items.ItemDisplay;

public class CommandManager {
	ProjectKorraItems plugin;

	public CommandManager(ProjectKorraItems plugin) {
		this.plugin = plugin;
		init();
	}

	public void init() {
		CommandExecutor exe = new CommandExecutor() {
			@Override
			public boolean onCommand(CommandSender s, Command c, String label, String[] args) {
				if(args.length >= 1 && aliasChecker(args[0], Messages.RELOAD_ALIAS)) {
					if(!s.hasPermission("bendingitems.command.reload")) {
						s.sendMessage(Messages.NO_PERM);
						return true;
					}
					
					new ConfigManager(ProjectKorraItems.plugin);
					s.sendMessage(Messages.CONFIG_RELOADED);
					return true;
				}
				/*
				 * GIVING
				 */
				else if(args.length >= 1 && aliasChecker(args[0], Messages.GIVE_ALIAS)) {
					if(!s.hasPermission("bendingitems.command.give")) {
						s.sendMessage(Messages.NO_PERM);
						return true;
					}
					else if(args.length == 1) {
						s.sendMessage(Messages.GIVE_USAGE);
						return true;
					}
					else if(args.length >= 2 && aliasChecker(args[1], Messages.DISPLAY_ALIAS)) {
						s.sendMessage(ChatColor.YELLOW + " ---- " + ChatColor.GOLD + "Item Names " + ChatColor.YELLOW + "----");
						for(CustomItem citem : CustomItem.itemList)
							s.sendMessage(ChatColor.YELLOW + citem.getName());
						return true;
					}
					else if(args.length == 2 && !(s instanceof Player)) {
						s.sendMessage(Messages.PLAYER_ONLY);
						return true;
					}
					
					Player player = null;
					CustomItem citem = null;
					int qty = 0;
					if(args.length == 2) {
						citem = CustomItem.getCustomItem(args[1]);
						player = (Player) s;
					}
					else if(args.length == 3) {
						citem = CustomItem.getCustomItem(args[1]);
						player = ProjectKorraItems.plugin.getServer().getPlayer(args[2]);
						if(player == null) {
							player = (Player) s;
							try {
								qty = Integer.parseInt(args[2]);
							} 
							catch (Exception e) {
								// If the qty wasn't parsable then they were attemping to send a name
								s.sendMessage(Messages.INVALID_PLAYER);
								return true;
							}
						}
					}
					else if(args.length >= 4) {
						citem = CustomItem.getCustomItem(args[1]);
						player = ProjectKorraItems.plugin.getServer().getPlayer(args[2]);
						try {
							qty = Integer.parseInt(args[3]);
						} 
						catch (Exception e) {}
					}
				
					if(player == null) {
						s.sendMessage(Messages.INVALID_PLAYER);
						return true;
					}
					else if(citem == null) {
						s.sendMessage(Messages.ITEM_NOT_FOUND);
						return true;
					}
					
					if(qty < 1)
						qty = citem.getQuantity();
					ItemStack istack = citem.generateItem();
					istack.setAmount(qty);
					player.getInventory().addItem(istack);
					return true;
				}			
				/*
				 * STATS
				 */
				else if(args.length >= 1 && aliasChecker(args[0], Messages.STATS_ALIAS)) {
					if(!s.hasPermission("bendingitems.command.stats")) {
						s.sendMessage(Messages.NO_PERM);
						return true;
					}
					else if(args.length >= 1) {
						int page = 1;
						String phrase = null;
						if(args.length == 2) {
							try {
								page = Integer.parseInt(args[1]);
							} 
							catch(Exception e) {
								phrase = args[1];
							}
						}
						else if(args.length >= 3) {
							phrase = args[1];
							try {
								page = Integer.parseInt(args[2]); 
							} 
							catch(Exception e) {}
						}
						
						ArrayList<Attribute> attribs = new ArrayList<Attribute>(AttributeList.attributes);
						if(phrase != null) {
							for(int i = 0; i < attribs.size(); i++) {
								Attribute att = attribs.get(i);
								if(!att.getName().toLowerCase().contains(phrase.toLowerCase())) {
									ProjectKorraItems.log.info("Removing: " + att.getName());
									attribs.remove(i);
									i--;
								}	
							}
						}
						int maxPage = (attribs.size() - 1) / Messages.LINES_PER_PAGE;
						page -= 1;
						page = page < 0 ? 0 : page;		
						page = page > maxPage ? maxPage : page;
						int pageIndex = page * Messages.LINES_PER_PAGE;						
						s.sendMessage(ChatColor.YELLOW + " ---- " + ChatColor.GOLD + "Stats " + 
								ChatColor.YELLOW + "-- " + ChatColor.GOLD + "Page " +
								ChatColor.RED + (page + 1) + ChatColor.GOLD + "/" + ChatColor.RED +
								(maxPage + 1) + ChatColor.YELLOW + " ----");
						for(int i = pageIndex; i < pageIndex + Messages.LINES_PER_PAGE; i++) {
							if(i >= 0 && i < attribs.size()) {
								Attribute att = attribs.get(i);
								s.sendMessage(ChatColor.YELLOW + att.getName() + ": " + ChatColor.WHITE + att.getDesc());
							}
							else
								break;
						}
						if(phrase != null)
							s.sendMessage(ChatColor.GOLD + "Phrase: " + ChatColor.RED + phrase);
						return true;
					}
				}
				/*
				 * ITEMS
				 */
				else if(args.length >= 1 && aliasChecker(args[0], Messages.ITEMS_ALIAS)) {
					if(!s.hasPermission("bendingitems.command.items")) {
						s.sendMessage(Messages.NO_PERM);
						return true;
					}
					else if(!(s instanceof Player)) {
						s.sendMessage(Messages.PLAYER_ONLY);
						return true;
					}
					
					Player player = (Player) s;
					boolean showStats = false;
					if(args.length >= 2 && aliasChecker(args[1], Messages.STATS_ALIAS)) {
						if(!s.hasPermission("bendingitems.command.items.stats")) {
							s.sendMessage(Messages.NO_PERM);
							return true;
						}
						showStats = true;
					}
					new ItemDisplay(player, showStats);
					return true;
				}
				s.sendMessage(Messages.USAGE);
				return true;
			}
		};
		plugin.getCommand("projectkorraitems").setExecutor(exe);
	}
	
	public boolean aliasChecker(String s, String[] alias) {
		for(String ali : alias)
			if(s.equalsIgnoreCase(ali))
				return true;
		return false;
	}
}
