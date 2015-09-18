package com.minedrixmc.gizmos;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class FreezeRayGizmo implements Listener {
	
	public static HashMap<Player, Double> cooldowns = new HashMap<Player, Double>();
	public static ArrayList<Material> forbidden = new ArrayList<Material>();
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		
		Player player = e.getPlayer();
		
		if (player.getItemInHand().getType() == Material.DIAMOND_BARDING) {
			
			e.setCancelled(true);
			
			if (cooldowns.get(player) <= 0) {
				
				final Block block = player.getTargetBlock((Set<Material>) null, 256);
				
				final Material block1 = block.getLocation().add(0, 1, 0).getBlock().getType();
				final Material block2 = block.getLocation().add(0, 2, 0).getBlock().getType();
				
				if (isForbiddenMaterial(block1)) {
					
					player.sendMessage(colorize("&a&lGIZMO " + Gizmos.getArrow() + "&r&e You cannot use &bFreeze Ray&e while looking at &b" + toString(block1) + "&e."));					
					return;
					
				}
				
				if (isForbiddenMaterial(block2)) {
					
					player.sendMessage(colorize("&a&lGIZMO " + Gizmos.getArrow() + "&r&e You cannot use &bFreeze Ray&e while looking at &b" + toString(block2) + "&e."));					
					return;
					
				}
					
				block.getLocation().add(0, 1, 0).getBlock().setType(Material.ICE);
				block.getLocation().add(0, 2, 0).getBlock().setType(Material.ICE);
					
				player.sendMessage(colorize("&a&lGIZMO " + Gizmos.getArrow() + "&r&e You used &bFreeze Ray&e."));
					
				cooldowns.put(player, 10.0);
					
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Gizmos.getInstance(), new Runnable() {
						
			        @Override
			        public void run() {
			            
			        	block.getLocation().add(0, 1, 0).getBlock().setType(block1);
						block.getLocation().add(0, 2, 0).getBlock().setType(block2);

			        }
			            
			    }, 4 * 20L);
					
			}
			
			else {
				
				player.sendMessage(colorize("&a&lCOOLDOWN " + Gizmos.getArrow() + "&r&e You must wait &b" + round(cooldowns.get(player)) + "&e seconds to use &bFreeze Ray&e."));
				
			}
			
		}
			
	}
	
	public boolean isForbiddenMaterial(Material m) {
		
		if (forbidden.contains(m)) {
			
			return false;
			
		}
		
		else {
			
			return true;
			
		}
		
	}
	
	public String toString(Material m) {
		
		String s = m.toString().replace("_", " ").toLowerCase();
		
		StringBuilder output = new StringBuilder();
		
		for (int i = 0; i < s.length(); i++) {
			
			char letter = s.charAt(i);
			
			if (s.charAt(i - 1) == ' ' || i == 0) {
				
				output.append(Character.toUpperCase(letter));
				
			}
			
			else {
				
				output.append(letter);
				
			}
			
		}
		
		return s;
		
	}
	
    public static double round(double d) {
    	
        DecimalFormat twoDForm = new DecimalFormat("#.#");
        return Double.valueOf(twoDForm.format(d));
        
    }
	
	public static String colorize(String s) { return ChatColor.translateAlternateColorCodes('&', s); }
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		
		if (cooldowns.get(e.getPlayer()) == null) {
			
			cooldowns.put(e.getPlayer(), 0.0);
			
		}
		
	}

}
