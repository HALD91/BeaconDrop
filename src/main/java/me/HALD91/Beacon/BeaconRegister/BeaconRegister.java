package me.HALD91.Beacon.BeaconRegister;

import me.HALD91.Beacon.Data.Beacon;
import me.HALD91.Beacon.Main.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class BeaconRegister implements Listener {
    @EventHandler
    public void onRightClick(PlayerInteractEvent playerInteractEvent) {
        if (!Main.getInstance().getBpRegister().contains(playerInteractEvent.getPlayer()))
            return;
        if (!playerInteractEvent.getAction().equals(Action.RIGHT_CLICK_BLOCK))
            return;
        if (!playerInteractEvent.getClickedBlock().getType().equals(Material.BEACON))
            return;
        Beacon beacon = new Beacon(Main.getInstance().getBeacons().size() + 1, playerInteractEvent.getClickedBlock().getX(), playerInteractEvent.getClickedBlock().getY(), playerInteractEvent.getClickedBlock().getZ(), playerInteractEvent.getClickedBlock(), playerInteractEvent.getClickedBlock().getWorld().getName(), System.currentTimeMillis());
        if (Main.getInstance().getBeacons().contains(beacon))
            return;
        playerInteractEvent.setCancelled(true);
        Main.getInstance().addBeacon(beacon);
        playerInteractEvent.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&',Main.getInstance().getCfg().getPrefix() + " " + Main.getInstance().getMessages().getBeaconCreate()));
        Main.getInstance().removeBpRegister(playerInteractEvent.getPlayer());
    }
}
