package me.HALD91.Beacon.Main;

import me.HALD91.Beacon.BeaconRegister.BeaconRegister;
import me.HALD91.Beacon.CommandManager.CommandManager;
import me.HALD91.Beacon.Config.Config;
import me.HALD91.Beacon.Config.Loader;
import me.HALD91.Beacon.Config.Messages;
import me.HALD91.Beacon.Data.Beacon;
import me.HALD91.Beacon.Data.BeaconLoader;
import me.HALD91.Beacon.Data.Item;
import me.HALD91.Beacon.Listener.BeaconListener;
import me.HALD91.Beacon.Listener.PartyBeaconEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class Main extends JavaPlugin {
        private static Main beaconParty;

        private Messages messages;

        private Config config;

        private long cd;

        public static Main getInstance() {
                return beaconParty;
        }
        public long getCd() {
                return this.cd;
        }

        public void setCd(long cd) {
                this.cd = cd;
        }

        private long minuteToMilliseconds(int min) {
                long millis = (60000 * min);
                return millis;
        }

        public int allDropRates() {
                int a = 0;
                for (Item item : getItems())
                        a += item.getDropChance();
                return a;
        }

        private ArrayList<Beacon> beacons = new ArrayList<>();

        public void setBeacons(ArrayList<Beacon> beacons) {
                this.beacons = beacons;
        }

        public ArrayList<Beacon> getBeacons() {
                return this.beacons;
        }

        public void removeBeacon(Beacon beacon) {
                this.beacons.remove(beacon);
        }

        public void addBeacon(Beacon beacon) {
                this.beacons.add(beacon);
        }

        public Config getCfg() {
                return this.config;
        }

        public Messages getMessages() {
                return this.messages;
        }

        public void setMessages(Messages messages) {
                this.messages = messages;
        }

        public void setConfig(Config config) {
                this.config = config;
        }

        private ArrayList<Item> items = new ArrayList<>();

        public ArrayList<Item> getItems() {
                return this.items;
        }

        public void addItem(Item item) {
                this.items.add(item);
        }

        @Override
        public void onEnable() {
                beaconParty = this;
                if (getInstance() == null) {
                        Bukkit.getConsoleSender().sendMessage("Plugin error... No instance could be created!");
                        Bukkit.getServer().shutdown();
                }
                Loader loader = new me.HALD91.Beacon.Config.Loader();
                Bukkit.getConsoleSender().sendMessage(getCfg().getPrefix() + "&astored in instance!");
                Bukkit.getConsoleSender().sendMessage(getCfg().getPrefix() + "&aloading beacons!");
                this.beaconLoader.loadAllBeacons();

                getCommand("BeaconDrop").setExecutor((CommandExecutor)new CommandManager());
                Bukkit.getServer().getPluginManager().registerEvents((Listener)new BeaconListener(), (Plugin)this);
                Bukkit.getServer().getPluginManager().registerEvents((Listener)new PartyBeaconEvent(), (Plugin)this);
                Bukkit.getServer().getPluginManager().registerEvents((Listener)new BeaconRegister(), (Plugin)this);
                setCd(minuteToMilliseconds(getCfg().getCooldown()));
        }

        private BeaconLoader beaconLoader = new BeaconLoader();
        private ArrayList<Player> bpRegister = new ArrayList<>();
        private ArrayList<Player> bpDelete = new ArrayList<>();

        public ArrayList<Player> getBpRegister() {
                return this.bpRegister;
        }

        public void addBpRegister(Player player) {
                this.bpRegister.add(player);
        }

        public void removeBpRegister(Player player) {
                this.bpRegister.remove(player);
        }

        public ArrayList<Player> getBpRemove() {
                return this.bpDelete;
        }

        public void addBpRemove(Player player) {
                this.bpDelete.add(player);
        }

        public void removeBpRemove(Player player) {
                this.bpDelete.remove(player);
        }



        public void onDisable(){
                this.beaconLoader.saveAllBeacons();
                super.onDisable();
        }
}
