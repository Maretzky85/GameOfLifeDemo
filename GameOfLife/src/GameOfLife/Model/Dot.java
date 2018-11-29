package GameOfLife.Model;

public class Dot {

    private boolean alive = true;

    private int generation = 1;

    @Override
    public String toString() {
        if (alive) {
            return "*";
        } else {
            return " ";
        }
    }

    public int getGeneration() {
        generation++;
        return generation;
    }
}
