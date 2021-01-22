package com.projectkorra.items;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.projectkorra.items.abilityupdater.AbilityUpdater;
import com.projectkorra.items.attribute.AttributeListener;
import com.projectkorra.items.command.BaseCommand;
import com.projectkorra.items.command.EquipCommand;
import com.projectkorra.items.command.GiveCommand;
import com.projectkorra.items.command.ListCommand;
import com.projectkorra.items.command.ReloadCommand;
import com.projectkorra.items.command.StatsCommand;
import com.projectkorra.items.customs.PKIDisplay;

public class ProjectKorraItems extends JavaPlugin {
	public static ProjectKorraItems plugin;
	public static Logger log;

	@Override
	public void onEnable() {
		plugin = this;
		ProjectKorraItems.log = this.getLogger();
		
		PluginDescriptionFile pdfFile = this.getDescription();
		log.info(pdfFile.getName() + " Version " + pdfFile.getVersion() + " Has Been Enabled!");
		
		new BaseCommand();
		new EquipCommand();
		new GiveCommand();
		new ListCommand();
		new ReloadCommand();
		new StatsCommand();
		
		new ConfigManager();
		PKIDisplay.displays = new ConcurrentHashMap<Player, PKIDisplay>();
		
		PluginManager pm = this.getServer().getPluginManager();
		
		//
		
		pm.registerEvents(new PKIListener(), this);
		pm.registerEvents(new AttributeListener(), this);
		pm.registerEvents(new AbilityUpdater(), this);
		
		//

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
		
		if (PKIDisplay.displays != null && !PKIDisplay.displays.isEmpty()) {
			PKIDisplay.cleanup();
		}
	}
}
