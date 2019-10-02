package Data;

public class ResizeValues {
    private double fontSize;
    private double padW20;
    private double padW30;
    private double padW40;
    private double padW50;
    private double padW60;
    private double padW70;
    private double padW80;
    private double padW90;
    private double padW100;
    private double padW110;
    private double padW120;
    private double padW130;
    private double padW140;
    private double padW150;
    private double padW360;
    private double padH15;
    private double padH25;
    private double padH30;
    private double padH40;
    private double padH50;
    private double padH60;
    private double padH70;
    private double padH80;
    private double padH90;
    private double padH100;
    private double padH110;
    private double padH120;
    private double padH130;
    private double padH140;
    private double padH150;
    private double padH160;
    private double padH170;
    private double padH190;
    private double padH210;
    private double padH240;
    private double padH250;
    private double padH270;
    private double padH290;
    private double flagSizes;
    private double tableW;
    private double tableH;
    private double buttonsBarPadH = 10.0;
    private double buttonMinimizePadW = 90.0;
    private double buttonMaximizePadW = 50.0;
    private double buttonExitPadW = 10.0;

    public ResizeValues(double windowWidth, double windowHeight){
        fontSize = (windowHeight + windowWidth) / 100;
        padW20 = windowWidth / 51.195;
        padW30 = windowWidth / 34.13;
        padW40 = windowWidth / 25.6;
        padW50 = windowWidth / 20.48;
        padW60 = windowWidth / 17.06;
        padW70 = windowWidth / 14.628;
        padW80 = windowWidth / 12.8;
        padW90 = windowWidth / 11.378;
        padW100 = windowWidth / 10.24;
        padW110 = windowWidth / 9.309;
        padW120 = windowWidth / 8.53;
        padW130 = windowWidth / 7.877;
        padW140 = windowWidth / 7.314;
        padW150 = windowWidth / 6.827;
        padW360 = windowWidth / 2.844;
        padH15 = windowHeight / 38.4;
        padH25 = windowHeight / 23.04;
        padH30 = windowHeight / 19.2;
        padH40 = windowHeight / 14.4;
        padH50 = windowHeight / 11.52;
        padH60 = windowHeight / 9.6;
        padH70 = windowHeight / 8.228;
        padH80 = windowHeight / 7.2;
        padH90 = windowHeight / 6.4;
        padH100 = windowHeight / 5.76;
        padH110 = windowHeight / 5.236;
        padH120 = windowHeight / 4.8;
        padH130 = windowHeight / 4.43;
        padH140 = windowHeight / 4.114;
        padH150 = windowHeight / 3.839;
        padH160 = windowHeight / 3.6;
        padH170 = windowHeight / 3.388;
        padH190 = windowHeight / 3;
        padH210 = windowHeight / 2.742;
        padH240 = windowHeight / 2.4;
        padH250 = windowHeight / 2.304;
        padH270 = windowHeight / 2.133;
        padH290 = windowHeight / 1.986;
        flagSizes = windowWidth / 16;
        tableW = windowWidth / 2.56;
        tableH = windowHeight / 1.92;
    }

    public double getFontSize() {
        return fontSize;
    }

    public double getPadW20() {
        return padW20;
    }

    public double getPadW30() {
        return padW30;
    }

    public double getPadW40() {
        return padW40;
    }

    public double getPadW50() {
        return padW50;
    }

    public double getPadW60() {
        return padW60;
    }

    public double getPadW70() {
        return padW70;
    }

    public double getPadW80() {
        return padW80;
    }

    public double getPadW90() {
        return padW90;
    }

    public double getPadW100() {
        return padW100;
    }

    public double getPadW110() {
        return padW110;
    }

    public double getPadW120() {
        return padW120;
    }

    public double getPadW130() {
        return padW130;
    }

    public double getPadW140() {
        return padW140;
    }

    public double getPadW150() {
        return padW150;
    }

    public double getPadW360() {
        return padW360;
    }

    public double getPadH15() {
        return padH15;
    }

    public double getPadH25() {
        return padH25;
    }

    public double getPadH30() {
        return padH30;
    }

    public double getPadH40() {
        return padH40;
    }

    public double getPadH50() {
        return padH50;
    }

    public double getPadH60() {
        return padH60;
    }

    public double getPadH70() {
        return padH70;
    }

    public double getPadH80() {
        return padH80;
    }

    public double getPadH90() {
        return padH90;
    }

    public double getPadH100() {
        return padH100;
    }

    public double getPadH110() {
        return padH110;
    }

    public double getPadH120() {
        return padH120;
    }

    public double getPadH130() {
        return padH130;
    }

    public double getPadH140() {
        return padH140;
    }

    public double getPadH150() {
        return padH150;
    }

    public double getPadH160() {
        return padH160;
    }

    public double getPadH170() {
        return padH170;
    }

    public double getPadH190() {
        return padH190;
    }

    public double getPadH210() {
        return padH210;
    }

    public double getPadH240() {
        return padH240;
    }

    public double getPadH250() {
        return padH250;
    }

    public double getPadH270() {
        return padH270;
    }

    public double getPadH290() {
        return padH290;
    }

    public double getFlagSizes() {
        return flagSizes;
    }

    public double getTableW() {
        return tableW;
    }

    public double getTableH() {
        return tableH;
    }

    public double getButtonsBarPadH() {
        return buttonsBarPadH;
    }

    public double getButtonMinimizePadW() {
        return buttonMinimizePadW;
    }

    public double getButtonMaximizePadW() {
        return buttonMaximizePadW;
    }

    public double getButtonExitPadW() {
        return buttonExitPadW;
    }
}
