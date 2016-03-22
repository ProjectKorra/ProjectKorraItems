package com.projectkorra.items.command;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.projectkorra.items.Messages;
import com.projectkorra.projectkorra.command.PKCommand;

public class BaseCommand extends PKCommand {

	public BaseCommand() {
		super("items", "/b items <args>", "Base items command", Messages.ITEMS_ALIAS);
	}
	
	public void init() {
		new EquipCommand();
		new GiveCommand();
		new ListCommand();
		new ReloadCommand();
		new StatsCommand();
	}

	@Override
	public void execute(CommandSender sender, List<String> args) {
		if (args.size() == 0) {
			sender.sendMessage(ChatColor.RED + "/b items list " + ChatColor.YELLOW + "<stats> List items.");
			sender.sendMessage(ChatColor.RED + "/b items give " + ChatColor.YELLOW + "<item> <amount> <player> Give an item.");
			sender.sendMessage(ChatColor.RED + "/b items stats " + ChatColor.YELLOW +  "Check item stats.");
			sender.sendMessage(ChatColor.RED + "/b items equip " + ChatColor.YELLOW +  "Equip an item.");
			
			return;
		}
		
		for (PKICommand command : PKICommand.instances.values()) {
			if (Arrays.asList(command.getAliases()).contains(args.get(0).toLowerCase())) {
				command.execute(sender, args.subList(1, args.size()));
			}
		}
	}

}
