package me.knovius.prana.tasks;

import me.knovius.prana.Prana;
import me.knovius.prana.player.PranaPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PranaRegenTask extends BukkitRunnable {
    @Override
    public void run() {

        if (Bukkit.getOnlinePlayers().isEmpty()) return;

        for (Player player: Bukkit.getOnlinePlayers()) {
            PranaPlayer pranaPlayer = Prana.getInstance().getPranaManager().getPranaPlayer(player);

            if (player.isSneaking() && pranaPlayer.canRegen()) {

                pranaPlayer.addCurrentPrana(Prana.getInstance().getConfig().getDouble("prana-regenerated"));

            }
        }
    }
}
