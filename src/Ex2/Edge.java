package Ex2;

import api.EdgeData;

/**
 * This class implements EdgeData interface
 */
public class Edge implements EdgeData {
    private int src;
    private int dest;
    private double w;
    private int tag;
    private String info;

    public  Edge(int src, int dest, double w){
        if (w < 0) {
            throw new ArithmeticException("You inserted a negative value");
        }
        this.src = src;
        this.dest = dest;
        this.w = w;
        this.tag = 0 ;
        this.info = "";

    }

    /**
     * get the id of the node the edge coming from
     * @return int
     */
    @Override
    public int getSrc() {
        return this.src;
    }

    /**
     * get the id of the node the edge go to
     * @return int
     */
    @Override
    public int getDest() {
        return this.dest;
    }

    /**
     * get the weight of the edge
     * @return double
     */
    @Override
    public double getWeight() {
        return this.w;
    }

    /**
     * get the info of the edge
     * @return String
     */
    @Override
    public String getInfo() {              //unimplement
        return this.info;
    }

    /**
     * set the info of the edge
     * @param s
     */
    @Override
    public void setInfo(String s) {
        this.info = s;
    }

    /**
     * get the tag of the edge
     * @return int
     */
    @Override
    public int getTag() {
        return this.tag;
    }

    /**
     * set the tag of the edge
     * @param t
     */
    @Override
    public void setTag(int t) {
        this.tag = t;
    }
}