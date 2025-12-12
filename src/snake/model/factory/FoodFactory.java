package snake.model.factory;

import snake.model.Position;

/**
 * Interfaz de fábrica abstracta para crear objetos Food.
 * 
 * PATRÓN: Factory Method
 * FoodFactory define la interfaz para crear elementos de comida.
 * Las implementaciones concretas (NormalFoodFactory, BonusFoodFactory)
 * deciden el tipo específico y propiedades de la comida a crear.
 * Esto desacopla la creación de comida de la lógica del juego.
 * 
 * @author Snake Game
 * @version 1.0
 */
public interface FoodFactory {
    
    /**
     * Crea un elemento de comida en la posición especificada.
     * 
     * @param position la posición donde debe aparecer la comida
     * @return una nueva instancia de Food
     */
    Food createFood(Position position);
}
