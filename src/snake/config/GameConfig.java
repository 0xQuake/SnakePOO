package snake.config;

/**
 * Clase de configuración Singleton para los ajustes del juego.
 * 
 * PATRÓN: Singleton
 * GameConfig asegura que solo existe una instancia de configuración del juego.
 * Esto proporciona un punto de acceso global para constantes y ajustes del juego,
 * asegurando consistencia a través de todos los componentes del juego.
 * 
 * @author Snake Game
 * @version 1.0
 */
public class GameConfig {
    private static GameConfig instance;
    
    private final int gridWidth;
    private final int gridHeight;
    private final int cellSize;
    private final int initialSnakeLength;
    private final double bonusFoodChance;

    /**
     * Constructor privado para el patrón Singleton.
     */
    private GameConfig() {
        this.gridWidth = 20;
        this.gridHeight = 20;
        this.cellSize = 25;
        this.initialSnakeLength = 3;
        this.bonusFoodChance = 0.15;
    }

    /**
     * Obtiene la instancia singleton de GameConfig.
     * Usa double-checked locking para seguridad de hilos.
     * 
     * @return la instancia singleton
     */
    public static synchronized GameConfig getInstance() {
        if (instance == null) {
            instance = new GameConfig();
        }
        return instance;
    }

    /**
     * Obtiene el ancho de la cuadrícula en celdas.
     * 
     * @return ancho de la cuadrícula
     */
    public int getGridWidth() {
        return gridWidth;
    }

    /**
     * Obtiene la altura de la cuadrícula en celdas.
     * 
     * @return altura de la cuadrícula
     */
    public int getGridHeight() {
        return gridHeight;
    }

    /**
     * Obtiene el tamaño de cada celda en píxeles.
     * 
     * @return tamaño de celda en píxeles
     */
    public int getCellSize() {
        return cellSize;
    }

    /**
     * Obtiene el ancho del panel del juego en píxeles.
     * 
     * @return ancho del panel
     */
    public int getPanelWidth() {
        return gridWidth * cellSize;
    }

    /**
     * Obtiene la altura del panel del juego en píxeles.
     * 
     * @return altura del panel
     */
    public int getPanelHeight() {
        return gridHeight * cellSize;
    }

    /**
     * Obtiene la longitud inicial de la serpiente.
     * 
     * @return longitud inicial de la serpiente
     */
    public int getInitialSnakeLength() {
        return initialSnakeLength;
    }

    /**
     * Obtiene la probabilidad de que aparezca comida bonus (0.0 a 1.0).
     * 
     * @return probabilidad de aparición de comida bonus
     */
    public double getBonusFoodChance() {
        return bonusFoodChance;
    }
}
