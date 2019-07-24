package Data;

public class WindowSize {
    private int width;
    private int heigth;

    public WindowSize(int width, int heigth) {
        this.width = width;
        this.heigth = heigth;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeigth() {
        return heigth;// + 14;
    }

    public void setHeigth(int heigth) {
        this.heigth = heigth;
    }

    @Override
    public String toString() {
        return width + "x" + heigth;
    }
}
