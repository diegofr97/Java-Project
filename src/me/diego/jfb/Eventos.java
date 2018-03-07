package me.diego.jfb;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;


public class PluginEventHandler implements Listener {
		
	@EventHandler
	void quebrarStone(BlockBreakEvent event) {
		String stone = "LOG";
		Material blockType = event.getBlock().getType();
		if(blockType == Material.getMaterial(stone)) {
			event.setDropItems(false);
			event.getPlayer().getInventory().addItem(new ItemStack(Material.FLINT, 1));
			
		}
		
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
	    if(e.getBlock().getType() == Material.STONE) {
	    	if (e.getBlock().getLocation() == e.getPlayer().getEyeLocation()) {
	    		e.isCancelled();	    		
	    	} else {
	    		
	    		e.setDropItems(false);
	        	e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.FLINT, 1));
	    		
	    	}
	    }
	}

}
