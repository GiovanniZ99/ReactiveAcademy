package academy.esercizi.esercizio_29_1;

public class Location {
    private final int coordinataX;
    private final int coordinataY;

    public Location(int coordinataX, int coordinataY) {
        this.coordinataX = coordinataX;
        this.coordinataY = coordinataY;
    }

    public int getCoordinataY() {
        return coordinataY;
    }

    public int getCoordinataX() {
        return coordinataX;
    }
}
