package me.HALD91.Beacon.Listener;

import me.HALD91.Beacon.Data.Beacon;
import me.HALD91.Beacon.Main.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class BeaconListener implements Listener {
    @EventHandler
    public void onLeftClick(PlayerInteractEvent onRemoveBeacon) {
        if (!Main.getInstance().getBpRemove().contains(onRemoveBeacon.getPlayer()))
            return;
        if (!onRemoveBeacon.getAction().equals(Action.LEFT_CLICK_BLOCK))
            return;
        if (!onRemoveBeacon.getClickedBlock().getType().equals(Material.BEACON))
            return;
        onRemoveBeacon.setCancelled(true);
        Beacon isRight = null;
        for (Beacon beacon : Main.getInstance().getBeacons()) {
            if (onRemoveBeacon.getClickedBlock().getX() == beacon.getX() && onRemoveBeacon.getClickedBlock().getY() == beacon.getY() && onRemoveBeacon.getClickedBlock().getZ() == beacon.getZ())
                isRight = beacon;
        }
        if (isRight == null)
            return;
        Main.getInstance().removeBeacon(isRight);
        onRemoveBeacon.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&',Main.getInstance().getConfig().getString("plugin.prefix") + " " + "&5Partybeacon &cdestroyed!"));
        Main.getInstance().removeBpRemove(onRemoveBeacon.getPlayer());
        onRemoveBeacon.getClickedBlock().setType(Material.AIR);
    }
}
