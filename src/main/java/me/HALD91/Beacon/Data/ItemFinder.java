package me.HALD91.Beacon.Data;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ItemFinder {
    public void createFile(int i, String prefix) {
        File file = new File("plugins/BeaconDrop/Items/" + i + ".yml");
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
        yamlConfiguration.options().header("This is the item configuration of item ID: " + i + ".\n\nPlease use the official minecraft material names!  + \n\nEnchantment have to get added by plugin id. If you want 2 enchantments use a ',' between ids. \n\nID 1-5: Protection, ID 6-10: Fire Protection, ID 11-13: Fall Protection, ID 14-17: Blast Protection \n\nID 18-21: Projectile Protection, ID 22-24: Thorns, ID 25-27: Damage, ID 28-29: Knockback \n\nID 30: Fire Ascpect, ID 31: Durability, ID 32-36: Arrow Damage");
        yamlConfiguration.set("Item.Material", Material.GRASS.toString());
        yamlConfiguration.set("Item.Enchantments", "1,8");
        yamlConfiguration.set("Item.DropChance", Integer.valueOf(20));
        try {
            yamlConfiguration.save(file);
            Bukkit.getConsoleSender().sendMessage(prefix + "&a ITEM ID &5" + i + " &aGOT A NEW FILE!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ItemBuilder itemBuilder = new ItemBuilder();

    public void searchItem(int i, String prefix) {
        File file = new File("plugins/BeaconDrop/Items/" + i + ".yml");
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
        if (!file.exists()) {
            createFile(i, prefix);
            return;
        }
        Material material = Material.getMaterial(yamlConfiguration.getString("Item.Material"));
        String enchantment = yamlConfiguration.getString("Item.Enchantments");
        int dropChance = yamlConfiguration.getInt("Item.DropChance");
        this.itemBuilder.BuildItem(material, enchantment, dropChance);
        Bukkit.getConsoleSender().sendMessage(prefix + "&aITEM ID &5"  + i + " &aFOUND AND LOADED!");
    }
}
