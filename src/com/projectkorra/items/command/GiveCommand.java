package com.projectkorra.items.command;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.projectkorra.items.Messages;
import com.projectkorra.items.customs.CustomItem;
import com.projectkorra.projectkorra.command.PKCommand;

public class GiveCommand extends PKCommand {

	public GiveCommand() {
		super("give", "/bending give [item] <amount> <player>", "This command gives you or another player a bending item", Messages.GIVE_ALIAS);
	}

	@Override
	public void execute(CommandSender sender, List<String> args) {
		if (correctLength(sender, args.size(), 0, 3)) {
			if (hasPermission(sender)) {
				if (args.size() == 0) {
					sender.sendMessage(ChatColor.YELLOW + " ---- " + ChatColor.GOLD + "Item Names " + ChatColor.YELLOW + "----");
					for (CustomItem citem : CustomItem.itemList)
						sender.sendMessage(ChatColor.YELLOW + citem.getName());
					return;
				}
				if (isPlayer(sender)) {
					Player player = (Player)sender;
					
					CustomItem ci = null;
					ItemStack i = null;
					
					if (args.size() >= 1) {
						player.sendMessage("..");
						try {
							player.sendMessage("...");
							ci = CustomItem.getCustomItem(args.get(1).toString());
						} catch (Exception e) {
							e.printStackTrace();
							return;
						}
						i = ci.generateItem();
					}
					player.getInventory().addItem(i);
				}
			}
		}
	}

}
