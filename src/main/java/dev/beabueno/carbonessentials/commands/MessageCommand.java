package dev.beabueno.carbonessentials.commands;

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
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length >= 2) {
                if (Bukkit.getPlayerExact(args[0]) != null) {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    StringBuilder builder = new StringBuilder();

                    for (int i = 1; i < args.length; i++) {
                        builder.append(args[i]).append(" ");
                    }

                    player.sendMessage("You ->" + target.getName() + ": " + builder);
                    target.sendMessage(player.getName() + "-> You: " + builder);

                    messagemanager.getRecentMessages().put(player.getUniqueId(), target.getUniqueId());
                } else {
                    player.sendMessage(ChatColor.RED + "This player was not found!");
                }
            } else {
                player.sendMessage(ChatColor.RED + "Invalid Usage! Use /message <player> [message]");
            }
        } else {

        }
        return false;
    }
}
