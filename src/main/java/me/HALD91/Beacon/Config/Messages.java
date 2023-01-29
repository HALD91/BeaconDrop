package me.HALD91.Beacon.Config;

public class Messages {
    private String permissionMessage;
    private String beaconDC;

    private String beaconCreate;

    private String beaconCountdown;



    public Messages(String beaconDC, String beaconCreate, String beaconCountdown, String permissionMessage) {
        this.beaconCountdown = beaconCountdown;
        this.beaconDC = beaconDC;
        this.beaconCreate = beaconCreate;
        this.permissionMessage = permissionMessage;
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
