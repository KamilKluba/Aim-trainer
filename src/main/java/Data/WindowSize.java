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

    public void setWidth(double width) {
        this.width = (int)width;
    }

    public int getHeight() {
        return heigth;// + 14;
    }

    public void setHeight(double heigth) {
        this.heigth = (int)heigth;
    }

    @Override
    public String toString() {
        return width + "x" + heigth;
    }
}
