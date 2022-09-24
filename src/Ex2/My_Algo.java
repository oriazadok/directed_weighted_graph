package Ex2;

import api.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


import java.io.*;
import java.util.*;


/**
 * This class implements DirectedWeightedGraphAlgorithms interface
 */
public class My_Algo implements DirectedWeightedGraphAlgorithms {

    public final double EPS = 0.0001;

    private HashMap<Integer, NodeData> nodes = new HashMap<>();
    private HashMap<NodeData, HashMap> edges = new HashMap<>();
    private Graph g;

    private Stack<Integer> stack = new Stack<>();

    public My_Algo() {

        g = new Graph(nodes, edges);
    }

    public My_Algo(Graph g){
        this.g = g;
    }

    @Override
    public void init(DirectedWeightedGraph g) {
        this.g = (Graph) g;
    }

    /**
     * return the graph
     * @return DirectedWeightedGraph
     */
    @Override
    public DirectedWeightedGraph getGraph() {
        return this.g;
    }

    /**
     * return a copy of the graph
     * @return DirectedWeightedGraph
     */
    @Override
    public DirectedWeightedGraph copy() {
        HashMap<Integer, NodeData> nodes = new HashMap<>();
        HashMap<NodeData, HashMap> edges = new HashMap<>();
        DirectedWeightedGraph newGraph = new Graph(nodes, edges);

        for (NodeData n : this.g.getNodes().values()) {

            Location l = new Location(n.getLocation().x(), n.getLocation().y(), 0);;
            Node newNode = new Node(n.getKey(), l);
            newGraph.addNode(newNode);

            HashMap<Integer, EdgeData> hm = this.g.getEdges().get(n);
            for (EdgeData edge : hm.values()) {
                newGraph.connect(edge.getSrc(), edge.getDest(), edge.getWeight());
            }
        }
        return newGraph;
    }

    /**
     * check if the directed graph is strongly connected
     * @return boolean
     */
    @Override
    public boolean isConnected() {
        if((g.edgeSize() == 0) && (g.nodeSize() == 1)) { return true;}
        if (g.edgeSize() < g.nodeSize()) { return false;}
        if (g.edgeSize() == g.nodeSize()) {
            for(int i=0; i<g.nodeSize(); i++){
                if ((g.getOut()[i].size() != 1) && (g.getOut()[i] != null)){
                    return false;
                }
                if ((g.getIn()[i].size() != 1) && (g.getIn()[i] != null)){
                    return false;
                }
            }
        }

        resetTags();

        int firstNodeId = (int) this.g.getNodes().keySet().toArray()[0];
        boolean accessible = DFS(firstNodeId, this.g);

        if (accessible == false) {
            return false;
        }

        HashMap<NodeData,HashMap> reverseArrows = new HashMap<>();
        for (NodeData n : g.getNodes().values()) {
            reverseArrows.put(n, new HashMap());
        }
        Graph reverse = new Graph(g.getNodes(), reverseArrows);


        for (NodeData n : this.g.getNodes().values()) {
            HashMap<Integer, EdgeData> hm = this.g.getEdges().get(n);
            for (EdgeData edge : hm.values()) {
                reverse.connect(edge.getDest(), edge.getSrc(), edge.getWeight());
            }
        }

        resetTags();

        boolean reverseAccessible = DFS(firstNodeId, reverse);

        if (reverseAccessible == false) {
            return false;
        }
        return true;
    }

    private boolean DFS(int firstNodeId, DirectedWeightedGraph g) {
        boolean[] visited = new boolean[this.g.nodeSize()];
        Stack<Integer> stack = new Stack<>();
        visited[firstNodeId] = true;
        stack.push(firstNodeId);
        while (stack.isEmpty() == false) {
            int node = stack.pop();
            Iterator<EdgeData> itr = g.edgeIter(node);
            while (itr.hasNext()) {
                EdgeData e = itr.next();
                if (visited[e.getDest()] == false) {
                    visited[e.getDest()] = true;
                    stack.add(e.getDest());
                }
            }
        }
        for (int i=0; i<visited.length; i++) {
            if (visited[i] == false) {
                return false;
            }
        }
        return true;
    }

    public void resetTags() {
        for (NodeData n : this.g.getNodes().values()) {
            n.setTag(0);
        }
    }


    @Override
    public double shortestPathDist(int src, int dest) {

//        if (isConnected() == false) {
//            return -1;
//        }

        HashMap<Integer, NodeData> nodesMap = new HashMap<>();
        for (NodeData nodeData : g.getNodes().values()) {
            nodeData.setWeight(Integer.MAX_VALUE);
        }

        NodeData start = g.getNode(src);
        start.setWeight(0);
        nodesMap.put(src, start);

        while (nodesMap.isEmpty() == false) {
            NodeData currentNode = this.findMinimumDistanceNode(nodesMap);
            if (currentNode.getKey() == dest) {
                break;
            }
            int currentNodeKey = currentNode.getKey();
            double currentNodeDistance = currentNode.getWeight();
            nodesMap.remove(currentNodeKey);

            HashMap<Integer, Edge> hm = g.getEdges().get(currentNode);
            for (Edge e : hm.values()) {
                Integer neighbor = e.getDest();
                double distanceToNeighbor = g.getEdge(currentNodeKey, neighbor).getWeight() + currentNodeDistance;
                Node nei = (Node) g.getNode(neighbor);

                if (distanceToNeighbor < nei.getWeight() ) {
                    nei.setWeight(distanceToNeighbor);
                    nodesMap.put(neighbor, nei);
                }
            }
        }

        double distance = g.getNode(dest).getWeight();

        return distance;
    }

