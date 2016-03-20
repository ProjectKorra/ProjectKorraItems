package com.projectkorra.items.command;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;

import com.projectkorra.items.Messages;
import com.projectkorra.items.ProjectKorraItems;

public class Commands {

	private ProjectKorraItems plugin;
	
	public Commands(ProjectKorraItems plugin) {
		this.plugin = plugin;
		init();
	}

	private void init() {
		PluginCommand projectkorraitems = plugin.getCommand("item");
		new EquipCommand();
		new GiveCommand();
		new ListCommand();
		new ReloadCommand();
		new StatsCommand();

		/**
		 * Set of all of the Classes which extend Command
		 */

		CommandExecutor exe;

		exe = new CommandExecutor() {
			@Override
			public boolean onCommand(CommandSender s, Command c, String label, String[] args) {
				for (int i = 0; i < args.length; i++) {
					args[i] = args[i];
				}

				if (args.length == 0 && Arrays.asList(Messages.ITEMS_ALIAS).contains(label.toLowerCase())) {
					s.sendMessage(ChatColor.RED + "/item equip " + ChatColor.YELLOW + "Equip an item");
					s.sendMessage(ChatColor.RED + "/item list " + ChatColor.YELLOW + "Lists all bending items");
					s.sendMessage(ChatColor.RED + "/item stats <specific> " + ChatColor.YELLOW + "Displays stats of items");
					return true;
				}

				List<String> sendingArgs = Arrays.asList(args).subList(1, args.length);
				for (PKICommand command : PKICommand.instances.values()) {
					if (Arrays.asList(command.getAliases()).contains(args[0].toLowerCase())) {
						command.execute(s, sendingArgs);
						return true;
					}
				}
				return true;
			}
		};
		projectkorraitems.setExecutor(exe);
	}
}