package me.HALD91.Beacon.Config;

import me.HALD91.Beacon.Data.ItemFinder;
import me.HALD91.Beacon.Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Loader {
    private ConfigCreator configCreator;

    private ItemFinder itemFinder = new ItemFinder();

    private void createMessages() {
        File file2 = new File(Main.getInstance().getDataFolder(),"/messages.yml");
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file2);
        yamlConfiguration.set("BeaconDrop.beaconDC", "&7This &3BeaconDrop &7is on &ccooldown. &7CD: &a%time%");
        yamlConfiguration.set("BeaconDrop.beaconCreate", "&3BeaconDrop &acreated!");
        yamlConfiguration.set("BeaconDrop.beaconCountdown", "&3BeaconDrop &7at &a%cords% &7dropping in &a%time%!");
        yamlConfiguration.set("BeaconDrop.Permission.Message", "&3BeaconDrop &cYou dont have permission to use this");
        try {
            yamlConfiguration.save(file2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Loader() {
        createMessages();
        File file2 = new File(Main.getInstance().getDataFolder(), "/messages.yml");
        YamlConfiguration yamlConfiguration1 = YamlConfiguration.loadConfiguration(file2);
        Messages messages1 = new Messages(yamlConfiguration1.getString("plugin.beaconDC"), yamlConfiguration1.getString("plugin.beaconCreate"), yamlConfiguration1.getString("plugin.beaconCountdown"), yamlConfiguration1.getString("BeaconDrop.Permission.Message"));
        Main.getInstance().setMessages(messages1);
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',Main.getInstance().getConfig().getString("plugin.prefix") + "loaded!"));
        for (int i = 0; i < Main.getInstance().getConfig().getInt("plugin.diffitems"); i++)
            this.itemFinder.searchItem(i, Main.getInstance().getConfig().getString("plugin.prefix"));
    }
}
