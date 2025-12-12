package snake.model.observer;

/**
 * Interfaz observador para actualizaciones del estado del juego.
 * 
 * PATRÓN: Observer
 * GameObserver define la interfaz que todos los observadores deben implementar.
 * La capa Vista implementa esta interfaz para recibir notificaciones
 * cuando el estado del juego cambia, asegurando desacoplamiento completo del Modelo.
 * 
 * @author Snake Game
 * @version 1.0
 */
public interface GameObserver {
    
    /**
     * Se llama cuando el estado del juego ha sido actualizado.
     * Los observadores deben refrescar su visualización basándose en el nuevo estado.
     */
    void onGameUpdated();
    
    /**
     * Se llama cuando el juego termina.
     * 
     * @param finalScore la puntuación final del jugador
     */
    void onGameOver(int finalScore);
    
    /**
     * Se llama cuando la puntuación cambia.
     * 
     * @param newScore la puntuación actualizada
     */
    void onScoreChanged(int newScore);
}
