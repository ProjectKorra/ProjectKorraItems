package com.projectkorra.items.items;

import java.util.Map;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.projectkorra.items.ProjectKorraItems;
import com.projectkorra.items.attribute.Action;
import com.projectkorra.items.attribute.AttributeList;
import com.projectkorra.items.utils.AttributeUtils;
import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.Element;
import org.jetbrains.annotations.NotNull;

public class Glider {

	/**
	 * A Glider is used for the "AirGlide" stat, and it allows users to sore
	 * through the air while they are sneaking. The glider will persist until
	 * the player reaches the ground. Gliders will also use up a players charges
	 * if the items that they are using have charges. Players must be Airbenders
	 * to glide.
	 * 
	 * @param player the player that will glide
	 */
	public Glider(Player player) {
		this(player, false);
	}

	/**
	 * A Glider is used for the "AirGlide" stat, and it allows users to sore
	 * through the air while they are sneaking. The glider will persist until
	 * the player reaches the ground. Gliders will also use up a players charges
	 * if the items that they are using have charges. Players must be Airbenders
	 * to glide.
	 * 
	 * @param player the player that will glide
	 * @param auto if the gliding should start automatically without sneaking
	 */
	public Glider(Player player, final boolean auto) {
		BendingPlayer bplayer = BendingPlayer.getBendingPlayer(player.getName());
		if (player == null || bplayer == null)
			return;

		if (!player.isSneaking() && player.getLocation().getBlock().getType() == Material.AIR && bplayer.hasElement(Element.AIR)) {

			/*
			 * The gliding action will be performed on a separate runnable that
			 * will cancel itself once it is done.
			 */
			new BukkitRunnable() {
				int counter = 0;

				public void run() {
					Block block = player.getLocation().add(0, -0.5, 0).getBlock();
					if (block.getType() != Material.AIR) {
						this.cancel();
						return;
					}

					Map<String, Double> attribs = AttributeUtils.getSimplePlayerAttributeMap(player);
					if ((!player.isSneaking() && !auto) || (player.isSneaking() && auto)) {
						this.cancel();
						return;
					}

					if (attribs.containsKey("AirGlide")) {
						double speed = AttributeList.AIR_GLIDE_SPEED;
						double fallSpeed = AttributeList.AIR_GLIDE_FALL;
						if (attribs.containsKey("AirGlideSpeed"))
							speed = speed + speed * attribs.get("AirGlideSpeed") / 100.0;
						if (attribs.containsKey("AirGlideFallSpeed"))
							fallSpeed = fallSpeed + fallSpeed * attribs.get("AirGlideFallSpeed") / 100.0;

						Location loc = player.getEyeLocation();
						loc.setPitch(0);
						Vector vel = loc.getDirection();
						vel.normalize();
						vel.multiply(speed);
						vel.setY(fallSpeed);
						player.setFallDistance(0);
						player.setVelocity(vel);
						if (counter == 0)
							AttributeUtils.decreaseCharges(player, Action.SHIFT);
						counter++;
					}
				}
			}.runTaskTimer(ProjectKorraItems.plugin, 1, 1);
		}
	}
}
