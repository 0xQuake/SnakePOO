package snake.controller;

import snake.model.Direction;
import snake.model.GameModel;
import snake.model.GameState;
import snake.model.strategy.FastSpeed;
import snake.model.strategy.NormalSpeed;
import snake.model.strategy.SlowSpeed;
import snake.view.GameFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Controlador que gestiona la entrada del usuario y el bucle del juego.
 * Orquesta la comunicación entre el Modelo y la Vista.
 * 
 * MVC: Controlador
 * GameController maneja la entrada del teclado vía KeyListener,
 * gestiona el bucle del juego usando un Timer de Swing, y coordina
 * las actualizaciones entre las capas Modelo y Vista.
 * 
 * @author Snake Game
 * @version 1.0
 */
public class GameController {
    private final GameModel model;
    private final GameFrame view;
    private Timer gameTimer;

    /**
     * Crea un nuevo GameController.
     * 
     * @param model el modelo del juego
     * @param view el frame del juego
     */
    public GameController(GameModel model, GameFrame view) {
        this.model = model;
        this.view = view;
        
        initializeKeyListener();
        initializeGameLoop();
    }

    /**
     * Inicializa el manejo de entrada del teclado.
     */
    private void initializeKeyListener() {
        view.getGamePanel().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e);
            }
        });
    }

    /**
     * Maneja la entrada del teclado para los controles del juego.
     */
    private void handleKeyPress(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                model.setDirection(Direction.UP);
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                model.setDirection(Direction.DOWN);
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                model.setDirection(Direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                model.setDirection(Direction.RIGHT);
                break;
            case KeyEvent.VK_SPACE:
                model.togglePause();
                break;
            case KeyEvent.VK_ENTER:
                if (model.getGameState() == GameState.GAME_OVER) {
                    model.initializeGame();
                    restartGameLoop();
                }
                break;
            case KeyEvent.VK_1:
                changeSpeed(new SlowSpeed());
                break;
            case KeyEvent.VK_2:
                changeSpeed(new NormalSpeed());
                break;
            case KeyEvent.VK_3:
                changeSpeed(new FastSpeed());
                break;
        }
    }

    /**
     * Cambia la velocidad del juego actualizando la estrategia.
     */
    private void changeSpeed(snake.model.strategy.MovementStrategy strategy) {
        model.setMovementStrategy(strategy);
        restartGameLoop();
    }

    /**
     * Inicializa el timer del bucle del juego.
     */
    private void initializeGameLoop() {
        gameTimer = new Timer(model.getMovementStrategy().getMovementDelay(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (model.getGameState() == GameState.RUNNING) {
                    model.update();
                }
            }
        });
        gameTimer.start();
    }

    /**
     * Reinicia el bucle del juego con la configuración de velocidad actual.
     */
    private void restartGameLoop() {
        if (gameTimer != null) {
            gameTimer.stop();
        }
        gameTimer = new Timer(model.getMovementStrategy().getMovementDelay(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (model.getGameState() == GameState.RUNNING) {
                    model.update();
                }
            }
        });
        gameTimer.start();
    }

    /**
     * Inicia el juego haciendo visible la vista.
     */
    public void startGame() {
        SwingUtilities.invokeLater(() -> {
            view.setVisible(true);
            view.getGamePanel().requestFocusInWindow();
        });
    }
}
