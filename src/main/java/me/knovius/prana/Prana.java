package me.knovius.prana;

import lombok.Getter;
import me.knovius.prana.listeners.JoinQuitListener;
import me.knovius.prana.player.PranaManager;
import me.knovius.prana.tasks.PranaActionBar;
import me.knovius.prana.tasks.PranaRegenTask;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;

public class Prana extends JavaPlugin {

    @Getter
    public static Prana instance;

    @Getter
    private PranaManager pranaManager;

    private PranaActionBar pranaActionBar;
    private PranaRegenTask pranaRegenTask;

    @Override
    public void onEnable() {
        instance = this;
        pranaManager = new PranaManager(this);
        pranaActionBar = new PranaActionBar();
        pranaRegenTask = new PranaRegenTask();
        // Plugin startup logic

        Bukkit.getPluginManager().registerEvents(new JoinQuitListener(), this);

        pranaActionBar.runTaskTimerAsynchronously(this, 5L, 30L);
        pranaRegenTask.runTaskTimerAsynchronously(this, 0L, 20L);

        saveConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
