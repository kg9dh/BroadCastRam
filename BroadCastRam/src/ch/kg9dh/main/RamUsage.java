package ch.kg9dh.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class RamUsage extends JavaPlugin{

	public TicksPerSecond ticksps;
	
	public boolean _player;
	public boolean _server;
	
	public boolean _usage;
	public boolean _graphics;
	public boolean _percent;
	
	public String label;
	
	public void onDisable() {		
	}
	
	public void onEnable() {
		
		loadConfig();
		
		_player = this.getConfig().getBoolean("player-output");
		_server = this.getConfig().getBoolean("server-output");
		
		_usage = this.getConfig().getBoolean("usage-output");
		_graphics = this.getConfig().getBoolean("graphics-output");
		_percent = this.getConfig().getBoolean("percent-output");
		
		label = this.getConfig().getString("label");
		
		ticksps = new TicksPerSecond(this);
		getCommand("tps").setExecutor(ticksps);
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
		
		int percent = 100*um/tm;
		
		if(cmd.getName().equalsIgnoreCase("ram")){
			if(_player){
				if(_usage){
					sender.sendMessage(ChatColor.YELLOW+"[Total: "+tm+"MB]"+ChatColor.RED+" [Used: "+um+"MB] "+ChatColor.GREEN+"[Free: "+fm+"MB]");
				} if(_graphics){
					sender.sendMessage(ChatColor.RED+perc(tm,um)+ChatColor.YELLOW+" ["+percent+"%] Are used.");
				} if(_percent){
					sender.sendMessage(ChatColor.DARK_RED + "[Baconia] " + ChatColor.GREEN + "The server is using " + percent + "% of it's RAM.");
				}
			}else if(_server){
				if(_usage){
					Bukkit.getServer().broadcastMessage(ChatColor.YELLOW+"[Total: "+tm+"MB]"+ChatColor.RED+" [Used: "+um+"MB] "+ChatColor.GREEN+"[Free: "+fm+"MB]");
				} if(_graphics){
					Bukkit.getServer().broadcastMessage(ChatColor.RED+perc(tm,um)+ChatColor.YELLOW+" ["+percent+"%] Are used.");
				} if(_percent){
					Bukkit.getServer().broadcastMessage(ChatColor.DARK_RED + "["+label+"] " + ChatColor.GREEN + "The server is using " + percent + "% of it's RAM.");
				}
			}
		}
		
		return false;
	}
	
	public String perc(int total, int part){
		String s = ChatColor.RED+"";
		int x = 70*part/total;
		int z = 70-x;
		for(int y=0; y<=x; y++){
			s = s+"|";
		}
		s=s+ChatColor.GREEN;
		for(int y=0; y<=z; y++){
			s = s+"|";
		}
		return s;
	}
	
	private void loadConfig() {
		FileConfiguration cfg = this.getConfig();
		cfg.options().copyDefaults(true);
		this.saveConfig();		
	}
	
}
