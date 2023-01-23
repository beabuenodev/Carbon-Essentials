package dev.beabueno.carbonessentials.commands;

import dev.beabueno.carbonessentials.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ReplyCommand implements CommandExecutor {
    private MessageManager messagemanager;

    public ReplyCommand(MessageManager messagemanager) {
        this.messagemanager = messagemanager;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length >= 1) {
                if(messagemanager.getRecentMessages().containsKey(player.getUniqueId())) {
                    UUID uuid = messagemanager.getRecentMessages().get(player.getUniqueId());
                    if (Bukkit.getPlayer(uuid) != null) {
                        Player target = Bukkit.getPlayer(uuid);
                        StringBuilder builder = new StringBuilder();

                        for (int i = 1; i < args.length; i++) {
                            builder.append(args[i]).append(" ");
                        }

                        player.sendMessage("You ->" + target.getName() + ": " + builder);
                        target.sendMessage(player.getName() + "-> You: " + builder);
                    } else {
                        player.sendMessage(ChatColor.RED + "The person you messaged has gone offline!");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "You haven't messaged anybody recently!");
                }
            } else {
                player.sendMessage(ChatColor.RED + "Invalid Usage! Use /reply [message]");
            }
        } else {

        }
        return false;
    }
}
