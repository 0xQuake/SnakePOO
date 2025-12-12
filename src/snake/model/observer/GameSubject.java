package snake.model.observer;

/**
 * Interfaz sujeto para el patrón Observer.
 * 
 * PATRÓN: Observer (Sujeto)
 * GameSubject define métodos para gestionar observadores.
 * El GameModel implementa esta interfaz para permitir que las Vistas
 * se suscriban a los cambios del estado del juego.
 * 
 * @author Snake Game
 * @version 1.0
 */
public interface GameSubject {
    
    /**
     * Registra un observador para recibir actualizaciones del juego.
     * 
     * @param observer el observador a registrar
     */
    void registerObserver(GameObserver observer);
    
    /**
     * Elimina un observador de la lista de notificaciones.
     * 
     * @param observer el observador a eliminar
     */
    void removeObserver(GameObserver observer);
    
    /**
     * Notifica a todos los observadores registrados de una actualización del juego.
     */
    void notifyObservers();
}
