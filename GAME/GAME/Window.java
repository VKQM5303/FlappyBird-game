package GAME;

import javax.swing.*;
import java.awt.*;

class Window {
    public static int WIDTH = 900; // Set the width of the window
    public static int HEIGHT = 600; // Set the height of the window

    public Window(int width, int height, String title, Game game) {
        JFrame frame = new JFrame();
        frame.add(game);
        frame.setTitle(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the window when the user clicks the close button
        frame.setMaximumSize(new Dimension(width, height)); // Set the maximum size of the window
        frame.setPreferredSize(new Dimension(width, height)); // Set the preferred size of the window
        frame.setMinimumSize(new Dimension(width, height));
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

    }

    // Run the application from here
    public static void main(String[] args) {
        Game game = new Game();
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            Window window = new Window(WIDTH, HEIGHT, "Flappy Dragon", game);
        });
    }
}
