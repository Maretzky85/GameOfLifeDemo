package GameOfLife.Controller;

import GameOfLife.Common.Config;

import static GameOfLife.Common.Config.CONSOLE_VIEW;
import static GameOfLife.Common.Config.FRAME_RATE;

/**
 * This class is for controlling how many frames is generated for second
 * This is clock for theoretical model, that run selected functions in requested frequency
 * Additional sends call one per second (for FPS generation)
 */

public class FrameControlLoop extends Thread {

    private Runnable updater;
    private Runnable statTimer;

    private boolean isRunning = false;
    private boolean isPause = true;

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

    /**
     * Run function for starting loop control
     * checks time between current time and start time, waits for rest ms,
     * if time between current and start time is greater than time frame, than runs required command.
     */
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
            //FPS loging in ======================
            if (currentTime - startTime > 1000) {
                statTimer.run();
                startTime = System.currentTimeMillis();
                FPS = tics;
                tics = 0;
            }

            //===============================================
        }
    }

    /**
     * function for killing FrameControlLoop
     */
    void toggleLoopState() {
        isRunning = !isRunning;
    }

    /**
     * togglePause
     * while true does not send update request
     */
    void togglePause() {
        isPause = !isPause;
    }

    /**
     * decrease/increase speed
     * decrease or increase update speed by altering timeframe value
     */
    void decreaseSpeed() {
        timeFrame = timeFrame + 3;
        if (Config.isPrintStatistics()) {
            System.out.println("new requested FPS: " + 1000 / timeFrame);
        }
    }

    void increaseSpeed() {
        timeFrame = Math.max(timeFrame - 3, 5);
        if (Config.isPrintStatistics()) {
            System.out.println("new requested FPS: " + 1000 / timeFrame);
        }

    }

    /**
     * Aditional timed function - called once per second
     *
     * @param showStatistics - runnable function called once per second
     */
    void attachStatisticTimer(Runnable showStatistics) {
        statTimer = showStatistics;
    }

    int getFPS() {
        return FPS;
    }
}
