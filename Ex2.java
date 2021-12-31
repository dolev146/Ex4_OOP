import Classes.G;
import Classes.GA;
import FileHandling.StoreNE;
import Interfaces.DirectedWeightedGraph;
import Interfaces.DirectedWeightedGraphAlgorithms;
import gui.graph.GFrame;


import static FileHandling.CImport.importJson;

/**
 * This class is the main class for Ex2 - your implementation will be tested
 * using this class.
 */
public class Ex2 {
    /**
     * This static function will be used to ClassesTest your implementation
     * for testing: "Ex2/data/G1.json"
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return ans - DirectedWeightedGraph
     */
    public static DirectedWeightedGraph getGrapg(String json_file) {
        StoreNE ne = importJson(json_file);
        if (ne != null) {
            return new G(ne.nodes, ne.edges);
        }
        return null;
    }

    /**
     * This static function will be used to ClassesTest your implementation
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return ans - DirectedWeightedGraphAlgorithms
     */
    public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) {
        DirectedWeightedGraphAlgorithms ans = new GA();
        // ****** Add your code here ******
        ans.init(getGrapg(json_file));
        // ********************************
        return ans;
    }

    /**
     * This static function will run your GUI using the json fime.
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     */
    public static void runGUI(String json_file) {
        DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);
        // ****** Add your code here ******
        new GFrame(alg);
        // ********************************
    }

    public static void main(String[] args) {

        runGUI(args[0]);


    }
}