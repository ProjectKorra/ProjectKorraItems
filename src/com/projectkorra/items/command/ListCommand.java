package com.projectkorra.items.command;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.projectkorra.items.Messages;
import com.projectkorra.items.customs.ItemDisplay;

public class ListCommand extends PKICommand {

	public ListCommand() {
		super("list", "/bending list", "This command lists bending items.", Messages.ITEMS_ALIAS);
	}

	@Override
	public void execute(CommandSender sender, List<String> args) {
		if (this.correctLength(sender, args.size(), 0, 1)) {
			if (args.size() == 0) {
				if (!hasPermission(sender)) {
					sender.sendMessage(Messages.NO_PERM);
					return;
				} else if (!isPlayer(sender)) {
					sender.sendMessage(Messages.PLAYER_ONLY);
					return;
				}

				Player player = (Player) sender;
				boolean showStats = false;

				if (args.size() == 1) {
					for (String s : Messages.STATS_ALIAS) {
						if (args.get(0).equalsIgnoreCase(s)) {
							showStats = true;
						}

						new ItemDisplay(player, showStats);
						return;
					}
				}
			}
		}
	}
}
