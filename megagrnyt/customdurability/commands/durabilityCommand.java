package megagrnyt.customdurability.commands;

import megagrnyt.customdurability.CustomDurability;
import megagrnyt.customdurability.api.durabilityManager;
import megagrnyt.customdurability.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.ArrayList;
import java.util.List;

public class durabilityCommand implements CommandExecutor, TabCompleter {

    private final durabilityManager manager = CustomDurability.getmanager();
    private final CustomDurability customDurability = CustomDurability.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(Messages.PERMISSION)) {
                if (args.length == 1 && args[0].equalsIgnoreCase("gui")) {
                    openGui(player);
                    return true;
                } else if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                    customDurability.reloadConfig();
                    player.sendMessage(Messages.REFRESH_CONFIG);
                    return true;
                } else if (args.length == 3 && args[0].equalsIgnoreCase("set")) {
                    applyDurability(player, args[1], args[2]);
                    return true;
                } else {
                    sendHelpMessage(player);
                }
            } else {
                player.sendMessage(Messages.NO_PERMISSION);
            }
        }
        return true;
    }

    private void applyDurability(Player player, String value1, String value2) {
        ItemStack item = player.getInventory().getItemInMainHand();
        if(item != null && !item.getType().isAir()) {
            int maxdurability, durability;
            try {
                maxdurability = Integer.valueOf(value1);
                durability = Integer.valueOf(value2);
            } catch (NumberFormatException e) {
                player.sendMessage("Something went wrong: " + e.getMessage());
                return;
            }
            if(durability > maxdurability) {
                durability = maxdurability;
            }
            manager.applyCustomDurabilityItem(item, maxdurability, durability);
            player.sendMessage(Messages.APPLIED_DURABILTIY);
        } else {
            player.sendMessage(Messages.NO_ITEM);
        }
    }

    private void openGui(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 54, Messages.title);
        player.openInventory(inventory);
        player.setMetadata("customdurability", new FixedMetadataValue(customDurability, true));
        for (Player p : Bukkit.getOnlinePlayers()) {
            ItemStack[] contents = p.getInventory().getContents();
            for (ItemStack item : contents) {
                if (item != null && !item.getType().isAir()) {
                    if (manager.hasCustomDurability(item)) {
                        ItemStack invItem = item.clone();
                        inventory.addItem(manager.initGuiItem(invItem, p.getName()));
                    }
                }
            }
        }
    }

    private void sendHelpMessage(Player player) {
        for(String str : Messages.helpCommand) {
            player.sendMessage(str);
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> output = new ArrayList<>();
        if (args.length == 1 && sender.hasPermission(Messages.PERMISSION)) {
            output.add("gui");
            output.add("reload");
            output.add("set <maxdurability> <durability>");
            return output;
        }
        return null;
    }

}
