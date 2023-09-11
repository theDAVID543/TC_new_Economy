package thedavid.tc_new_economy;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class TC_new_Economy extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginCommand("tce").setExecutor(new commandHandler(this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