    private NodeData findMinimumDistanceNode(HashMap<Integer, NodeData> nodesMap) {
        NodeData minimumDistanceNode = null;
        double minimumDistance = Integer.MAX_VALUE;
        for (NodeData node : nodesMap.values()) {
            if (node.getWeight() <= minimumDistance) {
                minimumDistanceNode = node;
                minimumDistance = node.getWeight();
            }
        }

        return minimumDistanceNode;
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        HashMap<Integer, NodeData> nodesMap = new HashMap<>();
        for (NodeData nodeData : g.getNodes().values()) {
            nodeData.setWeight(Integer.MAX_VALUE);
        }
        HashMap<Integer, LinkedList<NodeData>> shortestPathMap = new HashMap<>();
        for (NodeData nodeData : g.getNodes().values()) {
            shortestPathMap.put(nodeData.getKey(), new LinkedList<>());
        }
        NodeData start = g.getNode(src);
        start.setWeight(0);
        nodesMap.put(src, start);
        while (nodesMap.isEmpty() == false) {
            NodeData currentNode = this.findMinimumDistanceNode(nodesMap);
            if (currentNode.getKey() == dest) {
                break;
            }
            int currentNodeKey = currentNode.getKey();
            double currentNodeDistance = currentNode.getWeight();
            nodesMap.remove(currentNodeKey);
            LinkedList<NodeData> currentNodePath;
            currentNodePath = shortestPathMap.get(currentNodeKey);


            HashMap<Integer, Edge> hm = g.getEdges().get(currentNode);
            for (Edge e : hm.values()) {
                int neighbor = e.getDest();
                double distanceToNeighbor = g.getEdge(currentNodeKey, neighbor).getWeight() + currentNodeDistance;
                Node nei = (Node) g.getNode(neighbor);
                if (distanceToNeighbor < nei.getWeight()) {
                    LinkedList<NodeData> currentNeighborPath = new LinkedList<>(currentNodePath);
                    currentNeighborPath.add(nei);
                    shortestPathMap.put(nei.getKey(), currentNeighborPath);
                    nei.setWeight(distanceToNeighbor);
                    nodesMap.put(nei.getKey(), nei);
                }
            }
        }
        return shortestPathMap.get(dest);
    }

    //da extra
    @Override
    public NodeData center() {
        double mincenter = Double.MAX_VALUE;
        NodeData center = null;
        for (NodeData node : g.getNodes().values()) {
            double biggestDistance = 0;
            for (int i = 0; i < g.nodeSize(); i++) {
                if (node.getKey() != g.getNodes().get(i).getKey()) {
                    double distance = shortestPathDist(node.getKey(), g.getNodes().get(i).getKey());
                    if (distance > biggestDistance) {
                        biggestDistance = distance;
                    }
                }
            }
            if (biggestDistance < mincenter) {
                mincenter = biggestDistance;
                center = node;
            }
        }
        return center;
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {

        List<NodeData> ans = new LinkedList<>();

        NodeData current = cities.get(0);

        while (true) {
            if (cities.size() == 0)  {
                break;
            }

            NodeData minNode = null;
            double minDist = Double.MAX_VALUE;

            for (int i=0; i<cities.size(); i++){
                if (current.getKey() == cities.get(i).getKey()) {
                    continue;
                }
                double t = shortestPathDist(current.getKey(), cities.get(i).getKey());
                if(t < minDist) {
                    minDist = t;
                    minNode = cities.get(i);
                }
            }
            ans.add(current);
            cities.remove(current);
            current = minNode;


        }

        return ans;
    }

    public List<NodeData> copyList(List<NodeData> list){
        List<NodeData> newList = new LinkedList<>();
        for(int i=0; i< list.size();i++){
            newList.add(list.get(i));
        }
        return list;
    }

    @Override
    public boolean save(String file){

        JsonArray Edges = new JsonArray();
        JsonArray Nodes = new JsonArray();


        Iterator edges = this.g.edgeIter();
        while(edges.hasNext()) {
            EdgeData current = (EdgeData) edges.next();

            JsonObject EdgeToJson = new JsonObject();
            EdgeToJson.addProperty("src", current.getSrc());
            EdgeToJson.addProperty("w", current.getWeight());
            EdgeToJson.addProperty("dest", current.getDest());

            Edges.add(EdgeToJson);
        }

        Iterator nodes = this.g.nodeIter();
        while(nodes.hasNext()) {
            NodeData current = (NodeData) nodes.next();

            JsonObject NodeToJson = new JsonObject();
            NodeToJson.addProperty("pos", " " + current.getLocation().x() +
                    "," + current.getLocation().y() + "," + current.getLocation().z());
            NodeToJson.addProperty("id", current.getKey());

            Nodes.add(NodeToJson);
        }

        JsonObject graphToJson = new JsonObject();
        graphToJson.add("Edges", Edges);
        graphToJson.add("Nodes", Nodes);


        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(graphToJson);

        try {
            PrintWriter pw = new PrintWriter(file);
            pw.write(json);
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean load (String file){
        try {
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(Graph.class, new JsonToGraph());
            Gson gson = builder.create();

            FileReader reader = new FileReader(file);
            this.g = gson.fromJson(reader, Graph.class);
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

}