package snake.model.strategy;

/**
 * Estrategia de movimiento de velocidad lenta - juego relajado.
 * 
 * PATRÓN: Strategy (Estrategia Concreta)
 * SlowSpeed proporciona un retardo de movimiento más largo para principiantes
 * o jugadores que prefieren una experiencia de juego más relajada.
 * 
 * @author Snake Game
 * @version 1.0
 */
public class SlowSpeed implements MovementStrategy {
    private static final int DELAY_MS = 250;

    @Override
    public int getMovementDelay() {
        return DELAY_MS;
    }

    @Override
    public String getName() {
        return "Lento";
    }
}
