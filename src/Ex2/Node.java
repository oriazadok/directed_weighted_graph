package Ex2;

import api.GeoLocation;
import api.NodeData;

import java.util.ArrayList;

/**
 * This class implements NodeData interface
 */
public class Node implements NodeData {

    private int key;
    private int tag;
    private double weight;
    private String info;
    private Location location;

    public Node(int key, Location location){

        this.key = key;
        this.tag = 0;
        this.weight = 0;
        this.info = "";
        this.location = location;
    }

    /**
     * get the key(=id) of the node
     * @return int
     */
    @Override
    public int getKey() {
        return this.key;
    }

    /**
     * get the location of the current node
     * @return GeoLocation
     */
    @Override
    public GeoLocation getLocation() {
        return this.location;
    }

    /**
     * set the location of the current node
     * @return
     */
    @Override
    public void setLocation(GeoLocation p) {
        location.setX(p.x());
        location.setY(p.y());
    }

    /**
     * get the weight of the current node
     * @return double
     */
    @Override
    public double getWeight() {
        return this.weight;
    }

    /**
     * set the weight of the current node
     * @return
     */
    @Override
    public void setWeight(double w) {
        this.weight = w;
    }

    /**
     * get the info of the current node
     * @return String
     */
    @Override
    public String getInfo() {
        return this.info;
    }

    /**
     * set the info of the current node
     * @return
     */
    @Override
    public void setInfo(String s) {
        this.info = s;
    }

    /**
     * get the tag of the current node
     * @return int
     */
    @Override
    public int getTag() {
        return this.tag;
    }

    /**
     * set the info of the current node
     * @return
     */
    @Override
    public void setTag(int t) {
        this.tag = t;
    }
}