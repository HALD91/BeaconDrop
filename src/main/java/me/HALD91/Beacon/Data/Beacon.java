package me.HALD91.Beacon.Data;

import org.bukkit.block.Block;

public class Beacon {
    private int id;

    private int x;

    private int y;

    private int z;

    private String worldName;

    private Block block;

    private long cd;

    public Beacon(int id, int x, int y, int z, Block block, String worldName, long cd) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.z = z;
        this.worldName = worldName;
        this.block = block;
        this.cd = cd;
    }

    public Block getBlock() {
        return this.block;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getZ() {
        return this.z;
    }

    public int getId() {
        return this.id;
    }

    public long getCd() {
        return this.cd;
    }

    public String getWorldName() {
        return this.worldName;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public void setCd(long cd) {
        this.cd = cd;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWorldName(String worldName) {
        this.worldName = worldName;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setZ(int z) {
        this.z = z;
    }
}
