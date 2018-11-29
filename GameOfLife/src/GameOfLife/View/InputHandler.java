package GameOfLife.View;

import javafx.scene.input.KeyEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

class InputHandler extends Observable {

    private Map<String, Object> data = new HashMap<>();

    void toggleDotInModel(int x, int y) {
        data = new HashMap<>();
        data.put("target", "model");
        data.put("X", x);
        data.put("Y", y);
        setChanged();
        notifyObservers(data);
    }

    void requestGamePause() {
        data = new HashMap<>();
        data.put("target", "control");
        data.put("setting", "pause");
        setChanged();
        notifyObservers(data);
    }

    void handleKeyboardInput(KeyEvent event) {
        event.consume();
        data = new HashMap<>();
        switch (event.getText()) {
            case "+":
                data.put("target", "control");
                data.put("setting", "increaseSpeed");
                setChanged();
                notifyObservers(data);

                break;
            case "-":
                data.put("target", "control");
                data.put("setting", "decreaseSpeed");
                setChanged();
                notifyObservers(data);
                break;
            default:
                event.consume();
                break;
        }

    }
}
