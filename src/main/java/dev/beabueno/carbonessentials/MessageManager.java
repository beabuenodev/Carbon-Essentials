package dev.beabueno.carbonessentials;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class MessageManager implements Listener {

    private HashMap<UUID, UUID> recentMessages;

    public MessageManager() {
        recentMessages = new HashMap<>();
    }

    public HashMap<UUID, UUID> getRecentMessages() {
        return recentMessages;
    }

    public void setRecentMessages(HashMap<UUID, UUID> recentMessages) {
        this.recentMessages = recentMessages;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        recentMessages.remove(e.getPlayer().getUniqueId());
    }

}
