package org.example.event;

import org.bukkit.Bukkit;
import org.example.GameState;

/**
 * Clase que maneja el estado del evento dentro del servidor.
 * Controla la transición entre estados y envía mensajes a todos los jugadores.
 */
public class EventManager {

    // Estado actual del evento (WAITING, STARTING, PVP, FINISHED)
    private GameState gameState = GameState.WAITING;

    /**
     * Obtiene el estado actual del evento.
     * @return Estado actual (GameState)
     */
    public GameState getGameState() {
        return gameState;
    }

    /**
     * Indica si actualmente el evento está en modo PvP.
     * @return true si el estado es PVP, false en caso contrario
     */
    public boolean isPvp() {
        return gameState == GameState.PVP;
    }

    /**
     * Inicia el evento, cambiando el estado a STARTING
     * y enviando un mensaje de aviso a todos los jugadores.
     */
    public void startEvent() {
        gameState = GameState.STARTING;
        Bukkit.broadcastMessage("§eEl evento ha comenzado. ¡Prepárense!");
    }

    /**
     * Activa el modo PvP dentro del evento.
     * Cambia el estado a PVP y notifica a todos los jugadores.
     */
    public void enablePvp() {
        gameState = GameState.PVP;
        Bukkit.broadcastMessage("§c¡PvP ACTIVADO!");
    }

    /**
     * Finaliza el evento, cambiando el estado a FINISHED
     * y notificando a todos los jugadores que terminó.
     */
    public void endEvent() {
        gameState = GameState.FINISHED;
        Bukkit.broadcastMessage("§aEl evento ha finalizado.");
    }
}
