package Ex2;

import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * This class implements DirectedWeightedGraph interface
 */
public class Graph implements DirectedWeightedGraph {

    private HashMap<Integer, NodeData> nodes;
    private HashMap<NodeData, HashMap> edges;
    private int sumOfEdges;
    private int mc;

    private ArrayList<Integer>[] out;
    private ArrayList<Integer>[] in;

    public Graph(HashMap<Integer, NodeData> nodes, HashMap<NodeData, HashMap> edges) {
        this.nodes = nodes;
        this.edges = edges;
        this.sumOfEdges = 0;
        this.mc = 0;

        out = new ArrayList[100];
        in = new ArrayList[100];
    }

    /**
     * get the nodes of the Graph
     * @return HashMap<Integer, NodeData>
     */
    public HashMap<Integer, NodeData> getNodes() {
        return this.nodes;
    }

    /**
     * set the nodes of the Graph
     * @param nodes
     */
    public void setNodes(HashMap<Integer, NodeData> nodes) {
        this.nodes = nodes;
    }

    /**
     * get the HashMap represents the edges of the Graph
     * @return HashMap<NodeData, HashMap>
     */
    public HashMap<NodeData, HashMap> getEdges() {
        return this.edges;
    }

    /**
     * set the HashMap represents the nodes of the Graph
     * @param edges
     */
    public void setEdges(HashMap<NodeData, HashMap > edges) {
        this.edges = edges;
    }

    public ArrayList<Integer>[] getOut() {
        return out;
    }

    public void setOut(ArrayList<Integer>[] out) {
        this.out = out;
    }

    public ArrayList<Integer>[] getIn() {
        return in;
    }

    public void setIn(ArrayList<Integer>[] in) {
        this.in = in;
    }

    /**
     * get the node by the node_id,
     * @param key - the node_id
     * @return NodeData, null if none
     */
    @Override
    public NodeData getNode(int key) {
        return this.nodes.get(key);
    }

    /**
     * get the edge by two nodes represent source and destination
     * @param src - the id of the node the edge coming from
     * @param dest - the id of the node the edge go to
     * @return EdgeData
     */
    @Override
    public EdgeData getEdge(int src, int dest) {
        NodeData n = getNode(src);
        return (EdgeData) this.edges.get(n).get(dest);
    }

    /**
     * add a node to the graph
     * @param n
     */
    @Override
    public void addNode(NodeData n) {
        if (this.nodes.get(n.getKey()) != null) {
            System.out.println("The id of this Node is already exist");
            return;
        }
        this.nodes.put(n.getKey(), n);
        this.edges.put(n, new HashMap<Integer, EdgeData>());

        if (n.getKey() >= this.out.length) {
            increase(n.getKey());
        }
        this.out[n.getKey()] = new ArrayList<Integer>();
        this.in[n.getKey()] = new ArrayList<Integer>();
    }

    private void increase(int num) {
        ArrayList<Integer>[] new_out = new ArrayList[num + 100];
        ArrayList<Integer>[] new_in = new ArrayList[num + 100];
        for (int i=0; i< out.length; i++) {
            new_out[i] = this.out[i];
            new_in[i] = this.in[i];
        }
        this.out = new_out;
        this.in = new_in;
    }

    /**
     * connect two nodes -> adding an edge to the graph
     * @param src - the source of the edge.
     * @param dest - the destination of the edge.
     * @param w - positive weight representing the cost (aka time, price, etc) between src-->dest.
     */
    @Override
    public void connect(int src, int dest, double w) {
        if (src == dest){
            System.out.println("src and dest must have different values");
            return;
        }

        NodeData from = getNode(src);
        if (from == null) {
            System.out.println("the id of the source does not exist in the graph");
            return;
        }
        NodeData to = getNode(dest);
        if (to == null) {
            System.out.println("the id of the destination does not exist in the graph");
            return;
        }

        EdgeData e = new Edge(src, dest, w);

        this.edges.get(from).put(dest, e);

        //insert into the in/out arraylist of the nodes
        if (this.out[src] == null) {
            this.out[src] = new ArrayList<>();
        }
        if (this.in[dest] == null) {
            this.in[dest] = new ArrayList<>();
        }
        this.out[src].add(dest);
        this.in[dest].add(src);

        this.sumOfEdges++;
    }

