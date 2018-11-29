package GameOfLife.View;

import GameOfLife.Common.Common;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.Observable;

import static javafx.scene.input.KeyEvent.KEY_PRESSED;
import static javafx.scene.input.MouseEvent.MOUSE_PRESSED;

class InputHandler extends Observable {

    void handleInput(InputEvent event) {
        if (event.getEventType().equals(MOUSE_PRESSED)) {
            MouseEvent mouseEvent = (MouseEvent) event;
            switch (mouseEvent.getButton()) {
                case NONE:
                    break;
                case PRIMARY:
                    int gridXposition = Common.translateWindowXtoBoardX(mouseEvent.getX());
                    int gridYposition = Common.translateWindowYtoBoardY(mouseEvent.getY());
                    int position[] = new int[]{gridXposition, gridYposition};
                    setChanged();
                    notifyObservers(position);
                    break;
                case MIDDLE:
                    break;
                case SECONDARY:
                    setChanged();
                    notifyObservers("p");
                    break;
            }
        } else if (event.getEventType().equals(KEY_PRESSED)) {
            KeyEvent keyEvent = (KeyEvent) event;
            setChanged();
            notifyObservers(keyEvent.getText());
        }
    }
}
