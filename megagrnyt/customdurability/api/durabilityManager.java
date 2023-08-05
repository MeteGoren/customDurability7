package megagrnyt.customdurability.api;

import de.tr7zw.nbtapi.NBT;
import de.tr7zw.nbtapi.NBTItem;
import de.tr7zw.nbtapi.iface.ReadWriteNBT;
import megagrnyt.customdurability.utils.HexUtil;
import megagrnyt.customdurability.utils.Messages;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class durabilityManager {
    public int getDurability(ItemStack item) {
        return new NBTItem(item).getCompound("customdurability").getInteger("durability");
    }
    public int getMaxDurability(ItemStack item) {
        return new NBTItem(item).getCompound("customdurability").getInteger("maxdurability");
    }
    public Short getItemMaxDurability(ItemStack item) {
        return item.getType().getMaxDurability();
    }
    public boolean hasCustomDurability(ItemStack item) {
        return NBT.get(item, nbt -> nbt.getCompound("customdurability")) != null && NBT.get(item, nbt -> nbt.getCompound("customdurability")).getInteger("maxdurability") != null && NBT.get(item, nbt -> nbt.getCompound("customdurability")).getInteger("durability") != null;
    }
    public void repairItem(ItemStack item, int repairAmount) {
        int durability = getDurability(item) + repairAmount;
        applyCustomDurabilityItem(item, getMaxDurability(item), durability);
    }
    public void damageItem(ItemStack item, int damageAmount) {
        int durability = getDurability(item) - damageAmount;
        applyCustomDurabilityItem(item, getMaxDurability(item), durability);
    }
    public void applyCustomDurabilityItem(ItemStack item, int maxdurability, int durability) {
        if(durability > maxdurability) {
            durability = maxdurability;
        }
        int finalDurability = durability;
        NBT.modify(item, nbt -> {
            ReadWriteNBT compound = nbt.getOrCreateCompound("customdurability");
            compound.setInteger("durability", finalDurability);
            compound.setInteger("maxdurability", maxdurability);
            int itemDurability = getItemMaxDurability(item);
            int percentage = itemDurability - (itemDurability * finalDurability / maxdurability);
            nbt.setInteger("Damage", percentage);
        });
        if(durability <= 0) {
            item.setAmount(0);
        }
    }

    public ItemStack initGuiItem(ItemStack item, String name) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(meta.hasDisplayName() ? meta.getDisplayName() : HexUtil.color("<#fc7095>"+item.getType().name().toLowerCase()));
        List<String> lore = new ArrayList<>();
        if(meta.hasLore()) {
            lore = meta.getLore();
        }

        int durability = getDurability(item);
        int maxdurability = getMaxDurability(item);
        int percentage = 100 * durability / maxdurability;

        String mending = HexUtil.color(meta.hasEnchant(Enchantment.MENDING) ? "<green>☑": "<red>☒");

        List<String> loreDescription = Messages.itemDescription;
        for(String str : loreDescription) {
            lore.add(str.replace("%player%", name)
                    .replace("%current%", String.valueOf(durability))
                    .replace("%max%", String.valueOf(maxdurability))
                    .replace("%percentage%", String.valueOf(percentage))
                    .replace("%repair%", mending)
                    );
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;

    }

}
