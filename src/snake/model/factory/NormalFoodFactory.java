package snake.model.factory;

import snake.model.Position;

/**
 * Fábrica para crear elementos de comida normal con puntos estándar.
 * 
 * PATRÓN: Factory Method (Creador Concreto)
 * NormalFoodFactory crea elementos de comida estándar que valen 10 puntos.
 * Este es el tipo de comida predeterminado que aparece durante el juego regular.
 * 
 * @author Snake Game
 * @version 1.0
 */
public class NormalFoodFactory implements FoodFactory {
    private static final int NORMAL_FOOD_POINTS = 10;

    @Override
    public Food createFood(Position position) {
        return new Food(position, NORMAL_FOOD_POINTS, Food.FoodType.NORMAL);
    }
}
