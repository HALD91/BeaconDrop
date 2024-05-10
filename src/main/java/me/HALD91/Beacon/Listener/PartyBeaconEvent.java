package me.HALD91.Beacon.Listener;

import me.HALD91.Beacon.Config.Messages;
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
        if (!player.hasPermission("bp.activate")) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',Messages.getMessage("BeaconDrop.Permission.Message")));
            return;
        }
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
                playerInteractEvent.setCancelled(true);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',Main.getInstance().getConfig().getString("plugin.prefix") + " " + Messages.getMessage("BeaconDrop.beaconDC").replace("%time%", remainingTime(a))));
                return;
            }
            Main.getInstance().getBeacons().remove(beacon);
            beacon.setCd(System.currentTimeMillis() + Main.getInstance().getCd());
            Main.getInstance().addBeacon(beacon);
            startDrop(beacon, block);
            playerInteractEvent.setCancelled(true);
        }
    }

    public String remainingTime(long endTime) {
        long currentTime = System.currentTimeMillis();
        long remainingTime = endTime - currentTime;

        if (remainingTime <= 0) { // Time has passed
            return null;
        } else if (remainingTime < 60000) { // Less than a minute
            long remainingSeconds = remainingTime / 1000;
            return remainingSeconds + " seconds";
        } else { // More than a minute
            long remainingMinutes = remainingTime / 60000;
            return remainingMinutes + " minutes";
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
                                        PartyBeaconEvent.this.drop(block);
                                    }
                                },20L);
                            }
                        },20L);
                    }
                },20L);
            }
        },20L);
    }

    private int random(int max, int min) {
        Random r = new Random();
        int seed = r.nextInt();
        Random r2 = new Random(seed);
        int result = r2.nextInt(max - min + 1 + min);
        return result;
    }

    private void drop(Block block) {
        int dropAmount = Main.getInstance().getConfig().getInt("plugin.dropamount"); // Total number of items to drop
        int numberOfItemsToDrop = (int) (Math.random() * 1) + 3; // Random value between 3 and 5 for the first batch

        dropItemsInBatches(block, dropAmount, numberOfItemsToDrop, 40L); // Start dropping items
    }

    private void dropItemsInBatches(Block block, int dropAmount, int numberOfItemsToDrop, long delay) {
        if (dropAmount <= 0) return; // No more items to drop

        // Drop a batch of items
        dropBatch(block, numberOfItemsToDrop);

        // Update drop amount
        dropAmount -= numberOfItemsToDrop;

        final int finalDropAmount = dropAmount;
        final int finalNumberOfItemsToDrop = numberOfItemsToDrop;
        final long finalDelay = delay;

        // Schedule next batch if there are more items to drop
        if (dropAmount > 0) {
            // Delayed task to drop the next batch after 1 second
            Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin) Main.getInstance(), () ->
                    dropItemsInBatches(block, finalDropAmount, finalNumberOfItemsToDrop, finalDelay), finalDelay);
        }
    }


    private void dropBatch(Block block, int numberOfItemsToDrop) {
        for (int i = 0; i < numberOfItemsToDrop; i++) {
            // Generate and drop each item individually
            ItemStack itemStack;
            int randomRate = random(Main.getInstance().allDropRates(), 0);
            Item item = findItem(randomRate);

            Location loc = block.getLocation().add(0.5, 2.0, 0.5); // Adjusted to a higher position above the beacon

            // Limit the range of random offsets for initial position within the 3 by 3 area
            double xOffset;
            double zOffset;
            do {
                xOffset = (Math.random() - 0.5) * 0.5; // Random value between -0.25 and 0.25 for x-coordinate
                zOffset = (Math.random() - 0.5) * 0.5; // Random value between -0.25 and 0.25 for z-coordinate
            } while (Math.abs(xOffset) > 0.75 || Math.abs(zOffset) > 0.75); // Ensure offsets are within the 3 by 3 area

            loc.add(xOffset, 0, zOffset); // Apply the random offsets to initial position

            // Adjust the position slightly up
            loc.add(0, -0.25, 0);

            Vector vector = getRandomVector(); // Get a random vector for initial velocity

            if (item == null) {
                itemStack = new ItemStack(Material.DIAMOND_SWORD);
            } else {
                itemStack = item.getItemStack();
            }

            // Drop the item with the adjusted velocity
            playEffectAll(block);
            playSoundAll(block.getLocation(), Sound.CLICK);
            block.getWorld().dropItem(loc, itemStack).setVelocity(vector);
        }
    }

    // Generate a random velocity vector within the confined space around the beacon
    private Vector getRandomVector() {
        double x = (Math.random() * 2 - 1) * 0.1; // Random value between -0.1 and 0.1 for x-coordinate
        double z = (Math.random() * 2 - 1) * 0.1; // Random value between -0.1 and 0.1 for z-coordinate
        return new Vector(x, 0.2, z); // Adjusted initial upward velocity
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
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',Main.getInstance().getConfig().getString("plugin.prefix") + " " + Messages.getMessage("BeaconDrop.beaconCountdown").replace("%cords%", "X: " + b.getX() + " Y: " + b.getY() + " Z: " + b.getZ()).replace("%time%", a + "")));
    }

    private void playSoundAll(Location location, Sound sound) {
        for (Player player : Bukkit.getOnlinePlayers())
            player.playSound(location, sound, 1.0F, 1.0F);
    }
}
