package GAME;
//package GAME;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.Timer;

class Game extends JPanel implements ActionListener {
    private boolean isRunning = false; // Variable used to check if the game is running
    private ProxyImage proxyImage; // Variable used to load the image
    private Image background; // Variable used to store the image
    private Bird bird; // Variable used to store the bird object
    private TubeColumn tubeColumn; // Variable used to store the wall object
    private int highScore;

    public Game() {
        proxyImage = new ProxyImage("bg1.png"); // Load the image
        background = proxyImage.loadImage().getImage(); // Get the image
        setFocusable(true);
        setDoubleBuffered(false);
        addKeyListener(new GameKeyAdapter());
        Timer timer = new Timer(15, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Toolkit.getDefaultToolkit().sync(); // Synchronize the display on some systems
        if (isRunning) {
            bird.tick(); // Update the bird
            tubeColumn.tick(); // Update the wall
            checkColision(); // Check if the bird has collided with the wall
        }
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(background, 0, 0, null);
        if (isRunning) {
            this.bird.render(g2, this);
            this.tubeColumn.render(g2, this);
            g2.setColor(Color.red);
            g.setFont(new Font("MV Boli", 1, 30));
            g2.drawString("Current score: " + this.tubeColumn.getPoints(), 10, 50);

        } else {
            g2.setColor(Color.black);
            g.setFont(new Font("MV Boli", 1, 50));
            g2.drawString("Press Enter to Start Game", Window.WIDTH / 2 - 350, Window.HEIGHT / 2);
            g2.setColor(Color.black);
            g.setFont(new Font("MV Boli", 1, 15));
        }
        g2.setColor(Color.red);
        g.setFont(new Font("MV Boli", 1, 30));
        g2.drawString("High Score: " + highScore, Window.WIDTH - 230, 50);

        g.dispose();
    }

    private void restartGame() {
        if (!isRunning) {
            this.isRunning = true;
            this.bird = new Bird(Window.WIDTH / 2, Window.HEIGHT / 2); // Create the bird object in the middle of the
                                                                       // screen
            this.tubeColumn = new TubeColumn(); // Create the wall object
        }
    }

    private void endGame() {
        this.isRunning = false;
        if (this.tubeColumn.getPoints() > highScore) { // If the current score is higher than the high score
            this.highScore = this.tubeColumn.getPoints(); // Set the high score to the current score
        }
        this.tubeColumn.setPoints(0); // Set the current score to 0

    }

    private void checkColision() {
        Rectangle rectBird = this.bird.getBounds(); // Get the bounds of the bird
        Rectangle rectTube; // Create a variable to store the bounds of the wall

        for (int i = 0; i < this.tubeColumn.getTubes().size(); i++) { // Loop through all the walls
            Tube tempTube = this.tubeColumn.getTubes().get(i); // Get the current wall
            rectTube = tempTube.getBounds(); // Get the bounds of the current wall
            if (rectBird.intersects(rectTube)) { // If the bird has collided with the wall
                endGame(); // End the game
            }
        }
    }

    class GameKeyAdapter extends KeyAdapter {

        private final Controller controller;

        public GameKeyAdapter() {
            controller = new Controller();
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                restartGame();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (isRunning) {
                controller.controllerReleased(bird, e);
            }
        }
    }
}
