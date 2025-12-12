package snake.view;

import snake.config.GameConfig;
import snake.model.GameModel;
import snake.model.GameState;
import snake.model.Position;
import snake.model.composite.SnakeSegment;
import snake.model.factory.Food;
import snake.model.observer.GameObserver;

import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel implements GameObserver {
    private final GameConfig config;
    private final GameModel model;

    private static final Color BACKGROUND_COLOR = new Color(30, 30, 30);
    private static final Color GRID_COLOR = new Color(50, 50, 50);
    private static final Color SNAKE_HEAD_COLOR = new Color(76, 175, 80);
    private static final Color SNAKE_BODY_COLOR = new Color(129, 199, 132);
    private static final Color NORMAL_FOOD_COLOR = new Color(244, 67, 54);
    private static final Color BONUS_FOOD_COLOR = new Color(255, 193, 7);
    private static final Color TEXT_COLOR = Color.WHITE;

  
    public GamePanel(GameModel model) {
        this.config = GameConfig.getInstance();
        this.model = model;
        
        setPreferredSize(new Dimension(config.getPanelWidth(), config.getPanelHeight()));
        setBackground(BACKGROUND_COLOR);
        setFocusable(true);
        
        model.registerObserver(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        drawGrid(g2d);
        drawFood(g2d);
        drawSnake(g2d);
        drawOverlay(g2d);
    }

    /**
     * Draws the background grid.
     */
    private void drawGrid(Graphics2D g) {
        g.setColor(GRID_COLOR);
        int cellSize = config.getCellSize();
        
        for (int x = 0; x <= config.getGridWidth(); x++) {
            g.drawLine(x * cellSize, 0, x * cellSize, config.getPanelHeight());
        }
        for (int y = 0; y <= config.getGridHeight(); y++) {
            g.drawLine(0, y * cellSize, config.getPanelWidth(), y * cellSize);
        }
    }

    /**
     * Draws the food item.
     */
    private void drawFood(Graphics2D g) {
        Food food = model.getCurrentFood();
        if (food == null) return;
        
        int cellSize = config.getCellSize();
        Position pos = food.getPosition();
        
        Color foodColor = food.getType() == Food.FoodType.BONUS 
                ? BONUS_FOOD_COLOR : NORMAL_FOOD_COLOR;
        
        g.setColor(foodColor);
        int padding = 2;
        g.fillOval(
            pos.getX() * cellSize + padding,
            pos.getY() * cellSize + padding,
            cellSize - padding * 2,
            cellSize - padding * 2
        );
        
        if (food.getType() == Food.FoodType.BONUS) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 12));
            String points = "+" + food.getPoints();
            FontMetrics fm = g.getFontMetrics();
            int textX = pos.getX() * cellSize + (cellSize - fm.stringWidth(points)) / 2;
            int textY = pos.getY() * cellSize + (cellSize + fm.getAscent()) / 2 - 2;
            g.drawString(points, textX, textY);
        }
    }

    /**
     * Draws the snake (head and body segments).
     */
    private void drawSnake(Graphics2D g) {
        int cellSize = config.getCellSize();
        int padding = 1;

        Position headPos = model.getSnake().getHead().getPosition();
        g.setColor(SNAKE_HEAD_COLOR);
        g.fillRoundRect(
            headPos.getX() * cellSize + padding,
            headPos.getY() * cellSize + padding,
            cellSize - padding * 2,
            cellSize - padding * 2,
            8, 8
        );

        drawEyes(g, headPos);

        g.setColor(SNAKE_BODY_COLOR);
        for (SnakeSegment segment : model.getSnake().getSegments()) {
            Position pos = segment.getPosition();
            g.fillRoundRect(
                pos.getX() * cellSize + padding,
                pos.getY() * cellSize + padding,
                cellSize - padding * 2,
                cellSize - padding * 2,
                6, 6
            );
        }
    }

    /**
     * Draws the snake's eyes based on direction.
     */
    private void drawEyes(Graphics2D g, Position headPos) {
        int cellSize = config.getCellSize();
        int eyeSize = 4;
        int eyeOffset = 5;
        
        g.setColor(Color.WHITE);
        
        int baseX = headPos.getX() * cellSize;
        int baseY = headPos.getY() * cellSize;
        int centerX = baseX + cellSize / 2;
        int centerY = baseY + cellSize / 2;

        switch (model.getCurrentDirection()) {
            case UP:
                g.fillOval(centerX - eyeOffset - eyeSize/2, baseY + 5, eyeSize, eyeSize);
                g.fillOval(centerX + eyeOffset - eyeSize/2, baseY + 5, eyeSize, eyeSize);
                break;
            case DOWN:
                g.fillOval(centerX - eyeOffset - eyeSize/2, baseY + cellSize - 9, eyeSize, eyeSize);
                g.fillOval(centerX + eyeOffset - eyeSize/2, baseY + cellSize - 9, eyeSize, eyeSize);
                break;
            case LEFT:
                g.fillOval(baseX + 5, centerY - eyeOffset - eyeSize/2, eyeSize, eyeSize);
                g.fillOval(baseX + 5, centerY + eyeOffset - eyeSize/2, eyeSize, eyeSize);
                break;
            case RIGHT:
                g.fillOval(baseX + cellSize - 9, centerY - eyeOffset - eyeSize/2, eyeSize, eyeSize);
                g.fillOval(baseX + cellSize - 9, centerY + eyeOffset - eyeSize/2, eyeSize, eyeSize);
                break;
        }
    }

    /**
     * Draws overlay messages for PAUSED and GAME_OVER states.
     */
    private void drawOverlay(Graphics2D g) {
        if (model.getGameState() == GameState.RUNNING) {
            return;
        }

        g.setColor(new Color(0, 0, 0, 180));
        g.fillRect(0, 0, config.getPanelWidth(), config.getPanelHeight());

        g.setColor(TEXT_COLOR);
        
        if (model.getGameState() == GameState.PAUSED) {
            g.setFont(new Font("Arial", Font.BOLD, 36));
            drawCenteredString(g, "PAUSED", config.getPanelHeight() / 2 - 20);
            g.setFont(new Font("Arial", Font.PLAIN, 16));
            drawCenteredString(g, "Press SPACE to resume", config.getPanelHeight() / 2 + 20);
        } else if (model.getGameState() == GameState.GAME_OVER) {
            g.setFont(new Font("Arial", Font.BOLD, 36));
            drawCenteredString(g, "GAME OVER", config.getPanelHeight() / 2 - 40);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            drawCenteredString(g, "Score: " + model.getScore(), config.getPanelHeight() / 2);
            g.setFont(new Font("Arial", Font.PLAIN, 16));
            drawCenteredString(g, "Press ENTER to restart", config.getPanelHeight() / 2 + 40);
        }
    }

    /**
     * Helper to draw centered text.
     */
    private void drawCenteredString(Graphics2D g, String text, int y) {
        FontMetrics fm = g.getFontMetrics();
        int x = (config.getPanelWidth() - fm.stringWidth(text)) / 2;
        g.drawString(text, x, y);
    }

    @Override
    public void onGameUpdated() {
        repaint();
    }

    @Override
    public void onGameOver(int finalScore) {
        repaint();
    }

    @Override
    public void onScoreChanged(int newScore) {
        repaint();
    }
}
