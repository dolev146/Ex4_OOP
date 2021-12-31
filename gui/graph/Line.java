package gui.graph;

public class Line {
    public double x1;
    public double x2;
    public double y1;
    public double y2;
    public String w;

    public Line(double x1, double x2, double y1, double y2, double w) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        String text = Double.toString(Math.abs(w));
        int integerPlaces = text.indexOf('.');
        int decimalPlaces = text.length() - integerPlaces - 1;
        if (decimalPlaces <= 7) {
            this.w = String.valueOf(w);
        } else {
            this.w = String.valueOf(w).substring(0, String.valueOf(w).indexOf(".") + 5);
        }

    }
}
