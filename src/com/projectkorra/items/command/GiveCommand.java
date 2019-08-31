package com.projectkorra.items.command;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.projectkorra.items.Messages;
import com.projectkorra.items.ProjectKorraItems;
import com.projectkorra.items.customs.PKItem;

public class GiveCommand extends PKICommand {

	public GiveCommand() {
		super("give", "/bending items give <Item> [Amount] [Player]", "This command gives you or another player a bending item.", Messages.GIVE_ALIAS);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, List<String> args) {
		if (correctLength(sender, args.size(), 0, 3)) {
			if (hasPermission(sender)) {
				if (args.size() == 0) {
					sender.sendMessage(ChatColor.YELLOW + " ---- " + ChatColor.GOLD + "Item Names " + ChatColor.YELLOW + "----");
					for (PKItem citem : PKItem.itemList)
						sender.sendMessage(ChatColor.YELLOW + citem.getName());
					return;
				}
				if (isPlayer(sender)) {
					Player player = (Player)sender;
					
					PKItem ci = null;
					ItemStack i = null;
					int q = 1;
					
					if (args.size() >= 1) {
						ci = PKItem.getCustomItem(args.get(0));
						
						if (ci == null) {
							sender.sendMessage(Messages.ITEM_NOT_FOUND);
							return;
						}
						i = ci.generateItem();
						
						if (args.size() >= 2) {
							try {
								q = Integer.parseInt(args.get(1));
							} catch (Exception e) {
								sender.sendMessage(Messages.NOT_INT);
								return;
							}
							i.setAmount(q);
							
							if (args.size() == 3) {
								Player target = (Player)ProjectKorraItems.plugin.getServer().getPlayer(args.get(2));
								
								if (target == null) {
									sender.sendMessage(Messages.INVALID_PLAYER);
									return;
								}
								
								target.getInventory().addItem(i);
								return;
							}
						}
					}
					player.getInventory().addItem(i);
					return;
				}
				sender.sendMessage(Messages.PLAYER_ONLY);
				return;
			}
			sender.sendMessage(Messages.NO_PERM);
			return;
		}
	}

}