    /**
     *
     * @return
     * @throws RuntimeException
     */
    @Override
    public Iterator<NodeData> nodeIter() {
        Iterator<NodeData> nodeIter = new Iterator<NodeData>() {
            private Iterator<NodeData> iter = nodes.values().iterator();
            private int modeCounter = mc;

            @Override
            public void remove() {
                if (modeCounter != mc) { throw new RuntimeException();}
                NodeData node = iter.next();
                removeNode(node.getKey());
                modeCounter++;
                iter.remove();
            }
            @Override
            public boolean hasNext() {
                if (modeCounter != mc) { throw new RuntimeException();}
                return iter.hasNext();
            }

            @Override
            public NodeData next() {
                if (modeCounter != mc) { throw new RuntimeException();}
                return iter.next();
            }
        };
        return nodeIter;
    }

    /**
     *
     * @return
     */
    @Override
    public Iterator<EdgeData> edgeIter()  throws RuntimeException {
        HashMap<Integer, EdgeData> allEdges = new HashMap<>();
        int counter = 0;
        for (NodeData n : nodes.values()) {
            HashMap<Integer, EdgeData> hm = edges.get(n);
            for (EdgeData edge : hm.values()) {
                allEdges.put(counter, edge);
                counter++;
            }
        }

        Iterator<EdgeData> edges = allEdges.values().iterator();
        return edges;
    }

    /**
     *
     * @param node_id
     * @return
     */
    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        HashMap<Integer, EdgeData> hm = edges.get(getNode(node_id));
        Iterator<EdgeData> edgeIter = new Iterator<>() {
            private Iterator<EdgeData> iter = hm.values().iterator();
            private int modeCounter = mc;
            @Override
            public void remove() {
                if (modeCounter != mc) { throw new RuntimeException();}
                EdgeData edge = iter.next();
                modeCounter++;
                iter.remove();
            }

            @Override
            public boolean hasNext() {
                if (modeCounter != mc) { throw new RuntimeException();}
                return iter.hasNext();
            }

            @Override
            public EdgeData next() {
                if (modeCounter != mc) { throw new RuntimeException();}
                return iter.next();
            }
        };
        return edgeIter;
    }

    /**
     * remove the node with the given key (=id)
     * @param key
     * @return  erased NodeData
     */
    @Override
    public NodeData removeNode(int key) {
        if (this.nodes.containsKey(key) == false) {
            System.out.println("this key does not exist");
            return null;
        }
        NodeData n = getNode(key);

        // remove edges that go out from the node
        this.edges.remove(n);
        this.out[key] = null;

        //remove edges that go into the node
        ArrayList<Integer> inForKey = this.in[key];
        for (int i=0; i<inForKey.size(); i++) {
            NodeData t = getNode(inForKey.get(i));
            this.edges.get(t).remove(key);
//            this.out
        }
        this.in[key] = null;

        //remove the node
        this.nodes.remove(key);
        return n;
    }

    /**
     * remove the edge with the given two id of nodes representing source and destination
     * @param src - the id of the node the edge coming from
     * @param dest - the id of the node the edge go to
     * @return erased EdgeData
     */
    @Override
    public EdgeData removeEdge(int src, int dest) {
        if (src == dest){
            System.out.println("src and dest must have different values");
            return null;
        }

        NodeData from = getNode(src);
        if (from == null) {
            System.out.println("the id of the source does not exist in the graph");
            return null;
        }
        NodeData to = getNode(dest);
        if (to == null) {
            System.out.println("the id of the destination does not exist in the graph");
            return null;
        }
        NodeData n = getNode(src);
        EdgeData e = (EdgeData) this.edges.get(n).get(dest);
        this.edges.get(n).remove(dest);
        sumOfEdges--;
        return e;
    }

    /**
     * returns the number of vertices (nodes) in the graph
     * @return int
     */
    @Override
    public int nodeSize() {
        return this.nodes.size();
    }

    /**
     * returns the number of edges in the graph
     * @return int
     */
    @Override
    public int edgeSize() {
        return this.sumOfEdges;
    }

    /**
     * returns the Mode Count - for testing changes in the graph
     * @return
     */
    @Override
    public int getMC() {
        return this.mc;
    }

}
