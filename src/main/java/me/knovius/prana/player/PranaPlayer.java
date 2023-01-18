package me.knovius.prana.player;

import lombok.Getter;
import lombok.Setter;
import me.knovius.prana.Prana;
import org.bukkit.entity.Player;

public class PranaPlayer {

    private final Prana plugin;

    @Getter
    @Setter
    private double prana;
    @Getter
    @Setter
    private double maxPrana;

    private Player player;

    public PranaPlayer(Prana plugin, Player player) {
        this.plugin = plugin;
        this.player = player;
    }

    public void addMaxPrana(double amount) {
        double currentMaxPrana = maxPrana;

        if (currentMaxPrana + amount > plugin.getConfig().getDouble("max-prana")) return;

        maxPrana = currentMaxPrana+amount;
    }

    public void removeMaxPrana(double amount) {

        double currentMaxPrana = maxPrana;

        if (currentMaxPrana - amount < 0) {
            maxPrana = 0;
            return;
        }
        maxPrana = currentMaxPrana-amount;
    }

    public void addCurrentPrana(double amount) {
        double currentPrana = prana;

        if (currentPrana + amount > maxPrana) {
            prana = maxPrana;
            return;
        }

        prana = currentPrana+amount;
    }

    public void removeCurrentPrana(double amount) {
        double currentPrana = prana;

        if (currentPrana - amount < 0) {
            prana = 0;
        }

        prana = currentPrana-amount;
    }

    public boolean canRegen() {

        if (prana < maxPrana) {
            return true;
        }

        return false;
    }
}
