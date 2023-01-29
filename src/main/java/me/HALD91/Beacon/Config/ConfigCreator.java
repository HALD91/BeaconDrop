package me.HALD91.Beacon.Config;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigCreator {
    public File file = new File("plugins/BeaconDrop/Config/config.yml");

    public YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(this.file);

    public int createConfig() {
        if (this.file.exists())
            return 0;
        this.yamlConfiguration.set("plugin.prefix", "&8[&eSystem&8]");
        this.yamlConfiguration.set("plugin.cooldown", Integer.valueOf(60));
        this.yamlConfiguration.set("plugin.diffitems", Integer.valueOf(4));
        this.yamlConfiguration.set("plugin.dropamount", Integer.valueOf(24));
        try {
            this.yamlConfiguration.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        createMessages();
        return 1;
    }

    private void createMessages() {
        File file2 = new File("plugins/BeaconDrop/Config/messages.yml");
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
        Bukkit.getServer().shutdown();
    }
}
