package snake.model;

/**
 * Enumeración que representa los posibles estados del juego.
 * Controla el flujo del juego y determina qué acciones son válidas.
 * 
 * @author Snake Game
 * @version 1.0
 */
public enum GameState {
    /**
     * El juego está activamente en ejecución y acepta entradas.
     */
    RUNNING,
    
    /**
     * El juego está temporalmente pausado. Puede reanudarse.
     */
    PAUSED,
    
    /**
     * El juego ha terminado debido a una colisión. Requiere reinicio.
     */
    GAME_OVER
}
