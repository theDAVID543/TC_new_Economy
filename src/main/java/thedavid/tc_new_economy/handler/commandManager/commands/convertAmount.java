package thedavid.tc_new_economy.handler.commandManager.commands;

import org.bukkit.entity.Player;
import thedavid.tc_new_economy.TC_new_Economy;
import thedavid.tc_new_economy.handler.commandManager.SubCommand;

import java.util.Map;

public class convertAmount implements SubCommand {
    public convertAmount(TC_new_Economy plugin){
        this.plugin = plugin;
    }
    private TC_new_Economy plugin;
    @Override
    public void execute(Player player, Map<String, String> parsedArgs) {
        plugin.converter.convertAmount(player, Integer.parseInt(parsedArgs.get("amount")));
    }
}
