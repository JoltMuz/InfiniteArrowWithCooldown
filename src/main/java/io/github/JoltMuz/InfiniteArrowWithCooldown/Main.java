package io.github.JoltMuz.InfiniteArrowWithCooldown;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{
private static JavaPlugin plugin;
	
	@Override
    public void onEnable() 
	{
		plugin = this;
		
		getConfig();
		saveDefaultConfig();
        reloadConfig();
		
		this.getCommand("InfiniteArrowWithCooldown").setExecutor(new Commands(plugin));
		getServer().getPluginManager().registerEvents(new Listeners(plugin), this);
		
	}
	@Override
    public void onDisable() 
	{
		saveConfig();
	}
	
	public static JavaPlugin getPlugin()
	{
		return plugin;
	}

}
