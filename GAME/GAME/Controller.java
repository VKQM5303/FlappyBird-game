package GAME;
//package GAME;

import java.awt.event.KeyEvent;

interface IStrategy {
    public void controller(Bird bird, KeyEvent kevent);

    public void controllerReleased(Bird bird, KeyEvent kevent);
}

class Controller implements IStrategy {
    public void controller(Bird bird, KeyEvent kevent) {
    }

    public void controllerReleased(Bird bird, KeyEvent kevent) {
        if (kevent.getKeyCode() == KeyEvent.VK_SPACE) { // If the space bar is pressed bird jumps
            bird.jump();
        }
    }

}