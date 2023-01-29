package me.HALD91.Beacon.Config;

public class Config {

    private String prefix;

    private int cooldown;

    private int diffitems;

    private int dropamount;

    public Config(String prefix, int diffitems, int cooldown, int dropamount) {
        this.cooldown = cooldown;
        this.diffitems = diffitems;
        this.dropamount = dropamount;
        this.prefix = prefix;
    }

    public int getCooldown() {
        return this.cooldown;
    }

    public int getDiffitems() {
        return this.diffitems;
    }

    public int getDropamount() {
        return this.dropamount;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public void setDiffitems(int diffitems) {
        this.diffitems = diffitems;
    }

    public void setDropamount(int dropamount) {
        this.dropamount = dropamount;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
