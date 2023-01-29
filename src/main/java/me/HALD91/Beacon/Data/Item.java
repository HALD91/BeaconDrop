package me.HALD91.Beacon.Data;

import org.bukkit.inventory.ItemStack;

public class Item {
    private ItemStack itemStack;

    private int dropChance;

    public Item(ItemStack itemStack, int dropChance) {
        this.dropChance = dropChance;
        this.itemStack = itemStack;
    }

    public int getDropChance() {
        return this.dropChance;
    }

    public ItemStack getItemStack() {
        return this.itemStack;
    }

    public void setDropChance(int dropChance) {
        this.dropChance = dropChance;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }
}
