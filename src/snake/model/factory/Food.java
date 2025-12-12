package snake.model.factory;

import snake.model.Position;

/**
 * Representa un elemento de comida en el juego con posición y valor en puntos.
 * Creado por implementaciones de FoodFactory.
 * 
 * PATRÓN: Factory Method (Producto)
 * Food es el producto creado por FoodFactory. Diferentes implementaciones
 * de fábricas crean comida con diferentes valores de puntos.
 * 
 * @author Snake Game
 * @version 1.0
 */
public class Food {
    private final Position position;
    private final int points;
    private final FoodType type;

    /**
     * Enumeración para diferentes tipos de comida.
     */
    public enum FoodType {
        NORMAL,
        BONUS
    }

    /**
     * Crea un nuevo elemento de comida.
     * 
     * @param position la posición en la cuadrícula
     * @param points el valor en puntos al ser comido
     * @param type el tipo de comida
     */
    public Food(Position position, int points, FoodType type) {
        this.position = position;
        this.points = points;
        this.type = type;
    }

    /**
     * Obtiene la posición de esta comida.
     * 
     * @return la posición
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Obtiene el valor en puntos de esta comida.
     * 
     * @return los puntos otorgados al ser comida
     */
    public int getPoints() {
        return points;
    }

    /**
     * Obtiene el tipo de esta comida.
     * 
     * @return el tipo de comida
     */
    public FoodType getType() {
        return type;
    }
}
