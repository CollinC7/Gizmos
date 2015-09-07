package com.minedrixmc.gizmos;

import java.text.DecimalFormat;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CactusColaGizmo implements Listener {
	
	// FINISHED
	
	public static HashMap<Player, Double> cooldowns = new HashMap<Player, Double>();
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		
		Player player = e.getPlayer();
		
		if (player.getItemInHand().getType() == Material.CACTUS) {
			
			e.setCancelled(true);
			
			if (cooldowns.get(player) <= 0) {
				
				player.sendMessage(colorize("&a&lGIZMO " + Gizmos.getArrow() + "&r&e You used &bCactus Cola&e."));
				
				PotionEffect speed = new PotionEffect(PotionEffectType.SPEED, 5, 2, false, false);
				
				player.addPotionEffect(speed);
				
				cooldowns.put(player, 15.0);
				
			}
			
			else {
				
				player.sendMessage(colorize("&a&lCOOLDOWN " + Gizmos.getArrow() + "&r&e You must wait &b" + round(cooldowns.get(player)) + "&e seconds to use &bCactus Cola&e."));
				
			}
			
		}
		
	}
	
    public static double round(double d) {
    	
        DecimalFormat twoDForm = new DecimalFormat("#.#");
        return Double.valueOf(twoDForm.format(d));
        
    }
	
	public static String colorize(String s) { return ChatColor.translateAlternateColorCodes('&', s); }

}
