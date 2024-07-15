package GAME;
//package GAME;

import java.awt.event.KeyEvent;

public interface IStrategy {
    public void controller(Bird bird, KeyEvent kevent);

    public void controllerReleased(Bird bird, KeyEvent kevent);
}
