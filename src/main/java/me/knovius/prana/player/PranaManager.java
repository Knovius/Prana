package me.knovius.prana.player;

import me.knovius.prana.Prana;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PranaManager {

    private final Prana plugin;

    private ConcurrentHashMap<UUID, PranaPlayer> playerMap;

    private File pranaPlayerFile;

    private YamlConfiguration pranaPlayerConfig;

    public PranaManager(Prana plugin) {
        this.plugin = plugin;
        playerMap = new ConcurrentHashMap<>();

        pranaPlayerFile = new File(plugin.getDataFolder(), "pranaplayer.yml");
        if (!pranaPlayerFile.exists()) {
            plugin.saveResource("pranaplayer.yml", false);
        }
        pranaPlayerConfig = YamlConfiguration.loadConfiguration(pranaPlayerFile);
    }

    public PranaPlayer getPranaPlayer(Player player) {

        return playerMap.get(player.getUniqueId());
    }

    public void addPranaPlayer(Player player, PranaPlayer pranaPlayer) {

        playerMap.put(player.getUniqueId(), pranaPlayer);
    }

    public void loadPlayer(Player player) {

        PranaPlayer pranaPlayer = new PranaPlayer(plugin, player);
        if (pranaPlayerConfig.contains("pranaplayer." + player.getUniqueId() )) {


            pranaPlayer.setMaxPrana(pranaPlayerConfig.getDouble("pranaplayer." + player.getUniqueId() + ".MAX-PRANA"));
            pranaPlayer.setPrana(pranaPlayerConfig.getDouble("pranaplayer." + player.getUniqueId() + ".PRANA"));

            addPranaPlayer(player, pranaPlayer);
            return;
        }
        pranaPlayer.setMaxPrana(plugin.getConfig().getDouble("default-prana"));
        pranaPlayer.setPrana(plugin.getConfig().getDouble("default-prana"));

        addPranaPlayer(player, pranaPlayer);
    }

    public void savePlayer(Player player) {
        PranaPlayer pranaPlayer = getPranaPlayer(player);

        pranaPlayerConfig.set("pranaplayer." + player.getUniqueId() + ".MAX-PRANA", pranaPlayer.getMaxPrana());
        pranaPlayerConfig.set("pranaplayer." + player.getUniqueId() + ".PRANA", pranaPlayer.getPrana());

    }

}
