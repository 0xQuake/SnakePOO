package snake.model;

import snake.config.GameConfig;
import snake.model.composite.SnakeBodyComposite;
import snake.model.factory.*;
import snake.model.observer.GameObserver;
import snake.model.observer.GameSubject;
import snake.model.strategy.MovementStrategy;
import snake.model.strategy.NormalSpeed;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Modelo principal del juego que contiene toda la lógica y estado del juego.
 * Esta clase es completamente independiente de frameworks de UI.
 * 
 * PATRÓN: Observer (Sujeto), Strategy (Contexto)
 * GameModel actúa como el Sujeto en el patrón Observer, notificando
 * a las Vistas de cambios de estado. También actúa como el Contexto para el
 * patrón Strategy, usando implementaciones intercambiables de MovementStrategy.
 * 
 * @author Snake Game
 * @version 1.0
 */
public class GameModel implements GameSubject {
    private final GameConfig config;
    private final List<GameObserver> observers;
    private final Random random;
    
    private SnakeBodyComposite snake;
    private Food currentFood;
    private Direction currentDirection;
    private Direction nextDirection;
    private GameState gameState;
    private MovementStrategy movementStrategy;
    private int score;
    
    private final FoodFactory normalFoodFactory;
    private final FoodFactory bonusFoodFactory;

    /**
     * Crea un nuevo GameModel con configuración predeterminada.
     */
    public GameModel() {
        this.config = GameConfig.getInstance();
        this.observers = new ArrayList<>();
        this.random = new Random();
        this.normalFoodFactory = new NormalFoodFactory();
        this.bonusFoodFactory = new BonusFoodFactory();
        this.movementStrategy = new NormalSpeed();
        
        initializeGame();
    }

    /**
     * Inicializa o reinicia el juego a su estado inicial.
     */
    public void initializeGame() {
        int startX = config.getGridWidth() / 2;
        int startY = config.getGridHeight() / 2;
        
        this.snake = new SnakeBodyComposite(new Position(startX, startY));
        
        for (int i = 1; i < config.getInitialSnakeLength(); i++) {
            snake.grow();
        }
        
        this.currentDirection = Direction.RIGHT;
        this.nextDirection = Direction.RIGHT;
        this.score = 0;
        this.gameState = GameState.RUNNING;
        
        spawnFood();
        notifyObservers();
    }

    /**
     * Actualiza el estado del juego para un tick.
     * Llamado por el bucle del juego en el Controlador.
     */
    public void update() {
        if (gameState != GameState.RUNNING) {
            return;
        }

        currentDirection = nextDirection;
        snake.move(currentDirection);

        if (checkCollisions()) {
            gameState = GameState.GAME_OVER;
            notifyGameOver();
            return;
        }

        if (snake.getHead().getPosition().equals(currentFood.getPosition())) {
            score += currentFood.getPoints();
            snake.grow();
            spawnFood();
            notifyScoreChanged();
        }

        notifyObservers();
    }

    /**
     * Genera un nuevo elemento de comida en una posición aleatoria no ocupada.
     */
    private void spawnFood() {
        Position foodPosition;
        do {
            int x = random.nextInt(config.getGridWidth());
            int y = random.nextInt(config.getGridHeight());
            foodPosition = new Position(x, y);
        } while (snake.occupies(foodPosition));

        FoodFactory factory = random.nextDouble() < config.getBonusFoodChance()
                ? bonusFoodFactory : normalFoodFactory;
        
        currentFood = factory.createFood(foodPosition);
    }

    /**
     * Verifica colisiones con paredes y con uno mismo.
     * 
     * @return true si ocurrió una colisión
     */
    private boolean checkCollisions() {
        Position headPos = snake.getHead().getPosition();

        if (headPos.getX() < 0 || headPos.getX() >= config.getGridWidth() ||
            headPos.getY() < 0 || headPos.getY() >= config.getGridHeight()) {
            return true;
        }

        return snake.hasSelfCollision();
    }

    /**
     * Establece la siguiente dirección para la serpiente.
     * Evita revertirse directamente hacia sí misma.
     * 
     * @param direction la nueva dirección
     */
    public void setDirection(Direction direction) {
        if (!direction.isOpposite(currentDirection)) {
            this.nextDirection = direction;
        }
    }

    /**
     * Alterna entre estados RUNNING y PAUSED.
     */
    public void togglePause() {
        if (gameState == GameState.RUNNING) {
            gameState = GameState.PAUSED;
        } else if (gameState == GameState.PAUSED) {
            gameState = GameState.RUNNING;
        }
        notifyObservers();
    }

    /**
     * Establece la estrategia de movimiento (velocidad).
     * 
     * @param strategy la nueva estrategia de movimiento
     */
    public void setMovementStrategy(MovementStrategy strategy) {
        this.movementStrategy = strategy;
    }

    public SnakeBodyComposite getSnake() {
        return snake;
    }

    public Food getCurrentFood() {
        return currentFood;
    }

    public GameState getGameState() {
        return gameState;
    }

    public int getScore() {
        return score;
    }

    public MovementStrategy getMovementStrategy() {
        return movementStrategy;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    @Override
    public void registerObserver(GameObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(GameObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (GameObserver observer : observers) {
            observer.onGameUpdated();
        }
    }

    private void notifyGameOver() {
        for (GameObserver observer : observers) {
            observer.onGameOver(score);
        }
    }

    private void notifyScoreChanged() {
        for (GameObserver observer : observers) {
            observer.onScoreChanged(score);
        }
    }
}
