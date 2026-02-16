package TheFindingOfIZack.Util;

import TheFindingOfIZack.FileIO.Util.Savable;

public class SimpleBoundingBox implements Savable {
    private double minX;
    private double minY;
    private double width;
    private double height;

    public SimpleBoundingBox(double minX, double minY, double width, double height) {
        this.minX = minX;
        this.minY = minY;
        this.width = width;
        this.height = height;
    }

    public boolean intersects(double x, double y, double w, double h) {
        return x < minX + width && x + w > minX && y < minY + height && y + h > minY;
    }

    public boolean intersects(SimpleBoundingBox other) {
        return other.minX < minX + width && other.minX + other.width > minX &&
               other.minY < minY + height && other.minY + other.height > minY;
    }

    public double getMinX() { return minX; }
    public double getMinY() { return minY; }
    public double getWidth() { return width; }
    public double getHeight() { return height; }
}
