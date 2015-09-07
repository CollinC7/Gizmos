package com.minedrixmc.gizmos;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class GizmoCommand implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		return true;
		
	}
	
	public static String colorize(String s) { return ChatColor.translateAlternateColorCodes('&', s); }

}
