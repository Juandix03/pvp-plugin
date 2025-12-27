package org.example.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.example.event.EventManager;

/**
 * Listener que controla el daño entre entidades dentro del evento.
 * Cancela cualquier daño si el evento no está en modo PvP.
 */
public class DamageListener implements Listener {

    // Referencia al EventManager para consultar el estado actual del evento
    private final EventManager eventManager;

    /**
     * Constructor que inyecta el EventManager.
     * @param eventManager Instancia del EventManager del plugin
     */
    public DamageListener(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    /**
     * Método llamado cada vez que una entidad recibe daño de otra.
     * Cancela el daño si el evento aún no está en PvP.
     *
     * @param event Evento de daño entre entidades
     */
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {

        // Cancelar daño si el PvP no está activo
        if (!eventManager.isPvp()) {
            event.setCancelled(true);
        }
    }
}
