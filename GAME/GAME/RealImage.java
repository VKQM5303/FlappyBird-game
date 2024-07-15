package GAME;
//package GAME;

import javax.swing.ImageIcon;

interface IImage {
    public ImageIcon loadImage();
}

class RealImage implements IImage {

    private final String src;
    private ImageIcon imageIcon;

    public RealImage(String src) {
        this.src = src;
    }

    @Override
    public ImageIcon loadImage() {
        if (imageIcon == null) {
            this.imageIcon = new ImageIcon(getClass().getResource(src));
        }
        return imageIcon;
    }

}
