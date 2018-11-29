package GameOfLife.CommonUsage;

import static GameOfLife.CommonUsage.Config.RECTANGLE_HEIGHT;
import static GameOfLife.CommonUsage.Config.RECTANGLE_WIDTH;

public class CommonFunctions {
    public static int translateWindowXtoBoardX(double xPosition) {
        return (int) (xPosition - RECTANGLE_WIDTH / 2) / RECTANGLE_WIDTH;
    }

    public static int translateWindowYtoBoardY(double yPosition) {
        return (int) (yPosition - RECTANGLE_HEIGHT / 2) / RECTANGLE_HEIGHT;
    }
}
