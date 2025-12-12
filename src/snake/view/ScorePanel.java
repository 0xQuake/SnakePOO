package snake.view;

import snake.model.GameModel;
import snake.model.observer.GameObserver;

import javax.swing.*;
import java.awt.*;

/**
 * Panel que muestra la puntuación actual y la velocidad del juego.
 * 
 * PATRÓN: Observer (Observador Concreto)
 * ScorePanel observa el GameModel y actualiza la visualización de puntuación
 * cada vez que el estado del juego cambia.
 * 
 * @version 1.0
 */
public class ScorePanel extends JPanel implements GameObserver {
    private final GameModel model;
    private final JLabel scoreLabel;
    private final JLabel speedLabel;

    /**
     * Crea un nuevo ScorePanel.
     * 
     * @param model el modelo del juego a observar
     */
    public ScorePanel(GameModel model) {
        this.model = model;
        
        setBackground(new Color(40, 40, 40));
        setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
        
        Font labelFont = new Font("Arial", Font.BOLD, 18);
        
        scoreLabel = new JLabel("Puntuación: 0");
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(labelFont);
        
        speedLabel = new JLabel("Velocidad: " + model.getMovementStrategy().getName());
        speedLabel.setForeground(new Color(129, 199, 132));
        speedLabel.setFont(labelFont);
        
        add(scoreLabel);
        add(speedLabel);
        
        model.registerObserver(this);
    }

    @Override
    public void onGameUpdated() {
        updateLabels();
    }

    @Override
    public void onGameOver(int finalScore) {
        scoreLabel.setText("Puntuación Final: " + finalScore);
    }

    @Override
    public void onScoreChanged(int newScore) {
        scoreLabel.setText("Puntuación: " + newScore);
    }
    
    private void updateLabels() {
        scoreLabel.setText("Puntuación: " + model.getScore());
        speedLabel.setText("Velocidad: " + model.getMovementStrategy().getName());
    }
}
