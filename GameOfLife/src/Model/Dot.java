package Model;

public class Dot {

    private boolean alive = false;

    boolean isAlive() {
        return alive;
    }

    public void toggleStatus() {
        alive = !alive;
    }

    @Override
    public String toString() {
        if (alive) {
            return " * ";
        } else {
            return "   ";
        }
    }
}
