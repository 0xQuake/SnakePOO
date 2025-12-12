package snake;

import snake.controller.GameController;
import snake.model.GameModel;
import snake.view.GameFrame;

import javax.swing.*;

/**
 * Punto de entrada principal para la aplicación del juego Snake.
 * Inicializa los componentes MVC e inicia el juego.
 * 
 * @author Snake Game
 * @version 1.0
 */
public class Main {
    
    /**
     * Punto de entrada de la aplicación.
     * 
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("No se pudo establecer el look and feel del sistema: " + e.getMessage());
        }

        SwingUtilities.invokeLater(() -> {
            GameModel model = new GameModel();
            
            GameFrame view = new GameFrame(model);
            
            GameController controller = new GameController(model, view);
            
            controller.startGame();
            
            System.out.println("¡Juego de la Serpiente iniciado exitosamente!");
            System.out.println("Controles:");
            System.out.println("  Flechas / WASD: Mover serpiente");
            System.out.println("  ESPACIO: Pausar/Reanudar");
            System.out.println("  1/2/3: Cambiar velocidad (Lento/Normal/Rápido)");
            System.out.println("  ENTER: Reiniciar después de fin del juego");
        });
    }
}
