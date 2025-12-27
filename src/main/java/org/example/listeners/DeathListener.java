package org.example.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.example.GameState;
import org.example.event.EventManager;

/**
 * Listener que gestiona las muertes de los jugadores durante el evento.
 * - Si el evento está en PvP, transforma al jugador muerto en espectador.
 * - Verifica cuántos jugadores siguen vivos y declara un ganador si queda uno.
 */
public class DeathListener implements Listener {

    // Referencia al plugin principal, necesaria para programar tareas en el scheduler
    private final JavaPlugin plugin;

    // Referencia al EventManager para consultar el estado del evento y finalizarlo
    private final EventManager eventManager;

    /**
     * Constructor que recibe el plugin principal y el EventManager.
     *
     * @param plugin Instancia del plugin principal
     * @param eventManager Instancia del EventManager
     */
    public DeathListener(JavaPlugin plugin, EventManager eventManager) {
        this.plugin = plugin;
        this.eventManager = eventManager;
    }

    /**
     * Método llamado cuando un jugador muere.
     * - Si el evento no está en PvP, no hace nada.
     * - Cambia al jugador muerto a modo espectador.
     * - Verifica si queda un solo jugador vivo y lo declara ganador.
     *
     * @param event Evento de muerte del jugador
     */
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {

        // Ignorar si el PvP no está activo
        if (!eventManager.isPvp()) return;

        Player dead = event.getEntity();

        // Ejecutar cambios 2 ticks después para evitar conflictos con la muerte
        Bukkit.getScheduler().runTaskLater(plugin, () -> {

            // Marcar al jugador muerto como espectador
            dead.setGameMode(GameMode.SPECTATOR);

            // Contar jugadores vivos (no espectadores)
            long alive = Bukkit.getOnlinePlayers().stream()
                    .filter(p -> p.getGameMode() != GameMode.SPECTATOR)
                    .count();

            // Si queda un solo jugador vivo, declararlo ganador
            if (alive == 1) {
                Player winner = Bukkit.getOnlinePlayers().stream()
                        .filter(p -> p.getGameMode() != GameMode.SPECTATOR)
                        .findFirst()
                        .orElse(null);

                if (winner != null) {
                    Bukkit.broadcastMessage("§6🏆 Ganador: " + winner.getName());
                    eventManager.endEvent();
                }
            }

        }, 2L);
    }
}
