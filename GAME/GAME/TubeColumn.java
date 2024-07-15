package GAME;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class TubeColumn {
    private int base = Window.HEIGHT - 60;

    private List<Tube> tubes;
    private Random random;
    private int points = 0; // Variable used to keep track of the score
    private int speed = 5; // Variable used to set the speed of the wall
    private int changeSpeed = speed;

    public TubeColumn() {
        tubes = new ArrayList<>();
        random = new Random();
        initTubes();
    }

    // Method used to create the wall
    private void initTubes() {
        int last = base;
        int randWay = random.nextInt(10);
        // Create the first wall in the game and set the position of the wall to the
        // right side of the screen
        for (int i = 0; i < 20; i++) {

            Tube tempTube = new Tube(900, last); // Create a new Tube object
            tempTube.setDx(speed); // Set the speed of the wall
            last = tempTube.getY() - tempTube.getHeight(); // Set the position of the wall
            if (i < randWay || i > randWay + 4) { // If the wall is not in the middle of the screen
                tubes.add(tempTube); // Add the wall to the array of Tube objects
            }
        }
    }

    // Method used to check the position of the walls and to create new walls
    public void tick() {
        for (int i = 0; i < tubes.size(); i++) { // Loop through the array of Tube objects
            tubes.get(i).tick(); // Get the position of the wall

            if (tubes.get(i).getX() < 0) { // If the wall has moved off the left side of the screen
                tubes.remove(tubes.get(i)); // Remove the wall from the array of Tube objects
            }
        }
        if (tubes.isEmpty()) { // If the array of Tube objects is empty
            this.points += 1; // Increase the score by 1
            if (changeSpeed == points) {
                this.speed += 1; // Increase the speed of the wall by 1
                changeSpeed += 5;
            }
            initTubes(); // Create a new wall
        }

    }

    // Method used to draw the walls
    public void render(Graphics2D g, ImageObserver obs) {
        for (int i = 0; i < tubes.size(); i++) { // Loop through the array of Tube objects
            tubes.get(i).render(g, obs); // Draw the wall
        }

    }

    public List<Tube> getTubes() {
        return tubes;
    }

    public void setTubes(List<Tube> tubes) {
        this.tubes = tubes;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

}