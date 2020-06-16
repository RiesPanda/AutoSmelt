package tv.twitch.riespanda.autosmelt.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tv.twitch.riespanda.autosmelt.events.BlockBreak;

public class GivePickCommand implements CommandExecutor {
    private BlockBreak BlockBreak;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(player.hasPermission("AutoSmelt.give") || player.hasPermission("AutoSmelt.*")) {
                if(command.getName().equalsIgnoreCase("give")) {
                    if (args.length == 0) {
                        player.sendMessage(ChatColor.GREEN + "You just got the AutoSmelt pick!");
                        player.getInventory().addItem(BlockBreak.pick);
                    } else {
                        if (player.hasPermission("AutoSmelt.giveothers")) {
                            Player target = Bukkit.getPlayerExact(args[0]);
                            if(target != null){
                                target.sendMessage(ChatColor.GOLD + " " + player + ChatColor.GREEN + " Has just gave you the AutoSmelt pick!");
                                target.getInventory().addItem(BlockBreak.pick);
                            } else {
                                player.sendMessage(ChatColor.RED + "This player doesn't exist or isn't online.");
                            }
                        }
                    }
                }
            }
        } else {
            if(args.length == 0){
                System.out.println("You can't give the console an AutoSmelt pick!");
            } else {
                Player target = Bukkit.getPlayerExact(args[0]);
                if (target == null) {
                    System.out.println("This player doesn't exist or isn't online.");
                } else {
                    target.getInventory().addItem(BlockBreak.pick);
                    target.sendMessage(ChatColor.GREEN + "You have been given an AutoSmelt pick by the console!");
                }
            }
        }
        return true;
    }
}
