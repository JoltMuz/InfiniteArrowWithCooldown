package io.github.JoltMuz.InfiniteArrowWithCooldown;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;


public class Listeners implements Listener
{
	JavaPlugin plugin;
	
	public Listeners(JavaPlugin plugin) 
	{
		this.plugin = plugin;
	}

	@EventHandler
    public void onArrowShoot(ProjectileLaunchEvent event) 
	{
		if (!(plugin.getConfig().getBoolean("enabled")))
		{
			return;
		}
        if (event.getEntity() instanceof Arrow) 
        {
            Arrow arrow = (Arrow) event.getEntity();
            if (arrow.getShooter() instanceof Player)
            {
            	Bukkit.getScheduler().runTaskLater(plugin, () -> 
            	{
            		((Player) arrow.getShooter()).getInventory().addItem(new ItemStack(Material.ARROW));
   	            }, plugin.getConfig().getInt("cooldown"));
            }
        }
    }
	
}
