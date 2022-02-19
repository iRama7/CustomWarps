package rama.customwarps.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import rama.customwarps.CustomWarps;

public class PracticeCommand implements CommandExecutor {

    private CustomWarps plugin;

    public PracticeCommand(CustomWarps plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            FileConfiguration config = plugin.getConfig();

            Player player = (Player) sender;

            Double x = config.getDouble("warps.practice.x");
            Double y = config.getDouble("warps.practice.y");
            Double z = config.getDouble("warps.practice.z");
            float pitch = config.getInt("warps.practice.pitch");
            float yaw = config.getInt("warps.practice.yaw");
            String worldName = config.getString("warps.practice.world");
            World world = Bukkit.getWorld(worldName);
            Location PracticeLoc = new Location(world, x, y, z, yaw, pitch);

            player.teleport(PracticeLoc);
        }
        return false;
    }
}
