package megagrnyt.customdurability.listeners;

import de.tr7zw.nbtapi.NBT;
import de.tr7zw.nbtapi.iface.ReadWriteNBT;
import megagrnyt.customdurability.CustomDurability;
import megagrnyt.customdurability.api.durabilityManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

public class ItemDamageEvent implements Listener {

    private durabilityManager manager = CustomDurability.getmanager();

    @EventHandler(priority = EventPriority.HIGH)
    public void ItemDamage(PlayerItemDamageEvent event) {
        if(event.isCancelled()) {
            return;
        }
        ItemStack item = event.getItem();
        if(item != null && !item.getType().isAir() && item.hasItemMeta()) {
            if(manager.hasCustomDurability(item)) {
                int damageAmount = event.getDamage();
                manager.damageItem(item, damageAmount);
            }
        }
        return;
    }
}
