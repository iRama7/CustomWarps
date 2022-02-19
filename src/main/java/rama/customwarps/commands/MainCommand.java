package rama.customwarps.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import rama.customwarps.CustomWarps;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

public class MainCommand implements CommandExecutor {
    private CustomWarps plugin;

    public MainCommand(CustomWarps plugin){
        this.plugin = plugin;
    }



    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3[&cCustomWarps&3] &7/customwarps setwarp (nombre)"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3[&cCustomWarps&3] &7/customwarps warp (nombre) (jugador) [consola]"));
        }else if(args[0].equalsIgnoreCase("setwarp") && sender.hasPermission("customwarps.*")){
            FileConfiguration config = plugin.getConfig();
            Player player = Bukkit.getPlayer(sender.getName());
            Location playerLoc = player.getLocation();
            Double x = Double.valueOf(playerLoc.getBlockX());
            Double y = Double.valueOf(playerLoc.getBlockY());
            Double z = Double.valueOf(playerLoc.getBlockZ());
            float pitch = playerLoc.getPitch();
            float yaw = playerLoc.getYaw();
            String world = player.getWorld().getName();
            String warpName = args[1];

            config.set("warps."+"."+warpName, null);
            config.set("warps."+warpName+"."+"x", x);
            config.set("warps."+warpName+"."+"y", y);
            config.set("warps."+warpName+"."+"z", z);
            config.set("warps."+warpName+"."+"world", world);
            config.set("warps."+warpName+"."+"pitch", pitch);
            config.set("warps."+warpName+"."+"yaw", yaw);
            plugin.saveConfig();
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3[&cCustomWarps&3] &7Has definido un nuevo warp." ));
        }else if(args[0].equalsIgnoreCase("warp")){
            if(!(sender instanceof Player)){
                FileConfiguration config = plugin.getConfig();
                String warpName = args[1];
                String playerName = args[2];
                Player player = Bukkit.getPlayer(playerName);

                if(!config.isSet("warps."+warpName)){
                    sender.sendMessage("Ese warp no existe.");
                }

                Double x = config.getDouble("warps."+warpName+".x");
                Double y = config.getDouble("warps."+warpName+".y");
                Double z = config.getDouble("warps."+warpName+".z");
                float pitch = config.getInt("warps."+warpName+".pitch");
                float yaw = config.getInt("warps."+warpName+".yaw");
                String worldName = config.getString("warps."+warpName+".world");
                World world = Bukkit.getWorld(worldName);
                Location warpLoc = new Location(world, x, y, z, yaw, pitch);


                if(warpLoc.isWorldLoaded()){
                    player.teleport(warpLoc);

                }else{
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cEl &eend &cse encuentra CERRADO!"));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cAbrirá el viernes a las 23:59"));
                }
            }else{
                sender.sendMessage("Ese comando solo puede usarse desde la consola.");
            }
        }else if(args[0].equalsIgnoreCase("setpvp") && sender.hasPermission("customwarps.*")){
            if(sender instanceof Player){
                FileConfiguration config = plugin.getConfig();
                Player player = Bukkit.getPlayer(sender.getName());
                Location playerLoc = player.getLocation();
                Double x = Double.valueOf(playerLoc.getBlockX());
                Double y = Double.valueOf(playerLoc.getBlockY());
                Double z = Double.valueOf(playerLoc.getBlockZ());
                float pitch = playerLoc.getPitch();
                float yaw = playerLoc.getYaw();
                String world = player.getWorld().getName();

                config.set("warps.pvp", null);
                config.set("warps.pvp.x", x);
                config.set("warps.pvp.y", y);
                config.set("warps.pvp.z", z);
                config.set("warps.pvp.world", world);
                config.set("warps.pvp.pitch", pitch);
                config.set("warps.pvp.yaw", yaw);
                plugin.saveConfig();
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3[&cCustomWarps&3] &7Has definido el lugar de PvP." ));
            }
        }else if(args[0].equalsIgnoreCase("setpractice") && sender.hasPermission("customwarps.*")){
            if(sender instanceof Player){
                FileConfiguration config = plugin.getConfig();
                Player player = Bukkit.getPlayer(sender.getName());
                Location playerLoc = player.getLocation();
                Double x = Double.valueOf(playerLoc.getBlockX());
                Double y = Double.valueOf(playerLoc.getBlockY());
                Double z = Double.valueOf(playerLoc.getBlockZ());
                float pitch = playerLoc.getPitch();
                float yaw = playerLoc.getYaw();
                String world = player.getWorld().getName();

                config.set("warps.practice", null);
                config.set("warps.practice.x", x);
                config.set("warps.practice.y", y);
                config.set("warps.practice.z", z);
                config.set("warps.practice.world", world);
                config.set("warps.practice.pitch", pitch);
                config.set("warps.practice.yaw", yaw);
                plugin.saveConfig();
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3[&cCustomWarps&3] &7Has definido el lugar de Practice." ));
            }
        }
        return false;
    }
}
