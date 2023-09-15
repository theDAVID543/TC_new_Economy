package thedavid.tc_new_economy.handler;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import su.nightexpress.coinsengine.api.CoinsEngineAPI;
import su.nightexpress.coinsengine.api.currency.Currency;
import su.nightexpress.coinsengine.data.impl.CoinsUser;
import thedavid.tc_new_economy.TC_new_Economy;

import java.util.UUID;

public class economy {
    public economy(TC_new_Economy plugin){
        this.plugin = plugin;
    }
    public TC_new_Economy plugin;
    public Double getPlayerMoney(Player player){
        Currency currency = CoinsEngineAPI.getCurrency("money");
        return CoinsEngineAPI.getBalance(player,currency);
    }
    public void addPlayerMoney(Player player, Double amount){
        Currency currency = CoinsEngineAPI.getCurrency("money");
        CoinsEngineAPI.addBalance(player,currency, amount);
    }
}
