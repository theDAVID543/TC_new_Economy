package thedavid.tc_new_economy.convertCurrency;

import org.bukkit.scheduler.BukkitRunnable;
import thedavid.tc_new_economy.TC_new_Economy;

public class addMoneyTask {
    public addMoneyTask(TC_new_Economy plugin){
        this.plugin = plugin;
    }
    private TC_new_Economy plugin;
    private final Integer defaultAddMoney = 1;
    public void runAddMoneyTask(){
        new BukkitRunnable(){
            @Override
            public void run() {
                plugin.dataHandler.setMoneyVault(plugin.dataHandler.getMoneyVault() + defaultAddMoney);
            }
        }.runTaskTimer(plugin.instance, 20*60L,20*60L);
    }
}
