package me.diego.jfb;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.ArrayList;


public class Main extends JavaPlugin{
	
	ArrayList<String> cargoTM = new ArrayList();
	BukkitScheduler scheduler = getServer().getScheduler();
	
	public void onEnable() {
		
		Bukkit.getConsoleSender().sendMessage("[Rlife] Plugin ativado com sucesso!");
		Bukkit.getPluginManager().registerEvents(new Listeners(),this);
		Bukkit.getPluginManager().registerEvents(new PluginEventHandler(), this);
		saveDefaultConfig();
		saveConfig();	
		
	}
	      
	
	@Override
	public boolean onCommand(final CommandSender sender,final Command command,final String label,final String[] args) {
		
		if(command.getName().equalsIgnoreCase("cimento")) {
			
			if(!(sender instanceof Player)) {
				sender.sendMessage("Esse comando s� pode ser executado por Jogadores.");
			} else {
				
				if(!sender.hasPermission("teste.cimento")) {
					
					sender.sendMessage("Voc� n�o tem permiss�o.");
					return true;					
				}
				Player p = (Player) sender;
				final ItemStack item =  new ItemStack(Material.SULPHUR, 1);
				final ItemMeta meta = item.getItemMeta();
				meta.setDisplayName("Cimento");
				item.setItemMeta(meta);
				p.getInventory().addItem(item);
				p.updateInventory();
				
								
			}
			
		}
		
		if(command.getName().equalsIgnoreCase("pegar")) {
			
			Player p = (Player)sender;
			p.setCanPickupItems(true);
			
		}
		
		if(command.getName().equalsIgnoreCase("npegar")) {
			
			Player p = (Player)sender;
			p.setCanPickupItems(false);
			
		}
		
		int taskID = 0;
	
		if (command.getName().equalsIgnoreCase("trabalho")) {
			Player p = (Player) sender;
			p.sendMessage("RLife: " + getConfig().getString("trabalho"));
		}
		
		if (command.getName().equalsIgnoreCase("minerador")) {
			Player p = (Player) sender;
			if(cargoTM.contains(p.getName())){
				p.sendMessage("RLife: Ainda n�o � permitido fazer a troca de cargo");
			}else{
				taskID = scheduler.scheduleSyncDelayedTask(this, new Runnable(){
					public void run(){
						p.sendMessage("RLife: Aguarde para usar esse comando.");
						cargoTM.remove(p.getName());
					}
				},50000);
				p.sendMessage("RLife: Voc� � um minerador agora.");
				getServer().dispatchCommand(getServer().getConsoleSender(),
						"pex user " + p.getName() + " group set minerador");
				ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
				ItemStack cobb = new ItemStack(Material.COBBLESTONE, 3);
				ItemStack stick = new ItemStack(Material.STICK, 2);
				BookMeta bm = (BookMeta)book.getItemMeta();
				bm.setTitle("Minerador Manual");
				bm.setAuthor("Rlife");
				bm.setDisplayName("Manual do Minerador");
				bm.setPages("Bem vindo a mineradora RLife\n\n Este manual cont�m tudo sobre minera��o no server RLife.\n Procure a loja de ferramentas no centro da cidade, e com o material recebido, pe�a ao ferreiro fabricar uma picareta.","A mina fica a norte da cidade, depois de uma ponte.\n Venda o material na sa�da da mineradora.","Porque o trabalho dos mineradores � importante para a cidade?\n\n Cada mineiro ter� como finalidade abastecer a cidade com o material pego nas minas, movimentando assim a economia da cidade.");
				book.setItemMeta(bm);
				p.getInventory().addItem(book);
				p.getInventory().addItem(cobb);
				p.getInventory().addItem(stick);
				cargoTM.add(p.getName());
			}
		}
		
		if (command.getName().equalsIgnoreCase("tijoleiro")) {
			Player p = (Player) sender;
			if(cargoTM.contains(p.getName())){
				p.sendMessage("RLife: Ainda n�o � permitido fazer a troca de cargo");
			}else{
				taskID = scheduler.scheduleSyncDelayedTask(this, new Runnable(){
					public void run(){
						p.sendMessage("RLife: O cooldown da troca de cargo acabou.");
						cargoTM.remove(p.getName());
					}
				},50000);
				p.sendMessage("RLife: Voc� � um Tijoleiro agora.");
				getServer().dispatchCommand(getServer().getConsoleSender(),
						"pex user " + p.getName() + " group set tijoleiro");
				ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
				BookMeta bm = (BookMeta)book.getItemMeta();
				bm.setTitle("Tijoleiro Manual");
				bm.setAuthor("Rlife");
				bm.setDisplayName("Manual do Tijoleiro");
				bm.setPages("Bem vindo a F�brica de tijolos RLife\n\n Este manual cont�m tudo sobre fabrica��o de tijolos no server RLife.\n A f�brica de tijolos fica a oeste da cidade.","No primeiro andar da f�brica tem 3 m�quinas de argila, para funciona-las voc� tera que colocar o combust�vel que � comprado nas placas no mesmo andar.\n Cada m�quina produzir� em m�dia por combust�vel, 20 argilas.","Com as argilas em m�os, des�a ao t�rreo e usando o combust�vel, ou carv�o(mais barato) que pode ser adquirido na mineradora, queime as argilas nas fornalhas, gerando os tijolos que podem ser vendidos na loja de materiais de contru��o(Centro)","Porque o trabalho dos tijoleiros � importante para a cidade?\n\n Cada tijoleiro ter� como finalidade abastecer a cidade com o material pego na f�brica de tijolo, movimentando assim a economia da cidade.","Fale com o dono da loja de materiais de contru��o, ele pode lhe vender um livro que cont�m o crafting de todos os materiais dispon�veis na loja dele e na cidade.");
				book.setItemMeta(bm);
				p.getInventory().addItem(book);
				cargoTM.add(p.getName());
			}
		}
		
		if (command.getName().equalsIgnoreCase("cimenteiro")) {
			Player p = (Player) sender;
			if(cargoTM.contains(p.getName())){
				p.sendMessage("RLife: Ainda n�o � permitido fazer a troca de cargo");
			}else{
				taskID = scheduler.scheduleSyncDelayedTask(this, new Runnable(){
					public void run(){
						p.sendMessage("RLife: O cooldown da troca de cargo acabou.");
						cargoTM.remove(p.getName());
					}
				},50000);
				p.sendMessage("RLife: Voc� � um Cimenteiro agora.");
				getServer().dispatchCommand(getServer().getConsoleSender(),
						"pex user " + p.getName() + " group set cimenteiro");
				cargoTM.add(p.getName());
			}
		}
			
		return false;
	}
		

}
