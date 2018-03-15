package TheFindingOfIZack.Util;

import TheFindingOfIZack.FileIO.Util.Savable;

/**
 * Created by gordontheo on 4/10/17.
 */
public class Point implements Savable{
    private double x;
    private double y;

    public Point(double x, double y){
        this.x=x;
        this.y=y;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public void setLocation(double x, double y){
        this.x=x;
        this.y=y;
    }

    public void setLocation(Point p){
        this.x=p.getX();
        this.y=p.getY();
    }

    public void move(double x, double y){
        this.x=x;
        this.y=y;
    }
}
