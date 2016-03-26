package com.projectkorra.items.command;

import java.util.List;

import org.bukkit.command.CommandSender;

import com.projectkorra.items.ConfigManager;
import com.projectkorra.items.Messages;

public class ReloadCommand extends PKICommand {

	public ReloadCommand() {
		super("reload", "/bending items reload", "This command reloads Items configuration.", Messages.RELOAD_ALIAS);
	}

	@Override
	public void execute(CommandSender sender, List<String> args) {
		if (correctLength(sender, args.size(), 0, 0)) {
			if (!hasPermission(sender)) {
				sender.sendMessage(Messages.NO_PERM);
				return;
			}
			new ConfigManager();
			sender.sendMessage(Messages.CONFIG_RELOADED);
		}
	}

}
