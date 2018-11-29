package GameOfLife.Common;

import static GameOfLife.Common.Config.RECTANGLE_HEIGHT;
import static GameOfLife.Common.Config.RECTANGLE_WIDTH;

public class Common {
    public static int translateWindowXtoBoardX(double xPosition) {
        return (int) (xPosition - RECTANGLE_WIDTH / 2) / RECTANGLE_WIDTH;
    }

    public static int translateWindowYtoBoardY(double yPosition) {
        return (int) (yPosition - RECTANGLE_HEIGHT / 2) / RECTANGLE_HEIGHT;
    }
}
