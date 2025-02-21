package io.github.JoltMuz.InfiniteArrowWithCooldown;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Commands implements CommandExecutor
{
	JavaPlugin plugin;

	public Commands(JavaPlugin plugin) 
	{
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!sender.isOp())
		{
			sender.sendMessage(ChatColor.RED + "(!) " + ChatColor.GRAY + "This command is Operator only");
			return true;
		}
		if (args.length < 1)
		{
			sender.sendMessage(ChatColor.AQUA + "✔ " + ChatColor.GRAY + "Plugin: " + booleanToEnabled(toggle()));
			return true;
		}
		String subcommand = args[0].toLowerCase();
		switch (subcommand)
		{
		case "toggle":
			sender.sendMessage(ChatColor.AQUA + "✔ " + ChatColor.GRAY + "Plugin: " + ChatColor.AQUA + booleanToEnabled(toggle()));
			break;
		case "enable":
			sender.sendMessage(ChatColor.GREEN + "✔ " + ChatColor.GRAY + "Plugin: " + ChatColor.AQUA + booleanToEnabled(true));
			plugin.getConfig().set("enabled", true);
			break;
		case "disable":
			sender.sendMessage(ChatColor.RED + "✔ " + ChatColor.GRAY + "Plugin: " + ChatColor.AQUA + booleanToEnabled(false));
			plugin.getConfig().set("enabled", false);
			break;
		case "cooldown":
			handleCooldownCommand(args, sender, label);
			break;
		case "set":
		case "setCooldown":
			setCooldown(args, sender, label);
			break;
		case "check":
		case "checkCooldown":
			checkCooldown(sender);
			break;
		case "?":
			sendHelp(sender, label);
			break;
		default:
			sendHelp(sender, label);
			break;
		}
		return true;
	}
	
	boolean toggle()
	{
		if (plugin.getConfig().getBoolean("enabled"))
		{
			plugin.getConfig().set("enabled", false);
		}
		else
		{
			plugin.getConfig().set("enabled", true);
		}
		return plugin.getConfig().getBoolean("enabled");
	}
	
	void handleCooldownCommand(String[] args, CommandSender sender, String label)
	{
		if (args.length < 2)
		{
			checkCooldown(sender);
			return;
		}
		try 
		{
			int cooldown = Integer.parseInt(args[1]);
			plugin.getConfig().set("cooldown", cooldown);
			sender.sendMessage(ChatColor.GREEN + "✔ " + ChatColor.GRAY + "Cooldown set to: " + ChatColor.AQUA + cooldown);
			
		}
		catch (NumberFormatException e)
		{
			sender.sendMessage(ChatColor.RED + "(!) " + ChatColor.GRAY + "Invalid number of ticks: " + args[1]);
		}
	}
	void setCooldown(String[] args, CommandSender sender, String label)
	{
		if (args.length < 2)
		{
			sendHelp(sender, label);
			return;
		}
		try 
		{
			int cooldown = Integer.parseInt(args[1]);
			plugin.getConfig().set("cooldown", cooldown);
			sender.sendMessage(ChatColor.GREEN + "✔ " + ChatColor.GRAY + "Cooldown set to: " + ChatColor.AQUA + cooldown);
			
		}
		catch (NumberFormatException e)
		{
			sender.sendMessage(ChatColor.RED + "(!) " + ChatColor.GRAY + "Invalid number of ticks: " + args[1]);
		}
	}
	void checkCooldown(CommandSender sender)
	{
		boolean enabled = plugin.getConfig().getBoolean("enabled");
		ChatColor color = ChatColor.WHITE;
		if (enabled)
		{
			color = ChatColor.GREEN;
		}
		else
		{
			color = ChatColor.RED;
		}
		sender.sendMessage(color + "(" + booleanToEnabled(enabled) + ")" + ChatColor.GRAY + " Cooldown: " + plugin.getConfig().getInt("cooldown") + " ticks");
	}
	String booleanToEnabled(boolean isTrue)
	{
		if (isTrue)
		{
			return "Enabled";
		}
		else
		{
			return "Disabled";
		}
	}
	
	void sendHelp(CommandSender sender, String label)
	{
		StringBuilder message = new StringBuilder();
   	 	message	.append(ChatColor.AQUA + "   │  " + ChatColor.AQUA + "InfiniteArrowWithCooldown " + ChatColor.WHITE + "Commands \n")
       	    	.append(ChatColor.AQUA + "   │  " + ChatColor.GRAY + label + " ‣ " + ChatColor.WHITE + "Toggles Enable/Disable the plugin\n")
       	    	.append(ChatColor.AQUA + "   │  " + ChatColor.GRAY + label + " toggle ‣ " + ChatColor.WHITE + "Toggles Enable/Disable the plugin\n")
       	    	.append(ChatColor.AQUA + "   │  " + ChatColor.GRAY + label + " cooldown <cooldown> ‣ " + ChatColor.WHITE + "Sets the cooldown in ticks\n")
        	    .append(ChatColor.AQUA + "   │  " + ChatColor.GRAY + ChatColor.ITALIC + "⤷ Aliases ‣ set, setcooldown, cooldown.\n")
       	    	.append(ChatColor.AQUA + "   │  " + ChatColor.GRAY + label + " cooldown ‣ " + ChatColor.WHITE + "Shows the cooldown currently set\n")
        	    .append(ChatColor.AQUA + "   │  " + ChatColor.GRAY + ChatColor.ITALIC + "⤷ Aliases ‣ check, checkcooldown or cooldown.\n")
       	    	.append(ChatColor.AQUA + "   │  " + ChatColor.GRAY + label + " enable ‣ " + ChatColor.WHITE + "Enables the plugin\n")
       	    	.append(ChatColor.AQUA + "   │  " + ChatColor.GRAY + label + " disable ‣ " + ChatColor.WHITE + "Disables the plugin\n")
       	    	.append(ChatColor.AQUA + "   │  " + ChatColor.GRAY + label + " ? ‣ " + ChatColor.WHITE + "Shows this message\n")
       	    	.append(" ");
       	
       	sender.sendMessage(message.toString());
	}

}
