package Controller;

import static Common.Config.FRAME_RATE;

public class FrameControlLoop extends Thread {

    private Runnable updater;

    private boolean isRunning = false;

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
                updater.run();
                System.out.println("FPS: " + FPS);
                System.out.println("Frame: " + frame);
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
                System.out.println();
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
}
