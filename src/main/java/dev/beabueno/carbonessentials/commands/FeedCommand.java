package dev.beabueno.carbonessentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FeedCommand implements CommandExecutor {

    static final int HUNGER_FULL = 20;

    static final String PLAYER_FULL = "Your hunger bar has been filled up";
    static final String PLAYER_NOPERM = "You don't have permission to do this";
    static final String PLAYER_NOTONLINE = "This player is not online";

    static final String PERM_OWN = "basis.core.feed";
    static final String PERM_OTHERS = "basis.core.feed.others";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            if (args.length == 0) { //No player to feed
                Player p = (Player) sender;
                if (p.hasPermission(PERM_OWN)){
                    p.setFoodLevel(HUNGER_FULL);
                    p.sendMessage(ChatColor.GREEN + PLAYER_FULL);
                    return true;
                } else {
                    p.sendMessage(ChatColor.RED + PLAYER_NOPERM);
                    return false;
                }
            } else if (args.length == 1) { //There's a player to feed
                Player target = Bukkit.getPlayer(args[0]);
                Player p = (Player) sender;
                if (p.hasPermission(PERM_OTHERS)) {
                    if (target != null) {
                        target.setFoodLevel(HUNGER_FULL);
                        p.sendMessage(ChatColor.GREEN + PLAYER_FULL);
                        return true;
                    } else {
                        p.sendMessage(ChatColor.RED + PLAYER_NOTONLINE);
                        return false;
                    }
                } else {
                    p.sendMessage(ChatColor.RED + PLAYER_NOPERM);
                    return false;
                }
            } else {
                return false;
            }
        } else {
            System.out.println("You're console! You don't have a hunger bar!");
        }

        return false;
    }
}
