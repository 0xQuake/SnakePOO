package snake.model;

/**
 * Enumeración que representa las cuatro direcciones posibles de movimiento de la serpiente.
 * Esta es una clase del modelo pura sin dependencias de UI.
 * 
 * @author Snake Game
 * @version 1.0
 */
public enum Direction {
    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    private final int deltaX;
    private final int deltaY;

    /**
     * Constructor para el enum Direction.
     * 
     * @param deltaX el cambio en la coordenada X para esta dirección
     * @param deltaY el cambio en la coordenada Y para esta dirección
     */
    Direction(int deltaX, int deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    /**
     * Obtiene el cambio en la coordenada X para esta dirección.
     * 
     * @return el valor delta X
     */
    public int getDeltaX() {
        return deltaX;
    }

    /**
     * Obtiene el cambio en la coordenada Y para esta dirección.
     * 
     * @return el valor delta Y
     */
    public int getDeltaY() {
        return deltaY;
    }

    /**
     * Verifica si esta dirección es opuesta a otra dirección.
     * Se usa para evitar que la serpiente se revierta directamente.
     * 
     * @param other la dirección con la que comparar
     * @return true si las direcciones son opuestas, false en caso contrario
     */
    public boolean isOpposite(Direction other) {
        return this.deltaX + other.deltaX == 0 && this.deltaY + other.deltaY == 0;
    }
}
