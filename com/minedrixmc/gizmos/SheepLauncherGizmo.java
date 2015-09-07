package com.minedrixmc.gizmos;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;

public class SheepLauncherGizmo implements Listener {
	
	// FINISHED
	
	public static HashMap<Player, Double> cooldowns = new HashMap<Player, Double>();
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		
		Player player = e.getPlayer();
		
		if (player.getItemInHand().getType() == Material.IRON_BARDING) {
			
			if (cooldowns.get(player) <= 0) {
				
				player.sendMessage(colorize("&a&lGIZMO " + Gizmos.getArrow() + "&r&e You used &bSheep Launcher&e."));
				
		        final Sheep sheep = (Sheep)player.getWorld().spawnEntity(player.getLocation().add(0.0D, 2.0D, 0.0D), EntityType.SHEEP);
		              
		        sheep.setVelocity(player.getLocation().getDirection().multiply(2));
		        sheep.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 80, 100));
		        sheep.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 80, 100));
		        sheep.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 80, 100));
		        final BukkitScheduler scheduler = Bukkit.getScheduler();
		        
		        final int taskrd = scheduler.scheduleSyncRepeatingTask(Gizmos.getInstance(), new Runnable() {
		        	
		        	@SuppressWarnings("deprecation")
					@Override
		        	public void run() {
		        		
		        		sheep.setColor(DyeColor.getByData((byte)new Random().nextInt(15)));
		                    
		        	}
		                  
		        }, 0L, 0L);
		              
		        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Gizmos.getInstance(), new Runnable() {
		        	
		        	@Override
		        	public void run() {
		        		
		        		Location sloc = sheep.getLocation();
		        		World sworld = sloc.getWorld();
		        		sworld.playEffect(sloc, Effect.POTION_BREAK, 1, 1);
		        		sworld.playEffect(sloc, Effect.POTION_BREAK, 2, 2);
		        		sworld.playEffect(sloc, Effect.POTION_BREAK, 3, 3);
		        		sworld.playEffect(sloc, Effect.POTION_BREAK, 4, 4);
		        		sworld.playEffect(sloc, Effect.POTION_BREAK, 5, 5);
		        		sworld.playEffect(sloc, Effect.POTION_BREAK, 6, 6);
		        		sworld.playEffect(sloc, Effect.POTION_BREAK, 7, 7);
		        		sworld.playEffect(sloc, Effect.POTION_BREAK, 8, 8);
		        		sworld.playEffect(sloc, Effect.POTION_BREAK, 9, 9);
		        		sworld.playEffect(sloc, Effect.POTION_BREAK, 10, 10);
		        		sworld.playEffect(sloc, Effect.POTION_BREAK, 11, 11);
		        		sworld.playEffect(sloc, Effect.POTION_BREAK, 12, 12);
		        		sworld.playEffect(sloc, Effect.POTION_BREAK, 13, 13);
		        		sworld.playEffect(sloc, Effect.POTION_BREAK, 14, 14);
		        		sworld.playEffect(sloc, Effect.POTION_BREAK, 15, 15);
		        		sworld.playEffect(sloc, Effect.MOBSPAWNER_FLAMES, 5);
		        		sheep.remove();
		        		scheduler.cancelTask(taskrd);
		        	}
		        	
		        }, 25L);
		        
			}
			
			else {
				
				player.sendMessage(colorize("&a&lCOOLDOWN " + Gizmos.getArrow() + "&r&e You must wait &b" + round(cooldowns.get(player)) + "&e seconds to use &bSheep Launcher&e."));
				
			}
			
		}
		
	}
	
    public static double round(double d) {
    	
        DecimalFormat twoDForm = new DecimalFormat("#.#");
        return Double.valueOf(twoDForm.format(d));
        
    }
	
	public static String colorize(String s) { return ChatColor.translateAlternateColorCodes('&', s); }

}
