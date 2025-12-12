package snake.model.composite;

import snake.model.Position;
import java.util.Collections;
import java.util.List;

/**
 * Representa la cabeza de la serpiente - un nodo hoja en el patrón Composite.
 * 
 * PATRÓN: Composite (Hoja)
 * SnakeHead es un componente hoja que no puede contener hijos.
 * Representa el segmento líder de la serpiente que determina la dirección del movimiento.
 * 
 * @author Snake Game
 * @version 1.0
 */
public class SnakeHead implements SnakeComponent {
    private Position position;

    /**
     * Crea una nueva SnakeHead en la posición especificada.
     * 
     * @param position la posición inicial de la cabeza
     */
    public SnakeHead(Position position) {
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
