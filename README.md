# Evento PvP

## Descripción
**Evento PvP** es un plugin para Minecraft Paper/Spigot que implementa un sistema de eventos PvP dentro del servidor.  
Permite iniciar eventos, habilitar PvP y gestionar la muerte y movimiento de los jugadores durante el evento.

---

## Características
- Comando `/event` para controlar el evento:
  - `/event start` → Inicia el evento.
  - `/event pvp` → Habilita PvP durante el evento.
  - `/event end` → Finaliza el evento.
- Sistema de estado del evento (`WAITING`, `STARTING`, `PVP`, `FINISHED`).
- Control de PvP, evitando daño fuera de los eventos.
- Gestión de la muerte de jugadores:
  - Al morir, el jugador pasa a **modo espectador**.
  - Se detecta automáticamente al último jugador vivo y se anuncia al ganador.
- Bloqueo de movimiento mientras el evento está iniciando.

---

## Instalación
1. Colocar el `.jar` del plugin en la carpeta `plugins` de tu servidor Paper/Spigot.
2. Reiniciar o recargar el servidor.
3. Usar el comando `/event` para controlar el evento.

---

## Requisitos
- Minecraft Paper o Spigot **1.21.10** o superior.
- Java **17** o superior.

---

## Estructura del proyecto
- `Main.java` → Clase principal del plugin.
- `EventManager.java` → Gestión del estado del evento.
- `EventCommand.java` → Comandos relacionados al evento.
- `DamageListener.java` → Control de daño entre jugadores.
- `MoveListener.java` → Bloqueo de movimiento al iniciar el evento.
- `DeathListener.java` → Gestión de la muerte y detección del ganador.
- `plugin.yml` → Configuración básica del plugin.
