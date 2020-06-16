package tv.twitch.riespanda.autosmelt.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import tv.twitch.riespanda.autosmelt.AutoSmelt;

import java.util.List;

public class BlockBreak implements Listener {
    private AutoSmelt plugin;

    public BlockBreak(AutoSmelt plugin) {
        this.plugin = plugin;
    }

    public ItemStack pick = new ItemStack(Material.DIAMOND_PICKAXE);

    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        ItemStack iron = new ItemStack(Material.IRON_INGOT);
        ItemStack gold = new ItemStack(Material.GOLD_INGOT);

        if (!plugin.getConfig().getBoolean("smelt-pick")) {
            if (player.hasPermission("AutoSmelt.smelt") || player.hasPermission("AutoSmelt.*")) {
                if (!player.getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH)) {
                    switch (event.getBlock().getType()) {
                        case IRON_ORE:
                            player.getInventory().remove(Material.IRON_ORE);
                            player.getInventory().addItem(iron);
                            break;
                        case GOLD_ORE:
                            player.getInventory().remove(Material.GOLD_ORE);
                            player.getInventory().addItem(gold);
                            break;
                        default:
                            break;
                    }
                }
            }
        } else {
            if (player.hasPermission("AutoSmelt.smelt") || player.hasPermission("AutoSmelt.*")) {
                String pickName = plugin.getConfig().getString(ChatColor.translateAlternateColorCodes('&', "pick-name"));
                List<String> configLore = plugin.getConfig().getStringList(ChatColor.translateAlternateColorCodes('&', "pick-lore"));
                ItemMeta pickMeta = pick.getItemMeta();
                if (pickMeta != null) {
                    pickMeta.setDisplayName(pickName);
                    pickMeta.setLore(configLore);
                    pick.setItemMeta(pickMeta);
                }
                if (player.getInventory().getItemInMainHand().equals(pick)) {
                    if (!player.getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH)) {
                        switch (event.getBlock().getType()) {
                            case IRON_ORE:
                                player.getInventory().remove(Material.IRON_ORE);
                                player.getInventory().addItem(iron);
                                break;
                            case GOLD_ORE:
                                player.getInventory().remove(Material.GOLD_ORE);
                                player.getInventory().addItem(gold);
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        }
    }
}
