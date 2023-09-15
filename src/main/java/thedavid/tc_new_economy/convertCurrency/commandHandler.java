package thedavid.tc_new_economy.convertCurrency;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import thedavid.tc_new_economy.TC_new_Economy;

import java.util.ArrayList;
import java.util.List;

public class commandHandler implements CommandExecutor, TabCompleter {
    public commandHandler(TC_new_Economy plugin){
        this.plugin = plugin;
    }
    private TC_new_Economy plugin;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(!(sender instanceof Player)){
            return false;
        }
        Player player = (Player) sender;
        if(args.length > 0){
            if(args[0].equals("convert")){
                if(args.length > 1){
                    if(args[1].equals("hand")){
                        plugin.converter.convertHand(player);
                        return true;
                    }else if (args[1].equals("inventory")) {
                        plugin.converter.convertInventory(player);
                        return true;
                    }else if(args[1].equals("amount")){
                        if(args.length > 2){
                            plugin.converter.convertAmount(player, Integer.parseInt(args[2]));
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            return null;
        }
        if(args.length == 1){
            List<String> completer = new ArrayList<>();
            completer.add("convert");
            return completer;
        }
        if(args.length == 2){
            List<String> completer = new ArrayList<>();
            completer.add("hand");
            completer.add("inventory");
            completer.add("amount");
            return completer;
        }
        return null;
    }
}
