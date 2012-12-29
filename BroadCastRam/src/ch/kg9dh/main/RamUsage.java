package ch.kg9dh.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class RamUsage extends JavaPlugin{

	
	
	public void onDisable() {		
	}
	
	public void onEnable() {
	}
	
	@Override
 	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){	
		
		Runtime runtime = Runtime.getRuntime();
		
		long total = runtime.totalMemory();
		long free = runtime.freeMemory();	
		long used = total-free;
		
		int tm = (int) total/1048576;
		int fm = (int) free/1048576;
		int um = (int) used/1048576;
		
		if(cmd.getName().equalsIgnoreCase("ram")){
			Bukkit.getServer().broadcastMessage(ChatColor.YELLOW+"[Total: "+tm+"MB]"+ChatColor.RED+" [Used: "+um+"MB] "+ChatColor.GREEN+"[Free: "+fm+"MB]");
			Bukkit.getServer().broadcastMessage(ChatColor.RED+perc(tm,um)/*+ChatColor.GREEN+perc(tm,fm)*/);
		}
		
		return false;
	}
	
	public String perc(int total, int part){
		String s = ChatColor.RED+"";
		int x = 100*part/total;
		int z = 110-x;
		for(int y=0; y<=x; y++){
			s = s+"|";
		}
		s=s+ChatColor.GREEN;
		for(int y=0; y<=z; y++){
			s = s+"|";
		}
		return s;
	}
	
}
