package thedavid.tc_new_economy.convertCurrency;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import thedavid.tc_new_economy.TC_new_Economy;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class converter {
    public converter(TC_new_Economy plugin){
        this.plugin = plugin;
    }
    TC_new_Economy plugin;
    public final Integer convertRate = 100000;

    public void convertHand(Player player){
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        if(itemStack.getType() != Material.COPPER_INGOT){
            player.sendMessage(Component.text()
                            .append(Component.text("您手上拿的物品不是 銅錠 , 無法轉換至金錢").color(NamedTextColor.RED)
                            ));
            player.playSound(player, Sound.ENTITY_VILLAGER_NO, 1f, 2f);
            return;
        }
        int amount = itemStack.getAmount();
        int vaultAmount = plugin.dataHandler.getMoneyVault();
        double addmoney = 0;
        for(int i = 0; i<amount; i++){
            vaultAmount -= 1;
            addmoney += getOneItemMoney(vaultAmount);
        }
        plugin.economy.addPlayerMoney(player, addmoney);
        plugin.dataHandler.setMoneyVault(vaultAmount);
        DecimalFormat df = new DecimalFormat("####0.00");
        player.sendMessage(Component.text()
                .append(Component.text("已將 " + amount + " 個銅錠 轉換成 " + df.format(addmoney) + " $").color(NamedTextColor.GREEN))
        );
        player.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
        player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1f, 2f);
    }
    public void convertInventory(Player player){
        AtomicInteger amount = new AtomicInteger();
        player.getInventory().all(Material.COPPER_INGOT).forEach((k,v) ->{
            amount.addAndGet(v.getAmount());
        });
        if(amount.get() == 0){
            player.sendMessage(Component.text()
                    .append(Component.text("您背包內沒有 銅錠 , 無法轉換至金錢").color(NamedTextColor.RED)
                    ));
            player.playSound(player, Sound.ENTITY_VILLAGER_NO, 1f, 2f);
            return;
        }
        int vaultAmount = plugin.dataHandler.getMoneyVault();
        double addmoney = 0;
        for(int i = 0; i< amount.get(); i++){
            vaultAmount -= 1;
            addmoney += getOneItemMoney(vaultAmount);
        }
        plugin.economy.addPlayerMoney(player, addmoney);
        plugin.dataHandler.setMoneyVault(vaultAmount);
        DecimalFormat df = new DecimalFormat("####0.00");
        player.sendMessage(Component.text()
                .append(Component.text("已將 " + amount + " 個銅錠 轉換成 " + df.format(addmoney) + " $").color(NamedTextColor.GREEN))
        );
        player.getInventory().remove(Material.COPPER_INGOT);
        player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1f, 2f);
    }
    public void convertAmount(Player player, Integer wantAmount){
        AtomicInteger hasAmount = new AtomicInteger();
        player.getInventory().all(Material.COPPER_INGOT).forEach((k,v) ->{
            hasAmount.addAndGet(v.getAmount());
        });
        if(hasAmount.get() == 0){
            player.sendMessage(Component.text()
                    .append(Component.text("您背包內沒有 銅錠 , 無法轉換至金錢").color(NamedTextColor.RED)
                    ));
            player.playSound(player, Sound.ENTITY_VILLAGER_NO, 1f, 2f);
            return;
        }
        int amount = hasAmount.get();
        if(hasAmount.get() >= wantAmount){
            amount = wantAmount;
        }
        int vaultAmount = plugin.dataHandler.getMoneyVault();
        double addmoney = 0;
        for(int i = 0; i< amount; i++){
            vaultAmount -= 1;
            addmoney += getOneItemMoney(vaultAmount);
        }
        plugin.economy.addPlayerMoney(player, addmoney);
        plugin.dataHandler.setMoneyVault(vaultAmount);
        DecimalFormat df = new DecimalFormat("####0.00");
        player.sendMessage(Component.text()
                .append(Component.text("已將 " + amount + " 個銅錠 轉換成 " + df.format(addmoney) + " $").color(NamedTextColor.GREEN))
        );
//        player.getInventory().remove(Material.COPPER_INGOT);
        AtomicInteger finalAmount = new AtomicInteger(amount);
        player.getInventory().all(Material.COPPER_INGOT).forEach((k, v) ->{
            int thisItemAmount = v.getAmount();
            if(finalAmount.get() <= 0){
                return;
            }
            if(finalAmount.get() >= thisItemAmount){
                v.setAmount(0);
            }else{
                v.setAmount(thisItemAmount-finalAmount.get());
            }
            finalAmount.addAndGet(-thisItemAmount);
        });
        player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1f, 2f);
    }
    private double getOneItemMoney(Integer vaultAmount){
        return (double) vaultAmount / convertRate;
    }
}
