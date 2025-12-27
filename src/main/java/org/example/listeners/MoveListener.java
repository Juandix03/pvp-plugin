package org.example.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.example.GameState;
import org.example.event.EventManager;

/**
 * Listener que controla el movimiento de los jugadores durante el inicio del evento.
 * - Mientras el evento esté en estado STARTING, bloquea cualquier movimiento.
 */
public class MoveListener implements Listener {

    // Referencia al EventManager para consultar el estado del evento
    private final EventManager eventManager;

    /**
     * Constructor que inyecta el EventManager.
     *
     * @param eventManager Instancia del EventManager del plugin
     */
    public MoveListener(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    /**
     * Método llamado cada vez que un jugador se mueve.
     * Cancela el movimiento si el evento está en estado STARTING.
     *
     * @param event Evento de movimiento del jugador
     */
    @EventHandler
    public void onMove(PlayerMoveEvent event) {

        // Cancelar movimiento durante la fase de inicio del evento
        if (eventManager.getGameState() == GameState.STARTING) {
            event.setCancelled(true);
        }
    }
}
