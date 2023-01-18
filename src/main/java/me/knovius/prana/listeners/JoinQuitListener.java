package me.knovius.prana.listeners;

import me.knovius.prana.Prana;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Bukkit.getScheduler().runTaskLaterAsynchronously(Prana.getInstance(), new Runnable() {
            @Override
            public void run() {

                Prana.getInstance().getPranaManager().loadPlayer(event.getPlayer());

            }
        }, 5L);

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {

        Bukkit.getScheduler().runTaskLaterAsynchronously(Prana.getInstance(), new Runnable() {
            @Override
            public void run() {

                Prana.getInstance().getPranaManager().savePlayer(event.getPlayer());

            }
        }, 5L);

    }


}
