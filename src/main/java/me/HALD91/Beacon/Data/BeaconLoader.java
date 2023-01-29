package me.HALD91.Beacon.Data;

import me.HALD91.Beacon.Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class BeaconLoader {
    public void loadAllBeacons() {
        boolean beaconFound = true;
        ArrayList<Beacon> beacons = new ArrayList<>();
        int fileID = 0;
        while (beaconFound) {
            File file = new File("plugins/BeaconDrop/Data/BeaconID" + fileID + ".yml");
            YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
            if (!file.exists())
                beaconFound = false;
            if (!beaconFound)
                return;
            int id = fileID;
            int x = yamlConfiguration.getInt("beacon.x");
            int y = yamlConfiguration.getInt("beacon.y");
            int z = yamlConfiguration.getInt("beacon.z");
            String worldName = yamlConfiguration.getString("beacon.worldName");
            long cd = System.currentTimeMillis();
            Beacon beacon = new Beacon(id, x, y, z, Bukkit.getWorld(worldName).getBlockAt(x, y, z), worldName, cd);
            Bukkit.getConsoleSender().sendMessage(Main.getInstance().getCfg().getPrefix() + "found at: " + x + " " + y + " " + z);
            Main.getInstance().addBeacon(beacon);
            fileID++;
            file.delete();
        }
        if (beacons.size() == 0)
            Bukkit.getConsoleSender().sendMessage(Main.getInstance().getCfg().getPrefix() + "Beacon found!");
        Main.getInstance().setBeacons(beacons);
    }

    public void saveAllBeacons() {
        ArrayList<Beacon> beacons = Main.getInstance().getBeacons();
        if (beacons.isEmpty())
            return;
        int a = 0;
        for (Beacon beacon : beacons) {
            File file = new File("plugins/BeaconDrop/Data/BeaconID" + a + ".yml");
            YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
            yamlConfiguration.set("beacon.x", Integer.valueOf(beacon.getX()));
            yamlConfiguration.set("beacon.y", Integer.valueOf(beacon.getY()));
            yamlConfiguration.set("beacon.z", Integer.valueOf(beacon.getZ()));
            yamlConfiguration.set("beacon.worldName", beacon.getWorldName());
            try {
                yamlConfiguration.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            a++;
        }
    }
}
