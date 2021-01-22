package com.projectkorra.items.items;

import com.projectkorra.items.ProjectKorraItems;
import com.projectkorra.items.attribute.Action;
import com.projectkorra.items.utils.ElementUtils;
import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.projectkorra.util.ParticleEffect;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Grappling Hooks let the player grab onto blocks and fling themselves toward a
 * destination location. They can cause a fling to occur by left clicking, or
 * they can hold sneak to slowly climb the Grappling Hook, causing them to
 * slowly ascend to the destination.
 * 
 * TODO: This class is unfinished due to some issues with making the particle
 * animations realistic for a grappling hook.
 */
public class GrapplingHook {

	/**
	 * Represents the possible states that a GrapplingHook can be in.
	 */
	public static enum State {
		START, SHOOTING, HOOKED, FLUNG, CLIMBING
	}

	public static final ConcurrentHashMap<String, GrapplingHook> INSTANCES = new ConcurrentHashMap<String, GrapplingHook>();
	public static final double SPEED = 5;
	public static final double RANGE = 30;

	private State state;
	private Player player;
	private GrapplingHookTask task;
	private Location currentLoc;
	private Vector direction;
	private double speed;
	private double range;
	private int red, green, blue;

	/**
	 * Attempt to create a new Grappling Hook, fling a currently existing
	 * grappling hook, or retract a hook, all depending on the type of action.
	 * 
	 * @param player the player that used a grappling hook
	 * @param action either Action.SHIFT, or Action.LEFTCLICK
	 */
	public GrapplingHook(Player player, Action action) {
		if (INSTANCES.containsKey(player.getName())) {
			GrapplingHook hook = INSTANCES.get(player.getName());
			if (hook.state == State.HOOKED) {
				if (action == Action.LEFT_CLICK) {
					
				} else if (action == Action.RIGHT_CLICK) {
					
				} else if (action == Action.SHIFT) {

				}
			}
			return;
		}

		this.state = State.START;
		this.player = player;
		this.speed = SPEED;
		this.range = RANGE;
		INSTANCES.put(player.getName(), this);
		task = new GrapplingHookTask(this);
		task.runTaskTimer(ProjectKorraItems.plugin, 0, 1);
	}

	/**
	 * The GrapplingHookTask is the manipulates the specific instance of a
	 * GrapplingHook after it has been initially created. This task does its
	 * action based on the State of the GrapplingHook that created this task. If
	 * that variable is changed then this task will follow in turn.
	 */
	public class GrapplingHookTask extends BukkitRunnable {
		GrapplingHook hook;

		public GrapplingHookTask(GrapplingHook hook) {
			this.hook = hook;
		}

		public void run() {
			if (player == null || player.isDead() || !player.isOnline()) {
				cancel();
				return;
			}
			if (currentLoc != null) {
				if (!player.getWorld().equals(currentLoc.getWorld()) || currentLoc.distanceSquared(player.getEyeLocation()) > Math.pow(range, 2)) {
					cancel();
					return;
				}
			}

			if (state == State.START) {
				state = State.SHOOTING;
				direction = player.getEyeLocation().toVector().normalize();
				currentLoc = player.getEyeLocation();
			} else if (state == State.SHOOTING) {
				/*
				 * Use an iterator in case the speed value is too big, it would
				 * normally cause blocks to be skipped if we just jumped
				 * directly.
				 */
				playAnimation(player.getEyeLocation().add(0, -0.5, 0), currentLoc, 1, (float) 0.1, red, green, blue);
				BlockIterator iter = new BlockIterator(currentLoc.getWorld(), currentLoc.toVector(), direction, 0, (int) speed + 1);
				currentLoc = currentLoc.add(direction.clone().multiply(speed));
				while (iter.hasNext()) {
					Block block = iter.next();
					if (!ElementUtils.isTransparent(block.getType())) {
						currentLoc = block.getLocation();
						state = State.HOOKED;
						break;
					}
				}
			} else if (state == State.HOOKED) {
				playAnimation(player.getEyeLocation().add(0, -0.5, 0), currentLoc, 1, (float) 0.1, red, green, blue);
			}

		}

		public void cancel() {
			super.cancel();
			hook.task = null;
			hook.cancel();
		}
	}

	/**
	 * Cancels and removes this instance of GrapplingHook.
	 */
	public void cancel() {
		if (task != null)
			task.cancel();
		INSTANCES.remove(player.getName());
	}

	/**
	 * Displays the GrapplingHook "Beam" animation.
	 * 
	 * @param loc1 the starting location
	 * @param loc2 the ending location
	 * @param incr the spacing between each individual particle
	 * @param density the density of each particle
	 * @param r amount of red in the particle (0 - 255)
	 * @param g amount of green in the particle (0 - 255)
	 * @param b amount of blue in the particle (0 - 255)
	 */
	public static void playAnimation(Location loc1, Location loc2, double incr, float density, int r, int g, int b) {
		Location iter = loc1.clone();
		Vector dir = GeneralMethods.getDirection(loc1, loc2).normalize();
		while (iter.distanceSquared(loc1) <= iter.distanceSquared(loc2)) {
			ParticleEffect.REDSTONE.display(iter, 1, 0, 0, 0, new Particle.DustOptions(Color.fromRGB(r, g, b), density));
			iter = iter.add(dir.clone().multiply(incr));
		}
	}

	/**
	 * Displays the GrapplingHook "Beam" animation.
	 * 
	 * @param loc1 the starting location
	 * @param loc2 the ending location
	 * @param incr the spacing between each individual particle
	 * @param density the density of each particle
	 */
	public static void playAnimation(Location loc1, Location loc2, double incr, float density) {
		playAnimation(loc1, loc2, incr, density, 0, 0, 0);
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Player getPlayer() {
		return player;
	}

	public GrapplingHookTask getTask() {
		return task;
	}

	public void setTask(GrapplingHookTask task) {
		this.task = task;
	}

	public Location getCurrentLoc() {
		return currentLoc;
	}

	public void setCurrentLoc(Location currentLoc) {
		this.currentLoc = currentLoc;
	}

	public Vector getDirection() {
		return direction;
	}

	public void setDirection(Vector direction) {
		this.direction = direction;
	}
}
