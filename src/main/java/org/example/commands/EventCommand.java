package org.example.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.example.event.EventManager;

/**
 * Clase que maneja el comando /event.
 * Permite iniciar, activar PvP y finalizar un evento.
 */
public class EventCommand implements CommandExecutor {

    // Referencia al EventManager que controla el estado del evento
    private final EventManager eventManager;

    /**
     * Constructor que inyecta el EventManager.
     * @param eventManager Instancia del EventManager del plugin
     */
    public EventCommand(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    /**
     * Método llamado cuando un jugador ejecuta el comando /event
     *
     * @param sender La fuente que ejecuta el comando (jugador o consola)
     * @param command Comando ejecutado
     * @param label Alias usado del comando
     * @param args Argumentos del comando
     * @return true si el comando fue procesado correctamente
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // Si no se pasan argumentos, mostramos la ayuda básica
        if (args.length == 0) {
            sender.sendMessage("/event start | /event pvp | /event end");
            return true;
        }

        // Evaluamos el primer argumento para determinar la acción
        switch (args[0].toLowerCase()) {
            case "start" -> eventManager.startEvent();   // Inicia el evento
            case "pvp" -> eventManager.enablePvp();     // Activa PvP
            case "end" -> eventManager.endEvent();       // Finaliza el evento
            default -> sender.sendMessage("Comando inválido."); // Comando desconocido
        }

        return true;
    }
}
