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

    static final String PERM_OWN = "carbonessentials.feed";
    static final String PERM_OTHERS = "carbonessentials.feed.others";

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
                    p.sendMessage(CommandMessages.PLAYER_NOPERM);
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
                        p.sendMessage(CommandMessages.PLAYER_NOTONLINE);
                        return false;
                    }
                } else {
                    p.sendMessage(CommandMessages.PLAYER_NOPERM);
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
