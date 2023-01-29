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
        onRemoveBeacon.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&',Main.getInstance().getCfg().getPrefix() + " " + "&5Partybeacon &cdestroyed!"));
        Main.getInstance().removeBpRemove(onRemoveBeacon.getPlayer());
        onRemoveBeacon.getClickedBlock().setType(Material.AIR);
    }
   /* @EventHandler
    public void onDestroy(BlockBreakEvent blockBreakEvent) {
        Player playermode = blockBreakEvent.getPlayer();
        if (playermode.getGameMode() == GameMode.CREATIVE) {
            if (blockBreakEvent.getBlock().getType().equals(Material.BEACON)) {
                Beacon isRight = null;
                for (Beacon beacon : Main.getInstance().getBeacons()) {
                    if (blockBreakEvent.getBlock().getX() == beacon.getX() && blockBreakEvent.getBlock().getY() == beacon.getY() && blockBreakEvent.getBlock().getZ() == beacon.getZ())
                        isRight = beacon;
                }
                if (isRight == null)
                    return;
                Player player = blockBreakEvent.getPlayer();
                if (player.hasPermission("bp.break")) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().getCfg().getPrefix() + " " + "&5Partybeacon &cdestroyed!"));
                    Main.getInstance().removeBeacon(isRight);
                    return;
                }
                blockBreakEvent.setCancelled(true);
            }
        } else {
            Player player = blockBreakEvent.getPlayer();
            blockBreakEvent.setCancelled(true);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().getCfg().getPrefix() + " " + "&cYou need to be in creative to destory a PartyBeacon"));
        }
    }
    */
}
