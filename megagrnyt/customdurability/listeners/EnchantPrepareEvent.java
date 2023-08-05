package megagrnyt.customdurability.listeners;

import megagrnyt.customdurability.CustomDurability;
import megagrnyt.customdurability.api.durabilityManager;
import megagrnyt.customdurability.utils.Messages;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.inventory.ItemStack;

public class EnchantPrepareEvent implements Listener {
/*
    private durabilityManager manager = CustomDurability.getmanager();
    @EventHandler
    public void enchant(PrepareItemEnchantEvent event) {
        if(event.isCancelled()) {
            return;
        }
        ItemStack item = event.getItem();
        if(manager.hasCustomDurability(item)) {
            if(Messages.repairEnabled) {

            }
        }
    }*/
}
