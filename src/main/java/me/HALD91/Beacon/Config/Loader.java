package me.HALD91.Beacon.Config;

import me.HALD91.Beacon.Data.ItemFinder;
import me.HALD91.Beacon.Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Loader {
    private ConfigCreator configCreator;

    private ItemFinder itemFinder = new ItemFinder();

    public Loader() {
        this.configCreator = new ConfigCreator();
        if (this.configCreator.createConfig() == 1)
            return;
        File file = new File("plugins/BeaconDrop/Config/config.yml");
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
        Config config1 = new Config(yamlConfiguration.getString("plugin.prefix"), yamlConfiguration.getInt("plugin.diffitems"), yamlConfiguration.getInt("plugin.cooldown"), yamlConfiguration.getInt("plugin.dropamount"));
        File file2 = new File("plugins/BeaconDrop/Config/messages.yml");
        YamlConfiguration yamlConfiguration1 = YamlConfiguration.loadConfiguration(file2);
        Messages messages1 = new Messages(yamlConfiguration1.getString("plugin.beaconDC"), yamlConfiguration1.getString("plugin.beaconCreate"), yamlConfiguration1.getString("plugin.beaconCountdown"), yamlConfiguration1.getString("BeaconDrop.Permission.Message"));
        Main.getInstance().setMessages(messages1);
        Main.getInstance().setConfig(config1);
        Bukkit.getServer().getConsoleSender().sendMessage(config1.getPrefix() + "loaded!");
        for (int i = 0; i < config1.getDiffitems(); i++)
            this.itemFinder.searchItem(i, config1.getPrefix());
    }
}
