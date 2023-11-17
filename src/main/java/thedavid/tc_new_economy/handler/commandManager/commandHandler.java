package thedavid.tc_new_economy.handler.commandManager;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import thedavid.tc_new_economy.TC_new_Economy;
import thedavid.tc_new_economy.handler.commandManager.commands.convertAmount;
import thedavid.tc_new_economy.handler.commandManager.commands.convertHand;
import thedavid.tc_new_economy.handler.commandManager.commands.convertIneventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class commandHandler implements CommandExecutor, TabCompleter {
    public commandHandler(TC_new_Economy plugin){
        this.plugin = plugin;
        subCommands.put("convert hand", new convertHand(plugin));
        subCommands.put("convert inventory", new convertIneventory(plugin));
        subCommands.put("convert amount {amount}", new convertAmount(plugin));
    }
    private TC_new_Economy plugin;
    private final Map<String, SubCommand> subCommands = new HashMap<>();
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(!(sender instanceof Player)){
            return false;
        }
        Player player = (Player) sender;
        Pair<SubCommand, Map<String, String>> matchedData = matchCommand(args);
        if (matchedData != null) {
            matchedData.getKey().execute(player, matchedData.getValue());
        } else {
            // 提示玩家該指令不存在或其他默認行為
            player.sendMessage(
                    Component.text("該指令不存在").color(NamedTextColor.RED));
        }

//        if(args.length > 0){
//            if(args[0].equals("convert")){
//                if(args.length > 1){
//                    if(args[1].equals("hand")){
//                        plugin.converter.convertHand(player);
//                        return true;
//                    }else if (args[1].equals("inventory")) {
//                        plugin.converter.convertInventory(player);
//                        return true;
//                    }else if(args[1].equals("amount")){
//                        if(args.length > 2){
//                            plugin.converter.convertAmount(player, Integer.parseInt(args[2]));
//                            return true;
//                        }
//                    }
//                }
//            }
//        }
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
    private Pair<SubCommand, Map<String, String>> matchCommand(String[] args) {
        for (Map.Entry<String, SubCommand> entry : subCommands.entrySet()) {
            String[] commandParts = entry.getKey().split(" ");
            if (commandParts.length != args.length) {
                continue;
            }

            Map<String, String> parsedArgs = new HashMap<>();
            boolean matches = true;
            for (int i = 0; i < commandParts.length; i++) {
                if (commandParts[i].startsWith("{") && commandParts[i].endsWith("}")) {
                    String paramName = commandParts[i].substring(1, commandParts[i].length() - 1);
                    parsedArgs.put(paramName, args[i]);
                } else if (!commandParts[i].equals(args[i])) {
                    matches = false;
                    break;
                }
            }

            if (matches) {
                return new Pair<>(entry.getValue(), parsedArgs);
            }
        }
        return null;
    }
}
