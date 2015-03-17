package com.projectkorra.ProjectKorraItems;

import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.projectkorra.ProjectKorra.MetricsLite;
import com.projectkorra.ProjectKorraItems.abilityupdater.AbilityUpdater;
import com.projectkorra.ProjectKorraItems.attribute.AttributeListener;
import com.projectkorra.ProjectKorraItems.items.ItemDisplay;
import com.projectkorra.ProjectKorraItems.items.ItemListener;

public class ProjectKorraItems extends JavaPlugin {
	public static ProjectKorraItems plugin;
	public static Logger log;

	@Override
	public void onEnable() {
		ProjectKorraItems.log = this.getLogger();
		plugin = this;
		PluginDescriptionFile pdfFile = this.getDescription();
		log.info(pdfFile.getName() + " Version " + pdfFile.getVersion() + " Has Been Enabled!");
		new CommandManager();
		new ConfigManager();
		this.getServer().getPluginManager().registerEvents(new ItemListener(), this);
		this.getServer().getPluginManager().registerEvents(new AttributeListener(), this);
		AbilityUpdater.startUpdater();
		AbilityUpdater.startCleanup();
		
		try {
			MetricsLite metrics = new MetricsLite(this);
			metrics.start();
		} catch (IOException e) {
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
