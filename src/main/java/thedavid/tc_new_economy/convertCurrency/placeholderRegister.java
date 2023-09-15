package thedavid.tc_new_economy.convertCurrency;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import thedavid.tc_new_economy.TC_new_Economy;

import java.text.DecimalFormat;

public class placeholderRegister extends PlaceholderExpansion {
    public placeholderRegister(TC_new_Economy plugin){
        this.plugin = plugin;
    }
    private TC_new_Economy plugin;
    @Override
    public @NotNull String getIdentifier() {
        return "tce";
    }

    @Override
    public @NotNull String getAuthor() {
        return "theDAVID54";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }
    @Override
    public boolean persist() {
        return true; // This is required or else PlaceholderAPI will unregister the Expansion on reload
    }
    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if(params.equalsIgnoreCase("convertrate")){
            DecimalFormat df = new DecimalFormat("####0.00");
            return df.format((double) plugin.dataHandler.getMoneyVault() / plugin.converter.convertRate);
        }else if(params.equalsIgnoreCase("vaultamount")){
            return String.valueOf(plugin.dataHandler.getMoneyVault());
        }

        return null; // Placeholder is unknown by the Expansion
    }
}
