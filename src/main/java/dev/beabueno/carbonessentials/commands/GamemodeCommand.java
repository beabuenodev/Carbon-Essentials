package dev.beabueno.carbonessentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommand implements CommandExecutor {

    static final String GAMEMODE_CHANGED = "Gamemode changed to ";
    static final String GAMEMODE_NOTVALID = "Not a valid gamemode.";
    static final String PLAYER_NOTONLINE = "This player is not online";
    static final String PLAYER_NOPERMISSION = "You don't have permission to do this";

    static final String PERM_ALL = "basis.core.gm.*";
    static final String PERM_PREFIX = "basis.core.gm.";
    static final String PERM_OTHERS = "basis.core.gm.others";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (args.length == 1 && label.equalsIgnoreCase("gm")) {
                //If there's no username to change gm

                try {
                    GameMode gm = GameMode.valueOf(args[0].toUpperCase());
                    if (p.hasPermission(PERM_ALL) || p.hasPermission(PERM_PREFIX + args[0].toLowerCase())) { //Perms check
                        p.setGameMode(gm);
                        p.sendMessage(ChatColor.GREEN + GAMEMODE_CHANGED + gm.toString().toLowerCase());
                        return true;
                    } else {
                        p.sendMessage(ChatColor.RED + PLAYER_NOPERMISSION);
                        return false;
                    }

                } catch (IllegalArgumentException e) { //Exception in case args[0] isn't a valid value of enum
                    p.sendMessage(ChatColor.RED + GAMEMODE_NOTVALID);
                    return false;
                }

            } else if (args.length == 2 && label.equalsIgnoreCase("gm")) {
                //If there's an username to change gm

                try {
                    GameMode gm = GameMode.valueOf(args[0].toUpperCase());
                    Player target = Bukkit.getPlayer(args[1]);
                    if (p.hasPermission(PERM_OTHERS)) { //Permission Check
                        if (target != null) {
                            target.setGameMode(gm);
                            target.sendMessage(ChatColor.GREEN + GAMEMODE_CHANGED + gm.toString().toLowerCase());
                            p.sendMessage(ChatColor.GREEN + GAMEMODE_CHANGED + gm.toString().toLowerCase() + " for " + target.getDisplayName());
                            return true;
                        } else { //If the player is offline (target is null)
                            p.sendMessage(ChatColor.RED + PLAYER_NOTONLINE);
                            return false;
                        }
                    } else {
                        p.sendMessage(ChatColor.RED + PLAYER_NOPERMISSION);
                        return false;
                    }


                } catch (IllegalArgumentException e) { //Exception in case args[0] isn't a valid value of enum
                    p.sendMessage(ChatColor.RED + GAMEMODE_NOTVALID);
                    return false;
                }

            } else {
                return false;
            }
        }

        return false;
    }
}
