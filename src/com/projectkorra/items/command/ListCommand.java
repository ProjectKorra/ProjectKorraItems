package com.projectkorra.items.command;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.projectkorra.items.Messages;
import com.projectkorra.items.customs.PKIDisplay;

public class ListCommand extends PKICommand {

	public ListCommand() {
		super("list", "/bending items list", "This command lists all bending items and their recipies.", Messages.LIST_ALIAS);
	}

	@Override
	public void execute(CommandSender sender, List<String> args) {
		if (this.correctLength(sender, args.size(), 0, 1)) {
			if (!hasPermission(sender)) {
				sender.sendMessage(Messages.NO_PERM);
				return;
			}
			if (!isPlayer(sender)) {
				sender.sendMessage(Messages.PLAYER_ONLY);
				return;
			}
			Player player = (Player) sender;
			
			boolean show = false;
			
			if (args.size() >= 0) {
				if (args.size() == 1) {
					for (String s : Messages.STATS_ALIAS) {
						if (args.get(0) == s) {
							show = true;
						}
					}
				}
				PKIDisplay d = new PKIDisplay(player, show);
				d.createInventory();
			}
		}
	}
}
