package snake.model;

import java.util.Objects;

/**
 * Clase inmutable que representa una posición en la cuadrícula del juego.
 * Usa coordenadas de cuadrícula (no coordenadas de píxeles) para la lógica del juego.
 * 
 * @author Snake Game
 * @version 1.0
 */
public class Position {
    private final int x;
    private final int y;

    /**
     * Crea una nueva Posición con las coordenadas especificadas.
     * 
     * @param x la coordenada X en la cuadrícula
     * @param y la coordenada Y en la cuadrícula
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Obtiene la coordenada X.
     * 
     * @return la coordenada X
     */
    public int getX() {
        return x;
    }

    /**
     * Obtiene la coordenada Y.
     * 
     * @return la coordenada Y
     */
    public int getY() {
        return y;
    }

    /**
     * Crea una nueva Posición movida en la dirección especificada.
     * 
     * @param direction la dirección de movimiento
     * @return una nueva Posición desplazada por los valores delta de la dirección
     */
    public Position move(Direction direction) {
        return new Position(x + direction.getDeltaX(), y + direction.getDeltaY());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Position{x=" + x + ", y=" + y + "}";
    }
}
