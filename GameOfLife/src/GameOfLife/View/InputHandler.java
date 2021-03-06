package GameOfLife.View;

import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

import java.util.Observable;

import static GameOfLife.Common.Config.RECTANGLE_HEIGHT;
import static GameOfLife.Common.Config.RECTANGLE_WIDTH;
import static javafx.scene.input.KeyEvent.KEY_PRESSED;
import static javafx.scene.input.MouseEvent.MOUSE_PRESSED;

/**
 * Input Handler Class - extends observable Class
 * Receives input calls from JavaFX and passes through to observators with proper parameters
 */
class InputHandler extends Observable {

    /**
     * handleInput method
     * handles variety of inputs events
     * This version supports mouse and key events
     *
     * @param event - any event than extends Input Event class
     */
    void handleInput(InputEvent event) {
        if (event.getEventType().equals(MOUSE_PRESSED)) {
            MouseEvent mouseEvent = (MouseEvent) event;
            switch (mouseEvent.getButton()) {
                case PRIMARY:
                    if (((MouseEvent) event).getPickResult().getIntersectedNode() != null) {
                        Rectangle rectangle = (Rectangle) mouseEvent.getPickResult().getIntersectedNode();
                        int gridXposition = (int) (rectangle.getX() / RECTANGLE_WIDTH);
                        int gridYposition = (int) (rectangle.getY() / RECTANGLE_HEIGHT);
                        int position[] = new int[]{gridXposition, gridYposition};
                        setChanged();
                        notifyObservers(position);
                    }
                    break;
                case SECONDARY:
                    setChanged();
                    notifyObservers("p");
                    break;
                default:
                    break;
            }
        } else if (event.getEventType().equals(KEY_PRESSED)) {
            KeyEvent keyEvent = (KeyEvent) event;
            setChanged();
            notifyObservers(keyEvent.getText());
        }
    }
}
