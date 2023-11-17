package thedavid.tc_new_economy.handler.commandManager;

import org.bukkit.entity.Player;

import java.util.Map;

public interface SubCommand{
    void execute(Player player, Map<String, String> parsedArgs);
}
