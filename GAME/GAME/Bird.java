package GAME;
//package GAME;

import java.awt.*;
import java.awt.image.ImageObserver;

class Bird extends GameObject {
    private ProxyImage proxyImage; // ProxyImage object used to load the image of the bird
    private Tube[] tube; // Array of Tube objects used to create the walls in the game
    // Constructor

    public Bird(int x, int y) {
        super(x, y);
        if (proxyImage == null) {
            proxyImage = new ProxyImage("dragon2.png"); // Load the image of the bird
        }
        this.image = proxyImage.loadImage().getImage();
        this.width = image.getWidth(null); // Set the width and height of the bird
        this.height = image.getHeight(null);
        this.x -= width; // Adjust the x position of the bird
        this.y -= height; // Adjust the y position of the bird
        tube = new Tube[1]; // Create a new array of Tube objects
        tube[0] = new Tube(900, Window.HEIGHT - 60); // Create the first wall
        this.dy = 2; // Set the initial speed of the bird
    }

    // Method used to move the bird
    public void tick() {
        if (dy < 5) { // If the speed of the bird is less than 5
            dy += 2; // Increase the speed of the bird
        }
        this.y += dy; // Move the bird down the screen
        tube[0].tick(); // Move the wall down the screen
        checkWindowBorder(); // Check if the bird has hit the top or bottom of the screen
    }

    public void jump() {
        if (dy > 0) { // If the speed of the bird is greater than 0
            dy = 0; // Set the speed of the bird to 0
        }
        dy -= 15; // Move the bird up the screen
    }

    // Method used to check if the bird has hit the top or bottom of the screen
    private void checkWindowBorder() {
        if (this.x > Window.WIDTH) { // If the bird has moved off the right side of the screen
            this.x = Window.WIDTH; // Set the x position of the bird to the right side of the screen
        }
        if (this.x < 0) { // If the bird has moved off the left side of the screen
            this.x = 0; // Set the x position of the bird to the left side of the screen
        }
        if (this.y > Window.HEIGHT - 50) { // If the bird has moved off the bottom of the screen
            this.y = Window.HEIGHT - 50; // Set the y position of the bird to the bottom of the screen
        }
        if (this.y < 0) { // If the bird has moved off the top of the screen
            this.y = 0; // Set the y position of the bird to the top of the screen
        }
    }

    // Method used to check if the bird has hit the wall
    public void render(Graphics2D g, ImageObserver obs) {
        g.drawImage(image, x, y, obs); // Draw the bird
        tube[0].render(g, obs); // Draw the wall
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

}
