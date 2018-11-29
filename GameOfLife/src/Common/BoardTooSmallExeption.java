package Common;

public class BoardTooSmallExeption extends Exception {
    public BoardTooSmallExeption(String errorMessage) {
        super(errorMessage);
    }
}
