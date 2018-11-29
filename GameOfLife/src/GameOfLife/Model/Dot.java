package GameOfLife.Model;

public class Dot {

    private boolean alive = true;

    public boolean isAlive() {
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
