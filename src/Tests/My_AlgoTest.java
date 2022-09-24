package Tests;

import Ex2.Graph;
import Ex2.My_Algo;
import api.DirectedWeightedGraph;
import api.NodeData;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class My_AlgoTest {

    HashMap<Integer, NodeData> nodes = new HashMap<>();
    HashMap<NodeData, HashMap> edges = new HashMap<>();

    Graph g = new Graph(nodes, edges);
    My_Algo alg = new My_Algo(g);

    @Test
    void isConnected() {
        alg.load("data/NotConnected.json");
        assertFalse(alg.isConnected());

        alg.load("data/G1.json");
        boolean b = alg.isConnected();
        assertTrue(b);
    }


    @Test
    void shortestPathDist() {
        alg.load("data/G1.json");
        double t = alg.shortestPathDist(2,7);
        double a = 3.031440459773105;
        assertTrue(dest(t, a));
    }

    public static boolean dest(double t, double a) {
        double EPS = 0.0001;

        if ((a - EPS <= t) || (t <= a + EPS)) {
            return true;
        }
        return false;
    }

    @Test
    void shortestPath() {
        alg.load("data/G1.json");
        List<NodeData> test = alg.shortestPath(2, 7);
        for (NodeData n : test) {

        }
        int[] ans = {6,7};

        for (int i=0; i<test.size();i++){
            assertEquals(ans[i],test.get(i).getKey());
        }
    }

    @Test
    void center() {
        alg.load("data/G1.json");
        NodeData center = alg.getGraph().getNode(40);
        System.out.println(alg.center().getKey());
        assertEquals(alg.center(), center);
    }

    @Test
    void tsp() {
        alg.load("data/G2.json");
        List<NodeData> cities = new LinkedList<>();
        List<NodeData> ans = new LinkedList<>();
        int listAns[] = {0,21,22,23,24,25,26,8,7,27,6,5,28,4,3,2,1,16,15,14,17,18,19,20,30,13,12,11,10,9,29};
        for (int i=0; i<alg.getGraph().nodeSize(); i++) {
            cities.add(alg.getGraph().getNode(i));
        }
        ans = alg.tsp(cities);

        for (int i=0; i<alg.getGraph().nodeSize(); i++) {
            assertEquals(ans.get(i).getKey(),listAns[i]);
        }
    }

    @Test
    void save() {
        alg.load("data/G1.json");
        alg.save("data/savingTest.json");
    }

}