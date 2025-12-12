package snake.model.strategy;

/**
 * Estrategia de movimiento de velocidad rápida - juego desafiante.
 * 
 * PATRÓN: Strategy (Estrategia Concreta)
 * FastSpeed proporciona un retardo de movimiento corto para jugadores
 * que buscan una experiencia más desafiante y de ritmo rápido.
 * 
 * @author Snake Game
 * @version 1.0
 */
public class FastSpeed implements MovementStrategy {
    private static final int DELAY_MS = 80;

    @Override
    public int getMovementDelay() {
        return DELAY_MS;
    }

    @Override
    public String getName() {
        return "Rápido";
    }
}
