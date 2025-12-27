package org.example;

import org.bukkit.plugin.java.JavaPlugin;
import org.example.commands.EventCommand;
import org.example.event.EventManager;
import org.example.listeners.DamageListener;
import org.example.listeners.DeathListener;
import org.example.listeners.MoveListener;

/**
 * Clase principal del plugin.
 * - Inicializa el EventManager
 * - Registra comandos y listeners
 * - Maneja el ciclo de vida del plugin
 */
public final class Main extends JavaPlugin {

    // Administrador del estado del evento
    private EventManager eventManager;

    @Override
    public void onEnable() {

        // Crear instancia de EventManager
        this.eventManager = new EventManager();

        // Registrar comando /event
        getCommand("event").setExecutor(new EventCommand(eventManager));

        // Registrar listeners
        getServer().getPluginManager().registerEvents(
                new DamageListener(eventManager), this);
        getServer().getPluginManager().registerEvents(
                new MoveListener(eventManager), this);
        getServer().getPluginManager().registerEvents(
                new DeathListener(this, eventManager), this);

        getLogger().info("Last Stand Arena cargado."); // Mensaje al iniciar el plugin
    }

    /**
     * Obtener el EventManager
     * @return instancia del EventManager
     */
    public EventManager getEventManager() {
        return eventManager;
    }
}
