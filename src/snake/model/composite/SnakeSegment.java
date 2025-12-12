package snake.model.composite;

import snake.model.Position;
import java.util.Collections;
import java.util.List;

/**
 * Representa un segmento del cuerpo de la serpiente - un nodo hoja en el patrón Composite.
 * 
 * PATRÓN: Composite (Hoja)
 * SnakeSegment es un componente hoja que representa un segmento del cuerpo.
 * Cada segmento sigue al que está delante durante el movimiento.
 * 
 * @author Snake Game
 * @version 1.0
 */
public class SnakeSegment implements SnakeComponent {
    private Position position;

    /**
     * Crea un nuevo SnakeSegment en la posición especificada.
     * 
     * @param position la posición inicial de este segmento
     */
    public SnakeSegment(Position position) {
        this.position = position;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public List<Position> getAllPositions() {
        return Collections.singletonList(position);
    }

    @Override
    public boolean occupies(Position position) {
        return this.position.equals(position);
    }
}
