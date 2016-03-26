package com.projectkorra.items.command;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.projectkorra.items.Messages;
import com.projectkorra.projectkorra.command.PKCommand;

public class BaseCommand extends PKCommand {

	public BaseCommand() {
		super("items", "/bending items", "Base command for the Items side plugin", Messages.ITEMS_ALIAS);
	}

	@Override
	public void execute(CommandSender sender, List<String> args) {
		if (args.size() == 0) {
			sender.sendMessage(ChatColor.RED + "/bending items equip " + ChatColor.YELLOW +  "Equip an item.");
			sender.sendMessage(ChatColor.RED + "/bending items give <Item> [Amount] [Player] " + ChatColor.YELLOW + "Give an item.");
			sender.sendMessage(ChatColor.RED + "/bending items list " + ChatColor.YELLOW + "List all items.");
			sender.sendMessage(ChatColor.RED + "/bending items stats " + ChatColor.YELLOW +  "List item stats.");
			
			return;
		}
		
		for (PKICommand command : PKICommand.instances.values()) {
			if (Arrays.asList(command.getAliases()).contains(args.get(0).toLowerCase())) {
				command.execute(sender, args.subList(1, args.size()));
			}
		}
	}

}
