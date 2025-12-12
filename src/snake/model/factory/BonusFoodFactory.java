package snake.model.factory;

import snake.model.Position;

/**
 * Fábrica para crear elementos de comida bonus con puntos extra.
 * 
 * PATRÓN: Factory Method (Creador Concreto)
 * BonusFoodFactory crea comida bonus especial que vale 50 puntos.
 * Estas aparecen con menor frecuencia y proporcionan oportunidades de puntuación extra.
 * 
 * @author Snake Game
 * @version 1.0
 */
public class BonusFoodFactory implements FoodFactory {
    private static final int BONUS_FOOD_POINTS = 50;

    @Override
    public Food createFood(Position position) {
        return new Food(position, BONUS_FOOD_POINTS, Food.FoodType.BONUS);
    }
}
