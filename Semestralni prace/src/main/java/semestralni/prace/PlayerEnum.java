package semestralni.prace;

/**
 * @author Vasek
 */
public enum PlayerEnum {
    FIRST('O'),
    SECOND('X');

    public char symbol;

    PlayerEnum(char symbol) {
        this.symbol = symbol;
    }
}
