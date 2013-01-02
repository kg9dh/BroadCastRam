package ch.kg9dh.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TicksPerSecond implements CommandExecutor{
	
	public RamUsage plugin;
	
	public TicksPerSecond(RamUsage instance) {
        plugin = instance;
    }
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		final Player p = (Player) sender;
		
		if(command.getName().equalsIgnoreCase("tps")){
			
			final long time = System.currentTimeMillis();
			
			Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
											
			    @Override
			    public void run() {
			    	
			        int tpsResult = (int)(100 / ((System.currentTimeMillis() - time) / 1000));
			        if(plugin._player){
			        	p.sendMessage(ChatColor.DARK_RED + "["+plugin.label+"] " + ChatColor.GREEN + "The servers TPS is " + tpsResult);
			        }else if(plugin._server){
			        	Bukkit.getServer().broadcastMessage(ChatColor.DARK_RED + "["+plugin.label+"] " + ChatColor.GREEN + "The servers TPS is " + tpsResult);
			        }
			    }
			}, 100);
			
			return true;
		}
		return false;
	}
		
}