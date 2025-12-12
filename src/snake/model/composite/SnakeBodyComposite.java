package snake.model.composite;

import snake.model.Direction;
import snake.model.Position;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase compuesta que gestiona toda la estructura del cuerpo de la serpiente.
 * 
 * PATRÓN: Composite (Nodo Compuesto)
 * SnakeBodyComposite es el nodo compuesto que contiene y gestiona
 * todos los componentes de la serpiente (cabeza y segmentos). Proporciona una interfaz
 * unificada para operaciones sobre todo el cuerpo de la serpiente.
 * 
 * @author Snake Game
 * @version 1.0
 */
public class SnakeBodyComposite implements SnakeComponent {
    private final SnakeHead head;
    private final List<SnakeSegment> segments;

    /**
     * Crea un nuevo cuerpo de serpiente con la cabeza en la posición especificada.
     * 
     * @param headPosition la posición inicial de la cabeza de la serpiente
     */
    public SnakeBodyComposite(Position headPosition) {
        this.head = new SnakeHead(headPosition);
        this.segments = new ArrayList<>();
    }

    /**
     * Obtiene el componente cabeza de la serpiente.
     * 
     * @return el SnakeHead
     */
    public SnakeHead getHead() {
        return head;
    }

    /**
     * Obtiene la lista de segmentos del cuerpo.
     * 
     * @return lista de objetos SnakeSegment
     */
    public List<SnakeSegment> getSegments() {
        return new ArrayList<>(segments);
    }

    /**
     * Obtiene la longitud actual de la serpiente (cabeza + segmentos).
     * 
     * @return número total de componentes
     */
    public int getLength() {
        return 1 + segments.size();
    }

    /**
     * Mueve la serpiente en la dirección especificada.
     * Cada segmento se mueve a la posición del segmento delante de él.
     * 
     * @param direction la dirección de movimiento
     */
    public void move(Direction direction) {
        Position previousPosition = head.getPosition();
        head.setPosition(previousPosition.move(direction));
        
        for (SnakeSegment segment : segments) {
            Position temp = segment.getPosition();
            segment.setPosition(previousPosition);
            previousPosition = temp;
        }
    }

    /**
     * Hace crecer la serpiente agregando un nuevo segmento en la cola.
     * El nuevo segmento se agrega en la posición del último segmento
     * (o de la cabeza si no existen segmentos).
     */
    public void grow() {
        Position tailPosition;
        if (segments.isEmpty()) {
            tailPosition = head.getPosition();
        } else {
            tailPosition = segments.get(segments.size() - 1).getPosition();
        }
        segments.add(new SnakeSegment(tailPosition));
    }

    /**
     * Verifica si la cabeza de la serpiente colisiona con su propio cuerpo.
     * 
     * @return true si se detecta auto-colisión
     */
    public boolean hasSelfCollision() {
        Position headPos = head.getPosition();
        for (SnakeSegment segment : segments) {
            if (segment.getPosition().equals(headPos)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Position getPosition() {
        return head.getPosition();
    }

    @Override
    public void setPosition(Position position) {
        head.setPosition(position);
    }

    @Override
    public List<Position> getAllPositions() {
        List<Position> positions = new ArrayList<>();
        positions.add(head.getPosition());
        for (SnakeSegment segment : segments) {
            positions.add(segment.getPosition());
        }
        return positions;
    }

    @Override
    public boolean occupies(Position position) {
        if (head.occupies(position)) {
            return true;
        }
        for (SnakeSegment segment : segments) {
            if (segment.occupies(position)) {
                return true;
            }
        }
        return false;
    }
}
