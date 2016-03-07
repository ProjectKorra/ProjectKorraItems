package com.projectkorra.items;

import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.projectkorra.items.abilityupdater.AbilityUpdater;
import com.projectkorra.items.attribute.AttributeListener;
import com.projectkorra.items.command.Commands;
import com.projectkorra.items.customs.ItemDisplay;
import com.projectkorra.items.customs.ItemListener;

public class ProjectKorraItems extends JavaPlugin {
	public static ProjectKorraItems plugin;
	public static Logger log;

	@Override
	public void onEnable() {
		ProjectKorraItems.log = this.getLogger();
		plugin = this;
		PluginDescriptionFile pdfFile = this.getDescription();
		log.info(pdfFile.getName() + " Version " + pdfFile.getVersion() + " Has Been Enabled!");
		
		new Commands(this);
		
		new ConfigManager();
		
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(new ItemListener(), this);
		pm.registerEvents(new AttributeListener(), this);
		pm.registerEvents(new AbilityUpdater(), this);
		AbilityUpdater.startCleanup();

		try {
			MetricsLite metrics = new MetricsLite(this);
			metrics.start();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		log.info(pdfFile.getName() + " Has Been Disabled!");
		ItemDisplay.cleanup();
	}
}
