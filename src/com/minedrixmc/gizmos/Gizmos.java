package com.minedrixmc.gizmos;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Gizmos extends JavaPlugin {
	
	static JavaPlugin instance;
	static String arrow = "\u2794";
	
	@SuppressWarnings("deprecation")
	@Override
	public void onEnable() {
		
		instance = this;
		
		getCommand("gizmo").setExecutor(new GizmoCommand());
		
		getServer().getPluginManager().registerEvents(new CactusColaGizmo(), this);
		getServer().getPluginManager().registerEvents(new FireworkGizmo(), this);
		getServer().getPluginManager().registerEvents(new FreezeRayGizmo(), this);
		getServer().getPluginManager().registerEvents(new SheepLauncherGizmo(), this);
		getServer().getPluginManager().registerEvents(new SlimeShoesGizmo(), this);
		
		FreezeRayGizmo.forbidden.add(Material.WOOL);
		FreezeRayGizmo.forbidden.add(Material.LOG);
		FreezeRayGizmo.forbidden.add(Material.getMaterial(31));
		FreezeRayGizmo.forbidden.add(Material.LADDER);
		FreezeRayGizmo.forbidden.add(Material.VINE);
		FreezeRayGizmo.forbidden.add(Material.RED_ROSE);
		FreezeRayGizmo.forbidden.add(Material.YELLOW_FLOWER);
		FreezeRayGizmo.forbidden.add(Material.SIGN);
		FreezeRayGizmo.forbidden.add(Material.SIGN_POST);
		FreezeRayGizmo.forbidden.add(Material.IRON_DOOR);
		FreezeRayGizmo.forbidden.add(Material.IRON_DOOR_BLOCK);
		FreezeRayGizmo.forbidden.add(Material.WOODEN_DOOR);
		FreezeRayGizmo.forbidden.add(Material.WOOD_DOOR);
		FreezeRayGizmo.forbidden.add(Material.REDSTONE_TORCH_ON);
		FreezeRayGizmo.forbidden.add(Material.REDSTONE_TORCH_OFF);
		FreezeRayGizmo.forbidden.add(Material.PISTON_BASE);
		FreezeRayGizmo.forbidden.add(Material.PISTON_EXTENSION);
		FreezeRayGizmo.forbidden.add(Material.PISTON_MOVING_PIECE);
		FreezeRayGizmo.forbidden.add(Material.PISTON_STICKY_BASE);
		FreezeRayGizmo.forbidden.add(Material.CACTUS);
		FreezeRayGizmo.forbidden.add(Material.SUGAR_CANE_BLOCK);
		FreezeRayGizmo.forbidden.add(Material.CHEST);
		
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			
            @Override
            public void run() {
            	
            	for (Player player : Bukkit.getOnlinePlayers()) {
            	
            		if (!(CactusColaGizmo.cooldowns.get(player) <= 0)) {
                	
            			CactusColaGizmo.cooldowns.put(player, CactusColaGizmo.cooldowns.get(player) - 0.1);
                	
                	}
            		
            		else {
            			
            			CactusColaGizmo.cooldowns.put(player, 0.0);
            			
            		}
            		
            		if (!(FreezeRayGizmo.cooldowns.get(player) <= 0)) {
                    	
            			FreezeRayGizmo.cooldowns.put(player, FreezeRayGizmo.cooldowns.get(player) - 0.1);
                	
                	}
            		
            		else {
            			
            			FreezeRayGizmo.cooldowns.put(player, 0.0);
            			
            		}
            		
            		if (!(SheepLauncherGizmo.cooldowns.get(player) <= 0)) {
                    	
            			SheepLauncherGizmo.cooldowns.put(player, SheepLauncherGizmo.cooldowns.get(player) - 0.1);
                	
                	}
            		
            		else {
            			
            			SheepLauncherGizmo.cooldowns.put(player, 0.0);
            			
            		}
            		
            		if (!(SlimeShoesGizmo.cooldowns.get(player) <= 0)) {
                    	
            			SlimeShoesGizmo.cooldowns.put(player, SlimeShoesGizmo.cooldowns.get(player) - 0.1);
                	
                	}
            		
            		else {
            			
            			SlimeShoesGizmo.cooldowns.put(player, 0.0);
            			
            		}
                	
            	}
            	
            }
            
        }, 0L, 2L);
		
	}
	
	@Override
	public void onDisable() {
		
		
		
	}
	
	public static String getArrow() { return arrow; }
	
	public static String colorize(String s) { return ChatColor.translateAlternateColorCodes('&', s); }
	
	public static JavaPlugin getInstance() {
		
		return instance;
		
	}

}
