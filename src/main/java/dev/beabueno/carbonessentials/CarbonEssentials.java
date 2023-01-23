package dev.beabueno.carbonessentials;

import dev.beabueno.carbonessentials.commands.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class CarbonEssentials extends JavaPlugin {

    MessageManager messagemanager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Carbon Essentials has loaded! BETA");
        registerCommands();
        constructManager();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("Carbon Essentials has shut down successfully!");
    }

    public void registerCommands() {
        getCommand("gm").setExecutor(new GamemodeCommand());
        getCommand("feed").setExecutor(new FeedCommand());
        getCommand("heal").setExecutor(new HealCommand());

        getCommand("message").setExecutor(new MessageCommand(messagemanager));
        getCommand("reply").setExecutor(new ReplyCommand(messagemanager));
    }

    public void constructManager() {
        messagemanager = new MessageManager();
    }

}
