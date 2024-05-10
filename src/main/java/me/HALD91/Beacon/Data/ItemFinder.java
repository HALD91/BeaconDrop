package me.HALD91.Beacon.Data;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ItemFinder {
    public void createFile(int i, String prefix) {
        File file = new File("plugins/BeaconDrop/Items/" + i + ".yml");
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
        yamlConfiguration.options().header("This is the item configuration of item ID: " + i + ".\n\nPlease use the official minecraft material names!  + \n\nEnchantment have to get added by plugin id. If you want 2 enchantments use a ',' between ids. \n\nID 1-5: Protection, ID 6-10: Fire Protection, ID 11-13: Feather Falling, ID 14-17: Blast Protection \n\nID 18-21: Projectile Protection, ID 22-24: Thorns, ID 25-28: Sharpness, ID 29-30: Knockback \n\nID 31-32: Fire Ascpect, ID 33-35: Unbreaking, ID 36-40: Arrow Damage");
        yamlConfiguration.set("Item.Material", Material.DIAMOND_SWORD.toString());
        yamlConfiguration.set("Item.Name", "&eSword");
        yamlConfiguration.set("Item.Lore1.enable", false);
        yamlConfiguration.set("Item.Lore1.lore", "");
        yamlConfiguration.set("Item.Lore2.enable", false);
        yamlConfiguration.set("Item.Lore2.lore", "");
        yamlConfiguration.set("Item.Enchantments", "1,8");
        yamlConfiguration.set("Item.DropChance", Integer.valueOf(20));
        try {
            yamlConfiguration.save(file);
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',prefix + "&a ITEM ID &5" + i + " &aGOT A NEW FILE!"));
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
        String name = yamlConfiguration.getString("Item.Name");
        boolean Blore1 = yamlConfiguration.getBoolean("Item.Lore1.enable");
        boolean Blore2 = yamlConfiguration.getBoolean("Item.Lore2.enable");
        String Slore1 = yamlConfiguration.getString("Item.Lore1.lore");
        String Slore2 = yamlConfiguration.getString("Item.Lore2.lore");
        String enchantment = yamlConfiguration.getString("Item.Enchantments");
        int dropChance = yamlConfiguration.getInt("Item.DropChance");
        this.itemBuilder.BuildItem(material, enchantment, dropChance, name, Blore1, Slore1, Blore2, Slore2);
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',prefix + " " + "&aITEM ID &5"  + i + " &aFOUND AND LOADED!"));
    }
}
