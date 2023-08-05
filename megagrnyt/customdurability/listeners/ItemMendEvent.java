package megagrnyt.customdurability.listeners;


import megagrnyt.customdurability.CustomDurability;
import megagrnyt.customdurability.api.durabilityManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemMendEvent;
import org.bukkit.inventory.ItemStack;

public class ItemMendEvent implements Listener {

    private durabilityManager manager = CustomDurability.getmanager();

    @EventHandler(priority = EventPriority.HIGH)
    public void ItemMend(PlayerItemMendEvent event) {
        if(event.isCancelled()) {
            return;
        }
        ItemStack item = event.getItem();
        if(item != null && !item.getType().isAir() && item.hasItemMeta()) {
            if(manager.hasCustomDurability(item)) {
                int repairAmount = event.getRepairAmount();
                manager.repairItem(item, repairAmount);
            }
        }
        return;
    }
}
