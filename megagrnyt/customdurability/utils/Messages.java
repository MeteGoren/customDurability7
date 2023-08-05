package megagrnyt.customdurability.utils;

import megagrnyt.customdurability.CustomDurability;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;
import java.util.stream.Collectors;

public class Messages {
    public static final FileConfiguration config = CustomDurability.getInstance().getConfig();

    public static final String prefix = HexUtil.color(config.getString("prefix"));
    public static final String title =  HexUtil.color(config.getString("gui.title"));
    public static final List<String> itemDescription = HexUtil.color(config.getStringList("gui.item-description"));
    public static final String PERMISSION = config.getString("gui.permission");

    public static final String NO_PERMISSION =  HexUtil.color(config.getString("messages.NO_PERMISSION")).replace("%prefix%", prefix);
    public static final String NO_ITEM = HexUtil.color(config.getString("messages.NO_ITEM")).replace("%prefix%", prefix);
    public static final String APPLIED_DURABILTIY = HexUtil.color(config.getString("messages.APPLIED_DURABILTIY")).replace("%prefix%", prefix);
    public static final List<String> helpCommand = config.getStringList("messages.HELP_COMMAND")
            .stream()
            .map(i -> i.replace("%prefix%", prefix))
            .map(HexUtil::color)
            .collect(Collectors.toList());
    public static final String REFRESH_CONFIG = HexUtil.color(config.getString("messages.REFRESH_CONFIG")).replace("%prefix%", prefix);

    public static boolean repairEnabled = config.getBoolean("repair.enabled");
    public static int repairLevelCost = config.getInt("repair.level-cost");


}
