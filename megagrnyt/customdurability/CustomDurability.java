package megagrnyt.customdurability;

import megagrnyt.customdurability.api.durabilityManager;
import megagrnyt.customdurability.commands.durabilityCommand;
import megagrnyt.customdurability.listeners.*;
import megagrnyt.customdurability.utils.HexUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class CustomDurability extends JavaPlugin {

    private static CustomDurability instance;
    private static durabilityManager manager;

    public static CustomDurability getInstance() {
        return instance;
    }
    public static durabilityManager getmanager() {
        return manager;
    }

    @Override
    public void onEnable() {
        instance = this;
        manager = new durabilityManager();
        saveDefaultConfig();
        registerListeners();
        registerCommands();
    }

    private void registerCommands() {
        getCommand("customdurability").setExecutor(new durabilityCommand());
        getCommand("customdurability").setTabCompleter(new durabilityCommand());
    }

    private void registerListeners() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new ItemDamageEvent(), this);
        pm.registerEvents(new ItemMendEvent(), this);
        pm.registerEvents(new InventoryClickAndClose(), this);
        // pm.registerEvents(new PrepareAnvil(), this); broken
        // pm.registerEvents(new EnchantPrepareEvent(), this); broken
        print(" ");
        print("<green>ItemDamageEvent has been loaded.");
        print("<green>ItemMendEvent has been loaded");
        print(" ");
    }

    public void print(String str) {
        getServer().getConsoleSender().sendMessage(HexUtil.color(str));
    }

    @Override
    public void onDisable() {
        Bukkit.getOnlinePlayers().stream().filter(player -> player.hasMetadata("customdurability")).forEach(Player::closeInventory);
    }
}
