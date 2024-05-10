package me.HALD91.Beacon.Config;

import me.HALD91.Beacon.Main.Main;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Messages {
    private String permissionMessage;
    private String beaconDC;

    private String beaconCreate;

    private String beaconCountdown;
        public static File F_MSG = new File(Main.getInstance().getDataFolder(),"/messages.yml");

    public static YamlConfiguration Cfg_MSG = YamlConfiguration.loadConfiguration(F_MSG);

    public Messages(String beaconDC, String beaconCreate, String beaconCountdown, String permissionMessage) {
        this.beaconDC = beaconDC;
        this.beaconCreate = beaconCreate;
        this.beaconCountdown = beaconCountdown;
        this.permissionMessage = permissionMessage;
    }

    public static String getMessage(String path) {
        if (F_MSG.exists()) {
            return Cfg_MSG.getString(path);
        }
        return "Cokis";
    }

    public static void reloadMessage() {
        Cfg_MSG = YamlConfiguration.loadConfiguration(F_MSG);
    }

    public String getBeaconCountdown() {
        return this.beaconCountdown;
    }

    public String getBeaconCreate() {
        return this.beaconCreate;
    }

    public String getBeaconDC() {
        return this.beaconDC;
    }

    public String getPermissionMessage() {
        return this.permissionMessage;
    }

    public void setBeaconCountdown(String beaconCountdown) {
        this.beaconCountdown = beaconCountdown;
    }

    public void setBeaconCreate(String beaconCreate) {
        this.beaconCreate = beaconCreate;
    }

    public void setBeaconDC(String beaconDC) {
        this.beaconDC = beaconDC;
    }
    public void setPermissionMessage(String permissionMessage) {
        this.permissionMessage = permissionMessage;
    }
}
