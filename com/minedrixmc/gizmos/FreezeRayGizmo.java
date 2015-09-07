package com.minedrixmc.gizmos;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class FreezeRayGizmo implements Listener {
	
	// FINISHED
	
	public static HashMap<Player, Double> cooldowns = new HashMap<Player, Double>();
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		
		Player player = e.getPlayer();
		
		if (player.getItemInHand().getType() == Material.DIAMOND_BARDING) {
			
			e.setCancelled(true);
			
			if (cooldowns.get(player) <= 0) {
				
				if (isLookingAtAPlayer(player)) {
					
					final Player affected = playerLookingAt(player);
					
					affected.getLocation().getBlock().setType(Material.ICE);
					affected.getLocation().getBlock().getLocation().add(0, 1, 0).getBlock().setType(Material.ICE);
					
					player.sendMessage(colorize("&a&lGIZMO " + Gizmos.getArrow() + "&r&e You used &bFreeze Ray&e."));
					
					cooldowns.put(player, 10.0);
					
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Gizmos.getInstance(), new Runnable() {
						
			            @Override
			            public void run() {
			            	
			            	affected.getLocation().getBlock().setType(Material.AIR);
							affected.getLocation().getBlock().getLocation().add(0, 1, 0).getBlock().setType(Material.AIR);

			            }
			            
			        }, 2 * 20L);
					
				}
				
				else {
					
					player.sendMessage(colorize("&a&lGIZMO " + Gizmos.getArrow() + "&r&e You must be looking at a player to use &bFreeze Ray&e!"));
					
				}
				
			}
			
			else {
				
				player.sendMessage(colorize("&a&lCOOLDOWN " + Gizmos.getArrow() + "&r&e You must wait &b" + round(cooldowns.get(player)) + "&e seconds to use &bFreeze Ray&e."));
				
			}
			
		}
		
	}
	
	public static boolean isLookingAtAPlayer(Player player) {
		
		boolean result = false;
		
		for (Player p : Bukkit.getOnlinePlayers()) {
			
			if (player.getTargetBlock((Set<Material>) null, 256).getLocation() == p.getLocation().getBlock().getLocation() ||
					player.getTargetBlock((Set<Material>) null, 256).getLocation().add(0, 1, 0) == p.getLocation().getBlock().getLocation().add(0, 1, 0)) {
				
				result = true;
				break;
				
			}
			
			else {
				
				continue;
				
			}
			
		}
		
		return result;
		
	}
	
	public static Player playerLookingAt(Player player) {
		
		Player result = null;
		
		for (Player p : Bukkit.getOnlinePlayers()) {
			
			if (player.getTargetBlock((Set<Material>) null, 256).getLocation() == p.getLocation().getBlock().getLocation() ||
					player.getTargetBlock((Set<Material>) null, 256).getLocation().add(0, 1, 0) == p.getLocation().getBlock().getLocation().add(0, 1, 0)) {
				
				result = p;
				break;
				
			}
			
			else {
				
				continue;
				
			}
			
		}
		
		return result;
		
	}
	
    public static double round(double d) {
    	
        DecimalFormat twoDForm = new DecimalFormat("#.#");
        return Double.valueOf(twoDForm.format(d));
        
    }
	
	public static String colorize(String s) { return ChatColor.translateAlternateColorCodes('&', s); }

}
