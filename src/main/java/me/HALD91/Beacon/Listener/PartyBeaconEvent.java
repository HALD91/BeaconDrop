package me.HALD91.Beacon.Listener;

import me.HALD91.Beacon.Data.Beacon;
import me.HALD91.Beacon.Data.Item;
import me.HALD91.Beacon.Main.Main;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.Random;

public class PartyBeaconEvent implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent playerInteractEvent) {
        Player player = playerInteractEvent.getPlayer();
        if (!player.hasPermission("bp.activate"))
            return;
        if (playerInteractEvent.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            Beacon beacon = null;
            Block block = playerInteractEvent.getClickedBlock();
            for (Beacon b : Main.getInstance().getBeacons()) {
                if (block.getX() == b.getX() && block.getY() == b.getY() && block.getZ() == b.getZ())
                    beacon = b;
            }
            if (beacon == null)
                return;
            if (beacon.getCd() > System.currentTimeMillis() || beacon.getCd() == System.currentTimeMillis()) {
                long a = beacon.getCd();
                a -= System.currentTimeMillis();
                playerInteractEvent.setCancelled(true);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',Main.getInstance().getCfg().getPrefix() + " " + Main.getInstance().getMessages().getBeaconDC().replace("%time%", (a / 60000L) + " min")));
                return;
            }
            Main.getInstance().getBeacons().remove(beacon);
            beacon.setCd(System.currentTimeMillis() + Main.getInstance().getCd());
            Main.getInstance().addBeacon(beacon);
            startDrop(beacon, block);
            playerInteractEvent.setCancelled(true);
        }
    }

    private void startDrop(Beacon beacon, final Block block) {
        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.getInstance(), new Runnable() {
            public void run() {
                PartyBeaconEvent.this.playSoundAll(block.getLocation(), Sound.CLICK);
                PartyBeaconEvent.this.sendDropMessageAll(3, block);
                Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.getInstance(), new Runnable() {
                    public void run() {
                        PartyBeaconEvent.this.playSoundAll(block.getLocation(), Sound.CLICK);
                        PartyBeaconEvent.this.sendDropMessageAll(2, block);
                        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.getInstance(), new Runnable() {
                            public void run() {
                                PartyBeaconEvent.this.playSoundAll(block.getLocation(), Sound.CLICK);
                                PartyBeaconEvent.this.sendDropMessageAll(1, block);
                                Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.getInstance(), new Runnable() {
                                    public void run() {
                                        PartyBeaconEvent.this.playSoundAll(block.getLocation(), Sound.CLICK);
                                        PartyBeaconEvent.this.sendDropMessageAll(0, block);
                                        PartyBeaconEvent.this.dropItems(block);
                                    }
                                },20L);
                            }
                        },20L);
                    }
                },20L);
            }
        },20L);
    }

    private void dropItems(final Block block) {
        for (int i = 0; i < Main.getInstance().getCfg().getDropamount(); i++) {
            Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.getInstance(), new Runnable() {
                public void run() {
                    PartyBeaconEvent.this.drop(block);
                }
            },  (20 * i));
        }
    }

    private int random(int max, int min) {
        Random r = new Random();
        int seed = r.nextInt();
        Random r2 = new Random(seed);
        int result = r2.nextInt(max - min + 1 + min);
        return result;
    }

    private void drop(Block block) {
        ItemStack itemStack;
        int random = random(Main.getInstance().allDropRates(), 0);
        Item item = findItem(random);
        playEffectAll(block);
        playSoundAll(block.getLocation(), Sound.LEVEL_UP);
        Location loc = block.getLocation();
        int neg = random(6, 0);
        double x = 0.2D;
        double y = 0.2D;
        double z = 0.2D;
        if (neg == 1) {
            x = 0.3D;
            z = -0.1D;
            y = 0.3D;
        }
        if (neg == 2) {
            x = -0.3D;
            z = -0.1D;
            y = 0.3D;
        }
        if (neg == 3) {
            x = -0.3D;
            z = 0.1D;
            y = 0.3D;
        }
        if (neg == 4) {
            x = 0.1D;
            z = -0.3D;
            y = 0.3D;
        }
        if (neg == 5) {
            x = -0.1D;
            z = 0.3D;
            y = 0.3D;
        }
        Vector vector = new Vector(x, y, z);
        loc.setY(loc.getY() + 2.0D);
        if (item.getItemStack() == null) {
            itemStack = new ItemStack(Material.STONE);
        } else {
            itemStack = item.getItemStack();
        }
        block.getWorld().dropItemNaturally(loc, itemStack).setVelocity(vector);
    }

    private Item findItem(int random) {
        int current = 0;
        for (Item i : Main.getInstance().getItems()) {
            current += i.getDropChance();
            if (current > random || current == random)
                return i;
        }
        return null;
    }

    private void playEffectAll(Block block) {
        Location loc = block.getLocation();
        loc.setY(loc.getY() + 1.0D);
        for (Player all : Bukkit.getOnlinePlayers()) {
            all.playEffect(loc, Effect.FIREWORKS_SPARK, 1);
            all.playEffect(loc, Effect.FIREWORKS_SPARK, 1);
            all.playEffect(loc, Effect.FIREWORKS_SPARK, 1);
            all.playEffect(loc, Effect.FIREWORKS_SPARK, 1);
            all.playEffect(loc, Effect.FIREWORKS_SPARK, 1);
        }
    }

    private void sendDropMessageAll(int a, Block b) {
        for (Player player : Bukkit.getOnlinePlayers())
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',Main.getInstance().getCfg().getPrefix() + " " + Main.getInstance().getMessages().getBeaconCountdown().replace("%cords%", "X: " + b.getX() + " Y: " + b.getY() + " Z: " + b.getZ()).replace("%time%", a + "")));
    }

    private void playSoundAll(Location location, Sound sound) {
        for (Player player : Bukkit.getOnlinePlayers())
            player.playSound(location, sound, 1.0F, 1.0F);
    }
}
