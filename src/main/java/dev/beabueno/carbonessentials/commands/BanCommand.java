package dev.beabueno.carbonessentials.commands;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
//ban <player> [reason]

public class BanCommand implements CommandExecutor {
    static final String NO_REASON = "no reason!";
    static final String WRONG_USAGE = "Invalid usage! Usage: /ban <player> [reason]";
    static final String TARGET_BANNED = "You have been banned!";
    static final String BAN_PERM = "carbonessentials.ban";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (!sender.hasPermission(BAN_PERM)) {
                return false;
            }
        }
        if (args.length >= 1) {
            if (Bukkit.getOfflinePlayer(args[0]) != null) {
                OfflinePlayer t = Bukkit.getOfflinePlayer(args[1]);
                if (args.length == 1) {
                    Bukkit.getBanList(BanList.Type.NAME).addBan(t.getName(), NO_REASON, null, null);
                } else {
                    StringBuilder builder = new StringBuilder();
                    for (int i = 1; i < args.length; i++) {
                        builder.append(args[i]).append(" ");
                    }
                    Bukkit.getBanList(BanList.Type.NAME).addBan(t.getName(), builder.toString(), null, null);
                }
                if (Bukkit.getPlayer(t.getName()) != null) {
                    ((Player) t).kickPlayer(TARGET_BANNED);
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
