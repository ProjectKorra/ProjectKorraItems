package com.projectkorra.items.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.projectkorra.items.Messages;
import com.projectkorra.items.attribute.Attribute;
import com.projectkorra.items.attribute.AttributeList;

public class StatsCommand extends PKICommand {

	public StatsCommand() {
		super("stats", "/bending items stats", "This command lists all stats which can be applied to a bending item", Messages.STATS_ALIAS);
	}

	@Override
	public void execute(CommandSender sender, List<String> args) {
		if (correctLength(sender, args.size(), 0, 1)) {
			if (args.size() >= 0) {
				if (!hasPermission(sender)) {
					sender.sendMessage(Messages.NO_PERM);
					return;
				} else if (args.size() >= 0) {
					int page = 1;
					String phrase = null;
					if (args.size() == 1) {
						try {
							page = Integer.parseInt(args.get(0));
						}
						catch (Exception e) {
							phrase = args.get(0);
						}
					} else if (args.size() == 1) {
						phrase = args.get(0);
						try {
							page = Integer.parseInt(args.get(0));
						}
						catch (Exception e) {
						}
					}

					ArrayList<Attribute> attribs = new ArrayList<Attribute>(AttributeList.ATTRIBUTES);
					if (phrase != null) {
						for (int i = 0; i < attribs.size(); i++) {
							Attribute att = attribs.get(i);
							if (!att.getName().toLowerCase().contains(phrase.toLowerCase())) {
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
					sender.sendMessage(ChatColor.YELLOW + " ---- " + ChatColor.GOLD + "Stats " + ChatColor.YELLOW + "-- " + ChatColor.GOLD + "Page " + ChatColor.RED + (page + 1) + ChatColor.GOLD + "/" + ChatColor.RED + (maxPage + 1) + ChatColor.YELLOW + " ----");
					for (int i = pageIndex; i < pageIndex + Messages.LINES_PER_PAGE; i++) {
						if (i >= 0 && i < attribs.size()) {
							Attribute att = attribs.get(i);
							sender.sendMessage(ChatColor.YELLOW + att.getName() + ": " + ChatColor.WHITE + att.getDesc());
						} else
							break;
					}
					if (phrase != null)
						sender.sendMessage(ChatColor.GOLD + "Phrase: " + ChatColor.RED + phrase);
					return;
				}
			}
		}
	}
	
}
