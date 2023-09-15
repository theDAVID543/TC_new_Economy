package thedavid.tc_new_economy.admin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import su.nightexpress.coinsengine.api.CoinsEngineAPI;
import su.nightexpress.coinsengine.api.currency.Currency;
import su.nightexpress.coinsengine.data.impl.CoinsUser;
import thedavid.tc_new_economy.TC_new_Economy;

import java.util.UUID;

public class adminCommandHandler implements CommandExecutor {
    public adminCommandHandler(TC_new_Economy plugin){
        this.plugin = plugin;
    }
    private TC_new_Economy plugin;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(!(sender instanceof Player)){
            return false;
        }
//        if(args[0].equals("self")){
//            Player playerSender = (Player) sender;
//            XConomyAPI xcapi = new XConomyAPI();
//            BigDecimal money = xcapi.getPlayerData(playerSender.getUniqueId()).getBalance();
//            playerSender.sendMessage(
//                    Component.text(money.toString())
//            );
//
//            setPlayerMoney(playerSender.getUniqueId(), money.doubleValue());
//        } else if(args[0].equals("merge")){
//            XConomyAPI xcapi = new XConomyAPI();
//            Arrays.stream(Bukkit.getOfflinePlayers()).forEach(offlinePlayer -> {
//                UUID uuid = offlinePlayer.getUniqueId();
//                PlayerData playerData = xcapi.getPlayerData(uuid);
//                if(playerData != null){
//                    BigDecimal money = playerData.getBalance();
//                    if(money != null){
//                        Bukkit.getLogger().info(offlinePlayer.getName() + " : " + money);
//                        setPlayerMoney(uuid, money.doubleValue());
//                    }
//                }
//            });
//        }

        return true;
    }
    public void setPlayerMoney(UUID uuid, Double money){
        Currency currency = CoinsEngineAPI.getCurrency("money");
        if(CoinsEngineAPI.getUserData(uuid) == null){
            CoinsUser user = new CoinsUser(CoinsEngineAPI.PLUGIN, uuid, Bukkit.getOfflinePlayer(uuid).getName());
            user.getCurrencyData(currency).setBalance(money);
            CoinsEngineAPI.PLUGIN.getData().addUser(user);
        }
    }
}
