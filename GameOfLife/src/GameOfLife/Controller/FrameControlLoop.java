package GameOfLife.Controller;

import static GameOfLife.Common.Config.CONSOLE_VIEW;
import static GameOfLife.Common.Config.FRAME_RATE;

public class FrameControlLoop extends Thread {

    private Runnable updater;

    private boolean isRunning = false;
    private boolean isPause = false;

    private int tics = 0; //For FPS Debugging

    private long initialTime = System.currentTimeMillis(); //time for Loop Control
    private long startTime = System.currentTimeMillis(); //initial time for FPS console logging
    private long timeFrame = 1000 / FRAME_RATE; //time in milliseconds for one loop;
    private long timeCounterMs = 0; //milliseconds counter
    private int FPS = 0;
    private int frame = 0;

    FrameControlLoop(Runnable updater) {
        this.updater = updater;
    }

    public void run() {
        isRunning = true;
        while (isRunning) {
            long currentTime = System.currentTimeMillis();
            timeCounterMs += (currentTime - initialTime);
            initialTime = currentTime;

            if (timeCounterMs >= timeFrame) {
                if (!isPause) {
                    updater.run();
                }

                if (CONSOLE_VIEW) {
                    System.out.println("Frame: " + frame);
                    System.out.println("FPS: " + FPS);
                }
                tics += 1;
                frame++;
                timeCounterMs = 0;
            }
            try {
                Thread.sleep(timeFrame - timeCounterMs);
            } catch (InterruptedException ignored) {
            }
            //if statement for FPS loging in console=========
            if (currentTime - startTime > 1000) {
                if (!CONSOLE_VIEW) {
                    System.out.println("FPS: " + FPS);
                }
                startTime = System.currentTimeMillis();
                FPS = tics;
                tics = 0;
            }

            //===============================================
        }
    }

    void toggleLoopState() {
        isRunning = !isRunning;
    }

    void togglePause() {
        isPause = !isPause;
    }

    void decreaseSpeed() {
        timeFrame = timeFrame + 3;
        System.out.println("new FPS: " + 1000 / timeFrame);
    }

    void increaseSpeed() {
        timeFrame = Math.max(timeFrame - 3, 10);
        System.out.println("new FPS: " + 1000 / timeFrame);
    }
}
