package snake.model.strategy;

/**
 * Estrategia de movimiento de velocidad normal - juego balanceado.
 * 
 * PATRÓN: Strategy (Estrategia Concreta)
 * NormalSpeed proporciona un retardo de movimiento moderado adecuado
 * para juego estándar. Esta es la estrategia predeterminada.
 * 
 * @author Snake Game
 * @version 1.0
 */
public class NormalSpeed implements MovementStrategy {
    private static final int DELAY_MS = 150;

    @Override
    public int getMovementDelay() {
        return DELAY_MS;
    }

    @Override
    public String getName() {
        return "Normal";
    }
}
