package rama.customwarps.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import rama.customwarps.CustomWarps;

public class PvPCommand implements CommandExecutor {

    private CustomWarps plugin;

    public PvPCommand(CustomWarps plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            FileConfiguration config = plugin.getConfig();

            Player player = (Player) sender;

            Double x = config.getDouble("warps.pvp.x");
            Double y = config.getDouble("warps.pvp.y");
            Double z = config.getDouble("warps.pvp.z");
            float pitch = config.getInt("warps.pvp.pitch");
            float yaw = config.getInt("warps.pvp.yaw");
            String worldName = config.getString("warps.pvp.world");
            World world = Bukkit.getWorld(worldName);
            Location pvpLoc = new Location(world, x, y, z, yaw, pitch);

            player.teleport(pvpLoc);
        }
        return false;
    }
}
