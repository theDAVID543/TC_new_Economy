package thedavid.tc_new_economy;

import me.yic.xconomy.api.XConomyAPI;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import su.nightexpress.coinsengine.api.CoinsEngineAPI;
import su.nightexpress.coinsengine.api.currency.Currency;

import java.math.BigDecimal;

public class commandHandler implements CommandExecutor {
    public commandHandler(TC_new_Economy plugin){
        this.plugin = plugin;
    }
    private TC_new_Economy plugin;
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(!(sender instanceof Player)){
            return false;
        }
        Player playerSender = (Player) sender;
        XConomyAPI xcapi = new XConomyAPI();
        BigDecimal money = xcapi.getPlayerData(playerSender.getUniqueId()).getBalance();
        playerSender.sendMessage(
                Component.text(money.toString())
        );


        Currency currency = CoinsEngineAPI.getCurrency("money");
        return true;
    }
}
