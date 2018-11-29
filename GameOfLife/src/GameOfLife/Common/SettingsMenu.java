package GameOfLife.Common;

import java.util.Scanner;

public class SettingsMenu {
    private static Scanner scanner = new Scanner(System.in);

    private static void clearScreen() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            } else {
                Runtime.getRuntime().exec("clear");
                System.out.print("\033[H\033[2J");
            }
        } catch (Exception ignored) {
        }
    }

    public static void settingMenu() {
        int choice = 9;
        while (choice > 0) {
            clearScreen();
            System.out.println("SettingsMenu:\n" +
                    "1: View menu\n" +
                    "2: Game options menu\n" +
                    "0: Quit and start game");
            switch (scanner.nextInt()) {
                case 1:
                    viewMenu();
                    break;
                case 2:
                    gameMenu();
                    break;
                case 0:
                    choice = 0;
                    break;
                default:
                    System.out.print("Invalid option");
                    scanner.nextLine();
                    break;
            }
        }

    }

    private static void gameMenu() {
        int choice = 9;
        clearScreen();
        while (choice > 0) {
            System.out.println("Game settings menu:\n" +
                    "1: Set board X size - " + Config.getxSize() + "\n" +
                    "2: Set board Y size - " + Config.getySize() + "\n" +
                    "3: Set speed (FPS) - " + Config.getFrameRate() + "\n" +
                    "0: Return");
            switch (scanner.nextInt()) {
                case 1:
                    System.out.println("Please enter board X size\n");
                    int boardXsize = scanner.nextInt();
                    Config.setxSize(boardXsize);
                    clearScreen();
                    System.out.println("Set board X size to: " + boardXsize + "\n");
                    scanner.nextLine();
                    break;
                case 2:
                    System.out.println("Please enter board Y size\n");
                    int boardYsize = scanner.nextInt();
                    Config.setySize(boardYsize);
                    clearScreen();
                    System.out.println("Set board Y size to: " + boardYsize + "\n");
                    scanner.nextLine();
                    break;
                case 3:
                    System.out.println("Please enter desired speed in FPS\n");
                    int requestedFPS = scanner.nextInt();
                    Config.setFrameRate(requestedFPS);
                    clearScreen();
                    System.out.println("Desired FPS set to: " + requestedFPS + "\n");
                    scanner.nextLine();
                    break;
                default:
                    System.out.print("Invalid option");
                    scanner.nextLine();
                    break;
                case 0:
                    choice = 0;
                    break;
            }
        }
    }

    private static void viewMenu() {
        int choice = 9;
        clearScreen();
        while (choice > 0) {
            System.out.println("View Menu:\n" +
                    "1: Set Console View - current state - " + Config.isConsoleView() + "\n2: Set JavaFX View\n" +
                    "3: Set Window Height - " + Config.getRequestedWindowHeight() + "\n" +
                    "4: Set Window Width - " + Config.getRequestedWindowWidth() + "\n" +
                    "0: Return");
            switch (scanner.nextInt()) {
                case 1:
                    Config.setConsoleView(true);
                    clearScreen();
                    System.out.println("Set View to Console GameOfLife.View\n");
                    scanner.nextLine();
                    break;
                case 2:
                    Config.setConsoleView(false);
                    clearScreen();
                    System.out.println("Set View to JavaFX GameOfLife.View\n");
                    scanner.nextLine();
                    break;
                case 3:
                    System.out.println("Please enter requested window height");
                    int heightInput = scanner.nextInt();
                    if (!Config.setRequestedWindowHeight(heightInput)) {
                        System.out.println("Press enter to continue");
                        String temp = scanner.nextLine();
                    } else {
                        clearScreen();
                        System.out.println("Set Window height to " + heightInput + "\n");
                        scanner.nextLine();
                    }

                    break;
                case 4:
                    System.out.println("Please enter requested window width");
                    int widthinput = scanner.nextInt();
                    if (!Config.setRequestedWindowWidth(widthinput)) {
                        System.out.println("Press enter to continue");
                        String temp = scanner.nextLine();
                    } else {
                        clearScreen();
                        System.out.println("Set Window height to " + widthinput + "\n");
                        scanner.nextLine();
                    }
                    break;
                case 0:
                    choice = 0;
                    break;
            }
        }
    }


}
