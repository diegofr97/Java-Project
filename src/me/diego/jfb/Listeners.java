package me.diego.jfb;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Listeners implements Listener {
	
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		
		Player p =e.getPlayer();
		p.sendMessage("Bem vindo ao servidor Rlife, " +p.getName() + "!");
		
	}

}
