package me.HALD91.Beacon.Config;

import me.HALD91.Beacon.Main.Main;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ConfigCreator {
    public File file2 = new File(Main.getInstance().getDataFolder(),"/Config/messages.yml");

    public YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(this.file2);

//    public int createConfig() {
//        if (this.file.exists())
//            return 0;
//        this.yamlConfiguration.set("plugin.prefix", "&8[&eSystem&8]");
//        this.yamlConfiguration.set("plugin.cooldown", Integer.valueOf(60));
//        this.yamlConfiguration.set("plugin.diffitems", Integer.valueOf(4));
//        this.yamlConfiguration.set("plugin.dropamount", Integer.valueOf(24));
//        try {
//            this.yamlConfiguration.save(this.file);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        createMessages();
//        return 1;
//    }
}
