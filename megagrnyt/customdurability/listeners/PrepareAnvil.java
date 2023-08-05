package megagrnyt.customdurability.listeners;

import megagrnyt.customdurability.CustomDurability;
import megagrnyt.customdurability.api.durabilityManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Map;

public class PrepareAnvil implements Listener {
/*
    private durabilityManager manager = CustomDurability.getmanager();
    private FileConfiguration config = CustomDurability.getInstance().getConfig();
    @EventHandler
    public void onPrepareAnvil(PrepareAnvilEvent event) {
        ItemStack first = event.getInventory().getItem(0);
        ItemStack second = event.getInventory().getItem(1);
        if(first != null && second != null) {
            if(!first.getType().equals(second.getType())) {
                return;
            }
            if(!first.hasItemMeta() || !second.hasItemMeta()) {
                return;
            }
            if(config.getBoolean("repair.enabled")) {
                if (!manager.hasCustomDurability(first) || !manager.hasCustomDurability(second)) {
                    return;
                }
                int durability = manager.getDurability(first);
                int durability2 = manager.getDurability(second);
                int maxdurability = manager.getMaxDurability(first);
                if (durability >= maxdurability && durability + durability2 > maxdurability) {
                    return;
                }
                ItemStack firstCopy = first.clone();
                ItemStack secondCopy = second;
                EnchantmentStorageMeta stored = (EnchantmentStorageMeta) secondCopy.getItemMeta();
                stored.getStoredEnchants().forEach(
                        ((enchantment, level) -> {
                            firstCopy.addEnchantment(enchantment, level);
                        }
                ));
                event.getInventory().setRepairCost(config.getInt("repair.level-cost"));

                manager.applyCustomDurabilityItem(firstCopy, maxdurability, durability + durability2);
                event.setResult(firstCopy);
            }
        }
    }*/
}
