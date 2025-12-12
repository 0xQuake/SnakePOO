package snake.view;

import snake.model.GameModel;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private final GamePanel gamePanel;
    private final ScorePanel scorePanel;

 
    public GameFrame(GameModel model) {
        setTitle("Snake Game - Java MVC");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
        
        this.scorePanel = new ScorePanel(model);
        this.gamePanel = new GamePanel(model);
        
        add(scorePanel, BorderLayout.NORTH);
        add(gamePanel, BorderLayout.CENTER);
        
        JPanel instructionsPanel = createInstructionsPanel();
        add(instructionsPanel, BorderLayout.SOUTH);
        
        pack();
        setLocationRelativeTo(null);
    }
    
    private JPanel createInstructionsPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(40, 40, 40));
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
        
        Font font = new Font("Arial", Font.PLAIN, 12);
        Color textColor = new Color(180, 180, 180);
        
        String[] instructions = {
            "Arrow Keys: Move",
            "SPACE: Pause",
            "1/2/3: Speed",
            "ENTER: Restart"
        };
        
        for (String instruction : instructions) {
            JLabel label = new JLabel(instruction);
            label.setForeground(textColor);
            label.setFont(font);
            panel.add(label);
        }
        
        return panel;
    }

    
    public GamePanel getGamePanel() {
        return gamePanel;
    }
}
