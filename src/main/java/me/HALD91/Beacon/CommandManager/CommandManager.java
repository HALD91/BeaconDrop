package me.HALD91.Beacon.CommandManager;

import me.HALD91.Beacon.Main.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandManager implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (command.getName().equalsIgnoreCase("BeaconDrop")) {
            if (!(sender instanceof Player))
                return false;

            Player player = (Player) sender;
            if (player.hasPermission("beacondrop.use")) {
                if (args.length == 0) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Use &3/BeaconDrop Set/Create &7- To Create a beacon"));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Use &3/BeaconDrop Del/Delete &7- To Delete a beacon"));
                }

                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("Set") || args[0].equalsIgnoreCase("Create")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',Main.getInstance().getCfg().getPrefix() + " " + "&aRight-Click &7a beacon"));
                        if (!Main.getInstance().getBpRegister().contains(player)) {
                            Main.getInstance().addBpRegister(player);
                        }
                    }
                    if (args[0].equalsIgnoreCase("Delete") || args[0].equalsIgnoreCase("Del")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',Main.getInstance().getCfg().getPrefix() + " " + "&cLeft-Click &7a beacon"));
                        if (!Main.getInstance().getBpRemove().contains(player)) {
                            Main.getInstance().addBpRemove(player);
                        }
                    }
                }

            } else {
                if (!player.hasPermission("beacondrop.use")) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().getMessages().getPermissionMessage()));
                }
            }
        }
        return false;
    }
}
