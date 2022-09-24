package Ex2;

import DG_GUI.Frame;
import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.NodeData;

import java.util.HashMap;

/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraph getGrapg(String json_file) {
        DirectedWeightedGraph ans = null;
        // ****** Add your code here ******

        HashMap<Integer, NodeData> nodes = new HashMap<>();
        HashMap<NodeData, HashMap> edges = new HashMap<>();
        ans = new Graph(nodes, edges);

        My_Algo algo = new My_Algo((Graph) ans);
        algo.load(json_file);
        // ********************************
        return ans;
    }
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) {
        DirectedWeightedGraphAlgorithms ans = null;
        // ****** Add your code here ******
        //
        // ********************************

        HashMap<Integer, NodeData> nodes = new HashMap<>();
        HashMap<NodeData, HashMap> edges = new HashMap<>();
        Graph g = new Graph(nodes, edges);
        ans = new My_Algo(g);
//        ans.init(g);
        ans.load(json_file);

        return ans;
    }
    /**
     * This static function will run your GUI using the json fime.
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     *
     */
    public static void runGUI(String json_file) {
        DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);
        // ****** Add your code here ******
        //
        // ********************************

        Frame frame = new Frame(alg);
//        Frame frame = new Frame();
    }

    public static void main(String[] args) {
        DirectedWeightedGraphAlgorithms a = getGrapgAlgo("data/G1.json");
        runGUI("data/G1.json");
    }
}