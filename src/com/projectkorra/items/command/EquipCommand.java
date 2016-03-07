package com.projectkorra.items.command;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.projectkorra.items.Messages;
import com.projectkorra.items.customs.ItemEquip;

public class EquipCommand extends PKICommand {

	public EquipCommand() {
		super("equip", "/bending equip", "This command will equip a bending item", Messages.EQUIP_ALIAS);
	}

	@Override
	public void execute(CommandSender sender, List<String> args) {
		if (correctLength(sender, args.size(), 0, 0))
		if (args.size() == 0) {
			if (!hasPermission(sender)) {
				sender.sendMessage(Messages.NO_PERM);
				return;
			} else if (!isPlayer(sender)) {
				sender.sendMessage(Messages.PLAYER_ONLY);
				return;
			}

			Player player = (Player)sender;
			new ItemEquip(player);
			return;
		}
		
	}

}
