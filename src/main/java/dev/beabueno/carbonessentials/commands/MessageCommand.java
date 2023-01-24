package dev.beabueno.carbonessentials.commands;

import dev.beabueno.carbonessentials.CarbonEssentials;
import dev.beabueno.carbonessentials.MessageManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageCommand implements CommandExecutor {

    private MessageManager messagemanager;
    public MessageCommand(MessageManager messagemanager) {
        this.messagemanager = messagemanager;
    }

    //MESSAGES
    static final String MESSAGE_SENT = ChatColor.BLUE + "You -> %s:" + ChatColor.GRAY + "%s";
    static final String MESSAGE_ARRIVES = ChatColor.BLUE + "%s -> You:" + ChatColor.GRAY + "%s";
    static final String PLAYER_NOT_ONLINE = ChatColor.RED + "This player was not found!";
    static final String PLAYER_NOPERM = ChatColor.RED + "You don't have permission to do this!";
    static final String WRONG_USAGE = ChatColor.RED + "Invalid Usage! Use /message <player> [message]";

    //PERMISSIONS
    static final String MESSAGE_PERM = "carbonessentials.message";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(MESSAGE_PERM)) {
                if (args.length >= 2) {
                    if (Bukkit.getPlayerExact(args[0]) != null) {
                        Player target = Bukkit.getPlayerExact(args[0]);
                        StringBuilder builder = new StringBuilder();

                        for (int i = 1; i < args.length; i++) {
                            builder.append(args[i]).append(" ");
                        }

                        player.sendMessage(String.format(MESSAGE_SENT, target.getName(), builder));
                        target.sendMessage(String.format(MESSAGE_ARRIVES, target.getName(), builder));

                        messagemanager.getRecentMessages().put(player.getUniqueId(), target.getUniqueId());
                    } else {
                        player.sendMessage(PLAYER_NOT_ONLINE);
                    }
                } else {
                    player.sendMessage(WRONG_USAGE);
                }
            } else {
                player.sendMessage(PLAYER_NOPERM);
            }

        } else {

        }
        return false;
    }
}
