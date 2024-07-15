package GAME;
//package GAME;

import javax.swing.ImageIcon;

interface IImage {
    public ImageIcon loadImage();
}

class ProxyImage implements IImage {

    private final String src;
    private RealImage realImage;

    public ProxyImage(String src) {
        this.src = src;
    }

    public ImageIcon loadImage() {
        if (realImage == null) { // If the image has not been loaded
            this.realImage = new RealImage(src); // Load the image
        }

        return this.realImage.loadImage();
    }

}