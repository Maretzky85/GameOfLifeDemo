package GameOfLife.Model;

public class Dot {

    private boolean alive = true;

    private int generation = 0;

    @Override
    public String toString() {
        if (alive) {
            return "*";
        } else {
            return " ";
        }
    }

    public int getGeneration() {
        if (generation < 255) {
            generation++;
        }
        return generation;
    }
}
