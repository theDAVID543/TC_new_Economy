package thedavid.tc_new_economy.convertCurrency;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import thedavid.tc_new_economy.TC_new_Economy;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class dataHandler {
    public dataHandler(TC_new_Economy plugin){
        this.plugin = plugin;
    }
    private TC_new_Economy plugin;
    private File dataConfigFile;
    private FileConfiguration dataConfig;
    private final Integer defaultVaultMoney = 200000;
    public void createCustomConfig() {
        dataConfigFile = new File(plugin.instance.getDataFolder(), "data/moneyVault.yml");
        if (!dataConfigFile.exists()) {
            dataConfigFile.getParentFile().mkdirs();
            plugin.instance.saveResource("data/moneyVault.yml", false);
        }

        dataConfig = new YamlConfiguration();
        try {
            dataConfig.load(dataConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
    public void setMoneyVault(Integer amount) {
        dataConfig.set("moneyVault", amount);
        try {
            dataConfig.save(dataConfigFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Integer getMoneyVault() {
        if(dataConfig.getString("moneyVault") == null){
            setMoneyVault(defaultVaultMoney);
            return defaultVaultMoney;
        }
        return dataConfig.getInt("moneyVault");
    }
}
