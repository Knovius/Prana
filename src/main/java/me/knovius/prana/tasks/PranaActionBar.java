package me.knovius.prana.tasks;

import me.knovius.prana.Prana;
import me.knovius.prana.player.PranaPlayer;
import me.knovius.prana.utils.CM;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PranaActionBar extends BukkitRunnable {

    @Override
    public void run() {

        if (Bukkit.getOnlinePlayers().isEmpty()) return;

        for (Player player: Bukkit.getOnlinePlayers()) {

            PranaPlayer pranaPlayer = Prana.getInstance().getPranaManager().getPranaPlayer(player);

            if (pranaPlayer == null) return;

            String currentPrana = String.valueOf(pranaPlayer.getPrana());
            String maxPrana = String.valueOf(pranaPlayer.getMaxPrana());

            String bar = CM.color(Prana.getInstance().getConfig().getString("prana-bar")
                    .replace("%current_prana%", currentPrana)
                    .replace("%max_prana%", maxPrana));
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(bar));
        }
    }
}
