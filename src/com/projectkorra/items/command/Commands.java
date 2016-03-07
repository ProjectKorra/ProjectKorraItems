package com.projectkorra.items.command;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;

import com.projectkorra.items.Messages;
import com.projectkorra.items.ProjectKorraItems;
import com.projectkorra.projectkorra.ProjectKorra;

public class Commands {

	private ProjectKorraItems plugin;

	public static Set<String> invincible = new HashSet<String>();
	public static boolean debugEnabled = false;
	public static boolean isToggledForAll = false;

	public Commands(ProjectKorraItems plugin) {
		this.plugin = plugin;
		debugEnabled = ProjectKorra.plugin.getConfig().getBoolean("debug");
		init();
	}

	private void init() {
		PluginCommand projectkorraitems = plugin.getCommand("items");
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
					s.sendMessage(ChatColor.RED + "/items equip " + ChatColor.YELLOW + "Equip a bending item");
					s.sendMessage(ChatColor.RED + "/items give [item] <amount> <player> " + ChatColor.YELLOW + "Give bending items");
					s.sendMessage(ChatColor.RED + "/items stats <specific> " + ChatColor.YELLOW + "Shows all item stats");
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