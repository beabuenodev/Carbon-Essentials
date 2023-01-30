package dev.beabueno.carbonessentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
//kick <player> [reason]

public class KickCommand implements CommandExecutor {
    static final String NO_REASON = "no reason!";
    static final String WRONG_USAGE = "Invalid usage! Usage: /kick <player> [reason]";
    static final String KICK_PERM = "carbonessentials.kick";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (!sender.hasPermission(KICK_PERM)) {
                return false;
            }
        }
        if (args.length >= 1) {
            if (Bukkit.getPlayer(args[0]) != null) {
                Player t = Bukkit.getPlayer(args[1]);
                if (args.length == 1) {
                    t.kickPlayer(NO_REASON);
                } else {
                    StringBuilder builder = new StringBuilder();
                    for (int i = 1; i < args.length; i++) {
                        builder.append(args[i]).append(" ");
                    }
                    t.kickPlayer(builder.toString());
                }
            } else {
                if (sender instanceof Player) {
                    sender.sendMessage(CommandMessages.PLAYER_NOTONLINE);
                } else {
                    System.out.println(CommandMessages.PLAYER_NOTONLINE);
                }
            }
        } else {
            if (sender instanceof Player) {
                sender.sendMessage(WRONG_USAGE);
            } else {
                System.out.println(WRONG_USAGE);
            }
        }
        return false;
    }
}
