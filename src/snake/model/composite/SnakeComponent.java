package snake.model.composite;

import snake.model.Position;
import java.util.List;

/**
 * Interfaz para el patrón Composite que representa partes del cuerpo de la serpiente.
 * 
 * PATRÓN: Composite
 * Esta interfaz define un contrato común tanto para partes individuales de la serpiente
 * (SnakeHead, SnakeSegment) como para estructuras compuestas (SnakeBodyComposite).
 * Permite tratar objetos individuales y composiciones de manera uniforme.
 * 
 * @author Snake Game
 * @version 1.0
 */
public interface SnakeComponent {
    
    /**
     * Obtiene la posición actual de este componente.
     * 
     * @return la posición en la cuadrícula del juego
     */
    Position getPosition();
    
    /**
     * Establece una nueva posición para este componente.
     * 
     * @param position la nueva posición
     */
    void setPosition(Position position);
    
    /**
     * Obtiene todas las posiciones ocupadas por este componente y sus hijos.
     * Para nodos hoja, retorna una lista de un solo elemento.
     * Para nodos compuestos, retorna todas las posiciones de los hijos.
     * 
     * @return lista de todas las posiciones
     */
    List<Position> getAllPositions();
    
    /**
     * Verifica si este componente o algún hijo ocupa la posición dada.
     * 
     * @param position la posición a verificar
     * @return true si la posición está ocupada
     */
    boolean occupies(Position position);
}
