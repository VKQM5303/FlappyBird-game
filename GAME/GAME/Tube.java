package GAME;

import java.awt.*;
import java.awt.image.ImageObserver;

class Tube extends GameObject {

    private ProxyImage proxyImage;

    public Tube(int x, int y) {
        super(x, y);
        if (proxyImage == null) { // If the image has not been loaded
            proxyImage = new ProxyImage("trap.png"); // Load the image

        }
        this.image = proxyImage.loadImage().getImage(); // Get the image
        this.width = image.getWidth(null); // set the width of the image
        this.height = image.getHeight(null); // set the height of the image
    }

    @Override
    public void tick() {
        this.x -= dx;
    }

    @Override
    public void render(Graphics2D g, ImageObserver obs) {
        g.drawImage(image, x, y, obs);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
