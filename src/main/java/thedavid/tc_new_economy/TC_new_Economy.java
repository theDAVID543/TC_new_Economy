package thedavid.tc_new_economy;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import thedavid.tc_new_economy.convertCurrency.*;
import thedavid.tc_new_economy.admin.adminCommandHandler;
import thedavid.tc_new_economy.handler.commandManager.commandHandler;
import thedavid.tc_new_economy.handler.economy;

public final class TC_new_Economy extends JavaPlugin {
    public JavaPlugin instance;
    public dataHandler dataHandler;
    public converter converter;
    public economy economy;
    private placeholderRegister placeholderRegister;

    @Override
    public void onEnable() {
        instance = this;
        dataHandler = new dataHandler(this);
        converter = new converter(this);
        economy = new economy(this);
        new addMoneyTask(this).runAddMoneyTask();
        placeholderRegister = new placeholderRegister(this);
        placeholderRegister.register();
        Bukkit.getPluginCommand("tceconomyadmin").setExecutor(new adminCommandHandler(this));
        Bukkit.getPluginCommand("tceconomy").setExecutor(new commandHandler(this));
        dataHandler.createCustomConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        placeholderRegister.unregister();
    }
}
