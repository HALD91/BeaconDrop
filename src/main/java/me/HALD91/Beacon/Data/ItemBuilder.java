package me.HALD91.Beacon.Data;

import me.HALD91.Beacon.Main.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ItemBuilder {
    public void BuildItem(Material material, String enchantments, int dropChance, String name, boolean blore1, String slore1, boolean blore2, String slore2) {
        String[] enchantmentList = enchantments.split(",");
        ItemStack itemStack = new ItemStack(material);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        if (blore1 && blore2) {
            ArrayList Lore = new ArrayList();
            Lore.add(ChatColor.translateAlternateColorCodes('&', slore1));
            Lore.add(ChatColor.translateAlternateColorCodes('&', slore2));
            meta.setLore(Lore);
        } else {
            if (blore1) {
                ArrayList Lore = new ArrayList();
                Lore.add(ChatColor.translateAlternateColorCodes('&', slore1));
                meta.setLore(Lore);
            }
            if (blore2) {
                ArrayList Lore = new ArrayList();
                Lore.add(ChatColor.translateAlternateColorCodes('&', slore2));
                meta.setLore(Lore);
            }
        }
        for (String s : enchantmentList) {
            if (s.equalsIgnoreCase("1"))
                meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
            if (s.equalsIgnoreCase("2"))
                meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, false);
            if (s.equalsIgnoreCase("3"))
                meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, false);
            if (s.equalsIgnoreCase("4"))
                meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, false);
            if (s.equalsIgnoreCase("5"))
                meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
            if (s.equalsIgnoreCase("6"))
                meta.addEnchant(Enchantment.PROTECTION_FIRE, 1, false);
            if (s.equalsIgnoreCase("7"))
                meta.addEnchant(Enchantment.PROTECTION_FIRE, 2, false);
            if (s.equalsIgnoreCase("8"))
                meta.addEnchant(Enchantment.PROTECTION_FIRE, 3, false);
            if (s.equalsIgnoreCase("9"))
                meta.addEnchant(Enchantment.PROTECTION_FIRE, 4, false);
            if (s.equalsIgnoreCase("10"))
                meta.addEnchant(Enchantment.PROTECTION_FIRE, 5, true);
            if (s.equalsIgnoreCase("11"))
                meta.addEnchant(Enchantment.PROTECTION_FALL, 1, false);
            if (s.equalsIgnoreCase("12"))
                meta.addEnchant(Enchantment.PROTECTION_FALL, 2, false);
            if (s.equalsIgnoreCase("13"))
                meta.addEnchant(Enchantment.PROTECTION_FALL, 3, false);
            if (s.equalsIgnoreCase("14"))
                meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 1, false);
            if (s.equalsIgnoreCase("15"))
                meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 2, false);
            if (s.equalsIgnoreCase("16"))
                meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 3, false);
            if (s.equalsIgnoreCase("17"))
                meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 4, false);
            if (s.equalsIgnoreCase("18"))
                meta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 1, false);
            if (s.equalsIgnoreCase("19"))
                meta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 2, false);
            if (s.equalsIgnoreCase("20"))
                meta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 3, false);
            if (s.equalsIgnoreCase("21"))
                meta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 4, false);
            if (s.equalsIgnoreCase("22"))
                meta.addEnchant(Enchantment.THORNS, 1, false);
            if (s.equalsIgnoreCase("23"))
                meta.addEnchant(Enchantment.THORNS, 2, false);
            if (s.equalsIgnoreCase("24"))
                meta.addEnchant(Enchantment.THORNS, 3, false);
            if (s.equalsIgnoreCase("25"))
                meta.addEnchant(Enchantment.DAMAGE_ALL, 1, false);
            if (s.equalsIgnoreCase("26"))
                meta.addEnchant(Enchantment.DAMAGE_ALL, 2, false);
            if (s.equalsIgnoreCase("27"))
                meta.addEnchant(Enchantment.DAMAGE_ALL, 3, false);
            if (s.equalsIgnoreCase("28"))
                meta.addEnchant(Enchantment.DAMAGE_ALL, 4, false);
            if (s.equalsIgnoreCase("29"))
                meta.addEnchant(Enchantment.KNOCKBACK, 1, false);
            if (s.equalsIgnoreCase("30"))
                meta.addEnchant(Enchantment.KNOCKBACK, 2, false);
            if (s.equalsIgnoreCase("31"))
                meta.addEnchant(Enchantment.FIRE_ASPECT, 1, false);
            if (s.equalsIgnoreCase("32"))
                meta.addEnchant(Enchantment.FIRE_ASPECT, 2, false);
            if (s.equalsIgnoreCase("33"))
                meta.addEnchant(Enchantment.DURABILITY, 1, false);
            if (s.equalsIgnoreCase("34"))
                meta.addEnchant(Enchantment.DURABILITY, 1, false);
            if (s.equalsIgnoreCase("35"))
                meta.addEnchant(Enchantment.DURABILITY, 1, false);
            if (s.equalsIgnoreCase("36"))
                meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, false);
            if (s.equalsIgnoreCase("37"))
                meta.addEnchant(Enchantment.ARROW_DAMAGE, 2, false);
            if (s.equalsIgnoreCase("38"))
                meta.addEnchant(Enchantment.ARROW_DAMAGE, 3, false);
            if (s.equalsIgnoreCase("39"))
                meta.addEnchant(Enchantment.ARROW_DAMAGE, 4, false);
            if (s.equalsIgnoreCase("40"))
                meta.addEnchant(Enchantment.ARROW_DAMAGE, 5, false);
        }
        if (itemStack.getType().equals(Material.GOLDEN_APPLE)) {
            itemStack = new ItemStack(Material.GOLDEN_APPLE, 1, (short) 1);
        }
        itemStack.setItemMeta(meta);
        Item item = new Item(itemStack, dropChance);
        Main.getInstance().addItem(item);
    }
}
