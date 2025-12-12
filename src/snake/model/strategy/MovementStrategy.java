package snake.model.strategy;

/**
 * Interfaz de estrategia para diferentes comportamientos de velocidad de movimiento.
 * 
 * PATRÓN: Strategy
 * MovementStrategy define una interfaz para algoritmos de velocidad de movimiento.
 * Diferentes implementaciones proporcionan velocidades de juego variables, permitiendo
 * que el juego cambie entre niveles de dificultad en tiempo de ejecución.
 * 
 * @author Snake Game
 * @version 1.0
 */
public interface MovementStrategy {
    
    /**
     * Obtiene el retardo entre ticks del juego en milisegundos.
     * Valores más bajos significan movimiento más rápido.
     * 
     * @return el retardo en milisegundos
     */
    int getMovementDelay();
    
    /**
     * Obtiene el nombre de esta estrategia para propósitos de visualización.
     * 
     * @return el nombre de la estrategia
     */
    String getName();
}
