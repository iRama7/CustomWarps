package rama.customwarps;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import rama.customwarps.commands.MainCommand;
import rama.customwarps.commands.PracticeCommand;
import rama.customwarps.commands.PvPCommand;

import java.io.File;

public final class CustomWarps extends JavaPlugin {

    public String rutaConfig;

    @Override
    public void onEnable() {
        registerCmds();
        registerConfig();
        sendLog();
    }

    @Override
    public void onDisable() {

    }

    public void registerCmds(){
        this.getCommand("customwarps").setExecutor(new MainCommand(this));
        this.getCommand("pvp").setExecutor(new PvPCommand(this));
        this.getCommand("practice").setExecutor(new PracticeCommand(this));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&3[&cCustomWarps&3] &eCargando comandos..."));
    }

    public void registerConfig(){
        File config = new File(this.getDataFolder(),"config.yml");
        rutaConfig = config.getPath();
        if(!config.exists()){
            this.getConfig().options().copyDefaults(true);
            saveDefaultConfig();
        }
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&3[&cCustomWarps&3] &eCargando config.yml..."));
    }
    public void sendLog(){
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&3[&cCustomWarps&3] &eEl plugin ha cargado correctamente. &7 -por ImRama"));
    }


}
