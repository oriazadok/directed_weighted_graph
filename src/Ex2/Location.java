package Ex2;

import api.GeoLocation;

/**
 * This class implements GeoLocation interface
 */
public class Location implements GeoLocation {

    private double x;
    private double y;
    private double z;

    public Location(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }


    /**
     * setter for x value
     * @param x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * setter for y value
     * @param y
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * setter for z value
     * @param z
     */
    public void setZ(double z) {
        this.z = z;
    }


    /**
     * getters for x value
     * @return double
     */
    @Override
    public double x() {
        return this.x;
    }

    /**
     * getters for y value
     * @return double
     */
    @Override
    public double y() {
        return this.y;
    }

    /**
     * getters for z value
     * @return double
     */
    @Override
    public double z() {
        return this.z;
    }


    /**
     * This function measures the distance between the current location and a given location
     * @param g
     * @return double
     */
    @Override
    public double distance(GeoLocation g) {

        double dis_x = Math.abs(this.x - g.x());
        double dis_y = Math.abs(this.y - g.y());
        dis_x = dis_x * dis_x;
        dis_y = dis_y * dis_y;
        return Math.sqrt(dis_x + dis_y);
    }


}
